package classes;

import java.util.Date;

public class Funcionario {
	
	private String nome;
	private Date dataDeNascimento;
	private int idade;
	private String CPF;
	private Date dataDeCadastro;
	private String email;
	private String telefone;
	private double salario;
	private String sexo;
	private String funcao;
	private Endereco endereco;
	private boolean demitido;
	
	public Funcionario(String nome, Date dataDeNascimento, int idade, String CPF, Date dataDeCadastro, String email, String telefone, double salario, String sexo, String funcao, Endereco endereco) {
		
		
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.idade = idade;
		this.CPF = CPF;
		this.dataDeCadastro = dataDeCadastro;
		this.email = email;
		this.telefone = telefone;
		this.salario = salario;
		this.sexo = sexo;
		this.funcao = funcao;
		this.endereco = endereco;
		this.demitido = false;
	}
	public boolean equals(Funcionario f){
		if(this.nome.equals(f.nome) || this.CPF.equals(f.CPF)){
			return true;
		}		
		return false;
	}
	public boolean equals(String nome){
		if(this.nome.equals(nome)){
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
	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}	
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}
	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public boolean isDemitido() {
		return demitido;
	}
	public void setDemitido(boolean demitido) {
		this.demitido = demitido;
	}
}
