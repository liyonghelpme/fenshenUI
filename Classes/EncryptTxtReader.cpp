#include "EncryptTxtReader.h"
#include "utils.h"

extern "C"
{
	CCString* readEncryptTxt(const char* filePath)
	{
		#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID || CC_TARGET_PLATFORM == CC_PLATFORM_IOS)
			unsigned long desSize = 0;
			unsigned char * enc = CCFileUtils::sharedFileUtils()->getFileData(filePath, "rb", &desSize);
			char * raw = file_decrypt(enc,desSize);//Ω‚√‹

			CCString* ret = (CCString*)  CCString::create(raw);
			CC_SAFE_DELETE_ARRAY(enc);
			CC_SAFE_DELETE_ARRAY(raw);
			return ret;
		#else
			return CCString::createWithContentsOfFile(filePath);
		#endif
	}

    char * readEncryptStr(const char* filePath)
	{
	
		unsigned long desSize = 0;
		unsigned char * enc = CCFileUtils::sharedFileUtils()->getFileData(filePath, "rb", &desSize);
		char * raw = file_decrypt(enc,desSize);//Ω‚√‹

		//CCString* ret = (CCString*)  CCString::create(raw);
		//CC_SAFE_DELETE_ARRAY(enc);
		//CC_SAFE_DELETE_ARRAY(raw);
		return raw;
		
	}
}