package com.easou.game.sghhr;

public interface IPay {
	/**
	 * sdk接入，具体支付方法的实现
	 * @param payParaJsonStr
	 */
	public void doPay(final String payParaJsonStr);
}
