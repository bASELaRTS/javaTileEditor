package fowlplay;

import engine.Engine;
import engine.LinkedList;

public class Animation {
  private Engine m_engine;
  private LinkedList m_list;
  
  private int m_frameIndex;
  private long m_frameTimestamp;
  private boolean m_looping;
  private boolean m_animationFinished;
  
  public Animation(Engine engine) {
    this.m_list = new LinkedList();
    this.m_engine = engine;
    this.m_looping = true;
    this.m_animationFinished = false;
  }
  
  public void add(AnimationFrame frame) {
    if (frame!=null) {
      this.m_list.add(frame);
    }
  }
  
  public void update() {
    long ms = this.m_engine.getTimer().getTimestamp();
    AnimationFrame frame = this.getFrame();
    int nextFrameIndex;
    if ((ms-this.m_frameTimestamp)>frame.getDelay()) {
      this.m_frameTimestamp = ms;
      nextFrameIndex = this.m_frameIndex+1;
      if (nextFrameIndex>=this.m_list.count()) {
        if (this.m_looping) {
          this.m_frameIndex = 0;
        } else {
          this.m_animationFinished = true;
        }
      } else {
        this.m_frameIndex = nextFrameIndex;
      }
    }
  }
  
  public AnimationFrame getFrame() {return (AnimationFrame)this.m_list.item(this.m_frameIndex).getObject();}
  public void setLooping(boolean b) {this.m_looping=b;}
  public boolean getLooping() {return this.m_looping;}
  public boolean isAnimationFinished() {return this.m_animationFinished;}
}
