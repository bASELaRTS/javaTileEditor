package engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import engine.Engine;
import engine.Entity;

public class Map extends Entity {
  private int m_width;
  private int m_height;
  private int m_tileWidth;
  private int m_tileHeight;
  private int m_data[];
  
  public Map(Engine engine) {
    super(engine);
    
    this.setTileWidth(16);
    this.setTileHeight(16);
    this.setSize(0, 0);
    this.setVisible(true);
  }
  
  public void create(String name, int w, int h, int tw, int th) {
    this.setName(name);
    this.setSize(w, h);
    this.setTileWidth(tw);
    this.setTileHeight(th);
    
    this.m_data = new int[w*h];
    this.fill(-1);
  }  
  
  public void load(String filename) {
    this.load(new File(filename));
  }
  
  public void load(File file) {
    java.io.FileReader freader;
    java.io.BufferedReader reader;
    String name,str,strs[];
    int i,j,w,h,tw,th,t;
    
    try {
      freader = new FileReader(file);
      reader = new BufferedReader(freader);
      
      name = reader.readLine();
      w = Integer.parseInt(reader.readLine());
      h = Integer.parseInt(reader.readLine());
      tw = Integer.parseInt(reader.readLine());
      th = Integer.parseInt(reader.readLine());
      this.create(name, w, h, tw, th);
      
      for(j=0;j<h;j++) {
        str = reader.readLine();
        strs = str.split(";");
        for(i=0;i<w;i++) {
          t = Integer.parseInt(strs[i]);
          this.setTile(i,j,t);
        }
      }
      
      reader.close();
      freader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void save(String filename) {
    this.save(new File(filename));
  }
  
  public void save(File file) {
    try {
      java.io.FileWriter fwriter;
      java.io.BufferedWriter writer;
      fwriter = new FileWriter(file);
      writer = new BufferedWriter(fwriter);      
      writer.write(this.getName() + "\r\n");
      writer.write(""+this.getWidth()+"\r\n");
      writer.write(""+this.getHeight()+"\r\n");
      writer.write(""+this.getTileWidth()+"\r\n");
      writer.write(""+this.getTileHeight()+"\r\n");
      
      int i,j;
      int tile;
      for(j=0;j<this.getHeight();j++) {
        for(i=0;i<this.getWidth();i++) {
          tile = this.getTile(i, j);
          writer.write(""+tile);
          if (i<this.getWidth()-1) {
            writer.write(";");
          }
        }
        writer.write("\r\n");
      }
      
      writer.close();
      fwriter.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  public void fill(int tile) {
    for(int i=0;i<this.m_data.length;i++) {
      this.m_data[i] = tile;
    }
  }
  
  public void setTile(int index, int tile) {this.m_data[index]=tile;}
  public void setTile(int x, int y, int tile) {
    int index = y*this.getWidth()+x;
    this.setTile(index, tile);
  }  
  public int getTile(int index) {return this.m_data[index];}
  public int getTile(int x, int y) {
    if ((y>=0)&&(y<this.getHeight())&&(x>=0)&&(x<this.getWidth())) {
      int index = y*this.getWidth()+x;
      return this.getTile(index);
    }
    return -1;
  }
  
  public void paintTile(int tile, int x, int y, int tw, int th) {
  }
  
  public void paint() {
    int i,j;
    int w,h;
    int tx,ty;
    int tw,th;
    int tile;
    
    w = this.getWidth();
    h = this.getHeight();
    tw = this.getTileWidth();
    th = this.getTileHeight();
    
    ty = 0;
    for(j=0;j<h;j++) {
      tx = 0;
      for(i=0;i<w;i++) {
        tile = this.getTile(i, j);
        this.paintTile(tile,tx,ty,tw,th);
        tx+=tw;
      }
      ty+=th;
    }
  }
  
  protected void setWidth(int i) {this.m_width=i;}
  protected void setHeight(int i) {this.m_height=i;}
  protected void setSize(int w, int h) {
    this.setWidth(w);
    this.setHeight(h);
  }
  public int getWidth() {return this.m_width;}
  public int getHeight() {return this.m_height;}
  
  public void setTileWidth(int i) {this.m_tileWidth=i;}
  public void setTileHeight(int i) {this.m_tileHeight=i;}
  public int getTileWidth() {return this.m_tileWidth;}
  public int getTileHeight() {return this.m_tileHeight;}
}
