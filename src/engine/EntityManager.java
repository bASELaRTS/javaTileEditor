package engine;

import engine.LinkedList.LinkedListObject;

public class EntityManager {
  //private Vector<Entity> m_list;
  private LinkedList m_list;
  private Engine m_engine;
  
  public EntityManager(Engine engine) {
    this.m_engine = engine;
    //this.m_list = new Vector<Entity>(); 
    this.m_list = new LinkedList();
  }
  
  public void add(Entity e) {
    e.setEngine(this.getEngine());
    this.m_list.add(e);
  }
  public void clear() {this.m_list.clear();}
  public void remove(int index) {
    LinkedListObject o = this.m_list.item(index);
    if (o!=null) {
      this.m_list.remove(o);
    }
  }
  public void remove(Entity entity) {
    LinkedListObject o,n;
    o = this.m_list.getFirst();
    while(o!=null) {
      n = o.getNext();
      if (o.getObject().equals(entity)) {
        this.m_list.remove(o);
      }
      o = n;
    }
  }
  public Entity elementAt(int index) {
    LinkedListObject llo = this.m_list.item(index);
    if (llo!=null) {
      return (Entity)llo.getObject();
    }
    return null;
  }
  public int count() {return this.m_list.count();}
  
  public Entity find(String name) {
    LinkedListObject o;
    Entity e;
    
    o = this.m_list.getFirst();
    while(o!=null) {
      e = (Entity)o.getObject();
      if (e.getName().equals(name)) {
        return e;
      }
      o = o.getNext();
    }
    return null;
  }
  
  public void update() {
    LinkedListObject o,n;
    Entity e;
    o = this.m_list.getFirst();
    while(o!=null) {
      n = o.getNext();
      e = (Entity)o.getObject();
      if (!e.getRemove()) {
        e.update();
      } else {
        this.m_list.remove(o);
      }
      o = n;
    }
  }

  public void paint() {
    LinkedListObject o,n;
    Entity e;
    o = this.m_list.getFirst();
    while(o!=null) {
      n = o.getNext();
      e = (Entity)o.getObject();
      if (e.getVisible()) {
        e.paint();
      }
      o = n;
    }
  }
  
  public Engine getEngine() {return this.m_engine;}
}
