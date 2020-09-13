#include <jni.h>
#include <stdio.h>
#include "jvl_FFmpeg_jni_AVCodecContext.h"
#include "libavcodec/avcodec.h"
#include "libavformat/avformat.h"
#include "libavutil/pixdesc.h"
#include "Utility.h"

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_freeContext(JNIEnv* env, jobject obj, jlong AVCodecContextPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;

    avcodec_free_context(&pAVCodecContext);
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_copyParamsToContext(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jlong AVCodecParamPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParamPointer;
    
    int ret = avcodec_parameters_to_context(pAVCodecContext, pAVCodecParamContext);
    
    return ret;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_open(JNIEnv* env, jobject obj, jlong AVCodecContextPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
        
    int ret = avcodec_open2(pAVCodecContext, NULL, NULL);
    
    return ret;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_sendPacket(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jlong AVPacketPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    AVPacket * pAVPacket = (AVPacket *)(intptr_t)AVPacketPointer;
    
    return avcodec_send_packet(pAVCodecContext, pAVPacket);;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_receiveFrame(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jlong AVFramePointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
    
    return avcodec_receive_frame(pAVCodecContext, pAVFrame);;
}

JNIEXPORT jobject JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_getFramerate(JNIEnv* env, jobject obj, jlong AVCodecContextPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    
    return constructAVRational(env, pAVCodecContext->framerate.num, pAVCodecContext->framerate.den);
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_setFramerate(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jint num, jint den)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    
    pAVCodecContext->framerate.num = num;
    pAVCodecContext->framerate.den = den;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_getHeight(JNIEnv* env, jobject obj, jlong AVCodecContextPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    
    return pAVCodecContext->height;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_getWidth(JNIEnv* env, jobject obj, jlong AVCodecContextPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    
    return pAVCodecContext->width;
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_setHeight(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jint value)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    
    pAVCodecContext->height = value;
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_setWidth(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jint value)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    
    pAVCodecContext->width = value;
}

JNIEXPORT jobject JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_getSampleAspectRatio(JNIEnv* env, jobject obj, jlong AVCodecContextPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    
    return constructAVRational(env, pAVCodecContext->sample_aspect_ratio.num, pAVCodecContext->sample_aspect_ratio.den);
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_setSampleAspectRatio(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jint num, jint den)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    
    pAVCodecContext->sample_aspect_ratio.num = num;
    pAVCodecContext->sample_aspect_ratio.den = den;
}

JNIEXPORT jobject JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_getPixelFormat(JNIEnv* env, jobject obj, jlong AVCodecContextPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;

    return constructAVPixelFormat(env, pAVCodecContext->pix_fmt, av_get_pix_fmt_name(pAVCodecContext->pix_fmt));
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_setPixelFormat(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jint id, jstring name)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    const char * namePointer;
    
    namePointer = (*env)->GetStringUTFChars(env,name, 0);

    pAVCodecContext->pix_fmt = av_get_pix_fmt(namePointer);
    
    (*env)->ReleaseStringUTFChars(env, name, namePointer);
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_getSampleRate(JNIEnv* env, jobject obj, jlong AVCodecContextPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    
    return pAVCodecContext->sample_rate;
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_setSampleRate(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jint samprate)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;

    pAVCodecContext->sample_rate = samprate;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_getChannels(JNIEnv* env, jobject obj, jlong AVCodecContextPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    
    return pAVCodecContext->channels;
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_setChannels(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jint channels)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;

    pAVCodecContext->channels = channels;
}

JNIEXPORT jobject JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_getSampleFormat(JNIEnv* env, jobject obj, jlong AVCodecContextPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;

    return constructAVSampleFormat(env, pAVCodecContext->sample_fmt, av_get_sample_fmt_name(pAVCodecContext->sample_fmt));
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVCodecContext_setSampleFormat(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jint id, jstring name)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    const char * namePointer;
    
    namePointer = (*env)->GetStringUTFChars(env,name, 0);

    pAVCodecContext->sample_fmt = av_get_sample_fmt(namePointer);
    
    (*env)->ReleaseStringUTFChars(env, name, namePointer);
}