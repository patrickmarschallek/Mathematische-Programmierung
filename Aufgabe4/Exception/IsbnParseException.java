package Aufgabe4.Exception;

public class IsbnParseException extends Exception {

	private static final long serialVersionUID = 1L;

	public IsbnParseException(){
		super(	"Sie m�ssen die fehlende Stelle mit einem '*' erg�nzen\n" +
				"und es darf nur '*' enthalten sein und es m�ssen 10\n" +
				"zeichen eingegeben sein.");
	}
}
