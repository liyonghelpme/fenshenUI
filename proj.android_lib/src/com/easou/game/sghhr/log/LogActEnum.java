package com.easou.game.sghhr.log;

public enum LogActEnum {
	
	START(1),COPYASSERT(2),UNZIPASSERT(3),CHECKVERSION(4),
	CHECKSTATICDATA(5),REGISTER(6),SDKLOGIN(7),GETSERVERLIST(8),LOGIN(9),EAGAME(201),INTOBBS(301);
	
	private int act;

	private LogActEnum(int act){
		this.act = act;
	}

	public int getAct() {
		return act;
	}
}
