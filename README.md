###修改内容:
去掉zip包压缩,直接加载android 中assets 资源, 修复android 平台上加载lua脚本的一个bug 
暂时去掉了lua脚本加密
去掉了Splash 页面, 直接调用GameEntityActity 进入游戏
增加了Native.cpp Native.h MyCocos2dLua.cpp MyCocos2dLua.h Native.java 增加lua中直接调用java代码的接口sendCommand，通过CCUserDefault 本地文件传递一些数据

在Resources/testUI 文件夹里面加入了几个测试用的界面
TestMain.lua 启动lua文件
Splash.lua
LoginUI.lua
GameUI.lua
