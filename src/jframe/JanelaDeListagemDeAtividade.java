package jframe;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import classes.Atividade;
import classes.Fonte;
import classes.Gerente;
import classes.Obra;
import jframe.componentes.Botao;
import jframe.componentes.ComboCaixa;
import jframe.componentes.Etiqueta;
import jframe.componentes.Tabela;
import jframe.ouvintes.OuvinteDasJanelasDeNivelUm;
import jframe.ouvintes.OuvinteDoBotaoCancelar;
import jframe.ouvintes.janelaDeListagemDeAtividades.OuvinteDoBotaoAtualizarAtividadeListagemAtividade;
import jframe.ouvintes.janelaDeListagemDeAtividades.OuvinteDoComboBoxFiltrarAtividadePorObra;
import jframe.ouvintes.janelaDeListagemDeAtividades.OuvinteDoComboBoxFiltrarAtividadePorSemana;

public class JanelaDeListagemDeAtividade extends JanelaPadrao {
	
	private Tabela tabelaDeListagem;
	private ComboCaixa cbObra;
	private ComboCaixa cbSemana;
	

	public JanelaDeListagemDeAtividade() {
		OuvinteDasJanelasDeNivelUm ouvinteDaJanela = new OuvinteDasJanelasDeNivelUm(this);
		addWindowListener(ouvinteDaJanela);
		Gerente gerente = Gerente.obterInstancia();		
		
		setTitle("Listagem de Atividade");
		
		adicionarTabela();
		adicionarEtiquetas();
		adicionarBotoes();
		adicionarComboBox();
		
		setVisible(true);
		requestFocusInWindow();
	}	
	public void adicionarTabela(){
		tabelaDeListagem = new Tabela(this, null, 15, 73, 850, 425, "listaDeAtividade", null);		
	}
	public void adicionarEtiquetas(){
		Etiqueta lbDados = new Etiqueta(this, "Lista De Atividades", Fonte.tituloNegrito(), null, 340, 20, 250, 25);
		Etiqueta lbFiltrarPorObra = new Etiqueta(this, "Nome da Obra", Fonte.textoNormal(), null, 150, 10, 150, 25);
		Etiqueta lbFiltrarPorSemana = new Etiqueta(this, "Semana", Fonte.textoNormal(), null, 40, 10, 100, 25);
	}
	public void adicionarBotoes(){
		OuvinteDoBotaoAtualizarAtividadeListagemAtividade ouvinteAtualizar = new OuvinteDoBotaoAtualizarAtividadeListagemAtividade(this);
		OuvinteDoBotaoCancelar ouvinteCancelar = new OuvinteDoBotaoCancelar(this);
		
		ImageIcon botaoDetalharICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoDetalhar.png"));
		ImageIcon botaoCancelarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCancelar.png"));
		
		Botao btAtualizar = new Botao(this, Fonte.tituloNegrito(), ouvinteAtualizar, "Atualizar Atividade", 50, 500, 275, 50, botaoDetalharICO);		
		Botao btCancelar = new Botao(this, Fonte.tituloNegrito(), ouvinteCancelar, "Cancelar", 630, 500, 200, 50, botaoCancelarICO);	
		
	}
	public void adicionarComboBox(){
		
		ArrayList<Obra> array = Gerente.obterInstancia().getObras();
		int tam = array.size();
		String [] obrasNome = new String[tam+1];
		obrasNome[0] = "(Sem filtro)";
		for(int i = 0; i<tam;i++){
			obrasNome[i+1] = array.get(i).getNome();
		}
		
		ArrayList<Atividade> arrayDeAtividades = Gerente.obterInstancia().getAtividades();		
		int tamA = 0;		
		for (Atividade atividade : arrayDeAtividades) {
			if(atividade.getSemana() > tamA){
//				System.out.println(tamA);
				tamA = atividade.getSemana();
			}
		}		
		System.out.println(tamA);
		String[] semanas = new String[tamA+1];
		semanas[0] = "(Sem filtro)";		
		for(int i = 1; i <= tamA; i++) {
			semanas[i] = Integer.toString(i);
		}		
		
		OuvinteDoComboBoxFiltrarAtividadePorObra ouvinte = new OuvinteDoComboBoxFiltrarAtividadePorObra(this);		
		cbObra = new ComboCaixa(this, Fonte.textoNormal(), 150, 30, 150, 25, obrasNome);
		cbObra.addItemListener(ouvinte);
		
		
		OuvinteDoComboBoxFiltrarAtividadePorSemana ouvinteSemana = new OuvinteDoComboBoxFiltrarAtividadePorSemana(this);
		cbSemana = new ComboCaixa(this, Fonte.textoNormal(), 40, 30, 100, 25, semanas);
		cbSemana.addItemListener(ouvinteSemana);
		
	}

	public Tabela getTabelaDeListagem() {
		return tabelaDeListagem;
	}
	public void setTabelaDeListagem(Tabela tabelaDeListagem) {
		this.tabelaDeListagem = tabelaDeListagem;
	}		
	public ComboCaixa getCbObra() {
		return cbObra;
	}
	public void setCbObra(ComboCaixa cbObra) {
		this.cbObra = cbObra;
	}
	public ComboCaixa getCbSemana() {
		return cbSemana;
	}
	public void setCbSemana(ComboCaixa cbSemana) {
		this.cbSemana = cbSemana;
	}	
}
