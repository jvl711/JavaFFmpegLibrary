#include <jni.h>
#include <stdio.h>
#include "jvl_FFmpeg_jni_AVGlobal.h"
#include "libavformat/avformat.h"

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVGlobal_getAVErrorString(JNIEnv *env, jobject obj, jint errorNumber)
{
    return  (*env)->NewStringUTF(env, av_err2str(errorNumber));
}