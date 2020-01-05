package TileEditor;

import java.awt.Graphics;

public interface ITilePainter {
	public void paintTile(int tile, int x, int y, int tw, int th, Graphics graphics);
}
