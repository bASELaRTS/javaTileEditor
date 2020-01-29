package engine.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import engine.LayoutManager;
import engine.Size;

public class Font {
  private Vector<Character> m_characters;
  private int m_characterSpacing;
  private Size m_defaultSize;
  
  public Font() {
    this.m_characters = new Vector<Character>();
    this.m_defaultSize = new Size(8,8);
    this.setCharacterSpacing(1);
  }
  
  public void load(LayoutManager layoutManager, BufferedImage image) {
    this.m_characters.clear();
    
    for(int i=0;i<layoutManager.getBlocks().size();i++) {
      LayoutManager.Block block = layoutManager.getBlocks().elementAt(i);
      
      Character character = new Character();
      character.getSize().setSize(block.getSize());
      character.setCode(Integer.parseInt(block.getName()));
      
      BufferedImage img = new BufferedImage(character.getSize().getWidth(),character.getSize().getHeight(),BufferedImage.TYPE_INT_ARGB);
      Graphics graphics = img.getGraphics();
      graphics.drawImage(
          image, 
          0, 0, character.getSize().getWidth(), character.getSize().getHeight(), 
          block.getPoint().x, block.getPoint().y, block.getPoint().x + block.getSize().getWidth(), block.getPoint().y + block.getSize().getHeight(), 
          null
      );
      character.setImage(img);
      
      this.m_characters.add(character);
    }
  }
  
  public Character getCharacter(int index) {
    return this.m_characters.elementAt(index);
  }
  
  public Character getCharacter(char ch) {
    int i;
    Character character;
    for(i=0;i<this.m_characters.size();i++) {
      character = this.m_characters.elementAt(i);
      if (character.getCode() == (int)ch) {
        return character;
      }
    }
    return null;
  }
  
  public void setCharacterSpacing(int spacing) {this.m_characterSpacing=spacing;}
  public int getCharacterSpacing() {return this.m_characterSpacing;}
  public Size getDefaultSize() {return this.m_defaultSize;}
  
  public class Character {
    private int m_code;
    private BufferedImage m_image;
    private Size m_size;
    
    public Character() {
      this.m_size = new Size();      
      this.setCode(0);
      this.setImage(null);
    }    
    
    public void setCode(int ch) {this.m_code=ch;}
    public int getCode() {return this.m_code;}
    
    public void setImage(BufferedImage image) {this.m_image = image;}
    public BufferedImage getImage() {return this.m_image;}
    
    public Size getSize() {return this.m_size;}
  }
}
