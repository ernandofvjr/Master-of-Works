package jframe;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import classes.Fonte;
import classes.Gerente;
import jframe.componentes.AreaDeTexto;
import jframe.componentes.Arvore;
import jframe.componentes.Botao;
import jframe.componentes.CheckBox;
import jframe.componentes.Etiqueta;
import jframe.componentes.Painel;
import jframe.componentes.RolagemDoPainel;
import jframe.componentes.Tabela;
import jframe.ouvintes.janelaPrincipal.OuvinteDaTabelaAtividadesPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDaTabelaObrasPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDasTabelasPrincipalFocus;
import jframe.ouvintes.janelaPrincipal.OuvinteDeAtalhoJanelaPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDeSelecaoDaArvoreJanelaPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDoBotaoEditarPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDoBotaoSalvarJanelaPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDoMenuAbrirPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDoMenuAdicionar;
import jframe.ouvintes.janelaPrincipal.OuvinteDoMenuListagem;
import jframe.ouvintes.janelaPrincipal.OuvinteDoMenuNovoPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDoMenuSalvarPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDoMenuSairPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDoMenuSalvarPDF;
import jframe.ouvintes.janelaPrincipal.OuvinteDoMenuSobre;
import jframe.ouvintes.janelaPrincipal.OuvinteDoMouseDaArvoreJanelaPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDaJanelaAtualizarAtividadeDaJanelaPrincipal;
import jframe.ouvintes.janelaPrincipal.OuvinteDaJanelaPrincipal;

/**
 * Esta classe é a primeira janela a ser vista pelo usuário e apartir dela toda as outras janelas são acessadas
 * @author Elton J.
 * 
 *
 */
public class JanelaPrincipal extends JanelaPadrao {
	
	private Tabela obraTBL;
//	private Tabela atividadeTBL;
	private Tabela funcionarioTBL;	
	private AreaDeTexto descricaoCurtaADT;
//	private AreaDeTexto descricaoLongaADT;	
	private Gerente gerente = Gerente.obterInstancia();	
	private Etiqueta valorOrçamentoETQ;
	private Etiqueta valorGastosETQ;
	private Etiqueta valorAcrescimosETQ;
	private Etiqueta valorrestanteETQ;
	private Etiqueta descricaoCutaETQ;
	private Etiqueta acompanhamentoSemanalETQ;
	private Etiqueta planejadosETQ;
	private Etiqueta executadosETQ;
	
	//checkbox dos dias da semana planejados
	private CheckBox domCBP;
	private CheckBox segCBP;
	private CheckBox terCBP;
	private CheckBox quaCBP;
	private CheckBox quiCBP;
	private CheckBox sexCBP;
	private CheckBox sabCBP;
	
	//chekbox dos dias da semana executados
	private CheckBox domCBE;
	private CheckBox segCBE;
	private CheckBox terCBE;
	private CheckBox quaCBE;
	private CheckBox quiCBE;
	private CheckBox sexCBE;
	private CheckBox sabCBE;
	
	private Botao salvarBT;
	private Botao comentarioBT;
	
	private ArrayList<CheckBox> listaDeCheckbox = new ArrayList<CheckBox>();
	
//	private Arvore arvore;
//	private ModeloDeArvore modeloDeArvore;
	private Painel painelDaArvore;
	private OuvinteDeSelecaoDaArvoreJanelaPrincipal ouvinteDeSelecao;
	
	public JanelaPrincipal(){
		OuvinteDaJanelaPrincipal ouvintePrincipal = new OuvinteDaJanelaPrincipal(this);
		OuvinteDeAtalhoJanelaPrincipal ouvinteTest = new OuvinteDeAtalhoJanelaPrincipal(this);
		
		addKeyListener(ouvinteTest);
		addWindowListener(ouvintePrincipal);
		
		setTitle("Master of Works");		
		adicionarEtiqueta();
		adicionarBotao();
		adicionarBarraDeMenu();
		adicionarTabela();
		adicionarArvore();
		adicionarAreaDeTexto();	
		adicionarCheckBox();
		
		setVisible(true);
		requestFocusInWindow();		
		
	}
	
