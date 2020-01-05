package TileEditor;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TileEditorFormMapInfo {
  public static int OPTION_CANCEL = 0;
  public static int OPTION_OK = 1;
  
  private JLabel m_lblMapName;
  private JLabel m_lblMapWidth;
  private JLabel m_lblMapHeight;
  private JLabel m_lblTileWidth;
  private JLabel m_lblTileHeight;
  private JLabel m_lblVisible;
  
  private JTextField m_txtMapName;
  private JTextField m_txtMapWidth;
  private JTextField m_txtMapHeight;
  private JTextField m_txtTileWidth;
  private JTextField m_txtTileHeight;  
  private JCheckBox m_cbxVisible;
  
  private JPanel m_panelMain;
  
  public TileEditorFormMapInfo() {
    super();
    
    this.m_lblMapName = new JLabel("Name");
    this.m_lblMapWidth = new JLabel("Width");
    this.m_lblMapHeight = new JLabel("Height");
    this.m_lblTileWidth = new JLabel("TileWidth");
    this.m_lblTileHeight = new JLabel("TileHeight");
    this.m_lblVisible = new JLabel("Visible");
    
    this.m_txtMapName = new JTextField("",15);
    this.m_txtMapWidth = new JTextField("20",4);
    this.m_txtMapHeight = new JTextField("15",4);
    this.m_txtTileWidth = new JTextField("16",4);
    this.m_txtTileHeight = new JTextField("16",4);    
    this.m_cbxVisible = new JCheckBox();
    this.m_cbxVisible.setSelected(true);
    
    JPanel panelInput;
    panelInput = new JPanel();
    panelInput.setLayout(new GridLayout(6,2));
    panelInput.add(this.m_lblMapName);
    panelInput.add(this.m_txtMapName);
    panelInput.add(this.m_lblMapWidth);
    panelInput.add(this.m_txtMapWidth);
    panelInput.add(this.m_lblMapHeight);
    panelInput.add(this.m_txtMapHeight);
    panelInput.add(this.m_lblTileWidth);
    panelInput.add(this.m_txtTileWidth);
    panelInput.add(this.m_lblTileHeight);
    panelInput.add(this.m_txtTileHeight);
    panelInput.add(this.m_lblVisible);
    panelInput.add(this.m_cbxVisible);
    this.m_panelMain = panelInput;  
  }
    
  public int showDialog() {
    int result;    
    result = JOptionPane.showConfirmDialog(null, this.m_panelMain, "Map", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      return TileEditorFormMapInfo.OPTION_OK;
    }    
    return TileEditorFormMapInfo.OPTION_CANCEL;
  }
  
  public void setMapName(String s) {this.m_txtMapName.setText(s);}
  public String getMapName() {return this.m_txtMapName.getText();}
  public void setMapWidth(int i) {this.m_txtMapWidth.setText("" + i);}
  public int getMapWidth() {return Integer.parseInt(this.m_txtMapWidth.getText());}
  public void setMapHeight(int i) {this.m_txtMapHeight.setText("" + i);}
  public int getMapHeight() {return Integer.parseInt(this.m_txtMapHeight.getText());}
  public void setTileWidth(int i) {this.m_txtTileWidth.setText("" + i);}
  public int getTileWidth() {return Integer.parseInt(this.m_txtTileWidth.getText());}
  public void setTileHeight(int i) {this.m_txtTileHeight.setText("" + i);}
  public int getTileHeight() {return Integer.parseInt(this.m_txtTileHeight.getText());}
  public void setVisible(boolean b) {this.m_cbxVisible.setSelected(b);}
  public boolean getVisible() {return this.m_cbxVisible.isSelected();}
  
  public void setFromMap(TileMap map) {
    this.setMapName(map.getName());
    this.setMapWidth(map.getWidth());
    this.setMapHeight(map.getHeight());
    this.setTileWidth(map.getTileWidth());
    this.setTileHeight(map.getTileHeight());
    this.setVisible(map.getVisible());
  }
}
