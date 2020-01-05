package TileEditor;

import java.awt.image.BufferedImage;

public interface ITileManager {
	public void clear();
	public void add(Tile tile);
	public void remove(int index);
	public Tile elementAt(int index);
	public BufferedImage getImage(int index);
	public int count();
}
