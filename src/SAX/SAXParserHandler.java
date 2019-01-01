package SAX;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserHandler extends DefaultHandler {
	private String value = null;
	private Book book = null;
	private ArrayList<Book> bookList = new ArrayList<Book>();
	/**
	 * 用来遍历xml文件的开始标签
	 * 解析xml元素
	 * 
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		// 定义一个全局变量, 记录几本书
		int bookIndex = 0;
		
		// 调用DefaultHandler类的startElement方法
		super.startElement(uri, localName, qName, attributes);
		
		// 开始解析book元素的属性
		if(qName.equals("book")) {
			bookIndex++;
			
			// 创建一个book对象
			book = new Book();
			
			System.out.println("-------------------开始遍历第" + bookIndex + "本书---------------------");

			// 已知book元素下属性的名称, 根据属性名称获取属性值
//			String value = attributes.getValue("id");
//			System.out.println("book的属性值是" + value);
			
			// 不知道book元素下属性的名称以及个数, 如何获取属性名以及属性值
			int num = attributes.getLength();
			for(int i = 0; i < num; i++) {
				System.out.print("book元素的第" + (i + 1) + "个属性名是: " + attributes.getQName(i));
				System.out.println("--属性值是: " + attributes.getValue(i));
				if(attributes.getQName(i).equals("id")) {
					book.setId(attributes.getValue(i));
				}
			}
		}else if(!qName.equals("book") && !qName.equals("bookStore")) {
			System.out.print("节点名是: " + qName + "---");
			
		}
	}

	/**
	 * 用来遍历xml文件的结束标签
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(qName.equals("book")) {
			bookList.add(book);
			book = null;
			System.out.println("-------------------解析结束---------------------");
		}
		else if(qName.equals("name"))
			book.setName(value);
		else if(qName.equals("author"))
			book.setAuthor(value);
		else if(qName.equals("year"))
			book.setYear(value);
		else if(qName.equals("price"))
			book.setPrice(value);
		else if(qName.equals("language"))
			book.setLanguage(value);
	}

	/**
	 * 用来标识解析开始
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("SAX解析开始");
	}
	
	/**
	 * 解析标签内容	
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		value = new String(ch, start, length);
		if(!value.trim().equals(""))
			System.out.println(value);
	}

	/**
	 * 用来标识解析结束
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("SAX解析结束");
	}

	public ArrayList<Book> getBookList() {
		return bookList;
	}

	
	
}
