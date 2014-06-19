#include <jni.h>
#include <android/log.h>
#include "pay.h"
#include <string>


extern JNIEnv* env;

extern "C"
{
    
jobject getInstance(JNIEnv* env, jclass obj_class){
    jmethodID construction_id = env->GetMethodID(obj_class, "<init>", "()V");
    jobject obj = env->NewObject(obj_class, construction_id);
    return obj;
}

void logd(const char* msg)
{
	LOGD(msg);
}

void startPay(const char* orderUrl)
{
	 if(!env) {
		 logd("startPay() -> env is null");
	     return;
	 }

	 jclass java_class = env->FindClass("com/easou/game/ldsg/GameEntryActivity");
	 if (java_class == 0)
	 {
		 logd("not found class:com/easou/game/ldsg/GameEntryActivity");
		 return;
	 }

	 //查找方法
	 jmethodID java_method = env->GetStaticMethodID(java_class, "startPay", "(Ljava/lang/String;)V");
	 if(java_method == 0){
		 logd("not find java method!");
		 return;
	 }

	 //调用方法
	 char* url = new char[strlen(orderUrl)];
	 strcpy (url,orderUrl);
	 jstring jstrMSG = env->NewStringUTF(url);

	 env->CallStaticObjectMethod(java_class, java_method,jstrMSG);
	 logd("call method succ");
	 return;
}

void exitGame360()
{
	 logd("call method exitGame360");
	 if(!env) {
		 logd("exitGame360() -> env is null");
	     return;
	 }

	 jclass java_class = env->FindClass("com/easou/game/ldsg/GameEntryActivity");
	 if (java_class == 0)
	 {
		 logd("not found class:com/easou/game/ldsg/GameEntryActivity");
		 return;
	 }

	 //查找方法
	 jmethodID java_method = env->GetStaticMethodID(java_class, "exitGame360", "()V");
	 if(java_method == 0){
		 logd("not find java method!");
		 return;
	 }


	 env->CallStaticVoidMethod(java_class, java_method);
	 logd("call method succ");
	 return;
}

}
