package TileEditor;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TileEditorFormTilesLayoutSpacing {
  public static int OPTION_CANCEL = 0;
  public static int OPTION_OK = 1;
  
  private JLabel m_lblSpacing;  
  private JTextField m_txtSpacing;
  private JPanel m_panel;
  
  public TileEditorFormTilesLayoutSpacing() {
    this.m_lblSpacing = new JLabel("Spacing");
    this.m_txtSpacing = new JTextField("0",15);
    
    JPanel panel;
    panel = new JPanel();
    panel.setLayout(new GridLayout(1,2));
    panel.add(this.m_lblSpacing);
    panel.add(this.m_txtSpacing);
    this.m_panel = panel;
  }

  public int ShowDialog() {
    int result;
    result = JOptionPane.showConfirmDialog(null, this.m_panel, "TileSpacing", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      return TileEditorFormTilesLayoutSpacing.OPTION_OK;
    }    
    return TileEditorFormTilesLayoutSpacing.OPTION_CANCEL; 
  }
    
  public void setSpacing(int i) {this.m_txtSpacing.setText("" + i);}
  public int getSpacing() {return Integer.parseInt(this.m_txtSpacing.getText());}
}
