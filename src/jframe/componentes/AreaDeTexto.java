package jframe.componentes;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classes.Fonte;

public class AreaDeTexto extends JTextArea {
	
	public AreaDeTexto(JFrame janela, int x, int y, int w, int h){		
		setFont(Fonte.textoNormal());
		setLineWrap(true);
		setWrapStyleWord(true);	
		setEditable(true);
		
		JScrollPane painel = new JScrollPane(this);
		painel.setBounds(x, y, w, h);
		janela.add(painel);
	}
}
