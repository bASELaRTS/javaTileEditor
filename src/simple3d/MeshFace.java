package simple3d;

public class MeshFace {
  public int p1;
  public int p2;
  public int p3;
  
  public MeshFace() {
    this.setPoints(-1, -1, -1);
  }
  
  public MeshFace(int a, int b, int c) {
    this.setPoints(a, b, c);
  }
  
  public void setPoints(int a, int b, int c) {
    this.p1 = a;
    this.p2 = b;
    this.p3 = c;
  }
}
