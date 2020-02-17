package fowlplay;

import engine.Engine;
import engine.Entity;
import engine.graphics.Color;
import engine.graphics.IGraphics;
import engine.math.Vector3;

public class Coin extends Entity {
  private Animation m_animation;
  
  public Coin(Engine engine,int x, int y) {
    super(engine);
    
    this.setName("coin");
    this.getSize().setSize(8, 8);
    this.getPosition().setCoordinates(x, y, 0);
    
    this.m_animation = new Animation(engine);
    this.m_animation.add(new AnimationFrame(100,0));
    this.m_animation.add(new AnimationFrame(100,1));
    this.m_animation.add(new AnimationFrame(100,2));
    this.m_animation.add(new AnimationFrame(100,3));
  }
  
  public void update() {
    this.m_animation.update();
  }
  
  public void paint() {
    IGraphics graphics = this.getEngine().getGraphics();
    Fowlplay engine = (Fowlplay)this.getEngine();
    SceneMain scene = (SceneMain)engine.getScenes().elementAt(0);
    Camera camera = (Camera)scene.getEntities().find("camera");
    Vector3 v = new Vector3();
    
    Vector3.subtract(this.getPosition(), camera.getPosition(), v);
    AnimationFrame frame = this.m_animation.getFrame();
    
    int x = (int)v.x;
    int y = (int)v.y;
    int w = this.getSize().getWidth();
    //int h = this.getSize().getHeight();
    //graphics.fillRect(x, y, w, h, Color.fromARGB(255, 255, 255, 0));
    graphics.fillCircle(x, y, w, Color.fromARGB(255, 255, 192, 0));
    //graphics.drawString(""+this.m_frame, x, y, engine.graphics.Color.fromARGB(255, 255, 255, 255));
    graphics.drawFont(""+frame.getImageIndex(), x, y, engine.getFont());
  }
}
