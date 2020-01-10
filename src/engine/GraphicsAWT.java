package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GraphicsAWT implements IGraphics {
  private int m_width;
  private int m_height;
  private BufferedImage m_bitmap;
  private Graphics m_graphics;
  
  public GraphicsAWT(int width, int height) {
    this.setSize(width, height);
    
    this.m_bitmap = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
    this.m_graphics = this.m_bitmap.getGraphics();
  }
  
  public void setWidth(int i) {this.m_width=i;}
  public void setHeight(int i) {this.m_height=i;}
  public void setSize(int width, int height) {
    this.setWidth(width);
    this.setHeight(height);
  }

  // interface functions
  public void clear() {
    this.m_graphics.setColor(Color.black);
    this.m_graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
  }
  public void drawLine(int x1, int y1, int x2, int y2, Color color) {
    this.m_graphics.setColor(color);
    this.m_graphics.drawLine(x1, y1, x2, y2);
  }
  public void drawImage(BufferedImage image, int x, int y, int w, int h) {
    this.m_graphics.drawImage(image, x, y, w, h, null);
  }
  public void drawString(String s, int x, int y, Color color) {
    this.m_graphics.setColor(color);
    this.m_graphics.drawString(s, x, y);
  }
  public void fillRect(int x, int y, int w, int h, Color color) {
    this.m_graphics.setColor(color);
    this.m_graphics.fillRect(x, y, w, h);
  }
  public BufferedImage getImage() {return this.m_bitmap;}
  public int getWidth() {return this.m_width;}
  public int getHeight() {return this.m_height;}
  
}
