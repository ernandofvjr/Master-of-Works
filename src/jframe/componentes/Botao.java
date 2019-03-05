package jframe.componentes;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Botao extends JButton {
	public Botao(JFrame janela,Font fonte,ActionListener ouvinte,String texto,int x, int y,int w,int h, ImageIcon icone){
		
		setIcon(icone);
		
		setBounds(x, y, w, h);
		setText(texto);
		setFont(fonte);
		addActionListener(ouvinte);		
		janela.add(this);
	}
}
