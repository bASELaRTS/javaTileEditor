package platformer;

import engine.Engine;
import engine.EntityManager;
import engine.Scene;

public class SceneMain extends Scene {
  private EntityManager m_entities;
  
  public SceneMain(Engine engine) {
    super(engine);
    
    this.m_entities = new EntityManager(this.getEngine());
    
    this.getEntities().add(new BlockKeyboard(this.getEngine()));
    this.getEntities().add(new BlockMouse(this.getEngine()));
  }
  
  public void update() {
    this.getEntities().update();
  }
  
  public void paint() {
    this.getEntities().paint();
  }
  
  public EntityManager getEntities() {return this.m_entities;}
}
