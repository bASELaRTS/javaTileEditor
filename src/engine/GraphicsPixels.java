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
    //this.m_data = new int[this.getWidth()*this.getHeight()];
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
    //int color = Color.fromARGB(255, 0, 0, 0);
    int color = Color.black.getColor();
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
  public int getPixel(int x, int y) {
    int index = this.getIndex(x, y);
    if (index>=0) {
      return this.m_data[index];
    }
    return Color.black.getColor();
  }
  public void drawLine(int x1, int y1, int x2, int y2, int color) {}
  public void drawImage(BufferedImage image, int x, int y, int w, int h) {
    int i,j;
    int c;
    double tx,dx;
    double ty,dy;
    Color color = new Color();
    
    if (w<=0) return;
    if (h<=0) return;
    
    dx = (double)image.getWidth()/w;
    dy = (double)image.getHeight()/h;
    
    ty = 0;
    for(j=0;j<h;j++) {      
      tx = 0;
      for(i=0;i<w;i++) {        
        c = image.getRGB((int)tx, (int)ty);
        color.setColor(c);
        if (color.getA()>0) {
          this.setPixel(x+i, y+j, c);        
        }
        tx+=dx;
      }
      ty+=dy;
    }    
  }
  public void drawTexture(Texture texture, int x, int y, int w, int h) {
    int i,j;
    int c;
    double tx,dx;
    double ty,dy;
    Color color = new Color(); 
    
    if (w<=0) return;
    if (h<=0) return;
    
    dx = (double)texture.getWidth()/w;
    dy = (double)texture.getHeight()/h;
    
    ty = 0;
    for(j=0;j<h;j++) {      
      tx = 0;
      for(i=0;i<w;i++) {        
        c = texture.getPixel((int)tx, (int)ty);
        color.setColor(c);
        if (color.getA()>0) {
          this.setPixel(x+i, y+j, c);        
        }
        tx+=dx;
      }
      ty+=dy;
    }
  }
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
    if (x2>=this.getWidth()) x2=this.getWidth();
    if (y2>=this.getHeight()) y2=this.getHeight();
    
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
    /*
    int data[] = ((DataBufferInt)this.m_image.getRaster().getDataBuffer()).getData();
    for(int i=0;i<this.m_data.length;i++) {
      data[i] = this.m_data[i];
    }
    /**/
    return this.m_image;
  }
}
