package jframe.ouvintes;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import jframe.JanelaPrincipal;

public class OuvinteDasJanelasDeNivelUm implements WindowListener {
	private JFrame janela;
	public OuvinteDasJanelasDeNivelUm(JFrame janela) {
		this.janela = janela;
	}
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowClosing(WindowEvent e) {
		janela.dispose();
		new JanelaPrincipal();		
	}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
}