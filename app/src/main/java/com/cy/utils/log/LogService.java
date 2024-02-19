package com.cy.utils.log;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.cy.utils.common.FileUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by CaoYanYan
 * Date: 2024/1/8 9:22
 **/
public class LogService extends Service {
    private static final String TAG = "LogService";
    public final static String KEY_LOG = "log";
    private Writer writer;
    private Handler sHandler;
    private SimpleDateFormat fileFormatter = new SimpleDateFormat("yyyyMMdd_hhmmss", Locale.US);
    /**
     * 日志根目录
     */
    private String logDir;
    /**
     * 日志流程控制开关
     */
    private static boolean logSwitch = false;
    /**
     * 文件保存时间
     */
    private static final long maxTimeMillis = 30 * 60 * 1000;
    /**
     * 清理文件周期间隔
     */
    private static final long clearFile_Duration = 1 * 60 * 1000;

    /**
     * 上次清理文件的时间
     */
    private long clearFileLastTime;
    /**
     * 支持文件名称可修改
     */
    private final boolean isLogFileNameChanged = true;
    private WriteWorker writeWorker;
    private ReadWorker readerWork;

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        sHandler = new Handler(handlerThread.getLooper());
        fileFormatter.setTimeZone(TimeZone.getDefault());
        logDir = FileUtils.getDiskCacheDir(getApplicationContext()) + File.separator + "burstlog";
    }


    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        if (intent != null) {
            boolean logState = intent.getBooleanExtra(KEY_LOG, false);
            Log.i(TAG, "onStartCommand: logState=" + logState);
            if (logState && !logSwitch) {
                logSwitch = true;
                startCaptureLog();
            } else {
                release();
                sHandler.postDelayed(() -> stopSelf(), 1000);

            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        release();
    }

    /**
     * 资源销毁
     */
    private void release() {
        logSwitch = false;
        if (readerWork != null) {
            readerWork.stop();
        }
        if (writeWorker != null) {
            writeWorker.stop();
        }
        sHandler.removeCallbacksAndMessages(null);
    }

    private synchronized void doWrite(String data) {
        if (!logSwitch) {
            return;
        }
        String lastFileName = writer.getLastFileName();
        boolean isWriterClosed = !writer.isOpened();
        if (lastFileName == null || isWriterClosed || isLogFileNameChanged) {
            String newFileName = generateFileName();
            if (newFileName == null || newFileName.trim().length() == 0) {
                return;
            }
            if (!newFileName.equals(lastFileName) || isWriterClosed) {
                writer.close();
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - clearFileLastTime > clearFile_Duration) {
                    cleanLogFilesIfNecessary();
                    clearFileLastTime = currentTimeMillis;
                }
                if (!writer.open(newFileName)) {
                    return;
                }
            }
        }
        writer.appendLog(data);
    }

    private String generateFileName() {
        return fileFormatter.format(new Date(System.currentTimeMillis())) + ".text";
    }

    private void cleanLogFilesIfNecessary() {
        File dir = new File(logDir);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (shouldCleanFile(file)) {
                file.delete();
            }
        }
    }


    public boolean shouldCleanFile(File file) {
        long currentTimeMillis = System.currentTimeMillis();
        long lastModified = file.lastModified();
        return (currentTimeMillis - lastModified > maxTimeMillis);
    }


    private void startCaptureLog() {
        checkLogFolder();
        writer = new Writer();
        if (logSwitch) {
            if (writeWorker == null) {
                writeWorker = new WriteWorker();
            }
            if (!writeWorker.isStarted()) {
                writeWorker.start();
            }
            if (readerWork == null) {
                readerWork = new ReadWorker();
            }
            if (!readerWork.isStarted()) {
                readerWork.start();
            }
        }
    }

    private void checkLogFolder() {
        Log.i(TAG, "checkLogFolder: logDir:" + logDir);
        File folder = new File(logDir);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    private void doRead(String msg) {
        if (logSwitch) {
            writeWorker.enqueue(msg);
        }
    }

    private class ReadWorker implements Runnable {
        private volatile boolean started;
        private volatile boolean readSwitch;

        public boolean isStarted() {
            synchronized (this) {
                return started;
            }
        }

        public void start() {
            synchronized (this) {
                if (started) {
                    return;
                }
                readSwitch = true;
                new Thread(this).start();
                started = true;
            }
        }

        public void stop() {
            if (readSwitch) {
                readSwitch = false;
            }
        }

        @Override
        public void run() {
            Log.i(TAG, "ReaderWork read start");
            String[] cmds = {"logcat", "-c"};
            String shellCmd = "logcat -b all";
            Process process = null;
            Runtime runtime = Runtime.getRuntime();
            BufferedReader reader = null;
            try {
                runtime.exec(cmds).waitFor();
                process = runtime.exec(shellCmd);
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String msg = null;
                while (readSwitch && (msg = reader.readLine()) != null) {
                    doRead(msg);
                }
            } catch (Exception e) {
                Log.i(TAG, "ReadWorker: Exception:" + e.getMessage());
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (process != null) {
                    process.destroy();
                }
                synchronized (this) {
                    readSwitch = false;
                    started = false;
                }
                Log.i(TAG, "ReaderWork read end");
            }
        }
    }


    private class WriteWorker implements Runnable {
        private BlockingQueue<String> logs = new LinkedBlockingQueue<>();
        private volatile boolean started;
        private volatile boolean writeSwitch;

        public void enqueue(String log) {
            try {
                logs.put(log);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public boolean isStarted() {
            synchronized (this) {
                return started;
            }
        }

        public void start() {
            synchronized (this) {
                if (started) {
                    return;
                }
                writeSwitch = true;
                new Thread(this).start();
                started = true;
            }
        }

        public void stop() {
            synchronized (this) {
                if (writeSwitch) {
                    writeSwitch = false;
                    logs.clear();
                }
            }
        }

        @Override
        public void run() {
            Log.i(TAG, "WriteWorker write start");
            String log;
            try {
                while (writeSwitch && (log = logs.take()) != null) {
                    doWrite(log);
                }
            } catch (Exception e) {
                Log.i(TAG, "WriteWorker: Exception:" + e.getMessage());
            } finally {
                synchronized (this) {
                    started = false;
                    writeSwitch = false;
                }
                Log.i(TAG, "WriteWorker write end");
            }
        }

    }

    /**
     * 用于控制文件读写
     */
    private class Writer {
        private String lastFileName;
        private File logFile;
        private BufferedWriter bufferedWriter;

        public boolean isOpened() {
            return bufferedWriter != null && logFile.exists();
        }

        public String getLastFileName() {
            return this.lastFileName;
        }

        public File getFile() {
            return this.logFile;
        }

        public boolean open(String newFileName) {
            lastFileName = newFileName;
            logFile = new File(logDir, newFileName);
            if (!logFile.exists()) {
                try {
                    File parent = logFile.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    close();
                    return false;
                }
            }

            try {
                bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
            } catch (Exception e) {
                e.printStackTrace();
                close();
                return false;
            }
            return true;
        }

        public boolean close() {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bufferedWriter = null;
            lastFileName = null;
            logFile = null;
            return true;
        }

        public void appendLog(String flattenedLog) {
            try {
                bufferedWriter.write(flattenedLog);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException e) {
            }
        }
    }
}
