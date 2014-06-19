/*
** Lua binding: AppstorePurchase
** Generated automatically by tolua++-1.0.92 on 02/17/12 13:37:11.
*/

#ifndef __cplusplus
#include "stdlib.h"
#endif
#include "string.h"

#include "tolua++.h"
#include "AppstorePurchase.h"

/* Exported function */
TOLUA_API int  tolua_AppstorePurchase_open (lua_State* tolua_S);


/* function to release collected object via destructor */
#ifdef __cplusplus

static int tolua_collect_CAppstorePurchase (lua_State* tolua_S)
{
 CAppstorePurchase* self = (CAppstorePurchase*) tolua_tousertype(tolua_S,1,0);
	Mtolua_delete(self);
	return 0;
}
#endif


/* function to register type */
static void tolua_reg_types (lua_State* tolua_S)
{
#ifndef Mtolua_typeid
#define Mtolua_typeid(L,TI,T)
#endif
 tolua_usertype(tolua_S,"CAppstorePurchase");
 Mtolua_typeid(tolua_S,typeid(CAppstorePurchase), "CAppstorePurchase");
}

/* method: new of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_new00
static int tolua_AppstorePurchase_CAppstorePurchase_new00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertable(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,2,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  {
   CAppstorePurchase* tolua_ret = (CAppstorePurchase*)  Mtolua_new((CAppstorePurchase)());
    tolua_pushusertype(tolua_S,(void*)tolua_ret,"CAppstorePurchase");
  }
 }
 return 1;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'new'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: new_local of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_new00_local
static int tolua_AppstorePurchase_CAppstorePurchase_new00_local(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertable(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,2,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  {
   CAppstorePurchase* tolua_ret = (CAppstorePurchase*)  Mtolua_new((CAppstorePurchase)());
    tolua_pushusertype(tolua_S,(void*)tolua_ret,"CAppstorePurchase");
    tolua_register_gc(tolua_S,lua_gettop(tolua_S));
  }
 }
 return 1;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'new'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: delete of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_delete00
static int tolua_AppstorePurchase_CAppstorePurchase_delete00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,2,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'delete'", NULL);
#endif
  Mtolua_delete(self);
 }
 return 0;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'delete'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: getInstance of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_getInstance00
static int tolua_AppstorePurchase_CAppstorePurchase_getInstance00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertable(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,2,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  {
   CAppstorePurchase* tolua_ret = (CAppstorePurchase*)  CAppstorePurchase::getInstance();
    tolua_pushusertype(tolua_S,(void*)tolua_ret,"CAppstorePurchase");
  }
 }
 return 1;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'getInstance'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: registerPaymentQueue of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_registerPaymentQueue00
static int tolua_AppstorePurchase_CAppstorePurchase_registerPaymentQueue00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_iscppstring(tolua_S,2,0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,3,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
  const std::string strFun = ((const std::string)  tolua_tocppstring(tolua_S,2,0));
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'registerPaymentQueue'", NULL);
#endif
  {
   self->registerPaymentQueue(strFun);
   tolua_pushcppstring(tolua_S,(const char*)strFun);
  }
 }
 return 1;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'registerPaymentQueue'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: registerIsCanMakePayments of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_registerIsCanMakePayments00
static int tolua_AppstorePurchase_CAppstorePurchase_registerIsCanMakePayments00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_iscppstring(tolua_S,2,0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,3,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
  const std::string strFun = ((const std::string)  tolua_tocppstring(tolua_S,2,0));
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'registerIsCanMakePayments'", NULL);
#endif
  {
   self->registerIsCanMakePayments(strFun);
   tolua_pushcppstring(tolua_S,(const char*)strFun);
  }
 }
 return 1;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'registerIsCanMakePayments'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: registerRequestProduct of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_registerRequestProduct00
static int tolua_AppstorePurchase_CAppstorePurchase_registerRequestProduct00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_iscppstring(tolua_S,2,0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,3,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
  const std::string strFun = ((const std::string)  tolua_tocppstring(tolua_S,2,0));
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'registerRequestProduct'", NULL);
#endif
  {
   self->registerRequestProduct(strFun);
   tolua_pushcppstring(tolua_S,(const char*)strFun);
  }
 }
 return 1;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'registerRequestProduct'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: excCallback of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_excCallback00
static int tolua_AppstorePurchase_CAppstorePurchase_excCallback00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_iscppstring(tolua_S,2,0,&tolua_err) ||
     !tolua_isnumber(tolua_S,3,0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,4,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
  const std::string strFun = ((const std::string)  tolua_tocppstring(tolua_S,2,0));
  int value = ((int)  tolua_tonumber(tolua_S,3,0));
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'excCallback'", NULL);
#endif
  {
   self->excCallback(strFun,value);
   tolua_pushcppstring(tolua_S,(const char*)strFun);
  }
 }
 return 1;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'excCallback'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: buyProduct of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_buyProduct00
static int tolua_AppstorePurchase_CAppstorePurchase_buyProduct00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_iscppstring(tolua_S,2,0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,3,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
  const std::string strGoodsID = ((const std::string)  tolua_tocppstring(tolua_S,2,0));
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'buyProduct'", NULL);
#endif
  {
   self->buyProduct(strGoodsID);
   tolua_pushcppstring(tolua_S,(const char*)strGoodsID);
  }
 }
 return 1;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'buyProduct'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: excReuestProductCallback of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_excReuestProductCallback00
static int tolua_AppstorePurchase_CAppstorePurchase_excReuestProductCallback00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_isnumber(tolua_S,2,0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,3,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
  int retValue = ((int)  tolua_tonumber(tolua_S,2,0));
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'excReuestProductCallback'", NULL);
#endif
  {
   self->excReuestProductCallback(retValue);
  }
 }
 return 0;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'excReuestProductCallback'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: excPaymentQueue of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_excPaymentQueue00
static int tolua_AppstorePurchase_CAppstorePurchase_excPaymentQueue00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_isnumber(tolua_S,2,0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,3,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
  int retValue = ((int)  tolua_tonumber(tolua_S,2,0));
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'excPaymentQueue'", NULL);
#endif
  {
   self->excPaymentQueue(retValue);
  }
 }
 return 0;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'excPaymentQueue'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: setTranscationReceipt of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_setTranscationReceipt00
static int tolua_AppstorePurchase_CAppstorePurchase_setTranscationReceipt00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_iscppstring(tolua_S,2,0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,3,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
  std::string str = ((std::string)  tolua_tocppstring(tolua_S,2,0));
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'setTranscationReceipt'", NULL);
#endif
  {
   self->setTranscationReceipt(str);
   tolua_pushcppstring(tolua_S,(const char*)str);
  }
 }
 return 1;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'setTranscationReceipt'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: getTranscationReceipt of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_getTranscationReceipt00
static int tolua_AppstorePurchase_CAppstorePurchase_getTranscationReceipt00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,2,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'getTranscationReceipt'", NULL);
#endif
  {
   const char* tolua_ret = (const char*)  self->getTranscationReceipt();
   tolua_pushstring(tolua_S,(const char*)tolua_ret);
  }
 }
 return 1;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'getTranscationReceipt'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: setRecordTransactionErrorCode of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_setRecordTransactionErrorCode00
static int tolua_AppstorePurchase_CAppstorePurchase_setRecordTransactionErrorCode00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_isnumber(tolua_S,2,0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,3,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
  int value = ((int)  tolua_tonumber(tolua_S,2,0));
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'setRecordTransactionErrorCode'", NULL);
#endif
  {
   self->setRecordTransactionErrorCode(value);
  }
 }
 return 0;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'setRecordTransactionErrorCode'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* method: getRecordTransactionErrorCode of class  CAppstorePurchase */
