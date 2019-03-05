package classes;

import jframe.excecoes.validacao.AtividadeFinalizadaException;
import jframe.excecoes.validacao.AtividadeJaCadastradaException;
import jframe.excecoes.validacao.AtividadePrazoInvalidoException;
import jframe.excecoes.validacao.DescricaoInvalidaException;
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
import jframe.excecoes.validacao.FuncionarioJaDemitidoException;
import jframe.excecoes.validacao.FuncionarioSalarioInvalidoException;
import jframe.excecoes.validacao.FuncionarioSexoInvalidoException;
import jframe.excecoes.validacao.NomeInvalidoException;
import jframe.excecoes.validacao.ObraJaCadastradaException;
import jframe.excecoes.validacao.ObraOrcamentoInicialInvalidoException;
import jframe.excecoes.validacao.ObraTipoInvalidoException;
import jframe.excecoes.validacao.OrcamentoInvalidoException;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;
//essa classe valida todas as entradas
public class Validacao {
	
	private Gerente gerente = Gerente.obterInstancia();
	
	public void validarFuncionario(String nome, String idade, String sexo,String cpf, String email, String telefone, String salario, String funcao, String bairro, String CEP, String cidade, String numero, String rua) throws NomeInvalidoException, FuncionarioIdadeInvalidaException, FuncionarioCPFInvalidoException, FuncionarioEmailInvalidoException, FuncionarioSalarioInvalidoException, FuncionarioFuncaoInvalidaException, EnderecoBairroInvalidoException, EnderecoCEPInvalidoException, EnderecoCidadeInvalidaException, EnderecoNumeroInvalidoException, EnderecoRuaInvalidaException, FuncionarioSexoInvalidoException{
		if(!(nome.matches("[\\s\\p{L}]+")) || nome.equals("") || nome == null)
			throw new NomeInvalidoException();
		else if(!(idade.matches("[0-9]{2}|[0-9]{1}")) || idade.equals("  ") || Integer.parseInt(idade) < 18)
			throw new FuncionarioIdadeInvalidaException();
		else if(!(cpf.matches("[0-9]{3}+\\.[0-9]{3}+\\.[0-9]{3}+\\-([0-9]{2})")) || cpf.equals("   .   .   -  "))
			throw new FuncionarioCPFInvalidoException();
		else if(!(email.equals("")) && email != null){
			validarEmail(email);
		}
		else if(!(telefone.equals("")) && telefone != null){
			if(!telefone.matches("\\([1-9]{2}\\)+[0-9]{5}+\\-[0-9]{4}"))
				throw new FuncionarioEmailInvalidoException();
		}
		else if(!salario.matches("^[0-9]*[.]{0,1}[0-9]*$") || salario.equals("") || salario == null)
			throw new FuncionarioSalarioInvalidoException();
		else if(!funcao.matches("[\\s\\p{L}]+") || funcao.equals("") || funcao == null)
			throw new FuncionarioFuncaoInvalidaException();
		else if(sexo == null)
			throw new FuncionarioSexoInvalidoException();
		validarEndereco(rua, numero, CEP, cidade, bairro);
	}
	
	public String validarDinheiro(String dinheiro){
		String dinheiroFinal = "";
		for(int i = 0;i<dinheiro.length();i++){
			if(dinheiro.charAt(i) == ',')
				dinheiroFinal+=".";
			else
				dinheiroFinal+=dinheiro.charAt(i);
		}
		return dinheiroFinal;
	}
	public void validarNome(String nome) throws NomeInvalidoException{
		if(nome.equals("") || nome == null)
			throw new NomeInvalidoException();
	}
	public void validarObra(String nome, String orcamentoInicial, String tipo, String rua, String numero, String CEP, String cidade, String bairro) throws NomeInvalidoException, ObraOrcamentoInicialInvalidoException, ObraTipoInvalidoException, EnderecoBairroInvalidoException, EnderecoCEPInvalidoException, EnderecoCidadeInvalidaException, EnderecoNumeroInvalidoException, EnderecoRuaInvalidaException{
		validarNome(nome);
		if(!orcamentoInicial.matches("^[0-9]*[.]{0,1}[0-9]*$") || orcamentoInicial.equals("") || orcamentoInicial == null)
			throw new ObraOrcamentoInicialInvalidoException();
		else if(tipo == null)
			throw new ObraTipoInvalidoException();
		validarEndereco(rua, numero, CEP, cidade, bairro);
	}
	
	public void validarObraDuplicada(Obra obra) throws ObraJaCadastradaException{
		if(gerente.buscarObra(obra) != null){
			throw new ObraJaCadastradaException();
		}
	}
	
	public void validarFuncionarioDuplicado(Funcionario funcionario) throws FuncionarioJaCadastradoException{
		if(gerente.buscarFuncionario(funcionario) != null){
			throw new FuncionarioJaCadastradoException();
		}
	}
	
