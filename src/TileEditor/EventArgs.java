package TileEditor;

public class EventArgs {
	private Object m_sender;

	public EventArgs() {
		this.setSender(null);
	}
	
	public void setSender(Object object) {this.m_sender=object;}
	public Object getSender() {return this.m_sender;}
}