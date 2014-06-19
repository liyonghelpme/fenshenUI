#include <jni.h>
#include <android/log.h>

#define  LOG_TAG    "pay"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)

extern "C"
{
void startPay(const char*);
bool catchBackPressed();
void logd(const char*);
void startShare(const char*);
}
