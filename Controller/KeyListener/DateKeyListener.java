package Controller.KeyListener;

import java.awt.event.KeyEvent;

public class DateKeyListener extends DecimalKeyListener {

	public DateKeyListener(){
		super();
		this.keyList.add(KeyEvent.VK_PERIOD);
	}
}
