package jframe;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import classes.Fonte;
import classes.Obra;
import jframe.componentes.AreaDeTexto;
import jframe.componentes.Botao;
import jframe.componentes.CampoDeTexto;
import jframe.componentes.Etiqueta;
import jframe.componentes.RadioBotao;
import jframe.componentes.Tabela;
import jframe.ouvintes.OuvinteDasJanelasDeNivelUm;
import jframe.ouvintes.OuvinteDoBotaoCancelar;
import jframe.ouvintes.janelaDeOrcamentoDeObra.OuvinteDoBotaoConfirmarOrcamento;
import jframe.ouvintes.janelaDeOrcamentoDeObra.OuvinteDoBotaoDeletarGasto;
import jframe.ouvintes.janelaDeOrcamentoDeObra.OuvinteDoBotaoDetalharGastoOuAcrescimoOrcamento;
import jframe.ouvintes.janelaDeOrcamentoDeObra.OuvinteDoRadioBotaoOrcamento;

public class JanelaDeOrcamentoDaObra extends JanelaPadrao {
	
	private Obra obra;
	private Tabela tabelaAcrescimoEGastoTBL;
	private CampoDeTexto valorCDT;
	private CampoDeTexto nomeCDT;
	private AreaDeTexto descricaoADT;
	private RadioBotao acrescimoRB;
	private RadioBotao gastoRB;
	private RadioBotao escolherAcrescimoRB;
	private RadioBotao escolherGastoRB;
	private Etiqueta orcamentoInicialETQ;
	private Etiqueta totalAcrescimoETQ;
	private Etiqueta totalGastosETQ;
	private Etiqueta totalRestanteETQ;
	private Botao deletarGastoBT;
	
