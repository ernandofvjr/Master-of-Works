package jframe.ouvintes.janelaDeListagemDeAtividades;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import classes.Atividade;
import classes.Gerente;
import jframe.JanelaDeListagemDeAtividade;

public class OuvinteDoComboBoxFiltrarAtividadePorSemana implements ItemListener {
	
	private JanelaDeListagemDeAtividade janela;
	
	public OuvinteDoComboBoxFiltrarAtividadePorSemana(JanelaDeListagemDeAtividade janela) {
		this.janela = janela;
	}

	public void itemStateChanged(ItemEvent e) {
		
		String nome = janela.getCbSemana().getSelectedItem().toString();
		
		if(!nome.equals("(Sem filtro)")) {
			int num = Integer.parseInt(nome);
			
			Gerente gerente = Gerente.obterInstancia();
			
			ArrayList<Atividade> atividades = new ArrayList<Atividade>();
			
			for (Atividade atividade : gerente.getAtividades()) {
				if(atividade.getSemana() == num){
					atividades.add(atividade);
				}
			}
			
			janela.getTabelaDeListagem().setModel(janela.getTabelaDeListagem().atualizarTabela("listagemDeAtividade", null, null, null, null, atividades));
		}
		else {
			janela.getTabelaDeListagem().setModel(janela.getTabelaDeListagem().atualizarTabela("listaDeAtividade"));
		}
		
		
	}
}