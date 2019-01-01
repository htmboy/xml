package DOM;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMTest {
	

	/**
	 * 解析xml文件
	 */
	public void xmlParser() {
		
		try {
			// 提取代码, 提高复用
			DocumentBuilder db = getDocumentBuilder();
			
			// 通过DocumentBuilder对象的parser方法加载.xml文件到当前项目
			Document document = db.parse("books.xml");
			
			// 获取所有book节点的集合
			NodeList bookList = document.getElementsByTagName("book");
			
			// 通过nodelist的getLength()方法可以获取bookList的长度
			System.out.println("一共有" + bookList.getLength() + "本书");
			
			// 遍历每一个book节点
			for(int i = 0; i < bookList.getLength(); i++) {
				
				System.out.println("---下面开始遍历第" + (i + 1) + "本书的内容---");
				
				// 前提: 不知道节点的情况下用下面的方法遍历
				
				// 通过item(i)方法 获取一个book节点, nodelist的索引值从0开始
				Node book = bookList.item(i);
				
				// 获取book节点的所有属性集合
				//NamedNodeMap attrs = book.getAttributes();
				
				// System.out.println("第" + (i + 1) + "本书共有" + attrs.getLength() + "个属性");
				
				// 遍历book的属性
//				for(int j = 0; j < attrs.getLength(); j++) {
//					
//					// 通过item(index)方法获取book节点的某一个属性
//					Node attr = attrs.item(j);
//					
//					// 获取属性名
//					System.out.print("属性名: " + attr.getNodeName());
//					
//					// 获取属性值
//					System.out.println("--属性值" + attr.getNodeValue());
//					
//				}
				
				// 前提: 已经知道book节点有且有1个id属性
				
				// 将book节点进行强制类型转换, 转换成Element类型
				// Element book = (Element) bookList.item(i);
				
				// 通过getAttrbute("id") 方法获取指定属值
				//String attrValue = book.getAttribute("id");
				
				//System.out.println("id属性的值为" + attrValue);
				
				// 解析book节点的子节点
				NodeList childNodes = book.getChildNodes();
				
				System.out.println("第" + (i + 1) + "本书共有" + childNodes.getLength() + "个子节点");

				// 遍历childNodes获取每个节点的节点名和节点值
				for(int k = 0; k < childNodes.getLength(); k++) {
					
					// 区分出text类型的node以及element类型的node
					// NodeType 的类型有3 element attr text
					if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
						
						// 获取elemen类型的节点名
						System.out.print(childNodes.item(k).getNodeName());
						
						// 类似 javascript 需要获取节点第一个节点的节点值才是文本内容
						// System.out.println("--节点值是: " + childNodes.item(k).getFirstChild().getNodeValue());
						
						// 另外一个方法
						System.out.println("--节点值是: " + childNodes.item(k).getTextContent());
						
						// 区别在 <name><a>连接</a>冰与火之歌</name>
						// getFirstChild().getNodeValue() 获取不到name的文本内容, 显示为null
						// getTextContent() 会同时获取a标签的内容
					}
				}
				System.out.println("---结束遍历第" + (i + 1)+ "本书的内容---");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
		// 创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		// 创建DocumentBuilder对象
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db;
	}
	
	/**
	 * 生成XML
	 * xml 中 standalone = "yes" 表示没有dtd和schema说明文档
	 */
	public void createXML() {
		try {
			DocumentBuilder db = getDocumentBuilder();
			Document document = db.newDocument();
			
			// 设置 standalone = "yes"
			document.setXmlStandalone(true);
			
			Element bookstore = document.createElement("bookStore");
			
			// 向bookstore根节点中添加子节点book
			Element book = document.createElement("book");
			Element name = document.createElement("name");
			
			// 向标签添加文本内容
			name.setTextContent("小王子");
			
			// 向标签添加属性
			book.setAttribute("id", "1");
			
			// 将book节点添加到bookstore根节点中
			bookstore.appendChild(book);
			
			// 将bookstore节点(已经包含了book)添加dom树中
			document.appendChild(bookstore);
			
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();
			
			// dom树的合理换行
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.transform(new DOMSource(document), new StreamResult(new File("books1.xml")));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		new DOMTest().createXML();
	}
}
