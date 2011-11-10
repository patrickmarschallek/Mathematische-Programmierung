package Aufgabe4.Exception;

public class IsbnParseException extends Exception {

	private static final long serialVersionUID = 1L;

	public IsbnParseException(){
		super(	"Sie müssen die fehlende Stelle mit einem '*' ergänzen\n" +
				"und es darf nur '*' enthalten sein und es müssen 10\n" +
				"zeichen eingegeben sein.");
	}
}
