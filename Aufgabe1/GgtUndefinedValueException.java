package Aufgabe1;

/**
 * bei auftreten bei der Berechnung des größten gemeinsamen Teilers wenn die beiden
 * Eingabewerte 0 sind - ggt(0,0) 
 * 
 * @author Patrick
 *
 */
public class GgtUndefinedValueException extends Exception {

	private static final long serialVersionUID = 1L;

	public GgtUndefinedValueException(String e){
		super(e);
	}
}