	public JanelaDeOrcamentoDaObra(Obra obra){
		OuvinteDasJanelasDeNivelUm ouvinteDaJanela = new OuvinteDasJanelasDeNivelUm(this);
		addWindowListener(ouvinteDaJanela);
		this.obra = obra;
		setTitle("Orçamento da Obra");
		adicionarBotao();		
		adicionarEtiqueta();
		atualizarEtiquetas();
		adicionarCampoDeTexto();
		adicionarRadioBotao();
		adicoinarAreaDeTexto();
		adicionarTabela();
		
		setVisible(true);
		requestFocusInWindow();		
	}
	public void adicionarBotao(){
		OuvinteDoBotaoCancelar ouvinteCancelar = new OuvinteDoBotaoCancelar(this);
		OuvinteDoBotaoConfirmarOrcamento ouvinteConfirmar = new OuvinteDoBotaoConfirmarOrcamento(this);	
		OuvinteDoBotaoDetalharGastoOuAcrescimoOrcamento ouvinteDetalhar = new OuvinteDoBotaoDetalharGastoOuAcrescimoOrcamento(this);
		OuvinteDoBotaoDeletarGasto ouvinteDeletar = new OuvinteDoBotaoDeletarGasto(this);
		
		ImageIcon botaoConfirmarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCadastrar.png"));
		ImageIcon botaoCancelarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCancelar.png"));
		ImageIcon botaoDetalharICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoDetalhar.png"));
		ImageIcon botaoExcluirGastoICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoExcluir.png"));
		
		Botao confirmarBT = new Botao(this, Fonte.tituloNegrito(), ouvinteConfirmar, "Confirmar", 144, 494, 200, 50, botaoConfirmarICO);		
		Botao cancelarBT = new Botao(this, Fonte.tituloNegrito(), ouvinteCancelar, "Cancelar", 528, 494, 200, 50, botaoCancelarICO);
		Botao detalharBT = new Botao(this, Fonte.tituloNegrito(), ouvinteDetalhar, "Detalhar", 700, 400, 160, 50, botaoDetalharICO);
		deletarGastoBT = new Botao(this, Fonte.tituloNegrito(), ouvinteDeletar, "Deletar Gasto", 450, 400, 230, 50, botaoExcluirGastoICO);
		deletarGastoBT.setEnabled(false);
	}
	public void adicionarTabela(){
		tabelaAcrescimoEGastoTBL = new Tabela(this, null, 435, 100, 460, 300, "", this.obra);
		tabelaAcrescimoEGastoTBL.setModel(tabelaAcrescimoEGastoTBL.atualizarTabela("acréscimo", this.obra, null, null, null, null));	
	}
	public void adicionarEtiqueta(){
		
		Etiqueta orcamentoDaObraETQ = new Etiqueta(this, "Orçamento da Obra", Fonte.personalizadaNegrito(30), null, 300, 10, 300, 25);		
		Etiqueta relatorioObraETQ = new Etiqueta(this, "Relatório orçamentário da obra", Fonte.textoNegrito(), null, 560, 50, 220, 20);		
		Etiqueta inicialETQ = new Etiqueta(this, "Orçamento Inicial; R$", Fonte.textoNegrito(), null, 75, 50, 150, 20);
		Etiqueta acrescimosETQ = new Etiqueta(this, "Acréscimos; R$", Fonte.textoNegrito(), null, 75, 75, 125, 20);
		Etiqueta gastosETQ = new Etiqueta(this, "Gastos; R$", Fonte.textoNegrito(), null, 75, 100, 100, 20);
		Etiqueta restanteETQ = new Etiqueta(this, "Orçamento Restante; R$", Fonte.textoNegrito(), null, 75, 125, 175, 20);
		
		orcamentoInicialETQ = new Etiqueta(this, String.valueOf(obra.getOrcamentoInicial()), Fonte.textoNegrito(), null, 225, 50, 200, 20, SwingConstants.LEFT);		
		totalAcrescimoETQ = new Etiqueta(this, String.valueOf(obra.totalAcrescimos()), Fonte.textoNegrito(), null, 185, 75, 200, 20, SwingConstants.LEFT);
		totalAcrescimoETQ.setForeground(Color.CYAN);
		totalGastosETQ = new Etiqueta(this, String.valueOf(obra.totalGastos()), Fonte.textoNegrito(), null, 155, 100, 200, 20, SwingConstants.LEFT);
		totalGastosETQ.setForeground(Color.ORANGE);
		totalRestanteETQ = new Etiqueta(this, String.valueOf(obra.totalRestante()), Fonte.textoNegrito(), null, 250, 125, 200, 20, SwingConstants.LEFT);
		
		Etiqueta nomeETQ = new Etiqueta(this, "Nome*", Fonte.textoNormal(), null, 75, 200, 53, 20);
		Etiqueta valorETQ = new Etiqueta(this, "Valor R$*", Fonte.textoNormal(), null, 75, 230, 70, 20);
		Etiqueta descricaoETQ = new Etiqueta(this, "Descrição*", Fonte.textoNormal(), "Descrição do acréscimo ou gasto", 75, 250, 78, 20);
	}
	public void atualizarEtiquetas(){
		totalAcrescimoETQ.setText(String.valueOf(obra.totalAcrescimos()));
		totalGastosETQ.setText(String.valueOf(obra.totalGastos()));
		double totalRestanteTemp = obra.totalRestante();
		totalRestanteETQ.setText(String.valueOf(totalRestanteTemp));
		if(totalRestanteTemp >= 0){
			totalRestanteETQ.setForeground(Color.GREEN);
		}
		else{
			totalRestanteETQ.setForeground(Color.RED);
		}
	}
	public void adicionarCampoDeTexto(){
		nomeCDT = new CampoDeTexto(this, 145, 200, 200, 23);
		valorCDT = new CampoDeTexto(this, 145, 230, 200, 23);
	}
	public void adicoinarAreaDeTexto(){
		descricaoADT = new AreaDeTexto(this, 75, 275, 300, 200);
	}
	public void adicionarRadioBotao(){
		OuvinteDoRadioBotaoOrcamento ouvinteAcrescimo = new OuvinteDoRadioBotaoOrcamento(this, "Acréscimo");
		OuvinteDoRadioBotaoOrcamento ouvinteGasto = new OuvinteDoRadioBotaoOrcamento(this, "Gasto");
		
		ButtonGroup grupo1 = new ButtonGroup();
		ButtonGroup grupo2 = new ButtonGroup();
		
		acrescimoRB = new RadioBotao(this, 69, 175, 100, 23, "Acréscimo", grupo1, null);
		gastoRB = new RadioBotao(this, 200, 175, 100, 23,"Gasto" , grupo1, null);
		
		escolherAcrescimoRB = new RadioBotao(this, 550, 80, 100, 23, "Acréscimo", grupo2, ouvinteAcrescimo);
		escolherAcrescimoRB.setSelected(true);		
		escolherGastoRB = new RadioBotao(this, 675, 80, 100, 23, "Gasto", grupo2, ouvinteGasto);
	}	
	
