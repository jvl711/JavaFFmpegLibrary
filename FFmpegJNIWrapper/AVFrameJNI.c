#include <jni.h>
#include <stdio.h>
#include "jvl_FFmpeg_jni_AVFrame.h"
#include "libavformat/avformat.h"
#include "libavcodec/avcodec.h"

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFrame_allocate(JNIEnv* env, jobject obj)
{
    AVFrame *pTestContext = av_frame_alloc();
    
    jlong pointer = (intptr_t)pTestContext;
    
    return pointer;
}

/*   
    av_get_picture_type_char(pAVFrame->pict_type);
    pAVFrame->pkt_size;
    pAVFrame->pts;
    pAVFrame->key_frame;
    pAVFrame->coded_picture_number;
 */

JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFrame_getPTS(JNIEnv* env, jobject obj, jlong AVFramePointer)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
    
    
            
    return pAVFrame->pts;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getPacketSize(JNIEnv* env, jobject obj, jlong AVFramePointer)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
      
    return pAVFrame->pkt_size;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getKeyFrame(JNIEnv* env, jobject obj, jlong AVFramePointer)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
      
    return pAVFrame->key_frame;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getCodedPictureNumber(JNIEnv* env, jobject obj, jlong AVFramePointer)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
      
    return pAVFrame->coded_picture_number;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getPictureType(JNIEnv* env, jobject obj, jlong AVFramePointer)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
      
    return pAVFrame->pict_type;
}

JNIEXPORT jchar JNICALL Java_jvl_FFmpeg_jni_AVFrame_getPictureTypeChar(JNIEnv* env, jobject obj, jlong AVFramePointer)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
    
    return av_get_picture_type_char(pAVFrame->pict_type);
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getLineSize(JNIEnv* env, jobject obj, jlong AVFramePointer)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
    
    for(int i = 0; i < 8; i++)
    {
        printf("Linesize index: %d value %d\n", i, pAVFrame->linesize[i]);
        
        if(pAVFrame->linesize[i] > 0)
        {
            uint8_t * data = pAVFrame->data[i];
            printf("\tDatalength: %ld\n", sizeof(data));
        }
    }
    
    
    
    return pAVFrame->linesize[0];
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getWidth(JNIEnv* env, jobject obj, jlong AVFramePointer)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
      
    return pAVFrame->width;
}

JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getHeight(JNIEnv* env, jobject obj, jlong AVFramePointer)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
      
    return pAVFrame->height;
}


JNIEXPORT jbyte JNICALL Java_jvl_FFmpeg_jni_AVFrame_getData(JNIEnv* env, jobject obj, jlong AVFramePointer, jint i, jint j)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
    
    return *(pAVFrame->data[i]+j);
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVFrame_free(JNIEnv* env, jobject obj, jlong AVFramePointer)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
    
    av_frame_free(&pAVFrame);
}

JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVFrame_test(JNIEnv* env, jobject obj, jlong AVFramePointer)
{
    AVFrame * pAVFrame = (AVFrame *)(intptr_t)AVFramePointer;
    
    clock_t start = clock();
    printf("Start %ld\n",start);
    
    for(int i = 0; i < (pAVFrame->width * pAVFrame->height); i++)
    {
        uint8_t temp = *(pAVFrame->data[0] + i);
    }
    
    clock_t end = clock();
    
    printf("Clocks per second %ld\n", (CLOCKS_PER_SEC));
    printf("End %ld\n", end);
    printf("Complete in %ld\n", (end - start));
}