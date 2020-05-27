/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class jvl_FFmpeg_jni_AVFormatContext */

#ifndef _Included_jvl_FFmpeg_jni_AVFormatContext
#define _Included_jvl_FFmpeg_jni_AVFormatContext
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    allocateContext
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_allocateContext
  (JNIEnv *, jclass);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    allocateOutputContext
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_allocateOutputContext
  (JNIEnv *, jclass, jstring);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    allocateNewStream
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_allocateNewStream
  (JNIEnv *, jclass, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    openInput
 * Signature: (JLjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_openInput
  (JNIEnv *, jobject, jlong, jstring);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    closeInput
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_closeInput
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    findStreamInfo
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_findStreamInfo
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    getDuration
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getDuration
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    getFormatLongName
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getFormatLongName
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    getFormatName
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getFormatName
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    getMimeType
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getMimeType
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    getNumberOfStreams
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getNumberOfStreams
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    getAVCodecParameters
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getAVCodecParameters
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    getAVStream
 * Signature: (JI)J
 */
JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getAVStream
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    getBitrate
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_getBitrate
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    readFrame
 * Signature: (JJ)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_readFrame
  (JNIEnv *, jobject, jlong, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFormatContext
 * Method:    debug
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVFormatContext_debug
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif
