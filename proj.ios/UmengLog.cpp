#include "UmengLog.h"

using namespace std;
using namespace cocos2d;

extern "C"
{
	int UmengLogEvent(int tag,const char* logEvent,CCDictionary* kv)
	{
		#if (CC_TARGET_PLATFORM != CC_PLATFORM_WIN32  )
				AnalyticsUmeng* pUmeng = dynamic_cast<AnalyticsUmeng*>(g_pAnalytics);
				//AnalyticsFlurry* pFlurry = dynamic_cast<AnalyticsFlurry*>(g_pAnalytics);
		
				CCLOG("tag:%d logEvent:%s",tag,logEvent);
				switch (tag)
				{
				case TAG_LOG_EVENT_ID:
					{
						g_pAnalytics->logEvent(logEvent);
					}
					break;
				case TAG_LOG_EVENT_ID_KV:
					{
						CCArray* _array = kv->allKeys();
						LogEventParamMap paramMap;
						CCObject* obj = NULL;
						CCARRAY_FOREACH(_array,obj){
							const char * k = ((CCString*)obj)->getCString();
							const char * value = ((CCString*)kv->objectForKey(std::string(k)))->getCString();
							CCLOG(" k:%s value:%s",k,value);
							paramMap.insert(LogEventParamPair(k, value));
						}
						g_pAnalytics->logEvent(logEvent, &paramMap);
					}
					break;
				case TAG_LOG_ONLINE_CONFIG:
					{
						if (pUmeng != NULL)
						{
							CCLOG("Online config = %s", pUmeng->getConfigParams("abc"));           
						}
						else
						{
							CCLOG("Now is not using umeng!");
						}
					}
					break;
				case TAG_LOG_EVENT_ID_DURATION:
					{
						if (pUmeng != NULL)
						{
							pUmeng->logEventWithDuration("book", 12000);
							pUmeng->logEventWithDuration("book", 23000, "chapter1");
							LogEventParamMap paramMap;
							paramMap.insert(LogEventParamPair("type", "popular"));
							paramMap.insert(LogEventParamPair("artist", "JJLin"));
							pUmeng->logEventWithDuration("music", 2330000, &paramMap);
						}
						else
						{
							CCLOG("Now is not using umeng!");
						}
					}
					break;
				case TAG_LOG_EVENT_BEGIN:
					{
						g_pAnalytics->logTimedEventBegin("music");

						LogEventParamMap paramMap;
						paramMap.insert(LogEventParamPair("type", "popular"));
						paramMap.insert(LogEventParamPair("artist", "JJLin"));

						if (pUmeng != NULL)
						{
							pUmeng->logTimedEventWithLabelBegin("music", "one");
							pUmeng->logTimedKVEventBegin("music", "flag0", &paramMap);
						}
						//else if (pFlurry != NULL)
						//{
						//	pFlurry->logTimedEventBegin("music-kv", &paramMap);
						//}
					}
					break;
				case TAG_LOG_EVENT_END:
					{
						g_pAnalytics->logTimedEventEnd("music");
						if (pUmeng != NULL)
						{          
							pUmeng->logTimedEventWithLabelEnd("music", "one");
							pUmeng->logTimedKVEventEnd("music", "flag0");
						}
					/*	else if (pFlurry != NULL)
						{
							pFlurry->logTimedEventEnd("music-kv");
						}*/
					}
					break;
				case TAG_MAKE_ME_CRASH:
					{
						char* pNull = NULL;
						*pNull = 0;
					}
					break;
				default:
					break;
				}

		#endif
		return 0;
	}
}