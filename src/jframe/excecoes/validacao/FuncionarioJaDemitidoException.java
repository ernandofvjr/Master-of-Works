package jframe.excecoes.validacao;

public class FuncionarioJaDemitidoException extends Exception{
	public FuncionarioJaDemitidoException() {
		super("O funcionário já foi demitido");
	}
}
