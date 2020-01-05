package TileEditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

public class TileEditor extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;

  private JFileChooser m_fileChooser;
  
  private JMenuItem m_menuItemFileNew;
  private JMenuItem m_menuItemFileOpen;
  private JMenuItem m_menuItemFileSave;
  private JMenuItem m_menuItemFileRemove;
  private JMenuItem m_menuItemFileInfo;
  private JMenuItem m_menuItemFileExit;
  
  private JMenuItem m_menuItemViewMaps;
  private JMenuItem m_menuItemViewTiles;
  
  private JMenuItem m_menuItemViewZoom100p;
  private JMenuItem m_menuItemViewZoomIn;
  private JMenuItem m_menuItemViewZoomOut;

  private JButton m_btnToolbarNew;
  private JButton m_btnToolbarOpen;
  private JButton m_btnToolbarSave;
  private JButton m_btnToolbarZoomIn;
  private JButton m_btnToolbarZoomOut;
  private JButton m_btnToolbarZoom100p;
  
  private JLabel m_lblStatusBarText;
  
  private TileEditorPanel m_tilePanel;  
  private TileEditorFormMaps m_formMaps;
  private TileEditorFormTilesLayout m_formTiles;
  
  public TileEditor() {
    super();
    
    this.m_fileChooser = new JFileChooser();
    this.m_fileChooser.setCurrentDirectory(new File("D:\\bASEL\\Projects\\Java\\javaTileEditor\\data\\levels"));
    
    this.m_formMaps = new TileEditorFormMaps();
    this.m_formTiles = new TileEditorFormTilesLayout();
    
    this.m_tilePanel = new TileEditorPanel();
    this.m_tilePanel.setMapManager(this.m_formMaps.getPanel().getMapManager());
    this.m_tilePanel.setTileManager(this.m_formTiles.getPanel().getTileManager());

    JMenuBar menuBar;
    JMenu menu;
        
    // menubar
    menuBar = new JMenuBar();
        
    // menu file
    menu = new JMenu();
    menu.setText("File");
    menuBar.add(menu);
    
    this.m_menuItemFileNew = new JMenuItem();
    this.m_menuItemFileNew.setText("New...");
    this.m_menuItemFileNew.addActionListener(this);
    menu.add(this.m_menuItemFileNew);

    this.m_menuItemFileOpen = new JMenuItem();
    this.m_menuItemFileOpen.setText("Open...");
    this.m_menuItemFileOpen.addActionListener(this);
    menu.add(this.m_menuItemFileOpen);
    
    this.m_menuItemFileSave = new JMenuItem();
    this.m_menuItemFileSave.setText("Save...");
    this.m_menuItemFileSave.addActionListener(this);
    menu.add(this.m_menuItemFileSave);

    this.m_menuItemFileRemove = new JMenuItem();
    this.m_menuItemFileRemove.setText("Remove...");
    this.m_menuItemFileRemove.addActionListener(this);
    menu.add(this.m_menuItemFileRemove);

    menu.addSeparator();

    this.m_menuItemFileInfo = new JMenuItem();
    this.m_menuItemFileInfo.setText("Info...");
    this.m_menuItemFileInfo.addActionListener(this);
    menu.add(this.m_menuItemFileInfo);

    menu.addSeparator();
    
    this.m_menuItemFileExit = new JMenuItem();
    this.m_menuItemFileExit.setText("Exit");
    this.m_menuItemFileExit.addActionListener(this);
    menu.add(this.m_menuItemFileExit);
        
    // menu view
    menu = new JMenu();
    menu.setText("View");
    menuBar.add(menu);
    
    this.m_menuItemViewMaps= new JMenuItem();
    this.m_menuItemViewMaps.setText("Maps");
    this.m_menuItemViewMaps.addActionListener(this);
    menu.add(this.m_menuItemViewMaps);

    this.m_menuItemViewTiles = new JMenuItem();
    this.m_menuItemViewTiles.setText("Tiles");
    this.m_menuItemViewTiles.addActionListener(this);
    menu.add(this.m_menuItemViewTiles);
    
    menu.addSeparator();
    
    this.m_menuItemViewZoom100p = new JMenuItem();
    this.m_menuItemViewZoom100p.setText("Zoom 100%");
    this.m_menuItemViewZoom100p.addActionListener(this);
    menu.add(this.m_menuItemViewZoom100p);

    this.m_menuItemViewZoomIn = new JMenuItem();
    this.m_menuItemViewZoomIn.setText("Zoom In");
    this.m_menuItemViewZoomIn.addActionListener(this);
    menu.add(this.m_menuItemViewZoomIn);

    this.m_menuItemViewZoomOut = new JMenuItem();
    this.m_menuItemViewZoomOut.setText("Zoom Out");
    this.m_menuItemViewZoomOut.addActionListener(this);
    menu.add(this.m_menuItemViewZoomOut);

    // toolbar
    JToolBar toolbar;
    toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.setRollover(true);
    
    this.m_btnToolbarNew = new JButton();
    this.m_btnToolbarNew.setText("");
    this.m_btnToolbarNew.addActionListener(this);
    this.m_btnToolbarNew.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/new.png")));
    toolbar.add(this.m_btnToolbarNew);

    this.m_btnToolbarOpen = new JButton();
    this.m_btnToolbarOpen.setText("");
    this.m_btnToolbarOpen.addActionListener(this);
    this.m_btnToolbarOpen.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/folder.png")));
    toolbar.add(this.m_btnToolbarOpen);
    
    this.m_btnToolbarSave = new JButton();
    this.m_btnToolbarSave.setText("");
    this.m_btnToolbarSave.addActionListener(this);
    this.m_btnToolbarSave.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/save.png")));
    toolbar.add(this.m_btnToolbarSave);
    
    toolbar.addSeparator();
    
    this.m_btnToolbarZoom100p = new JButton();
    this.m_btnToolbarZoom100p.setText("");
    this.m_btnToolbarZoom100p.addActionListener(this);
    this.m_btnToolbarZoom100p.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/zoom.png")));
    toolbar.add(this.m_btnToolbarZoom100p);

    this.m_btnToolbarZoomIn = new JButton();
    this.m_btnToolbarZoomIn.setText("");
    this.m_btnToolbarZoomIn.addActionListener(this);
    this.m_btnToolbarZoomIn.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/zoom.png")));
    toolbar.add(this.m_btnToolbarZoomIn);

    this.m_btnToolbarZoomOut = new JButton();
    this.m_btnToolbarZoomOut.setText("");
    this.m_btnToolbarZoomOut.addActionListener(this);
    this.m_btnToolbarZoomOut.setIcon(new ImageIcon(Helper.Image.loadFromFile("data/TileEditor/zoom.png")));
    toolbar.add(this.m_btnToolbarZoomOut);


    toolbar.addSeparator();
    
    // panel
    JPanel panelMain;
    panelMain = new JPanel();
    panelMain.setMinimumSize(new Dimension(128,128));
    panelMain.setPreferredSize(new Dimension(320,240));
    panelMain.setLayout(new BorderLayout());
    
    panelMain.add(new JScrollPane(this.m_tilePanel),BorderLayout.CENTER);
    
    // statusbar
    JPanel panelStatusBar;
    panelStatusBar = new JPanel();
    panelStatusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
    panelStatusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
    
    this.m_lblStatusBarText = new JLabel();
    this.m_lblStatusBarText.setText("StatusBar");
    panelStatusBar.add(this.m_lblStatusBarText);
    
    // frame
    this.setTitle("TileEditor");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setJMenuBar(menuBar);
    this.add(toolbar,BorderLayout.PAGE_START);
    this.add(panelMain,BorderLayout.CENTER);
    this.add(panelStatusBar,BorderLayout.SOUTH);
    this.setResizable(true);
    this.pack();
    this.setLocationRelativeTo(null);
    this.setVisible(true);        
  }
  
  public static void main(String[] args) {
    new TileEditor();
  }  
  
	private void load(File file) {
		java.io.FileReader reader;
		java.io.BufferedReader stream;
		
		String str,strs[];
				
		try {
			reader = new FileReader(file);
			stream = new BufferedReader(reader);
		
			str = stream.readLine();
			while (str!=null) {
				strs = str.split("=");
				if (strs.length==2) {
					if (strs[0].equals("tilesheetimagefilename")) {
						this.m_formTiles.getPanel().loadImage(Helper.Image.loadFromFile(strs[1]));						
					} else if (strs[0].equals("tilesheetlayoutfilename")) {
						this.m_formTiles.getPanel().loadLayout(strs[1]);
						this.m_formTiles.getPanel().setLocked(true);
					} else if (strs[0].equals("mapfilename")) {
						TileMap map = new TileMap();
						map.load(strs[1]);
						map.setLocked(true);
						this.m_formMaps.getPanel().getMapManager().add(map);
					}
				}
				str = stream.readLine();
			}
			
			this.m_formTiles.repaint();
			this.m_formMaps.repaint();
						
			stream.close();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  
  
  public TileEditorPanel getPanel() {return this.m_tilePanel;}

  private void updateZoom() {
  	int maxWidth = 0;
  	int maxHeight = 0;
  	
  	for(int i=0;i<this.m_formMaps.getPanel().getMapManager().count();i++) {
  		TileMap map = this.m_formMaps.getPanel().getMapManager().elementAt(i);
  		int w = (int)((map.getWidth()*map.getTileWidth())*this.getPanel().getZoom());
  		int h = (int)((map.getHeight()*map.getTileHeight())*this.getPanel().getZoom());
  		if (w>maxWidth) maxWidth=w;
  		if (h>maxHeight) maxHeight=h;    		
  	}
  	
  	this.getPanel().setPreferredSize(new Dimension(maxWidth,maxHeight));
  	this.getPanel().repaint();  	
  }
  
  public void actionPerformed(ActionEvent arg0) {
    if (arg0.getSource()==this.m_menuItemFileExit) {
      this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    } else if (arg0.getSource()==this.m_menuItemViewMaps) {
    	this.m_formMaps.setVisible(true);
    } else if (arg0.getSource()==this.m_menuItemViewTiles) {
      this.m_formTiles.setVisible(true);
    } else if ((arg0.getSource()==this.m_menuItemFileNew)||(arg0.getSource()==this.m_btnToolbarNew)) {
    	this.m_formMaps.getPanel().getMapManager().clear();
    	this.m_formMaps.getPanel().repaint();
    } else if ((arg0.getSource()==this.m_menuItemFileOpen)||(arg0.getSource()==this.m_btnToolbarOpen)) {
    	this.m_fileChooser.setMultiSelectionEnabled(false);
    	if (this.m_fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
    		this.load(this.m_fileChooser.getSelectedFile());
    	}
    } else if ((arg0.getSource()==this.m_menuItemFileSave)||(arg0.getSource()==this.m_btnToolbarSave)) {    	
    } else if ((arg0.getSource()==this.m_menuItemViewZoom100p)||(arg0.getSource()==this.m_btnToolbarZoom100p)) {
    	this.getPanel().setZoom(1.0);
    	this.updateZoom();
    } else if ((arg0.getSource()==this.m_menuItemViewZoomIn)||(arg0.getSource()==this.m_btnToolbarZoomIn)) {
    	this.getPanel().setZoom(this.getPanel().getZoom()*2);
    	this.updateZoom();
    } else if ((arg0.getSource()==this.m_menuItemViewZoomOut)||(arg0.getSource()==this.m_btnToolbarZoomOut)) {
    	this.getPanel().setZoom(this.getPanel().getZoom()*0.5);
    	this.updateZoom();
    }
  }
}