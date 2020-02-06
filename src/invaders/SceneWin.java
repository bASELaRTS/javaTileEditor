package invaders;

import engine.Engine;
import engine.Keyboard;
import engine.Point;
import engine.Scene;

public class SceneWin extends Scene {
  private Point m_point;
  
  public SceneWin(Engine engine) {
    super(engine);
    
    this.m_point = new Point();
    this.m_point.setXY(20, 50);
  }

  public void update() {
    Keyboard keyboard = this.getEngine().getInput().getKeyboard();
    if (keyboard.isPressed()) {
      keyboard.clearPressed();
      
      Scene scene = this.getEngine().getScenes().elementAt(Invaders.SCENE_MAIN);
      scene.initialize();
      this.getEngine().getScenes().setActiveSceneIndex(Invaders.SCENE_MAIN);
    }
  }
  
  public void paint() {
    Invaders engine = (Invaders)this.getEngine();
    engine.getGraphics().drawFont("Winner", this.m_point.x, this.m_point.y, engine.getFont());
  }
}
