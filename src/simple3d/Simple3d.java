package simple3d;

import java.awt.Graphics;
import java.util.Vector;

import engine.Engine;
import engine.GFrame;
import engine.graphics.GraphicsPixels;
import engine.math.Vector3;

public class Simple3d extends Engine {
  private Camera m_camera;
  private Vector<Mesh> m_meshes;
  
  public Simple3d() {
    super("simple3d",320,240,1);
    this.setGraphics(new GraphicsPixels(this.getWidth(),this.getHeight()));
    this.m_camera = new Camera(this.getWidth(),this.getHeight());
    this.m_meshes = new Vector<Mesh>();
    
    this.getMeshes().add(Mesh.cube());
  }
  
  public void update() {
    super.update();     
    
    for(int i=0;i<this.getMeshes().size();i++) {
      Mesh mesh = this.getMeshes().elementAt(i);
      this.meshToLocal(mesh);
      this.localToWorld(mesh);
      this.worldToCamera(mesh);
      this.cameraToViewport(mesh);
      this.viewportToScreen(mesh);
    }
  }

  public void paint(Graphics g) {
    super.paint(g);
  }
  
  public static void main(String[] args) {
    new GFrame(new Simple3d());
  }
  
  private void meshToLocal(Mesh mesh) {
    int i;
    for(i=0;i<mesh.getPoints().size();i++) {
      mesh.getPointsT().elementAt(i).setVector(mesh.getPoints().elementAt(i));
    }
  }
  
  private void localToWorld(Mesh mesh) {}
  
  private void worldToCamera(Mesh mesh) {
    int i;
    Vector3 v1 = new Vector3();
    Vector3 v2 = new Vector3();
    
    for(i=0;i<mesh.getPointsT().size();i++) {
      v1.setVector(mesh.getPointsT().elementAt(i));
      
      Vector3.subtract(v1, this.getCamera().getPosition(), v2);
      v1.setVector(v2);
      
      mesh.getPointsT().elementAt(i).setVector(v1);
    }
  }
  
  private void cameraToViewport(Mesh mesh) {
    
  }
  
  private void viewportToScreen(Mesh mesh) {
    
  }
  
  public Vector<Mesh> getMeshes(){return this.m_meshes;}
  public Camera getCamera() {return this.m_camera;}
}
