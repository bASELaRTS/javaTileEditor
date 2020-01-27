package invaders;

import engine.Engine;
import engine.Entity;
import engine.graphics.Color;
import engine.graphics.IGraphics;
import engine.math.Vector3;

public class Stars extends Entity {
  private Star[] m_stars;
  
  public Stars(Engine engine) {
    super(engine);
    
    this.m_stars = new Star[100];
    Star star;
    for(int i=0;i<this.m_stars.length;i++) {
      star = new Star();
      star.getPosition().setCoordinates(Math.random()*engine.getWidth(), Math.random()*engine.getHeight(), (Math.random()*2)+1);
      star.getSpeed().setCoordinates(0, (3 - star.getPosition().z)*0.5, 0);
      this.m_stars[i] = star;
    }
  }
  
  public void update() {
    int i;
    Star star;
    Vector3 v = new Vector3();
    for(i=0;i<this.m_stars.length;i++) {
      star = this.m_stars[i];
      
      Vector3.add(star.getPosition(), star.getSpeed(), v);
      star.getPosition().setVector(v);
      
      if (star.getPosition().y>this.getEngine().getHeight()) {
        star.getPosition().x = Math.random()*this.getEngine().getWidth();
        star.getPosition().y = -1;
      }
    }
  }
  
  public void paint() {
    int i;
    int c;
    int x,y;
    Star star;
    Color color = new Color();
    IGraphics graphics = this.getEngine().getGraphics();
    
    for(i=0;i<this.m_stars.length;i++) {
      star = this.m_stars[i];
      x = (int)star.getPosition().x;
      y = (int)star.getPosition().y;
      c = (int)(255 * (3/(3-star.getPosition().z)));
      color.setColor(c,c,c);
      graphics.setPixel(x, y, color.getColor());
    }
  }
  
  public class Star {
    private Vector3 m_position;
    private Vector3 m_speed;
    
    public Star() {
      this.m_position = new Vector3();
      this.m_speed = new Vector3();
    }
    
    public Vector3 getPosition() {return this.m_position;}
    public Vector3 getSpeed() {return this.m_speed;}
  }
}
