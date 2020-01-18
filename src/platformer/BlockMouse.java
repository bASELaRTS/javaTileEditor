package platformer;

import engine.Color;
import engine.Engine;
import engine.Entity;
import engine.IGraphics;
import engine.Mouse;
import engine.Vector3;

public class BlockMouse extends Entity {
  private Color m_color;
  
  public BlockMouse(Engine engine) {
    super(engine);
    this.m_color = new Color(255,255,0);
    this.getSize().setSize(16, 16);
    this.getPosition().setVector(
        ((this.getEngine().getWidth()-this.getSize().getWidth())*0.5),
        ((this.getEngine().getHeight()-this.getSize().getHeight())*0.5),
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
    int w = (int)(this.getSize().getWidth());
    int h = (int)(this.getSize().getHeight());    
    int x = (int)(this.getPosition().x-(w*0.5));
    int y = (int)(this.getPosition().y-(h*0.5));
    graphics.fillRect(x, y, w, h, this.m_color.getColor());
  }
}
