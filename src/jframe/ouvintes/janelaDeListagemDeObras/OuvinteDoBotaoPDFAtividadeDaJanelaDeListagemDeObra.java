package jframe.ouvintes.janelaDeListagemDeObras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import classes.Gerente;
import classes.PersistenciaPDF;
import classes.Validacao;
import jframe.JanelaDeListagemDeObras;
import jframe.excecoes.validacao.PDFSalvarCancelarException;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;

public class OuvinteDoBotaoPDFAtividadeDaJanelaDeListagemDeObra implements ActionListener{
	
	private JanelaDeListagemDeObras janela;
	
	public OuvinteDoBotaoPDFAtividadeDaJanelaDeListagemDeObra(JanelaDeListagemDeObras janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		PersistenciaPDF pe = new PersistenciaPDF();
		
		int linha = janela.getTabelaDeListagem().getSelectedRow();
		
		try {
			validacao.validarTabela(linha);
			
			String nomeDaObra = janela.getTabelaDeListagem().getValueAt(linha, 0).toString();
			
			Gerente gerente = Gerente.obterInstancia();
			
			pe.salvarExcecucao(gerente.buscarObra(nomeDaObra));
			
			JOptionPane.showMessageDialog(janela, "PDF gerado com sucesso");
			
		} catch (TabelaNaoSelecionadaException | PDFSalvarCancelarException e1) {
			ImageIcon avisoICO = new ImageIcon(getClass().getResource("/imagens/IconeAtencao.png"));
			JOptionPane.showMessageDialog(janela, e1.getMessage(), "Aviso", JOptionPane.PLAIN_MESSAGE, avisoICO);
		}
	}
}