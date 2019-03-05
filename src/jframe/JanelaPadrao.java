package jframe;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class JanelaPadrao extends JFrame{
	
	public JanelaPadrao(){	
		
		ImageIcon img = new ImageIcon(getClass().getResource("/imagens/IconeMW.png"));		
		setIconImage(img.getImage());
		setResizable(false);
		setSize(900, 600);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}