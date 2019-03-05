package jframe.ouvintes.janelaCadastroDeAtividade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import classes.Atividade;
import classes.Validacao;
import jframe.JanelaCadastroDeAtividade;
import jframe.JanelaPrincipal;
import jframe.excecoes.validacao.AtividadeFinalizadaException;
import jframe.excecoes.validacao.DescricaoInvalidaException;

public class OuvinteDoBotaoEditarAtividade implements ActionListener{
	private JanelaCadastroDeAtividade janela;
	public OuvinteDoBotaoEditarAtividade(JanelaCadastroDeAtividade janela) {
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		Atividade atividade = janela.getAtividade();
		
		String descricaoCurta = janela.getDescricaoCurtaADT().getText();
		String descricaoLonga = janela.getDescricaoLongaADT().getText();
		
		try {
			String nome = atividade.getNome();
			
			validacao.validarAtividadeFinalizada(nome);
			
			validacao.validarDescricao(descricaoCurta);
			
			atividade.setDescricaoCurta(descricaoCurta);
			atividade.setDescricaoLonga(descricaoLonga);
			
			JOptionPane.showMessageDialog(janela, "Atividade Editada com sucesso!");
			janela.dispose();
			new JanelaPrincipal();
			
		} catch (DescricaoInvalidaException | AtividadeFinalizadaException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}		
	}
}