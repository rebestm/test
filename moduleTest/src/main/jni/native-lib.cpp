#include <jni.h>

//
// Created by rebestm on 2023-06-27.
//

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_ndk_NdkTest_getString(JNIEnv *env, jobject thiz) {
    // TODO: implement getString()
    return env->NewStringUTF("hello jni");
}