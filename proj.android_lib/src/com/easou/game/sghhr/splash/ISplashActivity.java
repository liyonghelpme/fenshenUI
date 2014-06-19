package com.easou.game.sghhr.splash;


public interface ISplashActivity {
	void setLoadingProgress(int n);
	int getLoadingProgress();
	void updateProgressTips(String msg);
	void runWorkflow();
	void jumpToLoginCenterActivity();
}
