package jframe.ouvintes.janelaPrincipal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import classes.Atividade;
import classes.Gerente;
import classes.PersistenciaGerente;
import jframe.JanelaPrincipal;

public class OuvinteDoBotaoSalvarJanelaPrincipal implements ActionListener {
	private JanelaPrincipal janela;
	public OuvinteDoBotaoSalvarJanelaPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		
		Gerente gerente = Gerente.obterInstancia();	
		
		Atividade atividade = new Atividade();
		
		String nomeDaObra = janela.getObraTBL().getValueAt(janela.getObraTBL().getSelectedRow(), 0).toString();
		String nomeDaAtividade = null;
		try {
			nomeDaAtividade = janela.getPainelDaArvore().getRolagem().getArvore().getLastSelectedPathComponent().toString();
		} catch (Exception e2) {
			e2.getMessage();
		}
		
		atividade = gerente.buscarAtividade(nomeDaObra, nomeDaAtividade);
		if(atividade != null) {
			OuvinteDeSelecaoDaArvoreJanelaPrincipal ouvinte = new OuvinteDeSelecaoDaArvoreJanelaPrincipal(janela);
			if(atividade.isBotaoSalvar() == true) {
				//Domingo
				if(janela.getDomCBP().isSelected()) {
					atividade.getDiasDaSemanaPlanejado()[0] = true;
				}
				else{
					atividade.getDiasDaSemanaPlanejado()[0] = false;
				}
				
				if(janela.getDomCBE().isSelected()) {
					atividade.getDiasDaSemanaExecutado()[0] = true;
				}
				else{
					atividade.getDiasDaSemanaExecutado()[0] = false;
				}
				
				//Segunda
				if(janela.getSegCBP().isSelected()) {
					atividade.getDiasDaSemanaPlanejado()[1] = true;
				}
				else{
					atividade.getDiasDaSemanaPlanejado()[1] = false;
				}
				
				if(janela.getSegCBE().isSelected()) {
					atividade.getDiasDaSemanaExecutado()[1] = true;
				}
				else{
					atividade.getDiasDaSemanaExecutado()[1] = false;
				}
				
				//Terça
				if(janela.getTerCBP().isSelected()) {
					atividade.getDiasDaSemanaPlanejado()[2] = true;
				}
				else{
					atividade.getDiasDaSemanaPlanejado()[2] = false;
				}
				
				if(janela.getTerCBE().isSelected()) {
					atividade.getDiasDaSemanaExecutado()[2] = true;
				}
				else{
					atividade.getDiasDaSemanaExecutado()[2] = false;
				}
				
				//Quarta
				if(janela.getQuaCBP().isSelected()) {
					atividade.getDiasDaSemanaPlanejado()[3] = true;
				}
				else{
					atividade.getDiasDaSemanaPlanejado()[3] = false;
				}
				
				if(janela.getQuaCBE().isSelected()) {
					atividade.getDiasDaSemanaExecutado()[3] = true;
				}
				else{
					atividade.getDiasDaSemanaExecutado()[3] = false;
				}
				
				//Quinta
				if(janela.getQuiCBP().isSelected()) {
					atividade.getDiasDaSemanaPlanejado()[4] = true;
				}
				else{
					atividade.getDiasDaSemanaPlanejado()[4] = false;
				}
				
				if(janela.getQuiCBE().isSelected()) {
					atividade.getDiasDaSemanaExecutado()[4] = true;
				}
				else{
					atividade.getDiasDaSemanaExecutado()[4] = false;
				}
				
				//Sexta
				if(janela.getSexCBP().isSelected()) {
					atividade.getDiasDaSemanaPlanejado()[5] = true;
				}
				else{
					atividade.getDiasDaSemanaPlanejado()[5] = false;
				}
				
				if(janela.getSexCBE().isSelected()) {
					atividade.getDiasDaSemanaExecutado()[5] = true;
				}
				else{
					atividade.getDiasDaSemanaExecutado()[5] = false;
				}
				
				//Sabado
				if(janela.getSabCBP().isSelected()) {
					atividade.getDiasDaSemanaPlanejado()[6] = true;
				}
				else{
					atividade.getDiasDaSemanaPlanejado()[6] = false;
				}	
				
				if(janela.getSabCBE().isSelected()) {
					atividade.getDiasDaSemanaExecutado()[6] = true;
				}
				else{
					atividade.getDiasDaSemanaExecutado()[6] = false;
				}
				atividade.setBotaoSalvar(false);			
				
				
				ouvinte.tornarEditavel(atividade, false, true);
				
				janela.getSalvarBT().setForeground(Color.RED);
				janela.getSalvarBT().setText("Reabrir Atividade");
				
				PersistenciaGerente persistencia = new PersistenciaGerente();				
				persistencia.salvarGerente(gerente);
				
				JOptionPane.showMessageDialog(janela, "Planejamento semanal salvo com sucesso!");
			}
			else {
				ouvinte.tornarEditavel(atividade, true, true);
				janela.getSalvarBT().setForeground(Color.BLACK);
				janela.getSalvarBT().setText("Salvar Atividade");
				
				atividade.setBotaoSalvar(true);
				
			}			
		}		
	}
}