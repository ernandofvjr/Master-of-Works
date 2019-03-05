package jframe.ouvintes.janelaPrincipal;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import classes.PersistenciaGerente;
import jframe.JanelaPrincipal;

public class OuvinteDaJanelaPrincipal implements WindowListener {
	private JanelaPrincipal janela;
	public OuvinteDaJanelaPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}
	public void windowActivated(WindowEvent e) {
		
		
		
	}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {			
		PersistenciaGerente persistencia = new PersistenciaGerente();
		persistencia.confimarSaida(janela, "fechar");	
	}	
	public void windowDeactivated(WindowEvent e) {
		
	}
	public void windowDeiconified(WindowEvent e) {
		
	}
	public void windowIconified(WindowEvent e) {
		
	}
	public void windowOpened(WindowEvent e) {
		
	}
}