package Helper;

public class Collision {
  public static boolean boxbox(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
    boolean h = ((x1+w1)>x2)&&(x1<(x2+w2));
    boolean v = ((y1+h1)>y2)&&(y1<(y2+h2));
    return (h&&v);
  }
}
