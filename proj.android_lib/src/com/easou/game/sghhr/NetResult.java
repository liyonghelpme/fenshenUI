package com.easou.game.sghhr;

public class NetResult {
	private int rc;
	private String msg;
	private String dt;
	public int getRc() {
		return rc;
	}
	public void setRc(int rc) {
		this.rc = rc;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	@Override
	public String toString() {
		return "NetResult [rc=" + rc + ", msg=" + msg + ", dt=" + dt + "]";
	}
	
	
}
