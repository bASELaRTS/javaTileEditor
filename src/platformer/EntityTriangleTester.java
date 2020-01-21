package platformer;

import engine.Engine;
import engine.Entity;
import engine.graphics.Color;

public class EntityTriangleTester extends Entity {
  private int[] x;  
  private int[] y;
  private int[] xd;
  private int[] yd;
  
  public EntityTriangleTester(Engine engine) {
    super(engine);
    
    x = new int[3];
    y = new int[3];
    xd = new int[3];
    yd = new int[3];
    
    x[0] = (int)(engine.getWidth()*0.5);
    y[0] = (int)(engine.getHeight()*0.5);
    xd[0] = 1;
    yd[0] = 0;
    
    x[1] = 0;
    y[1] = (int)(engine.getHeight()*0.5);
    xd[1] = 0;
    yd[1] = -1;
    
    x[2] = (int)(engine.getWidth());
    y[2] = (int)(engine.getHeight());    
    xd[2] = -1;
    yd[2] = 0;
  }
  
  public void update() {
    int i;

    for(i=0;i<3;i++) {
      x[i]+=xd[i];
      y[i]+=yd[i];
      if (x[i]>=this.getEngine().getWidth()) {
        x[i] = this.getEngine().getWidth()-1;
        xd[i] = 0;
        yd[i] = 1;
      }
      if (y[i]>=this.getEngine().getHeight()) {
        y[i] = this.getEngine().getHeight()-1;
        xd[i] = -1; 
        yd[i] = 0;
      }
      if (x[i]<0) {
        x[i] = 0;
        xd[i] = 0;
        yd[i] = -1;
      }
      if (y[i]<0) {
        y[i] = 0;
        xd[i] = 1;
        yd[i] = 0;
      }
    }
  }
  
  public void paint() {
    this.getEngine().getGraphics().fillTriangle(
        x[0],y[0],
        x[1],y[1],
        x[2],y[2],
        Color.white.getColor()
    );
  }
}
