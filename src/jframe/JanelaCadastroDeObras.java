package jframe;

import java.awt.Component;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import classes.Fonte;
import classes.Obra;
import jframe.componentes.Botao;
import jframe.componentes.CampoDeTexto;
import jframe.componentes.CampoDeTextoFormatado;
import jframe.componentes.Etiqueta;
import jframe.componentes.RadioBotao;
import jframe.ouvintes.OrdemDoFocoNosComponentesDaJanela;
import jframe.ouvintes.OuvinteDasJanelasDeNivelUm;
import jframe.ouvintes.OuvinteDoBotaoCancelar;
import jframe.ouvintes.janelaCadastroDeObras.OuvinteDoBotaoCadastrarObra;

public class JanelaCadastroDeObras extends JanelaPadrao {	
	
	private Obra obra;
	private CampoDeTexto orcamentoInicialCDT;
	private CampoDeTexto nomeDaObraCDT;	
	private CampoDeTexto cidadeCDT;
	private CampoDeTexto bairroCDT;
	private CampoDeTexto ruaCDT;
	private CampoDeTextoFormatado cepCDT;
	private CampoDeTexto numeroCDT;	
	private RadioBotao comercialRB;
	private RadioBotao residencialRB;
	private RadioBotao mistaRB;
	private OuvinteDasJanelasDeNivelUm ouvinteDaJanela = new OuvinteDasJanelasDeNivelUm(this);
	
	public JanelaCadastroDeObras(){		
		setTitle("Cadastro de Obra");
		addWindowListener(ouvinteDaJanela);
		
		adicionarEtiqueta();
		adicionarBotao();
		adicionarCampoDeTexto();
		adicionarRadioBotao();
		
		setVisible(true);
		
		ArrayList<Component> ordem = new ArrayList<Component>(9);
		
		ordem.add(nomeDaObraCDT);
		ordem.add(orcamentoInicialCDT);
		ordem.add(comercialRB);
		ordem.add(residencialRB);
		ordem.add(cidadeCDT);
		ordem.add(bairroCDT);
		ordem.add(ruaCDT);
		ordem.add(cepCDT);
		ordem.add(numeroCDT);
		
		OrdemDoFocoNosComponentesDaJanela ordemDoFoco = new OrdemDoFocoNosComponentesDaJanela(ordem);
		
		setFocusTraversalPolicy(ordemDoFoco);		
	}
	//construtor que recebe uma obra para a janela de detalhe
	public JanelaCadastroDeObras(Obra obra){
		this.obra = obra;
		setTitle("Edição de Obra");	
		addWindowListener(ouvinteDaJanela);
		adicionarEtiqueta();
		adicionarBotaoEditar();
		adicionarCampoDeTexto();
		adicionarRadioBotao();
		adicionarInforacoesObra();
		setVisible(true);	
		requestFocusInWindow();
	}
	private void adicionarEtiqueta(){		
		
		Etiqueta cadastroDeObraETQ = new Etiqueta(this, "", Fonte.personalizadaNegrito(30), null, 302, 41, 300, 25);		
		
		if(obra == null){
			cadastroDeObraETQ.setText("Cadastro De Obra");
		}
		else{
			cadastroDeObraETQ.setText("Detalhes da Obra");
		}
		
		Etiqueta obraETQ = new Etiqueta(this, "Obra", Fonte.tituloNegrito(), null, 210, 100, 63, 25);
		Etiqueta nomeDaObra = new Etiqueta(this, "Nome da Obra*", Fonte.textoNormal(), null, 125, 178, 113, 20);
		Etiqueta orcamentoInicialETQ = new Etiqueta(this, "Orçamento inicial   R$*", Fonte.textoNormal(), null, 125, 210, 153, 20);
		
		Etiqueta tipoDeConstrucaoETQ = new Etiqueta(this, "Tipo de construção*", Fonte.textoNormal(), null, 125, 244, 130, 20);
		
		Etiqueta enderecoETQ = new Etiqueta(this, "Endereço", Fonte.tituloNegrito(), null, 588, 100, 103, 25);
		Etiqueta cidadeETQ = new Etiqueta(this, "Cidade*", Fonte.textoNormal(), null, 512, 178, 93, 25);
		Etiqueta bairroETQ = new Etiqueta(this, "Bairro*", Fonte.textoNormal(), null, 512, 210, 93, 25);
		Etiqueta ruaETQ = new Etiqueta(this, "Rua*", Fonte.textoNormal(), null, 512, 244, 33, 25);
		Etiqueta cepETQ = new Etiqueta(this, "CEP*", Fonte.textoNormal(), "Código de Endereçamento Postal", 512, 276, 35, 25);
		Etiqueta numeroETQ = new Etiqueta(this, "Nº*", Fonte.textoNormal(), "Número", 697, 276, 28, 25);		
	}
	private void adicionarCampoDeTexto(){		
		orcamentoInicialCDT = new CampoDeTexto(this, 270, 210, 110, 23);	
		nomeDaObraCDT = new CampoDeTexto(this, 230, 178, 150, 23);
		cidadeCDT = new CampoDeTexto(this, 570, 178, 200, 23);
		bairroCDT = new CampoDeTexto(this, 570, 210, 200, 23);
		ruaCDT = new CampoDeTexto(this, 570, 244, 200, 23);
		try {
			cepCDT = new CampoDeTextoFormatado(this, 570, 276, 100, 23,"#####-###");
		} catch (ParseException e) {}
		numeroCDT = new CampoDeTexto(this, 720, 276, 50, 23);	
	}
	private void adicionarBotaoEditar() {
		ImageIcon botaoVoltarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoVoltar.png"));
		OuvinteDoBotaoCancelar ouvinteCancelar = new OuvinteDoBotaoCancelar(this);
		
		Botao voltarBt = new Botao(this, Fonte.tituloNegrito(), ouvinteCancelar, "Voltar", 528, 494, 200, 50, botaoVoltarICO);		
	}
	private void adicionarBotao(){		
		ImageIcon botaoCadatrarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCadastrar.png"));
		ImageIcon botaoCancelarICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCancelar.png"));
		
		OuvinteDoBotaoCadastrarObra ouvinte = new OuvinteDoBotaoCadastrarObra(this);
		OuvinteDoBotaoCancelar ouvinteCancelar = new OuvinteDoBotaoCancelar(this);
		
		Botao cadastrarBT = new Botao(this, Fonte.tituloNegrito(),ouvinte, "Cadastrar", 144, 494, 200, 50, botaoCadatrarICO);		
		Botao cancelarBT = new Botao(this, Fonte.tituloNegrito(),ouvinteCancelar, "Cancelar", 528, 494, 200, 50, botaoCancelarICO);		
	}
	private void adicionarRadioBotao(){		
		
		
		ButtonGroup grupo = new ButtonGroup();	
		
		
		
		comercialRB = new RadioBotao(this, 270, 244, 100, 18, "Comercial", grupo, null);
		residencialRB = new RadioBotao(this, 270, 270, 125, 18,"Residencial" , grupo, null);	
		mistaRB = new RadioBotao(this, 270, 295, 100, 18, "Mista", grupo, null);
		
//		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
//		mistaRB.setCursor(cursor);
//		residencialRB.setCursor(cursor);
//		comercialRB.setCursor(cursor);
	}
	private void adicionarInforacoesObra(){
		
		orcamentoInicialCDT.setText(String.valueOf(obra.getOrcamentoInicial()));
		orcamentoInicialCDT.setFocusable(false);
		nomeDaObraCDT.setText(obra.getNome());
		nomeDaObraCDT.setFocusable(false);
		cidadeCDT.setText(obra.getEndereco().getCidade());
		cidadeCDT.setFocusable(false);
		bairroCDT.setText(obra.getEndereco().getBairro());
		bairroCDT.setFocusable(false);
		ruaCDT.setText(obra.getEndereco().getRua());
		ruaCDT.setFocusable(false);
		cepCDT.setText(obra.getEndereco().getCEP());
		cepCDT.setFocusable(false);
		numeroCDT.setText(String.valueOf(obra.getEndereco().getNumero()));
		numeroCDT.setFocusable(false);
		if(obra.getTipoDeConstrucao().equals("C")){
			comercialRB.setSelected(true);
		}
		else if(obra.getTipoDeConstrucao().equals("R")){
			residencialRB.setSelected(true);
		}
		else if(obra.getTipoDeConstrucao().equals("M")) {
			mistaRB.setSelected(true);
		}
		comercialRB.setEnabled(false);
		residencialRB.setEnabled(false);;
		mistaRB.setEnabled(false);
	}
	
	public CampoDeTexto getOrcamentoInicialCDT() {
		return orcamentoInicialCDT;
	}
	public void setOrcamentoInicialCDT(CampoDeTexto orcamentoInicialCDT) {
		this.orcamentoInicialCDT = orcamentoInicialCDT;
	}
	public CampoDeTexto getNomeDaObraCDT() {
		return nomeDaObraCDT;
	}
	public void setNomeDaObraCDT(CampoDeTexto nomeDaObraCDT) {
		this.nomeDaObraCDT = nomeDaObraCDT;
	}
	public CampoDeTexto getCidadeCDT() {
		return cidadeCDT;
	}
	public void setCidadeCDT(CampoDeTexto cidadeCDT) {
		this.cidadeCDT = cidadeCDT;
	}
	public CampoDeTexto getBairroCDT() {
		return bairroCDT;
	}
	public void setBairroCDT(CampoDeTexto bairroCDT) {
		this.bairroCDT = bairroCDT;
	}
	public CampoDeTexto getRuaCDT() {
		return ruaCDT;
	}
	public void setRuaCDT(CampoDeTexto ruaCDT) {
		this.ruaCDT = ruaCDT;
	}
	public CampoDeTextoFormatado getCepCDT() {
		return cepCDT;
	}
	public void setCepCDT(CampoDeTextoFormatado cepCDT) {
		this.cepCDT = cepCDT;
	}
	public CampoDeTexto getNumeroCDT() {
		return numeroCDT;
	}
	public void setNumeroCDT(CampoDeTexto numeroCDT) {
		this.numeroCDT = numeroCDT;
	}
	public RadioBotao getComercialRB() {
		return comercialRB;
	}
	public void setComercialRB(RadioBotao comercialRB) {
		this.comercialRB = comercialRB;
	}
	public RadioBotao getResidencialRB() {
		return residencialRB;
	}
	public void setResidencialRB(RadioBotao residencialRB) {
		this.residencialRB = residencialRB;
	}	
	public RadioBotao getMistaRB() {
		return mistaRB;
	}
	public void setMistaRB(RadioBotao mistaRB) {
		this.mistaRB = mistaRB;
	}
	public Obra getObra() {
		return obra;
	}
	public void setObra(Obra obra) {
		this.obra = obra;
	}	
	
}