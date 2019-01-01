package DOM;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
		
		// 创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			
			// 创建DocumentBuilder对象
			DocumentBuilder db = dbf.newDocumentBuilder();
			
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
	
	public static void main(String[] args) {
		new DOMTest().xmlParser();
	}
}
