#ifndef  _UMENG_LOG_H_
#define  _UMENG_LOG_H_

#include "cocos2d.h"

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID  )
	#include "PluginManager.h"
	//#include "AnalyticsFlurry.h"
	
    #include "AnalyticsUmeng.h"
	using namespace cocos2d::plugin;
	extern ProtocolAnalytics* g_pAnalytics;
	extern std::string s_strAppKey;
#endif

extern "C"
{
	int UmengLogEvent(int tag,const char* logEvent,cocos2d::CCDictionary* kv);
}

#if (CC_TARGET_PLATFORM != CC_PLATFORM_WIN32  )
/*
{"OnlineConfig",        TAG_LOG_ONLINE_CONFIG},
{"LogEvent-eventId"   , TAG_LOG_EVENT_ID},
{"LogEvent-eventId-kv", TAG_LOG_EVENT_ID_KV},
{"LogEvent-eventId-Duration", TAG_LOG_EVENT_ID_DURATION},
{"LogEvent-Begin", TAG_LOG_EVENT_BEGIN},
{"LogEvent-End", TAG_LOG_EVENT_END},
{"MakeMeCrash", TAG_MAKE_ME_CRASH}
*/
enum {
    TAG_LOG_EVENT_ID = 0,
    TAG_LOG_EVENT_ID_KV,
    TAG_LOG_ONLINE_CONFIG,
    TAG_LOG_EVENT_ID_DURATION,
    TAG_LOG_EVENT_BEGIN,
    TAG_LOG_EVENT_END,
    TAG_MAKE_ME_CRASH
};
#endif

#endif

