package fowlplay;

import engine.Map;
import engine.graphics.IGraphics;
import engine.math.Vector3;

import java.awt.image.BufferedImage;

import engine.Engine;
import engine.LayoutManager;
import engine.LayoutManager.Block;

public class Tilemap extends Map {
  private LayoutManager m_layoutManager;
  
  public Tilemap(Engine engine) {
    super(engine);
    this.setName("tilemap");
    
    LayoutManager layoutManager = new LayoutManager();
    layoutManager.load("data/tiles/tiles.layout");
    layoutManager.setImage(Helper.Image.loadFromFile("data/tiles/tiles.png"));
    this.m_layoutManager = layoutManager;
  } 
 
  public void paintTile(int tile, int x, int y, int tw, int th) {
    IGraphics graphics = this.getEngine().getGraphics(); 
    SceneMain scene = (SceneMain)this.getEngine().getScenes().elementAt(0);    
    Vector3 v = new Vector3();
    Camera camera = (Camera)scene.getEntities().find("camera");
    if (tile>=0) {
      Vector3.subtract(new Vector3(x,y,0), camera.getPosition(), v);
      Block block = this.m_layoutManager.getBlock(tile);
      if (block!=null) {
        BufferedImage image = block.getImage();
        graphics.drawImage(image, (int)v.x, (int)v.y, tw, th);
      } else {
        graphics.fillRect((int)v.x, (int)v.y, tw, th, engine.graphics.Color.fromARGB(255, 64, 192, 0));
      }      
    }
  }
}
