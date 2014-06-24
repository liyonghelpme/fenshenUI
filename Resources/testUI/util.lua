function registerUpdate(obj)
	local function update(diff)
		obj:update(diff)
	end
	obj.updateFunc = CCDirector:sharedDirector():getScheduler():scheduleScriptFunc(update, 0, false)
end
function unRegisterUpdate(obj)
	CCDirector:sharedDirector():getScheduler():unscheduleScriptEntry(obj.updateFunc)
end


function registerTouch(obj, pri)
    local function onTouch(eventType, x, y)
        if eventType == "began" then   
            return obj:touchBegan(x, y)
        elseif eventType == "moved" then
            return obj:touchMoved(x, y)
        else
            return obj:touchEnded(x, y)
        end
    end
    if pri == nil then
        pri = 128
    end
    --single Touch
    obj.bg:registerScriptTouchHandler(onTouch, false, pri, true)
    obj.bg:setTouchEnabled(true)
end