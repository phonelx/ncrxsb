package monitor.common.util;

import java.io.*;
import java.util.List;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

/**
 * jdom工具类
 * @author  cl
 * @datetime  2011-5-10 下午03:27:04
 */
public class JDomUtil {

	/**
	 * 根据指定路径的XML文件建立JDom对象
	 * 
	 * @param filePath
	 *            XML文件的路径
	 * @return 返回建立的JDom对象，建立不成功返回null 。
	 */
	public static Document buildFromFile(String filePath) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder.build(new File(filePath));
			return anotherDocument;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	/**
	 * 根据XML 字符串 建立JDom对象
	 * 
	 * @param xmlString
	 *            XML格式的字符串
	 * @return 返回建立的JDom对象，建立不成功返回null 。
	 */
	public static Document buildFromXMLString(String xmlString) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder.build(new StringReader(xmlString));
			return anotherDocument;
		}catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	/**
	 * 根据Dom对象建立JDom对象
	 * 
	 * @param Dom
	 *            org.w3c.dom.Document对象
	 * @return 返回建立的JDom对象，建立不成功返回null 。
	 */
	public static Document buildFromDom(org.w3c.dom.Document Dom)
			throws JDOMException, IOException {
		org.jdom.input.DOMBuilder builder = new org.jdom.input.DOMBuilder();
		Document jdomDoc = builder.build(Dom);
		return jdomDoc;
	}
	
	/**
	 * 根据Dom对象建立JDom对象
	 * 
	 * @param Dom
	 *            org.w3c.dom.Document对象
	 * @return 返回建立的JDom对象，建立不成功返回null 。
	 */
	public static Document buildFromInputStream(InputStream input)
			throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document jdomDoc = builder.build(input);
		return jdomDoc;
	}


	
	public static String outputListAsString(List list, String encoding) {
		StringBuffer sb = new StringBuffer();
		
		Format fmt = Format.getCompactFormat();
        fmt.setEncoding(encoding);           
        fmt.setIndent("   ");              
		try {
		    XMLOutputter outputter = new XMLOutputter(fmt);
		    sb.append(outputter.outputString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static String outputDocumentAsString(Document document, String encoding) {
		StringBuffer sb = new StringBuffer();
		
		Format fmt = Format.getCompactFormat();
        fmt.setEncoding(encoding);           
        fmt.setIndent("   ");              
		try {
		    XMLOutputter outputter = new XMLOutputter(fmt);
		    sb.append(outputter.outputString(document));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	public static String outputElementAsString(Element element, String encoding) {
		StringBuffer sb = new StringBuffer();
		
		Format fmt = Format.getCompactFormat();
        fmt.setEncoding(encoding);           
        fmt.setIndent("   ");              
		try {
		    XMLOutputter outputter = new XMLOutputter(fmt);
		    sb.append(outputter.outputString(element));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	public static void outputDocumentAsFile(Document document,File file, String encoding){
		Format fmt = Format.getCompactFormat();
        fmt.setEncoding(encoding);           
        fmt.setIndent("   ");              
		try {
		    XMLOutputter outputter = new XMLOutputter(fmt);
		    FileOutputStream writer = new FileOutputStream(file);
		    outputter.output(document, writer);
			writer.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
}
