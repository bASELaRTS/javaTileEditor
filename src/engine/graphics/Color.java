package engine.graphics;

public class Color {
  public static final Color black = new Color(255,0,0,0);
  public static final Color white = new Color(255,255,255,255);
  public static final Color red = new Color(255,255,0,0);
  public static final Color green = new Color(255,0,255,0);
  public static final Color blue = new Color(255,0,0,255);
  
  private int m_color;
  
  public Color() {
    this.setColor(255, 0, 0, 0);
  }
  public Color(int c) {
    this.setColor(c);
  }
  public Color(int a, int r, int g, int b) {
    this.setColor(a,r,g,b);
  }
  public Color(int r, int g, int b) {
    this.setColor(255,r,g,b);
  }
  
  public void setColor(int c) {this.m_color = c;}
  public void setColor(int a, int r, int g, int b) {
    this.m_color = Color.fromARGB(a, r, g, b);
  }
  public void setColor(int r, int g, int b) {
    this.m_color = Color.fromARGB(255, r, g, b);
  }
  public int getColor() {return this.m_color;}
  public int getA() {return (this.m_color>>24)&0xff;}
  public int getR() {return (this.m_color>>16)&0xff;}
  public int getG() {return (this.m_color>>8)&0xff;}
  public int getB() {return (this.m_color)&0xff;}
  
  public static int fromARGB(int a, int r, int g, int b) {
    int c = 0;
    c |= (a&0xff)<<24;
    c |= (r&0xff)<<16;
    c |= (g&0xff)<<8;
    c |= (b&0xff);
    return c;
  }
}
