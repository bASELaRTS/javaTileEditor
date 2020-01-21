package platformer;

import engine.Engine;
import engine.Entity;
import engine.math.Vector3;

public class Actor extends Entity {  
  private Vector3 m_gravity;
  private Vector3 m_acceleration;
  private Vector3 m_speed;
  
  public Actor(Engine engine) {
    super(engine);
    
    this.m_gravity = new Vector3(0,1,0);
    this.m_speed = new Vector3(0,0,0);
    this.m_acceleration = new Vector3(0,0,0);
  }
  
  public void update() {
    super.update();
    
    Vector3 acceleration = new Vector3(this.getAcceleration());
    Vector3 position = new Vector3(this.getPosition());
    Vector3 speed = new Vector3(this.getSpeed());
    Vector3 v1 = new Vector3();    
    
    // update acceleration = current accerlation + gravity
    Vector3.add(acceleration, this.getGravity(), v1);
    acceleration.setVector(v1);
        
    // update speed
    Vector3.add(acceleration, speed, v1);
    speed.setVector(v1);
    
    // terminal velocity
    if (speed.x>5) speed.x=5;
    if (speed.y>5) speed.y=5;    
    
    // apply force
    Vector3.add(speed, position, v1);
    position.setVector(v1);    
    
    this.getSpeed().setVector(speed);
    this.getPosition().setVector(position);
  }
  
  public Vector3 getGravity() {return this.m_gravity;}
  public Vector3 getSpeed() {return this.m_speed;}
  public Vector3 getAcceleration() {return this.m_acceleration;}
}
