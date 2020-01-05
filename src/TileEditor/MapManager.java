package TileEditor;

import java.util.Vector;

public class MapManager implements IMapManager {
	private Vector<TileMap> m_list;
	
	public MapManager() {
		this.m_list = new Vector<TileMap>();
	}

	// interface function
	public void clear() {this.m_list.clear();}
	public void add(TileMap map) {this.m_list.add(map);}
	public void remove(int index) {this.m_list.remove(index);}
	public void remove(TileMap map) {this.m_list.remove(map);}
	public TileMap elementAt(int index) {return this.m_list.elementAt(index);}
	public int count() {return this.m_list.size();}

	public void insertBefore(int index, TileMap map) {
		this.m_list.insertElementAt(map, index);
	}

}
