package com.easou.game.sghhr.common;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.easou.game.sghhr.R;

/**
 * UI助手类
 * 
 * @author ted
 */
public class UIHelper {

	private UIHelper() {
	}

	public static final int UIHELPER_HANDLER_SHOW_RELOGIN_DIALOG = 0x1; // 显示重新登录对话框
	public static final int UIHELPER_HANDLER_SHOW_NONETWORK_DIALOG = 0x2; // 显示网络设置对话框
	public static final int UIHELPER_HANDLER_UPDATE_SERVERNAME_TEXT = 0x3; // 更新服务器名称
	public static final int UIHELPER_HANDLER_DOLOGIN = 0x4; // 登录doLogin

	/**
	 * 登录成功后，播放三国合伙人标题动画
	 */
	public static void startLoginAnimation(final Activity activity,
			final Handler mHandler) {
		final ViewHolder viewHolder = new ViewHolder();
//		viewHolder.flagView = (ImageView) activity.findViewById(R.id.flag);
//		viewHolder.left_cloude = (ImageView) activity
//				.findViewById(R.id.left_cloude);
//		viewHolder.right_cloude = (ImageView) activity
//				.findViewById(R.id.right_cloude);

		activity.findViewById(R.id.login_animation_area).setVisibility(
				View.VISIBLE);
		// 三国合伙人
//		Animation titleLogoAnimation = AnimationUtils.loadAnimation(activity,
//				R.anim.title_logo_animation);
//		ImageView imageView = (ImageView) activity
//				.findViewById(R.id.title_logo);
//		imageView.startAnimation(titleLogoAnimation);
//		viewHolder.flagView.startAnimation(titleLogoAnimation);
//		viewHolder.left_cloude.startAnimation(titleLogoAnimation);
//		viewHolder.right_cloude.startAnimation(titleLogoAnimation);

//		mHandler.postDelayed(new Runnable() {
//			@Override
//			public void run() {
				startSecondAnimation(activity, mHandler, viewHolder);
//			}
//		}, 1000);
	}

