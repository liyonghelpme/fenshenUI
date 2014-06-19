package com.easou.game.sghhr.secretarydialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easou.game.sghhr.R;

/** 小秘书activity */
public class SecretaryDialog extends Activity implements OnClickListener {
	private View btnConfirm, btnCancel, btnRest;
	private TextView textView1, textView2;
	private LinearLayout layoutbackground;
	private static DialogCallBack callBack;
	public static final int CONFIRM = 1;
	public static final int CANCAL = 2;
	public static final int REST = 3;
	/** 确认取消界面 */
	public static final int pointView = 4;
	/** 休息一会界面 */
	public static final int downView = 5;
	/** 下载选择界面 */
	public static final int downSelectView = 6;

	/** 小秘书点击事件回调 */
	public interface DialogCallBack {
		/** 确认按钮被点击 */
		void confirmPressed();

		/** 取消按钮被点击 */
		void cancalPressed();

		/** 休息一会按钮被点击 */
		void restPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secretary);
		btnConfirm = findViewById(R.id.btnConfirm);
		btnCancel = findViewById(R.id.btnCancel);
		btnRest = findViewById(R.id.btnRest);
		layoutbackground = (LinearLayout) findViewById(R.id.layoutBackground);
		btnConfirm.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		btnRest.setOnClickListener(this);
		int witchView = getIntent().getIntExtra("witchView", 111);
		switch (witchView) {
		case pointView:
			btnConfirm.setVisibility(0);
			btnCancel.setVisibility(0);
			btnRest.setVisibility(8);
			layoutbackground
					.setBackgroundResource(R.drawable.layoutbackground1);
			break;
		case downView:
			btnConfirm.setVisibility(8);
			btnCancel.setVisibility(8);
			btnRest.setVisibility(0);
			layoutbackground
					.setBackgroundResource(R.drawable.layoutbackground1);
			break;
		case downSelectView:
			btnConfirm.setVisibility(0);
			btnCancel.setVisibility(0);
			btnRest.setVisibility(8);
			layoutbackground
					.setBackgroundResource(R.drawable.layoutbackground2);
			break;
		default:
			break;
		}

		String text1 = getIntent().getStringExtra("text1");
		String text2 = getIntent().getStringExtra("text2");
		textView1 = (TextView) findViewById(R.id.text1);
		textView2 = (TextView) findViewById(R.id.text2);
		if (text1 != null && text2 != null) {
			textView1.setText(text1);
			textView2.setText(text2);
			textView2.setMovementMethod(ScrollingMovementMethod.getInstance());
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.btnConfirm) {
			this.finish();
			callBack.confirmPressed();
		} else if (v.getId() == R.id.btnCancel) {
			this.finish();
			callBack.cancalPressed();
		} else if (v.getId() == R.id.btnRest) {
			this.finish();
			callBack.restPressed();
		}
	}

	/**
	 * 启动小秘书提示
	 * 
	 * @param context
	 *            相关上下文
	 * @param witchView
	 *            小秘书按钮支持 SecretaryDialog.pointView 或 SecretaryDialog.downView
	 * 
	 * @param callBack
	 *            回调接口
	 */
	public static void startSecretaryDialog(Context context, String text1,
			String text2, int witchView, DialogCallBack callBack) {
		SecretaryDialog.callBack = callBack;
		Intent intent = new Intent();
		intent.putExtra("witchView", witchView);
		intent.putExtra("text1", text1);
		intent.putExtra("text2", text2);
		intent.setClass(context, SecretaryDialog.class);
		context.startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

	}

}