	private void adicionarArvore() {		
		
//		modeloDeArvore = new ModeloDeArvore(gerente.getSemanas());
		
		ouvinteDeSelecao = new OuvinteDeSelecaoDaArvoreJanelaPrincipal(this);
		OuvinteDoMouseDaArvoreJanelaPrincipal ouvinteDeMouse = new OuvinteDoMouseDaArvoreJanelaPrincipal(this);
		
		Arvore arvore = new Arvore(null);
		arvore.addTreeSelectionListener(ouvinteDeSelecao);
		arvore.addMouseListener(ouvinteDeMouse);
		RolagemDoPainel rolagem = new RolagemDoPainel(arvore);		
		painelDaArvore = new Painel(this, rolagem, 225, 5, 210, 415);
		
	}	
	private void adicionarCheckBox(){
		//planejados
		domCBP = new CheckBox(this, 667, 160, 35, 35, "Dom", "Domingo");
		segCBP = new CheckBox(this, 699, 160, 35, 35, "Seg", "Segunda-feira");
		terCBP = new CheckBox(this, 730, 160, 35, 35, "Ter", "Terça-feira");
		quaCBP = new CheckBox(this, 757, 160, 35, 35, "Qua", "Quarta-feira");
		quiCBP = new CheckBox(this, 787, 160, 35, 35, "Qui", "Quinta-feira");
		sexCBP = new CheckBox(this, 817, 160, 35, 35, "Sex", "Sexta-feira");
		sabCBP = new CheckBox(this, 847, 160, 35, 35, "Sab", "Sábado");	
		
		domCBP.setEnabled(false);
		segCBP.setEnabled(false);
		terCBP.setEnabled(false);
		quaCBP.setEnabled(false);
		quiCBP.setEnabled(false);
		sexCBP.setEnabled(false);
		sabCBP.setEnabled(false);
		
		listaDeCheckbox.add(domCBP);
		listaDeCheckbox.add(segCBP);
		listaDeCheckbox.add(terCBP);
		listaDeCheckbox.add(quaCBP);
		listaDeCheckbox.add(quiCBP);
		listaDeCheckbox.add(sexCBP);
		listaDeCheckbox.add(sabCBP);		
		
		//executados
		domCBE = new CheckBox(this, 672, 190, 35, 35, "Domingo");
		segCBE = new CheckBox(this, 700, 190, 35, 35, "Segunda-feira");
		terCBE = new CheckBox(this, 730, 190, 35, 35, "Terça-feira");
		quaCBE = new CheckBox(this, 760, 190, 35, 35, "Quarta-feira");
		quiCBE = new CheckBox(this, 788, 190, 35, 35, "Quinta-feira");
		sexCBE = new CheckBox(this, 818, 190, 35, 35, "Sexta-feira");
		sabCBE = new CheckBox(this, 848, 190, 35, 35, "Sábado");	
		
		domCBE.setEnabled(false);
		segCBE.setEnabled(false);
		terCBE.setEnabled(false);
		quaCBE.setEnabled(false);
		quiCBE.setEnabled(false);
		sexCBE.setEnabled(false);
		sabCBE.setEnabled(false);
		
		listaDeCheckbox.add(domCBE);
		listaDeCheckbox.add(segCBE);
		listaDeCheckbox.add(terCBE);
		listaDeCheckbox.add(quaCBE);
		listaDeCheckbox.add(quiCBE);
		listaDeCheckbox.add(sexCBE);
		listaDeCheckbox.add(sabCBE);
		
		
	
	}
	/**
	 * Adiciona os componente area de texto de acordo com suas coordenadas
	 */
	private void adicionarAreaDeTexto(){		
		descricaoCurtaADT = new AreaDeTexto(this, 670, 285, 200, 125);
		descricaoCurtaADT.setFocusable(false);
//		descricaoLongaADT = new AreaDeTexto(this, 670, 360, 200, 165);
//		descricaoLongaADT.setFocusable(false);
	}
	/**
	 * Adiciona os componentes tabela de acordo com suas coordenadas
	 */
	private void adicionarTabela(){		
		OuvinteDaTabelaObrasPrincipal ouvinteTabelaObra = new OuvinteDaTabelaObrasPrincipal(this);
//		OuvinteDaTabelaAtividadesPrincipal ouvinteTabelaFuncionario = new OuvinteDaTabelaAtividadesPrincipal(this);
		OuvinteDasTabelasPrincipalFocus focus = new OuvinteDasTabelasPrincipalFocus(this);
		
		obraTBL = new Tabela(this, ouvinteTabelaObra, 5, 5, 210, 415, "obra", null);
//		atividadeTBL = new Tabela(this, ouvinteTabelaFuncionario, 225, 5, 210, 415, "atividade", null);			
		funcionarioTBL = new Tabela(this, null, 445, 5, 210, 415, "funcionario", null);
		
		
		//setando um novo cursor nas tabelas
//		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
//		obraTBL.setCursor(cursor);
//		funcionarioTBL.setCursor(cursor);
		
		
		
		obraTBL.addFocusListener(focus);
		
		funcionarioTBL.addFocusListener(focus);
		
		
	}
	/**
	 * Adiciona os componentes etiquetas de acordo com suas coordenadas
	 */
	private void adicionarEtiqueta(){		
		valorOrçamentoETQ = new Etiqueta(this, "0", Fonte.textoNegrito(), null, 775, 10, 90, 23, SwingConstants.LEFT);
		valorAcrescimosETQ = new Etiqueta(this, "0", Fonte.textoNegrito(), null, 780, 40, 90, 23, SwingConstants.LEFT);
		valorAcrescimosETQ.setForeground(Color.cyan);
		valorGastosETQ = new Etiqueta(this, "0", Fonte.textoNegrito(), null, 750, 70, 90, 23, SwingConstants.LEFT);	
		valorGastosETQ.setForeground(Color.orange);
		valorrestanteETQ = new Etiqueta(this, "0", Fonte.textoNegrito(), null, 765, 100, 90, 23, SwingConstants.LEFT);
		planejadosETQ = new Etiqueta(this, "P-", Fonte.textoNormal(), "Planejado", 660, 175, 23, 23);
		executadosETQ = new Etiqueta(this, "E-", Fonte.textoNormal(), "Planejado", 660, 195, 23, 23);
		
		Etiqueta orçamentoETQ = new Etiqueta(this, "Orçamento: R$ ", Fonte.textoNegrito(), null, 670, 10, 120, 23);
		Etiqueta acrescimosETQ = new Etiqueta(this, "Acréscimos: R$ ", Fonte.textoNegrito(), null, 670, 40, 120, 23);
		Etiqueta gastosETQ = new Etiqueta(this, "Gastos: R$ ", Fonte.textoNegrito(), null, 670, 70, 120, 23);
		Etiqueta restanteETQ = new Etiqueta(this, "Restante: R$ ", Fonte.textoNegrito(), null, 670, 100, 120, 23);
		
		descricaoCutaETQ = new Etiqueta(this, "Descrição curta da atividade", Fonte.textoNegrito(), null, 670, 260, 250, 23);
		acompanhamentoSemanalETQ = new Etiqueta(this, "Acompanhamento Semanal", Fonte.textoNegrito(), null, 670, 135, 250, 23);
		
		descricaoCutaETQ.setEnabled(false);
		acompanhamentoSemanalETQ.setEnabled(false);
		planejadosETQ.setEnabled(false);
		executadosETQ.setEnabled(false);
//		Etiqueta descricaoLongaETQ = new Etiqueta(this, "Descrição longa da atividade", Fonte.textoNormal(), null, 670, 340, 200, 23);
	}
	/**
	 * Adiciona os componentes botao de acordo com suas coordenadas
	 */
	private void adicionarBotao(){
		ImageIcon novaObraICO = new ImageIcon(getClass().getResource("/imagens/IconeNovaObra.png"));
		ImageIcon editarObraICO = new ImageIcon(getClass().getResource("/imagens/IconeEditarObra.png"));
		ImageIcon listarObraICO = new ImageIcon(getClass().getResource("/imagens/IconeListarObra.png"));		
		
		ImageIcon novaAtividadeICO = new ImageIcon(getClass().getResource("/imagens/IconeNovaAtividade.png"));
		ImageIcon editarAtividadeICO = new ImageIcon(getClass().getResource("/imagens/IconeEditarAtividade.png"));
		ImageIcon listarAtividadeICO = new ImageIcon(getClass().getResource("/imagens/IconeListarAtividade.png"));
		
		ImageIcon novoFuncionarioICO = new ImageIcon(getClass().getResource("/imagens/IconeNovoFuncionario.png"));
		ImageIcon editarFuncionarioICO = new ImageIcon(getClass().getResource("/imagens/IconeEditarFuncionario.png"));
		ImageIcon listarFuncionarioICO = new ImageIcon(getClass().getResource("/imagens/IconeListarFuncionario.png"));		
		
		OuvinteDoMenuListagem ouvinteDoBotaoListagem = new OuvinteDoMenuListagem(this);
		OuvinteDoMenuAdicionar ouvinteDoBotaoCadastrarObra = new OuvinteDoMenuAdicionar(this, "obra");
		OuvinteDoMenuAdicionar ouvinteDoBotaoCadastrarAtividade = new OuvinteDoMenuAdicionar(this, "atividade");
		OuvinteDoMenuAdicionar ouvinteDoBotaoCadastrarFuncionario = new OuvinteDoMenuAdicionar(this, "funcionario");
		
		
		OuvinteDoBotaoEditarPrincipal ouvinteDoBotaoEditarObra = new OuvinteDoBotaoEditarPrincipal(this, "obra");
		OuvinteDoBotaoEditarPrincipal ouvinteDoBotaoEditarAtivade = new OuvinteDoBotaoEditarPrincipal(this, "atividade");
		OuvinteDoBotaoEditarPrincipal ouvinteDoBotaoEditarFuncionario = new OuvinteDoBotaoEditarPrincipal(this, "funcionario");
		
		Botao novaObraBT = new Botao(this, Fonte.textoNegrito(), ouvinteDoBotaoCadastrarObra, "Nova Obra", 5, 420, 210, 40, novaObraICO);
		Botao editarObraBT = new Botao(this, Fonte.textoNegrito(), ouvinteDoBotaoEditarObra, "Orçamento da Obra", 5, 455, 210, 40, editarObraICO);
		Botao listagemObraBT = new Botao(this, Fonte.textoNegrito(), ouvinteDoBotaoListagem, "Listar Obras", 5, 490, 210, 40, listarObraICO);		
		
		Botao novaAtividadeBT = new Botao(this, Fonte.textoNegrito(), ouvinteDoBotaoCadastrarAtividade, "Nova Atividade", 225, 420, 210, 40, novaAtividadeICO);
		Botao editarAtividadeBT = new Botao(this, Fonte.textoNegrito(), ouvinteDoBotaoEditarAtivade, "Editar Atividade", 225, 455, 210, 40, editarAtividadeICO);
		Botao listagemAtividadeBT = new Botao(this, Fonte.textoNegrito(), ouvinteDoBotaoListagem, "Listar Atividades", 225, 490, 210, 40, listarAtividadeICO);
		
		Botao novoFuncionarioBT = new Botao(this, Fonte.textoNegrito(), ouvinteDoBotaoCadastrarFuncionario, "Novo Funcionário", 445, 420, 210, 40, novoFuncionarioICO);
		Botao editarFuncionarioBT = new Botao(this, Fonte.textoNegrito(), ouvinteDoBotaoEditarFuncionario, "Editar Funcionário", 445, 455, 210, 40, editarFuncionarioICO);
		Botao listagemFuncionarioBT = new Botao(this, Fonte.textoNegrito(), ouvinteDoBotaoListagem, "Listar Funcionários", 445, 490, 210, 40, listarFuncionarioICO);
		
		if(gerente.getObras().isEmpty()){
			editarObraBT.setEnabled(false);
			listagemObraBT.setEnabled(false);
			novaAtividadeBT.setEnabled(false);			
		}
		if(gerente.getAtividades().isEmpty()){
			editarAtividadeBT.setEnabled(false);
			listagemAtividadeBT.setEnabled(false);
//			novoFuncionarioBT.setEnabled(false); 
		}
//		if(gerente.getFuncionarios().isEmpty()){			
//			editarFuncionarioBT.setEnabled(false);
//			listagemFuncionarioBT.setEnabled(false);
//		}
		OuvinteDoBotaoSalvarJanelaPrincipal ouvinteSalvar = new OuvinteDoBotaoSalvarJanelaPrincipal(this);
		salvarBT = new Botao(this, Fonte.textoNormal(), ouvinteSalvar, "Salvar Atividade", 670, 220, 200, 30, null);
		
		OuvinteDaJanelaAtualizarAtividadeDaJanelaPrincipal ouvinteComentario = new OuvinteDaJanelaAtualizarAtividadeDaJanelaPrincipal(this);
		comentarioBT = new Botao(this, Fonte.textoNormal(), ouvinteComentario, "Comentário Atividade", 670, 415, 200, 30, null);
		
		salvarBT.setEnabled(false);
		comentarioBT.setEnabled(false);
		
	}
	/**
	 * Adiciona uma barra de menu a janela, este menu é dividido em grupos contendo vários itens
	 */
	private void adicionarBarraDeMenu(){
		
		OuvinteDoMenuSalvarPDF ouvintesalvarPDF	= new OuvinteDoMenuSalvarPDF(this);
		OuvinteDoMenuSairPrincipal ouvinteSair = new OuvinteDoMenuSairPrincipal(this);
		OuvinteDoMenuAdicionar ouvinteAdicionarObra = new OuvinteDoMenuAdicionar(this, "obra");
		OuvinteDoMenuAdicionar ouvinteAdicionarAtividade = new OuvinteDoMenuAdicionar(this, "atividade");
		OuvinteDoMenuAdicionar ouvinteAdicionarFuncionario = new OuvinteDoMenuAdicionar(this, "funcionario");
		OuvinteDoMenuListagem ouvinteListagem = new OuvinteDoMenuListagem(this);
		OuvinteDoMenuSobre ouvinteSobre = new OuvinteDoMenuSobre(this);
		
		OuvinteDoMenuAbrirPrincipal ouvinteAbrir = new OuvinteDoMenuAbrirPrincipal(this);
		OuvinteDoMenuSalvarPrincipal ouvinteSalvar = new OuvinteDoMenuSalvarPrincipal(this);
		OuvinteDoMenuNovoPrincipal ouvinteNovo = new OuvinteDoMenuNovoPrincipal(this);
		
		
		JMenuBar barraDeMenu = new JMenuBar();
		this.setJMenuBar(barraDeMenu);		
		
		//Grupo do Menu Arquivo
		JMenu arquivo = new JMenu("Arquivo");
		barraDeMenu.add(arquivo);
		
		JMenuItem novo = new JMenuItem("Novo        F1");
		arquivo.add(novo);
		novo.addActionListener(ouvinteNovo);
		
		JMenuItem abrir = new JMenuItem("Abrir         F2");
		arquivo.add(abrir);
		abrir.addActionListener(ouvinteAbrir);
		
		JMenuItem salvar = new JMenuItem("Salvar       F3");
		arquivo.add(salvar);
		salvar.addActionListener(ouvinteSalvar);
		
		JMenuItem sair = new JMenuItem("Sair           ESC");
		arquivo.add(sair);
		sair.addActionListener(ouvinteSair);		
		
		//Grupo do Menu Adicionar
		JMenu adicionar = new JMenu("Adicionar");
		barraDeMenu.add(adicionar);				
		
		JMenuItem novaObra = new JMenuItem("Nova Obra                  F5");
		adicionar.add(novaObra);
		novaObra.addActionListener(ouvinteAdicionarObra);
		
		JMenuItem novaAtividade = new JMenuItem("Nova Atividade          F6");
		adicionar.add(novaAtividade);
		novaAtividade.addActionListener(ouvinteAdicionarAtividade);
		
		JMenuItem novoFuncionario = new JMenuItem("Novo Funcionário     F7");
		adicionar.add(novoFuncionario);
		novoFuncionario.addActionListener(ouvinteAdicionarFuncionario);		
		
		//Grupo do Menu Listagem
		JMenu listagem = new JMenu("Listagem");
		barraDeMenu.add(listagem);		
		
		JMenuItem listarObras = new JMenuItem("Listar Obras");
		listagem.add(listarObras);
		listarObras.addActionListener(ouvinteListagem);
		
		JMenuItem listarAtividades = new JMenuItem("Listar Atividades");
		listagem.add(listarAtividades);
		listarAtividades.addActionListener(ouvinteListagem);
		
		JMenuItem listarFuncionarios = new JMenuItem("Listar Funcionários");
		listagem.add(listarFuncionarios);
		listarFuncionarios.addActionListener(ouvinteListagem);
		
		//Grupo do Menu Ajuda
		JMenu ajuda = new JMenu("Ajuda");
		barraDeMenu.add(ajuda);		
		
		JMenuItem sobre = new JMenuItem("Sobre         F12");
		ajuda.add(sobre);
		sobre.addActionListener(ouvinteSobre);
		
		if(gerente.getObras().isEmpty()){
			adicionar.getItem(1).setEnabled(false);
			listagem.getItem(0).setEnabled(false);
		}
		if(gerente.getAtividades().isEmpty()){
			listagem.getItem(1).setEnabled(false);
//			adicionar.getItem(2).setEnabled(false);
			
		}
		if(gerente.getFuncionarios().isEmpty()){
			listagem.getItem(2).setEnabled(false);
		}		
	}	
	
