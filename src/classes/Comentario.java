package classes;

import java.util.Date;

public class Comentario {
	
	private String nome;
	private String descricao;
	private Date data;
	
	public Comentario(String nome, String descricao, Date data) {
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
	}
	
	public boolean equals(String nome){
		if(nome.equals(this.nome)){
			return true;
		}
		return false;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}