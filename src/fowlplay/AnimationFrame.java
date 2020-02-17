package fowlplay;

public class AnimationFrame {
  private int m_imageIndex;
  private int m_delay;

  public AnimationFrame() {
    this.setDelay(150);
    this.setImageIndex(-1);
  }
  
  public AnimationFrame(int delay, int imageIndex) {
    this.setDelay(delay);
    this.setImageIndex(imageIndex);
  }
  
  public void setDelay(int i) {this.m_delay=i;}
  public int getDelay() {return this.m_delay;}
  public void setImageIndex(int i) {this.m_imageIndex=i;}
  public int getImageIndex() {return this.m_imageIndex;}
}