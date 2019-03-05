package jframe.ouvintes.janelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import classes.PersistenciaGerente;
import jframe.JanelaPrincipal;

public class OuvinteDoMenuSalvarPrincipal extends JFileChooser implements ActionListener{

	private JanelaPrincipal janela;
	
	public OuvinteDoMenuSalvarPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}	
	public void actionPerformed(ActionEvent e) {		
		PersistenciaGerente persistencia = new PersistenciaGerente();
		persistencia.escolherOndeSalvarGerente();
		janela.dispose();
		new JanelaPrincipal();
	}
}