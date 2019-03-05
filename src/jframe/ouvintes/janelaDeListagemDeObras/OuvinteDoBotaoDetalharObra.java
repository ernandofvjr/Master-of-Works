package jframe.ouvintes.janelaDeListagemDeObras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import classes.Gerente;
import classes.Validacao;
import jframe.JanelaCadastroDeObras;
import jframe.JanelaDeListagemDeObras;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;

public class OuvinteDoBotaoDetalharObra implements ActionListener{
	private JanelaDeListagemDeObras janela;
	public OuvinteDoBotaoDetalharObra(JanelaDeListagemDeObras janela) {
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		Gerente gerente = Gerente.obterInstancia();		
		
		int linha = janela.getTabelaDeListagem().getSelectedRow();
		
		try {
			validacao.validarTabela(linha);
			
			String nomeDaObra = janela.getTabelaDeListagem().getValueAt(janela.getTabelaDeListagem().getSelectedRow(), 0).toString();
			
			janela.dispose();
			
			new JanelaCadastroDeObras(gerente.buscarObra(nomeDaObra));
			
		} catch (TabelaNaoSelecionadaException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}
	}
}