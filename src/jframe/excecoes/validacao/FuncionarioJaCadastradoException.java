package jframe.excecoes.validacao;

public class FuncionarioJaCadastradoException extends Exception{
	public FuncionarioJaCadastradoException() {
		super("Funcion�rio j� cadastrado");
	}
}
