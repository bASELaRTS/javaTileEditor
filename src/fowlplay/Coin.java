package fowlplay;

import engine.Engine;
import engine.Entity;
import engine.graphics.Color;
import engine.graphics.IGraphics;
import engine.math.Vector3;

public class Coin extends Entity {
  private int m_frame;
  private int m_frameDelay;
  private long m_frameTimestamp;
  
  public Coin(Engine engine,int x, int y) {
    super(engine);
    
    this.setName("coin");
    this.getSize().setSize(8, 8);
    this.getPosition().setCoordinates(x, y, 0);
    
    this.m_frame = 0;
    this.m_frameDelay = 100;
    this.m_frameTimestamp = engine.getTimer().getTimestamp();
  }
  
  private void animate() {
    long ms = this.getEngine().getTimer().getTimestamp();
    if ((ms-this.m_frameTimestamp)>this.m_frameDelay) {
      this.m_frameTimestamp = ms;
      this.m_frame++;
      if (this.m_frame>=4) {
        this.m_frame=0;
      }
    }
  }
  
  public void update() {
    this.animate();
  }
  
  public void paint() {
    IGraphics graphics = this.getEngine().getGraphics();
    Fowlplay engine = (Fowlplay)this.getEngine();
    SceneMain scene = (SceneMain)engine.getScenes().elementAt(0);
    Camera camera = (Camera)scene.getEntities().find("camera");
    Vector3 v = new Vector3();
    
    Vector3.subtract(this.getPosition(), camera.getPosition(), v);
    
    int x = (int)v.x;
    int y = (int)v.y;
    int w = this.getSize().getWidth();
    //int h = this.getSize().getHeight();
    //graphics.fillRect(x, y, w, h, Color.fromARGB(255, 255, 255, 0));
    graphics.fillCircle(x, y, w, Color.fromARGB(255, 255, 192, 0));
    //graphics.drawString(""+this.m_frame, x, y, engine.graphics.Color.fromARGB(255, 255, 255, 255));
    graphics.drawFont(""+this.m_frame, x, y, engine.getFont());
  }
}
