package platformer;

import java.awt.Color;
import java.awt.event.KeyEvent;

import engine.Engine;
import engine.Entity;
import engine.IGraphics;
import engine.Keyboard;
import engine.Vector3;

public class BlockKeyboard extends Entity {
  public BlockKeyboard(Engine engine) {
    super(engine);
    
    this.getSize().setVector(16, 16, 0);
    this.getPosition().setVector(
        ((this.getEngine().getWidth()-this.getSize().x)*0.5),
        ((this.getEngine().getHeight()-this.getSize().y)*0.5),
        0
    );
    
    Keyboard keyboard = this.getEngine().getInput().getKeyboard();
    keyboard.add(KeyEvent.VK_LEFT);
    keyboard.add(KeyEvent.VK_RIGHT);
    keyboard.add(KeyEvent.VK_UP);
    keyboard.add(KeyEvent.VK_DOWN);
  }
  
  public void update() {
    Keyboard keyboard = this.getEngine().getInput().getKeyboard();
    Vector3 position = new Vector3(this.getPosition());
    
    if (keyboard.isPressed()) {      
      if (keyboard.isPressed(KeyEvent.VK_LEFT)) {
        position.x--;
      } else if (keyboard.isPressed(KeyEvent.VK_RIGHT)) {
        position.x++;
      }
      
      if (keyboard.isPressed(KeyEvent.VK_UP)) {
        position.y--;
      } else if (keyboard.isPressed(KeyEvent.VK_DOWN)) {
        position.y++;
      }      
    }
    
    this.getPosition().setVector(position);
  }
  
  public void paint() {
    Engine engine = this.getEngine();
    IGraphics graphics = engine.getGraphics();
    int x = (int)(this.getPosition().x);
    int y = (int)(this.getPosition().y);
    int w = (int)(this.getSize().x);
    int h = (int)(this.getSize().y);    
    graphics.fillRect(x, y, w, h, Color.red);
  }
}