	public void validarFuncionarioDuplicado(Funcionario fun, String nome, String cpf) throws FuncionarioJaCadastradoException{
		int cont = 0;
		int contV = 0;
		if(fun.getNome().equals(nome))
			cont++;
		if(fun.getCPF().equals(cpf))
			cont++;
		if(cont != 2){
			for(Funcionario f: gerente.getFuncionarios()){
				if(f.getCPF().equals(cpf) || f.getNome().equals(nome)){
					contV++;
				}
			}
		}
		if(contV != 0){
			throw new FuncionarioJaCadastradoException();
		}
	}
	
	public void validarAtividadeDuplicada(Atividade atividade, String nomeDaObra) throws AtividadeJaCadastradaException{
		if(gerente.buscarAtividade(nomeDaObra, atividade.getNome()) != null){
			throw new AtividadeJaCadastradaException();
		}
	}
	
	public void validarEndereco(String rua, String numero, String CEP, String cidade, String bairro) throws EnderecoBairroInvalidoException, EnderecoCEPInvalidoException, EnderecoCidadeInvalidaException, EnderecoNumeroInvalidoException, EnderecoRuaInvalidaException{
		if(!bairro.matches("[\\s\\p{L}]+") || bairro.equals("") || bairro == null)
			throw new EnderecoBairroInvalidoException();
		else if(!CEP.matches("[0-9]{5}+\\-[0-9]{3}") || CEP.equals("      -   "))
			throw new EnderecoCEPInvalidoException();
		else if(!cidade.matches("[\\s\\p{L}]+") || cidade.equals("") || cidade == null)
			throw new EnderecoCidadeInvalidaException();
		else if(!numero.matches("^[0-9]*$") || numero.equals("") || numero == null)
			throw new EnderecoNumeroInvalidoException();
		else if(!rua.matches("[\\s\\p{L}]+") || rua.equals("") || rua == null)
			throw new EnderecoRuaInvalidaException();
	}
	
	public void validarTabela(int linha) throws TabelaNaoSelecionadaException{
		if(linha == -1)
			throw new TabelaNaoSelecionadaException();
	}
	
	public void validarAtividade(String nome, String prazo, int linha, String descricao) throws NomeInvalidoException, AtividadePrazoInvalidoException, TabelaNaoSelecionadaException, DescricaoInvalidaException{
		validarNome(nome);
		if(!prazo.matches("^[0-9]*$") || prazo.equals("") || prazo == null || Integer.parseInt(prazo) <= 0)
			throw new AtividadePrazoInvalidoException();
		validarDescricao(descricao);
		validarTabela(linha);
	}
	
	public void validarDescricao(String descricao) throws DescricaoInvalidaException{
		if(descricao == null || descricao.equals(""))
			throw new DescricaoInvalidaException();
	}
	public void validarComentario(String nome, String descricao, Atividade atividade) throws NomeInvalidoException, DescricaoInvalidaException{
		validarNome(nome);
		if(nome.equals("") && !descricao.equals("")){
			throw new NomeInvalidoException();
		}
		validarComentarioDuplicado(atividade, nome);
	}
	public void validarComentarioDuplicado(Atividade atividade, String nome) throws NomeInvalidoException{
		for(Comentario c: atividade.getComentarios()){
			if(c.getNome().equals(nome)){
				throw new NomeInvalidoException();
			}
		}
	}
	public void validarOrcamento(String nome, String orcamento, String descricao) throws NomeInvalidoException, OrcamentoInvalidoException, DescricaoInvalidaException{
		validarNome(nome);
		if(!orcamento.matches("^[0-9]*[.]{0,1}[0-9]*$") || orcamento.equals("") || orcamento == null)
			throw new OrcamentoInvalidoException();
		else if(descricao.equals("") || descricao == null)
			throw new DescricaoInvalidaException();
	}
	public void validarAtividadeFinalizada(String nomeDaAtividade) throws AtividadeFinalizadaException{
		String nome = "(Finalizada";
		String nomeV = "";
		if(nomeDaAtividade.length() >= nome.length()){
			for(int i = 0; i<nome.length();i++){
				nomeV+=nomeDaAtividade.charAt(i);
			}
		}
		if(nome.equals(nomeV)){
			throw new AtividadeFinalizadaException();
		}
	}
	public void validarEmail(String email) throws FuncionarioEmailInvalidoException{
		if(!(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")))
			throw new FuncionarioEmailInvalidoException();
	}
	public void validarFuncionarioDemitido(Funcionario funcionario) throws FuncionarioJaDemitidoException{
		if(funcionario.isDemitido()){
			throw new FuncionarioJaDemitidoException();
		}
	}
}
