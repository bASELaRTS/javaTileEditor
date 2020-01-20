package engine;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GFrame extends JFrame {
  private static final long serialVersionUID = 1L;

  private GPanel m_panel;
  
  public GFrame(Engine engine) {
    super();
    
    this.m_panel = new GPanel(engine);
    
    this.setTitle(engine.getName());
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.add(this.getPanel(),BorderLayout.CENTER);
    this.pack();
    this.setVisible(true);    
  } 
  
  public GPanel getPanel() {return this.m_panel;}
}
