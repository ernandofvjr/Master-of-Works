package jframe.excecoes.validacao;

public class GastoNaoSelecionadoException extends Exception{
	public GastoNaoSelecionadoException() {
		super("N�o foi selecionado um gasto");
	}
}
