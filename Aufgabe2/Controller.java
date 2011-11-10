package Aufgabe2;

import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.KeyListener.DecimalKeyListener;
import Controller.KeyListener.DualKeyListener;
import Controller.KeyListener.ElevenerKeyListener;
import Controller.KeyListener.FiverKeyListener;
import Controller.KeyListener.FiveteenerKeyListener;
import Controller.KeyListener.FourerKeyListener;
import Controller.KeyListener.FourteenerKeyListener;
import Controller.KeyListener.HexaDecimalKeyListener;
import Controller.KeyListener.NinerKeyListener;
import Controller.KeyListener.OctalKeyListener;
import Controller.KeyListener.SevenerKeyListener;
import Controller.KeyListener.SixerKeyListener;
import Controller.KeyListener.ThirteenerKeyListener;
import Controller.KeyListener.ThreesomeKeyListener;
import Controller.KeyListener.TwelverKeyListener;

public class Controller implements Observer {

	private View view;
	private Model model;
	private HashMap<String, KeyListener> keyListenerHashMap;
	private LinkedList<KeyListener> keyListenerList;

	public Controller() {
		this.model = new Model();
		this.view = new View(this.model);
		this.view.addObserver(this);

		this.keyListenerHashMap = new HashMap<String, KeyListener>();
		this.keyListenerList = new LinkedList<KeyListener>();

		this.keyListenerList.add(new DualKeyListener());
		this.keyListenerList.add(new ThreesomeKeyListener());
		this.keyListenerList.add(new FourerKeyListener());
		this.keyListenerList.add(new FiverKeyListener());
		this.keyListenerList.add(new SixerKeyListener());
		this.keyListenerList.add(new SevenerKeyListener());
		this.keyListenerList.add(new OctalKeyListener());
		this.keyListenerList.add(new NinerKeyListener());
		this.keyListenerList.add(new DecimalKeyListener());
		this.keyListenerList.add(new ElevenerKeyListener());
		this.keyListenerList.add(new TwelverKeyListener());
		this.keyListenerList.add(new ThirteenerKeyListener());
		this.keyListenerList.add(new FourteenerKeyListener());
		this.keyListenerList.add(new FiveteenerKeyListener());
		this.keyListenerList.add(new HexaDecimalKeyListener());

		for (int i = 0; i < View.itemList.length; i++) {
			this.keyListenerHashMap.put(View.itemList[i],
					this.keyListenerList.get(i));
		}

		this.view.getEingabewert().addKeyListener(
				this.keyListenerHashMap.get("Dezimal"));
		this.view.getEingabetyp().setSelectedItem("Dezimal");
		this.view.getAusgabetyp().setSelectedItem("Dual");

		this.view.getA().addKeyListener(this.keyListenerList.get(9));
		this.view.getN().addKeyListener(this.keyListenerList.get(9));
		this.view.getM().addKeyListener(this.keyListenerList.get(9));
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		ActionEvent event = (ActionEvent) arg1;
		// Umrechnung der Zahlensysteme werden an dieser Stelle vom Controller
		// bearbeitet
		if (event.getActionCommand().equals("comboBoxChanged")
				&& event.getSource() == this.view.getEingabetyp()) {
			this.changeKeyListener(this.view.getEingabetyp());
			this.view.getEingabewert().setText("");
			this.view.getAusgabewert().setText("");
		} else if (event.getActionCommand().equals("umwandeln")) {
			if (!this.view.getEingabewert().getText().equals("")) {
				try {

					this.model.toDezimal((this.view.getEingabetyp()
							.getSelectedIndex() + 2), this.view
							.getEingabewert().getText().toCharArray());

					if (this.view.getAusgabetyp().getSelectedItem() != "Dezimal") {
						this.model.fromDezimal(this.view.getAusgabetyp()
								.getSelectedIndex() + 2, this.model
								.getDezimalSystem());
						this.view.getAusgabewert().setText(
								"" + this.model.getOtherSystem());
					} else {
						this.view.getAusgabewert().setText(
								"" + this.model.getDezimalSystem());
					}

				} catch (NumberFormatException e) {
					this.showExceptionDialog("Falsches Zahlenformat");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				 this.showExceptionDialog("Bitte geben Sie einen Wert ein.");
			}
		}

		this.view.getNumberSystemPanel().validate();

		// das fortgesetzte Quadrieren wird vom Controller an dieser Stelle
		// gesteuert
		if (event.getActionCommand().equals("berechnen")) {
			if ((this.view.getA().getText().isEmpty()
					|| this.view.getM().getText().isEmpty() || this.view.getN()
					.getText().isEmpty())) {
				this.showExceptionDialog("Fuer a, n und m muss eine Eingabe Erfolgen");
			} else if (Long.parseLong(this.view.getN().getText()) > Integer.MAX_VALUE) {
				this.showExceptionDialog("Falsches Zahlenformat fuer n");
			} else if (Integer.parseInt(this.view.getM().getText()) > Short.MAX_VALUE) {
				this.showExceptionDialog("Falsches Zahlenformat fuer m ( 1 - "+Short.MAX_VALUE+")");
			} else if (Long.parseLong(this.view.getA().getText()) > Integer.MAX_VALUE) {
				this.showExceptionDialog("Falsches Zahlenformat fuer a");
			} else if (Integer.parseInt(this.view.getA().getText()) == 0
					&& Integer.parseInt(this.view.getN().getText()) == 0) {
				this.showExceptionDialog("Fuer a = 0 und b = 0 gibt es keine definierte Lösung");
			} else if (Integer.parseInt(this.view.getM().getText()) == 0) {
				this.showExceptionDialog("Fuer x mod 0 gibt es keine definierte Lösung");
			} else if ((Integer.parseInt(this.view.getA().getText()) * Integer
					.parseInt(this.view.getN().getText())) > Integer.MAX_VALUE) {
				this.showExceptionDialog("Fuer a und n nicht zu Hohe Werte eingeben");
			} else {
				try {
					this.model.modularPotenzen(
							Integer.parseInt(this.view.getA().getText()),
							Integer.parseInt(this.view.getN().getText()),
							Short.parseShort(this.view.getM().getText()));
					this.view.getResult().setText(
							"" + this.model.getBinaryExponation());

				} catch (NumberFormatException e) {
					this.showExceptionDialog("Falsches Zahlenformat für Eingabewerte");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void changeKeyListener(JComboBox selectBox) {
		for (KeyListener listener : this.view.getEingabewert()
				.getKeyListeners()) {
			this.view.getEingabewert().removeKeyListener(listener);
		}
		this.view.getEingabewert().addKeyListener(
				this.keyListenerHashMap.get(selectBox.getSelectedItem()));
	}

	private void showExceptionDialog(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Eingabefehler",
				JOptionPane.ERROR_MESSAGE);
	}

	public View getView() {
		return view;
	}

	public Model getModel() {
		return model;
	}
}
