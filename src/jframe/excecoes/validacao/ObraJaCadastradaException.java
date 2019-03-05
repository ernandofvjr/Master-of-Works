package jframe.excecoes.validacao;

public class ObraJaCadastradaException extends Exception{
	public ObraJaCadastradaException() {
		super("Obra já cadastrada");
	}
}
