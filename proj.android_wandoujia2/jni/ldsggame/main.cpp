#include "AppDelegate.h"
#include "cocos2d.h"
#include "platform/android/jni/JniHelper.h"
#include "PluginJniHelper.h"
#include <jni.h>
#include "pay.h"
#include <android/log.h>


using namespace cocos2d;

JNIEnv* env;

extern "C"
{
    
jint JNI_OnLoad(JavaVM *vm, void *reserved)
{
    JniHelper::setJavaVM(vm);
    PluginJniHelper::setJavaVM(vm);
    return JNI_VERSION_1_4;
}

void Java_org_cocos2dx_lib_Cocos2dxRenderer_nativeInit(JNIEnv*  pEnv, jobject thiz, jint w, jint h)
{
	env = pEnv;
    if (!CCDirector::sharedDirector()->getOpenGLView())
    {
        CCEGLView *view = CCEGLView::sharedOpenGLView();
        view->setFrameSize(w, h);

        AppDelegate *pAppDelegate = new AppDelegate();
        CCApplication::sharedApplication()->run();
    }
    else
    {
        ccDrawInit();
        ccGLInvalidateStateCache();

        CCShaderCache::sharedShaderCache()->reloadDefaultShaders();
        CCTextureCache::reloadAllTextures();
        CCNotificationCenter::sharedNotificationCenter()->postNotification(EVNET_COME_TO_FOREGROUND, NULL);
        CCDirector::sharedDirector()->setGLDefaultValues(); 
    }
}

}
