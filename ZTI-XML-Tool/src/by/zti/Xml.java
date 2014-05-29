package by.zti;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

/**
 * 
 * Небольшой интерфейс, для работы с XML файлами
 * 
 * @author Cvazer
 * @version 1.0;
 */
public class Xml {
	
	/**
	 * 
	 * Позволяет создавать XML файл.
	 * 
	 * @param name Имя файл
	 * @param rootName Имя корня
	 * @param rewrite Перезаписывать-ли при совпадении имен
	 * @return Указатель на созданный файл
	 */
	public static File newXml(String name, String rootName, boolean rewrite){
		if(!name.contains(".xml")){
			name+=".xml";
		}
		File xmlDocument = new File(name);
		checkFileExistance(xmlDocument, rewrite);
		if(rewrite){
			saveXml(rootName, xmlDocument);
		}
		return xmlDocument;
	}
	
	/**
	 * 
	 * Удаляет ветвь
	 * 
	 * @param xmlFile XML файл
	 * @param nodeName Имя удаляемой ветви
	 * @param rootName Корень удаляемой ветви
	 */
	public static void removeNode(File xmlFile, String nodeName, String rootName){
		checkFileExistance(xmlFile, false);
		Document document = new Document();
		document = parseDocument(document, xmlFile);
		findNode(document.getRootElement(), rootName).removeContent(findNode(document.getRootElement(), nodeName));
		saveXml(xmlFile, document);
	}
	
	/**
	 * 
	 * Удаление прилежашей к корню ветви
	 * 
	 * @param xmlFile XML файл
	 * @param nodeName Имя удаляемой ветви
	 */
	public static void removeRootNode(File xmlFile, String nodeName){
		checkFileExistance(xmlFile, false);
		Document document = new Document();
		document = parseDocument(document, xmlFile);
		document.getRootElement().removeChildren(nodeName);
		saveXml(xmlFile, document);
	}
	
	/**
	 * 
	 * Добавляение ветви
	 * 
	 * @param xmlFile XML файл
	 * @param nodeName имя новой ветви
	 * @param rootName корень новой ветви
	 */
	public static void addNode(File xmlFile, String nodeName, String rootName){
		checkFileExistance(xmlFile, false);
		Document document = new Document();
		document = parseDocument(document, xmlFile);
		findNode(document.getRootElement(), rootName).addContent(new Element(nodeName));
		saveXml(xmlFile, document);
	}
	
	
	/**
	 * 
	 * Добавление ветви к корню
	 * 
	 * @param xmlFile XML файл
	 * @param nodeName имя новой ветви
	 */
	public static void addRootNode(File xmlFile, String nodeName){
		checkFileExistance(xmlFile, false);
		Document document = new Document();
		document = parseDocument(document, xmlFile);
		document.getRootElement().addContent(new Element(nodeName));
		saveXml(xmlFile, document);
	}
	
	/**
	 * 
	 * Получение значения атрибута
	 * 
	 * @param xmlFile XML файл
	 * @param nodeName Имя ветки атрибута
	 * @param attName Имя самого атрибута
	 * @return значение атрибута
	 */
	public static Object getAttribute(File xmlFile, String nodeName, String attName){
		checkFileExistance(xmlFile, false);
		Document document = new Document();
		document = parseDocument(document, xmlFile);
		return findNode(document.getRootElement(), nodeName).getAttributeValue(attName);
	}
	
	/**
	 * 
	 * Установление значения атрибута или создает его
	 * 
	 * @param xmlFile XML файл
	 * @param nodeName имя ветки атрибута
	 * @param attName имя самого атрибута 
	 * @param aValue значение атрибута
	 */
	public static void setAttribute(File xmlFile, String nodeName, String attName, String aValue){
		checkFileExistance(xmlFile, false);
		Document document = new Document();
		document = parseDocument(document, xmlFile);
		findNode(document.getRootElement(), nodeName).setAttribute(attName, aValue);
		saveXml(xmlFile, document);
	}
	
	/**
	 * 
	 * удаление атрибута
	 * 
	 * @param xmlFile XML файл
	 * @param nodeName Имя ветви атрибута
	 * @param attName имя атрибута
	 */
	public static void removeAttributes(File xmlFile, String nodeName, String attName){
		checkFileExistance(xmlFile, false);
		Document document = new Document();
		document = parseDocument(document, xmlFile);
		findNode(document.getRootElement(), nodeName).removeAttribute(attName);
		saveXml(xmlFile, document);
	}
	
	private static Document parseDocument(Document document, File xmlFile){
		try {
			SAXBuilder parser = new SAXBuilder();
			document = (Document) parser.build(xmlFile);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (JDOMException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return document;
	}
	
	private static void checkFileExistance(File file, boolean rewrite){
		if(file.exists()&&!rewrite){
			return;
		}else if(file.exists()&&rewrite){
			try {
				file.delete();
				file.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}else{
			try {
				file.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
	
	private static void saveXml(String rootName, File xmlFile){
		try {
			Element rootElement = new Element(rootName);
			Document newDocument = new Document(rootElement);
			XMLOutputter outputter = new XMLOutputter();
			FileWriter writer = new FileWriter(xmlFile);
			outputter.output(newDocument, writer);
			writer.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private static void saveXml(File xmlFile, Document document){
		try {
			XMLOutputter outputter = new XMLOutputter();
			FileWriter writer = new FileWriter(xmlFile);
			outputter.output(document, writer);
			writer.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	private static Element findNode(Element e, String nodeName){
		List<Element> childrens = e.getChildren();
		Element root = null;
		for(Element el: childrens){
			if(el.getName().contentEquals(nodeName)){
				root=el;
				break;
			}else if(el.getChildren().size()!=0){
				return findNode(el, nodeName);
			}
		}
		return root;
	}
}
