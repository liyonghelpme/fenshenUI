#ifndef ANALYTICS_UMENG
#define ANALYTICS_UMENG 0
#endif
#include "cocos2d.h"
#include "AppDelegate.h"
#include "SimpleAudioEngine.h"
#include "script_support/CCScriptSupport.h"
#include "CCLuaEngine.h"
#include "utils.h"
#include "lua_extensions.h"
#include "MyCocos2dLua.h"



#if (CC_TARGET_PLATFORM != CC_PLATFORM_WIN32 && ANALYTICS_UMENG == 1)
#include "PluginManager.h"
//#include "AnalyticsFlurry.h"
#include "AnalyticsUmeng.h"
using namespace cocos2d::plugin;

// The app key of flurry
//#define FLURRY_KEY_IOS          "KMGG7CD9WPK2TW4X9VR8"
//#define FLURRY_KEY_ANDROID      "SPKFH8KMPGHMMBWRBT5W"
#define UMENG_KEY_IOS           "50d2b18c5270152187000097" //AppKey 5199ef1556240bc5fe0887dc
#define UMENG_KEY_ANDROID       ""          // umeng key for android is setted in AndroidManifest.xml

ProtocolAnalytics* g_pAnalytics = NULL;
std::string s_strAppKey = "";
#endif


USING_NS_CC;
using namespace CocosDenshion;


#if (CC_TARGET_PLATFORM != CC_PLATFORM_WIN32 && ANALYTICS_UMENG == 1)
void AppDelegate::loadAnalyticsPlugin()
{
    PluginProtocol* pPlugin = NULL;
    ccLanguageType langType = CCApplication::sharedApplication()->getCurrentLanguage();

    std::string umengKey  = "";
   // std::string flurryKey = "";

#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS)
    umengKey  = UMENG_KEY_IOS;
    //flurryKey = FLURRY_KEY_IOS;
#elif (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    umengKey  = UMENG_KEY_ANDROID;
    //flurryKey = FLURRY_KEY_ANDROID;
#endif
	pPlugin = PluginManager::getInstance()->loadPlugin("AnalyticsUmeng");
        s_strAppKey = umengKey;
	/*
    if (kLanguageChinese == langType)
    {
        pPlugin = PluginManager::getInstance()->loadPlugin("AnalyticsUmeng");
        s_strAppKey = umengKey;
    }
    else
    {
        pPlugin = PluginManager::getInstance()->loadPlugin("AnalyticsFlurry");
        s_strAppKey = flurryKey;
    }
	*/
    g_pAnalytics = dynamic_cast<ProtocolAnalytics*>(pPlugin);
}
#endif

#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS || CC_TARGET_PLATFORM == CC_PLATFORM_MAC)
    extern int tolua_AppstorePurchase_open(lua_State* tolua_s);
#endif

AppDelegate::AppDelegate()
{
    // fixed me
    //_CrtSetDbgFlag(_CRTDBG_ALLOC_MEM_DF|_CRTDBG_LEAK_CHECK_DF);
}

AppDelegate::~AppDelegate()
{
    // end simple audio engine here, or it may crashed on win32
    SimpleAudioEngine::sharedEngine()->end();
    //CCScriptEngineManager::purgeSharedManager();
}

