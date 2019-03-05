package jframe.componentes;

import java.awt.Color;

import javax.swing.JTree;

public class Arvore extends JTree {
	
	private ModeloDeArvore modelo;
	
	public Arvore(ModeloDeArvore modelo) {
		adicionarModelo(modelo);
		setRootVisible(true);
		setShowsRootHandles(true);
		setBackground(Color.WHITE);	
	}
	
	public void adicionarModelo(ModeloDeArvore modelo) {
		this.modelo = modelo;
		setModel(modelo);
	}
	
	public ModeloDeArvore getModelo() {
		return modelo;
	}
	public void setModelo(ModeloDeArvore modelo) {
		this.modelo = modelo;
	}	
}
