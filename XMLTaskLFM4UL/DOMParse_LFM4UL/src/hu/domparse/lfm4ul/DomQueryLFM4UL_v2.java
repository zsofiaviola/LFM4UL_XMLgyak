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
			//DOM objektum l�trehoz�sa 
			File xmlFile = new File("xmlLFM4UL.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			
			Document document = dBuilder.parse(xmlFile); //parszolas
			document.getDocumentElement().normalize();
			
			//XPath obejktum l�trehoz�s
			XPath xPath = XPathFactory.newInstance().newXPath(); 
			
			
			//lek�rdez�sek
			//adatb�zis lek�rdez�s (�sszes adat)
			String expression = "/koromlakkgyarto/*"; //megadjuk az eleresi utat �s a csom�pont list�t
			
			//gy�rt� adatai
			//String expression = "koromlakkgyarto/gyarto"; 
			
			//111. sz�m� v�s�rl� lek�rdez�s
			//String expression = "//vasarlo[@id='111']";
			
			//2500ft feletti kieg�sz�t�
			//String expression = "/koromlakkgyarto/kiegeszitok[ar>2000]";
			
			//utolso ajandek 
			//String expression = "/koromlakkgyarto/ajandek[last()]";
			
			//lista k�szit�s, XPath kifejez�s megir�sa, ki�rt�kel�s
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			//v�gigmegy�nk a csom�pontokon
			for(int i=0; i<nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				Node nNode = nodeList.item(i);
				
				System.out.println("Aktualis elem:" + node.getNodeName());
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element)nNode;
					
					// ID ki�r�sa
					String ID = elem.getAttribute("ID");
					System.out.println("ID: " + ID);
					
					//tulajonds�gok kiirat�sa (gyerekelemek)
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
	
			
//hibakezel�s			
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
	
	
