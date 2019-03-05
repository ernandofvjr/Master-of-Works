package jframe.excecoes.validacao;

public class FuncionarioNaoDeletadoException extends Exception{
	public FuncionarioNaoDeletadoException() {
		super("O funcionário não foi deletado");
	}
}
