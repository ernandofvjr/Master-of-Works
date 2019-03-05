package jframe;

import javax.swing.ImageIcon;
import classes.Atividade;
import classes.Fonte;
import classes.LeitorDeTxt;
import jframe.componentes.AreaDeTexto;
import jframe.componentes.Botao;
import jframe.componentes.Calendario;
import jframe.componentes.CampoDeTexto;
import jframe.componentes.ComboCaixa;
import jframe.componentes.Etiqueta;
import jframe.componentes.Tabela;
import jframe.ouvintes.OuvinteDasJanelasDeNivelUm;
import jframe.ouvintes.OuvinteDoBotaoCancelar;
import jframe.ouvintes.janelaCadastroDeAtividade.OuvinteDoBotaoCadastrarAtividade;
import jframe.ouvintes.janelaCadastroDeAtividade.OuvinteDoBotaoCalendarioInicioAtividade;
import jframe.ouvintes.janelaCadastroDeAtividade.OuvinteDoBotaoCalendarioTerminoAtividade;
import jframe.ouvintes.janelaCadastroDeAtividade.OuvinteDoBotaoCopiarNomeCadastroDeAtividade;
import jframe.ouvintes.janelaCadastroDeAtividade.OuvinteDoBotaoEditarAtividade;

public class JanelaCadastroDeAtividade extends JanelaPadrao {
	
	private Atividade atividade;
	private AreaDeTexto descricaoCurtaADT;
	private AreaDeTexto descricaoLongaADT;
	private ComboCaixa listaAtividadesCC;
	private CampoDeTexto prazoCDT;
//	private CampoDeTexto terminoCDT;
	private Tabela obraTBL;
	private OuvinteDasJanelasDeNivelUm ouvinteDaJanela = new OuvinteDasJanelasDeNivelUm(this);
	private Calendario calendarioInicio;
	private Calendario calendarioTermino;
	private  LeitorDeTxt leitor = new LeitorDeTxt();
	private String[] atividades = leitor.lerArquivoDeProfissoes("atividades");
	
	public JanelaCadastroDeAtividade(){		
		addWindowListener(ouvinteDaJanela);
		setTitle("Cadastro de Atividade");
		adicionarEtiqueta();
		adicionarBotao();
		adicionarCampoDeTexto();
		adicionarAreaDeTexto();
		adicionarTabela();
		adicionarCalendario();
		adicionarComboBox();
		setVisible(true);
		requestFocusInWindow();
	}
	//construtor que recebe uma atividade para a janela de edição de atividade
	public JanelaCadastroDeAtividade(Atividade atividade){		
		this.atividade = atividade;
		addWindowListener(ouvinteDaJanela);
		setTitle("Editar Atividade");
		adicionarEtiqueta();
		adicionarBotao();
		adicionarBotaoEditar();
		adicionarCampoDeTexto();
		adicionarAreaDeTexto();
		adicionarCalendarioSemOuvinte();
		adicionarComboBox();
		adicionarTabela();
		adicionarInformacoes();
		setVisible(true);		
		requestFocusInWindow();
	}
	private void adicionarEtiqueta(){
		Etiqueta cadastroDeAtividadeETQ = new Etiqueta(this, "Cadastro De Atividade", Fonte.personalizadaNegrito(30), null, 325, 51, 350, 25);
		if(atividade != null){
			cadastroDeAtividadeETQ.setText("Editar Atividade");
		}
		
		Etiqueta terminoETQ = new Etiqueta(this, "Término*", Fonte.textoNormal(), "Data de término da atividade", 123, 195, 110, 20);
		Etiqueta inicioETQ = new Etiqueta(this, "Início*", Fonte.textoNormal(), "Data de início da ativade", 123, 165, 110, 20);
		Etiqueta nomeDaAtividade = new Etiqueta(this, "Nome da atividade*", Fonte.textoNormal(), null, 123, 106, 125, 20);
		Etiqueta selecionarObra = new Etiqueta(this, "Selecione uma obra*", Fonte.textoNormal(), "Escolha uma obra para adicionar essa atividade", 123, 235, 150, 20);
		Etiqueta descricaoCurtaETQ = new Etiqueta(this, "Descrição Curta*", Fonte.textoNormal(), null, 475, 106, 230, 20);
		Etiqueta descricaoLongaETQ = new Etiqueta(this, "Descrição Longa", Fonte.textoNormal(), null, 475, 276, 210, 20);
		Etiqueta prazoETQ = new Etiqueta(this, "Prazo (dias)*", Fonte.textoNormal(), "Prazo em dias para o término da atividade", 370, 175, 110, 20);
		
	}
	private void adicionarCampoDeTexto(){
		prazoCDT = new CampoDeTexto(this, 370, 195, 50, 23, null);
		prazoCDT.setEditable(false);
//		terminoCDT = new CampoDeTexto(this, 230, 165, 50, 23, null);
//		terminoCDT.setEditable(false);
	}
	private void adicionarAreaDeTexto(){
		descricaoCurtaADT = new AreaDeTexto(this, 475, 130, 400, 130);
		descricaoLongaADT = new AreaDeTexto(this, 475, 300, 400, 180);
	}
	private void adicionarComboBox() {
		listaAtividadesCC = new ComboCaixa(this, Fonte.textoNormal(), 123, 135, 322, 21, atividades);
	}
	
	
	private void adicionarCalendario(){
		OuvinteDoBotaoCalendarioInicioAtividade ouvinteInicio = new OuvinteDoBotaoCalendarioInicioAtividade(this);
		calendarioInicio = new Calendario(this, 195, 165, 150, 23, ouvinteInicio);
		OuvinteDoBotaoCalendarioTerminoAtividade ouvinteTermino = new OuvinteDoBotaoCalendarioTerminoAtividade(this);
		calendarioTermino = new Calendario(this, 195, 195, 150, 23, ouvinteTermino);		
	}
	private void adicionarCalendarioSemOuvinte(){
//		OuvinteDoBotaoCalendarioInicioAtividade ouvinteInicio = new OuvinteDoBotaoCalendarioInicioAtividade(this);
		calendarioInicio = new Calendario(this, 195, 165, 150, 23, null);
//		OuvinteDoBotaoCalendarioTerminoAtividade ouvinteTermino = new OuvinteDoBotaoCalendarioTerminoAtividade(this);
		calendarioTermino = new Calendario(this, 195, 195, 150, 23, null);
	}
	
