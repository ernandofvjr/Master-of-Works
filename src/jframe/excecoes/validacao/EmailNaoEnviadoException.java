package jframe.excecoes.validacao;

public class EmailNaoEnviadoException extends Exception{
	public EmailNaoEnviadoException() {
		super("Erro ao enviar email");
	}
}
