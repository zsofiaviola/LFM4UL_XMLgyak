package hu.domparse.lfm4ul;

import java.io.File;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DomModifyLFM4UL {

	public static void main(String argv[]) {

		try {
			File inputFile = new File("xmlLFM4UL.xml"); //megadom a file nevet
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile); //file elemzes
			
		

			// ID:1234 körömlakk ár módosítás 2400 ft-ra
			NodeList nodes = doc.getElementsByTagName("koromlakkok"); //Megkapjuk a koromlakkok NodeListet a dokumentumban
			//for ciklussal vegigmegyunk
			for (int temp = 0; temp < nodes.getLength(); temp++) {
				Node node = nodes.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if (node.getAttributes().getNamedItem("ID").getTextContent().equals("1234")) { //ID alapjan keressuk meg
						NodeList childNodes = node.getChildNodes();
						for (int temp2 = 0; temp2 < childNodes.getLength(); temp2++) {
							Node childNode = childNodes.item(temp2);
							if (childNode.getNodeName().equals("ar")) {
								childNode.setTextContent("2400");

							}

						}
					}
				}

			}

			// ID:111 vásárló név módosítás
			nodes = doc.getElementsByTagName("vasarlo"); //Megkapjuk a vasarlo NodeListet a dokumentumban

			for (int temp = 0; temp < nodes.getLength(); temp++) {
				Node node = nodes.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if (node.getAttributes().getNamedItem("ID").getTextContent().equals("111")) { //ID alapjan keressuk meg
						NodeList childNodes = node.getChildNodes();
						for (int temp2 = 0; temp2 < childNodes.getLength(); temp2++) {
							Node childNode = childNodes.item(temp2);
							if (childNode.getNodeName().equals("nev")) {
								childNode.setTextContent("Nagyne Kiss Anna");

							}

						}
					}
				}

			}
			
			//Ajándékok törlése
			Node koromlakkgyarto = doc.getFirstChild();
			NodeList childNodes = koromlakkgyarto.getChildNodes();
			for(int temp3 = 0; temp3 < childNodes.getLength(); temp3++) {
				Node node = childNodes.item(temp3);
	            
				if("ajandek".equals(node.getNodeName()))
					koromlakkgyarto.removeChild(node); //removeChild()-el torles
			}

			write(doc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//kiirató fgv
	private static void write(Document doc) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		System.out.println("-Modified File-");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amunt", "2");

		DOMSource source = new DOMSource(doc);

		StreamResult console = new StreamResult(System.out); //kiiratás konzolra

		transformer.transform(source, console);
		
	}
}