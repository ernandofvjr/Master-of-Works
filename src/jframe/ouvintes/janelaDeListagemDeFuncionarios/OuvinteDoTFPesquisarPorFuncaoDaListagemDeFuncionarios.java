package jframe.ouvintes.janelaDeListagemDeFuncionarios;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import classes.Funcionario;
import jframe.JanelaDeListagemDeFuncionarios;

public class OuvinteDoTFPesquisarPorFuncaoDaListagemDeFuncionarios implements KeyListener{
	private JanelaDeListagemDeFuncionarios janela;
	public OuvinteDoTFPesquisarPorFuncaoDaListagemDeFuncionarios(JanelaDeListagemDeFuncionarios janela) {
		this.janela = janela;
	}
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {
		
		janela.getTfPesquisarPorNome().setText("");
		
		String funcao = janela.getTfPesquisarPorFuncao().getText();			
		
		ArrayList<Funcionario> arrayFiltrado = new ArrayList<Funcionario>();
		ArrayList<Funcionario> array = janela.getArray();
		
		for(Funcionario f: array){
			String funcaoF = "";
			
			if(f.getNome().length() >= funcao.length()){
				
				for(int i = 0; i<funcao.length();i++){
					funcaoF += f.getFuncao().charAt(i);
				}
				if(funcaoF.equals(funcao)){
					arrayFiltrado.add(f);
				}
			}
		}		
		janela.getTabelaDeListagem().setModel(janela.getTabelaDeListagem().atualizarTabela("listagemDeFuncionario", null, null, null, arrayFiltrado, null));
	}
}