	private static void startSecondAnimation(Activity activity,
			Handler mHandler, ViewHolder viewHolder) {

//		viewHolder.left_cloude.clearAnimation();
//		viewHolder.right_cloude.clearAnimation();
//
//		// 旗子
		// ImageView flagView = (ImageView) findViewById(R.id.flag);
//		viewHolder.flagView.setBackgroundResource(R.drawable.flag_animation);
//		AnimationDrawable animationDrawable = (AnimationDrawable) viewHolder.flagView
//				.getBackground();
//		animationDrawable.start();
//
//		// 左边的云彩
		// ImageView left_cloude = (ImageView) findViewById(R.id.left_cloude);
//		Animation left_cloudeAnimation = new TranslateAnimation(0, -6, 0, 0);
//		left_cloudeAnimation.setRepeatMode(Animation.REVERSE);
//		left_cloudeAnimation.setDuration(833);
//		left_cloudeAnimation.setRepeatCount(10000);
//		viewHolder.left_cloude.setAnimation(left_cloudeAnimation);
//		left_cloudeAnimation.start();
//
//		// 右边的云彩
		// ImageView right_cloude = (ImageView) findViewById(R.id.right_cloude);
//		Animation right_cloudeAnimation = new TranslateAnimation(0, 6, 0, 0);
//		right_cloudeAnimation.setRepeatMode(Animation.REVERSE);
//		right_cloudeAnimation.setDuration(833);
//		right_cloudeAnimation.setRepeatCount(10000);
//		viewHolder.right_cloude.setAnimation(right_cloudeAnimation);
//		right_cloudeAnimation.start();

//		// 射箭
//		final ImageView arrowImage = (ImageView) activity
//				.findViewById(R.id.arrow);
//		arrowImage.setVisibility(View.VISIBLE);
//		arrowImage.setBackgroundResource(R.drawable.arrow_fly_animation);
//		final AnimationDrawable arrowAD = (AnimationDrawable) arrowImage
//				.getBackground();
//		arrowAD.setOneShot(true);
//		arrowAD.start();
//		mHandler.postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				arrowAD.stop();
//				arrowImage.setBackgroundResource(R.drawable.arrow12);
//			}
//		}, 660);
		
		
		
		// 鸟
		final ImageView birdImage = (ImageView) activity
				.findViewById(R.id.bird);
		birdImage.setVisibility(View.VISIBLE);
		birdImage.setBackgroundResource(R.drawable.bird_fly_animation);
		final AnimationDrawable bird = (AnimationDrawable) birdImage
				.getBackground();
	   
		bird.start();
        		
		Animation birdFlyToAnimation = AnimationUtils.loadAnimation(activity,
		R.anim.bird_fly_to_animation);
//		birdFlyToAnimation.setFillEnabled(true);
//		birdFlyToAnimation.setFillAfter(true);
		//birdFlyToAnimation.setRepeatCount(INFNITE);
		
		birdImage.startAnimation(birdFlyToAnimation);
		
		float b =activity.getWindowManager().getDefaultDisplay().getWidth();
        float a =activity.getWindowManager().getDefaultDisplay().getHeight();
		System.out.println("---------屏幕宽度--------"+a+"----"+b);
		
		
		int[] stars = new int[]{R.id.star,R.id.star1,R.id.star2,R.id.star3,R.id.star4,R.id.star5};
		
		for (int i : stars) {
			
			// 星星
			final ImageView starImage = (ImageView) activity
					.findViewById(i);
			starImage.setVisibility(View.VISIBLE);
			starImage.setBackgroundResource(R.drawable.star_fly_animation);
			final AnimationDrawable star = (AnimationDrawable) starImage
					.getBackground();
			
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					star.start();
				}
		    }, (long) Math.ceil(Math.random()*1000));
			
		}
       
	}

	
	
	/**
	 * 存储标题动画视图的类
	 * 
	 * @ted
	 */
	private static class ViewHolder {
		ImageView flagView;
		ImageView left_cloude;
		ImageView right_cloude;
	}

	/************************************************
	 *************** 三国合伙人标题动画 **************
	 ************************************************/
	private static Runnable runnableRef;
	private static AnimationDrawable anim;

	/**
	 * 启动Splash动画
	 * 
	 * @param activity
	 * @param config
	 * @param onAnimFinishCallback
	 */
	// public static void start1(final Activity activity, final IConfig config,
	// final Runnable onAnimFinishCallback) {
	// final Handler handler = new Handler();
	// final LinearLayout egame_layout = (LinearLayout) activity
	// .findViewById(R.id.egame_layout);
	// final ImageView egame_logo = (ImageView) activity
	// .findViewById(R.id.egame_logo);
	// final Animation scaleAnimation = AnimationUtils.loadAnimation(activity,
	// R.anim.anim_egame_scale);
	// egame_logo.startAnimation(scaleAnimation);
	// handler.postDelayed(new Runnable() {
	// @Override
	// public void run() {
	// scaleAnimation.cancel();
	// // 淡出
	// final Animation fadeOutAnimation = AnimationUtils
	// .loadAnimation(activity, R.anim.anim_egame_fadeout);
	//
	// egame_logo.startAnimation(fadeOutAnimation);
	// // egame_logo.startAnimation(fadeOutAnimation);
	//
	// handler.postDelayed(new Runnable() {
	//
	// @Override
	// public void run() {
	// fadeOutAnimation.cancel();
	// egame_layout.setVisibility(View.GONE);
	//
	// final LinearLayout studio_layout = (LinearLayout) activity
	// .findViewById(R.id.studio_layout);
	// final ImageView loadingImageView = (ImageView) activity
	// .findViewById(R.id.loading_studio_anim);
	// anim = (AnimationDrawable) loadingImageView
	// .getBackground();
	// anim.start();
	//
	// runnableRef = new Runnable() {
	// public void run() {
	// anim.stop();
	// handler.removeCallbacks(runnableRef); // 停止Timer
	// studio_layout.setVisibility(View.GONE);
	// if (config.isHaveFlashScreen()) {
	// // 存在闪屏页面
	// final LinearLayout flashScreenLayout = (LinearLayout) activity
	// .findViewById(R.id.flash_screen_layout);
	// flashScreenLayout
	// .setVisibility(View.VISIBLE);
	// activity.findViewById(
	// R.id.flash_screen_image)
	// .setBackgroundResource(
	// config.getFlashScreenId());
	// handler.postDelayed(new Runnable() {
	// @Override
	// public void run() {
	// flashScreenLayout
	// .setVisibility(View.GONE);
	// onAnimFinishCallback.run();
	// }
	// }, 1500);
	// } else {
	// // 没有闪屏页
	// onAnimFinishCallback.run();
	// }
	// }
	// };
	// handler.postDelayed(runnableRef, 2000); // 开始Timer
	// }
	// }, 960);
	// }
	// }, 1200); // 开始Timer
	// }

	/**
	 * 启动Splash动画
	 * 
	 * @param activity
	 * @param config
	 * @param onAnimFinishCallback
	 */
	public static void start(final Activity activity, final IConfig config,
			final Runnable onAnimFinishCallback) {
		final Handler handler = new Handler();

		final LinearLayout egame_layout = (LinearLayout) activity
				.findViewById(R.id.egame_layout);
		final ImageView egame_logo = (ImageView) activity
				.findViewById(R.id.egame_logo);
		egame_logo.setBackgroundResource(R.drawable.logo_egames01);
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 淡出
				final Animation fadeOutAnimation = AnimationUtils
						.loadAnimation(activity, R.anim.anim_egame_fadeout);
				egame_logo.startAnimation(fadeOutAnimation);

				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						egame_layout.setVisibility(View.GONE);
						
						//final LinearLayout studio_layout = (LinearLayout) activity
						//		.findViewById(R.id.studio_layout);
						//studio_layout.setVisibility(View.GONE);

						
						if (config.isHaveFlashScreen()) {
							// 存在闪屏页面
							final LinearLayout flashScreenLayout = (LinearLayout) activity
									.findViewById(R.id.flash_screen_layout);
							flashScreenLayout.setVisibility(View.VISIBLE);
							activity.findViewById(R.id.flash_screen_image)
									.setBackgroundResource(
											config.getFlashScreenId());
							handler.postDelayed(new Runnable() {
								@Override
								public void run() {
									flashScreenLayout.setVisibility(View.GONE);
									onAnimFinishCallback.run();
								}
							}, 1500);
						} else {
							// 没有闪屏页
							onAnimFinishCallback.run();
						}
						handler.removeCallbacks(this);
						Log.d("关闭淡出图片run", "关闭淡出图片run");
					}
				}, 960);
				handler.removeCallbacks(this);
				Log.d("关闭显示图片run", "关闭显示图片run");
			}
		}, 2500);

	}

}
