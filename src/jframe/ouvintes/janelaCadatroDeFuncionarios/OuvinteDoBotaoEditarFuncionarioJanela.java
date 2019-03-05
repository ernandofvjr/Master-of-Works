package jframe.ouvintes.janelaCadatroDeFuncionarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import classes.Atividade;
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

public class OuvinteDoBotaoEditarFuncionarioJanela implements ActionListener{
	
	private JanelaCadastroDeFuncionarios janela;
	
	public OuvinteDoBotaoEditarFuncionarioJanela(JanelaCadastroDeFuncionarios janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		Funcionario funcionario = janela.getFuncionario();
		String nome = janela.getTfNome().getText();
		String idade = janela.getTfIdade().getText();
		String CPF = janela.getTfCPF().getText();
		String email = janela.getTfEmail().getText();
		String telefone = janela.getTfTelefone().getText();
		String salario = validacao.validarDinheiro(janela.getTfSalario().getText());
		String sexo = null;
		if(janela.getRbFeminino().isSelected()){
			sexo = "F";
		}
		else if(janela.getRbMasculino().isSelected()){
			sexo = "M";
		}
		String funcao = janela.getProfissoes()[janela.getTfFuncao().getSelectedIndex()];
		Endereco endereco = funcionario.getEndereco();
		String rua = janela.getTfRua().getText();
		String numero = janela.getTfNumero().getText();
		String CEP = janela.getTfCEP().getText();
		String cidade = janela.getTfCidade().getText();
		String bairro = janela.getTfBairro().getText();
		int linhaAtividade = janela.getAtividadeTBL().getSelectedRow();
		if(email.isEmpty()){
			email = "";
		}
		if(telefone.equals("(  )     -    ")){
			telefone = "";
		}
		PersistenciaGerente persistencia = new PersistenciaGerente();		
		
		try{
			validacao.validarFuncionario(nome, idade, sexo ,CPF, email, telefone, salario, funcao, bairro, CEP, cidade, numero, rua);
			
			validacao.validarFuncionarioDuplicado(funcionario, nome, CPF);
			
			funcionario.setNome(nome);
			funcionario.setIdade(Integer.parseInt(idade));
			funcionario.setCPF(CPF);
			funcionario.setEmail(email);
			funcionario.setTelefone(telefone);
			funcionario.setSalario(Double.parseDouble(salario));
			funcionario.setSexo(sexo);
			funcionario.setFuncao(funcao);
			endereco.setRua(rua);
			endereco.setNumero(Integer.parseInt(numero));
			endereco.setCEP(CEP);
			endereco.setCidade(cidade);
			endereco.setBairro(bairro);
			
			Gerente gerente = Gerente.obterInstancia();
			

			ArrayList<Funcionario> array = null;
			for(Atividade a: gerente.getAtividades()){
				for(Funcionario f: a.getFuncionarios()){
					array = a.getFuncionarios();
					if(janela.getFuncionario().equals(f)){
						break;
					}
				}
			}
			
			if( array != null)
				array.remove(janela.getFuncionario());
			
			if(linhaAtividade != -1){
				String nomeDaObra = janela.getObraTBL().getValueAt(janela.getObraTBL().getSelectedRow(), 0).toString();
				String nomeDaAtividade = janela.getAtividadeTBL().getValueAt(janela.getAtividadeTBL().getSelectedRow(), 0).toString();
				
				validacao.validarAtividadeFinalizada(nomeDaAtividade);
				
				gerente.buscarAtividade(nomeDaObra, nomeDaAtividade).getFuncionarios().add(funcionario);
			}
			
			persistencia.salvarGerente(gerente);
			JOptionPane.showMessageDialog(janela, "Funcionário editado com sucesso!");
			janela.dispose();
			new JanelaPrincipal();
		} catch (NomeInvalidoException | FuncionarioIdadeInvalidaException | FuncionarioCPFInvalidoException |FuncionarioEmailInvalidoException | FuncionarioSalarioInvalidoException | FuncionarioFuncaoInvalidaException | EnderecoBairroInvalidoException | EnderecoCEPInvalidoException | EnderecoCidadeInvalidaException | EnderecoNumeroInvalidoException | EnderecoRuaInvalidaException | FuncionarioSexoInvalidoException |  FuncionarioJaCadastradoException | AtividadeFinalizadaException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}
	}
}