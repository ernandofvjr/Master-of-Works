package jframe.ouvintes.janelaCadatroDeFuncionarios;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;

import jframe.JanelaCadastroDeFuncionarios;

public class OuvinteDoBotaoCalendarioFuncionario implements PropertyChangeListener {
	private JanelaCadastroDeFuncionarios janela;
	public OuvinteDoBotaoCalendarioFuncionario(JanelaCadastroDeFuncionarios janela) {
		this.janela = janela;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {	
			Date dataDeNascimento = janela.getCalendario().getDate();			
			if(dataDeNascimento != null){
				Calendar cal = Calendar.getInstance();
				
				Date dataAtual = cal.getTime(); 				
				
				cal.setTime(dataAtual);
				int anoAtual = cal.get(Calendar.YEAR);
				int mesAtual = cal.get(Calendar.MONTH);
				int diaAtual = cal.get(Calendar.DAY_OF_MONTH);
				
				cal.setTime(dataDeNascimento);				
				int anoDeNascimento = cal.get(Calendar.YEAR);
				int mesDeNascimento = cal.get(Calendar.MONTH);
				int diaDeNascimento = cal.get(Calendar.DAY_OF_MONTH);				
				
				int idade = anoAtual - anoDeNascimento;
				
				if(idade < 1){
					janela.getTfIdade().setText("0");
				}
				
				if(mesAtual < mesDeNascimento){
					idade--;
				}
				else if(mesAtual == mesDeNascimento){
					if(diaAtual <= diaDeNascimento){
						idade--;
					}
				}				
		janela.getTfIdade().setText(Integer.toString(idade));
		}			
	}
}