package platformer;

import engine.Engine;
import engine.Mouse;
import engine.graphics.Color;

public class Player extends Actor {
  public Player(Engine engine) {
    super(engine);
    this.setName("player");
    this.getSize().setSize(16, 16);
  }
  
  
  public void update() {
    Mouse mouse = this.getEngine().getInput().getMouse();
    
    if (mouse.getLeft()) {
      this.getSpeed().setCoordinates(0, 0, 0);
      this.getPosition().setCoordinates(mouse.getX(), mouse.getY(), 0);
      this.getAcceleration().setCoordinates(5, -15, 0);
    }
    
    super.update();
    
    this.getAcceleration().setCoordinates(0, 0, 0);
  }
  
  public void paint() {
    int x = (int)this.getPosition().x;
    int y = (int)this.getPosition().y;
    int w = this.getSize().getWidth();
    int h = this.getSize().getHeight();
    
    int color = Color.white.getColor();
    this.getEngine().getGraphics().fillRect(x, y, w, h, color);
  }
}
