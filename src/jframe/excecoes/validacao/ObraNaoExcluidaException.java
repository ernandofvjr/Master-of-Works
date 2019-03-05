package jframe.excecoes.validacao;

public class ObraNaoExcluidaException extends Exception{
	public ObraNaoExcluidaException() {
		super("A obra não foi excluida");
	}
}
