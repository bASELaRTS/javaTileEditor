package TileEditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class TileEditorFormMaps extends JFrame implements ActionListener, ISelected {
	private static final long serialVersionUID = 1L;
	
	private JFileChooser m_fileChooser;
	private TileEditorPanelMaps m_panel;
	
	private BufferedImage m_imgLocked;
	private BufferedImage m_imgUnLocked;

	private JButton m_btnAddMap;
	private JButton m_btnLoadMap;
	private JButton m_btnSaveMap;
	private JButton m_btnDeleteMap;	
	private JButton m_btnMapInfo;
	private JButton m_btnMapVisibility;
	private JButton m_btnMapLock;

	private JButton m_btnMapMoveUp;
	private JButton m_btnMapMoveDown;
	
	public TileEditorFormMaps() {
		super();
		
		this.m_fileChooser = new JFileChooser();
		this.m_fileChooser.setCurrentDirectory(new File("data/maps"));
		
		this.m_imgLocked = Helper.Image.loadFromFile("data/TileEditor/lock.png");
		this.m_imgUnLocked = Helper.Image.loadFromFile("data/TileEditor/unlock.png");

		this.m_panel = new TileEditorPanelMaps();
		this.m_panel.addSelectedListener(this);
		
		this.m_btnAddMap = new JButton();
		this.m_btnAddMap.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/new.png")));
		this.m_btnAddMap.addActionListener(this);

		this.m_btnLoadMap = new JButton();
		this.m_btnLoadMap.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/folder.png")));
		this.m_btnLoadMap.addActionListener(this);
		
		this.m_btnSaveMap = new JButton();
		this.m_btnSaveMap.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/save.png")));
		this.m_btnSaveMap.addActionListener(this);

		this.m_btnDeleteMap = new JButton();
		this.m_btnDeleteMap.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/delete.png")));
		this.m_btnDeleteMap.addActionListener(this);

		this.m_btnMapInfo = new JButton();
		this.m_btnMapInfo.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/info.png")));
		this.m_btnMapInfo.addActionListener(this);

		this.m_btnMapVisibility = new JButton();
		this.m_btnMapVisibility.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/eye.png")));
		this.m_btnMapVisibility.addActionListener(this);

		this.m_btnMapLock = new JButton();
		this.m_btnMapLock.setIcon(new ImageIcon(this.m_imgUnLocked));
		this.m_btnMapLock.addActionListener(this);

		this.m_btnMapMoveUp = new JButton();
		this.m_btnMapMoveUp.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/up.png")));
		this.m_btnMapMoveUp.addActionListener(this);

		this.m_btnMapMoveDown = new JButton();
		this.m_btnMapMoveDown.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/down.png")));
		this.m_btnMapMoveDown.addActionListener(this);

		JToolBar toolbar;
		toolbar = new JToolBar();
		toolbar.setFloatable(false);		
		toolbar.add(this.m_btnAddMap);
		toolbar.add(this.m_btnLoadMap);
		toolbar.add(this.m_btnSaveMap);
		toolbar.add(this.m_btnDeleteMap);
		toolbar.addSeparator();
		toolbar.add(this.m_btnMapInfo);
		toolbar.add(this.m_btnMapVisibility);
		toolbar.add(this.m_btnMapLock);
		toolbar.addSeparator();
		toolbar.add(this.m_btnMapMoveUp);
		toolbar.add(this.m_btnMapMoveDown);
		
		JPanel panel;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(toolbar,BorderLayout.CENTER);
		
		this.setTitle("Maps");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		this.add(this.m_panel,BorderLayout.CENTER);
		this.add(panel,BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
  public static void main(String[] args) {
  	TileEditorFormMaps form = new TileEditorFormMaps();
    form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  public TileEditorPanelMaps getPanel() {return this.m_panel;}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==this.m_btnAddMap) {
      TileEditorFormMapInfo form;
			TileMap map = new TileMap();
      form = new TileEditorFormMapInfo();
      if (form.showDialog()==TileEditorFormMapInfo.OPTION_OK) {
      	map.create(
      			form.getMapName(),
      			form.getMapWidth(),
      			form.getMapHeight(),
      			form.getTileWidth(),
      			form.getTileHeight()
      	);
      	map.setTileManager(new TileManager());
      	
  			this.m_panel.getMapManager().add(map);
      }
			this.m_panel.repaint();
		} else if (arg0.getSource()==this.m_btnLoadMap) {
			this.m_fileChooser.setMultiSelectionEnabled(false);
			if (this.m_fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				TileMap map = new TileMap();
				map.load(this.m_fileChooser.getSelectedFile());
				map.setLocked(true);				
				map.setTileManager(new TileManager());
				this.getPanel().getMapManager().add(map);
				
				this.getPanel().setSelectedMap(map);
				this.getPanel().repaint();
				
				this.setLockedStatus();
			}
		} else if (arg0.getSource()==this.m_btnSaveMap) {
			TileMap map = this.m_panel.getSelectedMap();
			if (map!=null) {
      	this.m_fileChooser.setMultiSelectionEnabled(false);
      	if (this.m_fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
        	map.save(this.m_fileChooser.getSelectedFile());
      	}				
			}
		} else if (arg0.getSource()==this.m_btnDeleteMap) {
			TileMap map = this.m_panel.getSelectedMap();
			if (map!=null) {
				this.m_panel.getMapManager().remove(map);
      	this.m_panel.repaint();
			}			
		} else if (arg0.getSource()==this.m_btnMapInfo) {
			TileMap map = this.getPanel().getSelectedMap();
			if (map!=null) {
        TileEditorFormMapInfo form = new TileEditorFormMapInfo();
        form.setFromMap(map);
        if (form.showDialog()==TileEditorFormMapInfo.OPTION_OK) {
          map.setName(form.getMapName());
          map.setTileWidth(form.getTileWidth());
          map.setTileHeight(form.getTileHeight());
          map.setVisible(form.getVisible());
          
          if ((map.getWidth()!=form.getMapWidth())||(map.getHeight()!=form.getMapHeight())) {
          	map.resize(form.getMapWidth(), form.getMapHeight());
          }
        }
        this.getPanel().repaint();		
			}
		} else if (arg0.getSource()==this.m_btnMapVisibility) {
			TileMap map = this.getPanel().getSelectedMap();
			if (map!=null) {
				map.setVisible(!map.getVisible());
			}
			this.getPanel().repaint();
		} else if (arg0.getSource()==this.m_btnMapLock) {
			TileMap map = this.getPanel().getSelectedMap();
			if (map!=null) {
				map.setLocked(!map.getLocked());
				this.setLockedStatus();
			}
		} else if (arg0.getSource()==this.m_btnMapMoveUp) {
			int index = this.getPanel().getSelectedMapIndex();
			if (index>0) {
			}
		} else if (arg0.getSource()==this.m_btnMapMoveDown) {
			int index = this.getPanel().getSelectedMapIndex();
			if (index>0) {				
			}			
		}
	}

	private void setLockedStatus() {
		TileMap map = this.getPanel().getSelectedMap();
		if (map!=null) {
			if (map.getLocked()) {
				this.m_btnMapLock.setIcon(new ImageIcon(this.m_imgLocked));
			} else {
				this.m_btnMapLock.setIcon(new ImageIcon(this.m_imgUnLocked));			
			}			
		}
	}
	
	// ISelected
	public void selected(EventArgs event) {
		if (event.getSender()==this.getPanel()) {		  
			this.setLockedStatus();
		}
	}  

}
