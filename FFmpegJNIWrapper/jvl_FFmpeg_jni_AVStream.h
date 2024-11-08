/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class jvl_FFmpeg_jni_AVStream */

#ifndef _Included_jvl_FFmpeg_jni_AVStream
#define _Included_jvl_FFmpeg_jni_AVStream
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    isForced
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_jvl_FFmpeg_jni_AVStream_isForced
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    isAttachedPicture
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_jvl_FFmpeg_jni_AVStream_isAttachedPicture
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    isDefault
 * Signature: (J)Z
 */
JNIEXPORT jboolean JNICALL Java_jvl_FFmpeg_jni_AVStream_isDefault
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    guessFramerate
 * Signature: (JJ)Ljvl/FFmpeg/jni/AVRational;
 */
JNIEXPORT jobject JNICALL Java_jvl_FFmpeg_jni_AVStream_guessFramerate
  (JNIEnv *, jobject, jlong, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    getFramerate
 * Signature: (J)Ljvl/FFmpeg/jni/AVRational;
 */
JNIEXPORT jobject JNICALL Java_jvl_FFmpeg_jni_AVStream_getFramerate
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    getMetadataCount
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVStream_getMetadataCount
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    getMetadataKey
 * Signature: (JI)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVStream_getMetadataKey
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    getMetadataValue
 * Signature: (JI)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVStream_getMetadataValue
  (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    getMetadataValueByKey
 * Signature: (JLjava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jvl_FFmpeg_jni_AVStream_getMetadataValueByKey
  (JNIEnv *, jobject, jlong, jstring);

/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    getAttachedPicturePacket
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_jvl_FFmpeg_jni_AVStream_getAttachedPicturePacket
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    getDisposition
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVStream_getDisposition
  (JNIEnv *, jobject, jlong);

/*
 * Class:     jvl_FFmpeg_jni_AVStream
 * Method:    getID
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_jvl_FFmpeg_jni_AVStream_getID
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif
