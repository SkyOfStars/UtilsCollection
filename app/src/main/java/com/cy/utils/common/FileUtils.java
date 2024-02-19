package com.cy.utils.common;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.IntDef;


import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 文件流保存数据
 */
public class FileUtils {

    private static final String TAG = "FileUtils";
    public static final int ASC = 1; // 正序
    public static final int DESC = 2; // 倒序

    @IntDef(flag = false, value = {ASC, DESC})
    //注解作用域参数、方法、成员变量
    @Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
    //仅仅在源码阶段有效
    @Retention(RetentionPolicy.SOURCE)
    public @interface Order {

    }

    public interface ReadFileCallback {
        void sucess(String content);

        void error(Exception e);
    }

    public interface WriteFileCallback {
        void sucess();

        void error(Exception e);
    }

    /**
     * 将数据写入文件(/sys/class/qcom-battery/usb_switch_to_charge)
     *
     * @param filePath 文件路径
     * @param data     字符串数据
     */
    public static void writeToFile(String data, String filePath, WriteFileCallback callback) {
        BufferedWriter writer = null;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file, false);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            fileOutputStream.write(data.getBytes());
            if (callback != null) {
                callback.sucess();
            }
        } catch (Exception e) {
            if (callback != null) {
                callback.error(e);
            }
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeFile(String filePath, String content) {
        Log.i(TAG, "writeFile: ");
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            if (TextUtils.isEmpty(filePath)) {
                return;
            }
            Log.i(TAG, "writeFile: path:" + filePath);
            File file = new File(filePath);
            if (!FileUtils.checkAndCreatFile(file)) {
                Log.i(TAG, "writeFile: create fail");
                return;
            }
            FileOutputStream os = null;
            try {
                os = new FileOutputStream(file);
                os.write(content.getBytes(StandardCharsets.UTF_8));
                Log.i(TAG, "write: over");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取数据，一次返回所有文本
     *
     * @param filePath 表示文件路径
     */
    public static String readFromFile(String filePath, ReadFileCallback callback) {
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream inputStream = null;
            BufferedReader reader = null;
            try {
                inputStream = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String str = null;
                StringBuffer stringBuffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    stringBuffer.append(str);
                }
                if (callback != null) {
                    callback.sucess(stringBuffer.toString());
                }
                return stringBuffer.toString();
            } catch (FileNotFoundException e) {
                if (callback != null) {
                    callback.error(e);
                }
            } catch (IOException e) {
                if (callback != null) {
                    callback.error(e);
                }
            } catch (Exception e) {
                if (callback != null) {
                    callback.error(e);
                }
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取目录下所有文件(按时间倒序排序)
     *
     * @param path  目录
     * @param order ASC: 正序  DESC：倒序
     * @return 返回排序后的文件列表
     */
    public static List<File> listFileSortByModifyTime(String path, @Order int order) {
        List<File> list = getFiles(path);
        if (list != null && list.size() > 0) {
            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {
                        return order == DESC ? 1 : -1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return order == DESC ? -1 : 1;
                    }
                }
            });
        }
        return list;
    }

    /**
     * 获取目录下所有文件（递归获取，包括子文件夹）
     *
     * @param dirPath 目录
     * @return 返回所有文件集合
     */
    public static List<File> getFiles(String dirPath) {
        List<File> files = new ArrayList();
        File realFile = new File(dirPath);
        if (realFile.isDirectory()) {
            File[] subFiles = realFile.listFiles();
            for (File file : subFiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath());
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null) {
                Result += line;
            }
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 文件中写入字符串
     *
     * @param file
     * @param value
     */
    public static boolean write2File(File file, String value) {
        if ((file == null) || (!file.exists())) {
            return false;
        }
        try {
            FileOutputStream fout = new FileOutputStream(file);
            PrintWriter pWriter = new PrintWriter(fout);
            pWriter.println(value);
            pWriter.flush();
            pWriter.close();
            fout.close();
            return true;
        } catch (IOException re) {
            return false;
        }
    }

    public static String getDiskCacheDir(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            if (context.getExternalCacheDir() != null) {
                return context.getExternalCacheDir().getPath();
            }
        } else {
            if (context.getCacheDir() != null) {
                return context.getCacheDir().getPath();
            }
        }
        return "";
    }

    /**
     * 从assets目录中复制文件到新目录
     *
     * @param srcPath  String  原assets文件相对路径
     * @param destPath String  目标绝对路径
     */
    public static boolean copyFilesFromAssets(Context context, String srcPath, String destPath) {

        try {
            File destFile = new File(destPath);
            if (!destFile.exists()) {
                File parentFile = destFile.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    if (!parentFile.mkdirs()) {
                        return false;
                    }
                }

                if (!destFile.createNewFile()) {
                    return false;
                }
            }

            //如果是文件
            InputStream fis = context.getAssets().open(srcPath);
            FileOutputStream fos = new FileOutputStream(destPath);
            byte[] buffer = new byte[1024];
            int len = 0;
            //循环从输入流读取 buffer字节
            while ((len = fis.read(buffer)) != -1) {
                //将读取的输入流写入到输出流
                fos.write(buffer, 0, len);
            }
            //刷新缓冲区
            fos.flush();
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean checkAndCreatFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.isFile()) {
            return true;
        } else {
            try {
                boolean newFile = file.createNewFile();
                return newFile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean checkAndCreatFile(String Filepath) {
        if (TextUtils.isEmpty(Filepath)) {
            return false;
        }
        File file = new File(Filepath);
        return checkAndCreatFile(file);
    }
}
