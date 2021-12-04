package hu.domparse.lfm4ul;

import java.io.File;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DomQueryLFM4UL {
	
	public static void main(String[]args) {
		
		
		try {
			//DOM objektum létrehozása 
			File xmlFile = new File("xmlLFM4UL.xml");
			//DOM létrehozása.
			//DocumentBuilderFactorybol megkapjuk a DocumentBuildert.
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			
			Document document = dBuilder.parse(xmlFile); //parszolas
			document.getDocumentElement().normalize();
			
			//lekérdezések
			NodeList nList = document.getElementsByTagName("gyarto"); //gyarto tag nevu elem
			writeNodeList(nList);
			
			NodeList nList1 = document.getElementsByTagName("vasarlo"); //vasarlo tag nevu elemek
			writeNodeList(nList1);
			
			NodeList nList2 = document.getElementsByTagName("koromlakkok"); //koromlakkok tag nevu elemek
			writeNodeList(nList2);
			
		//hibakezelés	
	}catch (SAXException | IOException | ParserConfigurationException e) {
		System.out.println("Hiba" + e.getMessage());
		e.printStackTrace();
	}
	}
	
			
	//kiiratófgv
	private static void writeNodeList(NodeList nList) {
		for (int i = 0; i < nList.getLength(); i++)   {
			Node elemNode = nList.item(i);  
			if (elemNode.getNodeType() == Node.ELEMENT_NODE )   
				{  
					System.out.println("NodeName: " + elemNode.getNodeName());  
					
					if (elemNode.hasAttributes())   
					{  
					NamedNodeMap nodeMap = elemNode.getAttributes();  
					for (int j = 0; j < nodeMap.getLength(); j++)   
					{  
					Node node = nodeMap.item(j);  
					System.out.println("Attribute Name:  " + node.getNodeName());  
					System.out.println("Attribute Value: " + node.getNodeValue());  
					}  
					}  
					//visszateres igazzal ha van gyereke,, hamis ha nincs
					if (elemNode.hasChildNodes()){
					writeNodeList(elemNode.getChildNodes());
					}
				} 
				else {
					System.out.println(elemNode.getTextContent());
				}
	
	}
	}
}

	
	
