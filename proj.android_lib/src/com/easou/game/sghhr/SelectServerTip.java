package com.easou.game.sghhr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 选服弹窗
 * */
public class SelectServerTip {

	/** 选服操作结束 */
	static public interface OnServerSelected {
		public void onSelected(ServerInfo _serverInfo);
	}

	private Dialog mDialog;
	/** 选服 GridView --gamecenter_gridlist */
	private GridView gridView;
	
	private GridView gamecenter_gridlist_play;
	
	private ArrayList<HashMap<String, Object>> gameCenterList;
	private Context context;
	private LinearLayout gamecenter_lay;
	private TextView gamecenter_1;
	private ImageView gamecenter_1tip;
	private OnServerSelected onServerSelected;
	private List<ServerInfo> serverList;
	private ImageView mask;

	public SelectServerTip(Context context, OnServerSelected onServerSelected,
			List<ServerInfo> _serverList) {
		
       
		Activity instance = (Activity) context;
		mask = (ImageView) instance.findViewById(R.id.login_main_mask);
		
		//new AlertDialog
		
		
		
		mDialog = new Dialog(context, R.style.dialog);
		
		
		
		this.context = context;
		this.onServerSelected = onServerSelected;
		Window window = mDialog.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		window.setGravity(Gravity.CENTER);
		window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
//  
		mDialog.setContentView(R.layout.select_server_lay);
		mDialog.setFeatureDrawableAlpha(Window.FEATURE_OPTIONS_PANEL, 0);
		mDialog.setCanceledOnTouchOutside(true);
		mDialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
			     mask.setVisibility(View.INVISIBLE);
			}
		});
		


		serverList = _serverList;
		// 生成动态数组，并且转入数据
		gameCenterList = new ArrayList<HashMap<String, Object>>();

		if ((serverList != null) && (serverList.size() > 0)) {
			for (int i = 0; i < serverList.size(); i++) {
				if (serverList.get(i).getServer_status() == 1) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("server_name", serverList.get(i).getServer_name());
					map.put("server_id", serverList.get(i).getServer_id());
					map.put("server_status", serverList.get(i)
							.getServer_status());
					map.put("server_port", serverList.get(i).getServer_port());
					gameCenterList.add(map);
				}
			}
		} else
			return;

		gamecenter_lay = (LinearLayout) mDialog
				.findViewById(R.id.gamecenter_lay);
//		gamecenter_1 = (TextView) mDialog.findViewById(R.id.gamecenter_1);
//		gamecenter_1tip = (ImageView) mDialog.findViewById(R.id.tip_id);
		
		if(MobileFunction.playList.size()>0){
			gamecenter_gridlist_play = (GridView) mDialog.findViewById(R.id.gamecenter_gridlist_play);
			
			GamesListAdapter saImageItems_play = new GamesListAdapter(this.context,
					MobileFunction.playList, gamecenter_gridlist_play);
			gamecenter_gridlist_play.setAdapter(saImageItems_play);
			gamecenter_gridlist_play.setOnItemClickListener(new ItemClickListener());
		}
		
