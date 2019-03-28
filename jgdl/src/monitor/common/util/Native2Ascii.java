package monitor.common.util;
import java.io.File;



public class Native2Ascii {

	/**@description
	 * @param 
	 * @return void
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String path = Native2Ascii.class.getClassLoader().getResource("").getPath() ;
		path = path.substring(0, path.indexOf("WebRoot")+8) ;
		File file = new File(path+"resource/js");
		String outPutFolder = "d:/temp/" ;
		File of = new File(outPutFolder);
		if(!of.exists()){
			of.mkdirs() ;
		}
		for(File jsFile : file.listFiles()){
			if(!jsFile.isDirectory() && !jsFile.isHidden()){
				 Runtime.getRuntime().exec("native2ascii -encoding  utf-8 "+jsFile.getAbsolutePath()+"  "+outPutFolder+jsFile.getName());
			}else if(jsFile.isDirectory() && !jsFile.getName().endsWith(".svn")){
				String outputFilePath = outPutFolder+jsFile.getName() ;
				File file111 = new File(outputFilePath);
				if(!file111.exists()){
					file111.mkdirs() ;
				}
				for(File file1 : jsFile.listFiles()){
					if(!file1.isDirectory() && !file1.isHidden()){
						 Runtime.getRuntime().exec("native2ascii -encoding  utf-8 "+file1.getAbsolutePath()+"  "+outputFilePath+"/"+file1.getName());
					}
				}
			}
		}
	}

}
