package fowlplay;


import engine.Engine;
import engine.GFrame;
import engine.LayoutManager;
import engine.graphics.Font;

public class Fowlplay extends Engine {
  private Font m_font;
  
  public Fowlplay() {
    super("FowlPlay",320,240,1);    
   
    LayoutManager layoutManager = new LayoutManager();
    layoutManager.load("data/fowlplay/font.layout");
    this.m_font = new Font();
    this.m_font.load(layoutManager, Helper.Image.loadFromFile("data/fowlplay/font.png"));
    
    //this.setGraphics(new engine.graphics.GraphicsPixels(this.getWidth(),this.getHeight()));
    this.setGraphics(new engine.graphics.GraphicsAWT(this.getWidth(), this.getHeight()));
    
    this.getScenes().add(new SceneMain(this));
  }
    
  public static void main(String[] args) {
    new GFrame(new Fowlplay());
  }
  
  public Font getFont() {return this.m_font;}
}
