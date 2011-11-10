package Aufgabe4;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Aufgabe1.GgtUndefinedValueException;
import Aufgabe4.Exception.Isbn10ParseException;
import Aufgabe4.Exception.IsbnParseException;
import Controller.DocumentListener.CheckDigitListener;
import Controller.DocumentListener.IsbnDocumentListener;
import Controller.KeyListener.IsbnKeyListener;

public class Controller implements Observer {

	private View view;
	private Model model;
	
	public Controller(){
		this.model = new Model();
		this.view  = new View(this.model);
		this.view.addObserver(this);
		
		this.view.getEingabe().addKeyListener(new IsbnKeyListener());
		this.view.getEingabe().getDocument().addDocumentListener(new IsbnDocumentListener());
		
		this.view.getCheckDigit().addKeyListener(new IsbnKeyListener());
		this.view.getCheckDigit().getDocument().addDocumentListener(new CheckDigitListener());
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		ActionEvent event = (ActionEvent) arg1;
		String isbnValue = this.view.getEingabe().getText();
		String checkDigit = this.view.getCheckDigit().getText();
		
		if(event.getActionCommand().equals("ISBN prüfen") ){
			if(isbnValue.length() == 9 && !checkDigit.equals("")){
				try {
					this.model.checkIsbn(isbnValue,checkDigit);
				} catch (Isbn10ParseException e) {
					this.showExceptionDialog(e);
				}
			}else{
				this.showExceptionDialog(new Exception());
			}
		}else if(event.getActionCommand().equals("ISBN Prüfziffer berechnen")){
			if(isbnValue.length() == 9 && checkDigit.equals("")){
				try {
					this.model.createCheckDigit(isbnValue);
					this.view.getEingabe().setText(isbnValue+checkDigit);
				} catch (Isbn10ParseException e) {
					this.showExceptionDialog(e);
				}
			}else{
				this.showExceptionDialog(new Exception());
			}
		}else if(event.getActionCommand().equals("ISBN wiederherstellen")){
			try {
				this.model.calculateMissingValue(isbnValue+checkDigit);
			} catch (IsbnParseException e) {
				this.showExceptionDialog(e);
			} catch (GgtUndefinedValueException e) {
				this.showExceptionDialog(e);
			}
		}
		
	}

	public View getView() {
		return view;
	}

	public Model getModel() {
		return model;
	}
	
	private void showExceptionDialog(Exception e){
		JOptionPane.showMessageDialog(new JFrame(),
				e.getMessage(),"Eingabefehler",JOptionPane.ERROR_MESSAGE);
	}
}
