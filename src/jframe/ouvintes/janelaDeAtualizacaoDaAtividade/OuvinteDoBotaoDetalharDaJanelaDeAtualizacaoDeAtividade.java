package jframe.ouvintes.janelaDeAtualizacaoDaAtividade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import classes.Atividade;
import classes.Comentario;
import classes.Validacao;
import jframe.JanelaDeAtualizaçãoDaAtividade;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;

public class OuvinteDoBotaoDetalharDaJanelaDeAtualizacaoDeAtividade implements ActionListener{
	private JanelaDeAtualizaçãoDaAtividade janela;
	public OuvinteDoBotaoDetalharDaJanelaDeAtualizacaoDeAtividade(JanelaDeAtualizaçãoDaAtividade janela) {
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		Atividade atividade = janela.getAtividade();
		
		int linha = janela.getTabelaDeComentarios().getSelectedRow();
		
		try {
			validacao.validarTabela(linha);
			
			String nomeDoComentario = janela.getTabelaDeComentarios().getValueAt(linha, 0).toString();
			
			Comentario comentario = atividade.buscarComentario(nomeDoComentario);
			
			janela.getNomeDoComentarioCDT().setText("");
			
			janela.getComentarioADT().setText(comentario.getDescricao());
			
		} catch (TabelaNaoSelecionadaException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}		
	}
}