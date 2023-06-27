package com.example.basic;

public class InstanceClass {
    static String TAG = "MyLibrary";

    static InstanceClass instanceClass =null;

    public static InstanceClass getIns() {
        if(instanceClass ==null) {
            instanceClass = new InstanceClass();
        }
        return instanceClass;
    }

    public String makeMainString() {
        return "testMyLibrary";
    }
}
