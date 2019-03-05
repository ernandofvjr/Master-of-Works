package jframe.ouvintes.janelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import classes.PersistenciaGerente;
import jframe.JanelaPrincipal;

public class OuvinteDoMenuSairPrincipal implements ActionListener {
	private JanelaPrincipal janela;
	public OuvinteDoMenuSairPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent e) {
		PersistenciaGerente persistencia = new PersistenciaGerente();
		persistencia.confimarSaida(janela, "fechar");
	}
}