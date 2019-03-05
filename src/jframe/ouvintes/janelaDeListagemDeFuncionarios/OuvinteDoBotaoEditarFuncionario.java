package jframe.ouvintes.janelaDeListagemDeFuncionarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import classes.Gerente;
import classes.Validacao;
import jframe.JanelaCadastroDeFuncionarios;
import jframe.JanelaDeListagemDeFuncionarios;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;

public class OuvinteDoBotaoEditarFuncionario implements ActionListener{
	
	private JanelaDeListagemDeFuncionarios janela;
	
	public OuvinteDoBotaoEditarFuncionario(JanelaDeListagemDeFuncionarios janela) {
		this.janela = janela;
	}	
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		Gerente gerente = Gerente.obterInstancia();
		
		
		int linha = janela.getTabelaDeListagem().getSelectedRow();
		
		try {
			validacao.validarTabela(linha);
			
			String nomeDoFuncionario = janela.getTabelaDeListagem().getValueAt(janela.getTabelaDeListagem().getSelectedRow(), 0).toString();
			
			janela.dispose();
			
			new JanelaCadastroDeFuncionarios(gerente.buscarFuncionario(nomeDoFuncionario));
			
		} catch (TabelaNaoSelecionadaException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}
	}
}