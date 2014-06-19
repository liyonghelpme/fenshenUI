package com.easou.game.sghhr.splash;

import java.util.HashMap;

public abstract class BaseStartTask implements IStartTask {
	private int power = 0;
	private IStartTask nextTask = null;
	protected SplashBaseActivity mSplashActivity = null;
	private int currentStepStartProgress = 0;
	private int tag = 0;

	public BaseStartTask(SplashBaseActivity slpashActivity) {
		this.mSplashActivity = slpashActivity;
	}

	@Override
	public void setTag(int tag) {
		// TODO Auto-generated method stub
		this.tag = tag;
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return this.tag;
	}

	@Override
	public void setNextTask(IStartTask nextTask) {
		this.nextTask = nextTask;
	}

	@Override
	public IStartTask getNextTask() {
		return this.nextTask;
	}

	@Override
	public IStartTask setPower(int power) {
		this.power = power;
		return this;
	}

	@Override
	public int getPower() {
		return power;
	}

	@Override
	public void setStartProgress(int startProgress) {
		currentStepStartProgress = startProgress;

	}

	@Override
	public void onProgress(int n) {
		if (n > 100) {
			n = 100;
		} else if (n < 0) {
			n = 0;
		}
		// Log.e("onProgress",""+n);
		mSplashActivity.setLoadingProgress(currentStepStartProgress + n * power
				/ 100);
	}

	@Override
	public void onProgressMsg(String msg) {
		mSplashActivity.updateProgressTips(msg);

	}

	@Override
	public void onError(int code, String msg, Exception e) {
		// Log.d("BaseStartTask", msg);
		if (e != null) {
			e.printStackTrace();
		}
	}

	@Override
	public void onFinish(HashMap<String, Object> paraForNextTask) {
		onProgress(100);
		IStartTask nextTask = getNextTask();
		if (nextTask != null) {
			nextTask.runTask(paraForNextTask);
		} else {
			// Log.d("BaseStartTask", "没有下一下启动任务了");
		}
	}

}
