package com.easou.game.sghhr;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.util.Log;

/**
 * 计算文件夹（不含子文件夹）的 MD5 签名，使用方法：
 * 
 * SignatureUtils.computeSign("/Path/To/Dir/");
 */

public class SignatureUtils {
	
	/**
	 * 将文件夹中的文件按照字母先后顺序排序	
	 * @param fliePath
	 * @return
	 */
	public static List<File> orderByName(String fliePath) {
		List<File> files = Arrays.asList(new File(fliePath).listFiles());
		Collections.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				if (o1.isDirectory() && o2.isFile())
					return -1;
				if (o1.isFile() && o2.isDirectory())
					return 1;
				return o1.getName().compareTo(o2.getName());
			}
		});

		return files;
	}

	/**
	 * 计算文件夹（不含子文件夹）的 MD5 签名
	 * @param dir 要计算签名的文件路径
	 */
	public static String computeSign(String dirPath) {
		List<File> fileList = orderByName(dirPath);
		StringBuffer sb = new StringBuffer();
		for (File file : fileList) {
			String s = Md5.encode(file);
			Log.i(SignatureUtils.class.getName(),file.getName()+":"+s+"   size="+file.length());
			sb.append(s);
		}
		String sign =  Md5.encode(sb.toString());
		Log.i(SignatureUtils.class.getName(),sign);
		return sign;
	}
}
