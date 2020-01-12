package platformer;

import engine.Engine;
import engine.EntityManager;
import engine.Keyboard;
import engine.Keyboard.Key;
import engine.Scene;

public class SceneMain extends Scene {
  private EntityManager m_entities;
  
  public SceneMain(Engine engine) {
    super(engine);
    
    this.m_entities = new EntityManager(this.getEngine());
    
    
    
    this.getEntities().add(new BlockKeyboard(this.getEngine()));
    this.getEntities().add(new BlockMouse(this.getEngine()));
   
    this.getEntities().add(new Console(this.getEngine(),19));
  }
  
  public void update() {
  	Console console = (Console)this.getEntities().find("console");
  	if (console!=null) {
  		Keyboard keyboard = this.getEngine().getInput().getKeyboard();
  		Key key = keyboard.getKeyPressed();
  		if (key!=null) {
  			console.println("KeyPressed: "+key.getKeyCode());
  		}
  	}
  	
    this.getEntities().update();
  }
  
  public void paint() {
    this.getEntities().paint();
  }
  
  public EntityManager getEntities() {return this.m_entities;}
}
