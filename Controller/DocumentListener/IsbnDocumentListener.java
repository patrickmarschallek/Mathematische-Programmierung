package Controller.DocumentListener;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class IsbnDocumentListener implements DocumentListener {

	protected Document doc;
	private static final int MAX_LENGTH = 9;
	private DocumentEvent e;
	
	private Runnable formater = new Runnable() {
	    public void run() {
	    	try{
	        	doc.remove(e.getOffset(),e.getLength()); 
	        }
	        catch(BadLocationException e){
	            e.printStackTrace();
	        }
	    }
	};

	@Override
	public void changedUpdate(DocumentEvent e) {}

	@Override
	public void insertUpdate(DocumentEvent e) {
		this.e = e;
		this.doc = e.getDocument();
		if(doc.getLength() > IsbnDocumentListener.MAX_LENGTH)
			SwingUtilities.invokeLater(formater);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {	}

}
