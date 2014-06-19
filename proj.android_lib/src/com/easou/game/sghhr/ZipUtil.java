package com.easou.game.sghhr;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.easou.game.sghhr.ResUpdateUtil.OnResInstallProgress;

public class ZipUtil {

	public static void unzip(String zipFile, String targetDir,
			OnResInstallProgress onResInstallProgress) throws IOException {
		int BUFFER = 4096; // 这里缓冲区我们使用4KB，
		String strEntry; // 保存每个zip的条目名称

		BufferedOutputStream dest = null; // 缓冲输出流
		FileInputStream fis = new FileInputStream(zipFile);
		ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
		ZipEntry entry; // 每个zip条目的实例
		
		long totalZipFileSize = new File(zipFile).length();
		long currentSize = 0;
		
		while ((entry = zis.getNextEntry()) != null) {
			byte data[] = new byte[BUFFER];
			strEntry = entry.getName();

			File entryFile = new File(targetDir, strEntry);
			//Log.i("Unzip: ", entryFile.getAbsolutePath());
			// 创建文件夹
			if (entry.isDirectory()) {
				if (!entryFile.exists()) {
					String progressMsg = "正在创建目录" + entryFile.getName();
					onResInstallProgress.onProgressMessage(progressMsg);
					entryFile.mkdirs();
				}
				continue;
			}
			File entryDir = new File(entryFile.getParent());
			String progressMsg = "正在解压" + entryFile.getName();
			onResInstallProgress.onProgressMessage(progressMsg);
			if (!entryDir.exists()) {
				entryDir.mkdirs();
			}

			if (!entryFile.exists()) {
				entryFile.createNewFile();
			} else {
				entryFile.delete();
				entryFile.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(entryFile);
			dest = new BufferedOutputStream(fos, BUFFER);
			int count;
			while ((count = zis.read(data, 0, BUFFER)) != -1) {
				currentSize +=count;
				dest.write(data, 0, count);
			}
			dest.flush();
			dest.close();
			onResInstallProgress.onProgress((int) (currentSize*100/totalZipFileSize));
		}
		zis.close();
	}
}
