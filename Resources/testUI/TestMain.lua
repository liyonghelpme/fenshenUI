require("testUI/Splash")


collectgarbage("setpause", 100)
collectgarbage("setstepmul", 5000)
function __G__TRACKBACK__(msg)
    print("\n\n\n----------------------------------------")
    --Tips("脚本错误："..tostring(msg),13,ccc3(255,0,0))
    --DialogPanel.ShowMsg("脚本错误："..tostring(msg))
    local errorLog = msg.."\n"..debug.traceback()
    --将脚本异常信息输出到屏幕
    --Main_logToUi(errorLog)
    --将脚本异常信息发送到服务器
    --NetHttpReq.sendErrorLog(errorLog)
    --将脚本异常打印到命令行
    
    print(errorLog)
    print("----------------------------------------\n\n\n")
end
function main()
	local sc = createSplash()
	CCDirector:sharedDirector():runWithScene(sc)
end

xpcall(main, __G__TRACKBACK__)