package com.cy.utils.common;

import android.Manifest;
import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import static androidx.core.app.ActivityCompat.checkSelfPermission;

/**
 * 本机信息帮助类
 */

public final class DeviceUtil {

    private DeviceUtil(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    private static CpuInfo last;

    /**
     * 获取进程数的方法
     *
     * @param context
     * @return
     */
    public static int getProcessCount(Context context) {
        int size = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager m = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            if (m != null) {
                long now = System.currentTimeMillis();
                //获取60秒之内的应用数据
                List<UsageStats> stats = m.queryUsageStats(UsageStatsManager.INTERVAL_BEST, now - 60 * 1000, now);
                size = stats.size();
            }
        } else {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            //获取进程的集合
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses != null) {
                size = runningAppProcesses.size();
            }
        }
        return size;
    }

    /**
     * 获取栈顶的包名
     *
     * @param context
     * @return
     */
    public static String getTopApp(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager m = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            if (m != null) {
                long now = System.currentTimeMillis();
                //获取60秒之内的应用数据
                List<UsageStats> stats = m.queryUsageStats(UsageStatsManager.INTERVAL_BEST, now - 60 * 1000, now);
                //取得最近运行的一个app，即当前运行的app
                if ((stats != null) && (!stats.isEmpty())) {
                    int j = 0;
                    for (int i = 0; i < stats.size(); i++) {
                        if (stats.get(i).getLastTimeUsed() > stats.get(j).getLastTimeUsed()) {
                            j = i;
                        }
                    }
                    return stats.get(j).getPackageName();
                }
            }
        } else {
            ActivityManager manager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            if (manager != null) {
                return manager.getRunningTasks(1).get(0).topActivity.getPackageName();
            }
        }
        return null;
    }

    /**
     * 获取可用内存的数据大小 ，单位为byte
     *
     * @param context
     * @return
     */
    public static long getAvailSpace(Context context) {
        //获取activityManager
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //构建可用内存对象
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        if (activityManager != null) {
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo.availMem;
        }
        return 0;
    }

    public static long getTotalSpace(Context context) {
        //获取activityManager
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //构建可用内存对象
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        if (activityManager != null) {
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo.totalMem;
        }
        return 0;
    }

    /**
     * 获取cpu总得使用率
     *
     * @return
     */
    public static double getCpuUsageRate() {
        double rate = 0.0;
        if (last == null) {
            last = getCpuInfo();
        }
        CpuInfo now = getCpuInfo();
        if (last != null && now != null) {
            double total = now.getTotalCpuTime() - last.getTotalCpuTime();
            if (total != 0) {
                double idle = now.idle - last.idle;
                rate = 100 * (total - idle) / total;
                last = now;
            }
        }
        return rate;
    }

    /**
     * 获取内存使用率
     *
     * @return
     */
    public static double getMemUsageRate() {
        double rate = 0.0;
        MemInfo info = getMemInfo();
        if (info != null) {
            rate = 100 * info.memFree / (double) info.memTotal;
        }
        return rate;
    }

    /**
     * 获取内存信息
     *
     * @return
     */
    public static MemInfo getMemInfo() {
        RandomAccessFile procMemFile = null;
        MemInfo info = null;
        try {
            procMemFile = new RandomAccessFile("proc/meminfo", "r");
            info = new MemInfo();
            String line;
            String[] strs;
            while ((line = procMemFile.readLine()) != null) {
                strs = line.split(":");
                if (strs.length >= 2) {
                    if ("MemTotal".equals(strs[0])) {
                        info.memTotal = Integer.parseInt(getNumber(strs[1]));
                    } else if ("MemFree".equals(strs[0])) {
                        info.memFree = Integer.parseInt(getNumber(strs[1]));
                    } else if ("MemAvailable".equals(strs[0])) {
                        info.memAvailable = Integer.parseInt(getNumber(strs[1]));
                    } else if ("Buffers".equals(strs[0])) {
                        info.buffers = Integer.parseInt(getNumber(strs[1]));
                    } else if ("Cached".equals(strs[0])) {
                        info.cached = Integer.parseInt(getNumber(strs[1]));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (procMemFile != null) {
                try {
                    procMemFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }

    /**
     * 获取cpu使用信息
     *
     * @return
     */
    public static CpuInfo getCpuInfo() {
        RandomAccessFile statFile = null;
        CpuInfo info = null;
        try {
            statFile = new RandomAccessFile("proc/stat", "r");
            info = new CpuInfo();
            String line = statFile.readLine();
            if (line != null) {
                String[] strs = line.replace("  ", " ").split(" ");
                if (strs.length >= 8) {
                    info.user = Integer.parseInt(getNumber(strs[1]));
                    info.nice = Integer.parseInt(getNumber(strs[2]));
                    info.system = Integer.parseInt(getNumber(strs[3]));
                    info.idle = Integer.parseInt(getNumber(strs[4]));
                    info.iowait = Integer.parseInt(getNumber(strs[5]));
                    info.irq = Integer.parseInt(getNumber(strs[6]));
                    info.softirq = Integer.parseInt(getNumber(strs[7]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statFile != null) {
                try {
                    statFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return info;
    }

    /**
     * 获取设备id
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String id = "";
        if (checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (mTelephonyMgr != null) {
                id = mTelephonyMgr.getDeviceId(); //获取IMEI号
                if (id == null || id.trim().length() == 0) {
                    id = mTelephonyMgr.getSubscriberId(); //获取IMSI号
                }
            }
        }
        if (id == null) {
            id = "";
        }
        return id;
    }

    public static String getUniqueID(Context context) {

        String mac = getMacAddress(context);
        if (mac != null) {
            mac = mac.replace(":", "");
        } else {
            mac = "";
        }
        LogUtil.i("DeviceUtil getUniqueID==" + Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID) + mac);
        return Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID) + mac;
    }

    /**
     * 获取Ethernet的MAC地址
     *
     * @return
     */
    public static String getMacAddress(Context context) {
        try {
            return loadFileAsString("/sys/class/net/eth0/address").toUpperCase(Locale.ENGLISH).substring(0, 17);
        } catch (IOException e) {
            return null;
        }
    }

    private static String loadFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }

    private static String getNumber(String str) {
        char[] chars = str.toCharArray();
        StringBuilder buffer = new StringBuilder();
        for (char aChar : chars) {
            if (aChar >= '0' && aChar <= '9') {
                buffer.append(aChar);
            }
        }
        return buffer.toString();
    }

    /**
     * 获取设备ip地址
     *
     * @param context
     * @return
     */
    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE || info.getType() == ConnectivityManager.TYPE_ETHERNET) {//当前使用2G/3G/4G/以太网 网络
                try {
                    Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                    while (en.hasMoreElements()) {
                        NetworkInterface intf = en.nextElement();
                        Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                        while (enumIpAddr.hasMoreElements()) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                try {
                    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    return intIP2StringIP(wifiInfo.getIpAddress());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    public static class MemInfo {
        public int memTotal;
        public int memFree;
        public int memAvailable;
        public int buffers;
        public int cached;

        @Override
        public String toString() {
            return "MemInfo{" +
                    "memTotal=" + memTotal +
                    ", memFree=" + memFree +
                    ", memAvailable=" + memAvailable +
                    ", buffers=" + buffers +
                    ", cached=" + cached +
                    '}';
        }
    }

    public static class CpuInfo {
        public int user;
        public int nice;
        public int system;
        public int idle;
        public int iowait;
        public int irq;
        public int softirq;

        public int getTotalCpuTime() {
            return user + nice + system + idle + iowait + irq + softirq;
        }

        @Override
        public String toString() {
            return "CpuInfo{" +
                    "user=" + user +
                    ", nice=" + nice +
                    ", system=" + system +
                    ", idle=" + idle +
                    ", iowait=" + iowait +
                    ", irq=" + irq +
                    ", softirq=" + softirq +
                    '}';
        }
    }
}
