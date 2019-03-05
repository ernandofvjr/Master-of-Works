package jframe.excecoes.validacao;

public class OrcamentoTipoInvalidoException extends Exception{
	public OrcamentoTipoInvalidoException() {
		super("Tipo de alteração de orçamento inválido");
	}
}
