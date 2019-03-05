package jframe.componentes;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import classes.Fonte;

public class CampoDeTexto extends JTextField {
	
	public CampoDeTexto(JFrame janela, int x, int y, int w, int h){
		setFont(Fonte.textoNormal());
		this.setBounds(x, y, w, h);
		janela.add(this);
		addFocusListener(new Ouvinte());			
	}
	public CampoDeTexto(JFrame janela, int x, int y, int w, int h, Object obj) {
		setFont(Fonte.textoNormal());
		this.setBounds(x, y, w, h);
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