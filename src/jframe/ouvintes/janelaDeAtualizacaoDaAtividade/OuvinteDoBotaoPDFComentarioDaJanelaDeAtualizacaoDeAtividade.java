package jframe.ouvintes.janelaDeAtualizacaoDaAtividade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import classes.PersistenciaPDF;
import jframe.JanelaDeAtualizaçãoDaAtividade;
import jframe.excecoes.validacao.PDFSalvarCancelarException;


public class OuvinteDoBotaoPDFComentarioDaJanelaDeAtualizacaoDeAtividade implements ActionListener {
	
	private JanelaDeAtualizaçãoDaAtividade janela;
	
	public OuvinteDoBotaoPDFComentarioDaJanelaDeAtualizacaoDeAtividade(JanelaDeAtualizaçãoDaAtividade janela) {
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		PersistenciaPDF pe = new PersistenciaPDF();	
		
		try {
			
			pe.salvarComentarios(janela.getAtividade());
			
			JOptionPane.showMessageDialog(janela, "PDF gerado com sucesso");
			
		} catch (PDFSalvarCancelarException e1) {
			ImageIcon avisoICO = new ImageIcon(getClass().getResource("/imagens/IconeAtencao.png"));
			JOptionPane.showMessageDialog(janela, e1.getMessage(), "Aviso", JOptionPane.PLAIN_MESSAGE, avisoICO);
		}		
	}
}