package jframe.ouvintes.janelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import classes.Atividade;
import classes.Funcionario;
import classes.Gerente;
import classes.Validacao;
import jframe.JanelaCadastroDeAtividade;
import jframe.JanelaCadastroDeFuncionarios;
import jframe.JanelaDeOrcamentoDaObra;
import jframe.JanelaPrincipal;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;

public class OuvinteDoBotaoEditarPrincipal implements ActionListener {
	
	private JanelaPrincipal janela;
	private String nome;
	
	public OuvinteDoBotaoEditarPrincipal(JanelaPrincipal janela, String nome){
		this.janela = janela;
		this.nome = nome;
	}

	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		Gerente gerente = Gerente.obterInstancia();	
		int linha = janela.getObraTBL().getSelectedRow();
		String nomeDaObra = null;
		try {
			validacao.validarTabela(linha);
			nomeDaObra = janela.getObraTBL().getValueAt(janela.getObraTBL().getSelectedRow(), 0).toString();
		} catch (TabelaNaoSelecionadaException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
			return;
		}
		
				
		switch (nome) {
		case "obra":{
			if(nomeDaObra != null){
				janela.dispose();
				new JanelaDeOrcamentoDaObra(gerente.buscarObra(nomeDaObra));
			}
			break;
		}
		case "atividade":{
//			linha = janela.getAtividadeTBL().getSelectedRow();
			String nomeDaAtividade = null;
			try {
				nomeDaAtividade = janela.getPainelDaArvore().getRolagem().getArvore().getLastSelectedPathComponent().toString();
			} catch (Exception e2) {
				e2.getMessage();
			}
			if(nomeDaAtividade != null) {
				try {
					validacao.validarTabela(linha);
//					String nomeDaAtividade = janela.getAtividadeTBL().getValueAt(janela.getAtividadeTBL().getSelectedRow(), 0).toString();
					Atividade atividade = gerente.buscarAtividade(nomeDaObra, nomeDaAtividade);
					if(atividade != null) {
						janela.dispose();
						new JanelaCadastroDeAtividade(atividade);
					}
					
				} catch (TabelaNaoSelecionadaException e1) {
					JOptionPane.showMessageDialog(janela, e1.getMessage());
				}
				break;
			}
			break;
		}
		case "funcionario":{
			linha = janela.getFuncionarioTBL().getSelectedRow();
			String nomeDaAtividade = null;
			try {
				nomeDaAtividade = janela.getPainelDaArvore().getRolagem().getArvore().getLastSelectedPathComponent().toString();
			} catch (Exception e2) {
				e2.getMessage();
			}
			if(nomeDaAtividade != null) {
				try {
					validacao.validarTabela(linha);
					
					String nomeDoFuncionario = janela.getFuncionarioTBL().getValueAt(janela.getFuncionarioTBL().getSelectedRow(),0).toString();
					Funcionario funcionario = gerente.buscarFuncionario(nomeDaObra, nomeDaAtividade, nomeDoFuncionario);
					if(funcionario != null) {
						janela.dispose();
						new JanelaCadastroDeFuncionarios(funcionario);
					}
					
				} catch (TabelaNaoSelecionadaException e1) {
					JOptionPane.showMessageDialog(janela, e1.getMessage());
				}
				break;
			}
			break;
			
		}
		}
	}	
}