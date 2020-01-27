package engine;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class LayoutManager {
  private Vector<Block> m_blocks;
  private BufferedImage m_image;
  
  public LayoutManager() {
    this.m_blocks = new Vector<Block>();
  }
  
  public void load(String filename) {
    this.load(new java.io.File(filename));
  }
  public void load(java.io.File file) {
    java.io.FileReader reader;
    java.io.BufferedReader stream;
    
    this.m_blocks.clear();
    
    try {
      reader = new FileReader(file);
      stream = new BufferedReader(reader);  
      
      int count = Integer.parseInt(stream.readLine());
      for(int i=0;i<count;i++) {
        String line = stream.readLine();
        String strs[] = line.split(";");

        Block block = new Block();
        block.setName(strs[0]);
        block.getPoint().x = (Integer.parseInt(strs[1]));
        block.getPoint().y = (Integer.parseInt(strs[2]));
        block.getSize().setWidth(Integer.parseInt(strs[3]));
        block.getSize().setHeight(Integer.parseInt(strs[4]));
        
        this.add(block);
      }
      
      stream.close();
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
  
  public void save(String filename) {
    this.save(new java.io.File(filename));
  }
  public void save(java.io.File file) {
    java.io.FileWriter writer;
    java.io.BufferedWriter stream;
    
    try {
      writer = new FileWriter(file);
      stream = new BufferedWriter(writer);    
      
      stream.write(""+this.m_blocks.size()+"\r\n");
      for(int i=0;i<this.m_blocks.size();i++) {
        Block block = this.m_blocks.elementAt(i);
        stream.write(
            block.getName()
            + ";" + block.getPoint().x
            + ";" + block.getPoint().y
            + ";" + block.getSize().getWidth()
            + ";" + block.getSize().getHeight() 
            + "\r\n" 
        );
      }
      
      stream.close();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
  
  public void add(String name, int x, int y, int w, int h) {
    Block block = new Block(name,x,y,w,h);
    this.add(block);
  }
  
  public void add(Block block) {
    if (block!=null) {
      this.m_blocks.add(block);
    }
  }
  
  public Block getBlock(int index) {
    if ((this.m_blocks.size()>0)&&(index<this.m_blocks.size())) {
      return this.m_blocks.elementAt(index);
    }
    return null;
  }
  
  public Block getBlock(String name) {
    int i;
    Block b;
    for(i=0;i<this.m_blocks.size();i++) {
      b = this.m_blocks.elementAt(i);
      if (b.getName().equals(name)) {
        return b;
      }
    }
    return null;
  }

  public Vector<Block> getBlocks(){return this.m_blocks;}
  public void setImage(BufferedImage image) {
    this.m_image=image;
    
    if (this.m_image!=null) {
      int i;
      int x,y,w,h;
      Block b;
      for(i=0;i<this.m_blocks.size();i++) {
        b = this.m_blocks.elementAt(i);
        x = b.getPoint().x;
        y = b.getPoint().y;
        w = b.getSize().getWidth();
        h = b.getSize().getHeight();
        
        BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics graphics = img.getGraphics();
        graphics.drawImage(image,0,0,w,h,x,y,x+w,y+h,null);
        b.setImage(img);
      }      
    }
  }
  public BufferedImage getImage() {return this.m_image;}
    
  public class Block {
    private String m_name;
    private Point m_point;
    private Size m_size;
    private BufferedImage m_image;
    
    public Block() {
      this.m_size = new Size();
      this.m_point = new Point();
      this.setName("");
    }
    public Block(String name, int x, int y, int w, int h) {
      this.m_size = new Size(w,h);
      this.m_point = new Point(x,y);
      this.setName(name);
    }
    
    public Size getSize() {return this.m_size;}
    public Point getPoint() {return this.m_point;}
    public void setName(String s) {this.m_name=s;}
    public String getName() {return this.m_name;}
    public void setImage(BufferedImage image) {this.m_image=image;}
    public BufferedImage getImage() {return this.m_image;}
  }
}
