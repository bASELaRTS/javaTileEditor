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
    this.clear(Color.fromARGB(0, 0, 0, 0));
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
  
  public void clear(int color) {
    for(int i=0;i<this.m_data.length;i++) {
      this.m_data[i]=color;
    }
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
  
  public static Texture checkerBoard(int w, int h, int blocks) {
    int i,j;
    int m = w/blocks;
    
    int color = 0;
    int colors[] = new int[2];
    colors[0] = Color.fromARGB(255, 255, 177, 10);
    colors[1] = Color.black.getColor();
    
    Texture texture = new Texture(w,h);
    i=0;
    for(j=0;j<h;j++) {
      for(i=0;i<w;i++) {
        texture.setPixel(i, j, colors[color]);
        if ((i>0)&&((i%m)==0)) {
          color = (color+1)%2;
        }
      }
      color = (color+1)%2;
      
      if ((j>0)&&((j%m)==0)) {
        color = (color+1)%2;
      }
    }
    return texture;
  }
  
  public static Texture heart() {
    int data[] = {
      0,0,1,1,0,0,0,1,1,0,0,  
      0,1,1,1,1,0,1,1,1,1,0,  
      1,1,1,1,1,1,1,1,1,1,1,  
      1,1,1,1,1,1,1,1,1,1,1,  
      1,1,1,1,1,1,1,1,1,1,1,  
      0,1,1,1,1,1,1,1,1,1,0,  
      0,0,1,1,1,1,1,1,1,0,0,  
      0,0,0,1,1,1,1,1,0,0,0,  
      0,0,0,0,1,1,1,0,0,0,0,  
      0,0,0,0,0,1,0,0,0,0,0  
    };
    int color = Color.fromARGB(255, 255, 0, 0);
    Texture texture = new Texture(11,10);
    for(int j=0;j<texture.getHeight();j++) {
      for(int i=0;i<texture.getWidth();i++) {
        if (data[j*texture.getWidth()+i]>0) {
          texture.setPixel(i, j, color);
        }
      }
    }
    return texture;
  }
}
