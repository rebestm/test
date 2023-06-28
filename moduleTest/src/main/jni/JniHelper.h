//
// Created by rebestm on 2023-06-28.
//

#ifndef ALLINONE_JNIHELPER_H
#define ALLINONE_JNIHELPER_H

#include <jni.h>
#include <string>

//DE
#define CC_TARGET_PLATFORM        CC_PLATFORM_ANDROID
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
//ANDROID
#include <android/log.h>
#define  LOG_TAG    "CR9G_SERVER"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#else
#define  LOGE(...)  printf(__VA_ARGS__)
#define  LOGD(...)  printf(__VA_ARGS__)
#endif


#define JAVACLASSNAME "com/example/ndk/NdkTest"


enum
{
    JNI_connectAndChangeUiJNI,
    JNI_disconnectedJNI,
    JNI_callBackFromConnectResultJNI,
    JNI_wallPadIsJoinedJNI,
    JNI_masterExitJNI,
    JNI_recvChatMsgJNI,
    JNI_recvMoveJNI,
    JNI_recvRunJNI,
    JNI_recvAppIconsJNI,
    JNI_recvBitmapJNI,
    JNI_recvUDPMoveJNI,
    JNI_recvSyncAppIconsJNI,
    JNI_recvUDPBitmapJNI,
    JNI_recvUDPRTPVIDEOJNI,
    JNI_recvWidgetInfoJNI,
    JNI_exitUserJNI,
    JNI_recvHeartBeatJNI,
    JNI_MAX
};


typedef struct JniMethodInfo_
{
    JNIEnv *    env;
    jclass      classID;
    jmethodID   methodID;
} JniMethodInfo;

class JniHelper
{
public:
    static void setJavaVM(JavaVM *javaVM);
    static JavaVM* getJavaVM();
    static JNIEnv* getEnv();

    static bool setClassLoaderFrom(jobject activityInstance);


    static jobject jContext;

    static void setMethodIds();
    static jclass getClassId();
    static jmethodID getMethodId(int idx);
private:
    static JNIEnv* cacheEnv(JavaVM* jvm);



    static JavaVM* _psJavaVM;

    JniHelper() {
        jContext=NULL;
        _psJavaVM=NULL;
    }
};


#endif //ALLINONE_JNIHELPER_H






