package platformer;

import engine.Engine;
import engine.Entity;
import engine.Keyboard;
import engine.Keyboard.Key;
import engine.graphics.Color;
import engine.graphics.IGraphics;

public class Console extends Entity {
	private String[] m_lines;
	private int m_lineIndex;
	private Color m_lineColor;
	
	public Console(Engine engine, int lineCount) {
		super(engine);
		
		this.setName("console");
		this.setVisible(false);
		
		this.m_lines = new String[lineCount];
		this.m_lineIndex = 0;
		this.m_lineColor = new Color(255,255,255);
		
		this.clear();
	}
	
	public void clear() {
		for(int i=0;i<this.m_lines.length;i++) {
			this.m_lines[i]="";
		}
		this.m_lineIndex = 0;
	}
	
	public void println(String string) {
		if (this.m_lineIndex<this.m_lines.length) {
			this.m_lines[this.m_lineIndex] = string;
			this.m_lineIndex++;
		} else {
			for(int i=1;i<this.m_lines.length;i++) {
				this.m_lines[i-1] = this.m_lines[i];
			}
			this.m_lines[this.m_lines.length-1]=string;
		}
	}
	
	public void update() {
		Keyboard keyboard = this.getEngine().getInput().getKeyboard();
		Key key = keyboard.getKeyPressed();
		if (key!=null) {
			if (key.getKeyCode()==128) {
				key.setState(false);
				this.setVisible(!this.getVisible());
			}
		}
	}
	
	public void paint() {
		IGraphics graphics = this.getEngine().getGraphics();
		int x = 2;
		int y = 12;
		for(int i=0;i<this.m_lines.length;i++) {
			String str = this.m_lines[i];
			graphics.drawString(str, x, y, this.m_lineColor.getColor());
			y+=12;
		}
	}
}