//		AssetManager mgr=getAssets();//得到AssetManager
//		Typeface tf=Typeface.createFromAsset(mgr, "fonts/ttf.ttf");//根据路径得到Typeface
//		tv.setTypeface(tf);//设置字体  
//		refreshPlayList();

		// 添加并且显示
		gridView = (GridView) mDialog.findViewById(R.id.gamecenter_gridlist);
		
		
		// 生成适配器
		GamesListAdapter saImageItems = new GamesListAdapter(this.context,
				serverList, gridView);
		gridView.setAdapter(saImageItems);
		//设置选服列表高度，以达到自适应屏幕
		autoGridViewSize();
		gridView.setOnItemClickListener(new ItemClickListener());
	}

	/***
	 * 让选服列表自适应屏幕高度
	 */
	private void autoGridViewSize() {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		final int height = display.getHeight();//获取屏幕高度
		//final int width = display.getWidth();//获取屏幕高度
		//gridView.getLayoutParams().width = width / 2;
		ViewTreeObserver observer = gridView.getViewTreeObserver();
		observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				gridView.getViewTreeObserver().removeGlobalOnLayoutListener(
						this);
				int mHeight = gridView.getHeight();
				if (mHeight > height / 2) {//如果查出屏幕一半，则高度设为一半
					gridView.getLayoutParams().height = height / 2;
				}
			}
		});
	}

	
	public void show() {
		mask.setVisibility(View.VISIBLE);
		mDialog.show();
	}

	// 当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
	class ItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			ServerInfo item = serverList.get(arg2);
			;
			// 显示所选Item的ItemText
			if (item.getServer_status() != -1) {
				int status = item.getServer_status();
				if (status == 0) {
					Toast.makeText(context, "服务器暂未开启", Toast.LENGTH_LONG)
							.show();
				} else if (status == 1 || status == 2 || status == 3) {
					if (!MobileFunction.playList.contains(item)) {
						if(MobileFunction.playList.size()>3){
							MobileFunction.playList.remove(MobileFunction.playList.size()-1);
						}
						MobileFunction.playList.add(0, item);
					}

					refreshPlayList();

					onServerSelected.onSelected(item);
					mDialog.dismiss();
				} else {
					Toast.makeText(context, "服务器繁忙", Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(context, "服务器状态异常", Toast.LENGTH_LONG).show();
			}
		}
	}

	public void refreshPlayList() {
		if (MobileFunction.playList.size() > 0) {

			if (MobileFunction.playList.get(0) != null) {
				gamecenter_lay.setVisibility(View.VISIBLE);
//				gamecenter_1.setVisibility(View.VISIBLE);
//				gamecenter_1.setText((String) MobileFunction.playList.get(0)
//						.getServer_name());
//				int status = MobileFunction.playList.get(0).getServer_status();
//				if (status == 1) {
//					gamecenter_1tip
//							.setBackgroundResource(R.drawable.smooth_txt);
//				} else if (status == 2) {
//					gamecenter_1tip.setBackgroundResource(R.drawable.full_txt);
//				} else if (status == 3) {
//					gamecenter_1tip
//							.setBackgroundResource(R.drawable.latest_txt);
//				}
//				gamecenter_1.setOnClickListener(new View.OnClickListener() {
//					@Override
//					public void onClick(View arg0) {
//						ServerInfo item = MobileFunction.playList.get(0);
//						onServerSelected.onSelected(item);
//						mDialog.dismiss();
//					}
//				});
			}
			if (MobileFunction.playList.size() == 1) {
				return;
			}
		}
	}

	/**
	 * 界面适配类
	 * 
	 * @author Erica
	 * */
	class GamesListAdapter extends ArrayAdapter<ServerInfo> {
		private Context context_m = null;

		List<ServerInfo> apps_m;
		GridView mListView;

		public GamesListAdapter(Context context, List<ServerInfo> apps,
				GridView _mListView) {
			super(context, R.layout.gamecenter_item, apps);
			this.context_m = context;
			apps_m = apps;
			mListView = _mListView;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = newView(parent);
			}

			bindView(position, convertView);

			return (convertView);
		}

		private View newView(ViewGroup parent) {
			return (LayoutInflater.from(context_m).inflate(
					R.layout.gamecenter_item, parent, false));
		}

		public int getCount() {
			return apps_m.size();
		}

		/**
		 * 数据适配
		 * */
		private void bindView(int position, View row) {
			ViewHolder holder = (ViewHolder) row.getTag();
			if (holder == null) {
				holder = new ViewHolder();
				holder.name = (TextView) row.findViewById(R.id.server_name);
				holder.tip_img = (ImageView) row.findViewById(R.id.tip_id);
				row.setTag(holder);
			}

			holder.name.setText(apps_m.get(position % apps_m.size())
					.getServer_name());
			if (apps_m.get(position % apps_m.size()).getServer_status() == 1) {
				holder.tip_img.setBackgroundResource(R.drawable.smooth_txt);
			} else if (apps_m.get(position % apps_m.size()).getServer_status() == 2) {
				holder.tip_img.setBackgroundResource(R.drawable.full_txt);
			} else if (apps_m.get(position % apps_m.size()).getServer_status() == 3) {
				holder.tip_img.setBackgroundResource(R.drawable.latest_txt);
			}
		}

		private class ViewHolder {
			private ImageView tip_img;
			private TextView name;
		}
	}

}