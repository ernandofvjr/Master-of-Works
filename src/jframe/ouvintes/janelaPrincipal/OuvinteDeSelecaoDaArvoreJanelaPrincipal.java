package jframe.ouvintes.janelaPrincipal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;

import classes.Atividade;
import classes.Gerente;
import jframe.JanelaPrincipal;
import jframe.componentes.CheckBox;

public class OuvinteDeSelecaoDaArvoreJanelaPrincipal implements TreeSelectionListener{
	
	private JanelaPrincipal janela;
	
	public OuvinteDeSelecaoDaArvoreJanelaPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {				
		
		Gerente gerente = Gerente.obterInstancia();		
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		Atividade atividade = new Atividade();
		
		modelo.addColumn("FUNCIONÁRIOS");
		
//		float[] hsbval = new float[3];
//		Color.RGBtoHSB(230, 240, 255, hsbval);
//		janela.getPainelDaArvore().getRolagem().setBackground(Color.getHSBColor(hsbval[0], hsbval[1], hsbval[2]));
		
		Object objeto = janela.getPainelDaArvore().getRolagem().getArvore().getLastSelectedPathComponent();
		
		if(objeto instanceof Atividade){
			String nomeDaObra = janela.getObraTBL().getValueAt(janela.getObraTBL().getSelectedRow(), 0).toString();
			String nomeDaAtividade = null;
			try {
				nomeDaAtividade = janela.getPainelDaArvore().getRolagem().getArvore().getLastSelectedPathComponent().toString();
			} catch (Exception e2) {
				e2.getMessage();
			}		
			
			if(nomeDaAtividade != null) {
				
				atividade = gerente.buscarAtividade(nomeDaObra, nomeDaAtividade);	
				
				if(atividade != null) {			
					
					janela.getComentarioBT().setEnabled(true);				
					janela.getAcompanhamentoSemanalETQ().setEnabled(true);
					janela.getDescricaoCutaETQ().setEnabled(true);
					janela.getExecutadosETQ().setEnabled(true);
					janela.getPlanejadosETQ().setEnabled(true);		
					
					for (CheckBox checkBox : janela.getListaDeCheckbox()) {					
						checkBox.setEnabled(false);
						checkBox.setSelected(false);					
					}			
					
					if(atividade.isBotaoSalvar() == true) {
						janela.getSalvarBT().setForeground(Color.BLACK);
						janela.getSalvarBT().setText("Salvar Atividade");
						janela.getSalvarBT().setEnabled(true);
						tornarEditavel(atividade, true, true);	
						
					}
					
					else {
						janela.getSalvarBT().setForeground(Color.RED);
						janela.getSalvarBT().setText("Reabrir Atividade");
						janela.getSalvarBT().setEnabled(true);
						tornarEditavel(atividade, false, true);
					}		
					
					janela.getDescricaoCurtaADT().setText(atividade.getDescricaoCurta());
					janela.getDescricaoCurtaADT().setCaretPosition(0);				
					janela.getFuncionarioTBL().setModel(janela.getFuncionarioTBL().atualizarTabela("funcionario", null, atividade, null, null, null));
				
				}		
			}
		}
		else {
			for (CheckBox checkBox : janela.getListaDeCheckbox()) {
				checkBox.setEnabled(false);
				checkBox.setSelected(false);
			}
			
			janela.getSalvarBT().setText("Salvar Atividade");
			janela.getSalvarBT().setForeground(Color.BLACK);
			janela.getSalvarBT().setEnabled(false);
			janela.getComentarioBT().setEnabled(false);
			
			janela.getAcompanhamentoSemanalETQ().setEnabled(false);
			janela.getDescricaoCutaETQ().setEnabled(false);
			janela.getPlanejadosETQ().setEnabled(false);
			janela.getExecutadosETQ().setEnabled(false);
		}
		
				
	}
	public void tornarEditavel(Atividade atividade, boolean enable, boolean select) {
		ArrayList<Integer> diasDaSemana = verificarDiasDaSemana(atividade);	
		for (Integer integer : diasDaSemana) {
			
			switch (integer) {
			case 1:
				janela.getDomCBP().setEnabled(enable);
				janela.getDomCBE().setEnabled(enable);
				if(atividade.getDiasDaSemanaPlanejado()[0] == true) {
					janela.getDomCBP().setSelected(select);					
				}
				if(atividade.getDiasDaSemanaExecutado()[0] == true) {
					janela.getDomCBE().setSelected(select);
				}
				break;
			case 2:
				janela.getSegCBP().setEnabled(enable);
				janela.getSegCBE().setEnabled(enable);
				if(atividade.getDiasDaSemanaPlanejado()[1] == true) {
					janela.getSegCBP().setSelected(select);
				}
				if(atividade.getDiasDaSemanaExecutado()[1] == true) {
					janela.getSegCBE().setSelected(select);
				}
				break;
			case 3:
				janela.getTerCBP().setEnabled(enable);
				janela.getTerCBE().setEnabled(enable);
				if(atividade.getDiasDaSemanaPlanejado()[2] == true) {
					janela.getTerCBP().setSelected(select);
				}
				if(atividade.getDiasDaSemanaExecutado()[2] == true) {
					janela.getTerCBE().setSelected(select);
				}
				break;
			case 4:
				janela.getQuaCBP().setEnabled(enable);
				janela.getQuaCBE().setEnabled(enable);
				if(atividade.getDiasDaSemanaPlanejado()[3] == true) {
					janela.getQuaCBP().setSelected(select);
				}
				if(atividade.getDiasDaSemanaExecutado()[3] == true) {
					janela.getQuaCBE().setSelected(select);
				}
				break;
			case 5:
				janela.getQuiCBP().setEnabled(enable);
				janela.getQuiCBE().setEnabled(enable);
				if(atividade.getDiasDaSemanaPlanejado()[4] == true) {
					janela.getQuiCBP().setSelected(select);
				}
				if(atividade.getDiasDaSemanaExecutado()[4] == true) {
					janela.getQuiCBE().setSelected(select);
				}
				break;
			case 6:
				janela.getSexCBP().setEnabled(enable);
				janela.getSexCBE().setEnabled(enable);
				if(atividade.getDiasDaSemanaPlanejado()[5] == true) {
					janela.getSexCBP().setSelected(select);
				}
				if(atividade.getDiasDaSemanaExecutado()[5] == true) {
					janela.getSexCBE().setSelected(select);
				}
				break;
			case 7:
				janela.getSabCBP().setEnabled(enable);
				janela.getSabCBE().setEnabled(enable);
				if(atividade.getDiasDaSemanaPlanejado()[6] == true) {
					janela.getSabCBP().setSelected(select);
				}
				if(atividade.getDiasDaSemanaExecutado()[6] == true) {
					janela.getSabCBE().setSelected(select);
				}
				break;
			default:
				break;
			}
		}
	}
	public ArrayList<Integer> verificarDiasDaSemana(Atividade atividade) {		
		
			
		Date dataInicial = atividade.getDataDeCriacao();
		Date dataFinal = atividade.getDataDeConclusao();
		
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(dataInicial);
		calendario.add(Calendar.DATE, -1);
		dataInicial = calendario.getTime();	
		
//		calendario.setTime(dataFinal);
//		calendario.add(Calendar.DATE, +1);
//		dataFinal = calendario.getTime();	
		
		ArrayList<Integer> diasDaSemana = new ArrayList<Integer>();		
		ArrayList<Date> dias = criarLista(dataInicial, dataFinal);
		
		Calendar c = Calendar.getInstance();
		for (Date date : dias) {			
			c.setTime(date);
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			diasDaSemana.add(dayOfWeek);
		}		
		return diasDaSemana;
	}
	private ArrayList<Date> criarLista(Date dataInicial, Date dataFinal){
		ArrayList<Date> datas = new ArrayList<Date>();
		Date dataTemp = dataInicial;
		while(dataTemp.before(dataFinal)) {            	
			Calendar c = Calendar.getInstance();
			c.setTime(dataTemp);
			c.add(Calendar.DATE, 1);
			dataTemp = c.getTime();
			datas.add(dataTemp);
		}
		return datas;
	}
}
