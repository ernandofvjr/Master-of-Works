package jframe.ouvintes.janelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import classes.Atividade;
import jframe.JanelaDeAtualizaçãoDaAtividade;
import jframe.JanelaPrincipal;

public class OuvinteDaJanelaAtualizarAtividadeDaJanelaPrincipal implements ActionListener{

	private JanelaPrincipal janela;
	public OuvinteDaJanelaAtualizarAtividadeDaJanelaPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent e) {
//		Validacao validacao = new Validacao();
		
		
//		int linha = janela.getAtividadeTBL().getSelectedRow();
//		Atividade atividade = Gerente.obterInstancia().getAtividades().get(linha);
		Object objeto = janela.getPainelDaArvore().getRolagem().getArvore().getLastSelectedPathComponent();
		if(objeto instanceof Atividade){
			Atividade atividade = (Atividade) objeto;
			janela.dispose();
			new JanelaDeAtualizaçãoDaAtividade(atividade);
		}
		
//		try {
//			validacao.validarTabela(linha);
//			
//			Atividade atividade = Gerente.obterInstancia().getAtividades().get(linha);
//			
//			janela.dispose();
//			
//			new JanelaDeAtualizaçãoDaAtividade(atividade);
//			
//		} catch (TabelaNaoSelecionadaException e1) {
//			JOptionPane.showMessageDialog(janela, e1.getMessage());
//		}		
	}

}
