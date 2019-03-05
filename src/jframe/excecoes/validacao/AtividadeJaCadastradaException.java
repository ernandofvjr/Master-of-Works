package jframe.excecoes.validacao;

public class AtividadeJaCadastradaException extends Exception{
	public AtividadeJaCadastradaException() {
		super("Atividade já cadastrada nessa obra");
	}
}
