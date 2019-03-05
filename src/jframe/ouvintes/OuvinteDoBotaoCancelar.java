package jframe.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import jframe.JanelaPrincipal;

public class OuvinteDoBotaoCancelar implements ActionListener{
	private JFrame janela;
	public OuvinteDoBotaoCancelar(JFrame janela) {
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent e) {
		janela.dispose();
		new JanelaPrincipal();
	}
}