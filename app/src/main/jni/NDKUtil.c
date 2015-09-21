#include "com_wf_news_util_NDKUtil.h"
/*
 * Class:     com_wf_news_util_NDKUtil
 * Method:    getStringFromNative
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_wf_news_util_NDKUtil_getStringFromNative
  (JNIEnv * env, jobject obj){
     return (*env)->NewStringUTF(env, "Hello, this is from NDK!");
  }