package jframe.excecoes.validacao;

public class AtividadeJaCadastradaException extends Exception{
	public AtividadeJaCadastradaException() {
		super("Atividade j� cadastrada nessa obra");
	}
}
