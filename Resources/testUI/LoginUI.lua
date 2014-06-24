require("testUI/GameUI")

local function touchBegan(x, y)
end
local function touchMoved(x, y)
end
local function touchEnded(x, y)
	CCDirector:sharedDirector():replaceScene(createGame())
end


function createLogin()
	local obj = {}
	local scene = CCScene:create()
	local layer = CCLayer:create()
	scene:addChild(layer)

	local vs = CCDirector:sharedDirector():getVisibleSize()

	
	local eb = CCEditBox:create(CCSizeMake(300, 50), "testUI/back.png")
	eb:setPosition(ccp(vs.width/2, vs.height/2+100))
	layer:addChild(eb)

	local eb = CCEditBox:create(CCSizeMake(300, 50), "testUI/back.png")
	eb:setPosition(ccp(vs.width/2, vs.height/2))
	layer:addChild(eb)


	local mi = CCMenuItemLabel:create(CCLabelTTF:create("登陆", "", 30))
	mi:setPosition(ccp(vs.width/2, vs.height/2-50));
	mi:registerScriptTapHandler(touchEnded)

	--local butObj = {touchBegan=touchBegan, touchMoved=touchMoved, touchEnded=touchEnded}
	
	local but = CCMenu:createWithItem(mi)
	layer:addChild(but)
	but:setContentSize(CCSizeMake(0, 0))
	but:setPosition(ccp(0, 0))


	--butObj.bg = but
	--registerTouch(butObj)

	return scene
end