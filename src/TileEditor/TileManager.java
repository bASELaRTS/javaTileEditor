package TileEditor;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class TileManager implements ITileManager, ISelectionManager {
	private Vector<Tile> m_tiles;
	private BufferedImage m_image;
	
	public TileManager() {
		this.m_tiles = new Vector<Tile>();
		this.setSelectedIndex(-1);
	}
	
	public void load(File file) {
		java.io.FileReader reader;
		java.io.BufferedReader stream;
		
		this.m_tiles.clear();
		
		try {
			reader = new FileReader(file);
			stream = new BufferedReader(reader);	
			
			int count = Integer.parseInt(stream.readLine());
			for(int i=0;i<count;i++) {
				String line = stream.readLine();
				String strs[] = line.split(";");

				Tile tile = new Tile();
				tile.setName(strs[0]);
				tile.setX(Integer.parseInt(strs[1]));
				tile.setY(Integer.parseInt(strs[2]));
				tile.setWidth(Integer.parseInt(strs[3]));
				tile.setHeight(Integer.parseInt(strs[4]));
				tile.setImage(null);
				
				this.m_tiles.add(tile);
			}
			
			stream.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save(File file) {
		java.io.FileWriter writer;
		java.io.BufferedWriter stream;
		
		try {
			writer = new FileWriter(file);
			stream = new BufferedWriter(writer);		
			
			stream.write(""+this.m_tiles.size()+"\r\n");
			for(int i=0;i<this.m_tiles.size();i++) {
				Tile tile = this.m_tiles.elementAt(i);
				stream.write(
						tile.getName()
						+ ";" + tile.getX()
						+ ";" + tile.getY()
						+ ";" + tile.getWidth()
						+ ";" + tile.getHeight()
						+ "\r\n"
				);
			}
			
			stream.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setImage(BufferedImage image) {this.m_image=image;}
	public BufferedImage getImage() {return this.m_image;}
	public Vector<Tile> getTiles(){return this.m_tiles;}
	
	public Tile getSelectedTile() {
    for(int i=0;i<this.m_tiles.size();i++) {
      Tile tile = this.m_tiles.elementAt(i);
      if (tile.getSelected()) {
        return tile;
      }
    }
	  return null;
	}
	
	// interface function
	public void clear() {this.m_tiles.clear();}
	public void add(Tile tile) {this.m_tiles.add(tile);}
	public void add(BufferedImage image) {this.m_tiles.add(new Tile(image,0,0));}
	public BufferedImage getImage(int index) {
		Tile tile;
		BufferedImage image;
		
		tile = this.m_tiles.elementAt(index);
		image = tile.getImage();
		if (image==null) {
			if (this.m_image!=null) {
				image = new BufferedImage(tile.getWidth(),tile.getHeight(),BufferedImage.TYPE_INT_ARGB);
				Graphics graphics = image.getGraphics();
				graphics.drawImage(this.m_image
					,0,0,tile.getWidth(),tile.getHeight()
					,tile.getX(),tile.getY(),tile.getX()+tile.getWidth(),tile.getY()+tile.getHeight()
					,null
				);
			}
		}
		
		return image;
	}
	public void remove(int index) {this.m_tiles.remove(index);}	
	public int count() {return this.m_tiles.size();}
	public Tile elementAt(int index) {return this.m_tiles.elementAt(index);	}
	
	// ISelectionManager
  public void setSelectedIndex(int index) {
    for(int i=0;i<this.m_tiles.size();i++) {
      Tile tile = this.m_tiles.elementAt(i);
      if (i==index) {
        tile.setSelected(true);
      } else {
        tile.setSelected(false);
      }
    }
  }
  public int getSelectedIndex() {
    for(int i=0;i<this.m_tiles.size();i++) {
      Tile tile = this.m_tiles.elementAt(i);
      if (tile.getSelected()) {
        return i;
      }
    }
    return -1;
  }

}