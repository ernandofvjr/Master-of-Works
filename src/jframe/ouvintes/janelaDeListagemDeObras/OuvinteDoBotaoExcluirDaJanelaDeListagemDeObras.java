package jframe.ouvintes.janelaDeListagemDeObras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import classes.Atividade;
import classes.Gerente;
import classes.Validacao;
import jframe.JanelaDeListagemDeObras;
import jframe.excecoes.validacao.ObraNaoExcluidaException;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;

public class OuvinteDoBotaoExcluirDaJanelaDeListagemDeObras implements ActionListener {
	
	private JanelaDeListagemDeObras janela;
	
	public OuvinteDoBotaoExcluirDaJanelaDeListagemDeObras(JanelaDeListagemDeObras janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		Gerente gerente = Gerente.obterInstancia();
		
		int linha = janela.getTabelaDeListagem().getSelectedRow();
		
		try {
			validacao.validarTabela(linha);
			
			String nomeDaObra = janela.getTabelaDeListagem().getValueAt(janela.getTabelaDeListagem().getSelectedRow(), 0).toString();
			ImageIcon iconeAtencao = new ImageIcon(getClass().getResource("/imagens/IconeAtencao.png"));
			
			int resp = JOptionPane.showConfirmDialog(janela, "Você deseja excluir essa obra?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, iconeAtencao);
			
			if(resp == JOptionPane.YES_OPTION){
				
				ArrayList<Atividade> atividadesParaExclusao = new ArrayList<Atividade>();
				for(Atividade atividadeNaObra : gerente.buscarObra(nomeDaObra).getAtividades()){
					for(Atividade atividadeNoGerente : gerente.getAtividades()){
						if(atividadeNaObra.equals(atividadeNoGerente)){						
							atividadesParaExclusao.add(atividadeNoGerente);
						}
					}
				}
				for(Atividade atividadeParaExclusao : atividadesParaExclusao){
					
					gerente.getAtividades().remove(atividadeParaExclusao);
				}
				gerente.buscarObra(nomeDaObra).getAtividades().clear();
				gerente.getObras().remove(gerente.buscarObra(nomeDaObra));
				
				janela.getTabelaDeListagem().setModel(janela.getTabelaDeListagem().atualizarTabela("listaDeObra"));
			}
			else {
				throw new ObraNaoExcluidaException();
			}			
		} catch (TabelaNaoSelecionadaException | ObraNaoExcluidaException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}		
	}
}