package jframe.ouvintes.janelaDeOrcamentoDeObra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import classes.Validacao;
import jframe.JanelaDeOrcamentoDaObra;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;

public class OuvinteDoBotaoDetalharGastoOuAcrescimoOrcamento implements ActionListener {

	private JanelaDeOrcamentoDaObra janela;
	
	public OuvinteDoBotaoDetalharGastoOuAcrescimoOrcamento(JanelaDeOrcamentoDaObra janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		Validacao validacao = new Validacao();
		
		int linha = janela.getTabelaAcrescimoEGastoTBL().getSelectedRow();
		
		janela.getDescricaoADT().setCaretPosition(0);
		
		try {
			validacao.validarTabela(linha);
			
			if(janela.getEscolherAcrescimoRB().isSelected()){
				janela.getDescricaoADT().setText(janela.getObra().getAcrescimos().get(janela.getTabelaAcrescimoEGastoTBL().getSelectedRow()).getDescricao());
			}
			else{
				janela.getDescricaoADT().setText(janela.getObra().getGastos().get(janela.getTabelaAcrescimoEGastoTBL().getSelectedRow()).getDescricao());
			}
			janela.getNomeCDT().setText("");
		} catch (TabelaNaoSelecionadaException e) {
			JOptionPane.showMessageDialog(janela, e.getMessage());
		}		
	}
}