package Aufgabe2;

import Model.MainModel;

public class Model extends MainModel {

	private int dezimalSystem;
	private String otherSystem;
	private int binaryExponation;
	/**
	 * Das Feld enthält das Alphabet der Zahlensystem bis zur Basis 16
	 */
	private final static char[] ALPHABET = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public int getBinaryExponation() {
		return binaryExponation;
	}

	public int getDezimalSystem() {
		return dezimalSystem;
	}

	public String getOtherSystem() {
		return otherSystem;
	}

	/**
	 * Die Funktion berechnet eine Dezimalzahl aus der übergebenen Zahl (number)
	 * diese ist von der Basis (base). Das ganze wird über das effektive 
	 * Hornerschema berechnet.
	 * 
	 * @param base
	 * @param number
	 * 
	 */
	public void toDezimal(int base, char[] number) {
		this.dezimalSystem = this.checkNumber(number[0]);
		
		for (int n = 0; n < number.length - 1; n++) {
			this.dezimalSystem = (this.dezimalSystem * base) + this.checkNumber(number[n+1]);
		}
		System.out.println(this.dezimalSystem);
		this.setChanged();
		this.notifyObservers(this.dezimalSystem);
	}

	/**
	 * Die Funktion bekommt einen Characterwert für eine Zahlenstelle.
	 * Damit A als 15 erkannt wird. Die Zuordnungen liegen im konstanten 
	 * Feld {@link Aufgabe2.Model.ALPHABET}
	 * 
	 * 
	 * @param number char
	 * @return value int
	 */
	private int checkNumber(char number){
		int value = 0;
		for(int i = 0; i < Model.ALPHABET.length;i++){
			if(Model.ALPHABET[i] == number)
				value = i;
		}
		return value;
	}
	
	/**
	 * Die Funktion berechnet von einer gegebenen Dezimalzahl(number) die Zahl
	 * eines anderen Zahlensystems. Das neue Zahlensystem ist von der Basis(base).
	 * Berechnet wird das über das effektive Hornerschema.
	 * 
	 * @param base int
	 * @param number int
	 * 
	 */
	public void fromDezimal(int base, int number) {
		// ausgabe String der Zahl im angegebenen Zahlensystem
		StringBuilder erg = new StringBuilder("");
		while (number > 0) {
			erg.append(ALPHABET[number % base]);
			number /= base;
		}
		this.otherSystem = erg.reverse().toString();
		
		if(this.otherSystem.equals(""))
			this.otherSystem = "0";
		
		this.setChanged();
		this.notifyObservers(this.otherSystem);
	}

	/**
	 * Die Funktion berechnet mithilfe von erweitertes Quadrieren eine Potenz. 
	 * Diese Potenz wird dann noch mit dem angegeben Modulowert verechnet. 
	 * Nach der Formal :
	 * 
	 * <center><strong>a ^ n % m</strong></center>
	 * 
	 * @param a int
	 * @param n int
	 * @param m short
	 * 
	 * @throws ArithmeticException wenn m = 0
	 */
	public void modularPotenzen(int a, int n, short m) throws ArithmeticException{
		int temp = a;
		if(n > 0){
			this.fromDezimal(2, n);
			String exp = this.getOtherSystem();
			for (int i = 1; i < exp.length(); i++) {
				if (exp.charAt(i) == '1') 
					temp = ((temp*temp) % m) * a;
				else
					temp = ((temp*temp) % m);
			}
		}
		else{
			temp = 1;
		}
		
		this.binaryExponation = temp % m;
		this.setChanged();
		this.notifyObservers(this.binaryExponation);
	}
}
