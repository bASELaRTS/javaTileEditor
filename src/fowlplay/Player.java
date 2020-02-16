package fowlplay;

import java.awt.event.KeyEvent;

import engine.Engine;
import engine.Entity;
import engine.Keyboard;
import engine.Map;
import engine.graphics.IGraphics;
import engine.math.Vector3;

public class Player extends Entity {
  private Vector3[] m_collisionHotSpots;
  private int m_state;
  private Vector3 m_speed;
  
  public Player(Engine engine) {
    super(engine);
    
    this.setName("player");
    this.getSize().setSize(32, 32);
    this.m_speed = new Vector3();
    
    this.m_collisionHotSpots = new Vector3[6];
    this.m_collisionHotSpots[0] = new Vector3(this.getSize().getWidth()*0.5,this.getSize().getHeight(),0);
    this.m_collisionHotSpots[1] = new Vector3(this.getSize().getWidth()*0.5,0,0);
    this.m_collisionHotSpots[2] = new Vector3(0,this.getSize().getHeight()*0.5,0);
    this.m_collisionHotSpots[3] = new Vector3(this.getSize().getWidth(),this.getSize().getHeight()*0.5,0);
    
    this.m_collisionHotSpots[4] = new Vector3(2,this.getSize().getHeight(),0);
    this.m_collisionHotSpots[5] = new Vector3(this.getSize().getWidth()-2,this.getSize().getHeight(),0);
    
    this.m_state = 0;
  }
  
  public void update() {
    Vector3 p = new Vector3(this.getPosition());
    Vector3 s = new Vector3(this.getSpeed());
        
    this.handleMovement(p, s);
    this.handleCollisionMaps(p, s);
    this.handleCollisionObjects(p);    
        
    this.getPosition().setVector(p);
    this.getSpeed().setVector(s);
    
  }
  
  public void paint() {
    IGraphics graphics = this.getEngine().getGraphics();
    SceneMain scene = (SceneMain)this.getEngine().getScenes().elementAt(0);
    Vector3 v = new Vector3();
    Camera camera = (Camera)scene.getEntities().find("camera");
    
    Vector3.subtract(this.getPosition(), camera.getPosition(), v);
    
    int x = (int)v.x;
    int y = (int)v.y;
    int w = this.getSize().getWidth();
    int h = this.getSize().getHeight();
    graphics.fillRect(x, y, w, h, engine.graphics.Color.fromARGB(255, 255, 255, 255));
  }

  private void handleMovement(Vector3 p, Vector3 s) {
    Vector3 v = new Vector3();
    Vector3 a = new Vector3();
    Keyboard keyboard = this.getEngine().getInput().getKeyboard();
    double maxx = 3;
    double maxy = 5;
    
    if (keyboard.isPressed(KeyEvent.VK_LEFT)) {
      a.x =-1;
    } else if (keyboard.isPressed(KeyEvent.VK_RIGHT)) {
      a.x = 1;
    } else {
      s.x = 0;
    }
    
    s.x = s.x + a.x;
    if (s.x> maxx) s.x = maxx;
    if (s.x<-maxx) s.x =-maxx;
    
    // vertical movement
    s.y += 1;
    if (s.y>maxy) s.y=maxy;
    
    if (keyboard.isPressed(KeyEvent.VK_UP)) {
      if (this.m_state==0) {
        this.m_state = 1;
        s.y-=12;
      }
    }     
    
    Vector3.add(p, s, v);
    p.setVector(v);    
  }
  
  private void handleCollisionMaps(Vector3 p, Vector3 s) {
    int tx,ty;
    int tw,th;
    int tile;
    Vector3 v = new Vector3();
    SceneMain scene = (SceneMain)this.getEngine().getScenes().elementAt(0);
    Map map = (Map)scene.getEntities().find("tilemap");
    
    tw = map.getTileWidth();
    th = map.getTileHeight();            
        
    // collision bottom
    Vector3.add(p, this.m_collisionHotSpots[0], v);    
    tx = (int)(v.x/tw);
    ty = (int)(v.y/th);
    tile = map.getTile(tx, ty);
    if (tile>0) {
      p.y = (ty*th)-this.getSize().getHeight();
      s.y = 0;
      this.m_state = 0;
    }
    Vector3.add(p, this.m_collisionHotSpots[4], v);    
    tx = (int)(v.x/tw);
    ty = (int)(v.y/th);
    tile = map.getTile(tx, ty);
    if (tile>0) {
      p.y = (ty*th)-this.getSize().getHeight();
      s.y = 0;
      this.m_state = 0;
    }
    Vector3.add(p, this.m_collisionHotSpots[5], v);    
    tx = (int)(v.x/tw);
    ty = (int)(v.y/th);
    tile = map.getTile(tx, ty);
    if (tile>0) {
      p.y = (ty*th)-this.getSize().getHeight();
      s.y = 0;
      this.m_state = 0;
    }
    // collision top
    Vector3.add(p, this.m_collisionHotSpots[1], v);    
    tx = (int)(v.x/tw);
    ty = (int)(v.y/th);
    tile = map.getTile(tx, ty);
    if (tile>0) {
      p.y = (ty*th)+th;
      s.y = 0;
    }
    // collision left
    Vector3.add(p, this.m_collisionHotSpots[2], v);
    tx = (int)(v.x/tw);
    ty = (int)(v.y/th);
    tile = map.getTile(tx, ty);
    if (tile>0) {
      p.x = (tx*tw)+tw;
      s.x = 0;
    }        
    // collision right
    Vector3.add(p, this.m_collisionHotSpots[3], v);
    tx = (int)(v.x/tw);
    ty = (int)(v.y/th);
    tile = map.getTile(tx, ty);
    if (tile>0) {
      p.x = (tx*tw)-this.getSize().getWidth();
      s.x = 0;
    }        
        
    this.getPosition().setVector(p);
    this.getSpeed().setVector(s);
  }
  
  private void handleCollisionObjects(Vector3 p) {
    int i;
    int x = (int)p.x;
    int y = (int)p.y;
    int w = this.getSize().getWidth();
    int h = this.getSize().getHeight();
    SceneMain scene = (SceneMain)this.getEngine().getScenes().elementAt(0);
    for(i=0;i<scene.getEntities().count();i++) {
      Entity entity = scene.getEntities().elementAt(i);
      if (!entity.getRemove()) {        
        if (Helper.Collision.boxbox(
            x,y,w,h,
            (int)entity.getPosition().x,
            (int)entity.getPosition().y,
            entity.getSize().getWidth(),
            entity.getSize().getHeight()
        )) {
          if (entity.getName().equals("coin")) {            
            entity.setRemove(true);
          }
        }
      }
    }
  }
  
  public Vector3 getSpeed() {return this.m_speed;}
}
