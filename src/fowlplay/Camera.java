package fowlplay;

import engine.Engine;
import engine.Entity;
import engine.math.Vector3;

public class Camera extends Entity {
  private Vector3 m_offset;
  
  private Entity m_entity;
  
  public Camera(Engine engine) {
    super(engine);
        
    this.setName("camera");
    this.setVisible(false);
    this.getSize().setSize(engine.getWidth(),engine.getHeight());
        
    this.m_offset = new Vector3(this.getSize().getWidth()*0.5,this.getSize().getHeight()*0.5,0);
    this.setEntity(null);
  }
  
  public void update() {
    Fowlplay engine = (Fowlplay)this.getEngine();
    SceneMain scene = (SceneMain)engine.getScenes().elementAt(0);
    Entity entity = this.getEntity();
    Vector3 s = new Vector3();
    Vector3 v = new Vector3();
    
    if (entity!=null) {
      Tilemap map = (Tilemap)scene.getEntities().find("tilemap");
      s.setCoordinates((int)map.getWidth()*map.getTileWidth(),(int)map.getHeight()*map.getTileHeight(),0);

      Vector3.subtract(entity.getPosition(), this.m_offset, v);
      if (v.x<0) v.x = 0;
      if (v.x>(s.x-engine.getWidth())) v.x=(s.x-engine.getWidth());
      if (v.y<0) v.y = 0;      
      if (v.y>(s.y-engine.getHeight())) v.y=(s.y-engine.getHeight());
      this.getPosition().setVector(v);
    }
  }

  public void setEntity(Entity entity) {this.m_entity=entity;}
  public Entity getEntity() {return this.m_entity;}
}
