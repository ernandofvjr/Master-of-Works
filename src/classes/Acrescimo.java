package classes;

import java.util.Date;

public class Acrescimo {
	private String nome;
	private Date data;
	private double valor;
	private String descricao;
	
	public Acrescimo(String nome, Date data, double valor, String descricao){
		this.nome= nome;
		this.data = data;
		this.valor = valor;
		this.descricao = descricao;
	}	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
}
