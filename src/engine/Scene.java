package engine;

public class Scene {
  private Engine m_engine;
  private String m_name;
  
  public Scene(Engine engine) {
    this.setEngine(engine);
    this.setName("");
  }
  
  public void update() {}
  public void paint() {}
  
  public void setName(String name) {this.m_name=name;}
  public String getName() {return this.m_name;}
  
  public void setEngine(Engine engine) {this.m_engine=engine;}
  public Engine getEngine() {return this.m_engine;}
}
