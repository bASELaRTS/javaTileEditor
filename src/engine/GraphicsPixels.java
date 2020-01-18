package engine;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GraphicsPixels implements IGraphics {
  private Size m_size;
  private BufferedImage m_image;
  private int m_data[];
  private int m_width;
  private int m_height;
  
  public GraphicsPixels(int width, int height) {
    super();
    
    this.m_size = new Size(width,height);
    this.m_width = this.m_size.getWidth();
    this.m_height = this.m_size.getHeight();
    
    this.m_image = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
    this.m_data = ((DataBufferInt)this.m_image.getRaster().getDataBuffer()).getData();
  }
  
  private int getIndex(int x, int y) {
    int index = y*this.m_width+x;
        
    if ((index>=0)&&(index<(this.m_width*this.m_height))) {
      return index;
    }
    
    return -1;
  }

  // interface functions
  public void clear() {
    int color = Color.fromARGB(255, 0, 0, 0);
    for(int i=0;i<this.m_data.length;i++) {
      this.m_data[i]=color;
    }
  }
  public void setPixel(int x, int y, int color) {
    int index = this.getIndex(x, y);
    if (index>=0) {
      this.m_data[index]=color;
    }
  }
  public int getPixel(int x, int y) {return 0;}
  public void drawLine(int x1, int y1, int x2, int y2, int color) {}
  public void drawImage(BufferedImage image, int x, int y, int w, int h) {}
  public void drawString(String s, int x, int y, int color) {}
  public void fillRect(int x, int y, int w, int h, int color) {
    int i,j;
    int index;
    int x1 = x;
    int y1 = y;
    int x2 = x+w;
    int y2 = y+h;
    if (x1<0) x1=0;
    if (y1<0) y1=0;
    if (x2>=w) x2=w;
    if (y2>=y) y2=h;
    
    for(j=y1;j<y2;j++) {
      index = j*this.m_width+x1;
      for(i=x1;i<x2;i++) {
        this.m_data[index]=color;
         index++;
      }
    }
    
  }
  public int getWidth() {return this.m_size.getWidth();}
  public int getHeight() {return this.m_size.getHeight();}
  public BufferedImage getImage() {
    return this.m_image;
  }
}
