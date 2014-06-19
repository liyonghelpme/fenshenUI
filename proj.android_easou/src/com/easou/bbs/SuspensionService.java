package com.easou.bbs;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.easou.game.sghhr.easou.R;
import com.easou.game.sghhr.log.LogActEnum;
import com.easou.game.sghhr.log.LogUtil;



//浮动窗口
public class SuspensionService extends Service {
	WindowManager wm = null;
	WindowManager.LayoutParams wmParams = null;
	View view;
	private float mTouchStartX;
	private float mTouchStartY;
	private float x;
	private float y;
	int state;
	private Button button_show  ;  
	
	//右边
	private Button button_goin_right ;  
	private FrameLayout fl_show_goin_right; 
	private LinearLayout temp_ll_right; 
	//左边
	private Button button_goin_left ;  
	private FrameLayout fl_show_goin_left; 
	private LinearLayout temp_ll_left; 
	
	private float StartX;
	private float StartY;
	private boolean canToch  = true;

    private int screen_width; 
 

	@Override
	public void onCreate() {
		super.onCreate();
//		view = LayoutInflater.from(this).inflate(R.layout.suspension, null);
//        button_show  = (Button) view.findViewById(R.id.show);     
//
//          
//        temp_ll_left = (LinearLayout) view.findViewById(R.id.temp_ll_left);
//        button_goin_left = (Button) view.findViewById(R.id.goin_left);
//        fl_show_goin_left= (FrameLayout) view.findViewById(R.id.show_goin_left);
//
//        temp_ll_right = (LinearLayout) view.findViewById(R.id.temp_ll_right);
//        button_goin_right = (Button) view.findViewById(R.id.goin_right);
//        fl_show_goin_right = (FrameLayout) view.findViewById(R.id.show_goin_right);
//
//        OnClickListener oc  =   new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				try {
//					LogUtil.sendLogInfo(LogActEnum.INTOBBS.getAct(),SuspensionService.this);
//				    if (fl_show_goin_right.isShown()){
//						setGone(false ,true);
//					} else if(fl_show_goin_left.isShown()){
//				    	setGone(true ,true);
//				    }
//					    Intent mIntent = new Intent(SuspensionService.this , EASOUBBSActivity.class);  
//				        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
//				        startActivity(mIntent);      
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//					view .setVisibility(View.VISIBLE);
//				}  
//			}
//		};
//        button_goin_right.setOnClickListener(oc);
//        button_goin_left.setOnClickListener(oc);
//		createView();
	
	}
	private final int FLUSH_GUIWEI  = 10093;
	private final int FlUSH_GONE = 10094;
	private Handler hanlder =new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case FLUSH_GUIWEI:
				EASOUBBSActivity.Location_x =wmParams.x ;
				EASOUBBSActivity.Location_y =wmParams.y;
				wm.updateViewLayout(view, wmParams);
				break;
			case FlUSH_GONE:
					fl_show_goin_left.setVisibility(View.GONE);
					temp_ll_left.setVisibility(View.GONE);

					fl_show_goin_right.setVisibility(View.GONE);
					temp_ll_right.setVisibility(View.GONE);
			

