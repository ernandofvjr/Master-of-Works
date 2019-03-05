package jframe.ouvintes.janelaDeOrcamentoDeObra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import classes.Acrescimo;
import classes.Gasto;
import classes.Gerente;
import classes.PersistenciaGerente;
import classes.Validacao;
import jframe.JanelaDeOrcamentoDaObra;
import jframe.excecoes.validacao.DescricaoInvalidaException;
import jframe.excecoes.validacao.NomeInvalidoException;
import jframe.excecoes.validacao.OrcamentoInvalidoException;
import jframe.excecoes.validacao.OrcamentoTipoInvalidoException;

public class OuvinteDoBotaoConfirmarOrcamento implements ActionListener {

	private JanelaDeOrcamentoDaObra janela;
	
	public OuvinteDoBotaoConfirmarOrcamento(JanelaDeOrcamentoDaObra janela) {
		this.janela = janela;
	}	
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		Gerente gerente = Gerente.obterInstancia();
		Calendar calendario = Calendar.getInstance();
		Date dataDeCadastro = calendario.getTime();
		String valor = validacao.validarDinheiro(janela.getValorCDT().getText());
		String descricao = janela.getDescricaoADT().getText();
		String nome = janela.getNomeCDT().getText();
		
		try {
			validacao.validarOrcamento(nome, valor, descricao);
			
			if(janela.getAcrescimoRB().isSelected()){
				Acrescimo acrescimo	= new Acrescimo(nome, dataDeCadastro, Double.parseDouble(valor), descricao);			
				janela.getObra().getAcrescimos().add(acrescimo);
			}
			else if(janela.getGastoRB().isSelected()){
				Gasto gasto = new Gasto(nome, dataDeCadastro, Double.parseDouble(valor), descricao);
				janela.getObra().getGastos().add(gasto);
			}
			else {
				throw new OrcamentoTipoInvalidoException();
			}
			PersistenciaGerente persistencia = new PersistenciaGerente();
			persistencia.salvarGerente(gerente);
			
			janela.getValorCDT().setText("");
			janela.getDescricaoADT().setText("");
			
			if(janela.getEscolherAcrescimoRB().isSelected()){
				janela.getTabelaAcrescimoEGastoTBL().setModel(janela.getTabelaAcrescimoEGastoTBL().atualizarTabela("acréscimo", janela.getObra(), null, null, null, null));
			}
			else{
				janela.getTabelaAcrescimoEGastoTBL().setModel(janela.getTabelaAcrescimoEGastoTBL().atualizarTabela("gasto", janela.getObra(), null, null, null, null));
			}
			
			janela.atualizarEtiquetas();	
		} catch (NomeInvalidoException | OrcamentoInvalidoException | DescricaoInvalidaException | OrcamentoTipoInvalidoException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}		
	}
}