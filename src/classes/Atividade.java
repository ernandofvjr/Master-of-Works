package classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Atividade {
	
	private String nome;
	private String descricaoLonga;
	private String descricaoCurta;	
	private String status;
	private Obra obra;
	
	private Date dataDeCriacao;
	private Date dataDeConclusao;
	private int semana;
	private int semanaDoAno;
	private int ano;
	private int prazo;
	private boolean[] diasDaSemanaPlanejado = new boolean[7];
	private boolean[] diasDaSemanaExecutado = new boolean[7];
	private boolean botaoSalvar;
	
	private ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
	
	public Atividade() {}
	
	public Atividade(String nome, int prazo, String descricaoCurta, String descricaoLonga, Date dataDeCriacao, Date dataDeConclusao, String status, int semana, boolean[] diasDaSemana, Obra obra){
		this.nome = nome;
		this.prazo = prazo;
		this.descricaoLonga = descricaoLonga;
		this.descricaoCurta = descricaoCurta;
		this.dataDeCriacao = dataDeCriacao;
		this.status = status;
		this.dataDeConclusao = dataDeConclusao;
		this.semana = semana;
		this.diasDaSemanaPlanejado = diasDaSemana;
		this.obra = obra;
		this.botaoSalvar = true;
		calcularDatas();		
	}
	private void calcularDatas(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataDeCriacao);
		this.semanaDoAno = cal.get(Calendar.WEEK_OF_YEAR);
		this.ano = cal.get(Calendar.YEAR);
	}
	//compara a data atual com a data de conclusão e muda o atributo status da Atividade
	public void atualizarStatus(){
		if(!this.status.equals("Desativada")){
			Calendar calendario = Calendar.getInstance();
			Date dataAtual = calendario.getTime();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(dataAtual);
			cal.add(Calendar.DATE, -1);
			dataAtual = cal.getTime();
			
			if (dataAtual.before(this.dataDeConclusao)){
				status = "Ativada";
			}
			else{
				status = "Desativada";
				String novoNome = "X " + this.nome;
				this.nome = novoNome;
			}
		}						
	}
	public boolean checarPlanejamento(){
		
		boolean temp = false;
		for (boolean  b : this.diasDaSemanaExecutado) {
			if(b == true) {
				temp = true;
				
			}
		}
		if(temp == false) {
			return false;
		}
		
		boolean[] listaDeChecagem = new boolean[7];
		int contador = 0;
		
		for (boolean b : this.diasDaSemanaPlanejado) {			
			if(b == diasDaSemanaExecutado[contador]) {
				listaDeChecagem[contador] = true;
			}
			else {
				listaDeChecagem[contador] = false;
			}
			contador++;
		}
		for (boolean b : listaDeChecagem) {
			if(b == false) {
				return false;
			}
		}
		return true;
	}
	public Comentario buscarComentario(String nome){
		for(Comentario c: this.comentarios){
			if(c.equals(nome))
				return c;
		}
		return null;
	}
	public void deletarComentario(String nome){		
		for(Comentario c: this.comentarios){
			if(c.equals(nome))
			comentarios.remove(c);
			return;				
		}
	}
	public boolean equals(String nome){
		if(this.nome.equals(nome)){
			return true;
		}
		return false;
	}
	public String toString() {
		return nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	public int getPrazo() {
		return prazo;
	}
	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}	
	public String getDescricaoLonga() {
		return descricaoLonga;
	}
	public void setDescricaoLonga(String descricaoLonga) {
		this.descricaoLonga = descricaoLonga;
	}
	public String getDescricaoCurta() {
		return descricaoCurta;
	}
	public void setDescricaoCurta(String descricaoCurta) {
		this.descricaoCurta = descricaoCurta;
	}
	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	public ArrayList<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(ArrayList<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDataDeConclusao() {
		return dataDeConclusao;
	}
	public void setDataDeConclusao(Date dataDeConclusao) {
		this.dataDeConclusao = dataDeConclusao;
	}
	public int getSemana() {
		return semana;
	}
	public void setSemana(int semana) {
		this.semana = semana;
	}
	public boolean[] getDiasDaSemanaPlanejado() {
		return diasDaSemanaPlanejado;
	}
	public void setDiasDaSemanaPlanejado(boolean[] diasDaSemanaPlanejado) {
		this.diasDaSemanaPlanejado = diasDaSemanaPlanejado;
	}
	public boolean[] getDiasDaSemanaExecutado() {
		return diasDaSemanaExecutado;
	}
	public void setDiasDaSemanaExecutado(boolean[] diasDaSemanaExecutado) {
		this.diasDaSemanaExecutado = diasDaSemanaExecutado;
	}
	public int getSemanaDoAno() {
		return semanaDoAno;
	}
	public void setSemanaDoAno(int semanaDoAno) {
		this.semanaDoAno = semanaDoAno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public Obra getObra() {
		return obra;
	}
	public void setObra(Obra obra) {
		this.obra = obra;
	}
	public boolean isBotaoSalvar() {
		return botaoSalvar;
	}
	public void setBotaoSalvar(boolean botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}	
}