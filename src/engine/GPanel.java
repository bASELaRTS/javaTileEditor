package engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import engine.Keyboard.Key;

public class GPanel extends JPanel implements Runnable, KeyListener {
  private static final long serialVersionUID = 1L;
  
  private Engine m_engine;
  
  private Thread m_thread;
  private boolean m_threadRunning;
  
  public GPanel(Engine engine) {
    super();
    
    setEngine(engine);
    
    int w = (int)(engine.getWidth());
    int h = (int)(engine.getHeight());
    
    this.setMinimumSize(new Dimension(w,h));
    this.setPreferredSize(new Dimension(w,h));
    this.setFocusable(true);
    
    this.addKeyListener(this);
    
    this.m_thread = new Thread(this);
    this.m_thread.start();
  }
  
  public void run() {
    this.m_threadRunning = true;
    while (this.m_threadRunning) {
      try {
        if (this.getEngine()!=null) {
          this.getEngine().update();
        }
        
        this.repaint();
        
        Thread.sleep(10);
      } catch(Exception e) {
        
      }
    }
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    
    if (this.getEngine()!=null) {      
      this.getEngine().paint(g);   
      
      IGraphics graphics = this.getEngine().getGraphics();
      g.drawImage(graphics.getImage(),0,0,graphics.getWidth(),graphics.getHeight(),null);
    }
  }  

  private void setKey(int keyCode, boolean state) {
    if (this.getEngine()!=null) {
      Keyboard keyboard = this.getEngine().getInput().getKeyboard();
      Key key = keyboard.find(keyCode);
      if (key!=null) {
        key.setState(state);
      }
    }
  }
  public void keyPressed(KeyEvent arg0) {this.setKey(arg0.getKeyCode(), true);}
  public void keyReleased(KeyEvent arg0) {this.setKey(arg0.getKeyCode(), false);}
  public void keyTyped(KeyEvent arg0) {}
  
  public void setEngine(Engine engine) {this.m_engine=engine;}
  public Engine getEngine() {return this.m_engine;}  
}
