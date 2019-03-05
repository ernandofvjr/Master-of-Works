package jframe.ouvintes.janelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import classes.PersistenciaGerente;
import jframe.JanelaPrincipal;

public class OuvinteDoMenuNovoPrincipal implements ActionListener {
	
	private JanelaPrincipal janela;
	
	public OuvinteDoMenuNovoPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent e) {
		
		PersistenciaGerente persistencia = new PersistenciaGerente();
		persistencia.confimarSaida(janela, "novo");		
	}
}