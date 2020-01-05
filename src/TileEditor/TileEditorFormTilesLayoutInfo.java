package TileEditor;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TileEditorFormTilesLayoutInfo {
	public static int OPTION_CANCEL = 0;
	public static int OPTION_OK = 1;
	
	private JLabel m_lblName;
	private JLabel m_lblX;
	private JLabel m_lblY;
	private JLabel m_lblWidth;
	private JLabel m_lblHeight;
	
	private JTextField m_txtName;
	private JTextField m_txtX;
	private JTextField m_txtY;
	private JTextField m_txtWidth;
	private JTextField m_txtHeight;
	
	private JPanel m_panel;
	
	public TileEditorFormTilesLayoutInfo() {
		this.m_lblName = new JLabel("Name");
		this.m_lblX = new JLabel("X");
		this.m_lblY = new JLabel("Y");
		this.m_lblWidth = new JLabel("Width");
		this.m_lblHeight = new JLabel("Height");
		
		this.m_txtName = new JTextField("",15);
		this.m_txtX = new JTextField("0",4);
		this.m_txtY = new JTextField("0",4);
		this.m_txtWidth = new JTextField("16",4);
		this.m_txtHeight = new JTextField("16",4);
		
		JPanel panel;
		panel = new JPanel();
		panel.setLayout(new GridLayout(5,2));
		panel.add(this.m_lblName);
		panel.add(this.m_txtName);
		panel.add(this.m_lblX);
		panel.add(this.m_txtX);		
		panel.add(this.m_lblY);
		panel.add(this.m_txtY);		
		panel.add(this.m_lblWidth);
		panel.add(this.m_txtWidth);		
		panel.add(this.m_lblHeight);
		panel.add(this.m_txtHeight);	
		this.m_panel = panel;
	}

	public int ShowDialog() {
    int result;
    result = JOptionPane.showConfirmDialog(null, this.m_panel, "Map", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      return TileEditorFormTilesLayoutInfo.OPTION_OK;
    }    
    return TileEditorFormTilesLayoutInfo.OPTION_CANCEL;	
  }
		
	public void setName(String s) {this.m_txtName.setText(s);}
	public void setX(int i) {this.m_txtX.setText(""+i);}
	public void setY(int i) {this.m_txtY.setText(""+i);}
	public void setWidth(int i) {this.m_txtWidth.setText(""+i);}
	public void setHeight(int i) {this.m_txtHeight.setText(""+i);}
	
	public String getName() {return this.m_txtName.getText();}
	public int getX() {return Integer.parseInt(this.m_txtX.getText());}
	public int getY() {return Integer.parseInt(this.m_txtY.getText());}
	public int getWidth() {return Integer.parseInt(this.m_txtWidth.getText());}
	public int getHeight() {return Integer.parseInt(this.m_txtHeight.getText());}
	
	public void setTile(Tile tile) {
		this.setName(tile.getName());
		this.setX(tile.getX());
		this.setY(tile.getY());
		this.setWidth(tile.getWidth());
		this.setHeight(tile.getHeight());		
	}
}
