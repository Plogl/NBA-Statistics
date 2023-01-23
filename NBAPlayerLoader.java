// Name: Michael Bonfiglio
// Email: mabonfiglio@wisc.edu

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class NBAPlayerLoader implements INBAPlayerLoader{
	
	public NBAPlayerLoader() {
		
	}
	/**
	 * loads the xml file that holds the NBA Players Names and stat totals and
	 * takes those numbers to create NBAPlayer objects that are added to an
	 * arrayList which is returned
	 * @param filepathToXTML - XML file of NBA players
	 * @return listNBAPlayers - list of NBAPlayer objects.
	 */
	@Override
	public List<INBAPlayer> loadPlayers(String filepathToXTML) throws FileNotFoundException {
		List<INBAPlayer> listNBAPlayers = new ArrayList<>();
		try {
        File file = new File(filepathToXTML);
        // Defines a factory API that enables parser that produces
        // DOM object trees from XML documents.
        DocumentBuilderFactory dbf
            = DocumentBuilderFactory.newInstance();
        
        // creating an object of builder to parse the  xml file.
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document document = docBuilder.parse(file);

        //normalize puts nodes in full depth of the sub-tree 
        document.getDocumentElement().normalize();
        
        // nodeList gets all the nodes with name player.
        NodeList nodeList
            = document.getElementsByTagName("player");
        
        // Iterates through all the players in NodeList
        for (int x = 0; x < nodeList.getLength(); ++x) {
            Node node = nodeList.item(x);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
            	Element playerElement = (Element) node;
            	String name = playerElement.getElementsByTagName("name").item(0).getTextContent();
            	int rbs = Integer.parseInt(playerElement.getElementsByTagName("rbs").item(0).getTextContent());
            	int asts = Integer.parseInt(playerElement.getElementsByTagName("asts").item(0).getTextContent());
            	int steals = Integer.parseInt(playerElement.getElementsByTagName("steals").item(0).getTextContent());
            	int blocks = Integer.parseInt(playerElement.getElementsByTagName("blocks").item(0).getTextContent());
            	int pts = Integer.parseInt(playerElement.getElementsByTagName("pts").item(0).getTextContent());
            	//creates NBAPlayer object that is added to Arraylist
            	listNBAPlayers.add(new NBAPlayer(pts, asts, rbs, blocks, steals, name));
            }
        }
		}catch(Exception e) {
			System.out.println(e);
		}
		//array list is returned
		return listNBAPlayers;
	}
	

}
