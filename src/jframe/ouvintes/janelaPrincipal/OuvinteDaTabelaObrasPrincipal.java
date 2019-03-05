package jframe.ouvintes.janelaPrincipal;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.table.DefaultTableModel;

import classes.Gerente;
import classes.Obra;
import jframe.JanelaPrincipal;
import jframe.componentes.ModeloDeArvore;

public class OuvinteDaTabelaObrasPrincipal implements MouseListener {
	
	private JanelaPrincipal janela;	
	
	public OuvinteDaTabelaObrasPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}	
	public void mouseClicked(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}	
	public void mousePressed(MouseEvent e) {
		
		Gerente gerente = Gerente.obterInstancia();		
		String nomeDaObra = janela.getObraTBL().getValueAt(janela.getObraTBL().getSelectedRow(), 0).toString();			
		Obra obra = gerente.buscarObra(nomeDaObra);		
		
		janela.getValorOrçamentoETQ().setText(String.valueOf(obra.getOrcamentoInicial()));
		janela.getValorAcrescimosETQ().setText(String.valueOf(obra.totalAcrescimos()));
		janela.getValorGastosETQ().setText(String.valueOf(obra.totalGastos()));
		double totalRestanteTemp = obra.totalRestante();
		janela.getValorrestanteETQ().setText(String.valueOf(totalRestanteTemp));
		if(totalRestanteTemp >= 0){
			janela.getValorrestanteETQ().setForeground(Color.GREEN);
		}
		else{
			janela.getValorrestanteETQ().setForeground(Color.RED);
		}		
		
		
		janela.getDescricaoCurtaADT().setText("");
//		janela.getDescricaoLongaADT().setText("");
		
		DefaultTableModel modeloEmBranco = new DefaultTableModel();
		
		modeloEmBranco.addColumn("FUNCIONÁRIOS");
		
		janela.getFuncionarioTBL().setModel(modeloEmBranco);
		janela.getFuncionarioTBL().repaint();
		
		
		ModeloDeArvore modeloDeArvore = new ModeloDeArvore(obra.getSemanas());
		janela.getPainelDaArvore().getRolagem().getArvore().adicionarModelo(modeloDeArvore);
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	
}