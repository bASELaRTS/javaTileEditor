package TileEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class TileEditorFormTiles extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;

  private JFileChooser m_fileChooser;
  
  private TilePanel m_panel;  
  private JButton m_btnAdd;
  
  public TileEditorFormTiles() {
    super();
        
    this.m_fileChooser = new JFileChooser();
    
    this.m_panel = new TilePanel();
    
    this.m_btnAdd = new JButton();
    this.m_btnAdd.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/folder.png")));
    this.m_btnAdd.addActionListener(this);
    
    JToolBar toolbar;
    toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.add(this.m_btnAdd);
    
    JPanel panel;
    panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(toolbar,BorderLayout.CENTER);
    
    this.setTitle("Tiles");
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setResizable(true);
    this.setLocationRelativeTo(null);
    this.setLayout(new BorderLayout());
    this.add(this.m_panel,BorderLayout.CENTER);
    this.add(panel,BorderLayout.SOUTH);
    this.pack();
    this.setVisible(true);
  }
  
  /*
  public static void main(String[] args) {
    new TileEditorFormTiles();
  }  
  /**/

  public void actionPerformed(ActionEvent arg0) {
    if (arg0.getSource()==this.m_btnAdd) {
      this.m_fileChooser.setMultiSelectionEnabled(true);
      if(this.m_fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
        int i,c;
        c = this.m_fileChooser.getSelectedFiles().length;
        for(i=0;i<c;i++) {
          BufferedImage image;
          image = Helper.Image.loadFromFile(this.m_fileChooser.getSelectedFiles()[i]);          
          this.m_panel.add(image);        
        }
        this.m_panel.repaint();
      }
    }
  }
  
  public TilePanel getTilePanel() {return this.m_panel;}
  
  // tile panel
  public class TilePanel extends JPanel implements ITileManager, MouseListener, MouseMotionListener {
    private static final long serialVersionUID = 1L;

    private TileManager m_tiles;
    private int m_selectedIndex;
    
    public TilePanel() {
      super();      
      
      this.m_tiles = new TileManager();

      this.setPreferredSize(new Dimension(256,256));
      
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
    }
    
    public void paint(Graphics g) {
      super.paint(g);
      
      int c;
      int i;
      int x,y;
      int w,h;
      Tile tile;
      
      w = this.getWidth();
      h = this.getHeight();
      c = this.m_tiles.count();
      y = 0;
      x = 0;
      for(i=0;i<c;i++) {
        tile = this.m_tiles.elementAt(i);
        
        tile.setXY(x, y);
        
        if (tile.getImage()!=null)
          g.drawImage(tile.getImage(), tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight(), null);
        
        if (tile.getSelected()) {
          g.setColor(Color.red);
          g.drawRect(tile.getX(), tile.getY(), tile.getWidth()-1, tile.getHeight()-1);
        }
        
        x+=tile.getWidth();
        if (x>=(w-tile.getWidth())) {
          x = 0;
          y+=tile.getHeight();
          if (y>=(h-tile.getHeight())) {
            i=c;
          }
        }
      }
    }
    
    public void mouseDragged(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
      int i,c;
      int x,y;
      Tile tile;
      
      c = this.m_tiles.count();      
      x = e.getX();
      y = e.getY();
            
      for(i=0;i<c;i++) {
        tile = this.m_tiles.elementAt(i);
        tile.setSelected(false);
      }
      this.m_selectedIndex = -1;
            
      for(i=0;i<c;i++) {
        tile = this.m_tiles.elementAt(i);
        if ((x>=tile.getX())&&(x<(tile.getX()+tile.getWidth()))&&(y>=tile.getY())&&(y<(tile.getY()+tile.getHeight()))) {
          tile.setSelected(true);
          this.m_selectedIndex = i;
          i = c;
        }
      }
      
      this.repaint();
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    
		public void clear() {this.m_tiles.clear();}
		public void add(BufferedImage image) {this.add(new Tile(image,0,0));}
		public void add(Tile tile) {this.m_tiles.add(tile);}
		public Tile elementAt(int index) {return this.m_tiles.elementAt(index);}
		public int count() {return this.m_tiles.count();}
		public BufferedImage getImage(int index) {return this.m_tiles.getImage(index);}

		public void setSelected(int index) {
			for(int i=0;i<this.m_tiles.count();i++) {
				Tile tile = this.m_tiles.elementAt(i);
				if (index==i) {
					tile.setSelected(true);
				} else {
					tile.setSelected(false);
				}
			}
			this.m_selectedIndex = index;
		}
		public int getSelected() {return this.m_selectedIndex;}
  }

}
