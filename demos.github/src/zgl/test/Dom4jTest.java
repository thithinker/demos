package zgl.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 使用Dom4j
 * @author Y
 * 2014年7月26日
 */
public class Dom4jTest {
	public static void main(String[] args) {
		Dom4jTest test = new Dom4jTest();
		String path = "D:/abc.xml";
		test.write(test.createDocument(), path);
		test.read(path);
	}
	/**
	 * 创建Document
	 * @return Document
	 */
	public Document createDocument(){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("persons");
		
		Element person1 = root.addElement("person");
		person1.addAttribute("name", "汤姆").addAttribute("age", "12");
		Element birthday1 = person1.addElement("birthday");
		birthday1.addText("2002-02-01");
		Element province = person1.addElement("country");
		province.addText("us");
		
		
		Element person2 = root.addElement("person").addAttribute("name", "杰瑞").addAttribute("age", "9");
		person2.addElement("birthday").addText("2005-11-03");
		person2.addElement("country").addText("cn");
		
		return document;
		
	}
	
	/**
	 * 写Document
	 * @param document 完成的Document
	 * @param path 写入文件路径
	 */
	public void write(Document document, String path){
		try {
			//输出格式
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new FileWriter(path), format);
			writer.write(document);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(System.out, format);
			writer.write(document);
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	/**
	 * 读取xml文件
	 * @param path 读取文件的路径
	 */
	public void read(String path){
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(path);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element root = document.getRootElement();
		for(@SuppressWarnings("unchecked")
		Iterator<Element> iterator = root.elementIterator(); iterator.hasNext(); ){
			Element ele = iterator.next();
			System.out.println(ele.getName());
			System.out.println("\tname: " + ele.attributeValue("name") + 
					"\tage: " + ele.attributeValue("age") + 
					"\tbirthday: " + ele.element("birthday").getText() + 
					"\tcountry: " + ele.element("country").getText());
		}
		
		Element p = root.addElement("person").addAttribute("name", "杰克").addAttribute("age", "11");
		p.addElement("birthday").addText("2003-9-15");
		p.addElement("country").addText("en");
		
		//重新写入文件
		write(document, path);
	}
}
