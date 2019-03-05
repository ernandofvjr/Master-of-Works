package jframe.ouvintes.janelaDeAtualizacaoDaAtividade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import classes.Atividade;
import jframe.JanelaDeAtualizaçãoDaAtividade;

public class OuvinteDoBotaoExcluirDaJanelaDeAtualizacaoDeAtividade implements ActionListener{
	
	private JanelaDeAtualizaçãoDaAtividade janela;
	
	public OuvinteDoBotaoExcluirDaJanelaDeAtualizacaoDeAtividade(JanelaDeAtualizaçãoDaAtividade janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Atividade atividade = janela.getAtividade();
		
		ImageIcon iconeAtencao = new ImageIcon(getClass().getResource("/imagens/IconeAtencao.png"));
		
		int resp = JOptionPane.showConfirmDialog(janela, "Você deseja excluir esse comentário?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, iconeAtencao);
		if(resp == JOptionPane.YES_OPTION){
			String nomeDoComentario = janela.getTabelaDeComentarios().getValueAt(janela.getTabelaDeComentarios().getSelectedRow(), 0).toString();
			atividade.deletarComentario(nomeDoComentario);
			janela.getTabelaDeComentarios().setModel(janela.getTabelaDeComentarios().atualizarTabela("comentario", null, atividade, null, null, null));
		}		
	}
}