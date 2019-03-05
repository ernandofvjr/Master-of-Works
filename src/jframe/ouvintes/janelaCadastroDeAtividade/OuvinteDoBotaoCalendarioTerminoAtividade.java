package jframe.ouvintes.janelaCadastroDeAtividade;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import jframe.JanelaCadastroDeAtividade;

public class OuvinteDoBotaoCalendarioTerminoAtividade implements PropertyChangeListener {
	private JanelaCadastroDeAtividade janela;
	public OuvinteDoBotaoCalendarioTerminoAtividade(JanelaCadastroDeAtividade janela) {
		this.janela = janela;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		Date dataInicio = janela.getCalendarioInicio().getDate();		
		Date dataTermino = janela.getCalendarioTermino().getDate();	
		
		if(dataInicio != null ){
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(dataInicio);			
			int dataAtual = cal.get(Calendar.DAY_OF_YEAR);			
			cal.setTime(dataTermino);			
			int dataPrazo = cal.get(Calendar.DAY_OF_YEAR);				
			int dias = dataPrazo - dataAtual;
			dias++;
			if(dias <= 0){
				janela.getPrazoCDT().setText("0");
			}
			else{
				janela.getPrazoCDT().setText(Integer.toString(dias));
			}
		}
//		else {
//			janela.getInicioCDT().setText("0");
//		}
	}
}
