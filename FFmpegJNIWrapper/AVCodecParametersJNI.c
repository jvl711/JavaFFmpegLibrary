
#include <jni.h>
#include <stdio.h>
#include "jvl_FFmpeg_jni_AVCodecParameters.h"
#include "libavformat/avformat.h"
#include "libavcodec/avcodec.h"

AVProfile getProfileTest(AVCodec *pCodec, int profile)
{
    AVProfile notfound;
    
    if(pCodec->profiles != NULL)
    {
        int i = 0;
        
        while(pCodec->profiles[i].profile != FF_PROFILE_UNKNOWN)
        {
            if(pCodec->profiles[i].profile == profile)
            {
                return pCodec->profiles[i];
            }
            
            i++;
        }

        if(pCodec->profiles[i].profile == FF_PROFILE_UNKNOWN)
        {
            return pCodec->profiles[i];
        }
    }
    
    return notfound; //There was an issue getting a profile

}


JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecParameters_getCodecType(JNIEnv* env, jobject obj, jlong AVCodecParametersPointer)
{
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParametersPointer;
    
    return pAVCodecParamContext->codec_type;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecParameters_getWidth(JNIEnv* env, jobject obj, jlong AVCodecParametersPointer)
{
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParametersPointer;
    
    return pAVCodecParamContext->width;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecParameters_getHeight(JNIEnv* env, jobject obj, jlong AVCodecParametersPointer)
{
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParametersPointer;
    
    return pAVCodecParamContext->height;
}

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVCodecParameters_getProfileName(JNIEnv* env, jobject obj, jlong AVCodecParametersPointer)
{
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParametersPointer;
    
    AVCodec *pCodec = avcodec_find_decoder(pAVCodecParamContext->codec_id);
    
    AVProfile profile = getProfileTest(pCodec, pAVCodecParamContext->profile);
    
    return (*env)->NewStringUTF(env, profile.name);
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecParameters_getProfile(JNIEnv* env, jobject obj, jlong AVCodecParametersPointer)
{
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParametersPointer;
    
    return pAVCodecParamContext->profile;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecParameters_getChannels(JNIEnv* env, jobject obj, jlong AVCodecParametersPointer)
{
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParametersPointer;
    
    return pAVCodecParamContext->channels;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecParameters_getSampleRate(JNIEnv* env, jobject obj, jlong AVCodecParametersPointer)
{
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParametersPointer;
    
    return pAVCodecParamContext->sample_rate;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecParameters_getLevel(JNIEnv* env, jobject obj, jlong AVCodecParametersPointer)
{
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParametersPointer;
    
    return pAVCodecParamContext->level;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVCodecParameters_getBitrate(JNIEnv* env, jobject obj, jlong AVCodecParametersPointer)
{
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParametersPointer;
    
    return pAVCodecParamContext->bit_rate;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVCodecParameters_getCodecID(JNIEnv* env, jobject obj, jlong AVCodecParametersPointer)
{
    AVCodecParameters * pAVCodecParamContext = (AVCodecParameters *)(intptr_t)AVCodecParametersPointer;
    
    return pAVCodecParamContext->codec_id;
}