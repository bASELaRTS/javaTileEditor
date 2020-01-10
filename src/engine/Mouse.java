package engine;

public class Mouse {
  private int m_xposition;
  private int m_yposition;
  
  public Mouse() {
    this.setXY(0, 0);
  }
  
  public void setX(int i) {this.m_xposition=i;}
  public int getX() {return this.m_xposition;}
  public void setY(int i) {this.m_yposition=i;}
  public int getY() {return this.m_yposition;} 
  public void setXY(int x, int y) {
    this.setX(x);
    this.setY(y);
  }  
}
