package jframe;

import java.awt.Component;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import classes.Fonte;
import classes.Funcionario;
import classes.LeitorDeTxt;
import jframe.componentes.Botao;
import jframe.componentes.Calendario;
import jframe.componentes.CampoDeTexto;
import jframe.componentes.CampoDeTextoFormatado;
import jframe.componentes.ComboCaixa;
import jframe.componentes.Etiqueta;
import jframe.componentes.RadioBotao;
import jframe.componentes.Tabela;
import jframe.ouvintes.OrdemDoFocoNosComponentesDaJanela;
import jframe.ouvintes.OuvinteDasJanelasDeNivelUm;
import jframe.ouvintes.OuvinteDoBotaoCancelar;
import jframe.ouvintes.janelaCadatroDeFuncionarios.OuvinteDaTabelaObrasCadastrarFuncionario;
import jframe.ouvintes.janelaCadatroDeFuncionarios.OuvinteDoBotaoCadastrarFuncionario;
import jframe.ouvintes.janelaCadatroDeFuncionarios.OuvinteDoBotaoCalendarioFuncionario;
import jframe.ouvintes.janelaCadatroDeFuncionarios.OuvinteDoBotaoEditarFuncionarioJanela;

public class JanelaCadastroDeFuncionarios extends JanelaPadrao{
	
	private Funcionario funcionario;		
	private Tabela atividadeTBL;
	private Tabela obraTBL;	
	private CampoDeTexto tfNome;
	private CampoDeTexto tfEmail;
	private ComboCaixa tfFuncao;
	private CampoDeTexto tfSalario;
	private CampoDeTexto tfCidade;
	private CampoDeTexto tfRua;
	private CampoDeTexto tfNumero;
	private CampoDeTexto tfBairro;
	private CampoDeTextoFormatado tfIdade;
	private CampoDeTextoFormatado tfCPF;
	private CampoDeTextoFormatado tfTelefone;
	private CampoDeTextoFormatado tfCEP;
	private Calendario calendario;
	private RadioBotao rbMasculino;
	private RadioBotao rbFeminino;
	private  LeitorDeTxt leitor = new LeitorDeTxt();
	private String[] profissoes = leitor.lerArquivoDeProfissoes("profissoes");
	
	private OuvinteDasJanelasDeNivelUm ouvinteDaJanela = new OuvinteDasJanelasDeNivelUm(this);
	
