package jframe.excecoes.validacao;

public class TabelaNaoSelecionadaException extends Exception{
	public TabelaNaoSelecionadaException() {
		super("N�o foi selecionado nenhuma  linha da tabela");
	}
}
