package Controller.KeyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class DualKeyListener implements KeyListener {

	protected ArrayList<Integer> keyList;
	
	public DualKeyListener(){
		this.keyList = new ArrayList<Integer>();
		this.keyList.add(KeyEvent.VK_0);
		this.keyList.add(KeyEvent.VK_1);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {
		arg0.setKeyChar(Character.toUpperCase(arg0.getKeyChar()));
		if(!this.keyList.contains((int)arg0.getKeyChar())){
			arg0.consume();
		}
	}

}
