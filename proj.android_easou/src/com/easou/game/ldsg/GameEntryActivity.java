package com.easou.game.ldsg;

import android.content.Intent;

import com.easou.androidsdk.StartESPayCenter;
import com.easou.androidsdk.StartESPayCenter.PayCallBack;
import com.easou.bbs.SuspensionService;
import com.easou.game.sghhr.ServerInfo;
import com.easou.game.sghhr.common.BaseGameEntryActivity;
import com.easou.game.sghhr.common.LdsgPayBean;
import com.easou.game.sghhr.easou.Config;

public class GameEntryActivity extends BaseGameEntryActivity {
	@Override
	public void initSDKCallBack() {
		// TODO: 根据SDK文档说明，如果需要初始化SDK则此处进行，如果不需要则此方法可为空
	}

	/** 使用第三方SDK进行支付 */
	@Override
	public void pay(LdsgPayBean bean, ServerInfo info) {
		// 使用第三方SDK进行支付
		StartESPayCenter.startPortraitActivity(this, bean.getExt1(),
				new PayCallBack() {

					@Override
					public void paySuccess() {
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (Config.isOpenBBS) {
			Intent intent_su = new Intent(this, SuspensionService.class);
			this.startService(intent_su);
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (Config.isOpenBBS) {
			Intent intent = new Intent(this, SuspensionService.class);
			this.stopService(intent);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (Config.isOpenBBS) {
			Intent intent = new Intent(this, SuspensionService.class);
			this.stopService(intent);
		}
	}

	@Override
	public void onGameEnd() {
		// TODO Auto-generated method stub
		super.onGameEnd();
		if (Config.isOpenBBS) {
			Intent intent = new Intent(this, SuspensionService.class);
			this.stopService(intent);
		}
	}

}
