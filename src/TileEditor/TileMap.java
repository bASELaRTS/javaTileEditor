package TileEditor;

import java.awt.Graphics;

import engine.Map;

public class TileMap extends Map {
  private ITileManager m_tileManager;
  private ITilePainter m_tilePainter;
  private boolean m_selected;
  private boolean m_locked;
  
  public TileMap() {
    super(null);
  }
  
  public void paintTile(int tile, int x, int y, int tw, int th, Graphics graphics) {
  	if (this.m_tilePainter!=null) {
  		this.m_tilePainter.paintTile(this, tile, x, y, tw, th, graphics);
  	}
  }
  
  public void setTilePainter(ITilePainter tilePainter) {this.m_tilePainter = tilePainter;}
  public ITilePainter getTilePainter() {return this.m_tilePainter;}
  
  public void setTileManager(ITileManager tileManager) {this.m_tileManager=tileManager;}
  public ITileManager getTileManager() {return this.m_tileManager;}
  
  public void setSelected(boolean b) {this.m_selected=b;}
  public boolean getSelected() {return this.m_selected;}
  
  public void setLocked(boolean b) {this.m_locked=b;}
  public boolean getLocked() {return this.m_locked;}
}
