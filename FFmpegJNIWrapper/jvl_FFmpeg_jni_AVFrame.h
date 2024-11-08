/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class jvl_FFmpeg_jni_AVFrame */

#ifndef _Included_jvl_FFmpeg_jni_AVFrame
#define _Included_jvl_FFmpeg_jni_AVFrame
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    allocate
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFrame_allocate
  (JNIEnv *, jclass);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getPTS
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVFrame_getPTS
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getPacketSize
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getPacketSize
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getKeyFrame
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getKeyFrame
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getCodedPictureNumber
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getCodedPictureNumber
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getPictureType
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getPictureType
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getPictureTypeChar
 * Signature: (J)C
 */
JNIEXPORT jchar JNICALL Java_jvl_FFmpeg_jni_AVFrame_getPictureTypeChar
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getLineSize
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getLineSize
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getWidth
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getWidth
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getHeight
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getHeight
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getData
 * Signature: (JII)B
 */
JNIEXPORT jbyte JNICALL Java_jvl_FFmpeg_jni_AVFrame_getData
  (JNIEnv *, jobject, jlong, jint, jint);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    free
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVFrame_free
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    test
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_jvl_FFmpeg_jni_AVFrame_test
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getFramerateNumerator
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getFramerateNumerator
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVFrame
 * Method:    getFramerateDenominator
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVFrame_getFramerateDenominator
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif
