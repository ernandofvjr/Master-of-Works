package jframe.ouvintes.janelaDeAtualizacaoDaAtividade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import classes.Atividade;
import classes.Comentario;
import classes.Gerente;
import classes.PersistenciaGerente;
import classes.Validacao;
import jframe.JanelaDeAtualizaçãoDaAtividade;
import jframe.excecoes.validacao.AtividadeStatusInvalidoException;
import jframe.excecoes.validacao.DescricaoInvalidaException;
import jframe.excecoes.validacao.NomeInvalidoException;

public class OuvinteDoBotaoAtualizarDaJanelaDeAtualizacaoDeAtividade implements ActionListener{
	private JanelaDeAtualizaçãoDaAtividade janela;
	public OuvinteDoBotaoAtualizarDaJanelaDeAtualizacaoDeAtividade(JanelaDeAtualizaçãoDaAtividade janela) {
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		Atividade atividade = janela.getAtividade();
		
		String nome = janela.getNomeDoComentarioCDT().getText();
		if(nome == null)
			nome = "";
		String descricao = janela.getComentarioADT().getText();
		if(descricao == null)
			descricao = "";
		Calendar calendario = Calendar.getInstance();
		
		Date data = calendario.getTime();
		
		try {
			
			if(janela.getAtivadaRb().isSelected()){
				atividade.setStatus("Ativada");
			}
			else if(janela.getDesativadaRB().isSelected()){
				ImageIcon iconeAtencao = new ImageIcon(getClass().getResource("/imagens/IconeAtencao.png"));
				
				int resp = JOptionPane.showConfirmDialog(janela, "Você deseja desativar a atividade? (essa ação não poderá ser desfeita)", "Confirmar Desativação", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, iconeAtencao);
				
				if(resp == JOptionPane.YES_OPTION){
					atividade.setStatus("Desativada");
					
					Date dataAtual = calendario.getTime();		
					
					if (dataAtual.before(atividade.getDataDeConclusao())){
						atividade.setNome("(Finalizada antes do prazo)"+atividade.getNome());
					}
					else{
						atividade.setNome("(Finalizada após o prazo)"+atividade.getNome());
					}
					atividade.setDataDeConclusao(dataAtual);
				}
				else{
					throw new AtividadeStatusInvalidoException();
				}
				
				janela.dispose();
				new JanelaDeAtualizaçãoDaAtividade(atividade);
				return;
			}
			if(!nome.equals("") && !descricao.equals("")){
				validacao.validarComentario(nome, descricao, atividade);
				Comentario comentario = new Comentario(nome, descricao, data);
				atividade.getComentarios().add(comentario);
			}
			janela.getNomeDoComentarioCDT().setText("");
			janela.getComentarioADT().setText("");
			janela.getTabelaDeComentarios().setModel(janela.getTabelaDeComentarios().atualizarTabela("comentario", null, atividade, null, null, null));
			
			Gerente gerente = Gerente.obterInstancia();
            PersistenciaGerente persistencia = new PersistenciaGerente();			
			persistencia.salvarGerente(gerente);
			
		} catch (NomeInvalidoException | DescricaoInvalidaException | AtividadeStatusInvalidoException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage());
		}	
	}
}