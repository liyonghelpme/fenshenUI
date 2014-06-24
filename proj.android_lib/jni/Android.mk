LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)




LOCAL_MODULE := ldsggame_shared

LOCAL_MODULE_FILENAME := libldsggame

LOCAL_SRC_FILES := ldsggame/main.cpp \
                   ../../Classes/AppDelegate.cpp \
                   ../../Classes/net/MessageQueue.cpp \
                   ../../Classes/net/ODSocket.cpp \
                   ../../Classes/net/SocketListener.cpp \
                   ../../Classes/net/SocketMananger.cpp \
                   ../../Classes/net/TcpMessageBuilder.cpp \
                   ldsggame/pay.cpp \
                   ../../Classes/Native.cpp \
                   ../../Classes/MyCocos2dLua.cpp \




LOCAL_C_INCLUDES := $(LOCAL_PATH)/../../Classes \
					$(LOCAL_PATH)/../../Classes/net \
					$(LOCAL_PATH)/../../../../../plugin-x/protocols/include \
                    $(LOCAL_PATH)/../../../../plugin-x/protocols/include


LOCAL_C_INCLUDES += $(LOCAL_PATH)/../../../../../cocos2dx/platform/android/jni

LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static
LOCAL_WHOLE_STATIC_LIBRARIES += cocosdenshion_static
LOCAL_WHOLE_STATIC_LIBRARIES += cocos_extension_static
LOCAL_WHOLE_STATIC_LIBRARIES += cocos_lua_static
LOCAL_WHOLE_STATIC_LIBRARIES += PluginUmengStatic PluginProtocolStatic

include $(BUILD_SHARED_LIBRARY)


#import sequecen headfile sequence
$(call import-module,umeng/proj.android/jni) 
$(call import-module,protocols/proj.android/jni)

$(call import-module,cocos2dx)
$(call import-module,CocosDenshion/android)
$(call import-module,extensions)
$(call import-module,scripting/lua/proj.android/jni) 
