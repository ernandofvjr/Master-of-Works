package jframe.componentes;


import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ComboCaixa extends JComboBox<String>{
	public ComboCaixa(JFrame janela,Font fonte,int x, int y, int w, int h, String[] strings){
		
		super(strings);
		setFont(fonte);
		setBounds(x,y,w,h);
		
		janela.add(this);
	}
}
