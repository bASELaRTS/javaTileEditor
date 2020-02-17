package engine;

public class LinkedList {
  private LinkedListObject m_first;
  private LinkedListObject m_last;
  
  public LinkedList() {
    this.setFirst(null);
    this.setLast(null);
  }
  
  public void clear() {
    LinkedListObject o,n;
    
    o = this.getFirst();
    while(o!=null) {
      n = o.getNext();
      this.remove(o);
      o = n;
    }
  }
  
  public int count() {
    LinkedListObject o;
    int i = 0;
    o = this.getFirst();
    while(o!=null) {
      i++;
      o = o.getNext();
    }
    return i;
  }
  
  public LinkedListObject item(int index) {
    LinkedListObject o;
    int i;
    i = 0;
    o = this.getFirst();
    while(o!=null) {
      if (i==index) {
        return o;
      }
      i++;
      o = o.getNext();
    }
    return null;
  }
  
  public void add(Object object) {
    if (object!=null) {
      LinkedListObject o = new LinkedListObject(object);
      this.add(o);
    }
  }
  
  public void add(LinkedListObject llo) {
    if (llo!=null) {
      if (this.getFirst()==null) {
        this.setFirst(llo);
        this.setLast(llo);
        
        this.getFirst().setNext(null);
        this.getFirst().setPrevious(null);
        
        this.getLast().setNext(null);
        this.getLast().setPrevious(null);
      } else {
        llo.setPrevious(this.getLast());
        llo.setNext(null);
        
        this.getLast().setNext(llo);
        this.setLast(llo);
      }
    }
  }
  
  public void remove(LinkedListObject llo) {
    LinkedListObject previous;
    LinkedListObject next;
    
    previous = llo.getPrevious();
    next = llo.getNext();
    
    if (previous!=null) {
      previous.setNext(next);
    } else {
      this.setFirst(next);
    }
    
    if (next!=null) {
      next.setPrevious(previous);
    } else {
      this.setLast(previous);
    }
    
    llo.setObject(null);
    llo = null;
  }
  
  private void setFirst(LinkedListObject llo) {this.m_first=llo;}
  public LinkedListObject getFirst() {return this.m_first;}
  private void setLast(LinkedListObject llo) {this.m_last=llo;}
  public LinkedListObject getLast() {return this.m_last;}
  
  public class LinkedListObject {
    private LinkedListObject m_previous;
    private LinkedListObject m_next;
    private Object m_object;
    
    public LinkedListObject() {
      this.setNext(null);
      this.setPrevious(null);
      this.setObject(null);
    }
    
    public LinkedListObject(Object object) {
      this.setNext(null);
      this.setPrevious(null);
      this.setObject(object);
    }
    
    public LinkedListObject(Object object, LinkedListObject previous, LinkedListObject next) {
      this.setObject(object);
      this.setNext(next);
      this.setPrevious(previous);
    }
    
    public void setPrevious(LinkedListObject llo) {this.m_previous=llo;}
    public LinkedListObject getPrevious() {return this.m_previous;}    
    public void setNext(LinkedListObject llo) {this.m_next=llo;}
    public LinkedListObject getNext() {return this.m_next;}
    public void setObject(Object object) {this.m_object=object;}
    public Object getObject() {return this.m_object;}
  }
}
