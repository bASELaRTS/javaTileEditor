package platformer;

import engine.Engine;
import engine.Entity;
import engine.Texture;

public class EntityTexture extends Entity {
  private Texture m_texture;
  private double m_zoom;
  private double m_direction;
  private long m_timestamp;
  
  public EntityTexture(Engine engine, Texture texture) {
    super(engine);
    this.m_texture = texture;
    this.getSize().setSize(texture.getWidth(), texture.getHeight());
    
    this.m_direction = 2.0;
    this.m_zoom = 1.0;
    this.m_timestamp = this.getEngine().getTimer().getTimestamp();
  }
  
  public void update() {
    long ms = this.getEngine().getTimer().getTimestamp();
    if ((ms-this.m_timestamp)>=50) {
      this.m_timestamp = ms;
      this.m_zoom *= this.m_direction;
      if (this.m_zoom<0.1) {
        this.m_zoom = 0.1;
        this.m_direction=1.25;
      } else if (this.m_zoom>8) {
        this.m_zoom = 8.0;
        this.m_direction=0.5;
      }
    }
    this.getSize().setSize((int)(this.m_texture.getWidth()*this.m_zoom), (int)(this.m_texture.getHeight()*this.m_zoom));
    
    this.getPosition().setVector((this.getEngine().getWidth() - this.getSize().getWidth())*0.5,(this.getEngine().getHeight() - this.getSize().getHeight())*0.5, 0);
  }

  public void paint() {
    int x = (int)this.getPosition().x;
    int y = (int)this.getPosition().y;
    int w = this.getSize().getWidth();
    int h = this.getSize().getHeight();
    this.getEngine().getGraphics().drawTexture(this.m_texture, x, y, w, h);
  }
   
}
