package jframe.componentes;

import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classes.Acrescimo;
import classes.Atividade;
import classes.Comentario;
import classes.Fonte;
import classes.Funcionario;
import classes.Gasto;
import classes.Gerente;
import classes.Obra;

public class Tabela extends JTable {
	
	private Gerente gerente = Gerente.obterInstancia();
	
	public Tabela(){}
	
	//cria uma tabela de acordo com as coordenadas passada na assinatura e dependendo do que o usuário passou no parametro String tipoDaTabela
	//ele invoca os outros dois metodos para gerar os DefaultTableModel
	public Tabela(JFrame janela, MouseListener ouvinte, int x, int y, int w, int h, String tipoDaTabela, Obra obra){	
		
		setFont(Fonte.textoNormal());
		
		JScrollPane painel = new JScrollPane(this);
		
		painel.setBounds(x, y, w, h);
		
		addMouseListener(ouvinte);
		
		if(obra == null){
			this.setModel(atualizarTabela(tipoDaTabela));
		}
		else{
			this.setModel(atualizarTabela(tipoDaTabela, obra, null, null, null, null));
		}
		janela.add(painel);
	}
	//retorna um DefaultTableModel de acordo com a String passada na assinatura
	public DefaultTableModel atualizarTabela(String tipoDaTabela){
		DefaultTableModel modelo = new DefaultTableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		switch (tipoDaTabela) {			
		case "obra":{			
			modelo.addColumn("OBRAS");						
			for (Obra a: gerente.getObras()){
				Object[] linha = new Object[1];
				linha[0] = a.getNome();
				modelo.addRow(linha);
			}
			return modelo;
		}
		case "atividade":{							
			modelo.addColumn("ATIVIDADES");	
			return modelo;	
		}
		case "funcionario":{					
			modelo.addColumn("FUNCIONÁRIOS");			
			return modelo;
		}			
		case "listaDeFuncionario":{			
			modelo.addColumn("Nome");
			modelo.addColumn("Data de Nascimento");
			modelo.addColumn("Idade");
			modelo.addColumn("CPF");
			modelo.addColumn("Sexo");
			modelo.addColumn("Função");
			modelo.addColumn("Salário");			
			for (Funcionario a: gerente.getFuncionarios()){				
				Object[] linha = new Object[7];				
				linha[0] = a.getNome();
				linha[1] = sdf.format(a.getDataDeNascimento());
				linha[2] = a.getIdade();
				linha[3] = a.getCPF();
				linha[4] = a.getSexo();
				linha[5] = a.getFuncao();
				linha[6] = a.getSalario();
				modelo.addRow(linha);
			}			
			return modelo;
		}		
		case "listaDeObra":{			
			modelo.addColumn("Nome");
			modelo.addColumn("CEP");
			modelo.addColumn("Rua");
			modelo.addColumn("Número");
			modelo.addColumn("Cidade");
			modelo.addColumn("Bairro");			
			for (Obra a: gerente.getObras()){				
				Object[] linha = new Object[6];
				linha[0] = a.getNome();
				linha[1] = a.getEndereco().getCEP();
				linha[2] = a.getEndereco().getRua();
				linha[3] = a.getEndereco().getNumero();
				linha[4] = a.getEndereco().getCidade();
				linha[5] = a.getEndereco().getBairro();				
				modelo.addRow(linha);
			}
			return modelo;
		}
		case "listaDeAtividade":{
			modelo.addColumn("Nome");
			modelo.addColumn("Data de Início");
			modelo.addColumn("Prazo (dias)");
			modelo.addColumn("Satus");
			modelo.addColumn("Data de Conclusão");		
			for (Atividade a: gerente.getAtividades()){	
				a.atualizarStatus();
				Object[] linha = new Object[5];
				linha[0] = a.getNome();
				linha[1] = sdf.format(a.getDataDeCriacao());
				linha[2] = a.getPrazo();
				linha[3] = a.getStatus();
				linha[4] = sdf.format(a.getDataDeConclusao());			
				modelo.addRow(linha);
			}
			return modelo;
		}
		}
		return modelo;
	}
	//retorna um DefaultTableModel de acordo com a String passada na assinatura
	public DefaultTableModel atualizarTabela(String tipoDaTabela, Obra obra, Atividade atividade, Funcionario funcionario, ArrayList<Funcionario> funcionarios, ArrayList<Atividade> atividades){
		DefaultTableModel modelo = new DefaultTableModel();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		switch (tipoDaTabela) {	
		case "listagemDeFuncionario":{
			
			modelo.addColumn("Nome");
			modelo.addColumn("Data de Nascimento");
			modelo.addColumn("Idade");
			modelo.addColumn("CPF");
			modelo.addColumn("Sexo");
			modelo.addColumn("Função");
			modelo.addColumn("Salário");
			
			for (Funcionario a: funcionarios){
				
				Object[] linha = new Object[7];
				
				linha[0] = a.getNome();
				linha[1] = sdf.format(a.getDataDeNascimento());
				linha[2] = a.getIdade();
				linha[3] = a.getCPF();
				linha[4] = a.getSexo();
				linha[5] = a.getFuncao();
				linha[6] = a.getSalario();
							
				modelo.addRow(linha);
			}
			return modelo;
		}
		case "listagemDeAtividade":{
			modelo.addColumn("Nome");
			modelo.addColumn("Data de Início");
			modelo.addColumn("Prazo (dias)");
			modelo.addColumn("Satus");
			modelo.addColumn("Data de Conclusão");		
			for (Atividade a: atividades){	
				a.atualizarStatus();
				Object[] linha = new Object[5];
				linha[0] = a.getNome();
				linha[1] = sdf.format(a.getDataDeCriacao());
				linha[2] = a.getPrazo();
				linha[3] = a.getStatus();
				linha[4] = sdf.format(a.getDataDeConclusao());			
				modelo.addRow(linha);
			}
			return modelo;
		}
		case "atividade":{
			modelo.addColumn("ATIVIDADES");
			for(Atividade a : obra.getAtividades()){
				Object[] linha = new Object[1];
				linha[0] = a.getNome();
				modelo.addRow(linha);			
			}
			return modelo;
		}
		case "funcionario":{
			modelo.addColumn("FUNCIONÁRIOS");
			for(Funcionario f : atividade.getFuncionarios()){
				Object[] linha = new Object[1];
				linha[0] = f.getNome();
				modelo.addRow(linha);
			}
			return modelo;
		}
		case "acréscimo":{
			modelo.addColumn("Nome");
			modelo.addColumn("Valor");
			modelo.addColumn("Data");
			for (Acrescimo a: obra.getAcrescimos()){
				Object[] linha = new Object[3];
				linha[0] = a.getNome();
				linha[1] = "R$ " + a.getValor();
				linha[2] = sdf.format(a.getData());
				modelo.addRow(linha);
			}
			return modelo;
		}
		case "gasto":{
			modelo.addColumn("Nome");
			modelo.addColumn("Valor");
			modelo.addColumn("Data");
			for (Gasto g: obra.getGastos()){
				Object[] linha = new Object[3];
				linha[0] = g.getNome();
				linha[1] = "R$ " + g.getValor();	
				linha[2] = sdf.format(g.getData());
				modelo.addRow(linha);
			}
			return modelo;
		}
		case "comentario":{	
			modelo.addColumn("Nome");
			modelo.addColumn("Data");
			for (Comentario c: atividade.getComentarios()){
				Object[] linha = new Object[2];
				linha[0] = c.getNome();
				linha[1] = sdf.format(c.getData());
				modelo.addRow(linha);
			}
			return modelo;
		}
		}
		return modelo;		
	}	
	public boolean isCellEditable(int row, int column){  
        return false;
    }
}