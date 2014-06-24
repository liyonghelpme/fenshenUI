
require("testUI/util")
require("testUI/LoginUI")


--使用协程 处理
local function runWorkFlow(self)
	sendCommand("checkNetWork", "");
	self.lab:setString("checking ")
	while true do
		local ny = CCUserDefault:sharedUserDefault():getBoolForKey("_checkNetYet")
		print("checkNetYet ", ny)	
		if ny then
			CCUserDefault:sharedUserDefault():setBoolForKey("_checkNetYet", false)
			local nstate = CCUserDefault:sharedUserDefault():getBoolForKey("_netState")
			print("_netState is", nstat)
			if nstate then
				self.lab:setString("net is ok")
			else
				self.lab:setString("net is not connected")
			end
			break
		end
		coroutine.yield()
	end

	local passTime = 0
	while passTime < 1 do
		passTime = passTime+self.diff
		coroutine.yield()
	end

	sendCommand("checkActivation", "")
	while true do
		local ny = CCUserDefault:sharedUserDefault():getBoolForKey("_checkActYet")
		if ny then
			CCUserDefault:sharedUserDefault():setBoolForKey("_checkActYet", false)
			local act = CCUserDefault:sharedUserDefault():getBoolForKey("_activation");
			if act then
				self.lab2:setString("act is ok")
			else
				self.lab2:setString("act is not set yet")
			end
			break
		end
		coroutine.yield()
	end
	
	local passTime = 0
	while passTime < 1 do
		passTime = passTime+self.diff
		coroutine.yield()
	end


	sendCommand("checkDevice", "")

	while true do
		local ny = CCUserDefault:sharedUserDefault():getBoolForKey("_checkDevYet")
		if ny then
			CCUserDefault:sharedUserDefault():setBoolForKey("_checkDevYet", false)
			local act = CCUserDefault:sharedUserDefault():getBoolForKey("_device");
			if act then
				self.lab3:setString("dev is ok")
			else
				self.lab3:setString("dev is error")
			end
			break
		end
		coroutine.yield()
	end
	
	local passTime = 0
	while passTime < 1 do
		passTime = passTime+self.diff
		coroutine.yield()
	end
	sendCommand("checkUpdate", "")
	while true do
		local ny = CCUserDefault:sharedUserDefault():getBoolForKey("_checkUpdateYet")
		if ny then
			CCUserDefault:sharedUserDefault():setBoolForKey("_checkUpdateYet", false)
			local act = CCUserDefault:sharedUserDefault():getBoolForKey("_update");
			if act then
				self.lab4:setString("update is ok")
			else
				self.lab4:setString("update is error")
			end
			break
		end
		coroutine.yield()
	end

	sendCommand("checkApk", "")
	coroutine.yield()
	sendCommand("checkRes", "")
	coroutine.yield()
	sendCommand("gotoLogin", "")
	coroutine.yield()


	--等待10s
	local passTime = 0
	while passTime < 4 do
		passTime = passTime+self.diff
		coroutine.yield()
	end
end



local function update(self, diff)
	if not self.finish then
		self.diff = diff
		local res = coroutine.resume(self.workFlow, self)
		print(res)
		if not res then
			self.finish = true
			unRegisterUpdate(self)

			local sc = createLogin()
			CCDirector:sharedDirector():replaceScene(sc)

		end
	end
end

--runWorkFlow 
function createSplash()
	print("createSplash");
	local obj = {}
	obj.update = update
	obj.checkNetYet = false
	obj.state = 0


	local scene = CCScene:create()
	local layer = CCLayer:create()
	scene:addChild(layer)

	registerUpdate(obj)



	local sp = CCSprite:create("testUI/login_bg.png")
	layer:addChild(sp)
	
	local vs = CCDirector:sharedDirector():getVisibleSize()
	sp:setPosition(ccp(vs.width/2, vs.height/2))

	local lab = CCLabelTTF:create("not check Yet", "", 40);
	lab:setColor(ccc3(255, 0, 0))
	layer:addChild(lab)
	lab:setPosition(ccp(vs.width/2, vs.height/2+100))
	obj.lab = lab


	local lab = CCLabelTTF:create("not check Yet", "", 40);
	lab:setColor(ccc3(255, 100, 0))
	layer:addChild(lab)
	lab:setPosition(ccp(vs.width/2, vs.height/2+50))
	obj.lab2 = lab

	local lab = CCLabelTTF:create("not check Yet", "", 40);
	lab:setColor(ccc3(255, 100, 0))
	layer:addChild(lab)
	lab:setPosition(ccp(vs.width/2, vs.height/2+0))
	obj.lab3 = lab

	local lab = CCLabelTTF:create("not check Yet", "", 40);
	lab:setColor(ccc3(255, 100, 0))
	layer:addChild(lab)
	lab:setPosition(ccp(vs.width/2, vs.height/2-50))
	obj.lab4 = lab

	local lab = CCLabelTTF:create("not check Yet", "", 40);
	lab:setColor(ccc3(255, 100, 0))
	layer:addChild(lab)
	lab:setPosition(ccp(vs.width/2, vs.height/2-100))
	obj.lab5 = lab


	obj.workFlow = coroutine.create(runWorkFlow)


	return scene
end