#ifndef TOLUA_DISABLE_tolua_AppstorePurchase_CAppstorePurchase_getRecordTransactionErrorCode00
static int tolua_AppstorePurchase_CAppstorePurchase_getRecordTransactionErrorCode00(lua_State* tolua_S)
{
#ifndef TOLUA_RELEASE
 tolua_Error tolua_err;
 if (
     !tolua_isusertype(tolua_S,1,"CAppstorePurchase",0,&tolua_err) ||
     !tolua_isnoobj(tolua_S,2,&tolua_err)
 )
  goto tolua_lerror;
 else
#endif
 {
  CAppstorePurchase* self = (CAppstorePurchase*)  tolua_tousertype(tolua_S,1,0);
#ifndef TOLUA_RELEASE
  if (!self) tolua_error(tolua_S,"invalid 'self' in function 'getRecordTransactionErrorCode'", NULL);
#endif
  {
   int tolua_ret = (int)  self->getRecordTransactionErrorCode();
   tolua_pushnumber(tolua_S,(lua_Number)tolua_ret);
  }
 }
 return 1;
#ifndef TOLUA_RELEASE
 tolua_lerror:
 tolua_error(tolua_S,"#ferror in function 'getRecordTransactionErrorCode'.",&tolua_err);
 return 0;
#endif
}
#endif //#ifndef TOLUA_DISABLE

