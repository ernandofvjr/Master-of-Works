package jframe.excecoes.validacao;

public class FuncionarioNaoDeletadoException extends Exception{
	public FuncionarioNaoDeletadoException() {
		super("O funcion�rio n�o foi deletado");
	}
}
