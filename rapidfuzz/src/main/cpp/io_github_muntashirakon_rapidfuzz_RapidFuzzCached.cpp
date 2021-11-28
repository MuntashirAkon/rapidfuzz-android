// SPDX-License-Identifier: Apache-2.0

#include<string>

#include <jni.h>
#include <rapidfuzz/fuzz.hpp>

#include "io_github_muntashirakon_rapidfuzz_RapidFuzzCached.h"

using namespace rapidfuzz;

JNIEXPORT jlong JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeNewCachedRatio
  (JNIEnv *env, jclass clazz, jstring query) {
    const char* q = env->GetStringUTFChars(query, NULL);
    fuzz::CachedRatio<std::string> *scorer = new fuzz::CachedRatio(std::string(q));
    return (jlong) scorer;
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeGetRatio
  (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char* c = env->GetStringUTFChars(choice, NULL);
    fuzz::CachedRatio<std::string> *scorer = (fuzz::CachedRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

JNIEXPORT void JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeFreeCachedRatio
  (JNIEnv *env, jclass clazz, jlong ptr) {
    fuzz::CachedRatio<std::string> *scorer = (fuzz::CachedRatio<std::string> *) ptr;
    delete scorer;
}

JNIEXPORT jlong JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeNewCachedPartialRatio
  (JNIEnv *env, jclass clazz, jstring query) {
    const char* q = env->GetStringUTFChars(query, NULL);
    fuzz::CachedPartialRatio<std::string> *scorer = new fuzz::CachedPartialRatio(std::string(q));
    return (jlong) scorer;
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeGetPartialRatio
  (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char* c = env->GetStringUTFChars(choice, NULL);
    fuzz::CachedPartialRatio<std::string> *scorer = (fuzz::CachedPartialRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

JNIEXPORT void JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeFreeCachedPartialRatio
  (JNIEnv *env, jclass clazz, jlong ptr) {
    fuzz::CachedPartialRatio<std::string> *scorer = (fuzz::CachedPartialRatio<std::string> *) ptr;
    delete scorer;
}

JNIEXPORT jlong JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeNewCachedTokenSortRatio
  (JNIEnv *env, jclass clazz, jstring query) {
    const char* q = env->GetStringUTFChars(query, NULL);
    fuzz::CachedTokenSortRatio<std::string> *scorer = new fuzz::CachedTokenSortRatio(std::string(q));
    return (jlong) scorer;
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeGetTokenSortRatio
  (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char* c = env->GetStringUTFChars(choice, NULL);
    fuzz::CachedTokenSortRatio<std::string> *scorer = (fuzz::CachedTokenSortRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

JNIEXPORT void JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeFreeCachedTokenSortRatio
  (JNIEnv *env, jclass clazz, jlong ptr) {
    fuzz::CachedTokenSortRatio<std::string> *scorer = (fuzz::CachedTokenSortRatio<std::string> *) ptr;
    delete scorer;
}

JNIEXPORT jlong JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeNewCachedPartialTokenSortRatio
  (JNIEnv *env, jclass clazz, jstring query) {
    const char* q = env->GetStringUTFChars(query, NULL);
    fuzz::CachedPartialTokenSortRatio<std::string> *scorer = new fuzz::CachedPartialTokenSortRatio(std::string(q));
    return (jlong) scorer;
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeGetPartialTokenSortRatio
  (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char* c = env->GetStringUTFChars(choice, NULL);
    fuzz::CachedPartialTokenSortRatio<std::string> *scorer = (fuzz::CachedPartialTokenSortRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

JNIEXPORT void JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeFreeCachedPartialTokenSortRatio
  (JNIEnv *env, jclass clazz, jlong ptr) {
    fuzz::CachedPartialTokenSortRatio<std::string> *scorer = (fuzz::CachedPartialTokenSortRatio<std::string> *) ptr;
    delete scorer;
}

JNIEXPORT jlong JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeNewCachedTokenSetRatio
  (JNIEnv *env, jclass clazz, jstring query) {
    const char* q = env->GetStringUTFChars(query, NULL);
    fuzz::CachedTokenSetRatio<std::string> *scorer = new fuzz::CachedTokenSetRatio(std::string(q));
    return (jlong) scorer;
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeGetTokenSetRatio
  (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char* c = env->GetStringUTFChars(choice, NULL);
    fuzz::CachedTokenSetRatio<std::string> *scorer = (fuzz::CachedTokenSetRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

JNIEXPORT void JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeFreeCachedTokenSetRatio
  (JNIEnv *env, jclass clazz, jlong ptr) {
    fuzz::CachedTokenSetRatio<std::string> *scorer = (fuzz::CachedTokenSetRatio<std::string> *) ptr;
    delete scorer;
}

JNIEXPORT jlong JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeNewCachedPartialTokenSetRatio
  (JNIEnv *env, jclass clazz, jstring query) {
    const char* q = env->GetStringUTFChars(query, NULL);
    fuzz::CachedPartialTokenSetRatio<std::string> *scorer = new fuzz::CachedPartialTokenSetRatio(std::string(q));
    return (jlong) scorer;
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeGetPartialTokenSetRatio
  (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char* c = env->GetStringUTFChars(choice, NULL);
    fuzz::CachedPartialTokenSetRatio<std::string> *scorer = (fuzz::CachedPartialTokenSetRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

JNIEXPORT void JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeFreeCachedPartialTokenSetRatio
  (JNIEnv *env, jclass clazz, jlong ptr) {
    fuzz::CachedPartialTokenSetRatio<std::string> *scorer = (fuzz::CachedPartialTokenSetRatio<std::string> *) ptr;
    delete scorer;
}

JNIEXPORT jlong JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeNewCachedTokenRatio
  (JNIEnv *env, jclass clazz, jstring query) {
    const char* q = env->GetStringUTFChars(query, NULL);
    fuzz::CachedTokenRatio<std::string> *scorer = new fuzz::CachedTokenRatio(std::string(q));
    return (jlong) scorer;
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeGetTokenRatio
  (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char* c = env->GetStringUTFChars(choice, NULL);
    fuzz::CachedTokenRatio<std::string> *scorer = (fuzz::CachedTokenRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

JNIEXPORT void JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeFreeCachedTokenRatio
  (JNIEnv *env, jclass clazz, jlong ptr) {
    fuzz::CachedTokenRatio<std::string> *scorer = (fuzz::CachedTokenRatio<std::string> *) ptr;
    delete scorer;
}

JNIEXPORT jlong JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeNewCachedPartialTokenRatio
  (JNIEnv *env, jclass clazz, jstring query) {
    const char* q = env->GetStringUTFChars(query, NULL);
    fuzz::CachedPartialTokenRatio<std::string> *scorer = new fuzz::CachedPartialTokenRatio(std::string(q));
    return (jlong) scorer;
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeGetPartialTokenRatio
  (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char* c = env->GetStringUTFChars(choice, NULL);
    fuzz::CachedPartialTokenRatio<std::string> *scorer = (fuzz::CachedPartialTokenRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

JNIEXPORT void JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeFreeCachedPartialTokenRatio
  (JNIEnv *env, jclass clazz, jlong ptr) {
    fuzz::CachedPartialTokenRatio<std::string> *scorer = (fuzz::CachedPartialTokenRatio<std::string> *) ptr;
    delete scorer;
}

JNIEXPORT jlong JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeNewCachedWeightedRatio
  (JNIEnv *env, jclass clazz, jstring query) {
    const char* q = env->GetStringUTFChars(query, NULL);
    fuzz::CachedWRatio<std::string> *scorer = new fuzz::CachedWRatio(std::string(q));
    return (jlong) scorer;
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeGetWeightedRatio
  (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char* c = env->GetStringUTFChars(choice, NULL);
    fuzz::CachedWRatio<std::string> *scorer = (fuzz::CachedWRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

JNIEXPORT void JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeFreeCachedWeightedRatio
  (JNIEnv *env, jclass clazz, jlong ptr) {
    fuzz::CachedWRatio<std::string> *scorer = (fuzz::CachedWRatio<std::string> *) ptr;
    delete scorer;
}

JNIEXPORT jlong JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeNewCachedQuickRatio
  (JNIEnv *env, jclass clazz, jstring query) {
    const char* q = env->GetStringUTFChars(query, NULL);
    fuzz::CachedQRatio<std::string> *scorer = new fuzz::CachedQRatio(std::string(q));
    return (jlong) scorer;
}

JNIEXPORT jdouble JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeGetQuickRatio
  (JNIEnv *env, jclass clazz, jlong ptr, jstring choice, jdouble score_cutoff) {
    const char* c = env->GetStringUTFChars(choice, NULL);
    fuzz::CachedQRatio<std::string> *scorer = (fuzz::CachedQRatio<std::string> *) ptr;
    return scorer->ratio(c, score_cutoff);
}

JNIEXPORT void JNICALL Java_io_github_muntashirakon_rapidfuzz_RapidFuzzCached_nativeFreeCachedQuickRatio
  (JNIEnv *env, jclass clazz, jlong ptr) {
    fuzz::CachedQRatio<std::string> *scorer = (fuzz::CachedQRatio<std::string> *) ptr;
    delete scorer;
}
