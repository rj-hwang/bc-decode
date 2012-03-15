/**
 * 
 */
package cn.bc.decode;

import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 解码包含URL特殊字符串的文件名
 * 
 * @author dragon
 * 
 */
public class FileNameDecode {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String path = "D:\\Media\\Music\\mp3\\神圣天堂";
		File dir = new File(path);
		if (!dir.exists()) {
			System.out.println("目录不存在！path=" + path);
			return;
		}

		if (!dir.isDirectory()) {
			System.out.println("不是目录：path=" + path);
			return;
		}

		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".mp3");
			}
		});

		if (files == null || files.length == 0) {
			System.out.println("没有找到要处理的文件：path=" + path);
			return;
		}

		String fileName, newFileName;
		int index;
		for (File file : files) {
			fileName = file.getName();
			newFileName = null;
			try {
				newFileName = URLDecoder.decode(fileName, "UTF-8");
//				newFileName = newFileName.substring(newFileName.lastIndexOf("_") + 1);
				newFileName = file.getParentFile().getAbsolutePath()
						+ File.separator + newFileName;
				file.renameTo(new File(newFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
