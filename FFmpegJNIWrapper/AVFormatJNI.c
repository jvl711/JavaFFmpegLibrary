/*
 * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html 
 */

#include <jni.h>
#include <stdio.h>
#include "jvl_FFmpeg_jni_AVFormatContext.h"
#include "libavformat/avformat.h"
#include "Utility.h"


JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_allocateContext(JNIEnv* env, jobject obj)
{
    AVFormatContext *pTestContext = avformat_alloc_context();
    
    jlong pointer = (intptr_t)pTestContext;
            
    return pointer;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_allocateOutputContext(JNIEnv* env, jobject obj, jstring filePath)
{
    AVFormatContext * pTestContext;
    const char * filePathPointer;
    
    filePathPointer = (*env)->GetStringUTFChars(env,filePath, 0);
    
    (*env)->ReleaseStringUTFChars(env, filePath, filePathPointer);
    
    avformat_alloc_output_context2(&pTestContext, NULL, NULL, filePathPointer);
    
    jlong pointer = (intptr_t)pTestContext;
    
    return pointer;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_allocateNewStream(JNIEnv* env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    AVStream * out_stream;
    
    out_stream = avformat_new_stream(pFormatContext, NULL);
    
    jlong pointer = (intptr_t)out_stream;
    
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

    int ret = avformat_open_input(&pFormatContext, filePathPointer, NULL, NULL);
    
    if(ret == 0)
    {
        //Reading the header caused incorrect number of streams to be read for flac files.
        //Not sure why I was doing this.  It may need to be moved
        //ret = pFormatContext->iformat->read_header(pFormatContext);
        
        //if(ret != 0)
        //{
        //    fprintf(stdout, "Could not open file: %s\n", av_err2str(ret));
        //}
    }
    else
    {
        fprintf(stdout, "Could not open file: %s\n", av_err2str(ret));
    }
    
    
    return ret;
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

JNIEXPORT jobject JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_guessFramerate(JNIEnv *env, jobject obj)
{
    //AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    
    /*
    jclass javaClass = (*env)->FindClass(env, "jvl/FFmpeg/jni/AVRational");
    
    if (javaClass == NULL) 
    {
        printf("Find Class Failed.\n");
    } 
    else 
    {
        printf("Found class.\n");
    }

    jmethodID constructor = (*env)->GetMethodID(env, javaClass, "<init>", "(II)V");
    
    if (constructor == 0) 
    {
        printf("Find method Failed.\n");
    }
    else 
    {
        printf("Found method.\n");
    }
    
    return (*env)->NewObject(env, javaClass, constructor, 16, 9);
    */
    
    return constructAVRational(env, 16, 9);
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

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getMetadataKey(JNIEnv* env, jobject obj, jlong avFormatPointer, jint index)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    int cur = 0;
    int size = 0;
    
    size = av_dict_count(pFormatContext->metadata);
    
    if(pFormatContext->metadata != NULL || index >=  size)
    {
        AVDictionaryEntry *tag = NULL;
        tag = av_dict_get(pFormatContext->metadata, "", tag, AV_DICT_IGNORE_SUFFIX); //get initial tag
        cur++;
        
        while(tag != NULL && cur <= index)
        {
            tag = av_dict_get(pFormatContext->metadata, "", tag, AV_DICT_IGNORE_SUFFIX);
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

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getMetadataValue(JNIEnv* env, jobject obj, jlong avFormatPointer, jint index)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    int cur = 0;
    int size = 0;
    
    size = av_dict_count(pFormatContext->metadata);
    
    
    
    if(pFormatContext->metadata != NULL || index >= size)
    {
        AVDictionaryEntry *tag = NULL;
        tag = av_dict_get(pFormatContext->metadata, "", tag, AV_DICT_IGNORE_SUFFIX); //get initial tag
        cur++;
        
        while(tag != NULL && cur <= index)
        {
            tag = av_dict_get(pFormatContext->metadata, "", tag, AV_DICT_IGNORE_SUFFIX);
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

JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getMetadataValueByKey(JNIEnv* env, jobject obj, jlong avFormatPointer, jstring key)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;
    const char * keyPointer;
    
    if(key == NULL)
    {
        return NULL;
    }
    
    keyPointer = (*env)->GetStringUTFChars(env,key, 0);
    
    if(pFormatContext->metadata != NULL)
    {
        AVDictionaryEntry *tag = NULL;
        
        tag = av_dict_get(pFormatContext->metadata, keyPointer, NULL, 0);
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

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getMetadataCount(JNIEnv* env, jobject obj, jlong avFormatPointer)
{
    AVFormatContext * pFormatContext = (AVFormatContext *)(intptr_t)avFormatPointer;

    if(pFormatContext->metadata != NULL)
    {
        return av_dict_count(pFormatContext->metadata);
    }
    else
    {
        return 0;
    }
}