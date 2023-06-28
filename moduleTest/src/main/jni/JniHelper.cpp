//
// Created by rebestm on 2023-06-28.
//

#include "JniHelper.h"
#include <android/log.h>
#include <pthread.h>


static pthread_key_t g_key;
jclass g_clazz=NULL;
jmethodID* g_jmethod=new jmethodID[JNI_MAX];


void JniHelper::setMethodIds()
{
    JNIEnv* env = JniHelper::getEnv();

    //set class
    g_clazz = env->FindClass(JAVACLASSNAME);

    if (NULL == g_clazz) {
        LOGE("Classloader failed to find class");
        env->ExceptionClear();
    }

    //set method
    g_jmethod[JNI_connectAndChangeUiJNI]= env->GetMethodID(g_clazz, "connectAndChangeUiJNI", "()V");
//    g_jmethod[JNI_disconnectedJNI]= env->GetMethodID(g_clazz, "disconnectedJNI", "()V");
//    g_jmethod[JNI_callBackFromConnectResultJNI]= env->GetMethodID(g_clazz, "callBackFromConnectResultJNI", "(I)V");
//    g_jmethod[JNI_wallPadIsJoinedJNI]= env->GetMethodID(g_clazz, "wallPadIsJoinedJNI", "()V");
//    g_jmethod[JNI_masterExitJNI]= env->GetMethodID(g_clazz, "masterExitJNI", "()V");
//    g_jmethod[JNI_recvChatMsgJNI]= env->GetMethodID(g_clazz, "recvChatMsgJNI", "(ILjava/lang/String;Ljava/lang/String;)V");
//    g_jmethod[JNI_recvMoveJNI]= env->GetMethodID(g_clazz, "recvMoveJNI", "(IFFI)V");
//    g_jmethod[JNI_recvRunJNI]= env->GetMethodID(g_clazz, "recvRunJNI", "(III)V");
//    g_jmethod[JNI_recvAppIconsJNI]= env->GetMethodID(g_clazz, "recvAppIconsJNI", "([BI)V");
//    g_jmethod[JNI_recvBitmapJNI]= env->GetMethodID(g_clazz, "recvBitmapJNI", "([BI)V");
//    g_jmethod[JNI_recvUDPMoveJNI]= env->GetMethodID(g_clazz, "recvUDPMoveJNI", "(IFFI)V");
//    g_jmethod[JNI_recvSyncAppIconsJNI]= env->GetMethodID(g_clazz, "recvSyncAppIconsJNI", "([II)V");
//    g_jmethod[JNI_recvUDPBitmapJNI]= env->GetMethodID(g_clazz, "recvUDPBitmapJNI", "([BI)V");
//    g_jmethod[JNI_recvWidgetInfoJNI]= env->GetMethodID(g_clazz, "recvWidgetInfoJNI", "(II[BI)V");
//    g_jmethod[JNI_exitUserJNI]= env->GetMethodID(g_clazz, "exitUserJNI", "(I)V");
//    g_jmethod[JNI_recvUDPRTPVIDEOJNI]= env->GetMethodID(g_clazz, "recvUDPRTPVIDEOJNI", "([BI)V");
//    g_jmethod[JNI_recvHeartBeatJNI]= env->GetMethodID(g_clazz, "recvHeartBeatJNI", "()V");

}

void _detachCurrentThread(void* a) {
    JniHelper::getJavaVM()->DetachCurrentThread();
}

void JniHelper::setJavaVM(JavaVM *javaVM) {
    pthread_t thisthread = pthread_self();
    LOGD("JniHelper::setJavaVM(%p), pthread_self() = %ld", javaVM, thisthread);
    _psJavaVM = javaVM;

    pthread_key_create(&g_key, _detachCurrentThread);
}

JNIEnv* JniHelper::getEnv() {
    JNIEnv *_env = (JNIEnv *)pthread_getspecific(g_key);
    if (_env == NULL)
        _env = JniHelper::cacheEnv(_psJavaVM);

    return _env;
}

JavaVM* JniHelper::_psJavaVM = NULL;
jobject JniHelper::jContext=NULL;

JavaVM* JniHelper::getJavaVM() {
    pthread_t thisthread = pthread_self();
    LOGD("JniHelper::getJavaVM(), pthread_self() = %ld", thisthread);
    return _psJavaVM;
}

JNIEnv* JniHelper::cacheEnv(JavaVM* jvm) {
    JNIEnv* _env = NULL;
    // get jni environment
    if(jvm==NULL) return NULL;

    jint ret = jvm->GetEnv((void**)&_env, JNI_VERSION_1_6);

    switch (ret) {
        case JNI_OK :
            // Success!
            pthread_setspecific(g_key, _env);
            return _env;

        case JNI_EDETACHED :
            // Thread not attached
            if (jvm->AttachCurrentThread(&_env, NULL) < 0)
            {
                LOGE("Failed to get the environment using AttachCurrentThread()");

                return NULL;
            } else {
                // Success : Attached and obtained JNIEnv!
                pthread_setspecific(g_key, _env);
                return _env;
            }

        case JNI_EVERSION :
            // Cannot recover from this error
            LOGE("JNI interface version 1.4 not supported");
        default :
            LOGE("Failed to get the environment using GetEnv()");
            return NULL;
    }
}

jmethodID JniHelper::getMethodId(int idx)
{
    return g_jmethod[idx];
}

bool JniHelper::setClassLoaderFrom(jobject activityinstance) {
    JniHelper::jContext=JniHelper::getEnv()->NewGlobalRef(activityinstance);

    return true;
}