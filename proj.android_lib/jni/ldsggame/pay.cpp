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
	 jstring jstrMSG = env->NewStringUTF(orderUrl);

	 env->CallStaticObjectMethod(java_class, java_method,jstrMSG);
	 env->DeleteLocalRef(jstrMSG);
	 env->DeleteLocalRef(java_class);
	 logd("call method succ");
	 return;
}

bool catchBackPressed()
{
	 logd("call method catchBackPressed");
	 if(!env) {
		 logd("catchBackPressed() -> env is null");
	     return false;
	 }

	 jclass java_class = env->FindClass("com/easou/game/ldsg/GameEntryActivity");
	 if (java_class == 0)
	 {
		 logd("not found class:com/easou/game/ldsg/GameEntryActivity");
		 return false;
	 }

	 //查找方法
	 jmethodID java_method = env->GetStaticMethodID(java_class, "catchBackPressed", "()Z");
	 if(java_method == 0){
		 logd("not find java method!");
		 return false;
	 }


	 jboolean b=env->CallStaticBooleanMethod (java_class, java_method);
	 
	 logd("call method succ");
	 return b==JNI_TRUE?true:false;
}

void startShare(const char* jniJson)
{
		 if(!env) {
			 logd("startShare() -> startShare is null");
			 return;
		 }
		 jclass java_class = env->FindClass("com/easou/game/ldsg/GameEntryActivity");
		 if (java_class == 0)
		 {
			 logd("not found class:com/easou/game/ldsg/GameEntryActivity");
			 return;
		 }

		 //查找方法
		 jmethodID java_method = env->GetStaticMethodID(java_class, "startShare", "(Ljava/lang/String;)V");
		 if(java_method == 0){
			 logd("not find java method!");
			 return;
		 }

		 //调用方法
		 jstring jstrMSG = env->NewStringUTF(jniJson);

		 env->CallStaticObjectMethod(java_class, java_method,jstrMSG);
		 env->DeleteLocalRef(jstrMSG);
		 env->DeleteLocalRef(java_class);
		 logd("call method succ");
		 return;
}


}
