package jframe.ouvintes.janelaCadatroDeFuncionarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import classes.Endereco;
import classes.Funcionario;
import classes.Gerente;
import classes.PersistenciaGerente;
import classes.Validacao;
import jframe.JanelaCadastroDeFuncionarios;
import jframe.JanelaPrincipal;
import jframe.excecoes.validacao.AtividadeFinalizadaException;
import jframe.excecoes.validacao.EnderecoBairroInvalidoException;
import jframe.excecoes.validacao.EnderecoCEPInvalidoException;
import jframe.excecoes.validacao.EnderecoCidadeInvalidaException;
import jframe.excecoes.validacao.EnderecoNumeroInvalidoException;
import jframe.excecoes.validacao.EnderecoRuaInvalidaException;
import jframe.excecoes.validacao.FuncionarioCPFInvalidoException;
import jframe.excecoes.validacao.FuncionarioEmailInvalidoException;
import jframe.excecoes.validacao.FuncionarioFuncaoInvalidaException;
import jframe.excecoes.validacao.FuncionarioIdadeInvalidaException;
import jframe.excecoes.validacao.FuncionarioJaCadastradoException;
import jframe.excecoes.validacao.FuncionarioSalarioInvalidoException;
import jframe.excecoes.validacao.FuncionarioSexoInvalidoException;
import jframe.excecoes.validacao.NomeInvalidoException;

public class OuvinteDoBotaoCadastrarFuncionario implements ActionListener{
	
	private JanelaCadastroDeFuncionarios janela;
	
	public OuvinteDoBotaoCadastrarFuncionario(JanelaCadastroDeFuncionarios janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		String nome = janela.getTfNome().getText();
		Date dataDeNascimento = janela.getCalendario().getDate();
		String idade = janela.getTfIdade().getText();
		String CPF = janela.getTfCPF().getText();
		
		Calendar calendario = Calendar.getInstance();
		Date dataDeCadastro = calendario.getTime();
		
		String email = janela.getTfEmail().getText();
		if(email.isEmpty()){
			email = "";
		}
		String telefone = janela.getTfTelefone().getText();
		if(telefone.equals("(  )     -    ")){
			telefone = "";
		}
		
		String salario = validacao.validarDinheiro(janela.getTfSalario().getText());
		String sexo = null;
		if(janela.getRbFeminino().isSelected()){
			sexo = "F";
		}
		else if(janela.getRbMasculino().isSelected()){
			sexo = "M";
		}		

		String funcao = janela.getProfissoes()[janela.getTfFuncao().getSelectedIndex()];
		
		String rua = janela.getTfRua().getText();
		String numero = janela.getTfNumero().getText();
		String CEP = janela.getTfCEP().getText();
		String cidade = janela.getTfCidade().getText();
		String bairro = janela.getTfBairro().getText();
		
		int linhaAtividade = janela.getAtividadeTBL().getSelectedRow();
		
		try {
			validacao.validarFuncionario(nome, idade, sexo ,CPF, email, telefone, salario, funcao, bairro, CEP, cidade, numero, rua);
			
			Gerente gerente = Gerente.obterInstancia();
			
			Endereco endereco = new Endereco(rua,Integer.parseInt(numero),CEP,cidade,bairro);
			
			
			///// aquiiii caralhoooo
			Funcionario funcionario = new Funcionario(nome, dataDeNascimento, Integer.parseInt(idade), CPF, dataDeCadastro, email, telefone, Double.parseDouble(salario), sexo, funcao, endereco);
			
			validacao.validarFuncionarioDuplicado(funcionario);
			
			gerente.addFuncionario(funcionario);
			
			if(linhaAtividade != -1){
				String nomeDaObra = janela.getObraTBL().getValueAt(janela.getObraTBL().getSelectedRow(), 0).toString();
				String nomeDaAtividade = janela.getAtividadeTBL().getValueAt(janela.getAtividadeTBL().getSelectedRow(), 0).toString();
				
				validacao.validarAtividadeFinalizada(nomeDaAtividade);
				
				gerente.buscarAtividade(nomeDaObra, nomeDaAtividade).getFuncionarios().add(funcionario);
			}
			
			
			PersistenciaGerente persistencia = new PersistenciaGerente();
			persistencia.salvarGerente(gerente);
			
			JOptionPane.showMessageDialog(this.janela, "Funcionário cadastrado com sucesso!");	
			janela.dispose();
			new JanelaPrincipal();
		
		} catch (NomeInvalidoException | FuncionarioIdadeInvalidaException | FuncionarioCPFInvalidoException |FuncionarioEmailInvalidoException | FuncionarioSalarioInvalidoException | FuncionarioFuncaoInvalidaException | EnderecoBairroInvalidoException | EnderecoCEPInvalidoException | EnderecoCidadeInvalidaException | EnderecoNumeroInvalidoException | EnderecoRuaInvalidaException | FuncionarioSexoInvalidoException |  FuncionarioJaCadastradoException | AtividadeFinalizadaException e1) {
		
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		
		}
	}
}
