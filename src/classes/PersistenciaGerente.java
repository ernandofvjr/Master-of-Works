package classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import jframe.JanelaPrincipal;

public class PersistenciaGerente {
	
	private XStream xstream = new XStream(new DomDriver("UTF-8"));
	private File arquivo = new File("gerente.xml");
	
	//por meio do JFileChooser exibe uma janela ao usuário para escolher onde ele deseja salvar o arquivo no formato pdf
	public void escolherOndeSalvarGerente(){
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo XML", "xml");			
		JFileChooser fileChooser = new JFileChooser();			
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {			
			File arquivo = fileChooser.getSelectedFile();		
			if (FilenameUtils.getExtension(arquivo.getName()).equalsIgnoreCase("xml")) {			    
			} 
			else {
				arquivo = new File(arquivo.toString() + ".xml");  
			    arquivo = new File(arquivo.getParentFile(), FilenameUtils.getBaseName(arquivo.getName())+".xml"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
			}			
			Gerente gerente = Gerente.obterInstancia(this);
			this.salvarGerente(gerente, arquivo);
			if(arquivo.exists()){
				deletarGerenteTemp();
			}
		}				
	}
	//salva o gerente em um arquivo no formato xml recebendo um Gerente na assinatura
	public void salvarGerente (Gerente gerente) {
		String xml = xstream.toXML(gerente);
		try {
			arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo, "UTF-8");
			gravar.print(xml);
			gravar.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	//salva o gerente em um arquivo no formato xml recebendo um Gerente e um File(caminho para salvar o arquivo) na assinatura
	public void salvarGerente (Gerente gerente, File arquivo) {
		String xml = xstream.toXML(gerente);
		try {
			arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo, "UTF-8");
			gravar.print(xml);
			gravar.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	//recupera o Gerente na pasta raiz do projeto
	public Gerente recuperarGerente(){
		try {
			if(arquivo.exists()){
				FileInputStream fis = new FileInputStream(arquivo);
				return (Gerente) xstream.fromXML(fis);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Gerente();
	}
	//recupera o Gerente baseado no arquivo File(caminho para recuperar o arquivo) recebido na assinatura
	public Gerente recuperarGerente(File arquivo){
		try {
			if(arquivo.exists()){
				FileInputStream fis = new FileInputStream(arquivo);
				return (Gerente) xstream.fromXML(fis);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Gerente();
	}
	//delata o Gerente ue se encontra na pasta raiz do projeto
	public void deletarGerenteTemp(){
		try{
    		File file = new File("gerente.xml");
    		file.delete();
    	}catch(Exception a){
    		a.printStackTrace();
    	}	
	}
	//verifica se o usuário alterou algo em seu projeto, se caso algo foi alterado, ele exibe por meio do JOptionPane uma mensagem perguntando se o usuário deseja salvar o projeto
	public void confimarSaida(JFrame janela, String tipo){
		if(arquivo.exists()){			
			ImageIcon img = new ImageIcon(getClass().getResource("/imagens/IconeAtencao.png"));
			int escolha = JOptionPane.showConfirmDialog(janela, "Você deseja salvar seu trabalho?", "Confirmar saída", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, img);
			switch (escolha) {
			case 0:{					
				this.escolherOndeSalvarGerente();
				if(tipo.equals("novo")){
					Gerente gerente = Gerente.obterInstancia(this);
				}				
				janela.dispose();
				new JanelaPrincipal();
				break;
			}
			case 1:{
				if(tipo.equals("fechar")){
					this.deletarGerenteTemp();	
					System.exit(0);
					break;
				}
				else{
					this.deletarGerenteTemp();
					Gerente gerente = Gerente.obterInstancia(this);
					janela.dispose();
					new JanelaPrincipal();
				}				
			}
			}
		}
		else if(tipo.equals("fechar")){
			this.deletarGerenteTemp();
			System.exit(0);
		}
		else if(tipo.equals("novo")){
			this.deletarGerenteTemp();
			Gerente gerente = Gerente.obterInstancia(this);
			janela.dispose();
			new JanelaPrincipal();
		}
	}
}

