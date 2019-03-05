package jframe;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import classes.Atividade;
import classes.Fonte;
import jframe.componentes.AreaDeTexto;
import jframe.componentes.Botao;
import jframe.componentes.CampoDeTexto;
import jframe.componentes.Etiqueta;
import jframe.componentes.RadioBotao;
import jframe.componentes.Tabela;
import jframe.ouvintes.OuvinteDasJanelasDeNivelUm;
import jframe.ouvintes.OuvinteDoBotaoCancelar;
import jframe.ouvintes.janelaDeAtualizacaoDaAtividade.OuvinteDoBotaoAtualizarDaJanelaDeAtualizacaoDeAtividade;
import jframe.ouvintes.janelaDeAtualizacaoDaAtividade.OuvinteDoBotaoDetalharDaJanelaDeAtualizacaoDeAtividade;
import jframe.ouvintes.janelaDeAtualizacaoDaAtividade.OuvinteDoBotaoExcluirDaJanelaDeAtualizacaoDeAtividade;
import jframe.ouvintes.janelaDeAtualizacaoDaAtividade.OuvinteDoBotaoPDFComentarioDaJanelaDeAtualizacaoDeAtividade;

public class JanelaDeAtualizaçãoDaAtividade extends JanelaPadrao {
	
	private Atividade atividade;
	private Tabela tabelaDeComentarios;
	private RadioBotao ativadaRb;
	private RadioBotao desativadaRB;
	private AreaDeTexto comentarioADT;
	private CampoDeTexto nomeDoComentarioCDT;
	private Botao atualizarBT;
	private Botao excluirComentarioBT;
	
