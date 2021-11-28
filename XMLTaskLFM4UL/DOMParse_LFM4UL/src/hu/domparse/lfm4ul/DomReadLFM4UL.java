package hu.domparse.lfm4ul;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DomReadLFM4UL {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		try {
		//megadom a file nevet
		File file = new File("xmlLFM4UL.xml");
		//DOM létrehozása
		//DocumentBuilderFactorybol megkapjuk a DocumentBuildert
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		
		Document doc = dBuilder.parse(file); //file elemzes
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); //megkapjuk, gyokerelem kiiratasa
		NodeList nList = (NodeList) doc.getDocumentElement(); //gyerekelemek mentjuk listaba
		
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			System.out.println("Aktualis elem:"+ nNode.getNodeName());
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element)nNode;
				//ID kiiratás
				String ID = elem.getAttribute("ID"); //elem attributumot megkapjuk
				System.out.println("ID: " + ID);
				//tulajdonságok kiiratása (gyerekelemek)
				String nodeContent = " ";
				NodeList childNodes = elem.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					if (childNodes.item(j).getTextContent().trim() != "") {
						nodeContent = childNodes.item(j).getTextContent().trim();
						System.out.println(childNodes.item(j).getNodeName() + " : " + nodeContent);
					}	
				}
			}
			System.out.println();	
			}
			
	//hibakezelés
	}catch (SAXException | IOException | ParserConfigurationException e) {
		System.out.println("Hiba" + e.getMessage());
		e.printStackTrace();
	}	
	}	
}
			
		
