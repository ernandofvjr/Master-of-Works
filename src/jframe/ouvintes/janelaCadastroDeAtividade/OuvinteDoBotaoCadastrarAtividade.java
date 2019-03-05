package jframe.ouvintes.janelaCadastroDeAtividade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import classes.Atividade;
import classes.Gerente;
import classes.Obra;
import classes.PersistenciaGerente;
import classes.Semana;
import classes.Validacao;
import jframe.JanelaCadastroDeAtividade;
import jframe.JanelaPrincipal;
import jframe.excecoes.validacao.AtividadeJaCadastradaException;
import jframe.excecoes.validacao.AtividadePrazoInvalidoException;
import jframe.excecoes.validacao.DescricaoInvalidaException;
import jframe.excecoes.validacao.NomeInvalidoException;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;
import jframe.ouvintes.janelaPrincipal.OuvinteDeSelecaoDaArvoreJanelaPrincipal;

public class OuvinteDoBotaoCadastrarAtividade implements ActionListener {

	private JanelaCadastroDeAtividade janela;
	
	public OuvinteDoBotaoCadastrarAtividade(JanelaCadastroDeAtividade janela){
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {		
		
		Validacao validacao = new Validacao();
		
		String descricaoCurta = janela.getDescricaoCurtaADT().getText();
		
		String descricaoLonga = janela.getDescricaoLongaADT().getText();						
		String prazo = janela.getPrazoCDT().getText();
		String nome = janela.getAtividades()[janela.getListaAtividadesCC().getSelectedIndex()];
//		String nome = janela.getNomeDaAtividadeCDT().getText();
		
		int linha = janela.getObraTBL().getSelectedRow();
		
		try {
			
			validacao.validarAtividade(nome, prazo, linha, descricaoCurta);
			
			
			//pega a data atual e calcula com o prazo final			
			Calendar calendario = Calendar.getInstance();
			Date dataInicial = janela.getCalendarioInicio().getDate();

			
			//data inicial
			Calendar cal = Calendar.getInstance();
			cal.setTime(dataInicial);
			cal.add(Calendar.DATE, -1);
			dataInicial = cal.getTime();

			
			
			calendario.set(Calendar.DAY_OF_MONTH, calendario.get(Calendar.DAY_OF_MONTH) + Integer.parseInt(prazo));
//			Date dataDeConclusao = calendario.getTime();
			Date dataDeConclusao = janela.getCalendarioTermino().getDate();			
//			System.out.println(dataDeConclusao.toString());
			
			cal.setTime(dataDeConclusao);
			cal.add(Calendar.DATE, -1);
			dataDeConclusao = cal.getTime();			
//			System.out.println(dataDeConclusao.toString());
			
			//cria uma lista de datas a partir da dataAtual e dataDeConclusao
			ArrayList<Date> lista = new ArrayList<Date>();
			lista = criarLista(dataInicial, dataDeConclusao);
			
			//cria uma lista de listas de datas dividas em semanas
			ArrayList<ArrayList<Date>> listaDeDatas = new ArrayList<>();
			listaDeDatas = criarListaDeListasDeDatas(lista);
			
			
			Gerente gerente = Gerente.obterInstancia();				
			String nomeDaObra = janela.getObraTBL().getValueAt(janela.getObraTBL().getSelectedRow(), 0).toString();
			
//			validacao.validarAtividadeDuplicada(nome, nomeDaObra);
			
			
			int num = 1;
			for (ArrayList<Date> arrayList : listaDeDatas) {
				
				Date inicial = arrayList.get(0);
				Date conclusao = arrayList.get(arrayList.size()-1);	

//				System.out.println(conclusao.toString());
				
				long diff = conclusao.getTime() - inicial.getTime();
				
				int dias = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				dias++;
				
				boolean[] semana = new boolean[7];
				
				for (boolean b : semana) {
					b = false;
				}
				
				Obra obra = gerente.buscarObra(nomeDaObra);
				
				Atividade atividade = new Atividade(nome + " #"+num, dias, descricaoCurta, descricaoLonga, inicial, conclusao, "Ativada", num, semana, obra);	
				
				OuvinteDeSelecaoDaArvoreJanelaPrincipal ouvinte = new OuvinteDeSelecaoDaArvoreJanelaPrincipal(null);
											 
				ArrayList<Integer> diasDaSemana = ouvinte.verificarDiasDaSemana(atividade);	
				
					for (Integer integer : diasDaSemana) {
						
						switch (integer) {
						case 1:					
							atividade.getDiasDaSemanaPlanejado()[integer-1] = true;							
							break;
						case 2:
							atividade.getDiasDaSemanaPlanejado()[integer-1] = true;	
							break;
						case 3:
							atividade.getDiasDaSemanaPlanejado()[integer-1] = true;
							break;
						case 4:
							atividade.getDiasDaSemanaPlanejado()[integer-1] = true;
							break;
						case 5:
							atividade.getDiasDaSemanaPlanejado()[integer-1] = true;
							break;
						case 6:
							atividade.getDiasDaSemanaPlanejado()[integer-1] = true;
							break;
						case 7:
							atividade.getDiasDaSemanaPlanejado()[integer-1] = true;
							break;
						default:
							break;
						}
					}
				
				num++;
				
				validacao.validarAtividadeDuplicada(atividade, nomeDaObra);
				
//				System.out.println(atividade.getDataDeCriacao().toString());
//				System.out.println(atividade.getDataDeConclusao().toString());
//				for (boolean b : atividade.getDiasDaSemanaPlanejado()) {
//					System.out.println(b);
//				}
				
				gerente.addAtividade(atividade);
				
				
				obra.getAtividades().add(atividade);
				
				int semanaDoAno = atividade.getSemanaDoAno();
				int ano = atividade.getAno();
				
//				Semana sem = gerente.buscarSemana(semanaDoAno, ano);
				Semana sem = obra.buscarSemana(semanaDoAno, ano); 
				
				if(sem == null) {
					sem = new Semana(semanaDoAno, ano);
					sem.adicionarAtividade(atividade);
					obra.addSemana(sem);
				}
				
				else{
					sem.adicionarAtividade(atividade);
				}				
				
//				for (Date date : arrayList) {
//					System.out.println(date);
//				}				
			}
			
			
			
			//cria nova atividade
						
			
				
			
				
			
			
			
			
			
			
			//Salvar
			PersistenciaGerente persistencia = new PersistenciaGerente();
			
			persistencia.salvarGerente(gerente);
			
			JOptionPane.showMessageDialog(janela, "Atividade cadastrada com sucesso!");
			
			janela.dispose();
			new JanelaPrincipal();
		} catch (NomeInvalidoException | AtividadePrazoInvalidoException | TabelaNaoSelecionadaException | DescricaoInvalidaException | AtividadeJaCadastradaException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}
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
	private ArrayList<ArrayList<Date>> criarListaDeListasDeDatas(ArrayList<Date> lista){
		
		ArrayList<ArrayList<Date>> listaDeDatas = new ArrayList<>(); 
		int index = -1;
		int temp = -1;
		
		for (Date date : lista) {
        	
            if (temp != semanaDoAno(date))	{
            	temp = semanaDoAno(date);
            	index++;
                ArrayList<Date> semana = new ArrayList<Date>();
                listaDeDatas.add(semana);
            }
            listaDeDatas.get(index).add(date);                
        }
		
		return listaDeDatas;
	}
	private int semanaDoAno(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }  
}