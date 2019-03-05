package jframe.componentes;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class CheckBox extends JCheckBox {
	
	public CheckBox(JFrame janela, int x, int y, int w, int h, String texto, String dica){		
		setBounds(x, y, w, h);
		setText(texto);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.TOP);
		setToolTipText(dica);
		janela.add(this);		
	}
	public CheckBox(JFrame janela, int x, int y, int w, int h, String dica){		
		setBounds(x, y, w, h);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.TOP);
		setToolTipText(dica);
		janela.add(this);
	}
}