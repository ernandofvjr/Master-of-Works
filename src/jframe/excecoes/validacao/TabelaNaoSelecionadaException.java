package jframe.excecoes.validacao;

public class TabelaNaoSelecionadaException extends Exception{
	public TabelaNaoSelecionadaException() {
		super("Não foi selecionado nenhuma  linha da tabela");
	}
}
