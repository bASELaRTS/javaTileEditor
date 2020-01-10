package engine;

public class Mouse {
  private int m_xposition;
  private int m_yposition;
  
  private boolean m_left;
  private boolean m_middle;
  private boolean m_right;
  
  public Mouse() {
    this.setXY(0, 0);
    this.setLeft(false);
    this.setRight(false);
    this.setMiddle(false);
  }
  
  public void setX(int i) {this.m_xposition=i;}
  public int getX() {return this.m_xposition;}
  public void setY(int i) {this.m_yposition=i;}
  public int getY() {return this.m_yposition;} 
  public void setXY(int x, int y) {
    this.setX(x);
    this.setY(y);
  }
  
  public void setLeft(boolean b) {this.m_left=b;}
  public boolean getLeft() {return this.m_left;}
  public void setMiddle(boolean b) {this.m_middle=b;}
  public boolean getMiddle() {return this.m_middle;}
  public void setRight(boolean b) {this.m_right=b;}
  public boolean getRight() {return this.m_right;}
}
