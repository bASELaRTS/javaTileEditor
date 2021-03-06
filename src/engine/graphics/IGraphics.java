package engine.graphics;

import java.awt.image.BufferedImage;

public interface IGraphics {
  public void clear();
  
  public void setPixel(int x, int y, int color);
  public int getPixel(int x, int y);
  
  public void drawLine(int x1, int y1, int x2, int y2, int color);
  public void drawCircle(int x, int y, int r, int color);
  public void drawImage(BufferedImage image, int x, int y, int w, int h);
  public void drawTexture(Texture texture, int x, int y, int w, int h);
  public void drawString(String s, int x, int y, int color);
  
  public void fillRect(int x, int y, int w, int h, int color);
  public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int color);
  public void fillCircle(int x, int y, int r, int color);
  
  public void drawFont(String s, int x, int y, Font font);
  
  public int getWidth();
  public int getHeight();
  public BufferedImage getImage();
}
