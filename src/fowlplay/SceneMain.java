package fowlplay;

import engine.Engine;
import engine.EntityManager;
import engine.Scene;
import engine.graphics.Color;
import engine.graphics.Font;
import engine.graphics.IGraphics;

public class SceneMain extends Scene {
  private EntityManager m_entities;
  private Player m_player;
  
  public SceneMain(Engine engine) {
    super(engine);
    
    this.m_entities = new EntityManager(engine);
        
    this.initialize();
  }
  
  public void initialize() {
    Player player;
    player = new Player(this.getEngine());
    player.getPosition().setCoordinates(16, 16, 0);
    this.m_player = player;
    
    Tilemap map;
    map = new Tilemap(this.getEngine());
    map.load("data/fowlplay/tilemap.txt");
    
    Camera camera;
    camera = new Camera(this.getEngine()); 

    this.m_entities.clear();
    this.m_entities.add(camera);
    this.m_entities.add(map);
    this.m_entities.add(new Coin(this.getEngine(),134,32));
    this.m_entities.add(new Coin(this.getEngine(),155,32));
    this.m_entities.add(new Coin(this.getEngine(),176,32));
    this.m_entities.add(player);
    
    camera.setEntity(player);
  }
  
  public void update() {
    this.m_entities.update();
  }
  
  public void paint() {
    IGraphics graphics = this.getEngine().getGraphics();
    
    graphics.fillRect(0, 0, graphics.getWidth(), graphics.getHeight(), Color.fromARGB(255, 81, 194, 205));
    
    this.m_entities.paint();
    this.m_player.paint();
    
    Font font = ((Fowlplay)this.getEngine()).getFont();
    graphics.drawFont(""+this.getEngine().getTimer().getFPS(), 2, 2, font);
    graphics.drawFont(""+this.m_entities.count(), 2, 12, font);
  }
  
  public EntityManager getEntities() {return this.m_entities;}
}
