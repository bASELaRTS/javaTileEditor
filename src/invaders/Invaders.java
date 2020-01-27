package invaders;


import engine.Engine;
import engine.GFrame;
import engine.LayoutManager;

public class Invaders extends Engine {
  private LayoutManager[] m_sprites;
  
  public Invaders() {
    super("Invaders",160,120,2);    
   
    this.m_sprites = new LayoutManager[2];
    
    
    this.m_sprites[0] = new LayoutManager();
    this.m_sprites[0].load("data/invaders/invader.layout");
    this.m_sprites[0].setImage(Helper.Image.loadFromFile("data/invaders/invader.png"));
    
    this.m_sprites[1] = new LayoutManager();
    this.m_sprites[1].load("data/invaders/action1.layout");
    this.m_sprites[1].setImage(Helper.Image.loadFromFile("data/invaders/action1.png"));

    //this.setGraphics(new engine.graphics.GraphicsPixels(this.getWidth(),this.getHeight()));
    this.setGraphics(new engine.graphics.GraphicsAWT(this.getWidth(), this.getHeight()));
    
    this.getScenes().add(new SceneMain(this));
  }
  
  public LayoutManager getSprites(int index) {return this.m_sprites[index];}
    
  public static void main(String[] args) {
    new GFrame(new Invaders());
  }
}
