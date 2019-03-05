package jframe.componentes;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;

import classes.Fonte;

public class CampoDeTextoFormatado extends JFormattedTextField{
	
	
	public CampoDeTextoFormatado(JFrame janela, int x, int y, int w, int h, String mascara) throws ParseException{
		super(new MaskFormatter(mascara));
		setFont(Fonte.textoNormal());
		setBounds(x,y,w,h);
		janela.add(this);
		addFocusListener(new Ouvinte());
		
	}
	public CampoDeTextoFormatado(JFrame janela, int x, int y, int w, int h, String mascara, Object obj) throws ParseException{
		super(new MaskFormatter(mascara));
		setFont(Fonte.textoNormal());
		setBounds(x,y,w,h);
		janela.add(this);
	}	
	
	private class Ouvinte implements FocusListener{

		public void focusGained(FocusEvent e) {
			setText("");
			repaint();
		}

		public void focusLost(FocusEvent e) {
		}
	}
}