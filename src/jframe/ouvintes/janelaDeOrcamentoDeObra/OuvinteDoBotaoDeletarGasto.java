package jframe.ouvintes.janelaDeOrcamentoDeObra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import classes.Gasto;
import classes.Obra;
import classes.Validacao;
import jframe.JanelaDeOrcamentoDaObra;
import jframe.excecoes.validacao.GastoNaoSelecionadoException;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;

public class OuvinteDoBotaoDeletarGasto implements ActionListener{
	private JanelaDeOrcamentoDaObra janela;
	
	public OuvinteDoBotaoDeletarGasto(JanelaDeOrcamentoDaObra janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		int linha = janela.getTabelaAcrescimoEGastoTBL().getSelectedRow();		
		
		try {
			
			validacao.validarTabela(linha);			
			
			if(janela.getEscolherGastoRB().isSelected()){
				ImageIcon iconeAtencao = new ImageIcon(getClass().getResource("/imagens/IconeAtencao.png"));
				
				int resp = JOptionPane.showConfirmDialog(janela, "Você deseja exlcuir esse gasto?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, iconeAtencao);
				
				if(resp == JOptionPane.YES_OPTION){
					
					Obra obra = janela.getObra();
					
					Gasto gasto = obra.getGastos().get(linha);
					
					obra.getGastos().remove(gasto);
					
					janela.getTabelaAcrescimoEGastoTBL().setModel(janela.getTabelaAcrescimoEGastoTBL().atualizarTabela("gasto", janela.getObra(), null, null, null, null));
					
					janela.atualizarEtiquetas();
				}				
			}
			else{
				throw new GastoNaoSelecionadoException();
			}
		} catch (TabelaNaoSelecionadaException | GastoNaoSelecionadoException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}
	}
}