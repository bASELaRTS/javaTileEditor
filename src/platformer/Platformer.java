package platformer;

import engine.Engine;
import engine.GFrame;
//import engine.GraphicsAWT;
import engine.GraphicsPixels;

public class Platformer extends Engine {
  public Platformer() {
    super("Platformer",320,240);    
   
    //this.setGraphics(new GraphicsAWT(this.getWidth(),this.getHeight()));
    this.setGraphics(new GraphicsPixels(this.getWidth(),this.getHeight()));
    
    this.getScenes().add(new SceneMain(this));
  }
  
  public static void main(String[] args) {
    new GFrame(new Platformer());
  }
}
