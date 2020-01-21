package engine.math;

public class Vector3 {
  public double x;
  public double y;
  public double z;
  
  public Vector3(){
    this.setCoordinates(0,0,0);
  }
  
  public Vector3(Vector3 v) {
    this.setVector(v);
  }
  
  public Vector3(double dx, double dy, double dz){
    this.setCoordinates(dx, dy, dz);
  }
  
  public void setCoordinates(double dx, double dy, double dz){
    this.x = dx;
    this.y = dy;
    this.z = dz;
  }
  
  public void setVector(Vector3 v){
    this.setCoordinates(v.x,v.y,v.z);
  }
  
  public static void add(Vector3 v1, Vector3 v2, Vector3 v3){
    v3.x = v1.x + v2.x;
    v3.y = v1.y + v2.y;
    v3.z = v1.z + v2.z;
  }
  
  public static void subtract(Vector3 v1, Vector3 v2, Vector3 v3){
    v3.x = v1.x - v2.x;
    v3.y = v1.y - v2.y;
    v3.z = v1.z - v2.z;
  }

  public static void multiply(Vector3 v1, Vector3 v2, Vector3 v3){
    v3.x = v1.x * v2.x;
    v3.y = v1.y * v2.y;
    v3.z = v1.z * v2.z;
  }
  
  public static void multiply(Vector3 v1, double scalar, Vector3 v2){
    v2.x = v1.x * scalar;
    v2.y = v1.y * scalar;
    v2.z = v1.z * scalar;
  }
}