package com.easou.game.sghhr;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class BackgroundMusicService extends Service{
	
	private static MediaPlayer mediaPlayer;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		if(intent != null && "BACKGROUND".equals(intent.getType())){
			//循环播放背景音乐
			playMusic();
			intent = null;
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(mediaPlayer != null){
			if(mediaPlayer.isPlaying()){
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = null;
			}
		}
	}
	
	/**
	 * 循环播放背景音乐
	 */
	private void playMusic(){
		if(mediaPlayer == null ){
			mediaPlayer = MediaPlayer.create(this,R.raw.start_music);
		}
		if(!mediaPlayer.isPlaying())
			mediaPlayer.start();
	}
	
	/**
	 * 停止音乐
	 */
	public static void stopMusic(){
		if(mediaPlayer != null){
			if(mediaPlayer.isPlaying()){
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = null;
			}
		}
	}
	
	/**
	 * 暂停音乐
	 */
	public static void pauseMusic(){
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}
	}
	

}
