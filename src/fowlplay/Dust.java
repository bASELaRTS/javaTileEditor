package fowlplay;

import engine.Engine;
import engine.Entity;
import engine.graphics.Color;
import engine.graphics.IGraphics;
import engine.math.Vector3;

public class Dust extends Entity {
  private long m_timestamp;
  private int m_lifetime;
  private int m_radius;
  
  public Dust(Engine engine, int lifeTime) {
    super(engine);
    
    this.setName("dust");
    this.getSize().setSize(8, 8);
    
    this.m_timestamp = engine.getTimer().getTimestamp();
    this.m_lifetime = lifeTime;
    this.m_radius = 8;
  }
  
  public void update() {
    long ms = this.getEngine().getTimer().getTimestamp();
    double factor = ((ms-this.m_timestamp)/(double)this.m_lifetime) * 8.0;
    this.m_radius = (int)(8.0-factor);
    if (this.m_radius<=0) {
      this.setVisible(false);
      this.setRemove(true);
    }
  }
  
  public void paint() {
    IGraphics graphics = this.getEngine().getGraphics();
    SceneMain scene = (SceneMain)this.getEngine().getScenes().elementAt(0);
    Vector3 v = new Vector3();
    Camera camera = (Camera)scene.getEntities().find("camera");
    
    Vector3.subtract(this.getPosition(), camera.getPosition(), v);
    
    int x = (int)v.x;
    int y = (int)v.y;
    graphics.fillCircle(x, y, this.m_radius, Color.fromARGB(128, 255, 255, 255));
  }
  
  public void setLifeTime(int i) {this.m_lifetime=i;}
}
