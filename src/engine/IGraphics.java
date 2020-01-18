package engine;

import java.awt.image.BufferedImage;

public interface IGraphics {
  public void clear();
  
  public void setPixel(int x, int y, int color);
  public int getPixel(int x, int y);
  
  public void drawLine(int x1, int y1, int x2, int y2, int color);  
  public void drawImage(BufferedImage image, int x, int y, int w, int h);
  public void drawString(String s, int x, int y, int color);
  
  public void fillRect(int x, int y, int w, int h, int color);
  
  public int getWidth();
  public int getHeight();
  public BufferedImage getImage();
}
