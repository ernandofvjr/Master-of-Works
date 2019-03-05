package jframe.ouvintes.janelaDeListagemDeFuncionarios;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import classes.Funcionario;
import jframe.JanelaDeListagemDeFuncionarios;

public class OuvinteDoTFPesquisarPorNomeDaListagemDeFuncionarios implements KeyListener{
	private JanelaDeListagemDeFuncionarios janela;
	public OuvinteDoTFPesquisarPorNomeDaListagemDeFuncionarios(JanelaDeListagemDeFuncionarios janela) {
		this.janela = janela;
	}
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		janela.getTfPesquisarPorFuncao().setText("");
		
		String nome = janela.getTfPesquisarPorNome().getText();
		
		ArrayList<Funcionario> arrayFiltrado = new ArrayList<Funcionario>();
		ArrayList<Funcionario> array = janela.getArray();
		
		for(Funcionario f: array){
			String nomeF = "";
			
			if(f.getNome().length() >= nome.length()){
				
				for(int i = 0; i<nome.length();i++){
					nomeF += f.getNome().charAt(i);
				}				
				if(nomeF.equals(nome)){
					arrayFiltrado.add(f);
				}
			}
		}
		janela.getTabelaDeListagem().setModel(janela.getTabelaDeListagem().atualizarTabela("listagemDeFuncionario", null, null, null, arrayFiltrado, null));
	}
}