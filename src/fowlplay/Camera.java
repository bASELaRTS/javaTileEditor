package fowlplay;

import engine.Engine;
import engine.Entity;
import engine.math.Vector3;

public class Camera extends Entity {
  private Vector3 m_offset;
  
  public Camera(Engine engine) {
    super(engine);
        
    this.setName("camera");
    this.getSize().setSize(engine.getWidth(),engine.getHeight());
        
    this.m_offset = new Vector3(this.getSize().getWidth()*0.5,this.getSize().getHeight()*0.5,0);
  }
  
  public void update() {
    Fowlplay engine = (Fowlplay)this.getEngine();
    SceneMain scene = (SceneMain)engine.getScenes().elementAt(0);
    Entity entity = scene.getEntities().find("player");
    Vector3 s = new Vector3();
    Vector3 v = new Vector3();
    Tilemap map = (Tilemap)scene.getEntities().find("tilemap");
    s.setCoordinates((int)map.getWidth()*map.getTileWidth(),(int)map.getHeight()*map.getTileHeight(),0);
    
    if (entity!=null) {
      Vector3.subtract(entity.getPosition(), this.m_offset, v);
      if (v.x<0) v.x = 0;
      if (v.x>(s.x-engine.getWidth())) v.x=(s.x-engine.getWidth());
      if (v.y<0) v.y = 0;      
      if (v.y>(s.y-engine.getHeight())) v.y=(s.y-engine.getHeight());
      this.getPosition().setVector(v);
    }
  }
}
