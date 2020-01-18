package engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GraphicsAWT implements IGraphics {
  private Size m_size;
  private BufferedImage m_bitmap;
  private Graphics m_graphics;
  
  public GraphicsAWT(int width, int height) {
    this.m_size = new Size(width,height);
    this.m_bitmap = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
    this.m_graphics = this.m_bitmap.getGraphics();
  }
  
  // interface functions
  public void clear() {
    this.m_graphics.setColor(java.awt.Color.black);
    this.m_graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
  }
  public void setPixel(int x, int y, int color){    
    this.m_graphics.setColor(new java.awt.Color(color));
    this.m_graphics.drawLine(x,y,x,y);
  }
  public int getPixel(int x, int y){
    return java.awt.Color.black.getRGB();
  }
  public void drawLine(int x1, int y1, int x2, int y2, int color) {
    this.m_graphics.setColor(new java.awt.Color(color));
    this.m_graphics.drawLine(x1, y1, x2, y2);
  }
  public void drawImage(BufferedImage image, int x, int y, int w, int h) {
    this.m_graphics.drawImage(image, x, y, w, h, null);
  }
  public void drawString(String s, int x, int y, int color) {
    this.m_graphics.setColor(new java.awt.Color(color));
    this.m_graphics.drawString(s, x, y);
  }
  public void fillRect(int x, int y, int w, int h, int color) {
    this.m_graphics.setColor(new java.awt.Color(color));
    this.m_graphics.fillRect(x, y, w, h);
  }
  public BufferedImage getImage() {return this.m_bitmap;}
  public int getWidth() {return this.m_size.getWidth();}
  public int getHeight() {return this.m_size.getHeight();}
  
}
