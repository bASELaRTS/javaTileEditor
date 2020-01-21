package simple3d;

import java.util.Vector;

import engine.math.Vector3;

public class Mesh {
  private Vector<Vector3> m_points;
  private Vector<Vector3> m_pointsT;
  private Vector<MeshFace> m_faces;
  
  public Mesh() {
    this.m_points = new Vector<Vector3>();
    this.m_pointsT = new Vector<Vector3>();
    this.m_faces = new Vector<MeshFace>();
  }   
  
  public Vector<Vector3> getPoints(){return this.m_points;}
  public Vector<Vector3> getPointsT(){return this.m_pointsT;}
  public Vector<MeshFace> getFaces(){return this.m_faces;}
  
  
  //-=-=-=- static functions -=-=-=-=-
  public static Mesh cube() {
    Mesh mesh = new Mesh();
    
    /*    
      front    right    back     left      top    bottom
     0 --- 1  1 --- 5  5 --- 4  4 --- 0  4 --- 5  3 --- 2     
     | \   |  | \   |  | \   |  | \   |  | \   |  | \   |
     |   \ |  |   \ |  |   \ |  |   \ |  |   \ |  |   \ |
     3 --- 2  7 --- 6  6 --- 7  7 --- 3  0 --- 1  7 --- 6     
    */
    
    mesh.getPoints().add(new Vector3(-1, 1,-1)); 
    mesh.getPoints().add(new Vector3( 1, 1,-1)); 
    mesh.getPoints().add(new Vector3( 1,-1,-1)); 
    mesh.getPoints().add(new Vector3(-1,-1,-1)); 

    mesh.getPoints().add(new Vector3(-1, 1, 1));
    mesh.getPoints().add(new Vector3( 1, 1, 1));
    mesh.getPoints().add(new Vector3( 1,-1, 1));
    mesh.getPoints().add(new Vector3(-1,-1, 1));
    
    for(int i=0;i<mesh.getPoints().size();i++) {
      mesh.getPointsT().add(new Vector3(mesh.getPoints().elementAt(i)));
    }
    
    mesh.getFaces().add(new MeshFace(0,2,1));
    mesh.getFaces().add(new MeshFace(0,3,2));

    mesh.getFaces().add(new MeshFace(1,6,5));
    mesh.getFaces().add(new MeshFace(1,7,6));

    mesh.getFaces().add(new MeshFace(5,7,4));
    mesh.getFaces().add(new MeshFace(5,6,7));
    
    mesh.getFaces().add(new MeshFace(4,3,0));
    mesh.getFaces().add(new MeshFace(4,7,3));
    
    mesh.getFaces().add(new MeshFace(4,1,5));
    mesh.getFaces().add(new MeshFace(4,0,1));
    
    mesh.getFaces().add(new MeshFace(3,6,2));
    mesh.getFaces().add(new MeshFace(3,7,6));

    return mesh;
  }
}
