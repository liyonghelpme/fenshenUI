package com.easou.game.sghhr;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivationActivity extends Activity {
	private final int ACTIVATION_CODE_LEN = 6;
	private EditText edittext_activation_code;
	private Button button_do_active;
	private CustomProgressDialog mDialog;
	private Handler mHandler;
	private Context instance;
	private String apiDomain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activation);
		instance = this;
		mHandler = new Handler();
		findView();
		addListener();
	}

	private void addListener() {
		button_do_active.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String code = edittext_activation_code.getText()
						.toString();
				if (code != null && code.length() == ACTIVATION_CODE_LEN) {
					showProgressDialog();
					new Thread() {
						public void run() {
							boolean activeResult = Activation.doActive(code,instance,apiDomain);
							hideProgressDialog();
							if (activeResult) {
								showToast("恭喜您，激活成功！");
								ActivationActivity.this.setResult(Activation.ACTIVATION_SUCC);
								ActivationActivity.this.finish();
							} else {
								showToast("激活失败");
							}
						};
					}.start();

				} else {
					showToast("激活码有误");
				}
			}
		});

	}

	protected void showToast(final String msg) {
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(ActivationActivity.this, msg, Toast.LENGTH_LONG).show();
			}
		});
		
	}

	protected void showProgressDialog() {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				mDialog = CustomProgressDialog
						.createDialog(ActivationActivity.this);
				mDialog.setMessage("激活中，请稍后...");
				mDialog.setCancelable(false);
				mDialog.show();
			}
		});
	}

	protected void hideProgressDialog() {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				if (mDialog != null && mDialog.isShowing()) {
					mDialog.hide();
					mDialog.cancel();
					mDialog = null;
				}
			}
		});
	}

	private void findView() {
		edittext_activation_code = (EditText) findViewById(R.id.edittext_activation_code);
		button_do_active = (Button) findViewById(R.id.button_do_active);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		if(App.isOrderToExit()){
//			this.finish();
//		}
	}
	
	@Override
	public void onBackPressed() {
		Activation.stopAActivity();
		this.finish();
		super.onBackPressed();
	}
}

