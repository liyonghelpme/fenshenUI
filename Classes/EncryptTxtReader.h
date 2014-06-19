#ifndef __ENCRYPT_TXT_READER_H__
#define __ENCRYPT_TXT_READER_H__

#include "cocos2d.h"
using namespace cocos2d;

extern "C"
{
	char * readEncryptStr(const char* filePath);
	CCString* readEncryptTxt(const char* filePath);
}
#endif