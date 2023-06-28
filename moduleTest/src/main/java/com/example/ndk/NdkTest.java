package com.example.ndk;

import android.util.Log;

public class NdkTest {
    final static String TAG = "NdkTest";

    static {
        System.loadLibrary("native-lib");
    }
    static NdkTest ndkTest;

    static public NdkTest getInstance() {
        if(ndkTest==null) {
            ndkTest = new NdkTest();
        }
        return ndkTest;
    }

    NdkTest() {
        Log.d(TAG, "NdkTest() start");
        setActivityContextToJni(this);
    }

    public void connectAndChangeUiJNI() {
        Log.d(TAG, "connectAndChangeUiJNI()");
    }


    public native void setActivityContextToJni(NdkTest ndkTest);
    public native String getString();
    public native void callConnectAndChangeUiJNI();
}
