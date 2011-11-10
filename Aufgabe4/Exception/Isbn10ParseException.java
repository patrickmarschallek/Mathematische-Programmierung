package Aufgabe4.Exception;

public class Isbn10ParseException extends Exception {

	private static final long serialVersionUID = 1L;

	public Isbn10ParseException(){
		super(	"Die ISBN-10 darf nur aus Zahlen bestehen.");
	}
}
