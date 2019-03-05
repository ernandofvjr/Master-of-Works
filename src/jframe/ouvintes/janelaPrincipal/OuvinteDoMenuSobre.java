package jframe.ouvintes.janelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jframe.JanelaPrincipal;
import jframe.JanelaSobre;

public class OuvinteDoMenuSobre implements ActionListener {
	
	private JanelaPrincipal janela;
	
	public OuvinteDoMenuSobre(JanelaPrincipal janela){
		this.janela = janela;
	}	
	public void actionPerformed(ActionEvent e) {		
		JanelaSobre sobre = new JanelaSobre();
		sobre.chamarJanela(janela);
	}
}
