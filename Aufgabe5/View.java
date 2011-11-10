package Aufgabe5;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class View extends Observable implements ActionListener, Observer {

	private Model model;
	private JPanel panel,ceaserPanel,vigenerePanel;
	private JTextField vigenereKey;
	private JComboBox ceaserKey;
	private JTextArea chiffre,plaintext;
	private JButton clearChiffre,clearPlaintext,encipher,decipher,fileButton;
	private JFileChooser fileChooser;
	private JTabbedPane tabPanel;
	
	public View(Model model) {
		this.model = model;
		this.model.addObserver(this);

		this.fileChooser = new JFileChooser();
		//initialize
		this.panel = new JPanel(new GridBagLayout());
		
		this.ceaserPanel = new JPanel(new GridBagLayout());
		this.vigenerePanel = new JPanel(new GridBagLayout());
		
		this.plaintext 	= new JTextArea(5,30);
		this.plaintext.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
		this.chiffre	= new JTextArea(5,30);
		this.chiffre.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
		
		String[] keyValues = new String[26];
		for(int i=0;i<keyValues.length;i++)
			keyValues[i] = ""+(char)(i+65);
		this.ceaserKey = new JComboBox(keyValues);
		this.vigenereKey = new JTextField();
		
		this.decipher = new JButton("Dechiffrieren");
		this.decipher.addActionListener(this);
		this.encipher = new JButton("Chiffrieren");
		this.encipher.addActionListener(this);
		this.clearChiffre = new JButton("leere Chiffrat");
		this.clearChiffre.addActionListener(this);
		this.clearPlaintext = new JButton("leere Klartext");
		this.clearPlaintext.addActionListener(this);
		this.fileButton = new JButton("Datei öffnen");
		this.fileButton.addActionListener(this);
		
		this.tabPanel = new JTabbedPane();
		
		this.tabPanel.add("Ceaser",this.ceaserPanel);
		this.tabPanel.add("Vigenere",this.vigenerePanel);
		
		//MainPanel
		View.addComponent(this.panel, (GridBagLayout) this.panel.getLayout(),
				this.tabPanel, 0, 0, 1, 6, 0, 0);
		View.addComponent(this.panel, (GridBagLayout) this.panel.getLayout(),
				this.clearChiffre, 1, 0, 1, 1, 0, 0);
		View.addComponent(this.panel, (GridBagLayout) this.panel.getLayout(),
				this.clearPlaintext, 1, 1, 1, 1, 0, 0);
		View.addComponent(this.panel, (GridBagLayout) this.panel.getLayout(),
				this.decipher, 1, 2, 1, 1, 0, 0);
		View.addComponent(this.panel, (GridBagLayout) this.panel.getLayout(),
				this.encipher, 1, 3, 1, 1, 0, 0);
		View.addComponent(this.panel, (GridBagLayout) this.panel.getLayout(),
				this.fileButton, 1, 4, 1, 1, 0, 0);

		//CeaserPanel
		View.addComponent(this.ceaserPanel, (GridBagLayout) this.ceaserPanel.getLayout(),
				new JLabel("Schlüssel"), 0, 0, 1, 1, 0, 0);
		View.addComponent(this.ceaserPanel, (GridBagLayout) this.ceaserPanel.getLayout(),
				this.ceaserKey, 0, 1, 1, 1, 0, 0);
		View.addComponent(this.ceaserPanel, (GridBagLayout) this.ceaserPanel.getLayout(),
				new JLabel("Klartext"), 0, 2, 1, 1, 0, 0);
		View.addComponent(this.ceaserPanel, (GridBagLayout) this.ceaserPanel.getLayout(),
				this.plaintext, 0, 3, 1, 1, 0, 0);
		View.addComponent(this.ceaserPanel, (GridBagLayout) this.ceaserPanel.getLayout(),
				new JLabel("Chiffre"), 0, 4, 1, 1, 0, 0);
		View.addComponent(this.ceaserPanel, (GridBagLayout) this.ceaserPanel.getLayout(),
				this.chiffre, 0, 5, 1, 1, 0, 0);
		
		//VigenerePanel	
		View.addComponent(this.vigenerePanel, (GridBagLayout) this.vigenerePanel.getLayout(),
				new JLabel("Schlüssel"), 0, 0, 1, 1, 0, 0);
		View.addComponent(this.vigenerePanel, (GridBagLayout) this.vigenerePanel.getLayout(),
				this.vigenereKey, 0, 1, 1, 1, 0, 0);
		View.addComponent(this.vigenerePanel, (GridBagLayout) this.vigenerePanel.getLayout(),
				new JLabel("Klartext"), 0, 2, 1, 1, 0, 0);
		View.addComponent(this.vigenerePanel, (GridBagLayout) this.vigenerePanel.getLayout(),
				new JLabel("Chiffre"), 0, 4, 1, 1, 0, 0);
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public JButton getFileButton() {
		return fileButton;
	}

	public Model getModel() {
		return model;
	}

	public JPanel getCeaserPanel() {
		return ceaserPanel;
	}

	public JPanel getVigenerePanel() {
		return vigenerePanel;
	}

	public JTextField getVigenereKey() {
		return vigenereKey;
	}

	public JComboBox getCeaserKey() {
		return ceaserKey;
	}

	public JTextArea getChiffre() {
		return chiffre;
	}

	public JTextArea getPlaintext() {
		return plaintext;
	}

	public JButton getClearChiffre() {
		return clearChiffre;
	}

	public JButton getClearPlaintext() {
		return clearPlaintext;
	}

	public JButton getEncipher() {
		return encipher;
	}

	public JButton getDecipher() {
		return decipher;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public JTabbedPane getTabPanel() {
		return tabPanel;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.setChanged();
		this.notifyObservers(arg0);
	}

	static void addComponent(Container cont, GridBagLayout gbl,
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
