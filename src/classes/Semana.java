package classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Semana {
	
	private int semanaDoAno;
	private int ano;
	private Date primeiroDiaDaSemana;
	private Date segundoDiaDaSemana;
	private ArrayList<Atividade> atividades = new ArrayList<Atividade>();	
	
	public Semana(){
		
	}
	public Semana(int semanaDoAno, int ano) {
		this.semanaDoAno = semanaDoAno;
		this.ano = ano;
		calcularDatas();
	}
	private void calcularDatas(){
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, this.ano);
		cal.setFirstDayOfWeek(Calendar.SUNDAY);
		cal.set(Calendar.WEEK_OF_YEAR, semanaDoAno);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		primeiroDiaDaSemana = cal.getTime();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		segundoDiaDaSemana = cal.getTime();
	}
	public boolean equals(int semanaDoAno, int ano){
		if(this.semanaDoAno == semanaDoAno && this.ano == ano) {
			return true;
		}
		return false;
	}public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(primeiroDiaDaSemana);
	}
	public String toString(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
	public void adicionarAtividade(Atividade atividade){
		this.atividades.add(atividade);		
	}
	
	public ArrayList<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(ArrayList<Atividade> atividades) {
		this.atividades = atividades;
	}
	public Date getPrimeiroDiaDaSemana() {
		return primeiroDiaDaSemana;
	}
	public void setPrimeiroDiaDaSemana(Date primeiroDiaDaSemana) {
		this.primeiroDiaDaSemana = primeiroDiaDaSemana;
	}
	public Date getSegundoDiaDaSemana() {
		return segundoDiaDaSemana;
	}
	public void setSegundoDiaDaSemana(Date segundoDiaDaSemana) {
		this.segundoDiaDaSemana = segundoDiaDaSemana;
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
}