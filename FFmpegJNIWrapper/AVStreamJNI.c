#include <jni.h>
#include <stdio.h>
#include "jvl_FFmpeg_jni_AVStream.h"
#include "libavformat/avformat.h"

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

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVStream_getLanguage(JNIEnv* env, jobject obj, jlong AVStreamPointer)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;
    
    if(pAVStream->metadata != NULL)
    {
        AVDictionaryEntry *tag = NULL;

        //tag = av_dict_get(pAVStream->metadata, "", tag, AV_DICT_IGNORE_SUFFIX);
        tag = av_dict_get(pAVStream->metadata, "language", NULL, 0);
        
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

/*
JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVStream_getMetadata(JNIEnv* env, jobject obj, jlong AVStreamPointer)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;
    jstring temp = NULL; 
    char **dict;
    
    int ret = av_dict_get_string(pAVStream->metadata, dict, '\r', '\n');
    
    return (*env)->NewStringUTF(env, *dict);
}
*/

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVStream_debug(JNIEnv* env, jobject obj, jlong AVStreamPointer)
{
    AVStream * pAVStream = (AVStream *)(intptr_t)AVStreamPointer;

    char lang[] = "language";
    char def[] = "";
    
    AVDictionaryEntry *item = av_dict_get(pAVStream->metadata, lang, NULL, 0);
    
    if(item == NULL)
    {
        return (*env)->NewStringUTF(env, def);
    }
    else
    {
        return (*env)->NewStringUTF(env, item->value);
    }
    
    
    //return av_dict_count(pAVStream->metadata);
    
    //av_dict_get_string(pAVStream->metadata, dict, '+', ';');
    
    //return (*env)->NewStringUTF(env, dict);
    
    //return pAVStream->display_aspect_ratio.den;
}