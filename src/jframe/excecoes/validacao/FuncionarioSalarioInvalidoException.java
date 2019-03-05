package jframe.excecoes.validacao;

public class FuncionarioSalarioInvalidoException extends Exception{
	public FuncionarioSalarioInvalidoException() {
		super("Salário inválido");
	}
}
