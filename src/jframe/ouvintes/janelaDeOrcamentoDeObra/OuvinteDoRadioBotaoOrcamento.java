package jframe.ouvintes.janelaDeOrcamentoDeObra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jframe.JanelaDeOrcamentoDaObra;

public class OuvinteDoRadioBotaoOrcamento implements ActionListener {
	private JanelaDeOrcamentoDaObra janela;
	private String tipo;
	
	public OuvinteDoRadioBotaoOrcamento(JanelaDeOrcamentoDaObra janela, String tipo) {
		this.janela = janela;
		this.tipo = tipo;
	}

	public void actionPerformed(ActionEvent e) {
		switch (tipo) {
		case "Acréscimo":{
			janela.getDeletarGastoBT().setEnabled(false);
			janela.getTabelaAcrescimoEGastoTBL().setModel(janela.getTabelaAcrescimoEGastoTBL().atualizarTabela("acréscimo", janela.getObra(), null, null, null, null));
			break;
		}
		case "Gasto":{
			janela.getDeletarGastoBT().setEnabled(true);
			janela.getTabelaAcrescimoEGastoTBL().setModel(janela.getTabelaAcrescimoEGastoTBL().atualizarTabela("gasto", janela.getObra(), null, null, null, null));
			break;
		}
		}
	}
}