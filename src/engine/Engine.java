package engine;

import java.awt.Graphics;
import java.util.Vector;

public class Engine {
  private String m_name;
  private Timer m_timer;
  private IGraphics m_graphics;
  private Input m_input;
  private Vector<Scene> m_scenes;  
  private int m_width;
  private int m_height;
  
  public Engine(String name, int width, int height){
    this.m_timer = new Timer();
    this.m_scenes = new Vector<Scene>();
    this.m_input = new Input();
    
    this.setName(name);
    this.setSize(width, height);
    this.setGraphics(new GraphicsAWT(this.getWidth(),this.getHeight()));
  }
  
  public void update(){
    int i;
    Scene scene;

    this.getTimer().update();        
    
    for(i=0;i<this.getScenes().size();i++) {
      scene = this.getScenes().elementAt(i);
      scene.update();
    }
  }
  
  public void paint(Graphics g){
    int i;    
    Scene scene;
    
    this.getGraphics().clear();
    
    for(i=0;i<this.getScenes().size();i++) {
      scene = this.getScenes().elementAt(i);
      scene.paint();
    }
  }
  
  public void setName(String name) {this.m_name=name;}
  public String getName() {return this.m_name;}
  public void setWidth(int i) {this.m_width=i;}
  public int getWidth() {return this.m_width;}
  public void setHeight(int i) {this.m_height=i;}
  public int getHeight() {return this.m_height;}
  public void setSize(int w, int h) {
    this.setWidth(w);
    this.setHeight(h);
  }
  public void setGraphics(IGraphics graphics) {this.m_graphics=graphics;}
  public IGraphics getGraphics() {return this.m_graphics;}
  public Timer getTimer(){return this.m_timer;}
  public Input getInput() {return this.m_input;}
  public Vector<Scene> getScenes(){return this.m_scenes;}
}