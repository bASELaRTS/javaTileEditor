package engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import engine.Size;

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
  public void drawCircle(int x, int y, int r, int color) {
    this.m_graphics.setColor(new java.awt.Color(color));
    this.m_graphics.drawOval(x-r, y-r, r+r, r+r);
  }
  public void drawImage(BufferedImage image, int x, int y, int w, int h) {
    this.m_graphics.drawImage(image, x, y, w, h, null);
  }
  public void drawTexture(Texture texture, int x, int y, int w, int h) {
    int i,j;
    int color;
    double tx,dx;
    double ty,dy;
    
    if (w<=0) return;
    if (h<=0) return;
    
    dx = (double)texture.getWidth()/w;
    dy = (double)texture.getHeight()/h;
    
    ty = 0;
    for(j=0;j<h;j++) {      
      tx = 0;
      for(i=0;i<w;i++) {        
        color = texture.getPixel((int)tx, (int)ty);
        this.setPixel(x+i, y+j, color);        
        tx+=dx;
      }
      ty+=dy;
    }    
  }  
  public void drawString(String s, int x, int y, int color) {
    this.m_graphics.setColor(new java.awt.Color(color));
    this.m_graphics.drawString(s, x, y);
  }
  public void fillRect(int x, int y, int w, int h, int color) {
    this.m_graphics.setColor(new java.awt.Color(color));
    this.m_graphics.fillRect(x, y, w, h);
  }
  public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int color) {
    int x[] = {x1,x2,x3};
    int y[] = {y1,y2,y3};
    this.m_graphics.setColor(new java.awt.Color(color));
    this.m_graphics.fillPolygon(x,y,3);
  }
  
  public void drawFont(String string, int x, int y, Font font) {
    int i,w,h;
    int px;
    Font.Character character;
    px = x;
    for(i=0;i<string.length();i++) {
      character = font.getCharacter(string.charAt(i));
      if (character!=null) {
        w = character.getSize().getWidth();
        h = character.getSize().getHeight();
        this.drawImage(character.getImage(), px, y, w, h);
        px+=w + font.getCharacterSpacing();
      } else {
        px+=font.getDefaultSize().getWidth() + font.getCharacterSpacing();
      }
    }
  }
  
  public BufferedImage getImage() {return this.m_bitmap;}
  public int getWidth() {return this.m_size.getWidth();}
  public int getHeight() {return this.m_size.getHeight();}  
}
