package Aufgabe3;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

import Controller.KeyListener.DateKeyListener;

public class View extends Observable implements ActionListener, Observer {

	private Model model;
	private JPanel panel;
	private JTextField datum, wochentag;

	public View(Model model) {
		this.model = model;
		this.model.addObserver(this);

		this.datum = new JTextField("", 15);
		this.datum.addKeyListener(new DateKeyListener());
		
		this.wochentag = new JTextField("", 15);
		this.wochentag.setEditable(false);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				"Aufgabe-3 Kalendarrechnung"));
		panel.setLayout(new GridLayout(1, 2, 5, 5));
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridBagLayout());
		
		JButton button = new JButton("Wochentag errechnen");
		button.addActionListener(this);

		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				new JLabel("Datum (dd.mm.yyyy oder d.m.yyyy)"), 0, 0, 1, 1, 0, 0);
		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				this.datum, 1, 0, 1, 1, 1.0, 0);
		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				button, 0, 2, 2, 0, 0, 0);
				
		panel.add(inputPanel);

		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new GridBagLayout());

		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				new JLabel("Wochentag"), 0, 0, 1, 1, 0, 0);
		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				this.wochentag, 1, 0, 1, 1, 1.0, 0);
		
		panel.add(resultPanel);
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public JTextField getDatum() {
		return datum;
	}

	public JTextField getWochentag() {
		return wochentag;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		this.wochentag.setText((String)arg1);
		this.panel.validate();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
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
