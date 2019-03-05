package jframe.ouvintes.janelaDeListagemDeAtividades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import classes.Atividade;
import classes.Gerente;
import classes.Validacao;
import jframe.JanelaDeAtualizaçãoDaAtividade;
import jframe.JanelaDeListagemDeAtividade;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;

public class OuvinteDoBotaoAtualizarAtividadeListagemAtividade implements ActionListener{

	private JanelaDeListagemDeAtividade janela;
	
	public OuvinteDoBotaoAtualizarAtividadeListagemAtividade(JanelaDeListagemDeAtividade janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		Gerente gerente = Gerente.obterInstancia();		
		
		int linha = janela.getTabelaDeListagem().getSelectedRow();
		
		try {
			validacao.validarTabela(linha);
			
			Atividade atividade = gerente.getAtividades().get(linha);
			
			janela.dispose();
			
			new JanelaDeAtualizaçãoDaAtividade(atividade);
			
		} catch (TabelaNaoSelecionadaException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}		
	}
}