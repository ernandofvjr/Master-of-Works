package jframe.excecoes.validacao;

public class AtividadeFinalizadaException extends Exception{
	public AtividadeFinalizadaException() {
		super("A atividade j� foi finalizada");
	}

}
