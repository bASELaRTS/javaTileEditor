package invaders;

import java.awt.image.BufferedImage;

import engine.Engine;
import engine.Scene;

public class SceneSplashScreen extends Scene {
  private BufferedImage m_logo;
  private int m_logoX;
  private int m_logoY;
  private long m_splashTimestamp;
  
  public SceneSplashScreen(Engine engine) {
    super(engine);
    
    this.m_logo = Helper.Image.loadFromFile("data/invaders/baselarts_logo.png");
    this.m_logoX = (int)((this.getEngine().getWidth()-this.m_logo.getWidth())*0.5);
    this.m_logoY = (int)((this.getEngine().getHeight()-this.m_logo.getHeight())*0.5);
    
    this.m_splashTimestamp = engine.getTimer().getTimestamp();
  }
  
  public void update() {
    long ms = this.getEngine().getTimer().getTimestamp();
    
    if ((ms-this.m_splashTimestamp)>=2000) {
      this.getEngine().getScenes().setActiveSceneIndex(1);
    }
  }

  public void paint() {
    this.getEngine().getGraphics().drawImage(this.m_logo, this.m_logoX, this.m_logoY, this.m_logo.getWidth(), this.m_logo.getHeight());
  }
}
