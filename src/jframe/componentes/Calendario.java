package jframe.componentes;

import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import com.toedter.calendar.JDateChooser;

public class Calendario extends JDateChooser {
	
	public Calendario(JFrame janela, int x, int y, int w, int h, PropertyChangeListener ouvinte){
		
		addPropertyChangeListener(ouvinte);
//		addComponentListener(ouvinte);
		setBounds(x, y, w, h);
		janela.add(this);		
	}	
}
