package jframe.ouvintes.janelaCadastroDeObras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import classes.Endereco;
import classes.Gerente;
import classes.Obra;
import classes.PersistenciaGerente;
import classes.Validacao;
import jframe.JanelaCadastroDeObras;
import jframe.JanelaPrincipal;
import jframe.excecoes.validacao.EnderecoBairroInvalidoException;
import jframe.excecoes.validacao.EnderecoCEPInvalidoException;
import jframe.excecoes.validacao.EnderecoCidadeInvalidaException;
import jframe.excecoes.validacao.EnderecoNumeroInvalidoException;
import jframe.excecoes.validacao.EnderecoRuaInvalidaException;
import jframe.excecoes.validacao.NomeInvalidoException;
import jframe.excecoes.validacao.ObraJaCadastradaException;
import jframe.excecoes.validacao.ObraOrcamentoInicialInvalidoException;
import jframe.excecoes.validacao.ObraTipoInvalidoException;

public class OuvinteDoBotaoCadastrarObra implements ActionListener {
	
	private JanelaCadastroDeObras janela;
	
	public OuvinteDoBotaoCadastrarObra(JanelaCadastroDeObras janela){
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent e) {
		
		Validacao validacao = new Validacao();
		
		String tipo = null;
		String nome = janela.getNomeDaObraCDT().getText();
		String orcamentoInicial = validacao.validarDinheiro(janela.getOrcamentoInicialCDT().getText());
		String rua = janela.getRuaCDT().getText();
		String numero = janela.getNumeroCDT().getText();
		String CEP = janela.getCepCDT().getText();
		String cidade = janela.getCidadeCDT().getText();
		String bairro = janela.getBairroCDT().getText();
		
		if(janela.getComercialRB().isSelected()){
			tipo = "C";
		}
		else if(janela.getResidencialRB().isSelected()){
			tipo = "R";
		}
		else if(janela.getMistaRB().isSelected()) {
			tipo = "M";
		}
		
		try {
			validacao.validarObra(nome, orcamentoInicial, tipo, rua, numero, CEP, cidade, bairro);
		
			Endereco endereco = new Endereco(rua, Integer.parseInt(numero), CEP, cidade, bairro);
			
			Obra obra = new Obra(nome , Double.parseDouble(orcamentoInicial), endereco, tipo);
			
			validacao.validarObraDuplicada(obra);
			
			Gerente gerente = Gerente.obterInstancia();
			gerente.addObra(obra);
			PersistenciaGerente persistencia = new PersistenciaGerente();
			persistencia.salvarGerente(gerente);
			JOptionPane.showMessageDialog(this.janela, "Obra cadastrada com sucesso!");	
			janela.dispose();
			new JanelaPrincipal();
		
		} catch (NomeInvalidoException | ObraOrcamentoInicialInvalidoException | ObraTipoInvalidoException | EnderecoBairroInvalidoException | EnderecoCEPInvalidoException | EnderecoCidadeInvalidaException | EnderecoNumeroInvalidoException | EnderecoRuaInvalidaException | ObraJaCadastradaException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}
	}
	public JanelaCadastroDeObras getJanela() {		
		return janela;
	}

	public void setJanela(JanelaCadastroDeObras janela) {
		this.janela = janela;
	}	
}