#include <jni.h>
#include <stdio.h>
#include "jvl_FFmpeg_jni_AVPacket.h"
#include "libavformat/avformat.h"
#include "libavcodec/avcodec.h"

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVPacket_allocate(JNIEnv* env, jobject obj)
{
    AVPacket *pTestContext = av_packet_alloc();
    
    jlong pointer = (intptr_t)pTestContext;
    
    return pointer;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVPacket_getPTS(JNIEnv* env, jobject obj, jlong AVPacketPointer)
{
    AVPacket * pAVPacket = (AVPacket *)(intptr_t)AVPacketPointer;
    
    printf("AVPacket->pts %" PRId64 "\n", pAVPacket->pts);
    
    return pAVPacket->pts;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVPacket_getDTS(JNIEnv* env, jobject obj, jlong AVPacketPointer)
{
    AVPacket * pAVPacket = (AVPacket *)(intptr_t)AVPacketPointer;
    
    return pAVPacket->dts;
}

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVPacket_getPosition(JNIEnv* env, jobject obj, jlong AVPacketPointer)
{
    AVPacket * pAVPacket = (AVPacket *)(intptr_t)AVPacketPointer;

    return pAVPacket->pos;
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVPacket_setPosition(JNIEnv* env, jobject obj, jlong AVPacketPointer, jlong value)
{
    AVPacket * pAVPacket = (AVPacket *)(intptr_t)AVPacketPointer;

    pAVPacket->pos = value;
}

JNIEXPORT jbyteArray JNICALL Java_jvl_FFmpeg_jni_AVPacket_getData(JNIEnv* env, jobject obj, jlong AVPacketPointer)
{
    AVPacket * pAVPacket = (AVPacket *)(intptr_t)AVPacketPointer;
    //jbyteArray result;
    //jbyte temp[pAVPacket->size];
    //result = (*env)->NewByteArray(env, pAVPacket->size);
    jbyteArray result =(*env)->NewByteArray(env, pAVPacket->size);
    
    
    
    //for(int i = 0; i < pAVPacket->size; i++)
    //{
    //    temp[i] = pAVPacket->data[i];
    //}
    
    //(*env)->SetByteArrayRegion(env, result, 0, pAVPacket->size, temp);
    
    //return result;
    (*env)->SetByteArrayRegion(env, result, 0, pAVPacket->size, pAVPacket->data);
            
    return result;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVPacket_getSize(JNIEnv* env, jobject obj, jlong AVPacketPointer)
{
    AVPacket * pAVPacket = (AVPacket *)(intptr_t)AVPacketPointer;
    
    return pAVPacket->size;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVPacket_getStreamIndex(JNIEnv* env, jobject obj, jlong AVPacketPointer)
{
    AVPacket * pAVPacket = (AVPacket *)(intptr_t)AVPacketPointer;

    return pAVPacket->stream_index;
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVPacket_free(JNIEnv* env, jobject obj, jlong AVPacketPointer)
{
    AVPacket * pAVPacket = (AVPacket *)(intptr_t)AVPacketPointer;
    
    av_packet_free(&pAVPacket);
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVPacket_unreference(JNIEnv* env, jobject obj, jlong AVPacketPointer)
{
    AVPacket * pAVPacket = (AVPacket *)(intptr_t)AVPacketPointer;
    
    av_packet_unref(pAVPacket);
}

/*
jbyteArray ToJavaByteArray(JNIEnv* env, const uint8_t* bytes, size_t len) 
{
    jbyteArray byte_array =(*env)->NewByteArray(len);

    (*env)->SetByteArrayRegion(env, byte_array, 0, len, bytes);

    return byte_array;
}
*/