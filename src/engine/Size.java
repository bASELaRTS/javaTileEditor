package engine;

public class Size {
  private int m_width;
  private int m_height;
  
  public Size() {
    this.setSize(0, 0);
  }
  public Size(int w, int h) {
    this.setSize(w, h);
  }
  public void setSize(Size s) {
    this.setSize(s.getWidth(),s.getHeight());
  }
  
  public void setWidth(int i) {this.m_width=i;}
  public void setHeight(int i) {this.m_height=i;}
  public int getWidth() {return this.m_width;}
  public int getHeight() {return this.m_height;}
  public void setSize(int w, int h) {
    this.setWidth(w);
    this.setHeight(h);
  }
}
