package Aufgabe1;

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

import Controller.KeyListener.DecimalKeyListener;

public class View extends Observable implements ActionListener, Observer {

	private Model model;
	private JPanel panel;
	private JTextField a, b, ggt, t, s;

	public View(Model model) {
		this.model = model;
		this.model.addObserver(this);

		this.a = new JTextField("", 15);
		this.b = new JTextField("", 15);
		a.addKeyListener(new DecimalKeyListener());
		b.addKeyListener(new DecimalKeyListener());

		this.ggt = new JTextField("", 15);
		this.s = new JTextField("", 15);
		this.t = new JTextField("", 15);

		this.ggt.setEditable(false);
		this.s.setEditable(false);
		this.t.setEditable(false);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				"Aufgabe-1 erweitert euklidischer Algorithmus"));
		panel.setLayout(new GridLayout(1, 2, 5, 5));

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridBagLayout());

		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				new JLabel("a"), 0, 0, 1, 1, 0, 0);
		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				this.a, 1, 0, 1, 1, 1.0, 0);
		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				new JLabel("b"), 0, 1, 1, 1, 0, 0);
		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				this.b, 1, 1, 1, 1, 1.0, 0);

		JButton button = new JButton("Berechne ggt(a,b)");
		button.addActionListener(this);

		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				button, 0, 2, 2, 1, 0, 0);
				
		panel.add(inputPanel);

		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new GridBagLayout());

		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				new JLabel("ggt(a,b)"), 0, 0, 1, 1, 0, 0);
		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				this.ggt, 1, 0, 1, 1, 1.0, 0);
		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				new JLabel("s"), 0, 1, 1, 1, 0, 0);
		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				this.s, 1, 1, 1, 1, 1.0, 0);
		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				new JLabel("t"), 0, 2, 1, 1, 0, 0);
		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				this.t, 1, 2, 1, 1, 1.0, 0);

		panel.add(resultPanel);
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public JTextField getA() {
		return a;
	}

	public JTextField getB() {
		return b;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.ggt.setText(String.valueOf(model.getGgt()));
		this.s.setText(String.valueOf(model.getS1()));
		this.t.setText(String.valueOf(model.getT1()));
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