				break;
			}
			
		};
	};
	
	

	private void createView() {
		wm = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
		wmParams =  new WindowManager.LayoutParams();
		wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;  //2002
//		wmParams.flags |= 8;  //8   FLAG_NOT_FOCUSABLE
		wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                 | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		
		wmParams.gravity = Gravity.LEFT | Gravity.TOP; 

		screen_width = wm.getDefaultDisplay().getWidth();//屏幕宽度
//		screen_height = wm.getDefaultDisplay().getHeight();//屏幕高度

		wmParams.x = EASOUBBSActivity.Location_x ;
		wmParams.y = EASOUBBSActivity.Location_y;

		wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		wmParams.format = 1;


		button_show.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if(!canToch){
					return true;
				}
				x = event.getRawX();
				y = event.getRawY(); 
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					state = MotionEvent.ACTION_DOWN;
					StartX = x;
					StartY = y;
					mTouchStartX = event.getX();
					mTouchStartY = event.getY();
					Log.i("startP", "startX" + mTouchStartX + "====startY"
							+ mTouchStartY);
					LogUtil.sendLogInfo(LogActEnum.EAGAME.getAct(),SuspensionService.this);
					break;
				case MotionEvent.ACTION_MOVE:
					state = MotionEvent.ACTION_MOVE;
					updateViewPosition();
					break;
				case MotionEvent.ACTION_UP:
					state = MotionEvent.ACTION_UP;
					updateViewPosition();
					showImg();
					mTouchStartX = mTouchStartY = 0;
					break;
				}
				return true;
			}
		});

		wm.addView(view, wmParams);
		gackPlayce();
	}

	public void showImg() {
		if (Math.abs(x - StartX) < 3 && Math.abs(y - StartY) < 3) {
		    //1.先判断是不是应该 关闭
		    if (fl_show_goin_right.isShown()){
				setGone(false ,false);
				return ;
			}
		    if(fl_show_goin_left.isShown()){
		    	setGone(true ,false);
		    	return ;
		    }
			//2.再判断是不是应该 打开。
			if(wmParams.x >=(screen_width - button_show.getWidth() ) / 2  ){
				setVisble(true);
		    	return ;
			}else{
				setVisble(false);
		    	return; 
			}
		} else  {
			if (fl_show_goin_right.isShown() || fl_show_goin_left.isShown() ){
				return ;
			}
			gackPlayce();
		}
		
	}

    /**
     * 	
     * @param lr true 表示左边 ，false 表示右边
     */
    private void setVisble( final boolean lr){   
    	canToch = false;
		if(lr){ //左边 
			fl_show_goin_left.setVisibility(View.VISIBLE);
			temp_ll_left.setVisibility(View.VISIBLE);  
		}else{  //右边
		 	fl_show_goin_right.setVisibility(View.VISIBLE);
			temp_ll_right.setVisibility(View.VISIBLE);  
		}
		
		TranslateAnimation  tran  = null;
    	
		if(lr){
		    tran = new TranslateAnimation(
	    			Animation.RELATIVE_TO_SELF,1.0f,
	    			Animation.RELATIVE_TO_SELF, .0f, 
	    			Animation.RELATIVE_TO_SELF,0,
	    			Animation.RELATIVE_TO_SELF, 0);
		}else{ //右边
		     tran = new TranslateAnimation(
		    			Animation.RELATIVE_TO_SELF,-1.0f,
		    			Animation.RELATIVE_TO_SELF, 0.0f, 
		    			Animation.RELATIVE_TO_SELF,0,
		    			Animation.RELATIVE_TO_SELF, 0);
		} 	
    	tran.setDuration(100);
    	tran.setRepeatCount(0);
       	tran.setFillAfter(true); 
       	AnimationListener as = new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				canToch = true;
				
			}
       		
       	};
       	tran.setAnimationListener(as); 
       	if(lr){
       		fl_show_goin_left.startAnimation(tran);
       	}else{
       	  	fl_show_goin_right.startAnimation(tran);
       	}
  
    	button_show.setBackgroundResource(R.drawable.bbs_show_down);
    }
    
    /**
     * 
     * @param lr  true 表示 左边  ，  false 表示 右边。
     * @param notGuiwei true 表示不用归位   false 表示归位  。 因为 跳到BBS的时候会死机。
     */
    private void setGone( final boolean  lr , final boolean notGuiwei ){
    	canToch = false;
    	TranslateAnimation  tran = null;
    	if(lr){
    		tran = new TranslateAnimation(
	    			Animation.RELATIVE_TO_SELF,0.0f,
	    			Animation.RELATIVE_TO_SELF,1.0f, 
	    			Animation.RELATIVE_TO_SELF,0,
	    			Animation.RELATIVE_TO_SELF, 0);
    	}else{
    		tran = new TranslateAnimation(
    	    			Animation.RELATIVE_TO_SELF,0.0f,
    	    			Animation.RELATIVE_TO_SELF, -1.0f, 
    	    			Animation.RELATIVE_TO_SELF,0,
    	    			Animation.RELATIVE_TO_SELF, 0);
    	}
    	
    	tran.setDuration(100);
    	tran.setRepeatCount(0);
       	tran.setFillAfter(true); 
    	AnimationListener as = new AnimationListener() {
			
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
                if(!notGuiwei){
                	gackPlayce();
                }
				
			}
		};
		tran.setAnimationListener(as);
		if(lr){
			fl_show_goin_left.startAnimation(tran);
		}else{
			fl_show_goin_right.startAnimation(tran);
		}

		button_show.setBackgroundResource(R.drawable.bbs_show_up);
    }


	private void updateViewPosition( ) {
	   if( 	Math.abs(x - StartX) < 1.5 && Math.abs(y - StartY) < 1.5 ){
		   return ;
	   }		
		wmParams.x = (int) (x - mTouchStartX);
		wmParams.y = (int) (y - mTouchStartY);
	   if( fl_show_goin_left.isShown() ){ // 左边的可见
		   wmParams.x   -=  fl_show_goin_left.getWidth();
	   }
	   EASOUBBSActivity.Location_x =wmParams.x ;
	   EASOUBBSActivity.Location_y =wmParams.y;
	   wm.updateViewLayout(view, wmParams);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
//		wm.removeView(view);
//		wmParams =null;
//		wm = null;
//		view = null;
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}	


    private boolean  back_lr ;

	/**
	 * 归位
	 */
	private void gackPlayce(){
		System.gc();
		if( fl_show_goin_left.isShown()  ){
			if(  wmParams.x  +  view.getWidth() - button_show.getWidth() /2  <=  screen_width/2 ){ 
				back_lr = true;  
			}else{
				back_lr = false;
			}
		}else if(fl_show_goin_right.isShown()){
			if(  wmParams.x  <= ( screen_width  -  button_show.getWidth() ) /2 ){ 
				back_lr = true;  
			}else{
				back_lr = false;
			}
		}else{
		if(  wmParams.x  <= ( screen_width  - button_show.getWidth()) /2 ){ //归位左边
			back_lr = true;  
		}else{
			back_lr = false;
		}
		
		}
		canToch = false;
		Thread guiwei  = new Thread(){
			   public void run() {
				   try {
				   int bujin =screen_width / 20 ; //步进 10 ；
				   if(back_lr){  //左边归位
					   while ( wmParams.x > 00 ) {
					   Thread.sleep(10);
					   wmParams.x -= bujin ; 
					   if(wmParams.x  <=0){
						   wmParams.x  = 0 ;
					   }
						   hanlder.sendEmptyMessage(FLUSH_GUIWEI);
					   }
						
				   }else{ //右边归位
					   while(  wmParams.x < (screen_width -button_show.getWidth() ) ){
							Thread.sleep(10);
							 wmParams.x += bujin;
							   if(wmParams.x   >=    (screen_width -button_show.getWidth())   ){
								  wmParams.x  =  screen_width -button_show.getWidth() ;
							   }
							   hanlder.sendEmptyMessage(FLUSH_GUIWEI);			
					   }		   
				   }
				   hanlder.sendEmptyMessage( FlUSH_GONE);
				   canToch = true;  
				   
				   } catch (Exception e) {
						// TODO: handle exception
					   canToch = true;  
				  }
					   
				   
				   
			   };
		   };
		   guiwei.start();
		   
	}
	
	
}
