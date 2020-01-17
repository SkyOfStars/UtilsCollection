package com.cy.utils;

import android.util.Log;

import com.cy.utils.common.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * created by cy on 2019/11/27 0027.
 */
public final class Test {
    private static final String TAG = "Test";

    public static void main(String[] args) {
        System.out.println("main: --time=" + DateUtil.getDateByFormat("2019-12-21 12:34:46", DateUtil.dateFormatYMDHMS));
    }
}
