package jframe.ouvintes.janelaPrincipal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import classes.Atividade;
//import classes.Gerente;
import jframe.JanelaPrincipal;

public class OuvinteDaTabelaAtividadesPrincipal implements MouseListener{
	
	private JanelaPrincipal janela;		
	
	public OuvinteDaTabelaAtividadesPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}	
	public void mouseClicked(MouseEvent arg0) {
		
	}
	public void mouseEntered(MouseEvent arg0) {
		
	}
	public void mouseExited(MouseEvent arg0) {
		
		
	}
	public void mousePressed(MouseEvent arg0) {
		
		janela.getSalvarBT().setEnabled(true);
		janela.getComentarioBT().setEnabled(true);
		
		janela.getAcompanhamentoSemanalETQ().setEnabled(true);
		janela.getDescricaoCutaETQ().setEnabled(true);
		janela.getExecutadosETQ().setEnabled(true);
		janela.getPlanejadosETQ().setEnabled(true);
		
		//planejados
		janela.getDomCBP().setEnabled(false);
		janela.getDomCBP().setSelected(false);		
		janela.getSegCBP().setEnabled(false);
		janela.getSegCBP().setSelected(false);		
		janela.getTerCBP().setEnabled(false);
		janela.getTerCBP().setSelected(false);		
		janela.getQuaCBP().setEnabled(false);
		janela.getQuaCBP().setSelected(false);		
		janela.getQuiCBP().setEnabled(false);
		janela.getQuiCBP().setSelected(false);		
		janela.getSexCBP().setEnabled(false);
		janela.getSexCBP().setSelected(false);
		janela.getSabCBP().setEnabled(false);
		janela.getSabCBP().setSelected(false);
		
		//executados
		janela.getDomCBE().setEnabled(false);
		janela.getDomCBE().setSelected(false);		
		janela.getSegCBE().setEnabled(false);
		janela.getSegCBE().setSelected(false);		
		janela.getTerCBE().setEnabled(false);
		janela.getTerCBE().setSelected(false);		
		janela.getQuaCBE().setEnabled(false);
		janela.getQuaCBE().setSelected(false);		
		janela.getQuiCBE().setEnabled(false);
		janela.getQuiCBE().setSelected(false);		
		janela.getSexCBE().setEnabled(false);
		janela.getSexCBE().setSelected(false);		
		janela.getSabCBE().setEnabled(false);
		janela.getSabCBE().setSelected(false);
		
//		Gerente gerente = Gerente.obterInstancia();		
		
		DefaultTableModel modelo = new DefaultTableModel();
		
		Atividade atividade = new Atividade();
		
		modelo.addColumn("FUNCIONÁRIOS");
		
//		String nomeDaObra = janela.getObraTBL().getValueAt(janela.getObraTBL().getSelectedRow(), 0).toString();
//		String nomeDaAtividade = janela.getAtividadeTBL().getValueAt(janela.getAtividadeTBL().getSelectedRow(), 0).toString();
		
//		atividade = gerente.buscarAtividade(nomeDaObra, nomeDaAtividade);
		ArrayList<Integer> diasDaSemana = verificarDiasDaSemana(atividade);
		
		for (Integer integer : diasDaSemana) {
			switch (integer) {
			case 1:
				janela.getDomCBP().setEnabled(true);
				janela.getDomCBE().setEnabled(true);
				if(atividade.getDiasDaSemanaPlanejado()[0] == true) {
					janela.getDomCBP().setSelected(true);					
				}
				if(atividade.getDiasDaSemanaExecutado()[0] == true) {
					janela.getDomCBE().setSelected(true);
				}
				break;
			case 2:
				janela.getSegCBP().setEnabled(true);
				janela.getSegCBE().setEnabled(true);
				if(atividade.getDiasDaSemanaPlanejado()[1] == true) {
					janela.getSegCBP().setSelected(true);
				}
				if(atividade.getDiasDaSemanaExecutado()[1] == true) {
					janela.getSegCBE().setSelected(true);
				}
				break;
			case 3:
				janela.getTerCBP().setEnabled(true);
				janela.getTerCBE().setEnabled(true);
				if(atividade.getDiasDaSemanaPlanejado()[2] == true) {
					janela.getTerCBP().setSelected(true);
				}
				if(atividade.getDiasDaSemanaExecutado()[2] == true) {
					janela.getTerCBE().setSelected(true);
				}
				break;
			case 4:
				janela.getQuaCBP().setEnabled(true);
				janela.getQuaCBE().setEnabled(true);
				if(atividade.getDiasDaSemanaPlanejado()[3] == true) {
					janela.getQuaCBP().setSelected(true);
				}
				if(atividade.getDiasDaSemanaExecutado()[3] == true) {
					janela.getQuaCBE().setSelected(true);
				}
				break;
			case 5:
				janela.getQuiCBP().setEnabled(true);
				janela.getQuiCBE().setEnabled(true);
				if(atividade.getDiasDaSemanaPlanejado()[4] == true) {
					janela.getQuiCBP().setSelected(true);
				}
				if(atividade.getDiasDaSemanaExecutado()[4] == true) {
					janela.getQuiCBE().setSelected(true);
				}
				break;
			case 6:
				janela.getSexCBP().setEnabled(true);
				janela.getSexCBE().setEnabled(true);
				if(atividade.getDiasDaSemanaPlanejado()[5] == true) {
					janela.getSexCBP().setSelected(true);
				}
				if(atividade.getDiasDaSemanaExecutado()[5] == true) {
					janela.getSexCBE().setSelected(true);
				}
				break;
			case 7:
				janela.getSabCBP().setEnabled(true);
				janela.getSabCBE().setEnabled(true);
				if(atividade.getDiasDaSemanaPlanejado()[6] == true) {
					janela.getSabCBP().setSelected(true);
				}
				if(atividade.getDiasDaSemanaExecutado()[6] == true) {
					janela.getSabCBE().setSelected(true);
				}
				break;
			default:
				break;
			}
		}
		
		
		
		janela.getDescricaoCurtaADT().setText(atividade.getDescricaoCurta());
		janela.getDescricaoCurtaADT().setCaretPosition(0);			
		
//		janela.getDescricaoLongaADT().setText(atividade.getDescricaoLonga());
//		janela.getDescricaoLongaADT().setCaretPosition(0);
		
//		janela.getSegCB().setEnabled(true);
		
		janela.getFuncionarioTBL().setModel(janela.getFuncionarioTBL().atualizarTabela("funcionario", null, atividade, null, null, null));
	}
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	private ArrayList<Integer> verificarDiasDaSemana(Atividade atividade) {
//		ArrayList<Integer>
//		boolean[] diasDaSemana = new boolean[7];
		
		
			
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