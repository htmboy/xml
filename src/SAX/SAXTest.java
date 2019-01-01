package SAX;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.helpers.AttributesImpl;

public class SAXTest {
	
	public ArrayList<Book> xmlParser() {
		SAXParserHandler handler = null;
		//获取一个SAXParserFactory的实例
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			// 通过factory获取SAXParser实例
			SAXParser parser = factory.newSAXParser();
			
			// 创建SAXParserHandler对象
			handler = new SAXParserHandler();
			
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
		return handler.getBookList();
	}
	
	public void createXML() {
		ArrayList<Book> bookList = xmlParser();
		
		//生成xml
		
		// 创建一个TransformerFactory类的对象
		SAXTransformerFactory tff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
		try {
			
			// 通过SAXTransformerFactory对象创建一个TransformerHandler对象
			TransformerHandler handler = tff.newTransformerHandler();
			
			// 通过handler对象创建一个Transformer对象
			Transformer tr = handler.getTransformer();
			
			// 通过Transformer对象对生成的xml文件进行设置
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			
			// 设置xml是否换行
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			
			
			File f = new File("newBook.xml");
			if(!f.exists()) {
				f.createNewFile();
			}
			
			// 创建一个Result对象, 并且使其与handler关联
			Result result = new StreamResult(new FileOutputStream(f));
			
			// xml的设置, 如换行, 必须写在setResult的前面
			handler.setResult(result);
			
			// 利用handler对象进行xml文件内容编写
			
			// 打开document
			handler.startDocument();
			AttributesImpl attr = new AttributesImpl();
			handler.startElement("", "", "bookstore", attr);
			for(Book book : bookList) {
				attr.clear();
				attr.addAttribute("", "", "id", "", book.getId());
				handler.startElement("", "", "book", attr);
				
				if(book.getName() != null && !book.getName().trim().equals("")){
					// 创建name标签
					attr.clear();
					handler.startElement("", "", "name", attr);
					handler.characters(book.getName().toCharArray(), 0, book.getName().length());
					handler.endElement("", "", "name");
					
				}
				
				if(book.getYear() != null && !book.getYear().trim().equals("")){
					// 创建year标签
					attr.clear();
					handler.startElement("", "", "year", attr);
					handler.characters(book.getYear().toCharArray(), 0, book.getYear().length());
					handler.endElement("", "", "year");
				}
				
				if(book.getAuthor() != null && !book.getAuthor().trim().equals("")){
					// 创建author标签
					attr.clear();
					handler.startElement("", "", "author", attr);
					handler.characters(book.getAuthor().toCharArray(), 0, book.getAuthor().length());
					handler.endElement("", "", "author");
				}
				
				if(book.getPrice() != null && !book.getPrice().trim().equals("")){
					// 创建price标签
					attr.clear();
					handler.startElement("", "", "price", attr);
					handler.characters(book.getPrice().toCharArray(), 0, book.getPrice().length());
					handler.endElement("", "", "price");
				}
				
				if(book.getLanguage() != null && !book.getLanguage().trim().equals("")){
					// 创建language标签
					attr.clear();
					handler.startElement("", "", "language", attr);
					handler.characters(book.getLanguage().toCharArray(), 0, book.getLanguage().length());
					handler.endElement("", "", "language");
				}
				
				handler.endElement("", "", "book");
				
			}
			
			handler.endElement("", "", "bookstore");
			
			// 关闭document
			handler.endDocument();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new SAXTest().createXML();
	}

}
