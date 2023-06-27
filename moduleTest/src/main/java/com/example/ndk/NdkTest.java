package com.example.ndk;

public class NdkTest {
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


    public native String getString();
}
