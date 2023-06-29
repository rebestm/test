package com.example.ndk;

import android.util.Log;

import com.example.callback.JniCallBack;

public class NdkTest {
    final static String TAG = "NdkTest";


    static NdkTest ndkTest;

    static public NdkTest getInstance() {
        if(ndkTest==null) {
            ndkTest = new NdkTest();
        }
        return ndkTest;
    }


    /**
     * 여기사항을 app으로 올려준다.
     */
    static public JniCallBack jniCallBack = null;


    NdkTest() {
        Log.d(TAG, "NdkTest() start");
        
        // jni -> app 호출을 위해 class 등록
        setActivityContextToJni(this);
    }


    /**
     * jniCallBack 등록
     * @param jniCallBack
     */
    public static void setJniCallBack(JniCallBack jniCallBack) {
        NdkTest.jniCallBack = jniCallBack;
    }

    /**
     * JNI callback
     */
    public void connectAndChangeUiJNI() {
        Log.d(TAG, "connectAndChangeUiJNI()");
        if(jniCallBack!=null) {
            jniCallBack.jniHelloCallBack();
        }
    }


    /**
     * JNI
     */
    static {
        System.loadLibrary("native-lib");
    }
    public native void setActivityContextToJni(NdkTest ndkTest);
    public native String getString();
    public native void callConnectAndChangeUiJNI();
}
