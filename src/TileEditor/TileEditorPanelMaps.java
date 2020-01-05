package TileEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JPanel;

public class TileEditorPanelMaps extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private Vector<ISelected> m_selectedListener;
	private MapManager m_mapManager;
	private int m_rowHeight;
	
	public TileEditorPanelMaps() {
		super();
		
		this.m_selectedListener = new Vector<ISelected>();
		this.m_mapManager = new MapManager();
		this.m_rowHeight = 25;
		
		this.setPreferredSize(new Dimension(256,60));
		
		this.addMouseListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		int i;
		int w,h;
		int x,y;
		int c;
		TileMap map;		
		
		w = this.getWidth();
		h = this.getHeight();
		x = 0;
		y = 0;
		c = this.m_mapManager.count();
		for(i=0;i<c;i++) {
			map = this.m_mapManager.elementAt(i);

			if (map.getSelected()) {
				g.setColor(new Color(255,0,0,128));
			} else {
				g.setColor(Color.white);
			}
			g.fillRect(x, y, w, this.m_rowHeight);
			
			if (!map.getVisible()) {
				g.setColor(Color.lightGray);				
			} else {
				g.setColor(Color.black);
			}
			g.drawString(map.getName(), x+2, y+18);
			
			g.setColor(Color.black);
			g.drawRect(x, y, w, this.m_rowHeight);
			
			y+=this.m_rowHeight;
			
			if (y>=h) {
				i = c;
			}
		}
	}
	
	public void setSelectedMap(TileMap map) {
		for(int i=0;i<this.getMapManager().count();i++) {
			TileMap temp = this.getMapManager().elementAt(i);
			temp.setSelected(false);
		}
		map.setSelected(true);
		this.raiseSelectedListener();
	}
	
	public TileMap getSelectedMap() {
		for(int i=0;i<this.getMapManager().count();i++) {
			TileMap map = this.getMapManager().elementAt(i);
			if (map.getSelected()) {
				return map;
			}
		}
		return null;
	}
	
	public int getSelectedMapIndex() {
		for(int i=0;i<this.getMapManager().count();i++) {
			TileMap map = this.getMapManager().elementAt(i);
			if (map.getSelected()) {
				return i;
			}
		}
		return -1;
	}
	
	public void addSelectedListener(ISelected selectedListener) {
		this.m_selectedListener.add(selectedListener);
	}
	public void raiseSelectedListener() {
		for(int i=0;i<this.m_selectedListener.size();i++) {
			EventArgs event = new EventArgs();
			event.setSender(this);
			this.m_selectedListener.elementAt(i).selected(event);
		}
	}
	
	public IMapManager getMapManager() {return this.m_mapManager;}

	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {
		int i;
		int my;
		int c = this.getMapManager().count();
		int y;
		TileMap map;
		TileMap selected = this.getSelectedMap();
		
		y = arg0.getY();
		
		for(i=0;i<c;i++) {
			map = this.getMapManager().elementAt(i);
			map.setSelected(false);
		}
		
		my = 0;
		for(i=0;i<c;i++) {
			map = this.getMapManager().elementAt(i);
			if ((y>=my)&&(y<(my+this.m_rowHeight))) {
				map.setSelected(true);
			}
			my+=this.m_rowHeight;
		}

		map = this.getSelectedMap();
		if (selected!=map) {
			this.raiseSelectedListener();
		}
		
		this.repaint();
	}
	public void mouseReleased(MouseEvent arg0) {}
	
}
