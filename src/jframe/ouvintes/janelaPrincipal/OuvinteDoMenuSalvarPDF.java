package jframe.ouvintes.janelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import classes.PersistenciaPDF;
import jframe.JanelaPrincipal;

public class OuvinteDoMenuSalvarPDF implements ActionListener{
	
	private JanelaPrincipal janela;

	public OuvinteDoMenuSalvarPDF(JanelaPrincipal janela) {
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent e) {		
		PersistenciaPDF	persistencia = new PersistenciaPDF();		
	}
}