package com.easou.game.sghhr.splash;

import java.util.HashMap;

public interface IStartTask {
	void runTask(HashMap<String, Object> para);

	void setNextTask(IStartTask nextTask);

	IStartTask getNextTask();

	void onProgress(int n);

	void onProgressMsg(String msg);

	void onError(int code, String msg, Exception e);

	void onFinish(HashMap<String, Object> paraForNextTask);

	IStartTask setPower(int power);

	void setStartProgress(int startProgress);

	int getPower();

	void setTag(int tag);

	int getTag();
}
