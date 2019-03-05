package jframe.ouvintes.janelaPrincipal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import classes.Atividade;
import classes.Gerente;
import classes.Obra;
import classes.Semana;
import jframe.JanelaPrincipal;
import jframe.componentes.Arvore;

public class OuvinteDoMouseDaArvoreJanelaPrincipal implements MouseListener {
	
	private JanelaPrincipal janela;
	
	public OuvinteDoMouseDaArvoreJanelaPrincipal(JanelaPrincipal janela) {
		this.janela = janela;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {		
		Arvore arvore = janela.getPainelDaArvore().getRolagem().getArvore();
		if (SwingUtilities.isRightMouseButton(e)) {		
			
			int row = arvore.getClosestRowForLocation(e.getX(), e.getY());			
			arvore.setSelectionRow(row);						
			Object objeto = janela.getPainelDaArvore().getRolagem().getArvore().getLastSelectedPathComponent();
			
        	if(objeto instanceof Semana) {
        		
        		JPopupMenu menu = new JPopupMenu();
    		    JMenuItem item = new JMenuItem("Exibir Gráfico");    		    
    		    ActionListener ouvinte = new ActionListener() {				
    				@Override
    				public void actionPerformed(ActionEvent e) {	
    					Semana semana = (Semana) objeto;
    					JDialog janela = new JDialog();            		
	            		DefaultCategoryDataset ds = new DefaultCategoryDataset();
	            		
	            		Double somatorioCompletadas = 0.0;
	            		Double somatorioGeral = 0.0;
	            		
	            		for (Atividade atividade : semana.getAtividades()) {
	            			somatorioGeral++;
	            			double valor = 0 ;
	            			String estado;
	            			String nome = atividade.getNome();
	            			if(atividade.checarPlanejamento() == true) {
	            				somatorioCompletadas++;
	            				valor = 100;
	            				estado = "Completada";	            				
	            			}
	            			else {
	            				estado = "Não Completada";
	            			}
	            			ds.addValue(valor, estado, nome);	            			
						}
	            		Double ppc = somatorioCompletadas / somatorioGeral * 100;
	            		
	            		JFreeChart grafico = ChartFactory.createBarChart3D("Acompanhamento Semanal: " + semana.toString(), "PPC: " + ppc.intValue() + "%", "Porcentagem %", ds
	            				, PlotOrientation.VERTICAL, true, true, false);   		
	            		
	            		janela.setSize(900, 600);
	            		janela.setLocationRelativeTo(null);
	            		janela.setTitle("Gráfico");
	            		janela.setAlwaysOnTop(true);
	            		
	            		
	            		ChartPanel cp = new ChartPanel(grafico);
	            		cp.setMouseWheelEnabled(true);
	            		
	            		janela.setLayout(new BorderLayout());
	            		janela.add(cp, BorderLayout.CENTER);
	            		
	            		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            		janela.setVisible(true);
    				}
    			};    			
    		    item.addActionListener(ouvinte);    		    
    		    menu.add(item);
    		    menu.show(e.getComponent(), e.getX(), e.getY());
        	}
        	else if(objeto.equals("ATIVIDADES")) {
        		JPopupMenu menu = new JPopupMenu();
    		    JMenuItem item = new JMenuItem("Exibir Gráfico");    		    
    		    ActionListener ouvinte = new ActionListener() {				
    				@Override
    				public void actionPerformed(ActionEvent e) {
    					Gerente gerente = Gerente.obterInstancia();		
    					String nomeDaObra = janela.getObraTBL().getValueAt(janela.getObraTBL().getSelectedRow(), 0).toString();			
    					Obra obra = gerente.buscarObra(nomeDaObra);
    					JDialog janela = new JDialog();            		
	            		DefaultCategoryDataset ds = new DefaultCategoryDataset();
	            		
	            		
	            		for (Semana semana : obra.getSemanas()) {
	            			Double somatorioCompletadas = 0.0;
		            		Double somatorioGeral = 0.0;
	            			for (Atividade atividade : semana.getAtividades()) {
	            				somatorioGeral++;
		            			double valor = 0 ;
		            			String estado;
		            			String nome = atividade.getNome();
		            			if(atividade.checarPlanejamento() == true) {
		            				somatorioCompletadas++;
		            				valor = 100;
		            				estado = "Completada";	            				
		            			}
		            			else {
		            				estado = "Não Completada";
		            			}    				
	            				
							}	
	            			Double ppc = somatorioCompletadas / somatorioGeral * 100;
	            			ds.addValue(ppc, ppc+"%", semana.toString(semana.getPrimeiroDiaDaSemana()));	
						}
	            		
	            		JFreeChart grafico = ChartFactory.createBarChart3D("Acompanhamento Semanal", "PPC" + "%", "PPC %", ds
	            				, PlotOrientation.VERTICAL, true, true, false);   		
	            		
	            		janela.setSize(900, 600);
	            		janela.setLocationRelativeTo(null);
	            		janela.setTitle("Gráfico");
	            		janela.setAlwaysOnTop(true);
	            		
	            		
	            		ChartPanel cp = new ChartPanel(grafico);
	            		cp.setMouseWheelEnabled(true);
	            		
	            		janela.setLayout(new BorderLayout());
	            		janela.add(cp, BorderLayout.CENTER);
	            		
	            		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            		janela.setVisible(true);
    				}
    			};    			
    		    item.addActionListener(ouvinte);    		    
    		    menu.add(item);
    		    menu.show(e.getComponent(), e.getX(), e.getY());
        	}        	
        }		
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
}