	public JanelaCadastroDeFuncionarios(){		
		addWindowListener(ouvinteDaJanela);
		setTitle("Cadastro de Funcion�rio");
		
		adicionarEtiqueta();
		adicionarCampoDeTexto();
		adicionarBotao();
		adicionarTabela();
		adicionarEtiquetaTabela();
		adicionarComboBox();
		adicionarCalendario();
		setVisible(true);
		
		ArrayList<Component> ordem = new ArrayList<Component>(14);
		
		ordem.add(tfNome);
		ordem.add(tfCPF);
		ordem.add(tfIdade);
		ordem.add(rbMasculino);
		ordem.add(rbFeminino);
		ordem.add(tfFuncao);
		ordem.add(tfSalario);
		ordem.add(tfEmail);
		ordem.add(tfTelefone);
		ordem.add(tfCidade);
		ordem.add(tfBairro);
		ordem.add(tfRua);
		ordem.add(tfCEP);
		ordem.add(tfNumero);		
		
		OrdemDoFocoNosComponentesDaJanela ordemDoFoco = new OrdemDoFocoNosComponentesDaJanela(ordem);
		
		setFocusTraversalPolicy(ordemDoFoco);	
	}
	//construtor que recebe um funcionario para a janela de edi��o de funcion�rio
	public JanelaCadastroDeFuncionarios(Funcionario funcionario){		
		addWindowListener(ouvinteDaJanela);
		this.funcionario = funcionario;
		
		setTitle("Edi��o de Funcion�rio");
		
		adicionarEtiqueta();
		adicionarCalendario();
		adicionarCampoDeTexto();
		adicionarBotaoEditar();
		if(funcionario.isDemitido() == false)
			adicionarTabela();
		adicionarEtiquetaTabela();
		adicionarComboBox();
		adicionarInformacoesFuncionario();
		
		setVisible(true);
		requestFocusInWindow();
	}
	private void adicionarCalendario(){
		OuvinteDoBotaoCalendarioFuncionario ouvinte = new OuvinteDoBotaoCalendarioFuncionario(this);
		calendario = new Calendario(this, 205, 177, 150, 22, ouvinte);
	}
	private void adicionarBotaoEditar() {		
		ImageIcon botaoCancelarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCancelar.png"));
		ImageIcon botaoEditarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoEditar.png"));
		
		OuvinteDoBotaoEditarFuncionarioJanela ouvinteEditar = new OuvinteDoBotaoEditarFuncionarioJanela(this);
		OuvinteDoBotaoCancelar ouvinteCancelar = new OuvinteDoBotaoCancelar(this);
		if(funcionario.isDemitido() == false){
			Botao btEditar = new Botao(this, Fonte.tituloNegrito(), ouvinteEditar, "Editar", 144, 494, 200, 50, botaoEditarICO);	
		}
		Botao btCancelar = new Botao(this, Fonte.tituloNegrito(), ouvinteCancelar, "Cancelar", 528, 494, 200, 50, botaoCancelarICO);		
	}
	private void adicionarTabela(){		
		OuvinteDaTabelaObrasCadastrarFuncionario ouvinteObraTBL = new OuvinteDaTabelaObrasCadastrarFuncionario(this);

		obraTBL = new Tabela(this, ouvinteObraTBL, 525, 280, 150, 200, "obra", null);		
		atividadeTBL = new Tabela(this, null, 700, 280, 150, 200, "atividade", null);		
	}
	private void adicionarEtiqueta(){
		Etiqueta lbDados = new Etiqueta(this, "Cadastro de Funcion�rio", Fonte.personalizadaNegrito(30), "Dados do Funcion�rio", 280, 20, 375, 25);
		if(funcionario != null){
			lbDados.setText("Edi��o de Funcion�rio");
		}
		//Etiquetas relacionadas com a parte de dados do funcion�rio.		
		Etiqueta lbFuncionario = new Etiqueta(this, "Funcion�rio", Fonte.tituloNegrito(), null, 175, 64, 130, 25);
		Etiqueta lbNome = new Etiqueta(this, "Nome*", Fonte.textoNormal(), "Nome do Funcion�rio", 65, 110, 43, 17);
		Etiqueta lbIdade = new Etiqueta(this, "Idade", Fonte.textoNormal(), "Idade do Funcion�rio", 270, 145, 43, 17);
		Etiqueta lbDataDeNascimento = new Etiqueta(this, "Data de Nascimento*", Fonte.textoNormal(), "Data de Nascimento", 65, 180, 140, 17);
		Etiqueta lbCPF = new Etiqueta(this, "CPF*", Fonte.textoNormal(), "CPF do Funcion�rio", 65, 145, 36, 17);
		Etiqueta lbSexo = new Etiqueta(this, "Sexo*", Fonte.textoNormal(), "G�nero do Funcion�rio", 65, 215, 40, 17);
		Etiqueta lbFuncao = new Etiqueta(this, "Fun��o*", Fonte.textoNormal(), "Fun��o do Funcion�rio", 65, 285, 55, 17);
		Etiqueta lbSalario = new Etiqueta(this, "Sal�rio*", Fonte.textoNormal(), "Sal�rio do Funcion�rio", 65, 320, 55, 17);	
		Etiqueta lbEmail = new Etiqueta(this, "Email", Fonte.textoNormal(), "Email do Funcion�rio", 65, 355, 40, 17);
		Etiqueta lbTelefone = new Etiqueta(this, "Telefone", Fonte.textoNormal(), "Telefone do Funcion�rio", 65, 390, 65, 17);		
		
		//Etiquetas relacionadas com a parte de endere�o.
		Etiqueta lbEndereco = new Etiqueta(this, "Endere�o", Fonte.tituloNegrito(), "Endere�o do Funcion�rio",655 , 64, 100, 25);
		Etiqueta lbCidade = new Etiqueta(this, "Cidade*", Fonte.textoNormal(), "Cidade onde o funcion�rio mora", 525, 110, 53, 17);
		Etiqueta lbBairro = new Etiqueta(this, "Bairro*", Fonte.textoNormal(), "Bairro onde o funcion�rio mora", 525, 145, 55, 17);
		Etiqueta lbRua = new Etiqueta(this, "Rua*", Fonte.textoNormal(), "Rua onde o funcion�rio mora", 525, 180, 55, 17);
		Etiqueta lbCEP = new Etiqueta(this, "CEP*", Fonte.textoNormal(), "CEP de onde o funcion�rio mora", 525, 215, 35, 17);
		Etiqueta lbNumero = new Etiqueta(this, "Numero*", Fonte.textoNormal(), "Numero da casa do funcion�rio", 740, 215, 58, 17);
	}
	private void adicionarCampoDeTexto(){
		
		//Campos de texto relacionados com a parte de dados do funcion�rio
		tfNome = new CampoDeTexto(this, 130, 110, 225, 21);
		try {
			tfCPF = new CampoDeTextoFormatado(this, 130, 145, 120, 21,"###.###.###-##");
			tfIdade = new CampoDeTextoFormatado(this, 320, 145, 35, 21,"##", null);			
			tfTelefone = new CampoDeTextoFormatado(this, 130, 390, 120, 21,"(##)#####-####");
		} catch (ParseException e) {}
		tfIdade.setEditable(false);
		
		//Grupo de radio Buttons relacionados ao g�nero do funcion�rio
		ButtonGroup grupoRbSexo = new ButtonGroup();
		rbMasculino = new RadioBotao(this, 125, 215, 100, 14, "Masculino", grupoRbSexo, null);
		rbFeminino = new RadioBotao(this, 125, 250, 100, 14, "Feminino", grupoRbSexo, null);	
		
		//mais informa��es do funcion�rio

		tfSalario = new CampoDeTexto(this, 130, 320, 120, 21);
		tfEmail = new CampoDeTexto(this, 130, 355, 225, 21);
		
		//endere�o
		tfCidade = new CampoDeTexto(this, 580, 110, 270, 21);
		tfBairro = new CampoDeTexto(this, 580, 145, 270, 21);
		tfRua = new CampoDeTexto(this, 580, 180, 270, 21);
		try {
			tfCEP = new CampoDeTextoFormatado(this, 580, 215, 103, 21, "#####-###");
		} catch (ParseException e) {}
		tfNumero = new CampoDeTexto(this, 801, 215, 48, 21);

	}
	private void adicionarComboBox(){
		
		
		tfFuncao = new ComboCaixa(this, Fonte.textoNormal(),130, 285, 225, 21, profissoes);
	}
	private void adicionarBotao(){
		ImageIcon botaoCadastrarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCadastrar.png"));
		ImageIcon botaoCancelarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCancelar.png"));
		
		OuvinteDoBotaoCadastrarFuncionario ouvinteCadastrar = new OuvinteDoBotaoCadastrarFuncionario(this);
		OuvinteDoBotaoCancelar ouvinteCancelar = new OuvinteDoBotaoCancelar(this);
		
		Botao btCadastrar = new Botao(this, Fonte.tituloNegrito(), ouvinteCadastrar, "Cadastrar", 144, 494, 200, 50, botaoCadastrarICO);		
		Botao btCancelar = new Botao(this, Fonte.tituloNegrito(), ouvinteCancelar, "Cancelar", 528, 494, 200, 50, botaoCancelarICO);		
	}
	private void adicionarInformacoesFuncionario(){
//		Gerente gerente = Gerente.obterInstancia();
		tfNome.setText(funcionario.getNome());
		tfIdade.setText(String.valueOf(funcionario.getIdade()));
		tfCPF.setText(funcionario.getCPF());
		tfEmail.setText(funcionario.getEmail());
		tfTelefone.setText(funcionario.getTelefone());
		if(funcionario.getSexo().equals("M")){
			rbMasculino.setSelected(true);
		}
		else if(funcionario.getSexo().equals("F")){
			rbFeminino.setSelected(true);
		}
		for(int i = 0;i < profissoes.length;i++){
			if(funcionario.getFuncao().equals(profissoes[i])){
				tfFuncao.setSelectedIndex(i);
			}
		}
		
		
		tfSalario.setText(String.valueOf(funcionario.getSalario()));
		tfCidade.setText(funcionario.getEndereco().getCidade());
		tfRua.setText(funcionario.getEndereco().getRua());
		tfNumero.setText(String.valueOf(funcionario.getEndereco().getNumero()));
		tfCEP.setText(funcionario.getEndereco().getCEP());
		tfBairro.setText(funcionario.getEndereco().getBairro());
		
		if(funcionario.isDemitido() == true){
			tfNome.setFocusable(false);
			tfIdade.setFocusable(false);
			tfCPF.setFocusable(false);
			tfEmail.setFocusable(false);
			tfTelefone.setFocusable(false);
			rbMasculino.setEnabled(false);
			rbFeminino.setEnabled(false);
			tfFuncao.setEnabled(false);
			tfSalario.setFocusable(false);
			tfCidade.setFocusable(false);
			tfRua.setFocusable(false);
			tfNumero.setFocusable(false);
			tfCEP.setFocusable(false);
			tfBairro.setFocusable(false);
			
		}
		
	}
	private void adicionarEtiquetaTabela(){
		
		
		Etiqueta lbTabela = new Etiqueta(this, "Adicionar funcion�rio � atividade", Fonte.textoNormal(), "Escolha uma atividade na tabela", 400, 210, 350, 80);
		if(funcionario != null){
			lbTabela.setText("Alterar atividade do funcion�rio");
			if(funcionario.isDemitido() == true){
				lbTabela.setText("Funcion�rio Demitido");
			}
			else{
				Etiqueta lbTabelaBaixo = new Etiqueta(this, "Em branco caso o funcion�rio n�o esteja vinculado � nenhuma atividade", Fonte.textoNormal(), "", 400, 230, 480, 80);

			}
		}
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Tabela getAtividadeTBL() {
		return atividadeTBL;
	}
	public void setAtividadeTBL(Tabela atividadeTBL) {
		this.atividadeTBL = atividadeTBL;
	}
	public Tabela getObraTBL() {
		return obraTBL;
	}
	public void setObraTBL(Tabela obraTBL) {
		this.obraTBL = obraTBL;
	}
	public CampoDeTexto getTfNome() {
		return tfNome;
	}
	public void setTfNome(CampoDeTexto tfNome) {
		this.tfNome = tfNome;
	}
	public CampoDeTexto getTfEmail() {
		return tfEmail;
	}
	public void setTfEmail(CampoDeTexto tfEmail) {
		this.tfEmail = tfEmail;
	}
	public ComboCaixa getTfFuncao() {
		return tfFuncao;
	}
	public void setTfFuncao(ComboCaixa tfFuncao) {
		this.tfFuncao = tfFuncao;
	}
	public CampoDeTexto getTfSalario() {
		return tfSalario;
	}
	public void setTfSalario(CampoDeTexto tfSalario) {
		this.tfSalario = tfSalario;
	}
	public CampoDeTexto getTfCidade() {
		return tfCidade;
	}
	public void setTfCidade(CampoDeTexto tfCidade) {
		this.tfCidade = tfCidade;
	}
	public CampoDeTexto getTfRua() {
		return tfRua;
	}
	public void setTfRua(CampoDeTexto tfRua) {
		this.tfRua = tfRua;
	}
	public CampoDeTexto getTfNumero() {
		return tfNumero;
	}
	public void setTfNumero(CampoDeTexto tfNumero) {
		this.tfNumero = tfNumero;
	}
	public CampoDeTexto getTfBairro() {
		return tfBairro;
	}
	public void setTfBairro(CampoDeTexto tfBairro) {
		this.tfBairro = tfBairro;
	}
	public CampoDeTextoFormatado getTfIdade() {
		return tfIdade;
	}
	public void setTfIdade(CampoDeTextoFormatado tfIdade) {
		this.tfIdade = tfIdade;
	}
	public CampoDeTextoFormatado getTfCPF() {
		return tfCPF;
	}
	public void setTfCPF(CampoDeTextoFormatado tfCPF) {
		this.tfCPF = tfCPF;
	}
	public CampoDeTextoFormatado getTfTelefone() {
		return tfTelefone;
	}
	public void setTfTelefone(CampoDeTextoFormatado tfTelefone) {
		this.tfTelefone = tfTelefone;
	}
	public CampoDeTextoFormatado getTfCEP() {
		return tfCEP;
	}
	public void setTfCEP(CampoDeTextoFormatado tfCEP) {
		this.tfCEP = tfCEP;
	}
	public RadioBotao getRbMasculino() {
		return rbMasculino;
	}
	public void setRbMasculino(RadioBotao rbMasculino) {
		this.rbMasculino = rbMasculino;
	}
	public RadioBotao getRbFeminino() {
		return rbFeminino;
	}
	public void setRbFeminino(RadioBotao rbFeminino) {
		this.rbFeminino = rbFeminino;
	}
	public Calendario getCalendario() {
		return calendario;
	}
	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}
	public String[] getProfissoes() {
		return profissoes;
	}
	public void setProfissoes(String[] profissoes) {
		this.profissoes = profissoes;
	}
	
}