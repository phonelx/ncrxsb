/*
 * @copyright:  xindongsheng Technologies Co., Ltd. Copyright 2010-9999,  All rights reserved 
 * @description:  <description> 
 * @author:  liangxj 
 * @datetime:  2011-9-22 下午02:08:31
*/
package monitor.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

/** 
 * 将任意格式的图片文件转换为bmp格式的图片 
 * @author  liangxj
 * @datetime  2011-9-22 下午02:08:31
 */
public class Jpg2BmpUtil {
	/**
	 * jpg格式图片转换为bmp格式 
	 * @param jpgPath : jpg文件路径
	 * @param bmpPath : bmp文件路径
	 * @return void
	 * @throws Exception 
	 */
	public static void jpg2Bmp(String jpgPath,String bmpPath) throws  Exception{
		// 读取jpg格式图片文件 的文件输入流
		FileImageInputStream fiis=new FileImageInputStream(new File(jpgPath));
		//生成 bmp格式图片文件的文件输出流
        FileImageOutputStream fios=new FileImageOutputStream(new File(bmpPath));
        ImageReader jpegReader = null;
        Iterator<ImageReader> it1 = ImageIO.getImageReaders(fiis);
        if(it1.hasNext())
        {	  
              jpegReader = it1.next();                           
        }
        jpegReader.setInput(fiis);
        
          ImageWriter bmpWriter = null;
        Iterator<ImageWriter> it2 = ImageIO.getImageWritersByFormatName("bmp");
        if(it2.hasNext())
       {
             bmpWriter = it2.next();      
       }
          bmpWriter.setOutput(fios);
          BufferedImage br = jpegReader.read(0);
          bmpWriter.write(br);
         fiis.close();
         fios.close();
	}
	/**@description
	 * @param 
	 * @return void
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		Jpg2BmpUtil.jpg2Bmp("c:/integredSearch.png", "c:/004.bmp");
	    System.out.println("Jpeg到bmp图片转换完成.");
	}

}
