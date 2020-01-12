package engine;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Level {
	private Engine m_engine;
	private EntityManager m_entities;
	
	public Level(Engine engine) {		
		this.setEngine(engine);		
		this.m_entities = new EntityManager(engine);		
	}
	
	public void load(String filename) {
		this.load(new File(filename));
	}
	public void load(File file) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		Document xml;
		NodeList nodeList;
		Node nodeEntities;
		Node nodeEntity;
		Node node;
		
		try {
			db = dbf.newDocumentBuilder();
			xml = db.parse(file);
			xml.getDocumentElement().normalize();//optional
			
			int i;
			String str;
			
			str = xml.getDocumentElement().getNodeName();
			if (str.equalsIgnoreCase("level")) {
				
				nodeList = xml.getDocumentElement().getElementsByTagName("entities");
				if (nodeList.getLength()>0) {
					nodeEntities = nodeList.item(0);
					nodeList = nodeEntities.getChildNodes();
					for(i=0;i<nodeList.getLength();i++) {
						nodeEntity = nodeList.item(i);
						
						String type = this.getTagValueByName(nodeEntity, "type");
						String filename = this.getTagValueByName(nodeEntity, "filename");
						if (type.equalsIgnoreCase("map")) {
							System.out.println("map.filename: " + filename);

							node = this.getNodeByName(nodeEntity, "tilemap");
							if (node!=null) {
								System.out.println("tilemap.image" + this.getTagValueByName(node, "image"));
								System.out.println("tilemap.layout" + this.getTagValueByName(node, "layout"));
							}

							//Map map = new Map(this.getEngine());
							//map.load(filename);
						}
						
					}
				}						
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private String getTagValueByName(Node input, String tagName) {
		for(int i=0;i<input.getChildNodes().getLength();i++) {
			Node node = input.getChildNodes().item(i);
			if (node.getNodeType()==Node.ELEMENT_NODE) {
				if (node.getNodeName().equalsIgnoreCase(tagName)) {
					return node.getTextContent();
				}
			}
		}
		return "";
	}
	
	private Node getNodeByName(Node input, String nodeName) {
		for(int i=0;i<input.getChildNodes().getLength();i++) {
			Node node = input.getChildNodes().item(i);
			if (node.getNodeType()==Node.ELEMENT_NODE) {
				if (node.getNodeName().equalsIgnoreCase(nodeName)) {
					return node;
				}
			}
		}
		return null;
	}
	
	public void setEngine(Engine engine) {this.m_engine=engine;}
	public Engine getEngine() {return this.m_engine;}
	public EntityManager getEntities(){return this.m_entities;}
	
  public static void main(String[] args) {
    Level level = new Level(null);
    level.load("data/levels/level0.xml");
  }
}
