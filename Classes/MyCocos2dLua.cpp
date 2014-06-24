#include "MyCocos2dLua.h"
#include "tolua_fix.h"
#include "cocos2d.h"
#include "CCLuaEngine.h"
#include "Native.h"

/* method: sendCmd of class  MyPlugins */
#ifndef TOLUA_DISABLE_tolua_Cocos2d_sendCmd00
static int tolua_Cocos2d_sendCommand00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     //!tolua_isusertype(tolua_S,1,"MyPlugins",0,&tolua_err) ||
     !tolua_isstring(tolua_S,1,0,&tolua_err) ||
     !tolua_isstring(tolua_S,2,0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,3,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  //MyPlugins* self = (MyPlugins*)  tolua_tousertype(tolua_S,1,0);
  const char* cmd = ((const char*)  tolua_tostring(tolua_S,1,0));
  const char* arg = ((const char*)  tolua_tostring(tolua_S,2,0));
#ifndef TOLUA_RELEASE
  //if (!self) tolua_error(tolua_S,"invalid 'self' in function 'sendCommand'", NULL);
#endif
  {
   //self->sendCmd(cmd, arg);
  	sendCommand(cmd, arg);
  }
 }
 return 0;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'sendCommand'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE



TOLUA_API int tolua_myext_reg_types(lua_State* tolua_S)
{

 //tolua_usertype(tolua_S,"MyPlugins");
 	return 1;
}


TOLUA_API int tolua_myext_reg_modules(lua_State* tolua_S)
{
	tolua_function(tolua_S,"sendCommand", tolua_Cocos2d_sendCommand00);

  /*
  tolua_function(tolua_S,"setScriptTouchPriority", tolua_Cocos2d_setScriptTouchPriority00);

  tolua_cclass(tolua_S,"MyPlugins","MyPlugins","",NULL);
  tolua_beginmodule(tolua_S,"MyPlugins");
   tolua_function(tolua_S,"getInstance",tolua_Cocos2dExt_MyPlugins_getInstance00);
  tolua_endmodule(tolua_S);
  */
  return 1;
}


int tolua_MyExt_open(lua_State *tolua_S) {
	tolua_open(tolua_S);
    tolua_myext_reg_types(tolua_S);
    tolua_module(tolua_S, NULL, 0);
    tolua_beginmodule(tolua_S, NULL);
    tolua_myext_reg_modules(tolua_S);
    tolua_endmodule(tolua_S);
	return 1;
}
