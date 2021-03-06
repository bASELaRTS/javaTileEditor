package engine;

import engine.math.Vector3;

public class Entity {
  private Engine m_engine;
  private Scene m_scene;
  private String m_name;
  private Vector3 m_position;
  private Size m_size;
  private boolean m_visible;
  private boolean m_remove;
  
  public Entity(Engine engine){
    this.setEngine(engine);
    this.setScene(null);
    
    this.m_position = new Vector3();
    this.m_size = new Size();
    
    this.setName("Entity");
    this.setVisible(true);
    this.setRemove(false);
  }
  
  public void update(){}
  public void paint(){}
  
  public void setName(String name) {this.m_name=name;}
  public String getName() {return this.m_name;}
  public void setEngine(Engine engine) {this.m_engine=engine;}
  public Engine getEngine() {return this.m_engine;}
  public void setScene(Scene scene) {this.m_scene=scene;}
  public Scene getScene() {return this.m_scene;}
  public Vector3 getPosition(){return this.m_position;}
  public int getWidth() {return this.m_size.getWidth();}
  public int getHeight() {return this.m_size.getHeight();}
  public Size getSize(){return this.m_size;}
  public void setVisible(boolean b) {this.m_visible=b;}
  public boolean getVisible() {return this.m_visible;}
  public void setRemove(boolean b) {this.m_remove=b;}
  public boolean getRemove() {return this.m_remove;}
}