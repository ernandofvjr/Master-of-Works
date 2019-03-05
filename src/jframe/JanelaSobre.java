package jframe;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class JanelaSobre {	
	public void chamarJanela(JanelaPrincipal janela){ 
	ImageIcon img = new ImageIcon(getClass().getResource("/imagens/IconeGrande.png"));
	ImageIcon img2 = new ImageIcon(getClass().getResource("/imagens/FotoElton.png"));
	ImageIcon img3 = new ImageIcon(getClass().getResource("/imagens/FotoErnando.png"));
	
	JOptionPane.showMessageDialog(janela, "Master of Work Gerenciador de Obra\n"
			+ "Versão 1.0\nCopyright © 2016-2017 Master Of Work, Inc.\n"
			+ "Todos os direitos reservados.", "Sobre o Master of Work", JOptionPane.PLAIN_MESSAGE, img);
	JOptionPane.showMessageDialog(janela, "Elton J.\n"
			+ "\"Uma ALMA saudável\n"
			+ "Compõe-se de um CORPO saudável\n"
			+ "E uma MENTE saudável.\" - Soul Eater", "Master of Work criado por", JOptionPane.PLAIN_MESSAGE, img2);
	JOptionPane.showMessageDialog(janela, "Ernando F.\n"
			+"\"Ensinamentos obtidos sem\n"
			+"sofrimento são desprovidos\n"
			+"de valor.\" - Edward Elric"
			,"Master of Works criado por", JOptionPane.PLAIN_MESSAGE, img3);
	}
}
