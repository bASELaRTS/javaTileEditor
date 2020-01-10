package engine;

import java.util.Vector;

public class Keyboard {
  Vector<Key> m_keys;
  
  public Keyboard() {
    this.m_keys = new Vector<Key>();
  }
  
  public void add(Key key) {
    if (key!=null) {
      Key k = this.find(key.getKeyCode());
      if (k==null) {
        this.getKeys().add(key);
      }
    }
  }
  
  public void add(int keyCode) {
    Key k = this.find(keyCode);
    if (k==null) {
      this.getKeys().add(new Key(keyCode,false));
    }    
  }
  
  public boolean isPressed(int keyCode) {
    Key k = this.find(keyCode);
    if (k!=null) {
      return k.getState();
    }
    return false;
  }
  
  public boolean isPressed() {
    int i;
    Key k;
    for(i=0;i<this.getKeys().size();i++) {
      k = this.getKeys().elementAt(i);
      if (k.getState()) {
        return true;
      }
    }
    return false;    
  }
  
  public Key find(int keyCode) {
    int i;
    Key k;
    for(i=0;i<this.getKeys().size();i++) {
      k = this.getKeys().elementAt(i);
      if (k.getKeyCode()==keyCode) {
        return k;
      }
    }
    return null;
  }
  
  public Vector<Key> getKeys(){return this.m_keys;}
  
  public class Key {
    private int m_keyCode;
    private boolean m_state;
    
    public Key() {
      this.setKeyCode(0);
      this.setState(false);
    }
    
    public Key(int keyCode,boolean state) {
      this.setKeyCode(keyCode);
      this.setState(state);
    }    
    
    public void setKeyCode(int i) {this.m_keyCode=i;}
    public int getKeyCode() {return this.m_keyCode;}
    public void setState(boolean b) {this.m_state=b;}
    public boolean getState() {return this.m_state;}
  }
}
