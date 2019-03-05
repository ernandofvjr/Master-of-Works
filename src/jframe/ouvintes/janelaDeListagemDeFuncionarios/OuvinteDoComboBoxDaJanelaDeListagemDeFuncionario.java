package jframe.ouvintes.janelaDeListagemDeFuncionarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import classes.Funcionario;
import classes.Gerente;
import jframe.JanelaDeListagemDeFuncionarios;

public class OuvinteDoComboBoxDaJanelaDeListagemDeFuncionario implements ActionListener {
	
	private JanelaDeListagemDeFuncionarios janela;
	
	public OuvinteDoComboBoxDaJanelaDeListagemDeFuncionario(JanelaDeListagemDeFuncionarios janela) {
		this.janela = janela;
	}	
	public void actionPerformed(ActionEvent e) {
		
		
		String escolha = janela.getFiltroDeEscolhaCB().getSelectedItem().toString();
		Gerente gerente = Gerente.obterInstancia();
		ArrayList<Funcionario> arrayFiltrado = new ArrayList<Funcionario>();
		
		switch (escolha) {
		
		case "Todos":{
			for(Funcionario funcionario : gerente.getFuncionarios()){
				arrayFiltrado.add(funcionario);
			}
			janela.setArray(arrayFiltrado);
			break;
		}
		case "Empregados":{			
			
			for(Funcionario funcionario : gerente.getFuncionarios()){
				if(funcionario.isDemitido() == false){
					arrayFiltrado.add(funcionario);
				}
			}
			janela.setArray(arrayFiltrado);
			break;
		}
		case "Demitidos":{
			for(Funcionario funcionario : gerente.getFuncionarios()){
				if(funcionario.isDemitido()){
					arrayFiltrado.add(funcionario);
				}
			}
			janela.setArray(arrayFiltrado);
			break;
		}	
		}
		janela.getTabelaDeListagem().setModel(janela.getTabelaDeListagem().atualizarTabela("listagemDeFuncionario", null, null, null, arrayFiltrado, null));
	}
}