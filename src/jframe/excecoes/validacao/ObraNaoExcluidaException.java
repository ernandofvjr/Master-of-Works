package jframe.excecoes.validacao;

public class ObraNaoExcluidaException extends Exception{
	public ObraNaoExcluidaException() {
		super("A obra n�o foi excluida");
	}
}
