#include "Native.h"
#include <jni.h>
#include "JniHelper.h"
#include "cocos2d.h"


#define NATIVE "com.easou.game.sghhr.common.Native"

using namespace cocos2d;
void sendCommand(std::string cmd, std::string args) {
    CCLog("send Command native %s %s", cmd.c_str(), args.c_str());

    JniMethodInfo t;
    if(JniHelper::getStaticMethodInfo(t, NATIVE, "sendCommand", "(Ljava/lang/String;Ljava/lang/String;)V")){
            jstring stringArg = t.env->NewStringUTF(cmd.c_str());
            jstring stringArg1 = t.env->NewStringUTF(args.c_str());

            t.env->CallStaticVoidMethod(t.classID, t.methodID, stringArg, stringArg1);
            t.env->DeleteLocalRef(t.classID);
            t.env->DeleteLocalRef(stringArg);
            t.env->DeleteLocalRef(stringArg1);
            CCLog("delete local ref finish");
    }
}
