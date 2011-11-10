package Controller;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

import Model.MainModel;
import View.MainFrame;



public class MainController implements Observer{

	private MainModel model;
	private MainFrame view;
	
	public MainController(){
		this.model 	= new MainModel();
		this.view	= new MainFrame(model);
		
		this.view.addObserver(this);
		this.view.getMenuBar().addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		ActionEvent e = (ActionEvent) arg1;
		if(e.getActionCommand().equals("Aufgabe_1")){
			Aufgabe1.Controller aufgabe1Controller = new Aufgabe1.Controller();
			this.view.setPanel(aufgabe1Controller.getView().getPanel());
		}else if(e.getActionCommand().equals("Aufgabe_2")){
			Aufgabe2.Controller aufgabe2Controller = new Aufgabe2.Controller();
			this.view.setPanel(aufgabe2Controller.getView().getPanel());
		}else if(e.getActionCommand().equals("Aufgabe_3")){
			Aufgabe3.Controller aufgabe3Controller = new Aufgabe3.Controller();
			this.view.setPanel(aufgabe3Controller.getView().getPanel());
		}else if(e.getActionCommand().equals("Aufgabe_4")){
			Aufgabe4.Controller aufgabe4Controller = new Aufgabe4.Controller();
			this.view.setPanel(aufgabe4Controller.getView().getPanel());
		}else if(e.getActionCommand().equals("Aufgabe_5")){
			Aufgabe5.Controller aufgabe5Controller = new Aufgabe5.Controller();
			this.view.setPanel(aufgabe5Controller.getView().getPanel());
		}else if(e.getActionCommand().equals("Aufgabe_6")){
//			Aufgabe6.Controller aufgabe6Controller = new Aufgabe6.Controller();
//			this.view.setPanel(aufgabe6Controller.getView().getPanel());
		}else if(e.getActionCommand().equals("Aufgabe_7")){
//			Aufgabe7.Controller aufgabe7Controller = new Aufgabe7.Controller();
//			this.view.setPanel(aufgabe7Controller.getView().getPanel());
		}else if(e.getActionCommand().equals("Aufgabe_8")){
//			Aufgabe8.Controller aufgabe8Controller = new Aufgabe8.Controller();
//			this.view.setPanel(aufgabe8Controller.getView().getPanel());
		}else if(e.getActionCommand().equals("Aufgabe_9")){
//			Aufgabe9.Controller aufgabe9Controller = new Aufgabe9.Controller();
//			this.view.setPanel(aufgabe9Controller.getView().getPanel());
		}else if(e.getActionCommand().equals("Aufgabe_10")){
//			Aufgabe10.Controller aufgabe10Controller = new Aufgabe10.Controller();
//			this.view.setPanel(aufgabe10Controller.getView().getPanel());
		}else if(e.getActionCommand().equals("Beenden")){
			System.exit(0);
		}
	}
}
