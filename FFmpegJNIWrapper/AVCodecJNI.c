#include <jni.h>
#include <stdio.h>
#include "jvl_FFmpeg_jni_AVCodec.h"
#include "libavformat/avformat.h"
#include "libavcodec/avcodec.h"

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVCodec_findDecoder(JNIEnv* env, jobject obj, jint codec_id)
{
    AVCodec *pTest = avcodec_find_decoder(codec_id);
    
    jlong pointer = (intptr_t)pTest;    
    
    return pointer;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVCodec_findEncoder(JNIEnv* env, jobject obj, jint codec_id)
{
    AVCodec *pAVCodec = avcodec_find_encoder(codec_id);
    
    jlong pointer = (intptr_t)pAVCodec;    
    
    return pointer;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVCodec_findEncoderByName(JNIEnv* env, jobject obj, jstring name)
{
    const char * namePointer;
    
    namePointer = (*env)->GetStringUTFChars(env,name, 0);   
    (*env)->ReleaseStringUTFChars(env, name, namePointer);
    
    AVCodec *pAVCodec = avcodec_find_encoder_by_name(namePointer);
    
    jlong pointer = (intptr_t)pAVCodec;    
    
    return pointer;
}

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVCodec_getLongName(JNIEnv* env, jobject obj, jlong AVCodecPointer)
{
    AVCodec * pAVCodec = (AVCodec *)(intptr_t)AVCodecPointer;
    
    return (*env)->NewStringUTF(env, pAVCodec->long_name);
}


JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVCodec_getName(JNIEnv* env, jobject obj, jlong AVCodecPointer)
{
    AVCodec * pAVCodec = (AVCodec *)(intptr_t)AVCodecPointer;
    
    return (*env)->NewStringUTF(env, pAVCodec->name);
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVCodec_allocateContext(JNIEnv* env, jobject obj, jlong AVCodecPointer)
{
    AVCodec * pAVCodec = (AVCodec *)(intptr_t)AVCodecPointer;
    
    AVCodecContext *pTestContext = avcodec_alloc_context3(pAVCodec);
    
    jlong pointer = (intptr_t)pTestContext;    
    
    return pointer;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodec_copyParamsToContext(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jlong AVCodecParamPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParamPointer;
    
    int ret = avcodec_parameters_to_context(pAVCodecContext, pAVCodecParamContext);
    
    return ret;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodec_open(JNIEnv* env, jobject obj, jlong AVCodecContextPointer, jlong AVCodecPointer)
{
    AVCodecContext * pAVCodecContext = (AVCodecContext *)(intptr_t)AVCodecContextPointer;
    AVCodec * pAVCodec = (AVCodec *)(intptr_t)AVCodecPointer;
    
    int ret = avcodec_open2(pAVCodecContext, pAVCodec, NULL);
    
    return ret;
}
