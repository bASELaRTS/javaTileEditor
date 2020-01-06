package TileEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.swing.JPanel;

public class TileEditorPanelTilesLayout extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener, ITileManager {
	private static final long serialVersionUID = 1L;
	
	private Vector<ISelected> m_selectedListeners;
	
	private TileManager m_tileManager;	
	
	private Thread m_thread;
	private boolean m_threadRunning;
	
	private int m_mouseX;
	private int m_mouseY;
	private boolean m_mouseLeft;
	private boolean m_mouseDragging;
	private int m_mouseDragX;
	private int m_mouseDragY;
	
	private boolean m_keyLeft;
	private boolean m_keyRight;
	private boolean m_keyUp;
	private boolean m_keyDown;
	private boolean m_keyShift;
	private boolean m_keyS;
	private boolean m_keyI;

	private int m_previousX;
	private int m_previousY;
	private int m_previousWidth;
	private int m_previousHeight;
	private int m_previousSelected;
	
	private double m_zoom;
	private boolean m_debug;
	private boolean m_locked;
	
	public TileEditorPanelTilesLayout() {
		super();		
		this.m_selectedListeners = new Vector<ISelected>();
		
		this.m_previousX = 0;
		this.m_previousY = 0;
		this.m_previousWidth = 16;
		this.m_previousHeight = 16;
		this.m_previousSelected = -1;
		this.m_zoom = 1.0;
		this.m_debug = false;
		this.setLocked(false);
		this.setTileManager(null);
		
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(256,256));
		
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.m_thread = new Thread(this);
		this.m_thread.start();
	}
	
	public void loadImage(BufferedImage image) {
	  if (this.getTileManager()!=null) {
	    this.getTileManager().setImage(image);
	  }
	}
	
	public void loadLayout(String filename) {
	  if (this.getTileManager()!=null) {
	    this.getTileManager().load(new File(filename));	    
	  }
	}
	
	public void addTile() {
	  if (this.getTileManager()!=null) {
	    Tile tile = new Tile();
	    tile.setXY(this.m_previousX + this.m_previousWidth, this.m_previousY);
	    tile.setSize(this.m_previousWidth, this.m_previousHeight);
	    //tile.setSelected(true);
	    
	    this.getTileManager().getTiles().add(tile);
	    this.getTileManager().setSelectedIndex(this.getTileManager().count()-1);
	    
	    this.m_previousX = tile.getX();
	    this.m_previousY = tile.getY();
	    this.m_previousWidth = tile.getWidth();
	    this.m_previousHeight = tile.getHeight();	    
	  }
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		int i;
		int c;
		int x,y;
		int w,h;
		Tile t;
		BufferedImage image;
		
		// draw sprite sheet
		if (this.getTileManager()!=null) {
	    image = this.getTileManager().getImage();
	    if (image!=null) {
	      w = (int)(image.getWidth()*this.m_zoom);
	      h = (int)(image.getHeight()*this.m_zoom);
	      g.drawImage(image,0,0,w,h,null);
	    }		  
		  
	    // draw boxes
	    c = this.getTileManager().getTiles().size();
	    for(i=0;i<c;i++) {
	      t = this.getTileManager().getTiles().elementAt(i);
	      
	      x = (int)(t.getX()*this.m_zoom);
	      y = (int)(t.getY()*this.m_zoom);
	      w = (int)(t.getWidth()*this.m_zoom);
	      h = (int)(t.getHeight()*this.m_zoom);
	      
	      if (t.getSelected()) {
	        g.setColor(new Color(255,0,0,128));
	        g.fillRect(x,y,w,h);
	      } else {
	        g.setColor(Color.red);
	        g.drawRect(x,y,w,h);
	      }
	      
	      if (this.m_debug) {
	        g.setColor(Color.red);
	        g.drawString(""+i, x, y+12);
	      }
	    }
		}// if (this.getTileManager()!=null)
	}
	
	public void addSelectedListener(ISelected selectedListener) {
		this.m_selectedListeners.add(selectedListener);		
	}
	public void raiseTileSelected() {
		for(int i=0;i<this.m_selectedListeners.size();i++) {
			EventArgs event = new EventArgs();
			event.setSender(this);
			this.m_selectedListeners.elementAt(i).selected(event);
		}
	}
	
	public void loop() {
		int i,j;
		int c;
		int x,y;
		int dx,dy;
		Tile tile;
		
		if (this.getTileManager()!=null) {
	    c = this.getTileManager().getTiles().size();    
	    x = this.m_mouseX;
	    y = this.m_mouseY;
	    
	    if (this.m_mouseLeft) {
	      this.requestFocus();
	      
	      if (!this.m_mouseDragging) {
	        for(i=0;i<c;i++) {
	          this.getTileManager().getTiles().elementAt(i).setSelected(false);
	        }
	        
	        for(i=0;i<c;i++) {
	          tile = this.getTileManager().getTiles().elementAt(i);
	          if ((x>=tile.getX())&&(x<(tile.getX()+tile.getWidth()))&&(y>=tile.getY())&&(y<(tile.getY()+tile.getHeight()))) {
	            tile.setSelected(true);
	                        
	            this.m_mouseDragX = x;
	            this.m_mouseDragY = y;
	            this.m_mouseDragging = true;
	            
	            if (this.m_previousSelected!=i) {
	              this.m_previousSelected = i;
	              this.raiseTileSelected();
	            }
	            
	            i = c;
	          } else {
	            tile.setSelected(false);
	          }
	        }
	      } else {
	        if (!this.getLocked()) {
	          dx = x - this.m_mouseDragX;
	          dy = y - this.m_mouseDragY;
	          
	          for(i=0;i<c;i++) {
	            tile = this.getTileManager().getTiles().elementAt(i);
	            if (tile.getSelected()) {
	              tile.setX(tile.getX()+dx);
	              tile.setY(tile.getY()+dy);

	              this.m_previousX = tile.getX();
	              this.m_previousY = tile.getY();
	              this.m_previousWidth = tile.getWidth();
	              this.m_previousHeight = tile.getHeight();
	            }
	          }
	          
	          this.m_mouseDragX = x;
	          this.m_mouseDragY = y;          
	        }
	      }
	    } else {
	      this.m_mouseDragging = false;
	    }
	    
	    if (this.m_keyLeft||this.m_keyRight||this.m_keyUp||this.m_keyDown) {
	      if (!this.getLocked()) {
	        for(i=0;i<c;i++) {
	          tile = this.getTileManager().getTiles().elementAt(i);
	          if (tile.getSelected()) {
	            if (this.m_keyLeft) {
	              this.m_keyLeft = false;           
	              if (this.m_keyShift) {
	                tile.setWidth(tile.getWidth()-1);
	              } else {
	                tile.setX(tile.getX()-1);
	              }
	            } else if (this.m_keyRight) {
	              this.m_keyRight = false;
	              if (this.m_keyShift) {
	                tile.setWidth(tile.getWidth()+1);
	              } else {
	                tile.setX(tile.getX()+1);
	              }
	            }     

	            if (this.m_keyUp) {
	              this.m_keyUp = false;
	              if (this.m_keyShift) {
	                tile.setHeight(tile.getHeight()-1);
	              } else {
	                tile.setY(tile.getY()-1);
	              }
	            } else if (this.m_keyDown) {
	              this.m_keyDown = false;
	              if (this.m_keyShift) {
	                tile.setHeight(tile.getHeight()+1);
	              } else {
	                tile.setY(tile.getY()+1);
	              }
	            }
	          } // if tile.selected
	        } // for            
	      } // if locked
	    } // if arrow key
	        
	    if (this.m_keyI) {
	      this.m_keyI = false;
	      
	      if (this.m_keyShift) {
	        this.m_debug = !this.m_debug;
	      } else {
	        c = this.getTileManager().getTiles().size();      
	        for(i=0;i<c;i++) {
	          tile = this.getTileManager().getTiles().elementAt(i);
	          if (tile.getSelected()) {
	            TileEditorFormTilesLayoutInfo form = new TileEditorFormTilesLayoutInfo();
	            form.setTile(tile);
	            if (form.ShowDialog()==TileEditorFormTilesLayoutInfo.OPTION_OK) {
	              tile.setName(form.getName());
	              tile.setX(form.getX());
	              tile.setY(form.getY());
	              tile.setWidth(form.getWidth());
	              tile.setHeight(form.getHeight());
	            }
	            i =c;
	          }
	        }       
	      }
	    } // if keyI
	    
	    if (this.m_keyS) {
	      this.m_keyS = false;
	      c = this.getTileManager().getTiles().size();
	      
	      j = 0;
	      
	      for(i=0;i<c;i++) {
	        tile = this.getTileManager().getTiles().elementAt(i);
	        if ((tile.getSelected())&&(j==0)) {
	          j = i;
	        }
	        tile.setSelected(false);        
	      }
	      
	      if (c>0) {
	        j++;
	        if (j>=c) {
	          j = 0;
	        }
	        tile = this.getTileManager().getTiles().elementAt(j);
	        tile.setSelected(true);
	        
	        if (j!=this.m_previousSelected) {
	          this.m_previousSelected = j;
	          this.raiseTileSelected();
	        }
	      }
	    } // if keyS
	    
		}// if (this.getTileManager()!=null);		
	}
	
	public void setLocked(boolean b) {this.m_locked=b;}
	public boolean getLocked() {return this.m_locked;}

	private void setKeyState(int keyCode, boolean state) {
		if (keyCode==KeyEvent.VK_LEFT) this.m_keyLeft = state;
		if (keyCode==KeyEvent.VK_RIGHT) this.m_keyRight = state;
		if (keyCode==KeyEvent.VK_UP) this.m_keyUp = state;
		if (keyCode==KeyEvent.VK_DOWN) this.m_keyDown = state;
		if (keyCode==KeyEvent.VK_SHIFT) this.m_keyShift = state;
		if (keyCode==KeyEvent.VK_I) this.m_keyI = state;
		if (keyCode==KeyEvent.VK_S) this.m_keyS = state;
	}
	
	public void setTileManager(TileManager tileManager) {this.m_tileManager = tileManager;}
	public TileManager getTileManager() {return this.m_tileManager;}
	public void setZoom(double d) {this.m_zoom=d;}
	public double getZoom() {return this.m_zoom;}

	public void mouseDragged(MouseEvent arg0) {
		int x = (int)(arg0.getX()/this.m_zoom);
		int y = (int)(arg0.getY()/this.m_zoom);		
		this.m_mouseX = x;
		this.m_mouseY = y;		
	}
	public void mouseMoved(MouseEvent arg0) {
		int x = (int)(arg0.getX()/this.m_zoom);
		int y = (int)(arg0.getY()/this.m_zoom);		
		this.m_mouseX = x;
		this.m_mouseY = y;
	}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {
		boolean state = true;
		if (arg0.getButton()==MouseEvent.BUTTON1) this.m_mouseLeft = state;
		
	}
	public void mouseReleased(MouseEvent arg0) {
		boolean state = false;
		if (arg0.getButton()==MouseEvent.BUTTON1) this.m_mouseLeft = state;		
	}
	public void keyPressed(KeyEvent arg0) {this.setKeyState(arg0.getKeyCode(), true);}
	public void keyReleased(KeyEvent arg0) {this.setKeyState(arg0.getKeyCode(), false);}
	public void keyTyped(KeyEvent arg0) {}
	public void run() {
		this.m_threadRunning = true;
		while(this.m_threadRunning) {
			try {				
				this.loop();				
				this.repaint();
				
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void clear() {this.getTileManager().clear();}
	public void add(BufferedImage image) {this.getTileManager().add(image);}
	public void add(Tile tile) {this.getTileManager().add(tile);}
	public Tile elementAt(int index) {return this.getTileManager().elementAt(index);}
	public int count() {return this.getTileManager().count();}
	public BufferedImage getImage(int index) {return this.getTileManager().getImage(index);}

}
