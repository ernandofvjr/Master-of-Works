package jframe.excecoes.validacao;

public class FuncionarioJaDemitidoException extends Exception{
	public FuncionarioJaDemitidoException() {
		super("O funcion�rio j� foi demitido");
	}
}
