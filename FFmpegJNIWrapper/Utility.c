/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

#include "Utility.h"

jobject constructAVPixelFormat(JNIEnv *env, int id, char * name)
{
    jclass javaClass = (*env)->FindClass(env, "jvl/FFmpeg/jni/AVPixelFormat");
    
    if (javaClass == NULL) 
    {
        printf("Find Class Failed.\n");
    } 
    else 
    {
        printf("Found class.\n");
    }

    jmethodID constructor = (*env)->GetMethodID(env, javaClass, "<init>", "(ILjava/lang/String;)V");
    
    if (constructor == 0) 
    {
        printf("Find method Failed.\n");
    }
    else 
    {
        printf("Found method.\n");
    }
    
    //(*env)->NewStringUTF(env, tag->value)
    return (*env)->NewObject(env, javaClass, constructor, id, (*env)->NewStringUTF(env, name));
}

jobject constructAVRational(JNIEnv *env, int num, int den)
{
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
    
    return (*env)->NewObject(env, javaClass, constructor, num, den);
}