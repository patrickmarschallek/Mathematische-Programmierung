package Aufgabe3;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Aufgabe3.Exception.DateFormatException;
import Aufgabe3.Exception.DateRangeException;
import Aufgabe3.Exception.DayRangeException;
import Aufgabe3.Exception.MonthRangeException;

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
		ActionEvent event = (ActionEvent)arg1;
		
		if(event.getActionCommand().equals("Wochentag errechnen")){
			try {
				this.model.parseDate(this.view.getDatum().getText());
				this.model.calculateDayOfWeek();
			} catch (DayRangeException e) {
				this.showExceptionDialog(e);
			} catch (MonthRangeException e) {
				this.showExceptionDialog(e);
			} catch (DateRangeException e) {
				this.showExceptionDialog(e);
			} catch (DateFormatException e) {
				this.showExceptionDialog(e);
			}catch(Exception e){
				e.printStackTrace();
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
