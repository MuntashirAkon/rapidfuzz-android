// SPDX-License-Identifier: Apache-2.0

#include <jni.h>
#include <rapidfuzz/fuzz.hpp>

#include "io_github_muntashirakon_rapidfuzz_RapidFuzz.h"

using namespace rapidfuzz;

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzz_nativeRatio
  (JNIEnv *env, jclass clazz, jstring s1, jstring s2, jdouble score_cuttoff) {
    const char* str1 = env->GetStringUTFChars(s1, NULL);
    const char* str2 = env->GetStringUTFChars(s2, NULL);
    return (double) fuzz::ratio(str1, str2, (double) score_cuttoff);
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzz_nativePartialRatio
  (JNIEnv *env, jclass clazz, jstring s1, jstring s2, jdouble score_cuttoff) {
    const char* str1 = env->GetStringUTFChars(s1, NULL);
    const char* str2 = env->GetStringUTFChars(s2, NULL);
    return (double) fuzz::partial_ratio(str1, str2, (double) score_cuttoff);
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzz_nativeTokenSortRatio
  (JNIEnv *env, jclass clazz, jstring s1, jstring s2, jdouble score_cuttoff) {
    const char* str1 = env->GetStringUTFChars(s1, NULL);
    const char* str2 = env->GetStringUTFChars(s2, NULL);
    return (double) fuzz::token_sort_ratio(str1, str2, (double) score_cuttoff);
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzz_nativePartialTokenSortRatio
  (JNIEnv *env, jclass clazz, jstring s1, jstring s2, jdouble score_cuttoff) {
    const char* str1 = env->GetStringUTFChars(s1, NULL);
    const char* str2 = env->GetStringUTFChars(s2, NULL);
    return (double) fuzz::partial_token_sort_ratio(str1, str2, (double) score_cuttoff);
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzz_nativeTokenSetRatio
  (JNIEnv *env, jclass clazz, jstring s1, jstring s2, jdouble score_cuttoff) {
    const char* str1 = env->GetStringUTFChars(s1, NULL);
    const char* str2 = env->GetStringUTFChars(s2, NULL);
    return (double) fuzz::token_set_ratio(str1, str2, (double) score_cuttoff);
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzz_nativePartialTokenSetRatio
  (JNIEnv *env, jclass clazz, jstring s1, jstring s2, jdouble score_cuttoff) {
    const char* str1 = env->GetStringUTFChars(s1, NULL);
    const char* str2 = env->GetStringUTFChars(s2, NULL);
    return (double) fuzz::partial_token_set_ratio(str1, str2, (double) score_cuttoff);
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzz_nativeTokenRatio
  (JNIEnv *env, jclass clazz, jstring s1, jstring s2, jdouble score_cuttoff) {
    const char* str1 = env->GetStringUTFChars(s1, NULL);
    const char* str2 = env->GetStringUTFChars(s2, NULL);
    return (double) fuzz::token_ratio(str1, str2, (double) score_cuttoff);
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzz_nativePartialTokenRatio
  (JNIEnv *env, jclass clazz, jstring s1, jstring s2, jdouble score_cuttoff) {
    const char* str1 = env->GetStringUTFChars(s1, NULL);
    const char* str2 = env->GetStringUTFChars(s2, NULL);
    return (double) fuzz::partial_token_ratio(str1, str2, (double) score_cuttoff);
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzz_nativeWeightedRatio
  (JNIEnv *env, jclass clazz, jstring s1, jstring s2, jdouble score_cuttoff) {
    const char* str1 = env->GetStringUTFChars(s1, NULL);
    const char* str2 = env->GetStringUTFChars(s2, NULL);
    return (double) fuzz::WRatio(str1, str2, (double) score_cuttoff);
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzz_nativeQuickRatio
  (JNIEnv *env, jclass clazz, jstring s1, jstring s2, jdouble score_cuttoff) {
    const char* str1 = env->GetStringUTFChars(s1, NULL);
    const char* str2 = env->GetStringUTFChars(s2, NULL);
    return (double) fuzz::QRatio(str1, str2, (double) score_cuttoff);
}