	private void adicionarBotao(){
		ImageIcon botaoCadatrarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCadastrar.png"));
		ImageIcon botaoCancelarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCancelar.png"));
		
		OuvinteDoBotaoCadastrarAtividade ouvinte = new OuvinteDoBotaoCadastrarAtividade(this);
		OuvinteDoBotaoCancelar ouvinteCancelar = new OuvinteDoBotaoCancelar(this);
		OuvinteDoBotaoCopiarNomeCadastroDeAtividade ouvinteCopiar = new OuvinteDoBotaoCopiarNomeCadastroDeAtividade(this);
		
		if(atividade == null){
			Botao cadastrarBT = new Botao(this, Fonte.tituloNegrito(),ouvinte, "Cadastrar", 144, 494, 200, 50, botaoCadatrarICO);
		}
		Botao cancelarBT = new Botao(this, Fonte.tituloNegrito(),ouvinteCancelar, "Cancelar", 528, 494, 200, 50, botaoCancelarICO);		
		Botao copiarNomeBT = new Botao(this, Fonte.textoNormal(), ouvinteCopiar, "Copiar Nome", 600, 105, 115, 25, null);
	}
	private void adicionarBotaoEditar(){
		ImageIcon botaoCadatrarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCadastrar.png"));
		
		OuvinteDoBotaoEditarAtividade ouvinteEditar = new OuvinteDoBotaoEditarAtividade(this);
		
		Botao EditarBT = new Botao(this, Fonte.tituloNegrito(),ouvinteEditar, "Atualizar", 144, 494, 200, 50, botaoCadatrarICO);
	}
	
	private void adicionarTabela(){		
		
		obraTBL = new Tabela(this, null, 120, 258, 324, 222, "obra", null);
		
		if(atividade != null){
			obraTBL.setEnabled(false);
		}
	}
	private void adicionarInformacoes(){
		for(int i = 0;i < atividades.length;i++){
			if(atividade.getNome().equals(atividades[i])){
				listaAtividadesCC.setSelectedIndex(i);
			}
		}
		
		calendarioInicio.setDate(atividade.getDataDeCriacao());
		calendarioInicio.setEnabled(false);
		calendarioTermino.setDate(atividade.getDataDeConclusao());
		calendarioTermino.setEnabled(false);
		
		listaAtividadesCC.setEnabled(false);
		prazoCDT.setText(String.valueOf(atividade.getPrazo()));
		prazoCDT.setFocusable(false);
		descricaoCurtaADT.setText(atividade.getDescricaoCurta());
		descricaoLongaADT.setText(atividade.getDescricaoLonga());
	}	
	public AreaDeTexto getDescricaoCurtaADT() {
		return descricaoCurtaADT;
	}
	public void setDescricaoCurtaADT(AreaDeTexto descricaoCurtaADT) {
		this.descricaoCurtaADT = descricaoCurtaADT;
	}
	public AreaDeTexto getDescricaoLongaADT() {
		return descricaoLongaADT;
	}
	public void setDescricaoLongaADT(AreaDeTexto descricaoLongaADT) {
		this.descricaoLongaADT = descricaoLongaADT;
	}
	public CampoDeTexto getPrazoCDT() {
		return prazoCDT;
	}
	public void setPrazoCDT(CampoDeTexto prazoCDT) {
		this.prazoCDT = prazoCDT;
	}
	public Tabela getObraTBL() {
		return obraTBL;
	}
	public void setObraTBL(Tabela obraTBL) {
		this.obraTBL = obraTBL;
	}
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	public Calendario getCalendarioTermino() {
		return calendarioTermino;
	}
	public void setCalendarioTermino(Calendario calendarioTermino) {
		this.calendarioTermino = calendarioTermino;
	}
	public ComboCaixa getListaAtividadesCC() {
		return listaAtividadesCC;
	}
	public void setListaAtividadesCC(ComboCaixa listaAtividadesCC) {
		this.listaAtividadesCC = listaAtividadesCC;
	}
	public String[] getAtividades() {
		return atividades;
	}
	public void setAtividades(String[] atividades) {
		this.atividades = atividades;
	}
	public Calendario getCalendarioInicio() {
		return calendarioInicio;
	}
	public void setCalendarioInicio(Calendario calendarioInicio) {
		this.calendarioInicio = calendarioInicio;
	}
	
}