/* Open function */
TOLUA_API int tolua_AppstorePurchase_open (lua_State* tolua_S)
{
 tolua_open(tolua_S);
 tolua_reg_types(tolua_S);
 tolua_module(tolua_S,NULL,0);
 tolua_beginmodule(tolua_S,NULL);
  #ifdef __cplusplus
  tolua_cclass(tolua_S,"CAppstorePurchase","CAppstorePurchase","",tolua_collect_CAppstorePurchase);
  #else
  tolua_cclass(tolua_S,"CAppstorePurchase","CAppstorePurchase","",NULL);
  #endif
  tolua_beginmodule(tolua_S,"CAppstorePurchase");
   tolua_function(tolua_S,"new",tolua_AppstorePurchase_CAppstorePurchase_new00);
   tolua_function(tolua_S,"new_local",tolua_AppstorePurchase_CAppstorePurchase_new00_local);
   tolua_function(tolua_S,".call",tolua_AppstorePurchase_CAppstorePurchase_new00_local);
   tolua_function(tolua_S,"delete",tolua_AppstorePurchase_CAppstorePurchase_delete00);
   tolua_function(tolua_S,"getInstance",tolua_AppstorePurchase_CAppstorePurchase_getInstance00);
   tolua_function(tolua_S,"registerPaymentQueue",tolua_AppstorePurchase_CAppstorePurchase_registerPaymentQueue00);
   tolua_function(tolua_S,"registerIsCanMakePayments",tolua_AppstorePurchase_CAppstorePurchase_registerIsCanMakePayments00);
   tolua_function(tolua_S,"registerRequestProduct",tolua_AppstorePurchase_CAppstorePurchase_registerRequestProduct00);
   tolua_function(tolua_S,"excCallback",tolua_AppstorePurchase_CAppstorePurchase_excCallback00);
   tolua_function(tolua_S,"buyProduct",tolua_AppstorePurchase_CAppstorePurchase_buyProduct00);
   tolua_function(tolua_S,"excReuestProductCallback",tolua_AppstorePurchase_CAppstorePurchase_excReuestProductCallback00);
   tolua_function(tolua_S,"excPaymentQueue",tolua_AppstorePurchase_CAppstorePurchase_excPaymentQueue00);
   tolua_function(tolua_S,"setTranscationReceipt",tolua_AppstorePurchase_CAppstorePurchase_setTranscationReceipt00);
   tolua_function(tolua_S,"getTranscationReceipt",tolua_AppstorePurchase_CAppstorePurchase_getTranscationReceipt00);
   tolua_function(tolua_S,"setRecordTransactionErrorCode",tolua_AppstorePurchase_CAppstorePurchase_setRecordTransactionErrorCode00);
   tolua_function(tolua_S,"getRecordTransactionErrorCode",tolua_AppstorePurchase_CAppstorePurchase_getRecordTransactionErrorCode00);
  tolua_endmodule(tolua_S);
 tolua_endmodule(tolua_S);
 return 1;
}


#if defined(LUA_VERSION_NUM) && LUA_VERSION_NUM >= 501
 TOLUA_API int luaopen_AppstorePurchase (lua_State* tolua_S) {
 return tolua_AppstorePurchase_open(tolua_S);
};
#endif

