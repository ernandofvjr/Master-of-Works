package jframe;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class JanelaSobre {	
	public void chamarJanela(JanelaPrincipal janela){ 
	ImageIcon img = new ImageIcon(getClass().getResource("/imagens/IconeGrande.png"));
	ImageIcon img2 = new ImageIcon(getClass().getResource("/imagens/FotoElton.png"));
	ImageIcon img3 = new ImageIcon(getClass().getResource("/imagens/FotoErnando.png"));
	
	JOptionPane.showMessageDialog(janela, "Master of Work Gerenciador de Obra\n"
			+ "Vers�o 1.0\nCopyright � 2016-2017 Master Of Work, Inc.\n"
			+ "Todos os direitos reservados.", "Sobre o Master of Work", JOptionPane.PLAIN_MESSAGE, img);
	JOptionPane.showMessageDialog(janela, "Elton J.\n"
			+ "\"Uma ALMA saud�vel\n"
			+ "Comp�e-se de um CORPO saud�vel\n"
			+ "E uma MENTE saud�vel.\" - Soul Eater", "Master of Work criado por", JOptionPane.PLAIN_MESSAGE, img2);
	JOptionPane.showMessageDialog(janela, "Ernando F.\n"
			+"\"Ensinamentos obtidos sem\n"
			+"sofrimento s�o desprovidos\n"
			+"de valor.\" - Edward Elric"
			,"Master of Works criado por", JOptionPane.PLAIN_MESSAGE, img3);
	}
}
