package com.cy.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * created by cy on 2019/11/27 0027.
 */
public class Test {
    private static final String TAG = "Test";
    public static  void  main(String[] args){
        List< String > commandList = new ArrayList< String >();
        commandList.add("logcat" );
        commandList.add( "-f" );
        commandList.add( "log_path" );
        commandList.add( "-v" );
        commandList.add( "time" );
        String[] s = commandList.toArray(new String[commandList.size()]);
        System.out.println(s);
    }
}
