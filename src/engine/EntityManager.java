package engine;

import java.util.Vector;

public class EntityManager {
  private Vector<Entity> m_list;
  private Engine m_engine;
  
  public EntityManager(Engine engine) {
    this.m_engine = engine;
    this.m_list = new Vector<Entity>();    
  }
  
  public void add(Entity e) {
    e.setEngine(this.getEngine());
    this.m_list.add(e);
  }
  public void clear() {this.m_list.clear();}
  public void remove(int index) {this.m_list.remove(index);}
  public Entity elementAt(int index) {return this.m_list.elementAt(index);}
  public int count() {return this.m_list.size();}
  
  public Entity find(String name) {
    for(int i=0;i<this.m_list.size();i++) {
      Entity e = this.m_list.elementAt(i);
      if (e.getName().equals(name)) {
        return e;
      }
    }
    return null;
  }
  
  public void update() {
    for(int i=0;i<this.m_list.size();i++) {
      Entity e = this.m_list.elementAt(i);
      if (!e.getRemove()) {
        e.update();
      }
    }
    
    boolean removed = true;    
    while(removed) {
      removed = false;
      
      for(int i=0;i<this.m_list.size();i++) {
        Entity e = this.m_list.elementAt(i);
        if (e.getRemove()) {
          this.remove(i);
          i = this.m_list.size();
          removed = true;
        }
      }
    }
  }

  public void paint() {
    for(int i=0;i<this.m_list.size();i++) {
      Entity e = this.m_list.elementAt(i);
      if (e.getVisible()) {
        e.paint();
      }
    }
  }
  
  public Engine getEngine() {return this.m_engine;}
}
