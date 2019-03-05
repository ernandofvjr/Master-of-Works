package main;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import jframe.JanelaPrincipal;

public class Main {

	public static void main(String[] args) {
		
		try{
			UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
			new JanelaPrincipal();

	    } catch (Exception e) {
	    	e.printStackTrace();
	    	JOptionPane.showMessageDialog(null, e.getStackTrace());
	    }
	}
}