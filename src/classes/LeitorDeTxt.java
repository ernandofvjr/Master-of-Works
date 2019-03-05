package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorDeTxt {
	public static String[] lerArquivoDeProfissoes(String nomeDoArquivo){
		String linha = "";
		ArrayList<String> array = new ArrayList<String>();
		
		try {
			FileReader arq = new FileReader(nomeDoArquivo+".txt");
			
			BufferedReader lerArq = new BufferedReader(arq);
			
			while (linha != null){
		        linha = lerArq.readLine();
		        if(linha != null)
		        	array.add(linha);
		    }
			arq.close();
			
		} catch (IOException e) {}

		String[] vetor = new String[array.size()];
		
		for(int i = 0; i<array.size();i++){
			vetor[i] = array.get(i);
		}
		
		return vetor;
	}
}