bool AppDelegate::applicationDidFinishLaunching()
{
    // initialize director
    CCDirector *pDirector = CCDirector::sharedDirector();
    pDirector->setOpenGLView(CCEGLView::sharedOpenGLView());
    
    CCEGLView::sharedOpenGLView()->setDesignResolutionSize(480, 800, kResolutionShowAll);

    // turn on display FPS
    pDirector->setDisplayStats(false);

    // set FPS. the default value is 1.0/60 if you don't call this
    pDirector->setAnimationInterval(1.0 / 60);

    // register lua engine
    CCLuaEngine* pEngine = CCLuaEngine::defaultEngine();
    lua_State *state = pEngine->getLuaStack()->getLuaState();
    tolua_MyExt_open(state);

    CCScriptEngineManager::sharedManager()->setScriptEngine(pEngine);

	//加载cjson
	CCLuaStack *pStack = pEngine->getLuaStack(); 
    lua_State* L = pStack->getLuaState(); 
    luaopen_lua_extensions(L); 


#if (CC_TARGET_PLATFORM != CC_PLATFORM_WIN32 && ANALYTICS_UMENG == 1)
	loadAnalyticsPlugin();
    g_pAnalytics->setDebugMode(false);
    g_pAnalytics->startSession(s_strAppKey.c_str());
    g_pAnalytics->setCaptureUncaughtException(true);

    AnalyticsUmeng* pUmeng = dynamic_cast<AnalyticsUmeng*>(g_pAnalytics);
    //AnalyticsFlurry* pFlurry = dynamic_cast<AnalyticsFlurry*>(g_pAnalytics);
    if (pUmeng != NULL)
    {
        pUmeng->updateOnlineConfig();
        pUmeng->setDefaultReportPolicy(AnalyticsUmeng::REALTIME);
    }
	/*
    if (pFlurry != NULL)
    {
        pFlurry->setReportLocation(true);
        pFlurry->logPageView();
        // const char* sdkVersion = pFlurry->getSDKVersion();
        pFlurry->setVersionName("1.1");
        pFlurry->setAge(20);
        pFlurry->setGender(AnalyticsFlurry::MALE);
        pFlurry->setUserId("123456");
        pFlurry->setUseHttps(false);
    }*/
#endif

CCLog("before to load lua file");
CCLog("platform ");

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    CCLog("before android add search path");
	//pEngine->addSearchPath((CCFileUtils::sharedFileUtils()->getWritablePath() + "script/").c_str());
	//CCFileUtils::sharedFileUtils()->addSearchPath((CCFileUtils::sharedFileUtils()->getWritablePath() + "script/").c_str());
	
    CCString* pstrFileContent = CCString::createWithContentsOfFile("Entry");
	
    CCLOG("%s","read script from phone memory");
    
    pEngine->executeString(pstrFileContent->getCString());
    
#elif (CC_TARGET_PLATFORM == CC_PLATFORM_IOS || CC_TARGET_PLATFORM == CC_PLATFORM_MAC)
    
    CCLuaStack *pStack = pEngine->getLuaStack();
    lua_State *tolua_s = pStack->getLuaState();
    tolua_AppstorePurchase_open(tolua_s);
    
    std::string path = CCFileUtils::sharedFileUtils()->fullPathForFilename("Entry");
    pEngine->addSearchPath(path.substr(0, path.find_last_of("/")).c_str());
	CCString* pstrFileContent = CCString::createWithContentsOfFile("Entry");
	pEngine->executeString(pstrFileContent->getCString());
#else
    std::string path = CCFileUtils::sharedFileUtils()->fullPathForFilename("Entry");
    pEngine->addSearchPath(path.substr(0, path.find_last_of("/")).c_str());

	CCString* pstrFileContent = CCString::createWithContentsOfFile("Entry");
	const char * res = pstrFileContent->getCString();

	int len = strlen(res);
	int remain_len = len % 8;
	int desSize = len+1;
	if(remain_len>0){
		desSize += 8 - remain_len;
	}
	unsigned char* des = (unsigned char*)malloc(desSize*sizeof(unsigned char));

	//加密脚本
	file_encrypt(res,des,desSize);
	pEngine->executeString(file_decrypt(des,desSize));//解密脚本

	//unsigned long desSize = 0;
	//unsigned char * enc = CCFileUtils::sharedFileUtils()->getFileData(path.c_str(), "rb", &desSize);
	//char * res = file_decrypt(enc,desSize);
	//CCLOG(res);
	//pEngine->executeString(res);//解密脚本
#endif 

    return true;
}

// This function will be called when the app is inactive. When comes a phone call,it's be invoked too
void AppDelegate::applicationDidEnterBackground()
{
    CCDirector::sharedDirector()->stopAnimation();
    SimpleAudioEngine::sharedEngine()->pauseBackgroundMusic();
#if (CC_TARGET_PLATFORM != CC_PLATFORM_WIN32 && ANALYTICS_UMENG == 1)
	g_pAnalytics->stopSession();
#endif
}

// this function will be called when the app is active again
void AppDelegate::applicationWillEnterForeground()
{
    CCDirector::sharedDirector()->startAnimation();
    SimpleAudioEngine::sharedEngine()->resumeBackgroundMusic();
#if (CC_TARGET_PLATFORM != CC_PLATFORM_WIN32 && ANALYTICS_UMENG == 1)
	if (g_pAnalytics)
    {
        g_pAnalytics->startSession(s_strAppKey.c_str());
    }
#endif
}
