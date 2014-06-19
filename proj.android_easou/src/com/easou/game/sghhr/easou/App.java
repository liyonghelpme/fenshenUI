package com.easou.game.sghhr.easou;

import java.util.Properties;

import android.util.Log;

import com.easou.game.sghhr.CustomConfig;
import com.easou.game.sghhr.common.BaseApp;

public class App extends BaseApp{
	@Override
	public void onCreate() {
		instance = this;
		Config config = new Config();
		String qn = "9000001";
		try {
            Properties properties = new Properties();
            properties.load(getContext().getAssets().open("client.properties"));
            qn = properties.getProperty("qn","9000001");
		} catch (Exception e) {
			Log.e("","read qn error!",e);
		}
		CustomConfig.init(config.getPartenerId(),config.getApiDomain(),Config.isNeedActivation,qn);
		super.onCreate();
	}
}
