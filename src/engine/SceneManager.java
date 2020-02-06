package engine;

import java.util.Vector;

public class SceneManager {
	private Vector<Scene> m_list;
	private int m_sceneActiveIndex;
	
	public SceneManager() {
		this.m_list = new Vector<Scene>();
	}
	
	public void add(Scene scene) {this.m_list.add(scene);}
	public void remove(int index) {this.m_list.remove(index);}
	public Scene elementAt(int index) {return this.m_list.elementAt(index);}
	public int count() {return this.m_list.size();}
	
	public void update() {
		Scene scene = this.getActiveScene();
		if (scene!=null) {
			scene.update();
		}
	}
	
	public void paint() {
		Scene scene = this.getActiveScene();
		if (scene!=null) {
			scene.paint();
		}
	}
	
	public void setActiveSceneIndex(int i) {
	  if (i!=this.m_sceneActiveIndex) {
	    this.m_sceneActiveIndex=i;	    
	  }
	}
	public int getActiveSceneIndex() {return this.m_sceneActiveIndex;}
	public Scene getActiveScene() {
		for(int i=0;i<this.m_list.size();i++) {
			if (i==this.getActiveSceneIndex()) {
				return this.m_list.elementAt(i);
			}
		}
		return null;
	}
}
