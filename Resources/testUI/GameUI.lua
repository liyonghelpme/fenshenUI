function createGame()
	local scene = CCScene:create()
	local layer = CCLayer:create()
	scene:addChild(layer)

	local vs = CCDirector:sharedDirector():getVisibleSize()
	local sp = CCSprite:create("testUI/back.png")
	layer:addChild(sp)
	sp:setPosition(ccp(vs.width/2, vs.height/2))
	sp:runAction(CCRepeatForever:create(CCRotateBy:create(1, 360)))


	return scene
end