package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * 
 * @author Patrick
 *
 */
public class Menu extends Observable implements ActionListener{

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem exit;
	private ArrayList<JMenuItem> itemList;
	private final int AUFGABENANZAHL = 10;
	private final int[] ACTIVEAUFGABE = {1,2,3,4,5};
	
	
	/**
	 * 
	 */
	public Menu(){
		this.menuBar	= new JMenuBar();
		this.menu 		= new JMenu("Aufgaben");
		this.exit 		= new JMenuItem("Beenden");
		this.itemList 	= new ArrayList<JMenuItem>();
		
		this.menu.setMnemonic(KeyEvent.VK_A);
		this.initMenu();
	}

	/**
	 * 
	 */
	private void initMenu() {
		
		for(int i=1;i<=AUFGABENANZAHL;i++){
			this.itemList.add(new JMenuItem("Aufgabe_"+i));
			this.itemList.get(this.itemList.size()-1).setEnabled(false);
		
			for(int j = 0;j<this.ACTIVEAUFGABE.length ; j++){
				if(this.ACTIVEAUFGABE[j] == i){
					this.itemList.get(this.itemList.size()-1).setEnabled(true);
				}
			}
		}
		
		for(JMenuItem item:this.itemList){
			
			if(this.itemList.indexOf(item) == 0){
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));	
			}else if(this.itemList.indexOf(item) == 1){
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));	
			}else if(this.itemList.indexOf(item) == 2){
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));	
			}else if(this.itemList.indexOf(item) == 3){
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));	
			}else if(this.itemList.indexOf(item) == 4){
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));	
			}else if(this.itemList.indexOf(item) == 5){
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, ActionEvent.ALT_MASK));	
			}else if(this.itemList.indexOf(item) == 6){
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, ActionEvent.ALT_MASK));	
			}else if(this.itemList.indexOf(item) == 7){
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_8, ActionEvent.ALT_MASK));	
			}else if(this.itemList.indexOf(item) == 8){
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_9, ActionEvent.ALT_MASK));	
			}else if(this.itemList.indexOf(item) == 9){
				item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, ActionEvent.ALT_MASK));	
			}
			
			item.addActionListener(this);
			
			this.menu.add(item);
		}
		
		this.menu.addSeparator();
		this.exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));	
		this.exit.addActionListener(this);
		
		this.menu.add(this.exit);
		this.menuBar.add(this.menu);
	}

	
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.setChanged();
		this.notifyObservers(arg0);
	}
}
