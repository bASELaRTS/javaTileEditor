package engine;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import engine.Keyboard.Key;
import engine.graphics.IGraphics;

public class GPanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
  private static final long serialVersionUID = 1L;
  
  private Engine m_engine;
  
  private Thread m_thread;
  private boolean m_threadRunning;
  
  public GPanel(Engine engine) {
    super();
    
    setEngine(engine);
    
    int w = (int)(engine.getWidth());
    int h = (int)(engine.getHeight());
    
    BufferedImage emptyCursorImage = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
    Cursor emptyCursor = Toolkit.getDefaultToolkit().createCustomCursor(emptyCursorImage, new Point(0, 0), "blank cursor");
    this.setCursor(emptyCursor);
    //this.setCursor(Cursor.getDefaultCursor());
    
    this.setMinimumSize(new Dimension(w,h));
    this.setPreferredSize(new Dimension(w,h));
    this.setFocusable(true);
    
    this.addKeyListener(this);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
    
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
      } else {
      	keyboard.setUnknownKeyCode(keyCode, state);
      }
    }
  }
  public void keyPressed(KeyEvent arg0) {this.setKey(arg0.getKeyCode(), true);}
  public void keyReleased(KeyEvent arg0) {this.setKey(arg0.getKeyCode(), false);}
  public void keyTyped(KeyEvent arg0) {}
  
  private void setMousePosition(int x, int y) {
    if (this.getEngine()!=null) {
      Mouse mouse = this.getEngine().getInput().getMouse();
      mouse.setXY(x, y);
    }    
  }
  private void setMouseButton(int button, boolean state) {
    if (this.getEngine()!=null) {
      Mouse mouse = this.getEngine().getInput().getMouse();
      if (button==MouseEvent.BUTTON1) mouse.setLeft(state);
      if (button==MouseEvent.BUTTON2) mouse.setMiddle(state);
      if (button==MouseEvent.BUTTON3) mouse.setRight(state);
    }        
  }
  public void mouseDragged(MouseEvent arg0) {this.setMousePosition(arg0.getX(), arg0.getY());}
  public void mouseMoved(MouseEvent arg0) {this.setMousePosition(arg0.getX(), arg0.getY());}  
  public void mouseClicked(MouseEvent arg0) {}
  public void mouseEntered(MouseEvent arg0) {}
  public void mouseExited(MouseEvent arg0) {}
  public void mousePressed(MouseEvent arg0) {this.setMouseButton(arg0.getButton(), true);}
  public void mouseReleased(MouseEvent arg0) {this.setMouseButton(arg0.getButton(), false);}

  public void setEngine(Engine engine) {this.m_engine=engine;}
  public Engine getEngine() {return this.m_engine;}

}
