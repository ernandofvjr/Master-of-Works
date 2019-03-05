package jframe;

import javax.swing.ImageIcon;

import classes.Fonte;
import jframe.componentes.Botao;
import jframe.componentes.Etiqueta;
import jframe.componentes.Tabela;
import jframe.ouvintes.OuvinteDasJanelasDeNivelUm;
import jframe.ouvintes.OuvinteDoBotaoCancelar;
import jframe.ouvintes.janelaDeListagemDeObras.OuvinteDoBotaoDetalharObra;
import jframe.ouvintes.janelaDeListagemDeObras.OuvinteDoBotaoExcluirDaJanelaDeListagemDeObras;
import jframe.ouvintes.janelaDeListagemDeObras.OuvinteDoBotaoPDFAtividadeDaJanelaDeListagemDeObra;
import jframe.ouvintes.janelaDeListagemDeObras.OuvinteDoBotaoPDFOrcamentoDaJanelaDeListagemDeObra;

public class JanelaDeListagemDeObras extends JanelaPadrao{
	
	private Tabela tabelaDeListagem;
	
	public JanelaDeListagemDeObras() {	
		OuvinteDasJanelasDeNivelUm ouvinteDaJanela = new OuvinteDasJanelasDeNivelUm(this);
		addWindowListener(ouvinteDaJanela);
		setTitle("Listagem de Obras");
		
		adicionarTabela();
		adicionarEtiquetas();
		adicionarBotoes();
		
		setVisible(true);
		requestFocusInWindow();
	}
	private void adicionarBotoes() {
		ImageIcon botaoExcluirICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoExcluir.png"));
		ImageIcon botaoDetalharICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoDetalhar.png"));
		ImageIcon botaoCancelarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCancelar.png"));
		
		OuvinteDoBotaoExcluirDaJanelaDeListagemDeObras ouvinteExcluir = new OuvinteDoBotaoExcluirDaJanelaDeListagemDeObras(this);
		
		OuvinteDoBotaoCancelar ouvinteCancelar = new OuvinteDoBotaoCancelar(this);
		OuvinteDoBotaoDetalharObra ouvinteDetalhar = new OuvinteDoBotaoDetalharObra(this);
		
		OuvinteDoBotaoPDFOrcamentoDaJanelaDeListagemDeObra ouvinteOrcamento = new OuvinteDoBotaoPDFOrcamentoDaJanelaDeListagemDeObra(this);
		OuvinteDoBotaoPDFAtividadeDaJanelaDeListagemDeObra ouvinteAtividade = new OuvinteDoBotaoPDFAtividadeDaJanelaDeListagemDeObra(this);
		
		Botao btSalvarPDFOrcamento = new Botao(this, Fonte.tituloNegrito(), ouvinteOrcamento, "PDF Orçamento", 10, 500, 210, 50, null);
		Botao btSalvarPDFAtividade = new Botao(this, Fonte.tituloNegrito(), ouvinteAtividade, "PDF Atividade", 215, 500, 190, 50, null);
		
		Botao btExcluir = new Botao(this,Fonte.tituloNegrito(),ouvinteExcluir ,"Excluir", 400, 500, 150, 50, botaoExcluirICO);
		Botao btDetalhar = new Botao(this, Fonte.tituloNegrito(),ouvinteDetalhar,"Detalhar", 545, 500, 160, 50, botaoDetalharICO);				
		Botao btCancelar = new Botao(this,Fonte.tituloNegrito(),ouvinteCancelar ,"Cancelar", 700, 500, 170, 50, botaoCancelarICO);		
	}
	private void adicionarEtiquetas() {
		Etiqueta lbDados = new Etiqueta(this, "Lista de Obras", Fonte.tituloNegrito(), "Dados da Obra", 370, 20, 250, 25);		
	}
	private void adicionarTabela(){		
		tabelaDeListagem = new Tabela(this, null, 15, 73, 850, 425, "listaDeObra", null);
	}	
	public Tabela getTabelaDeListagem() {
		return tabelaDeListagem;
	}
	public void setTabelaDeListagem(Tabela tabelaDeListagem) {
		this.tabelaDeListagem = tabelaDeListagem;
	}	
}