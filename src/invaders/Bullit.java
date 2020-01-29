package invaders;

import java.awt.image.BufferedImage;

import Helper.Collision;
import engine.Engine;
import engine.Entity;

public class Bullit extends Actor {
  private long m_frameTimestamp;
  private int m_frameIndex;
  private int m_frameDelay;
  private int m_spriteIndex;
  
  public Bullit(Engine engine) {
    super(engine);
    this.setName("bullit");
    this.getGravity().setCoordinates(0, 0, 0);
    this.getSize().setSize(6, 9);
    this.getSpeedMin().setCoordinates(-1, -1, -1);
    
    this.m_frameTimestamp = engine.getTimer().getTimestamp();
    this.m_frameIndex = 0;
    this.m_frameDelay = 150;
  }
  
  public void update() {
    this.getAcceleration().y = -5;
    
    super.update();
    
    if (this.getPosition().y<-this.getSize().getHeight()) {
      this.setRemove(true);
    }
    
    this.animate();
    
    int x1 = (int)this.getPosition().x;
    int y1 = (int)this.getPosition().y;
    int w1 = (int)this.getSize().getWidth();
    int h1 = (int)this.getSize().getHeight();
    
    SceneMain scene = ((Invaders)this.getEngine()).getSceneMain();
    for(int i=0;i<scene.getEntities().count();i++) {
      Entity entity = scene.getEntities().elementAt(i);
      if (entity.getName().equals("enemy")) {
        int x2 = (int)entity.getPosition().x;
        int y2 = (int)entity.getPosition().y;
        int w2 = (int)entity.getSize().getWidth();
        int h2 = (int)entity.getSize().getHeight();
        
        if (Collision.boxbox(x1, y1, w1, h1, x2, y2, w2, h2)) {
          this.setRemove(true);
          entity.setRemove(true);
          i = scene.getEntities().count();
        }
      }
    }
  }
  
  private void animate() {
    long ms = this.getEngine().getTimer().getTimestamp();
    if ((ms-this.m_frameTimestamp)>=this.m_frameDelay) {
      this.m_frameTimestamp = ms;
      this.m_frameIndex = (this.m_frameIndex+1)%2;
    }    
  }
  
  public void paint() {
    int w = this.getSize().getWidth();
    int h = this.getSize().getHeight();
    int x = (int)(this.getPosition().x);
    int y = (int)(this.getPosition().y);
    BufferedImage image = ((Invaders)this.getEngine()).getSprites(1).getBlock(this.m_spriteIndex + this.m_frameIndex).getImage();
    this.getEngine().getGraphics().drawImage(image, x, y, w, h);
  }
}
