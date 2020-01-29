package invaders;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import engine.Engine;
import engine.Keyboard;
import engine.math.Vector3;

public class Player extends Actor {
  public Player(Engine engine) {
    super(engine);
    this.setName("player");
    this.getSize().setSize(14, 8);
    this.getGravity().setCoordinates(0, 0, 0);
    this.getSpeedMax().setCoordinates(2, 2, 2);
    this.getSpeedMin().setCoordinates(-2, -2, -2);
  }
  
  
  public void update() {
    Keyboard keyboard = this.getEngine().getInput().getKeyboard();
    Vector3 acceleration = new Vector3(this.getAcceleration());
    Vector3 speed = new Vector3(this.getSpeed());
    Vector3 position = new Vector3(this.getPosition());
    
    if (keyboard.isPressed(KeyEvent.VK_LEFT)) {
      acceleration.x -= 3;
    } else if (keyboard.isPressed(KeyEvent.VK_RIGHT)) {
      acceleration.x += 3;
    } else {
      if (speed.x>0.5) {
        speed.x -= 0.5;
      } else if (speed.x<-0.5) {
        speed.x += 0.5;
      } else {
        speed.x = 0.0;
      }
    }

    if (keyboard.isPressed(KeyEvent.VK_UP)) {
      acceleration.y -= 3;
    } else if (keyboard.isPressed(KeyEvent.VK_DOWN)) {
      acceleration.y += 3;
    } else {
      if (speed.y>0.5) {
        speed.y -= 0.5;
      } else if (speed.y<-0.5) {
        speed.y += 0.5;
      } else {
        speed.y = 0.0;
      }      
    }
    
    if (keyboard.isPressed(KeyEvent.VK_SPACE)) {
      keyboard.find(KeyEvent.VK_SPACE).setState(false);
      Bullit bullit = new Bullit(this.getEngine());
      SceneMain scene = ((Invaders)this.getEngine()).getSceneMain();
      bullit.getPosition().x = this.getPosition().x + (this.getSize().getWidth()*0.5);
      bullit.getPosition().y = this.getPosition().y - (bullit.getHeight());
      scene.getEntities().add(bullit);
    }

    this.getSpeed().setVector(speed);
    this.getAcceleration().setVector(acceleration);
    
    super.update();
    
    position.setVector(this.getPosition());
    if (position.x<0) position.x = 0;
    if (position.x>=(this.getEngine().getWidth()-this.getWidth())) position.x = (this.getEngine().getWidth()-this.getWidth()-1);    
    if (position.y<0) position.y = 0;
    if (position.y>=(this.getEngine().getHeight()-this.getHeight())) position.y = (this.getEngine().getHeight()-this.getHeight()-1);
    this.getPosition().setVector(position);
    
    this.getAcceleration().setCoordinates(0, 0, 0);
  }
  
  public void paint() {
    int x = (int)this.getPosition().x;
    int y = (int)this.getPosition().y;
    int w = this.getSize().getWidth();
    int h = this.getSize().getHeight();
    
    Invaders engine = (Invaders)this.getEngine();
    BufferedImage image = engine.getSprites(0).getBlock(0).getImage();
    engine.getGraphics().drawImage(image, x, y, w, h);
  }
}
