package jframe.ouvintes.janelaCadatroDeFuncionarios;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import classes.Gerente;

import jframe.JanelaCadastroDeFuncionarios;

public class OuvinteDaTabelaObrasCadastrarFuncionario implements MouseListener {
	
	private JanelaCadastroDeFuncionarios janela;	
	
	public OuvinteDaTabelaObrasCadastrarFuncionario(JanelaCadastroDeFuncionarios janela) {
		this.janela = janela;
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {		
		Gerente gerente = Gerente.obterInstancia();			
		String nomeDaObra = janela.getObraTBL().getValueAt(janela.getObraTBL().getSelectedRow(), 0).toString();				
		janela.getAtividadeTBL().setModel(janela.getAtividadeTBL().atualizarTabela("atividade", gerente.buscarObra(nomeDaObra), null, null, null, null));
	}
	public void mouseReleased(MouseEvent e) {}	
}