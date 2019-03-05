package jframe.ouvintes.janelaPrincipal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import classes.Gerente;
import classes.PersistenciaGerente;
import jframe.JanelaCadastroDeAtividade;
import jframe.JanelaCadastroDeFuncionarios;
import jframe.JanelaCadastroDeObras;
import jframe.JanelaPrincipal;
import jframe.JanelaSobre;

public class OuvinteDeAtalhoJanelaPrincipal implements KeyListener {
	
	private JanelaPrincipal janela;
	
	public OuvinteDeAtalhoJanelaPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		Gerente gerente = Gerente.obterInstancia();	
		
		if(e.getKeyCode() == KeyEvent.VK_F1){
			PersistenciaGerente persistencia = new PersistenciaGerente();
			persistencia.confimarSaida(janela, "novo");
		}
		else if(e.getKeyCode() == KeyEvent.VK_F2){
			PersistenciaGerente persistencia = new PersistenciaGerente();	
			
			JFileChooser fileChooser = new JFileChooser();		
			FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivo XML", "xml");		
			fileChooser.setFileFilter(filtro);		
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				
			  File arquivo = fileChooser.getSelectedFile();		  
			  gerente = Gerente.obterInstancia(persistencia, arquivo);
			  
			  janela.dispose();
			  new JanelaPrincipal();
			}	
		}
		else if(e.getKeyCode() == KeyEvent.VK_F3){
			PersistenciaGerente persistencia = new PersistenciaGerente();
			persistencia.escolherOndeSalvarGerente();
			janela.dispose();
			new JanelaPrincipal();
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			PersistenciaGerente persistencia = new PersistenciaGerente();
			persistencia.confimarSaida(janela, "fechar");
		}
		
		else if(e.getKeyCode() == KeyEvent.VK_F5){
			adicionar("Nova Obra");
		}
		else if(e.getKeyCode() == KeyEvent.VK_F6){
			if(!gerente.getObras().isEmpty()) {
				adicionar("Nova Atividade");
			}			
		}
		else if(e.getKeyCode() == KeyEvent.VK_F7){
			adicionar("Novo Funcionário");
		}		
		else if(e.getKeyCode() == KeyEvent.VK_F12){
			JanelaSobre sobre = new JanelaSobre();
			sobre.chamarJanela(janela);
		}
	}
	private void adicionar(String comando){
		
		janela.dispose();
		
		switch(comando){
			case "Novo Funcionário":				
				new JanelaCadastroDeFuncionarios();
				break;
				
			case "Nova Obra":				
				new JanelaCadastroDeObras();
				break;
				
			case "Nova Atividade":				
				new JanelaCadastroDeAtividade();
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
