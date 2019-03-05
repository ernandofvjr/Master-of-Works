package jframe.ouvintes.janelaDeListagemDeAtividades;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import classes.Gerente;
import classes.Obra;
import jframe.JanelaDeListagemDeAtividade;

public class OuvinteDoComboBoxFiltrarAtividadePorObra implements ItemListener{

	private JanelaDeListagemDeAtividade janela;
	
	public OuvinteDoComboBoxFiltrarAtividadePorObra(JanelaDeListagemDeAtividade janela) {
		this.janela = janela;
	}
	

	public void itemStateChanged(ItemEvent e) {
		String nome = janela.getCbObra().getSelectedItem().toString();
		
		if(!nome.equals("(Sem filtro)")){
		
			Gerente gerente = Gerente.obterInstancia();
			
			Obra o = gerente.buscarObra(nome);
			
			janela.getTabelaDeListagem().setModel(janela.getTabelaDeListagem().atualizarTabela("listagemDeAtividade", null, null, null, null, o.getAtividades()));
			
		}
		else {
			janela.getTabelaDeListagem().setModel(janela.getTabelaDeListagem().atualizarTabela("listaDeAtividade"));
		}		
	}
}
