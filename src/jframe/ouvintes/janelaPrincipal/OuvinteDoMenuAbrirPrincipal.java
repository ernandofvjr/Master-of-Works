package jframe.ouvintes.janelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import classes.Gerente;
import classes.PersistenciaGerente;
import jframe.JanelaPrincipal;

public class OuvinteDoMenuAbrirPrincipal implements ActionListener{

	private JanelaPrincipal janela;
	
	public OuvinteDoMenuAbrirPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}	
	
	public void actionPerformed(ActionEvent e) {
		
		PersistenciaGerente persistencia = new PersistenciaGerente();	
		
		JFileChooser fileChooser = new JFileChooser();		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivo XML", "xml");		
		fileChooser.setFileFilter(filtro);		
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			
		  File arquivo = fileChooser.getSelectedFile();		  
		  Gerente gerente = Gerente.obterInstancia(persistencia, arquivo);
		  
		  janela.dispose();
		  new JanelaPrincipal();
		}		
	}
}