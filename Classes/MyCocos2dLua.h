#ifndef __MYCOCOS2DLUA__
#define __MYCOCOS2DLUA__
#include "tolua++.h"


#if !defined(COCOS2D_DEBUG) || COCOS2D_DEBUG == 0
#define TOLUA_RELEASE
#endif


TOLUA_API int tolua_MyExt_open(lua_State *);

#endif