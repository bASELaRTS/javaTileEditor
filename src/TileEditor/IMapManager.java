package TileEditor;

public interface IMapManager {
	public void clear();
	public void add(TileMap map);
	public void insertBefore(int index, TileMap map);
	public void remove(int index);
	public void remove(TileMap map);
	public TileMap elementAt(int index);
	public int count();
}
