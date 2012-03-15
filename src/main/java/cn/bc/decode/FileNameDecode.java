/**
 * 
 */
package cn.bc.decode;

import java.io.File;
import java.io.FilenameFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * �������URL�����ַ������ļ���
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
		String path = "D:\\Media\\Music\\mp3\\��ʥ����";
		File dir = new File(path);
		if (!dir.exists()) {
			System.out.println("Ŀ¼�����ڣ�path=" + path);
			return;
		}

		if (!dir.isDirectory()) {
			System.out.println("����Ŀ¼��path=" + path);
			return;
		}

		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".mp3");
			}
		});

		if (files == null || files.length == 0) {
			System.out.println("û���ҵ�Ҫ������ļ���path=" + path);
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
