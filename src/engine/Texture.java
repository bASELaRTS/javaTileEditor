package engine;

import java.awt.image.BufferedImage;

public class Texture {
  private Size m_size;
  private int[] m_data;

  public Texture() {
    this.create(0, 0);
  }  
  public Texture(int width, int height) {
    this.create(width, height);    
  }
  public Texture(BufferedImage image) {
    this.create(image.getWidth(), image.getHeight());
    for(int j=0;j<this.getHeight();j++) {
      for(int i=0;i<this.getWidth();i++) {
        this.setPixel(i, j, image.getRGB(i, j));
      }
    }
  }
  
  private void create(int width, int height) {
    int size = width * height;
    this.m_size = new Size(width,height);
    
    this.m_data = null;
    if (size>0) {
      this.m_data = new int[this.getWidth()*this.getHeight()];    
    }
  }
  
  private int getIndex(int x, int y) {    
    int index = y*this.getWidth()+x;
    if ((index>=0)&&(index<this.m_data.length)) {
      return index;
    }
    return -1;
  }
  
  public void setPixel(int x, int y, int color) {
    int index = this.getIndex(x, y);
    if (index>=0) {
      this.m_data[index] = color;
    }
  }
  
  public int getPixel(int x, int y) {
    int index = this.getIndex(x, y);
    if (index>=0) {
      return this.m_data[index];
    }
    return Color.black.getColor();
  }
  
  public int getWidth() {return this.m_size.getWidth();}
  public int getHeight() {return this.m_size.getHeight();}
}
