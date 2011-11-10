package Aufgabe2;

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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class View extends Observable implements ActionListener, Observer {

	private JPanel panel;
	
	//Element zur darstellung des Zahlensystem Panels
	private Model model;
	private JPanel numberSystemsPanel;
	private JTextField ausgabewert;
	private JTextField eingabewert;
	private JComboBox eingabetyp;
	private JComboBox ausgabetyp;
	public final static String[] itemList = {	"Dual"
												,"Dreier"
												,"Vierer"
				 								,"Fünfer"
												,"Sechser"
												,"Siebener"
												,"Octal"
												,"Neuner"
												,"Dezimal"
												,"Elfer"
												,"Zwölfer"
												,"Dreizehner"
												,"Vierzehner"
												,"Fünfzehner"
												,"Hexadezimal"};
	
	//elemente zur darstelung das fortgesetzte Quadrieren
	private JTextField a,n,m,result;
	private JPanel exponantationPanel;

	public View(Model model) {
		this.model = model;
		this.model.addObserver(this);

		panel = new JPanel();
		panel.setLayout(new GridLayout(2,1,5,5));
		
		numberSystemsPanel = new JPanel();
		numberSystemsPanel.setBorder(new TitledBorder("Aufgabe-2 Zahlendarstellung"));
		numberSystemsPanel.setLayout(new GridLayout(1, 2, 5, 5));

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridBagLayout());

		
		
		eingabewert = new JTextField("",15);
		
		ausgabewert = new JTextField("",15);
		ausgabewert.setEditable(false);
		
		JLabel eingabeText = new JLabel("Eingabe:");
		JLabel eingabetypText = new JLabel("Typ:");
		JLabel eingabewertText = new JLabel("Wert:");
		
		JLabel ausgabeText = new JLabel("Ausgabe:");
		JLabel ausgabetypText = new JLabel("Typ:");
		JLabel ausgabewertText = new JLabel("Wert:");		
		
		eingabetyp = new JComboBox(View.itemList);
		ausgabetyp = new JComboBox(View.itemList);
		 
		JButton starten = new JButton("umwandeln");
		starten.addActionListener(this);
		
		eingabetyp.addActionListener(this);
		ausgabetyp.addActionListener(this);
		
		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				eingabeText, 0, 0, 2, 1, 0, 0);
		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				eingabetypText, 0, 1, 1, 1, 0, 0);
		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				eingabetyp, 1, 1, 1, 1, 1.0, 0);
		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				eingabewertText, 0, 2, 1, 1, 0, 0);
		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				eingabewert, 1, 2, 1, 1, 1.0, 0);
		View.addComponent(inputPanel, (GridBagLayout) inputPanel.getLayout(),
				starten, 0, 3, 2, 1, 0, 0);
		

		numberSystemsPanel.add(inputPanel);

		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(new GridBagLayout());

		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				ausgabeText, 0, 0, 2, 1, 0, 0);
		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				ausgabetypText, 0, 1, 1, 1, 0, 0);
		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				this.ausgabetyp, 1, 1, 1, 1, 1.0, 0);
		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				ausgabewertText, 0, 2, 1, 1, 0, 0);
		View.addComponent(resultPanel, (GridBagLayout) resultPanel.getLayout(),
				this.ausgabewert, 1, 2, 1, 1, 1.0, 0);
		
		numberSystemsPanel.add(resultPanel);
		
		//generate the view for the exponantation
		JLabel formel = new JLabel("f = a^n mod m");
		JLabel aLabel 	= new JLabel("a:");
		JLabel nLabel 	= new JLabel("n:");
		JLabel mLabel 	= new JLabel("m:");
		JLabel resultLabel 	= new JLabel("Ergebnis:");
		
		this.a 		= new JTextField("",15);
		this.n 		= new JTextField("",15);
		this.m 		= new JTextField("",15);
		
		this.result	= new JTextField("",15);
		this.result.setEditable(false);
		
		JButton calc = new JButton("berechnen");
		calc.addActionListener(this);
		
		this.exponantationPanel = new JPanel();
		this.exponantationPanel.setLayout(new GridBagLayout());
		this.exponantationPanel.setBorder(new TitledBorder("Aufgabe-2 binäres Exponentieren"));
		
		//positionieren der elemente
		View.addComponent(exponantationPanel, (GridBagLayout) exponantationPanel.getLayout(),
				aLabel, 0, 0, 1, 1, 0, 0);
		View.addComponent(exponantationPanel, (GridBagLayout) exponantationPanel.getLayout(),
				a, 1, 0, 1, 1, 0, 0);
		
		View.addComponent(exponantationPanel, (GridBagLayout) exponantationPanel.getLayout(),
				nLabel, 0, 1, 1, 1, 0, 0);
		View.addComponent(exponantationPanel, (GridBagLayout) exponantationPanel.getLayout(),
				n, 1, 1, 1, 1, 0, 0);
		
		View.addComponent(exponantationPanel, (GridBagLayout) exponantationPanel.getLayout(),
				mLabel, 0, 2, 1, 1, 0, 0);
		View.addComponent(exponantationPanel, (GridBagLayout) exponantationPanel.getLayout(),
				m, 1, 2, 1, 1, 0, 0);
		
		View.addComponent(exponantationPanel, (GridBagLayout) exponantationPanel.getLayout(),
				calc, 0, 3, 2, 1, 0, 0);
		
		View.addComponent(exponantationPanel, (GridBagLayout) exponantationPanel.getLayout(),
				resultLabel, 3, 2, 1, 1, 0, 0);
		View.addComponent(exponantationPanel, (GridBagLayout) exponantationPanel.getLayout(),
				this.result, 4, 2, 1, 1, 0, 0);
		
		View.addComponent(exponantationPanel, (GridBagLayout) exponantationPanel.getLayout(),
				formel,4,1,1,1,0,0);
		
		
		panel.add(this.numberSystemsPanel);
		panel.add(this.exponantationPanel);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
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

	public JTextField getAusgabewert() {
		return ausgabewert;
	}

	public JTextField getEingabewert() {
		return eingabewert;
	}

	public JPanel getNumberSystemPanel() {
		return numberSystemsPanel;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JPanel getExponantationPanel() {
		return exponantationPanel;
	}

	public JComboBox getEingabetyp() {
		return eingabetyp;
	}	
	
	public JComboBox getAusgabetyp() {
		return ausgabetyp;
	}

	public JTextField getA() {
		return a;
	}

	public JTextField getN() {
		return n;
	}

	public JTextField getM() {
		return m;
	}

	public JTextField getResult() {
		return result;
	}	
	
}
