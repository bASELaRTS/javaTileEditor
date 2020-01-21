package simple3d;

import engine.Size;
import engine.math.Vector3;

public class Camera {
  private Size m_size;  
  private Vector3 m_position;
  private Vector3 m_target;
  
  public Camera(int width, int height) {
    this.m_size = new Size(width,height);
    this.m_position = new Vector3(0,0,-10);
    this.m_target = new Vector3(0,0,0);
  }
  
  public Size getSize() {return this.m_size;}
  public Vector3 getPosition() {return this.m_position;}
  public Vector3 getTarget() {return this.m_target;}
}
