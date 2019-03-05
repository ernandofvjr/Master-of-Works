package jframe.componentes;

import javax.swing.JScrollPane;

public class RolagemDoPainel extends JScrollPane {
	
	private Arvore arvore;
	
	public RolagemDoPainel(Arvore arvore) {
		this.arvore = arvore;
		this.setViewportView(arvore);
	}

	public Arvore getArvore() {
		return arvore;
	}
	public void setArvore(Arvore arvore) {
		this.arvore = arvore;
	}
}