package SAX;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SAXTest {
	
	public void xmlParser() {
		//获取一个SAXParserFactory的实例
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			// 通过factory获取SAXParser实例
			SAXParser parser = factory.newSAXParser();
			
			// 创建SAXParserHandler对象
			SAXParserHandler handler = new SAXParserHandler();
			
			parser.parse("books.xml", handler);
			
			System.out.println("~!~! 共有" + handler.getBookList().size() + "本书");
			for(Book book : handler.getBookList()) {
				System.out.println(book.getId());
				System.out.println(book.getName());
				System.out.println(book.getAuthor());
				System.out.println(book.getYear());
				System.out.println(book.getPrice());
				System.out.println(book.getLanguage());
				System.out.println("---finish---");
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new SAXTest().xmlParser();
	}

}
