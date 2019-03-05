package jframe;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import classes.Fonte;
import classes.Funcionario;
import classes.Gerente;
import jframe.componentes.Botao;
import jframe.componentes.CampoDeTexto;
import jframe.componentes.Etiqueta;
import jframe.componentes.Tabela;
import jframe.ouvintes.OuvinteDasJanelasDeNivelUm;
import jframe.ouvintes.OuvinteDoBotaoCancelar;
import jframe.ouvintes.janelaDeListagemDeFuncionarios.OuvinteDoBotaoEditarFuncionario;
import jframe.ouvintes.janelaDeListagemDeFuncionarios.OuvinteDoBotaoExcluirDaJanelaDeListagemDeFuncionario;
import jframe.ouvintes.janelaDeListagemDeFuncionarios.OuvinteDoComboBoxDaJanelaDeListagemDeFuncionario;
import jframe.ouvintes.janelaDeListagemDeFuncionarios.OuvinteDoTFPesquisarPorFuncaoDaListagemDeFuncionarios;
import jframe.ouvintes.janelaDeListagemDeFuncionarios.OuvinteDoTFPesquisarPorNomeDaListagemDeFuncionarios;

public class JanelaDeListagemDeFuncionarios extends JanelaPadrao{
	
	private Tabela tabelaDeListagem;
	private CampoDeTexto tfPesquisarPorNome;
	private CampoDeTexto tfPesquisarPorFuncao;
	private ArrayList<Funcionario> array;
	private JComboBox<String> filtroDeEscolhaCB;
	
	public JanelaDeListagemDeFuncionarios(){	
		OuvinteDasJanelasDeNivelUm ouvinteDaJanela = new OuvinteDasJanelasDeNivelUm(this);
		addWindowListener(ouvinteDaJanela);
		Gerente gerente = Gerente.obterInstancia();		
		this.array = gerente.getFuncionarios();
		
		setTitle("Listagem de Funcionários");		
		
		adicionarTabela();
		adicionarEtiquetas();
		adicionarBotoes();
		adicionarCampoDeTextos();
		adicionarCaixaCombo();
		
		setVisible(true);
		requestFocusInWindow();
	}	
	private void adicionarCampoDeTextos() {
		
		OuvinteDoTFPesquisarPorNomeDaListagemDeFuncionarios ouvintePesquisarPorNome = new OuvinteDoTFPesquisarPorNomeDaListagemDeFuncionarios(this);
		OuvinteDoTFPesquisarPorFuncaoDaListagemDeFuncionarios ouvintePesquisarProFuncao = new OuvinteDoTFPesquisarPorFuncaoDaListagemDeFuncionarios(this);
		
		tfPesquisarPorNome = new CampoDeTexto(this, 15, 21, 214, 25);
		tfPesquisarPorNome.addKeyListener(ouvintePesquisarPorNome);
		
		tfPesquisarPorFuncao = new CampoDeTexto(this, 330, 21, 214, 25);
		tfPesquisarPorFuncao.addKeyListener(ouvintePesquisarProFuncao);		
	}
	private void adicionarBotoes() {	
		ImageIcon botaoExcluirICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoExcluir.png"));
		ImageIcon botaoEditarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoEditar.png"));
		ImageIcon botaoCancelarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCancelar.png"));
		
		OuvinteDoBotaoExcluirDaJanelaDeListagemDeFuncionario ouvinteExcluir = new OuvinteDoBotaoExcluirDaJanelaDeListagemDeFuncionario(this);
		OuvinteDoBotaoEditarFuncionario ouvinteEditar = new OuvinteDoBotaoEditarFuncionario(this);
		OuvinteDoBotaoCancelar ouvinteCancelar = new OuvinteDoBotaoCancelar(this);
		
		Botao btExcluir = new Botao(this, Fonte.tituloNegrito(), ouvinteExcluir ,"Demitir", 50, 500, 200, 50, botaoExcluirICO);	
		Botao btEditar = new Botao(this, Fonte.tituloNegrito(), ouvinteEditar, "Editar", 340, 500, 200, 50, botaoEditarICO);
		Botao btCancelar = new Botao(this, Fonte.tituloNegrito(), ouvinteCancelar, "Cancelar", 630, 500, 200, 50, botaoCancelarICO);		
	}
	private void adicionarEtiquetas() {
		
		Etiqueta lbPesquisarPorNome = new Etiqueta(this, "Pesquisar por nome", Fonte.textoNormal(), "Pesquisar funcionário por nome", 15, 2, 150, 20);
		Etiqueta lbPesquisarPorFuncao = new Etiqueta(this, "Pesquisar por função", Fonte.textoNormal(), "Pesquisar funcionário por função", 330, 2, 150, 20);
		Etiqueta lbFiltrarPorStatus = new Etiqueta(this, "Filtrar por status", Fonte.textoNormal(), null, 650, 2, 150, 20);		
	}
	public void adicionarTabela(){
		tabelaDeListagem = new Tabela(this, null, 15, 73, 850, 425, "listaDeFuncionario", null);
	}
	public void adicionarCaixaCombo(){
		
		OuvinteDoComboBoxDaJanelaDeListagemDeFuncionario ouvinteDoComboBox = new OuvinteDoComboBoxDaJanelaDeListagemDeFuncionario(this);
		
		String[] opcoes = {"Todos", "Empregados", "Demitidos"};
		
		filtroDeEscolhaCB = new JComboBox<String>(opcoes);
		filtroDeEscolhaCB.setFont(Fonte.textoNormal());
		filtroDeEscolhaCB.setBounds(650, 21, 150, 23);
		filtroDeEscolhaCB.addActionListener(ouvinteDoComboBox);
		this.add(filtroDeEscolhaCB);
	}
	
	public Tabela getTabelaDeListagem() {
		return tabelaDeListagem;
	}
	public void setTabelaDeListagem(Tabela tabelaDeListagem) {
		this.tabelaDeListagem = tabelaDeListagem;
	}
	public ArrayList<Funcionario> getArray() {
		return array;
	}
	public void setArray(ArrayList<Funcionario> array) {
		this.array = array;
	}
	public CampoDeTexto getTfPesquisarPorNome() {
		return tfPesquisarPorNome;
	}
	public void setTfPesquisarPorNome(CampoDeTexto tfPesquisarPorNome) {
		this.tfPesquisarPorNome = tfPesquisarPorNome;
	}
	public CampoDeTexto getTfPesquisarPorFuncao() {
		return tfPesquisarPorFuncao;
	}
	public void setTfPesquisarPorFuncao(CampoDeTexto tfPesquisarPorFuncao) {
		this.tfPesquisarPorFuncao = tfPesquisarPorFuncao;
	}
	public JComboBox<String> getFiltroDeEscolhaCB() {
		return filtroDeEscolhaCB;
	}
	public void setFiltroDeEscolhaCB(JComboBox<String> filtroDeEscolhaCB) {
		this.filtroDeEscolhaCB = filtroDeEscolhaCB;
	}
	
}
