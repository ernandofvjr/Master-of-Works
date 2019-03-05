package jframe.ouvintes.janelaPrincipal;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import jframe.JanelaPrincipal;
import jframe.componentes.CheckBox;

public class OuvinteDasTabelasPrincipalFocus implements FocusListener {
	
	private JanelaPrincipal janela;	

	public OuvinteDasTabelasPrincipalFocus(JanelaPrincipal janela) {
		this.janela = janela;
	}

	@Override
	public void focusGained(FocusEvent e) {
		
		for (CheckBox checkBox : janela.getListaDeCheckbox()) {
			checkBox.setEnabled(false);
			checkBox.setEnabled(false);
		}
//		janela.getObraTBL().setBackground(Color.getColor("cyan"));
		
//		float[] hsbval = new float[3];
//		Color.RGBtoHSB(230, 240, 255, hsbval);
//		janela.getObraTBL().setBackground(Color.getHSBColor(hsbval[0], hsbval[1], hsbval[2]));
		
//		//planejados
//		janela.getDomCBP().setEnabled(false);
//		janela.getDomCBP().setSelected(false);		
//		janela.getSegCBP().setEnabled(false);
//		janela.getSegCBP().setSelected(false);		
//		janela.getTerCBP().setEnabled(false);
//		janela.getTerCBP().setSelected(false);		
//		janela.getQuaCBP().setEnabled(false);
//		janela.getQuaCBP().setSelected(false);		
//		janela.getQuiCBP().setEnabled(false);
//		janela.getQuiCBP().setSelected(false);		
//		janela.getSexCBP().setEnabled(false);
//		janela.getSexCBP().setSelected(false);		
//		janela.getSabCBP().setEnabled(false);
//		janela.getSabCBP().setSelected(false);
//		
//		//executados
//		janela.getDomCBE().setEnabled(false);
//		janela.getDomCBE().setSelected(false);		
//		janela.getSegCBE().setEnabled(false);
//		janela.getSegCBE().setSelected(false);		
//		janela.getTerCBE().setEnabled(false);
//		janela.getTerCBE().setSelected(false);		
//		janela.getQuaCBE().setEnabled(false);
//		janela.getQuaCBE().setSelected(false);		
//		janela.getQuiCBE().setEnabled(false);
//		janela.getQuiCBE().setSelected(false);		
//		janela.getSexCBE().setEnabled(false);
//		janela.getSexCBE().setSelected(false);		
//		janela.getSabCBE().setEnabled(false);
//		janela.getSabCBE().setSelected(false);
		
		janela.getSalvarBT().setText("Salvar Atividade");
		janela.getSalvarBT().setForeground(Color.BLACK);
		janela.getSalvarBT().setEnabled(false);
		janela.getComentarioBT().setEnabled(false);
		
		janela.getAcompanhamentoSemanalETQ().setEnabled(false);
		janela.getDescricaoCutaETQ().setEnabled(false);
		janela.getPlanejadosETQ().setEnabled(false);
		janela.getExecutadosETQ().setEnabled(false);
		
		
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		
	}
	
}
