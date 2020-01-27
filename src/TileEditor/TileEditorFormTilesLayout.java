package TileEditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class TileEditorFormTilesLayout extends JFrame implements ActionListener, ISelected {
	private static final long serialVersionUID = 1L;
	
	private JFileChooser m_fileChooser;
	private TileEditorPanelTilesLayout m_panel;
	private JButton m_btnLoadImage;
	private JButton m_btnLoadLayout;
	private JButton m_btnSaveLayout;
	private JButton m_btnLockLayout;
	private JButton m_btnAddTile;
	private JButton m_btnDeleteTile;
	private JButton m_btnClearTiles;
	private JButton m_btnTileInfo;
  private JButton m_btnTileSpacing;
	private JButton m_btnZoom100p;
	private JButton m_btnZoomIn;
	private JButton m_btnZoomOut;
	private JLabel m_lblTileInfo;
	private JScrollPane m_scrollPane;
	
	private boolean m_locked;
	private BufferedImage m_imgLocked;
	private BufferedImage m_imgUnLocked;
	
	public TileEditorFormTilesLayout() {
		super();
		
		this.m_locked = false;
		this.m_imgLocked = Helper.Image.loadFromFile("data/TileEditor/lock.png");
		this.m_imgUnLocked = Helper.Image.loadFromFile("data/TileEditor/unlock.png");
		
		this.m_fileChooser = new JFileChooser();
		//this.m_fileChooser.setCurrentDirectory(new File("D:\\bASEL\\Projects\\Java\\javaTileEditor\\data\\tiles"));
		this.m_fileChooser.setCurrentDirectory(new File("data/tiles"));
		
		this.m_panel = new TileEditorPanelTilesLayout();
		this.m_panel.addSelectedListener(this);
		this.m_scrollPane = new JScrollPane(this.m_panel);
		
		this.m_btnLoadImage = new JButton();
		this.m_btnLoadImage.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/folder.png")));
		this.m_btnLoadImage.setToolTipText("Load Image");
		this.m_btnLoadImage.addActionListener(this);
		this.m_btnLoadLayout = new JButton();
		this.m_btnLoadLayout.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/folder.png")));
		this.m_btnLoadLayout.setToolTipText("Load Layout");
		this.m_btnLoadLayout.addActionListener(this);
		this.m_btnSaveLayout = new JButton();
		this.m_btnSaveLayout.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/save.png")));
		this.m_btnSaveLayout.setToolTipText("Save Layout");
		this.m_btnSaveLayout.addActionListener(this);
		this.m_btnLockLayout = new JButton();
		this.m_btnLockLayout.setIcon(new ImageIcon(this.m_imgUnLocked));
		this.m_btnLockLayout.setToolTipText("Lock Layout");
		this.m_btnLockLayout.addActionListener(this);
		
		this.m_btnAddTile = new JButton();
		this.m_btnAddTile.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/new.png")));
		this.m_btnAddTile.setToolTipText("Add Tile");
		this.m_btnAddTile.addActionListener(this);
		this.m_btnDeleteTile = new JButton();
		this.m_btnDeleteTile.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/Delete.png")));
		this.m_btnDeleteTile.setToolTipText("Delete Tile");
		this.m_btnDeleteTile.addActionListener(this);
		this.m_btnClearTiles = new JButton();
		this.m_btnClearTiles.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/Trash.png")));
		this.m_btnClearTiles.setToolTipText("Clear All Tiles");
		this.m_btnClearTiles.addActionListener(this);
		this.m_btnTileInfo = new JButton();
		this.m_btnTileInfo.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/Info.png")));
		this.m_btnTileInfo.setToolTipText("Tile Info");
		this.m_btnTileInfo.addActionListener(this);
    this.m_btnTileSpacing = new JButton();
    this.m_btnTileSpacing.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/Info.png")));
    this.m_btnTileSpacing.setToolTipText("Tile Spacing");
    this.m_btnTileSpacing.addActionListener(this);

		this.m_btnZoom100p = new JButton();
		this.m_btnZoom100p.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/zoom.png")));
		this.m_btnZoom100p.setToolTipText("Zoom 100%");
		this.m_btnZoom100p.addActionListener(this);
		this.m_btnZoomIn = new JButton();
		this.m_btnZoomIn.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/zoom.png")));
		this.m_btnZoomIn.setToolTipText("Zoom In");
		this.m_btnZoomIn.addActionListener(this);
		this.m_btnZoomOut = new JButton();
		this.m_btnZoomOut.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/zoom.png")));
		this.m_btnZoomOut.setToolTipText("Zoom Out");
		this.m_btnZoomOut.addActionListener(this);
				
		this.m_lblTileInfo = new JLabel();
		
		JToolBar toolbar;
		toolbar = new JToolBar();
		toolbar.setFloatable(false);
		toolbar.add(this.m_btnLoadImage);
		toolbar.add(this.m_btnLoadLayout);
		toolbar.add(this.m_btnSaveLayout);
		toolbar.addSeparator();
		toolbar.add(this.m_btnLockLayout);
		toolbar.addSeparator();
		toolbar.add(this.m_btnAddTile);
		toolbar.add(this.m_btnDeleteTile);
		toolbar.add(this.m_btnClearTiles);
		toolbar.add(this.m_btnTileInfo);
    toolbar.add(this.m_btnTileSpacing);
		toolbar.addSeparator();
		toolbar.add(this.m_btnZoom100p);
		toolbar.add(this.m_btnZoomIn);
		toolbar.add(this.m_btnZoomOut);
		toolbar.addSeparator();
		toolbar.add(this.m_lblTileInfo);
		
		JPanel panel;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(toolbar,BorderLayout.CENTER);
		
		this.setTitle("Tiles");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		this.add(this.m_scrollPane,BorderLayout.CENTER);
		this.add(panel,BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
  public static void main(String[] args) {
    TileEditorFormTilesLayout form = new TileEditorFormTilesLayout();
    form.getPanel().setTileManager(new TileManager());
    form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }  

	public void actionPerformed(ActionEvent arg0) {
	  if (this.getPanel().getTileManager()==null) {
	    
	  } else {
	    if (arg0.getSource()==this.m_btnLoadImage) {
        this.m_fileChooser.setMultiSelectionEnabled(false);
        if (this.m_fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
          BufferedImage image = Helper.Image.loadFromFile(this.m_fileChooser.getSelectedFile());        
          this.m_panel.getTileManager().setImage(image);
          if (image!=null) {
            int w = (int)(image.getWidth()*this.m_panel.getZoom());
            int h = (int)(image.getHeight()*this.m_panel.getZoom());
            this.m_panel.setPreferredSize(new Dimension(w,h));          
          }
        }       
	    } else if (arg0.getSource()==this.m_btnLoadLayout) {
        this.m_fileChooser.setMultiSelectionEnabled(false);
        if (this.m_fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
          this.m_panel.getTileManager().load(this.m_fileChooser.getSelectedFile());
          this.getPanel().setLocked(true);
          this.setLockedStatus();
        }       
	    } else if (arg0.getSource()==this.m_btnSaveLayout) {
        this.m_fileChooser.setMultiSelectionEnabled(false);
        if (this.m_fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
          this.m_panel.getTileManager().save(this.m_fileChooser.getSelectedFile());
        }       
	    } else if (arg0.getSource()==this.m_btnLockLayout) {
	      this.getPanel().setLocked(!this.getPanel().getLocked());
	      this.setLockedStatus();
	    } else if (arg0.getSource()==this.m_btnAddTile) {
	      this.m_panel.addTile();
	    } else if (arg0.getSource()==this.m_btnDeleteTile) {
        int index = this.getPanel().getTileManager().getSelectedIndex();
        if (index>=0) {
          this.m_panel.getTileManager().remove(index);
        }       
	    } else if (arg0.getSource()==this.m_btnClearTiles) {
	      this.m_panel.getTileManager().getTiles().clear();
	    } else if (arg0.getSource()==this.m_btnTileInfo) {
        int index = this.getPanel().getTileManager().getSelectedIndex();
        if (index>=0) {
          Tile tile = this.getPanel().getTileManager().getTiles().elementAt(index);
          TileEditorFormTilesLayoutInfo form = new TileEditorFormTilesLayoutInfo();
          form.setTile(tile);
          if (form.ShowDialog()==TileEditorFormTilesLayoutInfo.OPTION_OK) {
            tile.setName(form.getName());
            tile.setX(form.getX());
            tile.setY(form.getY());
            tile.setWidth(form.getWidth());
            tile.setHeight(form.getHeight());
          }
        }       
      } else if (arg0.getSource()==this.m_btnTileSpacing) {
        TileEditorFormTilesLayoutSpacing form = new TileEditorFormTilesLayoutSpacing();
        form.setSpacing(this.getPanel().getTileSpacing());
        if (form.ShowDialog()==TileEditorFormTilesLayoutSpacing.OPTION_OK) {
          this.getPanel().setTileSpacing(form.getSpacing());
          this.getPanel().setBackgroundColor(form.getBackgroundColor());
          this.getPanel().repaint();
        }          
	    } else if (arg0.getSource()==this.m_btnZoom100p) {
        this.m_panel.setZoom(1.0);
        BufferedImage image = this.m_panel.getTileManager().getImage();       
        if (image!=null) {
          int w = (int)(image.getWidth()*this.m_panel.getZoom());
          int h = (int)(image.getHeight()*this.m_panel.getZoom());
          this.m_panel.setPreferredSize(new Dimension(w,h));          
        }
	    } else if (arg0.getSource()==this.m_btnZoomIn) {
	      this.m_panel.setZoom(this.m_panel.getZoom()*2);
	      BufferedImage image = this.m_panel.getTileManager().getImage();       
	      if (image!=null) {
	        int w = (int)(image.getWidth()*this.m_panel.getZoom());
	        int h = (int)(image.getHeight()*this.m_panel.getZoom());
	        this.m_panel.setPreferredSize(new Dimension(w,h));    
	      }
	    } else if (arg0.getSource()==this.m_btnZoomOut) {     
	      this.m_panel.setZoom(this.m_panel.getZoom()*0.5);
	      BufferedImage image = this.m_panel.getTileManager().getImage();       
	      if (image!=null) {
	        int w = (int)(image.getWidth()*this.m_panel.getZoom());
	        int h = (int)(image.getHeight()*this.m_panel.getZoom());
	        this.m_panel.setPreferredSize(new Dimension(w,h));          
	      }
	    }	    
	  } // if (this.getPanel().getTileManager()==null)
	}
	
	private void setLockedStatus() {
		if (this.getPanel().getLocked()!=this.m_locked) {
			this.m_locked = this.getPanel().getLocked();
			if (this.m_locked) {
				this.m_btnLockLayout.setIcon(new ImageIcon(this.m_imgLocked));
			} else {
				this.m_btnLockLayout.setIcon(new ImageIcon(this.m_imgUnLocked));					
			}
		}
	}
	
	public void selected(EventArgs event) {
		if (event.getSender()==this.getPanel()) {
			this.setLockedStatus();
			
			Tile tile = this.getPanel().getTileManager().getSelectedTile();		
			if (tile!=null) {
	      this.m_lblTileInfo.setText("" + tile.getName() + " "
	          + "(" + tile.getX()
	          + "," + tile.getY()
	          + "," + tile.getWidth()
	          + "," + tile.getHeight()
	          + ")"
	      );			  
			}
		}
	}

	public TileEditorPanelTilesLayout getPanel() {return this.m_panel;}

}
