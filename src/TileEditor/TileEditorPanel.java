package TileEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TileEditorPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener, ITilePainter {
  private static final long serialVersionUID = 1L;

  private IMapManager m_mapManager;
  
  Thread m_thread;
  boolean m_threadRunning;
  
  private int m_mouseX;
  private int m_mouseY;
  private boolean m_mouseLeft;
  private boolean m_mouseRight;
  
  private double m_zoom;
  
  public TileEditorPanel() {
    super();    
    
    this.setZoom(1.0);
    
    this.setPreferredSize(new Dimension(256,256));
    
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
    
    this.m_thread = new Thread(this);
    this.m_thread.start();
  }  
  
  private TileMap getSelectedMap() {
  	if (this.m_mapManager!=null) {
  		for(int i=0;i<this.m_mapManager.count();i++) {
  			TileMap map = this.m_mapManager.elementAt(i);
  			if (map.getSelected()) {
  				return map;
  			}
  		}
  	}
  	return null;
  }
    
  private void loop() {
    TileMap map;
    int w,h;
    int tw,th;
    int tx,ty;
    
    map = this.getSelectedMap();
    if (map!=null) {  
      if ((map.getVisible())&&(!map.getLocked())) {
        tw = map.getTileWidth();
        th = map.getTileHeight();
        w = map.getWidth();
        h = map.getHeight();
        tx = this.m_mouseX/tw;
        ty = this.m_mouseY/th;
        
        if (tx<0) tx = 0;
        if (ty<0) ty = 0;
        if (tx>w-1) tx = w-1;
        if (ty>h-1) ty = h-1;
        
        if (this.m_mouseLeft) {
        	int tile = ((TileManager)map.getTileManager()).getSelectedIndex();        	        	
          map.setTile(tx, ty, tile);
        } else if (this.m_mouseRight) {
          map.setTile(tx, ty, -1);
        }            
      }
    }
    
    this.repaint();
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    
    int w,h;
    int x,y;
    int i,j;
    int tx,ty;
    int tw,th;
    
    // background
    w = this.getWidth();
    h = this.getHeight();
    tw = 16;
    th = 16;
    
    i = 0;
    j = 0;
    y = 0;    
    while(y<h) {
      x = 0;
      i = 0;
      while(x<w) {
        if ((i+j)%2==0) {
          g.setColor(Color.white);
        } else {
          g.setColor(Color.lightGray);
        }
        g.fillRect(x, y, tw, th);
        i++;
          
        x+=tw;
      }
      j++;
      y+=th;
    }
    
    // map
    if ((this.m_mapManager!=null)&&(this.m_mapManager.count()>0)) {
      TileMap map;

      // draw maps
      for(i=0;i<this.m_mapManager.count();i++) {
        map = this.m_mapManager.elementAt(i);
        
        if (map.getTilePainter()==null) {
        	map.setTilePainter(this);
        }
        
        if (map.getVisible()) {
          map.paint(g);
        }
      }
      
      // cursor
      map = this.getSelectedMap();
      if (map!=null) {
        tw = map.getTileWidth();
        th = map.getTileHeight();
              
        tx = this.m_mouseX / tw;
        ty = this.m_mouseY / th;
        if (tx<0) tx = 0;
        if (ty<0) ty = 0;
        if (tx>=map.getWidth()) tx=map.getWidth()-1;
        if (ty>=map.getHeight()) ty=map.getHeight()-1;
        
        if (!map.getLocked()) {
          x = (int)((tx * tw) * this.getZoom());
          y = (int)((ty * th) * this.getZoom());
          w = (int)(tw*this.getZoom());
          h = (int)(th*this.getZoom());
          g.setColor(Color.red);
          g.drawRect(x, y, w, h);        
        }
        
        // boundary
        w = (int)((map.getWidth()*tw)*this.getZoom());
        h = (int)((map.getHeight()*th)*this.getZoom());
        x = 0;
        y = 0;
        g.setColor(Color.red);
        g.drawRect(x, y, w, h);
      }
    }
  }
  
  public void run() {
    this.m_threadRunning = true;
    while(this.m_threadRunning) {
      try {
        this.loop();
        
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void mouseDragged(MouseEvent arg0) {
  	int x = (int)(arg0.getX() / this.getZoom());
  	int y = (int)(arg0.getY() / this.getZoom());
    this.m_mouseX = x;
    this.m_mouseY = y;
  }
  public void mouseMoved(MouseEvent arg0) {
  	int x = (int)(arg0.getX() / this.getZoom());
  	int y = (int)(arg0.getY() / this.getZoom());
    this.m_mouseX = x;
    this.m_mouseY = y;
  }

  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {
    boolean state = true;
    if (e.getButton()==MouseEvent.BUTTON1) {
      this.m_mouseLeft = state;
    } else if (e.getButton()==MouseEvent.BUTTON3) {
      this.m_mouseRight = state;
    }
  }
  public void mouseReleased(MouseEvent e) {
    boolean state = false;
    if (e.getButton()==MouseEvent.BUTTON1) {
      this.m_mouseLeft = state;
    } else if (e.getButton()==MouseEvent.BUTTON3) {
      this.m_mouseRight = state;
    }    
  }
  
  private void paintDummy(int tile, int x, int y, int tw, int th, Graphics graphics) {
    if (tile>=0) {
      graphics.setColor(Color.red);
      graphics.fillRect(x, y, tw, th);
      
      graphics.setColor(Color.white);
      graphics.drawString(""+tile, x, y+10);
      
      graphics.setColor(Color.lightGray);
      graphics.drawRect(x, y, tw, th);
    }      
  }
  
  public void paintTile(TileMap map, int tile, int x, int y, int tw, int th, Graphics graphics) {
    int c;
    BufferedImage image;
    
    int dx = (int)(x*this.getZoom());
    int dy = (int)(y*this.getZoom());
    int dtw = (int)(tw*this.getZoom());
    int dth = (int)(th*this.getZoom());

    ITileManager tileManager = map.getTileManager();
    if (tileManager!=null) {
      c = tileManager.count();
      if (c>0) {
        if ((tile>=0)&&(tile<c)) {
          image = tileManager.getImage(tile);        
          graphics.drawImage(image,dx,dy,dtw,dth,null);
        } else {
          this.paintDummy(tile, dx, dy, dtw, dth, graphics);
        }
      } else {
        this.paintDummy(tile, dx, dy, dtw, dth, graphics);
      }    	
    } else {
    	this.paintDummy(tile, dx, dy, dtw, dth, graphics);
    }
  }

	public void setMapManager(IMapManager mapManager) {this.m_mapManager=mapManager;}
  public void setZoom(double d) {this.m_zoom=d;}
  public double getZoom() {return this.m_zoom;}
}
