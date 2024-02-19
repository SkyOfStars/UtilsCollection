package com.cy.utils.http;

import android.util.Base64;

import com.bumptech.glide.load.Key;

import java.io.*;

public class QCast {

    public interface CallBack {
        void callBack(String str);
    }

    private static native byte[] getStringByNative(byte[] bArr);

    public static native String getStringByNativeTwo(String str);

    static {
        System.loadLibrary("qcast_data");
    }

    public static String getStringInternal(String data) throws UnsupportedEncodingException {
        try {
            byte[] bytes = new String(getStringByNative(Base64.decode(data, 0))).getBytes();
            byte num = bytes[bytes.length - 1];
            if (num <= 0 || num > 16 || num >= bytes.length) {
                return new String(bytes, Key.STRING_CHARSET_NAME);
            }
            for (int i = 1; i < num; i++) {
                if (num != bytes[(bytes.length - i) - 1]) {
                    return new String(bytes, Key.STRING_CHARSET_NAME);
                }
            }
            return new String(bytes, 0, bytes.length - num, Key.STRING_CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static String getString(String data) {
        try {
            return getStringInternal(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static void getStringSync(final String data, final CallBack callback) {
        new Thread(() -> {
            try {
                callback.callBack(QCast.getStringInternal(data));
            } catch (UnsupportedEncodingException e) {
                callback.callBack("error");
                e.printStackTrace();
            }
        }).start();
    }
}
