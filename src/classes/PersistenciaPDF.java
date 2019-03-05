package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jframe.excecoes.validacao.PDFSalvarCancelarException;

public class PersistenciaPDF {
	
	//recebe uma obra na assinatura e gera um arquivo pdf com os dados realcionados ao orçamento da obra
	//e salva este arquivo com o caminho que o usuário escolheu
	public void salvaExcecucaoOrcamentariaDeObra(Obra obra) throws PDFSalvarCancelarException{
		
		Document documento = new Document(PageSize.A4);
		
		try{			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			
			FileOutputStream caminho = escolherCaminho();
			
			if(caminho == null){
				throw new PDFSalvarCancelarException();
			}
			
			PdfWriter.getInstance(documento, caminho);
			
			documento.open();
			
			Image logoMW = Image.getInstance(getClass().getResource("/imagens/LogoMW.png"));
			
			logoMW.setAlignment(Element.ALIGN_CENTER);
			
			Font fonteTitulo = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			Font fonteNormal = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD);
			
			
			Paragraph tituloPG = new Paragraph("Orçamento da obra "+obra.getNome(), fonteTitulo);
			tituloPG.setAlignment(Element.ALIGN_CENTER);
			tituloPG.setSpacingAfter(20);
			
			Paragraph orcamentoTotal = new Paragraph("Orçamento Inical: R$ " + obra.getOrcamentoInicial()
			+"\nTotal de Acrescimos: R$ " + obra.totalAcrescimos()
			+"\nTotal de Gastos: R$ " + obra.totalGastos()
			+"\nOrçamento Restante: R$ " + obra.totalRestante());
			orcamentoTotal.setFont(fonteNormal);
			orcamentoTotal.setSpacingAfter(20);
			
			PdfPTable tabelaDeAcrescimo = new PdfPTable(3);
			tabelaDeAcrescimo.setSpacingAfter(20);
			
			PdfPCell cabecalhoAcrescimo = new PdfPCell(new Paragraph("ACRESCIMOS", fonteNormal));
			cabecalhoAcrescimo.setColspan(3);
			cabecalhoAcrescimo.setBackgroundColor(BaseColor.CYAN);
			cabecalhoAcrescimo.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			
			tabelaDeAcrescimo.addCell(cabecalhoAcrescimo);
			tabelaDeAcrescimo.addCell("NOME");
			tabelaDeAcrescimo.addCell("VALOR R$");
			tabelaDeAcrescimo.addCell("DATA");
			
			for(Acrescimo a : obra.getAcrescimos()){
				tabelaDeAcrescimo.addCell(a.getNome());
				tabelaDeAcrescimo.addCell("R$ " + a.getValor());
				tabelaDeAcrescimo.addCell(sdf.format(a.getData()));
			}
			
			PdfPTable tabelaDeGastos = new PdfPTable(3);
			PdfPCell cabecalhoGasto = new PdfPCell(new Paragraph("GASTOS", fonteNormal));
			cabecalhoGasto.setColspan(3);
			cabecalhoGasto.setBackgroundColor(BaseColor.ORANGE);
			cabecalhoGasto.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			tabelaDeGastos.addCell(cabecalhoGasto);
			tabelaDeGastos.addCell("NOME");
			tabelaDeGastos.addCell("VALOR R$");
			tabelaDeGastos.addCell("DATA");
			
			for(Gasto g : obra.getGastos()){
				tabelaDeGastos.addCell(g.getNome());
				tabelaDeGastos.addCell("R$ " + g.getValor());
				tabelaDeGastos.addCell(sdf.format(g.getData()));
			}			
			documento.add(logoMW);
			documento.add(tituloPG);
			documento.add(orcamentoTotal);
			documento.add(tabelaDeAcrescimo);			
			documento.add(tabelaDeGastos);
		}
		catch(DocumentException de){
			System.err.println(de.getMessage());
		}
		catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		documento.close();
	}
	//recebe uma obra na assinatura e gera um arquivo pdf com tabelas representando as atividades da obra, os comentários 
	//e funcionarios que fazem parte da atividade, e salva este arquivo com o caminho que o usuário escolheu
	public void salvarExcecucao(Obra obra) throws PDFSalvarCancelarException{
		Document documento = new Document(PageSize.A4);
		
		try{
			
			FileOutputStream caminho = escolherCaminho();
			
			if(caminho == null){
				throw new PDFSalvarCancelarException();
			}
			
			PdfWriter.getInstance(documento, caminho);
			
			
			Font fonteTitulo = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			Font fonteNormal = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD);	
			
			documento.open();
			Image logoMW = Image.getInstance(getClass().getResource("/imagens/LogoMW.png"));
			
			logoMW.setAlignment(Element.ALIGN_CENTER);			
			
			Paragraph tituloPG = new Paragraph("Atividades da Obra " + obra.getNome(), fonteTitulo);
			tituloPG.setAlignment(Element.ALIGN_CENTER);
			tituloPG.setSpacingAfter(20);
			
			documento.add(logoMW);
			documento.add(tituloPG);
					
			for(Atividade a : obra.getAtividades()){
				String status = a.getStatus();
				BaseColor cor;
				if(status.equals("Ativada")){
					cor = BaseColor.CYAN;
				}
				else{
					cor = BaseColor.ORANGE;
				}				
				
				PdfPTable tabelaDeAtividade = new PdfPTable(3);
				tabelaDeAtividade.setSpacingAfter(20);
				PdfPCell tituloDaAtividade = new PdfPCell(new Paragraph(a.getNome(), fonteNormal));
				tituloDaAtividade.setColspan(2);
				tituloDaAtividade.setBackgroundColor(cor);	
				
				
				PdfPCell statusDaAtividade = new PdfPCell(new Paragraph(status, fonteNormal));				
				statusDaAtividade.setColspan(1);
				statusDaAtividade.setBackgroundColor(cor);				
				
				tabelaDeAtividade.addCell(tituloDaAtividade);
				tabelaDeAtividade.addCell(statusDaAtividade);
				
				PdfPCell cell = new PdfPCell(new Phrase("COMENTÁRIOS"));
				cell.setColspan(2);
				tabelaDeAtividade.addCell(cell);				
				tabelaDeAtividade.addCell("FUNCIONÁRIOS");
				
				int tamanhoComentarios = a.getComentarios().size();
				int tamanhoFuncionarios = a.getFuncionarios().size();
				int maior = 0;
				
				if(tamanhoComentarios > tamanhoFuncionarios){
					maior = tamanhoComentarios;
				}
				else{
					maior = tamanhoFuncionarios;
				}				
				for(int n = 0; n < maior; n++){
					if(n < tamanhoComentarios){
						PdfPCell celula = new PdfPCell(new Phrase(a.getComentarios().get(n).getDescricao()));
						celula.setColspan(2);
						tabelaDeAtividade.addCell(celula);						
					}
					else{
						PdfPCell celula = new PdfPCell(new Phrase(""));
						celula.setColspan(2);
						tabelaDeAtividade.addCell(celula);
					}
					if(n < tamanhoFuncionarios){
						tabelaDeAtividade.addCell(a.getFuncionarios().get(n).getNome());
					}
					else{
						tabelaDeAtividade.addCell("");
					}
				}
				
				documento.add(tabelaDeAtividade);
			}
		}
		catch(DocumentException de){
			System.err.println(de.getMessage());
		}
		catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		documento.close();
	}
	
	public void salvarComentarios(Atividade atividade) throws PDFSalvarCancelarException{
		Document documento = new Document(PageSize.A4);		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			
			FileOutputStream caminho = escolherCaminho();
			
			if(caminho == null){
				throw new PDFSalvarCancelarException();
			}
			
			PdfWriter.getInstance(documento, caminho);
			
			
			Font fonteTitulo = new Font(FontFamily.TIMES_ROMAN, 20, Font.BOLD);
			Font fonteNormal = new Font(FontFamily.TIMES_ROMAN, 15, Font.BOLD);	
			
			documento.open();
			Image logoMW = Image.getInstance(getClass().getResource("/imagens/LogoMW.png"));
			
			logoMW.setAlignment(Element.ALIGN_CENTER);			
			
			Paragraph tituloPG = new Paragraph("Atividade " + atividade.getNome(), fonteTitulo);
			tituloPG.setAlignment(Element.ALIGN_CENTER);
			tituloPG.setSpacingAfter(20);
			
			documento.add(logoMW);
			documento.add(tituloPG);
			
			PdfPTable tabelaDeComentarios = new PdfPTable(3);
			PdfPCell cabecalho = new PdfPCell(new Paragraph("Nomes"));
			tabelaDeComentarios.addCell(cabecalho);
//			tabelaDeComentarios.addCell("Nome");
			tabelaDeComentarios.addCell("Descrição");
			tabelaDeComentarios.addCell("Data");
			
					
			for(Comentario c : atividade.getComentarios()){							
				
				tabelaDeComentarios.addCell(c.getNome());
				tabelaDeComentarios.addCell(c.getDescricao());
				tabelaDeComentarios.addCell(sdf.format(c.getData()));
				
				
			}
			
			documento.add(tabelaDeComentarios);
		}
		catch(DocumentException de){
			System.err.println(de.getMessage());
		}
		catch(IOException ioe){
			System.err.println(ioe.getMessage());
		}
		documento.close();
	}
	
	//retorna um FileOutputStream que contém o caminho, escolhido pelo usuário, para salvar arquivos.
	public FileOutputStream escolherCaminho() throws FileNotFoundException{
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo PDF", "pdf");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);
		
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			
			File arquivo = fileChooser.getSelectedFile();
			if (FilenameUtils.getExtension(arquivo.getName()).equalsIgnoreCase("pdf")) {			    
			} 
			else {
				arquivo = new File(arquivo.toString() + ".pdf");  
			    arquivo = new File(arquivo.getParentFile(), FilenameUtils.getBaseName(arquivo.getName())+".pdf"); // ALTERNATIVELY: remove the extension (if any) and replace it with ".xml"
			}			
			FileOutputStream os = new FileOutputStream(arquivo);
			return os;
		}
		return null;
		
	}
}