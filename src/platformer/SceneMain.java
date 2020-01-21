package platformer;

import engine.Engine;
import engine.EntityManager;
import engine.Keyboard;
import engine.Keyboard.Key;
import engine.graphics.Color;
import engine.Scene;

public class SceneMain extends Scene {
  private EntityManager m_entities;
  
  public SceneMain(Engine engine) {
    super(engine);
    
    this.m_entities = new EntityManager(this.getEngine());
          
    // entities
    this.getEntities().add(new Player(this.getEngine()));
   
    // console window
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
    
    int color = Color.red.getColor();
    this.getEngine().getGraphics().drawLine(0, 0, this.getEngine().getWidth(), this.getEngine().getHeight(), color);
    this.getEngine().getGraphics().drawLine(this.getEngine().getWidth(), 0, 0, this.getEngine().getHeight(), color);
    this.getEngine().getGraphics().drawCircle(160, 120, 100, color);
  }
  
  public EntityManager getEntities() {return this.m_entities;}
}
