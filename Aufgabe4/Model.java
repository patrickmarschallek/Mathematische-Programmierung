package Aufgabe4;

import Aufgabe1.GgtUndefinedValueException;
import Aufgabe4.Exception.Isbn10ParseException;
import Aufgabe4.Exception.IsbnParseException;
import Model.MainModel;

public class Model extends MainModel {
	
	private Aufgabe1.Model ggtModel;
	private char checkDigit;
	private String isbn10;
	private boolean isbnCheckValue;
	
	public Model(){
		this.checkDigit = '0';
		this.isbn10 = "";
		this.isbnCheckValue = false;
		this.ggtModel = new Aufgabe1.Model();
	}

	public void checkIsbn(String isbn10, String checkDigit) throws Isbn10ParseException{
		this.createCheckDigit(isbn10);
		this.isbnCheckValue = this.checkDigit==checkDigit.charAt(0)?true:false;
		this.notifyView();
	}
	
	public void checkIsbn10(String isbn10, String checkDigit) throws Isbn10ParseException{
		this.parseIsbn10(isbn10);
		long summe = 0;
		isbn10 += checkDigit;
		for(int i=1;i<=10;i++){
			if(isbn10.charAt(i-1) == 'X' && i == 10)
				summe+=100;
			else
				summe+=(i*(48+(int)isbn10.charAt(i-1)));
		}
		if(summe % 11 == 0)
			this.isbnCheckValue = true;
		
		this.notifyView();
	}
	
	private void notifyView() {
		this.setChanged();
		this.notifyObservers();
	}

	// (Summe von i=1 bis 9  i*zi) mod 11
	public void createCheckDigit(String isbn10) throws Isbn10ParseException{
		this.parseIsbn10(isbn10);
		long summe = 0;
		for(int i=1;i<10;i++){
			summe += (int) (i*((int)isbn10.charAt(i-1)-48));
		}
		this.checkDigit = (char) (((summe%11)==10?'X':summe%11+48));
		this.isbn10 = isbn10;
		this.notifyView();
	}
	
	public void calculateMissingValue(String isbn10) throws IsbnParseException, GgtUndefinedValueException{
		this.parseIsbn(isbn10);
		long summe = 0;

		for(int i=1;i<10;i++){
			if(isbn10.charAt(i-1) != '*')
				summe += (int) (i*((int)isbn10.charAt(i-1)-48));
			else
				this.ggtModel.setR1(i+1);
		}
		
		this.ggtModel.setR2(11);
		this.ggtModel.calculateGgt();
		
		isbn10.replace("*", ""+(((summe%11)*this.ggtModel.getS1())%11));
		this.notifyView();
	}

	private void parseIsbn(String isbn10) throws IsbnParseException {
		if(!isbn10.matches("[0-9]*\\*[0-9]*") && isbn10.length() != 10){
			throw new IsbnParseException();
		}
	}
	
	private void parseIsbn10(String isbn10) throws Isbn10ParseException {
		if(!isbn10.matches("[0-9]*") && isbn10.length() != 10){
			throw new Isbn10ParseException();
		}
	}

	public char getCheckDigit() {
		return checkDigit;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public boolean isIsbnCheckValue() {
		return isbnCheckValue;
	}
	
	
}
