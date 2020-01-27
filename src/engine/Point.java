package engine;

public class Point {
  public int x;
  public int y;
  
  public Point() {this.setXY(0, 0);}
  public Point(int x, int y) {this.setXY(x, y);}
  public Point(Point p) {this.setPoint(p);}
  
  public void setPoint(Point p) {this.setXY(p.x, p.y);}
  public void setXY(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
