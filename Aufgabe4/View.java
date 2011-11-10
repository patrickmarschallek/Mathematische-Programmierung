package Aufgabe4;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class View extends Observable implements ActionListener, Observer {

	private Model model;
	private JPanel panel;
	private JTextField eingabe,checkDigit;

	public View(Model model) {
		this.model = model;
		this.model.addObserver(this);

		this.eingabe = new JTextField("", 9);
		this.checkDigit = new JTextField("",1);
		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				"Aufgabe-4 modular Berechnung (ISBN 10)"));
		panel.setLayout(new GridBagLayout());
		
		JPanel input = new JPanel(new GridBagLayout());
		
		View.addComponent(input, (GridBagLayout) input.getLayout(),
				new JLabel("ISBN-10"), 0, 0, 5, 1, 0, 0);
		View.addComponent(input, (GridBagLayout) input.getLayout(),
				this.eingabe, 0, 1, 1, 5, 1.0, 0);
		View.addComponent(input, (GridBagLayout) input.getLayout(),
				new JLabel("Prüfziffer"), 1, 0, 1, 1, 0.1, 0.1);
		View.addComponent(input, (GridBagLayout) input.getLayout(),
				this.checkDigit, 1, 1, 1, 1, 0.1, 0.1);
		
		
		JButton check = new JButton("ISBN prüfen");
		check.addActionListener(this);
		
		JButton generate = new JButton("ISBN Prüfziffer berechnen");
		generate.addActionListener(this);
		
		JButton calculate = new JButton("ISBN wiederherstellen");
		calculate.addActionListener(this);
		
		View.addComponent(this.panel, (GridBagLayout) this.panel.getLayout(),
				input, 0, 0, 3, 1, 0, 0);
		View.addComponent(this.panel, (GridBagLayout) this.panel.getLayout(),
				check, 0, 2, 1, 1, 0, 0);
		View.addComponent(this.panel, (GridBagLayout) this.panel.getLayout(),
				generate, 1, 2, 1, 1, 0, 0);
		View.addComponent(this.panel, (GridBagLayout) this.panel.getLayout(),
				calculate, 2, 2, 1, 1, 0, 0);
			
	}

	public JPanel getPanel() {
		return this.panel;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		char checkDigit = this.model.getCheckDigit();
		String isbn10	= this.model.getIsbn10();

		this.checkDigit.setText(""+checkDigit);
		this.eingabe.setText(isbn10);
		
	}

	public JTextField getEingabe() {
		return eingabe;
	}

	public JTextField getCheckDigit() {
		return checkDigit;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.setChanged();
		this.notifyObservers(arg0);
	}

	private static void addComponent(Container cont, GridBagLayout gbl,
			Component c, int x, int y, int width, int height, double weightx,
			double weighty) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.insets = new Insets(15, 15, 5, 5);
		gbc.ipady = 5;
		gbc.ipadx = 5;
		gbl.setConstraints(c, gbc);
		cont.add(c);
	}
}
