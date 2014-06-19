package com.easou.game.sghhr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.cocos2dx.lib.Cocos2dxHelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;

public class ImageUtils {
	public static Bitmap zoomImg(String img, int newWidth, int newHeight) {
		// 图片源
		Bitmap bm = BitmapFactory.decodeFile(img);

		if (null != bm) {
			return zoomImg(bm, newWidth, newHeight);
		}
		return null;
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		Bitmap bm = zoomImg(
				"D:\\game\\client\\phone\\android\\ldsg\\game_tag\\LDPI_android_2014_02_11\\LdsgLua\\Lua\\Ldsg\\Resources\\1.home\\home_events_anim.png",
				166, 166);
		saveMyBitmap("d://cc.png", bm);

		System.out.println(System.currentTimeMillis() - t);
	}

	public static Bitmap zoomImg(Context context, String img, int newWidth,
			int newHeight) {
		// 图片源
		try {
			Bitmap bm = BitmapFactory.decodeStream(context.getAssets()
					.open(img));
			if (null != bm) {
				return zoomImg(bm, newWidth, newHeight);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 缩放图片
	public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
		// 获得图片的宽高
		int width = bm.getWidth();
		int height = bm.getHeight();
		// 计算缩放比例
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// 得到新的图片
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
				true);
		return newbm;
	}

	public static Map<String,Integer> scaleFileMap = new HashMap<String,Integer>();
    public static final String SCRIPT="/script/";
    public static String SCALE_FILE_PATH="";
   
    
    public static void readScaleFileMap(Context context){
    	
    	SCALE_FILE_PATH = context.getFilesDir().getAbsolutePath()+SCRIPT+"scalefile";
    	File f = new File(SCALE_FILE_PATH);
    	try {
    		if (!f.exists()){
        		f.getParentFile().mkdirs();
        		return;
        	}
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line = reader.readLine();
			while (line != null) {
				scaleFileMap.put(line, 1);
				line = reader.readLine();
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    public static void writeScaleFile(String line){
    	try {
			FileOutputStream out = new FileOutputStream(new File(SCALE_FILE_PATH), true);  
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			bw.write(line+System.getProperty("line.separator"));
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    
	public static void scaleFile(String fileName) {
		if(scaleFileMap.containsKey(fileName)){
			return;
		}
		scaleFileMap.put(fileName, 1);//防止重复缩放
		writeScaleFile(fileName);//写入
		
		File f = new File(Cocos2dxHelper.getCocos2dxWritablePath()+SCRIPT+ fileName);
		try {
			Bitmap bm = BitmapFactory.decodeStream(new FileInputStream(f));

			if (bm != null) {
				int width = bm.getWidth();
				int height = bm.getHeight();
				// 计算缩放比例
				float scaleWidth = 2f;
				float scaleHeight = 2f;
				// 取得想要缩放的matrix参数
				Matrix matrix = new Matrix();
				matrix.postScale(scaleWidth, scaleHeight);
				// 得到新的图片
				Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
						true);
				FileOutputStream fos = null;
				fos = new FileOutputStream(f);
                CompressFormat format = Bitmap.CompressFormat.PNG;
				if(fileName.endsWith(".jpg")){
					format = Bitmap.CompressFormat.JPEG;
				}
				
				newbm.compress(format, 50, fos);
				fos.flush();
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void zoomImg_2(InputStream is, OutputStream os) {
		Bitmap bm = BitmapFactory.decodeStream(is);
		if (bm != null) {
			int width = bm.getWidth();
			int height = bm.getHeight();
			// 计算缩放比例
			float scaleWidth = 2f;
			float scaleHeight = 2f;
			// 取得想要缩放的matrix参数
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			// 得到新的图片
			Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
					true);
			newbm.compress(Bitmap.CompressFormat.PNG, 50, os);
			// try {
			// // if (++index==1)
			// // newbm.compress(Bitmap.CompressFormat.PNG, 80, new
			// FileOutputStream( new File("/sdcard/test.png")));
			// } catch (FileNotFoundException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
	}

	public static void saveMyBitmap(String bitName, Bitmap mBitmap) {
		File f = new File(bitName);
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			fOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
