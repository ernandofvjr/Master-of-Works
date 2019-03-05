package jframe.excecoes.validacao;

public class AtividadeStatusInvalidoException extends Exception{
	public AtividadeStatusInvalidoException() {
		super("Status não alterado");
	}
}
