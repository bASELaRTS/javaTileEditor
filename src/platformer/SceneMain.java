package platformer;

import java.util.Vector;

import engine.Engine;
import engine.Entity;
import engine.Scene;

public class SceneMain extends Scene {
  private Vector<Entity> m_entities;
  
  public SceneMain(Engine engine) {
    super(engine);
    
    this.m_entities = new Vector<Entity>();
    
    Block block = new Block(this.getEngine());
    this.getEntities().add(block);
  }
  
  public void update() {
    int i;
    Entity e;
    for(i=0;i<this.getEntities().size();i++) {
      e = this.getEntities().elementAt(i);
      e.update();
    }
  }
  
  public void paint() {
    int i;
    Entity e;
    for(i=0;i<this.getEntities().size();i++) {
      e = this.getEntities().elementAt(i);
      e.paint();
    }
  }
  
  public Vector<Entity> getEntities() {return this.m_entities;}
}
