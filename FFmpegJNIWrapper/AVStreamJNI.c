#include <jni.h>
#include <stdio.h>
#include "jvl_FFmpeg_jni_AVStream.h"
#include "libavformat/avformat.h"
#include <stdbool.h> 

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVStream_getFramerateNumerator(JNIEnv* env, jobject obj, jlong AVStreamPointer)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;
    
    return pAVStream->r_frame_rate.num;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVStream_getFramerateDenominator(JNIEnv* env, jobject obj, jlong AVStreamPointer)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;
    
    
    
    return pAVStream->r_frame_rate.den;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVStream_getAttachedPicturePacket(JNIEnv* env, jobject obj, jlong AVStreamPointer)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;
    
    jlong pointer = (intptr_t)&pAVStream->attached_pic;
    
    return pointer;
}

JNIEXPORT jboolean JNICALL Java_jvl_FFmpeg_jni_AVStream_isForced(JNIEnv* env, jobject obj, jlong AVStreamPointer)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;
    
    if (pAVStream->disposition & AV_DISPOSITION_FORCED)
    {
        return true;
    }
    
    return false;
}

JNIEXPORT jboolean JNICALL Java_jvl_FFmpeg_jni_AVStream_isAttachedPicture(JNIEnv* env, jobject obj, jlong AVStreamPointer)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;
    
    if (pAVStream->disposition & AV_DISPOSITION_ATTACHED_PIC)
    {
        return true;
    }
    
    return false;
}

JNIEXPORT jboolean JNICALL Java_jvl_FFmpeg_jni_AVStream_isDefault(JNIEnv* env, jobject obj, jlong AVStreamPointer)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;
    
    if (pAVStream->disposition & AV_DISPOSITION_DEFAULT)
    {
        return true;
    }
    
    return false;
}


JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVStream_getMetadataKey(JNIEnv* env, jobject obj, jlong AVStreamPointer, jint index)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;
    int cur = 0;
    int size = 0;
    
    size = av_dict_count(pAVStream->metadata);
    
    if(pAVStream->metadata != NULL || index >=  size)
    {
        AVDictionaryEntry *tag = NULL;
        tag = av_dict_get(pAVStream->metadata, "", tag, AV_DICT_IGNORE_SUFFIX); //get initial tag
        cur++;
        
        while(tag != NULL && cur <= index)
        {
            tag = av_dict_get(pAVStream->metadata, "", tag, AV_DICT_IGNORE_SUFFIX);
            cur++;
        }
        
        if(tag == NULL)
        {
            return NULL;
        }
                
        return (*env)->NewStringUTF(env, tag->key);
    }
    else
    {
        return NULL;
    }
}

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVStream_getMetadataValue(JNIEnv* env, jobject obj, jlong AVStreamPointer, jint index)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;
    int cur = 0;
    int size = 0;
    
    size = av_dict_count(pAVStream->metadata);
    
    
    
    if(pAVStream->metadata != NULL || index >= size)
    {
        AVDictionaryEntry *tag = NULL;
        tag = av_dict_get(pAVStream->metadata, "", tag, AV_DICT_IGNORE_SUFFIX); //get initial tag
        cur++;
        
        while(tag != NULL && cur <= index)
        {
            tag = av_dict_get(pAVStream->metadata, "", tag, AV_DICT_IGNORE_SUFFIX);
            cur++;
        }
        
        if(tag == NULL)
        {
            return NULL;
        }
                
        return (*env)->NewStringUTF(env, tag->value);
    }
    else
    {
        return NULL;
    }
}

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVStream_getMetadataValueByKey(JNIEnv* env, jobject obj, jlong AVStreamPointer, jstring key)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;
    const char * keyPointer;
    
    if(key == NULL)
    {
        return NULL;
    }
    
    keyPointer = (*env)->GetStringUTFChars(env,key, 0);
    
    if(pAVStream->metadata != NULL)
    {
        AVDictionaryEntry *tag = NULL;
        
        tag = av_dict_get(pAVStream->metadata, keyPointer, NULL, 0);
        (*env)->ReleaseStringUTFChars(env, key, keyPointer);
        
        if(tag == NULL)
        {
            return NULL;
        }
                
        return (*env)->NewStringUTF(env, tag->value);
    }
    else
    {
        return NULL;
    }
    
}


JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVStream_getMetadataCount(JNIEnv* env, jobject obj, jlong AVStreamPointer)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;

    if(pAVStream->metadata != NULL)
    {
        return av_dict_count(pAVStream->metadata);
    }
    else
    {
        return 0;
    }
}
