package com.easou.game.sghhr.splash;

import java.util.HashMap;

import com.easou.game.sghhr.CustomConfig;
import com.easou.game.sghhr.MobileFunction;
import com.easou.game.sghhr.NetRequest;
import com.easou.game.sghhr.NetResult;
import com.easou.game.sghhr.UpdateInfoBean;

public class CheckUpdate extends BaseStartTask {

	public CheckUpdate(SplashBaseActivity slpashActivity) {
		super(slpashActivity);
	}

	@Override
	public void runTask(HashMap<String, Object> para) {
		// Log.d("StartWorkFlow",this.getClass().getName());
		onProgressMsg("正在检测升级");
		// 获取更新信息
		NetResult netRequest = NetRequest.getUpdateDataRequest(
				super.mSplashActivity, CustomConfig.getApiDomain());

		if (netRequest != null && netRequest.getRc() == 1000) {// 获取升级信息成功，则进一步解析，是否要进行升级
			final UpdateInfoBean updateInfo = MobileFunction
					.buildJsonUploadInf(netRequest.getDt());

			HashMap<String, Object> result = new HashMap<String, Object>();

			if (updateInfo.getApkUrl() != null
					&& updateInfo.getApkUrl().length() > 0) {
				// 更新APK
				result.put("UPDATE_INFO", updateInfo);
				result.put("IS_NEED_UPDATE_APK", true);
			} else {
				// 更新资源文件
				result.put("UPDATE_INFO", updateInfo);
				result.put("IS_NEED_UPDATE_RES", true);
			}
			onFinish(result);
		} else {
			String errorMsg = "网络连接错误";
			int errorType = 0;
			if (netRequest != null) {
				errorMsg = netRequest.getMsg();
				errorType = 1;
			}
			final String errorMessagefinal = errorMsg;
			super.mSplashActivity.showAlertDialogForError(errorMessagefinal,
					errorType, getTag(), para);
			onError(1, "errorMessagefinal", null);
		}
	}
}
