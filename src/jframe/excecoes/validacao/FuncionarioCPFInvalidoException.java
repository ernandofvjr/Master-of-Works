package jframe.excecoes.validacao;

public class FuncionarioCPFInvalidoException extends Exception{
	public FuncionarioCPFInvalidoException() {
		super("CPF inválido");
	}
}
