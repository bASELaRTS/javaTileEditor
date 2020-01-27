package TileEditor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TileEditorFormTilesLayoutSpacing implements ActionListener {
  public static int OPTION_CANCEL = 0;
  public static int OPTION_OK = 1;
  
  private JLabel m_lblSpacing;  
  private JTextField m_txtSpacing;
  
  private JLabel m_lblBackgroundColor;
  private JButton m_btnBackgroundColor;
  private Color m_clrBackgroundColor;
  
  private JPanel m_panel;
  
  public TileEditorFormTilesLayoutSpacing() {
    this.m_lblSpacing = new JLabel("Spacing");
    this.m_txtSpacing = new JTextField("0",15);
    
    this.m_lblBackgroundColor = new JLabel("BackgroundColor");
    this.m_btnBackgroundColor = new JButton();
    this.m_btnBackgroundColor.addActionListener(this);
    
    JPanel panel;
    panel = new JPanel();
    panel.setLayout(new GridLayout(2,2));
    panel.add(this.m_lblSpacing);
    panel.add(this.m_txtSpacing);
    panel.add(this.m_lblBackgroundColor);
    panel.add(this.m_btnBackgroundColor);
    this.m_panel = panel;
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource()==this.m_btnBackgroundColor) {
      this.setBackgroundColor(JColorChooser.showDialog(null, "Choose background color", this.m_clrBackgroundColor));
    }
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
  public void setBackgroundColor(Color color) {
    this.m_clrBackgroundColor = color;
    this.m_btnBackgroundColor.setBackground(color);
  }
  public Color getBackgroundColor() {return this.m_clrBackgroundColor;}
}
