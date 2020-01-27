package invaders;

import java.awt.image.BufferedImage;

import engine.Engine;

public class Enemy extends Actor {
  private long m_frameTimestamp;
  private int m_frameDelay;
  private int m_frameIndex;
  private int m_spriteIndex;
    
  public Enemy(Engine engine, int x, int y, int w, int h) {
    super(engine);
   
    this.getPosition().setCoordinates(x, y, 0);
    this.getSize().setSize(w,h);
    this.getGravity().setCoordinates(0, 0, 0);
    
    this.setName("enemy");
    this.setFrameDelay(150);
    this.setFrameIndex(0);
    this.setSpriteIndex(2);
  }
  
  public void update() {
    super.update();
    
    this.animate();
  }
  
  public void paint() {
    int x = (int)this.getPosition().x;
    int y = (int)this.getPosition().y;
    int w = (int)this.getSize().getWidth();
    int h = (int)this.getSize().getHeight();
    
    int imageIndex = this.getSpriteIndex()+getFrameIndex();
    BufferedImage image = ((Invaders)this.getEngine()).getSprites(0).getBlock(imageIndex).getImage();
    
    this.getEngine().getGraphics().drawImage(image, x, y, w, h);
  }
  
  private void animate() {
    long ms = this.getEngine().getTimer().getTimestamp();
    
    if ((ms-this.m_frameTimestamp)>=this.m_frameDelay) {
      this.m_frameTimestamp = ms;      
      this.m_frameIndex = (this.m_frameIndex+1)%2;
    }
  }
  
  public void setFrameDelay(int delay) {this.m_frameDelay=delay;}
  public void setFrameIndex(int index) {this.m_frameIndex=index;}
  public int getFrameIndex() {return this.m_frameIndex;}
  public void setSpriteIndex(int index) {this.m_spriteIndex=index;}
  public int getSpriteIndex() {return this.m_spriteIndex;}
}