	public JanelaDeAtualizaçãoDaAtividade(Atividade atividade) {
		
		this.atividade = atividade;
		OuvinteDasJanelasDeNivelUm ouvinteDaJanela = new OuvinteDasJanelasDeNivelUm(this);
		addWindowListener(ouvinteDaJanela);
		
		setTitle("Atualização da Atividade");
		adicionarEtiqueta();
		adicionarAreaDeTexto();
		adicionarTabela();
		adicionarBotao();
		adicionarRadioBotao();
		adicionarCampoDeTexto();
		status();
		
		setVisible(true);		
	}
	private void adicionarEtiqueta(){
		Etiqueta tituloETQ = new Etiqueta(this, "Atualizar Atividade", Fonte.tituloNegrito(), null, 350, 10, 300, 25);
		Etiqueta mudarETQ = new Etiqueta(this, "Mudar status da atividade", Fonte.textoNormal(), null, 75, 50, 175, 20);
		Etiqueta listaDeComentariosETQ = new Etiqueta(this, "Lista de Comentários", Fonte.textoNormal(), null, 630, 50, 150, 20);
		Etiqueta nomeDoComentarioETQ = new Etiqueta(this, "Nome do Comentário", Fonte.textoNormal(), null, 75, 150, 150, 20);
		Etiqueta comentarioETQ = new Etiqueta(this, "Comentário", Fonte.textoNormal(), null, 75, 175, 100, 20);
	}
	private void adicionarCampoDeTexto(){
		nomeDoComentarioCDT = new CampoDeTexto(this, 215, 150, 200, 20);
	}
	private void adicionarAreaDeTexto(){
		comentarioADT = new AreaDeTexto(this, 75, 200, 340, 225);
	}
	private void adicionarTabela(){
		tabelaDeComentarios = new Tabela(this, null, 547, 75, 313, 300, "", null);
		tabelaDeComentarios.setModel(tabelaDeComentarios.atualizarTabela("comentario", null, atividade, null, null, null));
	}
	private void adicionarBotao(){
		ImageIcon botaoExcluirICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoExcluir.png"));
		ImageIcon botaoConfirmarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCadastrar.png"));
		ImageIcon botaoDetalharICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoDetalhar.png"));
		ImageIcon botaoCancelarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCancelar.png"));
		
		OuvinteDoBotaoAtualizarDaJanelaDeAtualizacaoDeAtividade ouvinteAtualizar = new OuvinteDoBotaoAtualizarDaJanelaDeAtualizacaoDeAtividade(this);
		OuvinteDoBotaoDetalharDaJanelaDeAtualizacaoDeAtividade ouvinteDetalhar = new OuvinteDoBotaoDetalharDaJanelaDeAtualizacaoDeAtividade(this);
		OuvinteDoBotaoExcluirDaJanelaDeAtualizacaoDeAtividade ouvinteExcluir = new OuvinteDoBotaoExcluirDaJanelaDeAtualizacaoDeAtividade(this);
		OuvinteDoBotaoPDFComentarioDaJanelaDeAtualizacaoDeAtividade ouvintePDF = new OuvinteDoBotaoPDFComentarioDaJanelaDeAtualizacaoDeAtividade(this);
		OuvinteDoBotaoCancelar ouvinteCancelar = new OuvinteDoBotaoCancelar(this);
		
		excluirComentarioBT = new Botao(this, Fonte.tituloNegrito(), ouvinteExcluir, "Excluir", 544, 375, 160, 50, botaoExcluirICO);
		atualizarBT = new Botao(this, Fonte.tituloNegrito(), ouvinteAtualizar, "Atualizar", 350, 494, 200, 50, botaoConfirmarICO);
		
		Botao detalharBT = new Botao(this, Fonte.tituloNegrito(), ouvinteDetalhar, "Detalhar", 703, 375, 160, 50, botaoDetalharICO);
		Botao cancelarBT = new Botao(this, Fonte.tituloNegrito(), ouvinteCancelar, "Cancelar", 600, 494, 200, 50, botaoCancelarICO);
		Botao salvarComentarioPDFBT = new Botao(this, Fonte.tituloNegrito(), ouvintePDF, "PDF Comentário", 70, 494, 225, 50, null);
	}
	private void adicionarRadioBotao(){
		ButtonGroup grupo = new ButtonGroup();
		ativadaRb = new RadioBotao(this, 75, 75, 100, 20, "Ativada", grupo, null);
		desativadaRB = new RadioBotao(this, 175, 75, 120, 20, "Desativada", grupo, null);
	}
	private void status(){
		if(atividade.getStatus().equals("Desativada")){
			desativadaRB.setSelected(true);
			desativadaRB.setEnabled(false);
			ativadaRb.setEnabled(false);
			nomeDoComentarioCDT.setFocusable(false);
			comentarioADT.setFocusable(false);
			atualizarBT.setEnabled(false);
			excluirComentarioBT.setEnabled(false);
		}
		else if(atividade.getStatus().equals("Ativada")){
			ativadaRb.setSelected(true);
			
		}
	}
	
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	public Tabela getTabelaDeComentarios() {
		return tabelaDeComentarios;
	}
	public void setTabelaDeComentarios(Tabela tabelaDeComentarios) {
		this.tabelaDeComentarios = tabelaDeComentarios;
	}
	public RadioBotao getAtivadaRb() {
		return ativadaRb;
	}
	public void setAtivadaRb(RadioBotao ativadaRb) {
		this.ativadaRb = ativadaRb;
	}
	public RadioBotao getDesativadaRB() {
		return desativadaRB;
	}
	public void setDesativadaRB(RadioBotao desativadaRB) {
		this.desativadaRB = desativadaRB;
	}
	public AreaDeTexto getComentarioADT() {
		return comentarioADT;
	}
	public void setComentarioADT(AreaDeTexto comentarioADT) {
		this.comentarioADT = comentarioADT;
	}
	public CampoDeTexto getNomeDoComentarioCDT() {
		return nomeDoComentarioCDT;
	}
	public void setNomeDoComentarioCDT(CampoDeTexto nomeDoComentarioCDT) {
		this.nomeDoComentarioCDT = nomeDoComentarioCDT;
	}
	public Botao getAtualizarBT() {
		return atualizarBT;
	}
	public void setAtualizarBT(Botao atualizarBT) {
		this.atualizarBT = atualizarBT;
	}
	public Botao getExcluirComentarioBT() {
		return excluirComentarioBT;
	}
	public void setExcluirComentarioBT(Botao excluirComentarioBT) {
		this.excluirComentarioBT = excluirComentarioBT;
	}	
}