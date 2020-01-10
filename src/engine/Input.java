package engine;

public class Input {
  private Keyboard m_keyboard;
  private Mouse m_mouse;
  
  public Input() {
    this.m_keyboard = new Keyboard();
    this.m_mouse = new Mouse();
  }
  
  public Keyboard getKeyboard() {return this.m_keyboard;}
  public Mouse getMouse() {return this.m_mouse;}
}
