package jframe.ouvintes.janelaCadastroDeAtividade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jframe.JanelaCadastroDeAtividade;

public class OuvinteDoBotaoCopiarNomeCadastroDeAtividade implements ActionListener {	
	
	private JanelaCadastroDeAtividade janela;
	
	public OuvinteDoBotaoCopiarNomeCadastroDeAtividade(JanelaCadastroDeAtividade janela){
		this.janela = janela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nome = janela.getAtividades()[janela.getListaAtividadesCC().getSelectedIndex()];
		String texto = janela.getDescricaoCurtaADT().getText();
		String novoTexto = nome + " " + texto;
		janela.getDescricaoCurtaADT().setText(novoTexto);
		
	}

}
