package invaders;


import java.awt.image.BufferedImage;

import engine.Engine;
import engine.GFrame;
import engine.LayoutManager;
import engine.graphics.Font;

public class Invaders extends Engine {
  public static int SCENE_SPLASH = 0;
  public static int SCENE_MAIN = 1;
  public static int SCENE_WIN = 2;
  public static int SCENE_LOSE = 3;
  
  private LayoutManager[] m_sprites;
  private Font m_font;
  private SceneMain m_sceneMain;
  
  public Invaders() {
    super("Invaders",160,120,2);    
   
    this.m_sprites = new LayoutManager[2];
        
    this.m_sprites[0] = new LayoutManager();
    this.m_sprites[0].load("data/invaders/invader.layout");
    this.m_sprites[0].setImage(Helper.Image.loadFromFile("data/invaders/invader.png"));
    
    this.m_sprites[1] = new LayoutManager();
    this.m_sprites[1].load("data/invaders/action1.layout");
    this.m_sprites[1].setImage(Helper.Image.loadFromFile("data/invaders/action1.png"));

    Font font;
    LayoutManager layoutManager;
    BufferedImage image;
    
    layoutManager = new LayoutManager();
    layoutManager.load("data/invaders/font.layout");
    image = Helper.Image.loadFromFile("data/invaders/font.png");
    
    font = new Font();
    font.load(layoutManager, image);
    this.m_font = font;
    
    //this.setGraphics(new engine.graphics.GraphicsPixels(this.getWidth(),this.getHeight()));
    this.setGraphics(new engine.graphics.GraphicsAWT(this.getWidth(), this.getHeight()));
    
    this.getScenes().add(new SceneSplashScreen(this));
    
    this.m_sceneMain = new SceneMain(this);
    this.getScenes().add(this.m_sceneMain);
    
    this.getScenes().add(new SceneWin(this));
    this.getScenes().add(new SceneLose(this));
  }
  
  public LayoutManager getSprites(int index) {return this.m_sprites[index];}
  public Font getFont() {return this.m_font;}
  public SceneMain getSceneMain() {return this.m_sceneMain;}
    
  public static void main(String[] args) {
    new GFrame(new Invaders());
  }
}
