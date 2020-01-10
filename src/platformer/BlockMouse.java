package platformer;

import java.awt.Color;

import engine.Engine;
import engine.Entity;
import engine.IGraphics;
import engine.Mouse;
import engine.Vector3;

public class BlockMouse extends Entity {
  public BlockMouse(Engine engine) {
    super(engine);
    
    this.getSize().setVector(16, 16, 0);
    this.getPosition().setVector(
        ((this.getEngine().getWidth()-this.getSize().x)*0.5),
        ((this.getEngine().getHeight()-this.getSize().y)*0.5),
        0
    );
  }
  
  public void update() {
    Mouse mouse = this.getEngine().getInput().getMouse();
    Vector3 position = new Vector3(this.getPosition());

    position.setVector(mouse.getX(), mouse.getY(), 0);
    if (position.x<0) position.x=0;
    if (position.y<0) position.y=0;
    if (position.x>=this.getEngine().getWidth()) position.x=this.getEngine().getWidth()-1;
    if (position.y>=this.getEngine().getHeight()) position.y=this.getEngine().getHeight()-1;
    
    this.getPosition().setVector(position);
  }
  
  public void paint() {
    Engine engine = this.getEngine();
    IGraphics graphics = engine.getGraphics();
    int w = (int)(this.getSize().x);
    int h = (int)(this.getSize().y);    
    int x = (int)(this.getPosition().x-(w*0.5));
    int y = (int)(this.getPosition().y-(h*0.5));
    graphics.fillRect(x, y, w, h, Color.yellow);
  }
}
