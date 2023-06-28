#include <jni.h>
#include "JniHelper.h"
//
// Created by rebestm on 2023-06-27.
//

/**
 * jnio
 * @param jvm
 * @param reserved
 * @return
 */
jint JNI_OnLoad(JavaVM *vm, void *reserved)
{
    LOGD("JNI_OnLoad_test");

    // init
    JniHelper::setJavaVM(vm);
    JniHelper::setMethodIds();

    return JNI_VERSION_1_4;
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_ndk_NdkTest_setActivityContextToJni(JNIEnv *env, jobject thiz, jobject ndk_test) {
    // TODO: implement setActivityContextToJni()
    JniHelper::setClassLoaderFrom(ndk_test);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_ndk_NdkTest_getString(JNIEnv *env, jobject thiz) {
    // TODO: implement getString()
    return env->NewStringUTF("hello jni");
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_ndk_NdkTest_callConnectAndChangeUiJNI(JNIEnv *env, jobject thiz) {
    // TODO: implement callConnectAndChangeUiJNI()
    env->CallVoidMethod(JniHelper::jContext,JniHelper::getMethodId(JNI_connectAndChangeUiJNI));
}
