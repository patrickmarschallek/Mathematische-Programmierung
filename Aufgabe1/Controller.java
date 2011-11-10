package Aufgabe1;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller implements Observer {

	private View view;
	private Model model;
	
	public Controller(){
		this.model = new Model();
		this.view  = new View(this.model);
		this.view.addObserver(this);
	}
	@Override
	public void update(Observable arg0, Object arg1) {	
		try{
			if(this.view.getA().getText().isEmpty() == true || this.view.getB().getText().isEmpty() == true)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Für a und b muss eine Eingabe erfolgen.", "Fehlende Eingabe", JOptionPane.ERROR_MESSAGE);
			}else{
				model.setR1(Integer.valueOf(this.view.getA().getText()));
				model.setR2(Integer.valueOf(this.view.getB().getText()));
				model.calculateGgt();
			}
		}catch(GgtUndefinedValueException e){
			JOptionPane.showMessageDialog(new JFrame(),
					e.getMessage(),"Eingabefehler",JOptionPane.ERROR_MESSAGE);
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(new JFrame(),
					"Falsches Zahlenformat","Eingabefehler",JOptionPane.ERROR_MESSAGE);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public View getView() {
		return view;
	}

	public Model getModel() {
		return model;
	}
}
