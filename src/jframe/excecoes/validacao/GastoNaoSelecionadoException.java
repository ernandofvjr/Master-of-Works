package jframe.excecoes.validacao;

public class GastoNaoSelecionadoException extends Exception{
	public GastoNaoSelecionadoException() {
		super("Não foi selecionado um gasto");
	}
}
