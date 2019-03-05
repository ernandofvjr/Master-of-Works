package jframe.componentes;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import classes.Fonte;

public class RadioBotao extends JRadioButton{
	public RadioBotao(JFrame janela, int x, int y, int w, int h,String texto, ButtonGroup grupo, ActionListener ouvinte) {
		setBounds(x, y, w, h);
		setText(texto);
		setFont(Fonte.textoNormal());
		addActionListener(ouvinte);
		grupo.add(this);
		janela.add(this);
	}
}