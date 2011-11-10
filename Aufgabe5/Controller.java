package Aufgabe5;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller implements Observer {

	protected View view;
	private Model model;
	private ChangeListener changeListener;

	public Controller() {
		this.model = new Model();
		this.view = new View(this.model);
		this.view.addObserver(this);

		this.changeListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JTextArea chiffre = view.getChiffre();
				JTextArea plaintext = view.getPlaintext();

				// CeaserPanel
				if (0 == view.getTabPanel().getSelectedIndex()) {
					View.addComponent(view.getCeaserPanel(),
							(GridBagLayout) view.getCeaserPanel().getLayout(),
							chiffre, 0, 5, 1, 1, 0, 0);
					View.addComponent(view.getCeaserPanel(),
							(GridBagLayout) view.getCeaserPanel().getLayout(),
							plaintext, 0, 3, 1, 1, 0, 0);
				} else if (1 == view.getTabPanel().getSelectedIndex()) {// VigenerePanel
					View.addComponent(
							view.getVigenerePanel(),
							(GridBagLayout) view.getVigenerePanel().getLayout(),
							chiffre, 0, 5, 1, 1, 0, 0);
					View.addComponent(
							view.getVigenerePanel(),
							(GridBagLayout) view.getVigenerePanel().getLayout(),
							plaintext, 0, 3, 1, 1, 0, 0);
				}
			}
		};

		this.view.getTabPanel().addChangeListener(changeListener);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		ActionEvent e = (ActionEvent) arg1;
		System.out.println(e.getActionCommand() + " " + e.paramString());

		//FileChooser ActionPerformed
		if (e.getSource() == this.view.getFileButton()) {
			//Das Panel gibt an wo sich der Dialog öffnen soll
			int returnVal = this.view.getFileChooser().showOpenDialog(this.view.getPanel());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = this.view.getFileChooser().getSelectedFile();
				System.out.println(file.getPath()+" \n"+file.getName());
			}
		}
	}

	public View getView() {
		return view;
	}

	public Model getModel() {
		return model;
	}
}