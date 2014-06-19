package com.easou.game.sghhr.common;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easou.game.sghhr.BackgroundMusicService;
import com.easou.game.sghhr.R;
import com.easou.game.sghhr.ResUpdateUtil;
import com.easou.game.sghhr.SpHelper;
import com.easou.game.sghhr.splash.SplashBaseActivity;
import com.umeng.analytics.MobclickAgent;

public class BaseSplashActivity extends SplashBaseActivity {
	private TextView loading_tips;
	private TextView loading_first_tips;
	private LinearLayout load_progrss_ly;
	private int mCurrentProgress = 0;
	private RelativeLayout image_background;
	private ImageView img_bg_head;
	private boolean isChangeProgress;

	/**
	 * 初始化控件
	 */
	protected void findView() {
		loading_tips = (TextView) findViewById(R.id.loading_tips);
		load_progrss_ly = (LinearLayout) findViewById(R.id.loadign_ly);
		image_background = (RelativeLayout) findViewById(R.id.image_background);
		//img_bg_head = (ImageView) findViewById(R.id.image_bg_head);
		int count = load_progrss_ly.getChildCount();
		for (int i = 0; i < count; i++) {
			View view = load_progrss_ly.getChildAt(i);
			view.setVisibility(View.INVISIBLE);
		}
		loading_first_tips = (TextView) findViewById(R.id.loading_first_tips);
	}

	/** 背景更换资源文件库 */
	private static int[] resource;

	public static int[] getResource() {
		return resource;
	}

	public static void setResource(int[] resource) {
		BaseSplashActivity.resource = resource;
	}

	/** 开始动态背景 */
	protected void startBackground(final int count) {
		//img_bg_head.setVisibility(View.INVISIBLE);
		if (getResource().length == 1) {
			image_background.setBackgroundResource(getResource()[0]);
			new Timer().schedule(new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							startBackground(count);
						}
					});
					cancel();
				}
			}, 2000);
			return;
		}
		image_background.setBackgroundResource(getResource()[count]);
		Animation fade_in = AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in);
		final Animation fade_out = AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out);
		fade_in.setDuration(500);
		fade_out.setDuration(500);
		fade_in.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				new Timer().schedule(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						image_background.setAnimation(fade_out);
						fade_out.start();
						cancel();
					}
				}, 3000);
			}
		});
		fade_out.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				startBackground((count + 1) % getResource().length);
			}
		});
		image_background.setAnimation(fade_in);
		fade_in.start();
	}

	/** 开始动态进度条 */
	protected void startProgrssBar() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					int count = load_progrss_ly.getChildCount();
					int[] drawables = new int[count];
					boolean temp = true;
					isChangeProgress = true;
					while (isChangeProgress) {
						if (temp) {
							drawables[0] = R.drawable.loading_progress_03;
							drawables[count - 1] = R.drawable.loading_progress_06;
							for (int i = 1; i < count - 1; i++) {
								if (i % 2 == 0) {
									drawables[i] = R.drawable.loading_progress_01;
								} else {
									drawables[i] = R.drawable.loading_progress_02;
								}
							}
						} else {
							drawables[0] = R.drawable.loading_progress_04;
							drawables[count - 1] = R.drawable.loading_progress_05;
							for (int i = 1; i < count - 1; i++) {
								if (i % 2 == 0) {
									drawables[i] = R.drawable.loading_progress_02;
								} else {
									drawables[i] = R.drawable.loading_progress_01;
								}
							}
						}
						temp = !temp;
						Message msg = new Message();
						msg.obj = drawables;
						handler.sendMessage(msg);
						Thread.sleep(200);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int count = load_progrss_ly.getChildCount();
			int[] drawables = (int[]) msg.obj;
			for (int i = 0; i < count; i++) {
				load_progrss_ly.getChildAt(i).setBackgroundResource(
						drawables[i]);
			}
		}
	};

	/**
	 * 初始化
	 */
	protected void init() {
		// 初始化控件
		findView();
		// 启动动态进度条
		startProgrssBar();
		// 启动动态背景
		setResource(new int[] { R.drawable.background0 });
		startBackground(0);
		BaseApp.setOrderToExit(false);
		SpHelper.setContext(this);
		if (!ResUpdateUtil.isFirstStart(this)) {
			loading_first_tips.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		if (BaseApp.isOrderToExit()) {
			this.finish();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
		// 暂停音乐
		BackgroundMusicService.pauseMusic();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		isChangeProgress = false;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(Intent.ACTION_MAIN);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.addCategory(Intent.CATEGORY_HOME);
		startActivity(i);
	}

	@Override
	public void setLoadingProgress(final int n) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				mCurrentProgress = n;
				int count = load_progrss_ly.getChildCount();
				int process = n / (100 / count); // 有多少份。
				if (process > 0 && process <= count) {
					for (int i = 0; i < process; i++) {
						load_progrss_ly.getChildAt(i).setVisibility(
								View.VISIBLE);
					}
				}
			}
		});

	}

	@Override
	public int getLoadingProgress() {
		return mCurrentProgress;
	}

	@Override
	public void updateProgressTips(final String msg) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				loading_tips.setText(msg);

			}
		});
	}

	@Override
	public void jumpToLoginCenterActivity() {
	}
}
