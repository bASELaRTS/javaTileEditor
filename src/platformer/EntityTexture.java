package platformer;

import engine.Engine;
import engine.Entity;
import engine.Texture;

public class EntityTexture extends Entity {
  private Texture m_texture;
  private double m_zoom;
  private double m_direction;
  private long m_timestamp;
  
  public EntityTexture(Engine engine) {
    super(engine);
    
    //this.m_texture = Texture.checkerBoard(128, 128, 2);
    this.m_texture = Texture.heart();
    
    this.getSize().setSize(this.m_texture.getWidth(), this.m_texture.getHeight());
    
    this.m_direction = 0;
    this.m_zoom = 1.0;
    this.m_timestamp = this.getEngine().getTimer().getTimestamp();
  }
  
  public void update() {
    long ms = this.getEngine().getTimer().getTimestamp();
    if ((ms-this.m_timestamp)>=5) {
      this.m_timestamp = ms;
      this.m_direction = (this.m_direction+1)%360;
      this.m_zoom = (Math.sin(Helper.Math.degToRad(this.m_direction))+1)*2;
      /*
      this.m_zoom *= this.m_direction;
      if (this.m_zoom<0.1) {
        this.m_zoom = 0.1;
        this.m_direction=1.25;
      } else if (this.m_zoom>8) {
        this.m_zoom = 8.0;
        this.m_direction=0.75;
      }
      /**/
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
