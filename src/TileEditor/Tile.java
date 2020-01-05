package TileEditor;

import java.awt.image.BufferedImage;

public class Tile {
	private String m_name;
  private int m_x;
  private int m_y;
  private int m_width;
  private int m_height;
  private boolean m_selected;
  
  private BufferedImage m_image;
  
  public Tile() {
  	this.setTile("", 0, 0, 0, 0, false, null);
  }
  
  public Tile(BufferedImage image, int x, int y, int w, int h) {
  	this.setTile("", x, y, w, h, false, image);
  }
  
  public Tile(BufferedImage image, int x, int y) {
  	this.setTile("", x, y, image.getWidth(), image.getHeight(), false, image);
  }
  
  private void setTile(String name, int x, int y, int w, int h, boolean selected, BufferedImage image) {
  	this.setName(name);
  	this.setXY(x, y);
  	this.setSize(w, h);
  	this.setSelected(selected);
  	this.setImage(image);
  }
  
  public void setName(String s) {this.m_name=s;}
  public String getName() {return this.m_name;}
  
  public void setWidth(int i) {this.m_width=i;}
  public int getWidth() {return this.m_width;}
  public void setHeight(int i) {this.m_height=i;}
  public int getHeight() {return this.m_height;}
  public void setSize(int w, int h) {
    this.setWidth(w);
    this.setHeight(h);
  }
  
  public void setX(int i) {this.m_x=i;}
  public int getX() {return this.m_x;}
  public void setY(int i) {this.m_y=i;}
  public int getY() {return this.m_y;}
  public void setXY(int x, int y) {
    this.setX(x);
    this.setY(y);
  }
  
  public void setImage(BufferedImage image) {this.m_image=image;}
  public BufferedImage getImage() {return this.m_image;}
  
  public void setSelected(boolean b) {this.m_selected=b;}
  public boolean getSelected() {return this.m_selected;}
}