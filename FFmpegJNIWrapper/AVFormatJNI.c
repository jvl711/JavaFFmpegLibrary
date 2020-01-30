/*
 * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html 
 */

#include <jni.h>
#include <stdio.h>
#include "jvl_FFmpeg_jni_AVFormatContext.h"
#include "libavformat/avformat.h"


JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_allocateContext(JNIEnv* env, jobject obj)
{
    AVFormatContext *pTestContext = avformat_alloc_context();
    
    jlong pointer = (intptr_t)pTestContext;
    
    return pointer;
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_freeContext(JNIEnv* env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    
    avformat_free_context(pFormatContext);
}


JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_openInput(JNIEnv *env, jobject obj, jlong avFormatPointer, jstring filePath)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    const char * filePathPointer;
    
    filePathPointer = (*env)->GetStringUTFChars(env,filePath, 0);
    
    (*env)->ReleaseStringUTFChars(env, filePath, filePathPointer);

    return avformat_open_input(&pFormatContext, filePathPointer, NULL, NULL);
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_closeInput(JNIEnv *env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;    

    avformat_close_input(&pFormatContext);
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_findStreamInfo(JNIEnv *env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    
    
    return avformat_find_stream_info(pFormatContext, NULL);
}


JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getDuration(JNIEnv *env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;

    return (jlong)pFormatContext->duration;
}

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getFormatLongName(JNIEnv *env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;

    return (*env)->NewStringUTF(env, pFormatContext->iformat->long_name);
}

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getFormatName(JNIEnv *env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;

    return (*env)->NewStringUTF(env, pFormatContext->iformat->name);
}

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getMimeType(JNIEnv *env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
   
    return (*env)->NewStringUTF(env, pFormatContext->iformat->mime_type);
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getNumberOfStreams(JNIEnv *env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    
    return (jlong)pFormatContext->nb_streams;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getAVCodecParameters(JNIEnv *env, jobject obj, jlong avFormatPointer, jint streamIndex)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    
    AVCodecParameters *pTestContext = pFormatContext->streams[streamIndex]->codecpar;

    jlong pointer = (intptr_t)pTestContext;
    
    return pointer;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getAVStream(JNIEnv *env, jobject obj, jlong avFormatPointer, jint streamIndex)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    
    AVStream *pTestContext = pFormatContext->streams[streamIndex];

    jlong pointer = (intptr_t)pTestContext;
    
    return pointer;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getBitrate(JNIEnv *env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    
    
    
    return pFormatContext->bit_rate;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_readFrame(JNIEnv *env, jobject obj, jlong avFormatPointer, jlong avPacketPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    AVPacket * pPacket = (AVPacket *)(intptr_t)avPacketPointer;
    
    return av_read_frame(pFormatContext, pPacket);
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_debug(JNIEnv *env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    
    //AVStream *pTestContext = pFormatContext->streams[0];
    
    
    
    //return av_read_frame(pFormatContext, pPacket);
}

