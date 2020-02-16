package engine.graphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import engine.Size;

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
  public void drawLine(int x1, int y1, int x2, int y2, int color) {
    boolean steep = Math.abs(y2-y1)>Math.abs(x2-x1);
    int i;
    int dx;
    int dy;
    int ystep;
    
    if (steep) {
      i = x1;
      x1 = y1;
      y1 = i;
      
      i = x2;
      x2 = y2;
      y2 = i;
    }
    
    if (x1>x2) {
      i = x1;
      x1 = x2;
      x2 = i;
      
      i = y1;
      y1 = y2;
      y2 = i;
    }
    
    dx = x2-x1;
    dy = Math.abs(y2-y1);
    
    int err = dx/2;
    
    if (y1<y2) {
      ystep=1;
    } else {
      ystep = -1;
    }
    
    while(x1<=x2) {
      if (steep) {
        this.setPixel(y1, x1, color);
      } else {
        this.setPixel(x1, y1, color);
      }
      
      err -= dy;
      if (err<0) {
        y1 += ystep;
        err+=dx;
      }
      
      x1++;
    }
  }
  public void drawHLine(int x1, int y, int x2, int color) {
    int i;
    
    if (x1>x2) {
      i = x1;
      x1 = x2;
      x2 = i;
    }
    
    if ((x1<this.getWidth())&&(x2>0)) {
      if (x1<0) x1 = 0;
      if (x2>=this.getWidth()) x2 = this.getWidth();
      if ((x2-x1)>0) {
        for(i=x1;i<x2;i++) {
          this.setPixel(i, y, color);
        }
      }    
    }    
  }
  public void drawVLine(int x, int y1, int y2, int color) {
    int i;
    
    if (y1>y2) {
      i = y1;
      y1 = y2;
      y2 = i;
    }

    if ((y1<this.getHeight())&&(y2>0)) {
      if (y1<0) y1 = 0;
      if (y2>=this.getHeight())y2=this.getHeight();
      if ((y2-y1)>0) {
        for(i=y1;i<y2;i++) {
          this.setPixel(x, i, color);
        }
      }
    }   
  }
  public void drawCircle(int x, int y, int r, int color) {
    int f = 1-r;
    int ddF_x=1;
    int ddF_y=-2*r;
    int x0 = 0;
    int y0 = r;
    
    this.setPixel(x, y+r, color);
    this.setPixel(x, y-r, color);
    this.setPixel(x+r, y, color);
    this.setPixel(x-r, y, color);
    
    while(x0<y0) {
      if (f>=0) {
        y0--;
        ddF_y+=2;
        f+=ddF_y;
      }
      x0++;
      ddF_x+=2;
      f+=ddF_x;
      
      this.setPixel(x+x0, y+y0, color);
      this.setPixel(x-x0, y+y0, color);
      this.setPixel(x+x0, y-y0, color);
      this.setPixel(x-x0, y-y0, color);

      this.setPixel(x+y0, y+x0, color);
      this.setPixel(x-y0, y+x0, color);
      this.setPixel(x+y0, y-x0, color);
      this.setPixel(x-y0, y-x0, color);
    }
  }
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
  public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int color) {
    int i,j,k;
    int x,xx,y;
    
    // sort by y
    if (y1>y3) {
      i = x1;
      x1 = x3;
      x3 = i;
      i = y1;
      y1 = y3;
      y3 = i;
    }
    if (y2>y3) {
      i = x2;
      x2 = x3;
      x3 = i;
      i = y2;
      y2 = y3;
      y3 = i;
    }
    if (y1>y2) {
      i = x2;
      x2 = x1;
      x1 = i;
      i = y2;
      y2 = y1;
      y1 = i;
    }
    
    // find edge
    double dss = ((double)x2-x1)/(y2-y1);
    double dsl = ((double)x3-x1)/(y3-y1);
    double ds;
    double dl;
    int[] xstarts = new int[y3-y1];
    int[] xends = new int[y3-y1];
    
    ds = x1;
    dl = x1;
    j = 0;
    for(i=y1;i<y2;i++) {
      xstarts[j] = (int)ds;
      xends[j] = (int)dl;
      j++;
      ds+=dss;
      dl+=dsl;
    }
    
    dss = ((double)x3-x2)/(y3-y2);
    ds = x2;
    for(i=y2;i<y3;i++) {
      xstarts[j] = (int)ds;
      xends[j] = (int)dl;
      j++;
      ds+=dss;
      dl+=dsl;
    }
    
    // draw
    y = y1;
    for(i=0;i<j;i++) {
      x = xstarts[i];
      xx = xends[i];
      
      if (x>xx) {
        k = x;
        x = xx;
        xx = k;
      }
      
      this.drawHLine(x, y, xx, color);
      
      y++;
    }
  }
  
  public void fillCircle(int x, int y, int r, int color) {
    int f = 1-r;
    int ddF_x = 1;
    int ddF_y = -2 * r;
    int x0 = 0;
    int y0 = r;
    int px = x0;
    int py = y0;
    int delta = 0;
    int corners = 3;
    
    this.drawVLine(x, y-r, 2*r+1, color);
    
    while(x0<y0) {
      if (f>=0) {
        y0--;
        ddF_y+=2;
        f+=ddF_y;
      }
      x0++;
      ddF_x+=2;
      f+=ddF_x;
      // These checks avoid double-drawing certain lines
      if (x<(y+1)) {
        if ((corners & 1)>0){
          this.drawVLine(x+x0, y-y0, 2*y0+delta, color);          
        }
        if ((corners & 2)>0) {
          this.drawVLine(x-x0, y-y0, 2*y+delta, color);
        }
      }
      if (y0!=py) {
        if ((corners & 1)>0) {
          this.drawVLine(x+py, y-px, 2*px+delta, color);         
        }
        if ((corners & 2)>0) {
          this.drawVLine(x-py, y-px, 2*px+delta, color);
        }
        py=y;
      }
      px=x;
    }
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
