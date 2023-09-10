
#include <jni.h>
#include <string>



extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_mirrarrassigment_di_ApiModule_baseUrl(JNIEnv *env, jobject thiz) {
    std::string url = "https://api.nasa.gov/";
    return env->NewStringUTF(url.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_mirrarrassigment_di_ApiModule_apiKey(JNIEnv *env, jclass clazz) {
    std::string url = "hnDCdNlW2jA5M40WVUSRVXO9Lgv0j4LkDiLZvPox";
    return env->NewStringUTF(url.c_str());
}