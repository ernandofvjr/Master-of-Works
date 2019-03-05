package jframe.componentes;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Etiqueta extends JLabel {
	
	public Etiqueta(JFrame janela, String texto, Font fonte, String dica, int x, int y, int w, int h){
		
		setText(texto);
		setFont(fonte);
		setToolTipText(dica);
		setBounds(x, y, w, h);
		
		janela.add(this);
	}
	
	public Etiqueta(JFrame janela, String texto, Font fonte, String dica, int x, int y, int w, int h, int lado){
		
		setHorizontalAlignment(lado);
		setText(texto);
		setFont(fonte);
		setToolTipText(dica);
		setBounds(x, y, w, h);
		
		janela.add(this);
	}	
}