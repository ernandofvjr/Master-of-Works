package jframe.excecoes.validacao;

public class EnderecoCEPInvalidoException extends Exception{
	public EnderecoCEPInvalidoException() {
		super("CEP inválido");
	}
}
