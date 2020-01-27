package invaders;

import java.awt.Color;

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
        
    Player player = new Player(this.getEngine());
    player.getPosition().setCoordinates((int)((this.getEngine().getWidth()-player.getSize().getWidth())*0.5), (int)((this.getEngine().getHeight()-player.getSize().getHeight())-2), 0);
    this.getEntities().add(player);
  }
  
  public void update() {  	
    int i;
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
    
    this.getEntities().update();
  }
  
  public void paint() {
    this.getEntities().paint();    
    
    this.getEngine().getGraphics().drawString(""+this.getEntities().count(), 2, 12, Color.white.getRGB());
  }
  
  public EntityManager getEntities() {return this.m_entities;}
}
