package classes;

import java.io.File;
import java.util.ArrayList;


/**
 * Classe Que guarda todos os funcionários e obras
 * 
 * @author Ernando
 *
 */
public class Gerente {
	
	private static Gerente gerente;
	
	private ArrayList<Obra> obras = new ArrayList<Obra>();		
	private ArrayList<Atividade> atividades = new ArrayList<Atividade>();	
	private ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
//	private ArrayList<Semana> semanas = new ArrayList<Semana>();
	
	/**
	 * esse método faz com que só exista um gerente
	 * @return Gerente
	 */
	public static Gerente obterInstancia() {
		if (gerente == null)
			gerente = new Gerente();
		return gerente;
	}	
	/**
	 * esse método instancia o gerente com os valores do xml
	 * @param PersistenciaGerente
	 * @return Gerente
	 */
	public static Gerente obterInstancia(PersistenciaGerente pe) {
		gerente = pe.recuperarGerente();
		return gerente;
	}
	public static Gerente obterInstancia(PersistenciaGerente pe, File arquivo) { 
		gerente = pe.recuperarGerente(arquivo);
		return gerente;
	}	
	/**
	 * esse método busca uma obra no array de obras pelo nome
	 * @param nomeDaObra
	 * @return Obra
	 */
	public Obra buscarObra(String nomeDaObra){
		for(Obra obra : this.obras){			
			if(obra.equals(nomeDaObra)){
				return obra;
			}
		}
		return null;
	}
	/**
	 * esse método busca a obra no array de Obras
	 * @param Obra
	 * @return Obra
	 */
	public Obra buscarObra(Obra o){
		for(Obra obra : obras){			
			if(obra.equals(o))
				return obra;
		}
		return null;
	}
	/**
	 * esse método busca uma atividade pelo nome no arrya de atividades
	 * @param nomeDaAtividae
	 * @return Atividade
	 */
	public Atividade buscarAtividade(String nomeDaAtividade){
		for(Atividade atividade : this.atividades){
			if(atividade.equals(nomeDaAtividade)){
				return atividade;
			}
		}
		return null;
	}
	/**
	 * esse método busca uma atividade especifica pelo nome da obra em que ela está e o nome da atividade
	 * @param nomeDaObra
	 * @param nomeDaAtividade
	 * @return Atividade
	 */
	public Atividade buscarAtividade(String nomeDaObra, String nomeDaAtividade){
		for(Atividade atividade : buscarObra(nomeDaObra).getAtividades()){			
			if(atividade.equals(nomeDaAtividade)){
				return atividade;
			}
		}
		return null;
	}
	/**
	 * esse método buscar um funcionário em uma atividade
	 * @param nomeDaObra
	 * @param nomeDaAtividade
	 * @param nomeDoFuncionario
	 * @return Funcionario
	 */
	public Funcionario buscarFuncionario(String nomeDaObra, String nomeDaAtividade, String nomeDoFuncionario){
		for(Funcionario funcionario : buscarAtividade(nomeDaObra, nomeDaAtividade).getFuncionarios()){
			if(funcionario.equals(nomeDoFuncionario)){
				return funcionario;
			}
		}
		return null;
	}
	/**
	 * esse método busca um funcionário no array de funcionários pelo nome
	 * @param nomeDoFuncionario
	 * @return Funcionario
	 */
	public Funcionario buscarFuncionario(String nomeDoFuncionario){
		for(Funcionario funcionario : funcionarios){
			if(funcionario.equals(nomeDoFuncionario)){
				return funcionario;
			}
		}
		return null;
	}
	/**
	 * esse método busca um funcionário no array de funcionarios pelo objeto
	 * @param Funcionario
	 * 
	 * @return Funcionario
	 */
	public Funcionario buscarFuncionario(Funcionario f){
		for(Funcionario funcionario : this.funcionarios){			
			if(funcionario.equals(f)){
				return funcionario;
			}
		}
		return null;
	}
	
//	public Semana buscarSemana(int semanaDoAno, int ano){
//		for(Semana semana : this.semanas) {
//			if(semana.equals(semanaDoAno, ano)) {
//				return semana;
//			}
//		}
//		return null;
//	}
//	public void addSemana(Semana s){
//		this.semanas.add(s);
//	}
	
	
	public ArrayList<Obra> getObras() {
		return obras;
	}
	public void setObras(ArrayList<Obra> obras) {
		this.obras = obras;
	}
	public void addObra(Obra obra){
		this.obras.add(obra);
	}	
	public void deletarObra(int indice){
		this.obras.remove(indice);
	}
	public ArrayList<Atividade> getAtividades(){
		return atividades;
	}
	public void setAtividades(ArrayList<Atividade> atividades){
		this.atividades = atividades;
	}
	public void addAtividade(Atividade atividade){
		this.atividades.add(atividade);
	}
	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}	
	public void addFuncionario(Funcionario funcionario){
		this.funcionarios.add(funcionario);
	}
//	public ArrayList<Semana> getSemanas() {
//		return semanas;
//	}
//	public void setSemanas(ArrayList<Semana> semanas) {
//		this.semanas = semanas;
//	}	
}