	public Obra getObra() {
		return obra;
	}
	public void setObra(Obra obra) {
		this.obra = obra;
	}			
	public CampoDeTexto getValorCDT() {
		return valorCDT;
	}
	public Tabela getTabelaAcrescimoEGastoTBL() {
		return tabelaAcrescimoEGastoTBL;
	}
	public void setTabelaAcrescimoEGastoTBL(Tabela tabelaAcrescimoEGastoTBL) {
		this.tabelaAcrescimoEGastoTBL = tabelaAcrescimoEGastoTBL;
	}
	public void setValorCDT(CampoDeTexto valorCDT) {
		this.valorCDT = valorCDT;
	}	
	public CampoDeTexto getNomeCDT() {
		return nomeCDT;
	}
	public void setNomeCDT(CampoDeTexto nomeCDT) {
		this.nomeCDT = nomeCDT;
	}
	public AreaDeTexto getDescricaoADT() {
		return descricaoADT;
	}
	public void setDescricaoADT(AreaDeTexto descricaoADT) {
		this.descricaoADT = descricaoADT;
	}
	public RadioBotao getAcrescimoRB() {
		return acrescimoRB;
	}
	public void setAcrescimoRB(RadioBotao acrescimoRB) {
		this.acrescimoRB = acrescimoRB;
	}
	public RadioBotao getGastoRB() {
		return gastoRB;
	}
	public void setGastoRB(RadioBotao gastoRB) {
		this.gastoRB = gastoRB;
	}	
	public RadioBotao getEscolherAcrescimoRB() {
		return escolherAcrescimoRB;
	}
	public void setEscolherAcrescimoRB(RadioBotao escolherAcrescimoRB) {
		this.escolherAcrescimoRB = escolherAcrescimoRB;
	}
	public RadioBotao getEscolherGastoRB() {
		return escolherGastoRB;
	}
	public void setEscolherGastoRB(RadioBotao escolherGastoRB) {
		this.escolherGastoRB = escolherGastoRB;
	}
	public Etiqueta getOrcamentoInicialETQ() {
		return orcamentoInicialETQ;
	}
	public void setOrcamentoInicialETQ(Etiqueta orcamentoInicialETQ) {
		this.orcamentoInicialETQ = orcamentoInicialETQ;
	}
	public Etiqueta getTotalAcrescimoETQ() {
		return totalAcrescimoETQ;
	}
	public void setTotalAcrescimoETQ(Etiqueta totalAcrescimoETQ) {
		this.totalAcrescimoETQ = totalAcrescimoETQ;
	}
	public Etiqueta getTotalGastosETQ() {
		return totalGastosETQ;
	}
	public void setTotalGastosETQ(Etiqueta totalGastosETQ) {
		this.totalGastosETQ = totalGastosETQ;
	}
	public Etiqueta getTotalRestanteETQ() {
		return totalRestanteETQ;
	}
	public void setTotalRestanteETQ(Etiqueta totalRestanteETQ) {
		this.totalRestanteETQ = totalRestanteETQ;
	}
	public Botao getDeletarGastoBT() {
		return deletarGastoBT;
	}
	public void setDeletarGastoBT(Botao deletarGastoBT) {
		this.deletarGastoBT = deletarGastoBT;
	}	
}