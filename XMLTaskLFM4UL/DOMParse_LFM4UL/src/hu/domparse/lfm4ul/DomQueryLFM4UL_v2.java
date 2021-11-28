package hu.domparse.lfm4ul;

import java.io.File;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DomQueryLFM4UL {
	
	public static void main(String[]args) {
		
		try {
			//DOM objektum létrehozása 
			File xmlFile = new File("xmlLFM4UL.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			
			Document document = dBuilder.parse(xmlFile); //parszolas
			document.getDocumentElement().normalize();
			
			//XPath obejktum létrehozás
			XPath xPath = XPathFactory.newInstance().newXPath(); 
			
			
			//lekérdezések
			//adatbázis lekérdezés (összes adat)
			String expression = "/koromlakkgyarto/*"; //megadjuk az eleresi utat és a csomópont listát
			
			//gyártó adatai
			//String expression = "koromlakkgyarto/gyarto"; 
			
			//111. számú vásárló lekérdezés
			//String expression = "//vasarlo[@id='111']";
			
			//2500ft feletti kiegészítõ
			//String expression = "/koromlakkgyarto/kiegeszitok[ar>2000]";
			
			//utolso ajandek 
			//String expression = "/koromlakkgyarto/ajandek[last()]";
			
			//lista készités, XPath kifejezés megirása, kiértékelés
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			//végigmegyünk a csomópontokon
			for(int i=0; i<nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				Node nNode = nodeList.item(i);
				
				System.out.println("Aktualis elem:" + node.getNodeName());
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element)nNode;
					
					// ID kiírása
					String ID = elem.getAttribute("ID");
					System.out.println("ID: " + ID);
					
					//tulajondságok kiiratása (gyerekelemek)
					String nodeContent = " ";
					NodeList childNodes = elem.getChildNodes();
					for (int j = 0; j < childNodes.getLength(); j++) {
						if (childNodes.item(j).getTextContent().trim() != "") {
							nodeContent = childNodes.item(j).getTextContent().trim();
							System.out.println(childNodes.item(j).getNodeName() + " : " + nodeContent);
						}	
			
			}
				}
			}
	
			
//hibakezelés			
}catch(ParserConfigurationException e) {
    e.printStackTrace();
} catch(SAXException e) {
e.printStackTrace();
}
catch(IOException e) {
    e.printStackTrace();
    
} catch(XPathExpressionException e) {
        e.printStackTrace();
} 
} 
	
}
	
	
