package engine;

import java.awt.Graphics;

public class Engine {
  private String m_name;
  private Timer m_timer;
  private IGraphics m_graphics;
  private Input m_input;
  private SceneManager m_scenes;
  private Size m_size;
  
  public Engine(String name, int width, int height){
    this.m_size = new Size(width,height);
    this.m_timer = new Timer();
    this.m_scenes = new SceneManager();
    this.m_input = new Input();
    
    this.setName(name);
    this.setGraphics(new GraphicsAWT(this.getWidth(),this.getHeight()));
  }
  
  public void update(){
    this.getTimer().update();        
    this.m_scenes.update();
  }
  
  public void paint(Graphics g){
    this.getGraphics().clear();
    this.m_scenes.paint();
  }
  
  public void setName(String name) {this.m_name=name;}
  public String getName() {return this.m_name;}
  public int getWidth() {return this.m_size.getWidth();}
  public int getHeight() {return this.m_size.getHeight();}
  public void setGraphics(IGraphics graphics) {this.m_graphics=graphics;}
  public IGraphics getGraphics() {return this.m_graphics;}
  public Timer getTimer(){return this.m_timer;}
  public Input getInput() {return this.m_input;}
  public SceneManager getScenes(){return this.m_scenes;}
}