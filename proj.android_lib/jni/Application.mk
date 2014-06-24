APP_STL := gnustl_static
APP_CPPFLAGS := -frtti -DCOCOS2D_DEBUG=1 -DANALYTICS_UMENG=1
APP_CPPFLAGS += -fexceptions -Wno-error=format-security
APP_PLATFORM := android-8
APP_ABI := armeabi x86
NDK_TOOLCHAIN_VERSION := clang

