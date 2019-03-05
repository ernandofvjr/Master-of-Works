package jframe.componentes;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Painel extends JPanel {
	
	private RolagemDoPainel rolagem;
		
	public Painel(JFrame janela, RolagemDoPainel rolagem,int x, int y, int w, int h) {
		
		this.rolagem = rolagem;		
		setLayout(new BorderLayout());
		atualizarRolagem();
		setBounds(225, 5, 210, 415);
		
		janela.add(this);	
	}
	public void atualizarRolagem(){
		add(this.rolagem, BorderLayout.CENTER);
	}

	public RolagemDoPainel getRolagem() {
		return rolagem;
	}

	public void setRolagem(RolagemDoPainel rolagem) {
		this.rolagem = rolagem;
	}	
}
