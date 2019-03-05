package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Obra {
	
	private String nome;
	private Endereco endereco;
	private ArrayList<Atividade> atividades = new ArrayList<Atividade>();
	private double orcamentoInicial;
	private ArrayList<Gasto> gastos = new ArrayList<Gasto>();
	private ArrayList<Acrescimo> acrescimos = new ArrayList<Acrescimo>();
	private ArrayList<Semana> semanas = new ArrayList<Semana>();
	private String tipoDeConstrucao;
	
	public Obra(String nome, double orcamentoInicial, Endereco endereco, String n){
		this.nome = nome;
		this.orcamentoInicial = orcamentoInicial;
		this.endereco = endereco;
		this.tipoDeConstrucao = n;		
	}	
	public boolean equals(String nome){
		if(this.nome.equals(nome)){
			return true;
		}
		return false;
	}
	public boolean equals(Obra obra){
		if(this.nome.equals(obra.nome)){
			return true;
		}
		return false;
	}
	public String toString(){
		return nome;
	}
	//retorna um double com o valor total dos acréscimos da obra
	public double totalAcrescimos(){
		double total = 0;
		for(Acrescimo a : this.acrescimos){
			total += a.getValor();
		}
		return total;
	}
	//retorna um double com o valor total dos gastos da obra
	public double totalGastos(){
		double total = 0;
		for(Gasto g : this.gastos){
			total += g.getValor();
		}
		return total;
	}
	//retorna um double com o valor total restante da obra
	public double totalRestante(){
		return (this.orcamentoInicial + totalAcrescimos()) - totalGastos();
	}
	
	public Semana buscarSemana(int semanaDoAno, int ano){
		for(Semana semana : this.semanas) {
			if(semana.equals(semanaDoAno, ano)) {
				return semana;
			}
		}
		return null;
	}
	public void addSemana(Semana s){
		
		this.semanas.add(s);
		Collections.sort(semanas, new Comparator<Semana>() {
			  public int compare(Semana o1, Semana o2) {
			      return o1.getPrimeiroDiaDaSemana().compareTo(o2.getPrimeiroDiaDaSemana());
			  }
		});
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public ArrayList<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(ArrayList<Atividade> atividades) {
		this.atividades = atividades;
	}
	public double getOrcamentoInicial() {
		return orcamentoInicial;
	}
	public void setOrcamentoInicial(double orcamentoInicial) {
		this.orcamentoInicial = orcamentoInicial;
	}
	public ArrayList<Gasto> getGastos() {
		return gastos;
	}
	public void setGastos(ArrayList<Gasto> gastos) {
		this.gastos = gastos;
	}
	public ArrayList<Acrescimo> getAcrescimos() {
		return acrescimos;
	}
	public void setAcrescimos(ArrayList<Acrescimo> acrescimos) {
		this.acrescimos = acrescimos;
	}
	public String getTipoDeConstrucao() {
		return tipoDeConstrucao;
	}
	public void setTipoDeConstrucao(String tipoDeConstrucao) {
		this.tipoDeConstrucao = tipoDeConstrucao;
	}
	public ArrayList<Semana> getSemanas() {
		return semanas;
	}
	public void setSemanas(ArrayList<Semana> semanas) {
		this.semanas = semanas;
	}		
}