	public Tabela getObraTBL() {
		return obraTBL;
	}
	public void setObraTBL(Tabela obraTBL) {
		this.obraTBL = obraTBL;
	}
//	public Tabela getAtividadeTBL() {
//		return atividadeTBL;
//	}
//	public void setAtividadeTBL(Tabela atividadeTBL) {
//		this.atividadeTBL = atividadeTBL;
//	}
	public Tabela getFuncionarioTBL() {
		return funcionarioTBL;
	}
	public void setFuncionarioTBL(Tabela funcionarioTBL) {
		this.funcionarioTBL = funcionarioTBL;
	}	
	public AreaDeTexto getDescricaoCurtaADT() {
		return descricaoCurtaADT;
	}
	public void setDescricaoCurtaADT(AreaDeTexto descricaoCurtaADT) {
		this.descricaoCurtaADT = descricaoCurtaADT;
	}
	public Gerente getGerente() {
		return gerente;
	}
	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}
	public Etiqueta getValorOrçamentoETQ() {
		return valorOrçamentoETQ;
	}
	public void setValorOrçamentoETQ(Etiqueta valorOrçamentoETQ) {
		this.valorOrçamentoETQ = valorOrçamentoETQ;
	}
	public Etiqueta getValorGastosETQ() {
		return valorGastosETQ;
	}
	public void setValorGastosETQ(Etiqueta valorGastosETQ) {
		this.valorGastosETQ = valorGastosETQ;
	}
	public Etiqueta getValorAcrescimosETQ() {
		return valorAcrescimosETQ;
	}
	public void setValorAcrescimosETQ(Etiqueta valorAcrescimosETQ) {
		this.valorAcrescimosETQ = valorAcrescimosETQ;
	}
	public Etiqueta getValorrestanteETQ() {
		return valorrestanteETQ;
	}
	public void setValorrestanteETQ(Etiqueta valorrestanteETQ) {
		this.valorrestanteETQ = valorrestanteETQ;
	}	
	public Etiqueta getDescricaoCutaETQ() {
		return descricaoCutaETQ;
	}
	public Etiqueta getAcompanhamentoSemanalETQ() {
		return acompanhamentoSemanalETQ;
	}
	public void setDescricaoCutaETQ(Etiqueta descricaoCutaETQ) {
		this.descricaoCutaETQ = descricaoCutaETQ;
	}
	public void setAcompanhamentoSemanalETQ(Etiqueta acompanhamentoSemanalETQ) {
		this.acompanhamentoSemanalETQ = acompanhamentoSemanalETQ;
	}
	public Botao getSalvarBT() {
		return salvarBT;
	}
	public Botao getComentarioBT() {
		return comentarioBT;
	}
	public void setSalvarBT(Botao salvarBT) {
		this.salvarBT = salvarBT;
	}
	public void setComentarioBT(Botao comentarioBT) {
		this.comentarioBT = comentarioBT;
	}
	public CheckBox getDomCBP() {
		return domCBP;
	}
	public CheckBox getSegCBP() {
		return segCBP;
	}
	public CheckBox getTerCBP() {
		return terCBP;
	}
	public CheckBox getQuaCBP() {
		return quaCBP;
	}
	public CheckBox getQuiCBP() {
		return quiCBP;
	}
	public CheckBox getSexCBP() {
		return sexCBP;
	}
	public CheckBox getSabCBP() {
		return sabCBP;
	}
	public CheckBox getDomCBE() {
		return domCBE;
	}
	public CheckBox getSegCBE() {
		return segCBE;
	}
	public CheckBox getTerCBE() {
		return terCBE;
	}
	public CheckBox getQuaCBE() {
		return quaCBE;
	}
	public CheckBox getQuiCBE() {
		return quiCBE;
	}
	public CheckBox getSexCBE() {
		return sexCBE;
	}
	public CheckBox getSabCBE() {
		return sabCBE;
	}
	public void setDomCBP(CheckBox domCBP) {
		this.domCBP = domCBP;
	}
	public void setSegCBP(CheckBox segCBP) {
		this.segCBP = segCBP;
	}
	public void setTerCBP(CheckBox terCBP) {
		this.terCBP = terCBP;
	}
	public void setQuaCBP(CheckBox quaCBP) {
		this.quaCBP = quaCBP;
	}
	public void setQuiCBP(CheckBox quiCBP) {
		this.quiCBP = quiCBP;
	}
	public void setSexCBP(CheckBox sexCBP) {
		this.sexCBP = sexCBP;
	}
	public void setSabCBP(CheckBox sabCBP) {
		this.sabCBP = sabCBP;
	}
	public void setDomCBE(CheckBox domCBE) {
		this.domCBE = domCBE;
	}
	public void setSegCBE(CheckBox segCBE) {
		this.segCBE = segCBE;
	}
	public void setTerCBE(CheckBox terCBE) {
		this.terCBE = terCBE;
	}
	public void setQuaCBE(CheckBox quaCBE) {
		this.quaCBE = quaCBE;
	}
	public void setQuiCBE(CheckBox quiCBE) {
		this.quiCBE = quiCBE;
	}
	public void setSexCBE(CheckBox sexCBE) {
		this.sexCBE = sexCBE;
	}
	public void setSabCBE(CheckBox sabCBE) {
		this.sabCBE = sabCBE;
	}
	public Etiqueta getPlanejadosETQ() {
		return planejadosETQ;
	}
	public Etiqueta getExecutadosETQ() {
		return executadosETQ;
	}
	public void setPlanejadosETQ(Etiqueta planejadosETQ) {
		this.planejadosETQ = planejadosETQ;
	}
	public void setExecutadosETQ(Etiqueta executadosETQ) {
		this.executadosETQ = executadosETQ;
	}
	public ArrayList<CheckBox> getListaDeCheckbox() {
		return listaDeCheckbox;
	}
	public void setListaDeCheckbox(ArrayList<CheckBox> listaDeCheckbox) {
		this.listaDeCheckbox = listaDeCheckbox;
	}

	//	public Arvore getArvore() {
//		return arvore;
//	}
//	public void setArvore(Arvore arvore) {
//		this.arvore = arvore;
//	}
//	public ModeloDeArvore getModeloDeArvore() {
//		return modeloDeArvore;
//	}
//	public void setModeloDeArvore(ModeloDeArvore modeloDeArvore) {
//		this.modeloDeArvore = modeloDeArvore;
//	}
	public Painel getPainelDaArvore() {
		return painelDaArvore;
	}
	public void setPainelDaArvore(Painel painelDaArvore) {
		this.painelDaArvore = painelDaArvore;
	}
	public OuvinteDeSelecaoDaArvoreJanelaPrincipal getOuvinteDeSelecao() {
		return ouvinteDeSelecao;
	}
	public void setOuvinteDeSelecao(OuvinteDeSelecaoDaArvoreJanelaPrincipal ouvinteDeSelecao) {
		this.ouvinteDeSelecao = ouvinteDeSelecao;
	}	
	
//	public AreaDeTexto getDescricaoLongaADT() {
//		return descricaoLongaADT;
//	}
//	public void setDescricaoLongaADT(AreaDeTexto descricaoLongaADT) {
//		this.descricaoLongaADT = descricaoLongaADT;
//	}
}