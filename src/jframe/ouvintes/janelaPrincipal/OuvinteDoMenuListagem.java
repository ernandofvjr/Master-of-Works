package jframe.ouvintes.janelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jframe.JanelaDeListagemDeAtividade;
import jframe.JanelaDeListagemDeFuncionarios;
import jframe.JanelaDeListagemDeObras;
import jframe.JanelaPrincipal;

public class OuvinteDoMenuListagem implements ActionListener{
	
	private JanelaPrincipal janela;	
	
	public OuvinteDoMenuListagem(JanelaPrincipal janela) {
		this.janela = janela;
	}	
	public void actionPerformed(ActionEvent e) {		
		String comando = e.getActionCommand();
		
		janela.dispose();
		
		switch (comando){
			case "Listar Funcionários":
				new JanelaDeListagemDeFuncionarios();
				break;
			case "Listar Obras":				
				new JanelaDeListagemDeObras();
				break;
			case "Listar Atividades":
				new JanelaDeListagemDeAtividade();
				break;
		}
	}
}
