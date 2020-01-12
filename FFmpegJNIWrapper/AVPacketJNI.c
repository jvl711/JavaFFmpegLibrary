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
