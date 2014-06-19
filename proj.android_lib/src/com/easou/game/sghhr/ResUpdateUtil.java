package com.easou.game.sghhr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.util.EncodingUtils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class ResUpdateUtil {
	private final static String TAG = "ResUpdateUtil";
	private File lauScriptRootDir = null;
	private static File tagFile;
	public static interface OnResInstallProgress{
		void onProgressMessage(String msg);
		void onProgress(int n);
		void onFinish(Object o);
		void onError(int code,String msg);
	}

	public ResUpdateUtil(Context context) {
		lauScriptRootDir = new File(context.getFilesDir(), "script");
	}

    //从assets 文件夹中获取文件并读取数据  
    public static String getStringFromAssets(Context context,String fileName){  
        String result = "";  
            try {  
                InputStream in = context.getResources().getAssets().open(fileName);  
                //获取文件的字节数  
                int lenght = in.available();  
                //创建byte数组  
                byte[]  buffer = new byte[lenght];  
                //将文件中的数据读到byte数组中  
                in.read(buffer);  
                result = EncodingUtils.getString(buffer, "UTF-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            return result;  
    }  
    
	/** 获取游戏apk的版本号 */
	public static String getGameApkVersion(Context _context) {
		return Utils.getGameApkVersionCode(_context)+".0";
	}
	
	/** 获取游戏资源的版本号 */
	public static String getGameResVersion(Context _context) {
		//优先从解压后的资源文件中读取版本号
		File versionFile = new File(new File(_context.getFilesDir(), "script"), "version");
		if (!versionFile.exists()) {
			//从assert中读取版本号
			return getStringFromAssets(_context,"version");
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(versionFile));
			String tmpStr = reader.readLine();
			if (tmpStr != null && tmpStr.length() > 0) {
				return tmpStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return "0.0";
	}
	
	static public boolean isFirstStart(Activity activity){
		return !new File(new File(activity.getFilesDir(), "script"), "tagFile").exists();
	}

	/**
	 * 释放 assert到script目录
	 * 
	 * @param activity
	 */
	public void checkAndCopyAssertToScrpitDir(Activity activity,OnResInstallProgress onResInstallProgress) {
		//是否是覆盖安装并升级了apk
		if(SpHelper.isUpdated())
		{
			deleteAllFile(lauScriptRootDir);
			Log.d(TAG,"是覆盖安装并升级了apk");
		}
		
		
		if (!lauScriptRootDir.exists()) {
			lauScriptRootDir.mkdirs();
		}
		tagFile = new File(lauScriptRootDir, "tagFile");
		if (!tagFile.exists()) {
			Log.d(TAG, "拷贝zip资源");
			try {
				deleteAllFile(lauScriptRootDir);
				// 释放压缩文件
				File outFile = copyResoucesZipFile(activity, lauScriptRootDir,onResInstallProgress);
				onResInstallProgress.onFinish(outFile);
			} catch (IOException e) {
				e.printStackTrace();
				onResInstallProgress.onError(2, "拷贝zip资源出错");
			}
		} else {
			onResInstallProgress.onFinish(null);
			Log.d(TAG, "tagFile存在，跳过资源释放过程");
		}
	}

	private File copyResoucesZipFile(Activity activity, File targetDir, OnResInstallProgress onResInstallProgress)
			throws IOException {
		File outFile = new File(targetDir, "resources.zip");


		if (outFile.exists()) {
			outFile.delete();
			outFile.createNewFile();
		}
		InputStream in = null;
		in = activity.getAssets().open("resources.zip");

		FileOutputStream out = new FileOutputStream(outFile);

		byte[] buf = new byte[1024];
		int len;
		int currentLen = 0;
		int totalLen = 30*1024*1024;//假定资源包大小为30M左右
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
			currentLen+=len;
			onResInstallProgress.onProgress((int)((double)currentLen/(double)totalLen*100));
		}

		in.close();
		out.close();
		return outFile;
	}

	private void deleteAllFile(File rootDir) {
		File[] files = rootDir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				file.delete();
			} else {
				deleteAllFile(file);
			}
		}

	}

	// 释放安装包中的文件
	public static void installUpdate(Context context, File file,OnResInstallProgress onResInstallProgress) {
		try{
			// 解压
			ZipUtil.unzip(file.getAbsolutePath(), new File(context.getFilesDir(),
					"script").getAbsolutePath(),onResInstallProgress);
	
			// 删除更新包
			file.delete();
			tagFile.createNewFile();
			onResInstallProgress.onFinish(null);
		}catch(Exception e){
			onResInstallProgress.onError(1, "解压失败："+e.getMessage());
			e.printStackTrace();
		}
	}

}
