package fowlplay;

import engine.Engine;
import engine.EntityManager;
import engine.Scene;
import engine.graphics.Color;
import engine.graphics.IGraphics;

public class SceneMain extends Scene {
  private EntityManager m_entities;
  private Tilemap m_tilemap;
  private Player m_player;
  
  
  public SceneMain(Engine engine) {
    super(engine);
    
    this.m_entities = new EntityManager(engine);
    
    this.m_tilemap = new Tilemap(engine);
    this.m_tilemap.load("data/fowlplay/tilemap.txt");
    
    this.m_player = new Player(engine);
    
    this.initialize();
  }
  
  public void initialize() {
    this.m_player.getPosition().setCoordinates(16, 16, 0);
    
    this.m_entities.clear();
    this.m_entities.add(new Camera(this.getEngine()));
    this.m_entities.add(this.m_tilemap);
    this.m_entities.add(new Coin(this.getEngine(),134,32));
    this.m_entities.add(new Coin(this.getEngine(),155,32));
    this.m_entities.add(new Coin(this.getEngine(),176,32));
    this.m_entities.add(this.m_player);
  }
  
  public void update() {
    this.m_entities.update();
  }
  
  public void paint() {
    IGraphics graphics = this.getEngine().getGraphics();
    
    graphics.fillRect(0, 0, graphics.getWidth(), graphics.getHeight(), Color.fromARGB(255, 81, 194, 205));
    
    this.m_entities.paint();
    
    graphics.drawFont(""+this.getEngine().getTimer().getFPS(), 2, 2, ((Fowlplay)this.getEngine()).getFont());
  }
  
  public EntityManager getEntities() {return this.m_entities;}
}
