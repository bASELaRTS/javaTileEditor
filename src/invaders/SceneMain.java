package invaders;

import engine.Engine;
import engine.Entity;
import engine.EntityManager;
import engine.Scene;
import engine.math.Vector3;

public class SceneMain extends Scene {
  private EntityManager m_entities;    
  
  private int m_enemyDirection;
  
  public SceneMain(Engine engine) {
    super(engine);
    
    this.m_entities = new EntityManager(this.getEngine());
          
    // entities
    this.getEntities().add(new Stars(this.getEngine()));
    
    Player player = new Player(this.getEngine());
    player.getPosition().setCoordinates((int)((this.getEngine().getWidth()-player.getSize().getWidth())*0.5), (int)((this.getEngine().getHeight()-player.getSize().getHeight())-2), 0);
    this.getEntities().add(player);
  }
  
  public void initialize() {
    // enemies
    int i,j,x,y,w,h;
    int enemyx = 6;
    int enemyy = 5;
    
    this.m_enemyDirection = -1;
    w = 15;
    h = 10;    
    x = (int)((this.getEngine().getWidth() - (enemyx*(w+2)))*0.5);
    y = 10;
    for(j=0;j<enemyy;j++) {
      for(i=0;i<enemyx;i++) {
        Enemy enemy = new Enemy(this.getEngine(),x+(i*(w+2)),y+(j*(h+2)),w,h);
        this.getEntities().add(enemy);
      }      
    }
    
    Player player = (Player)this.getEntities().find("player");
    if (player!=null) {
      player.getPosition().setCoordinates((int)((this.getEngine().getWidth()-player.getSize().getWidth())*0.5), (int)((this.getEngine().getHeight()-player.getSize().getHeight())-2), 0);
    }
  }
  
  public void update() {  	
    int i,j;
    Entity entity;
    Enemy enemy;
    Vector3 position = new Vector3();
    boolean movementOkay = true;
    
    i = 0;
    while((i<this.getEntities().count())&&(movementOkay)) {
      entity = this.getEntities().elementAt(i);
      if (entity.getName().equals("enemy")) {
        enemy = (Enemy)entity;
        position.setVector(enemy.getPosition());
        if (this.m_enemyDirection<0) {
          if (position.x<=0) {
            movementOkay = false;
            this.m_enemyDirection=1;
          }
        } else if (this.m_enemyDirection>0) {
          if (position.x>(this.getEngine().getWidth()-enemy.getWidth())) {
            movementOkay = false;
            this.m_enemyDirection = -1;
          }
        }
      }
      i++;
    }
    
    for(i=0;i<this.getEntities().count();i++) {
      entity = this.getEntities().elementAt(i);
      if (entity.getName().equals("enemy")) {
        enemy = (Enemy)entity;
        position.setVector(enemy.getPosition());
        position.x+=(this.m_enemyDirection * 0.2);
        if (!movementOkay) {
          position.y+=5;
        }
        enemy.getPosition().setVector(position);
      }
    }
    
    // test game winner
    Player player = (Player)this.getEntities().find("player");
    j = 0;
    for(i=0;i<this.getEntities().count();i++) {
      entity = this.getEntities().elementAt(i);
      if (entity.getName().equals("enemy")) {
        j++;
        
        boolean hit = Helper.Collision.boxbox(
            (int)player.getPosition().x, (int)player.getPosition().y, player.getWidth(), player.getHeight(), 
            (int)entity.getPosition().x, (int)entity.getPosition().y, entity.getWidth(), entity.getHeight()
        );
        
        if (hit) {
          i = this.getEntities().count();
          this.getEngine().getInput().getKeyboard().clearPressed();
          this.getEngine().getScenes().elementAt(Invaders.SCENE_LOSE).initialize();
          this.getEngine().getScenes().setActiveSceneIndex(Invaders.SCENE_LOSE);
        }
      }
    }
    if (j==0) {
      this.getEngine().getInput().getKeyboard().clearPressed();
      this.getEngine().getScenes().elementAt(Invaders.SCENE_WIN).initialize();
      this.getEngine().getScenes().setActiveSceneIndex(Invaders.SCENE_WIN);
    }
    
    // test game over
    
    
    this.getEntities().update();
  }
  
  public void paint() {
    this.getEntities().paint();    
    
    Invaders engine = (Invaders)this.getEngine();
    String str = ""+this.getEntities().count();
    this.getEngine().getGraphics().drawFont(str, 2, 12, engine.getFont());
  }
  
  public EntityManager getEntities() {return this.m_entities;}
}
