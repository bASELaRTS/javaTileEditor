package engine;

import java.awt.Color;
import java.awt.image.BufferedImage;

public interface IGraphics {
  public void clear();
  
  public void drawLine(int x1, int y1, int x2, int y2, Color color);  
  public void drawImage(BufferedImage image, int x, int y, int w, int h);
  public void drawString(String s, int x, int y, Color color);
  
  public void fillRect(int x, int y, int w, int h, Color color);
  
  public int getWidth();
  public int getHeight();
  public BufferedImage getImage();
}
