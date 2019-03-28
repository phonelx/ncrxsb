package monitor.common.util;

/*
 * java.util.zip.ZipInputStream的编码字符集不同
 java.util.zip.ZipInputStream的字符集固定是UTF-8 
 注销的部分是解压缩的代码。
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class ZipUtil {
	private static Logger logger = Logger.getLogger(ZipUtil.class);
	
	public boolean compressFile(String srcFilePath,String zipFilePath){
		ZipOutputStream out;
		try {
			out = new ZipOutputStream(new FileOutputStream(zipFilePath));
			zip(srcFilePath,zipFilePath,out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	//压缩指定目录下的所有xml文件，不包括子目录,被compress()调用
	public void zip(String srcContentPath,String zipFilePath,ZipOutputStream out){
		File srcContentFile = new File(srcContentPath);
		
		File[] subFilePaths = srcContentFile.listFiles();
		for(int i=0;i<subFilePaths.length;i++){
			String extName=subFilePaths[i].getName().substring( subFilePaths[i].getName().lastIndexOf(".")+1,subFilePaths[i].getName().length() );
			if("XML".equalsIgnoreCase(extName)){
				try {
					out.putNextEntry(new ZipEntry(subFilePaths[i].getName()));
					FileInputStream in = new FileInputStream(new File(subFilePaths[i].getAbsolutePath()));
					int b;
					while ((b = in.read()) != -1) {
						out.write(b);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
			
	}
	public void unCompressFile(String zipFileName, String outputDirectory)
			throws Exception {
		try {
			ZipFile zipFile = new ZipFile(zipFileName);
			java.util.Enumeration e = zipFile.entries();
			ZipEntry zipEntry = null;
			createDirectory(outputDirectory, "");
			while (e.hasMoreElements()) {
				zipEntry = (ZipEntry) e.nextElement();
				System.out.println("正在解压: " + zipEntry.getName());
				String name = null;
				if (zipEntry.isDirectory()) {
					name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					File f = new File(outputDirectory + File.separator + name);
					f.mkdirs();
					System.out.println("创建目录：" + outputDirectory
							+ File.separator + name);
				} else {
					String fileName = zipEntry.getName();
					fileName = fileName.replace('\\', '/');
					// System.out.println("测试文件1：" +fileName);
					if (fileName.indexOf("/") != -1) {
						createDirectory(outputDirectory, fileName.substring(0,
								fileName.lastIndexOf("/")));
						fileName = fileName.substring(
								fileName.lastIndexOf("/") + 1, fileName
										.length());
					}
					File f = new File(outputDirectory + File.separator
							+ zipEntry.getName());
					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(f);
		
					byte[] by = new byte[1024];
					int c;
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
				}
			}
		
			// 删除文件不能在这里删，因为文件正在使用，应在上传那处删
			// 解压后，删除压缩文件
			// File zipFileToDel = new File(zipFileName);
			// zipFileToDel.delete();
			// System.out.println("正在删除文件："+ zipFileToDel.getCanonicalPath());
		
			// //删除解压后的那一层目录
			// delALayerDir(zipFileName, outputDirectory);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/*
	//压缩指定目录下的所有xml文件，包括子目录,被compress()调用
	public void zip(String srcFilePath,String zipFilePath,ZipOutputStream  out){
		File srcFile = new File(srcFilePath);
		if(srcFile.isDirectory()){
			File[] subFilePaths = srcFile.listFiles();
			for(int i=0;i<subFilePaths.length;i++){
				zip(subFilePaths[i].getAbsolutePath(),zipFilePath,out);
			}
		}else{
			String extName=srcFile.getName().substring( srcFile.getName().lastIndexOf(".")+1, srcFile.getName().length() );
			if("XML".equalsIgnoreCase(extName)){
				try {
					out.putNextEntry(new org.apache.tools.zip.ZipEntry(srcFile.getName()));
					FileInputStream in = new FileInputStream(srcFile);
					int b;
					while ((b = in.read()) != -1) {
						out.write(b);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} 
	}
	*/
	
	private void createDirectory(String directory, String subDirectory) {
		String dir[];
		File fl = new File(directory);
		try {
			if (subDirectory == "" && fl.exists() != true)
				fl.mkdirs();
			else if (subDirectory != "") {
				dir = subDirectory.replace('\\', '/').split("/");
				for (int i = 0; i < dir.length; i++) {
					File subFile = new File(directory + File.separator + dir[i]);
					if (subFile.exists() == false)
						subFile.mkdirs();
					directory += File.separator + dir[i];
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	


}
