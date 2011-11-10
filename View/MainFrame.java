package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.MainModel;


public class MainFrame extends Observable implements Observer,ActionListener {

	private JFrame frame;
	private Menu menuBar;
	private MainModel model;
	
	public MainFrame(MainModel model) {
		this.model		= model;
		this.menuBar 	= new Menu();
		this.frame 		= new JFrame();
		
		this.model.addObserver(this);
		
		this.frame.setSize(500, 500);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setTitle("Mathematische Programmierung");
		this.frame.setJMenuBar(menuBar.getMenuBar());
		this.frame.toFront();
		this.frame.setAlwaysOnTop(true);
		this.frame.setVisible(true);
	}

	public Menu getMenuBar() {
		return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("View "+arg0.getActionCommand());
		this.setChanged();
		this.notifyObservers(arg0);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public void setPanel(JPanel panel) {
		this.frame.setContentPane(panel);
		this.frame.getContentPane().setVisible(true);
		this.frame.getContentPane().repaint();
		this.frame.validate();
		this.frame.repaint();
		this.frame.pack();
	}

}
