package com.wf.news.util;

/**
 * Created by Kesson on 2015/9/20.
 */
public class NDKUtil {
    // please make proj before javah
    // cd src/main
    //javah -d jni -classpath D:\StudioSDK\platforms\android-22\android.jar;..\..\build\intermediates\classes\debug com.wf.news.util.NDKUtil
    public static native String getStringFromNative();

    public native int sum(int a, int b);

    static {
        System.loadLibrary("MyJni");
    }
}

