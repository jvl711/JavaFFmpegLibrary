#include <jni.h>
#include <stdio.h>
#include "jvl_FFmpeg_jni_AVCodec.h"
#include "libavformat/avformat.h"
#include "libavcodec/avcodec.h"
#include "libavutil/pixdesc.h"

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

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodec_getPixelFormatsCount(JNIEnv* env, jobject obj, jlong AVCodecPointer)
{
    AVCodec * pAVCodec = (AVCodec *)(intptr_t)AVCodecPointer;
    int i = 0;
    
    if(pAVCodec->pix_fmts != NULL)
    {
        while(pAVCodec->pix_fmts[i] != AV_PIX_FMT_NONE)
        {
            i++;
        }
    }
    
    return i;
}

JNIEXPORT jobject JNICALL Java_jvl_FFmpeg_jni_AVCodec_getPixelFormat(JNIEnv* env, jobject obj, jlong AVCodecPointer, jint index)
{
    AVCodec * pAVCodec = (AVCodec *)(intptr_t)AVCodecPointer;
    //enum AVPixelFormat src_pix_fmt = AV_PIX_FMT_CUDA;
    //enum AVPixelFormat pix_fmt;
    
    //pix_fmt = AV_PIX_FMT_CUDA;
    
    //return constructAVPixelFormat(env, 0, av_get_pix_fmt_name(pAVCodec->pix_fmts[index]));
    
    //char * name = av_get_pix_fmt_name(src_pix_fmt);    
    
    
    
    //printf("\nvalue of a_static: %s\n", av_get_pix_fmt_name(pAVCodec->pix_fmts[index]));
    
    return constructAVPixelFormat(env, pAVCodec->pix_fmts[index], av_get_pix_fmt_name(pAVCodec->pix_fmts[index]));
}
