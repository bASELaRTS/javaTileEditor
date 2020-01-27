package invaders;

import engine.Engine;
import engine.Entity;
import engine.math.Vector3;

public class Actor extends Entity {  
  private Vector3 m_gravity;
  private Vector3 m_acceleration;
  private Vector3 m_speed;
  private Vector3 m_speedMax;
  private Vector3 m_speedMin;
  
  public Actor(Engine engine) {
    super(engine);
    
    this.m_gravity = new Vector3(0,1,0);
    this.m_speed = new Vector3(0,0,0);
    this.m_acceleration = new Vector3(0,0,0);
    
    this.m_speedMax = new Vector3(5,5,5);
    this.m_speedMin = new Vector3(-5,-5,-5);
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
    if (speed.x<this.m_speedMin.x) speed.x=this.m_speedMin.x;
    if (speed.x>this.m_speedMax.x) speed.x=this.m_speedMax.x;
    if (speed.y<this.m_speedMin.y) speed.y=this.m_speedMin.y;
    if (speed.y>this.m_speedMax.y) speed.y=this.m_speedMax.y;    
    
    // apply force
    Vector3.add(speed, position, v1);
    position.setVector(v1);    
    
    this.getSpeed().setVector(speed);
    this.getPosition().setVector(position);
  }
  
  public Vector3 getGravity() {return this.m_gravity;}
  public Vector3 getSpeed() {return this.m_speed;}
  public Vector3 getAcceleration() {return this.m_acceleration;}
  public Vector3 getSpeedMax() {return this.m_speedMax;}
  public Vector3 getSpeedMin() {return this.m_speedMin;}
}
