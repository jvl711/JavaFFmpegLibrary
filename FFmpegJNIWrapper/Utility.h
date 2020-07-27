/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Utility.h
 * Author: jvl711
 *
 * Created on July 23, 2020, 4:44 PM
 */

#include <jni.h>

#ifndef UTILITY_H
#define UTILITY_H

#ifdef __cplusplus
extern "C" {
#endif

jobject constructAVRational(JNIEnv *env, int num, int den);


#ifdef __cplusplus
}
#endif

#endif /* UTILITY_H */

