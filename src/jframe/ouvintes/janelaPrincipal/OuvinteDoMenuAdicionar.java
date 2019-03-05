package jframe.ouvintes.janelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jframe.JanelaCadastroDeAtividade;
import jframe.JanelaCadastroDeFuncionarios;
import jframe.JanelaCadastroDeObras;
import jframe.JanelaPrincipal;

public class OuvinteDoMenuAdicionar implements ActionListener{	
	
	private JanelaPrincipal janela;
	private String comando;
	
	public OuvinteDoMenuAdicionar(JanelaPrincipal janela, String comando) {
		this.janela = janela;
		this.comando = comando;
	}	
	public void actionPerformed(ActionEvent e) {		
		janela.dispose();
		
		switch(comando){
			case "funcionario":				
				new JanelaCadastroDeFuncionarios();
				break;
				
			case "obra":				
				new JanelaCadastroDeObras();
				break;
				
			case "atividade":				
				new JanelaCadastroDeAtividade();
				break;
		}
	}
}