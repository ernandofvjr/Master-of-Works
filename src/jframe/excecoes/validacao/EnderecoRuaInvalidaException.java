package jframe.excecoes.validacao;

public class EnderecoRuaInvalidaException extends Exception{
	public EnderecoRuaInvalidaException() {
		super("Nome da rua inválido");
	}
}
