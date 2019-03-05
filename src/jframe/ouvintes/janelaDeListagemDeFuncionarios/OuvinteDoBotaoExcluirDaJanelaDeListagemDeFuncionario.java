package jframe.ouvintes.janelaDeListagemDeFuncionarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import classes.Atividade;
import classes.Email;
import classes.Funcionario;
import classes.Gerente;
import classes.Validacao;
import jframe.JanelaDeListagemDeFuncionarios;
import jframe.excecoes.validacao.EmailNaoEnviadoException;
import jframe.excecoes.validacao.FuncionarioEmailInvalidoException;
import jframe.excecoes.validacao.FuncionarioJaDemitidoException;
import jframe.excecoes.validacao.FuncionarioNaoDeletadoException;
import jframe.excecoes.validacao.TabelaNaoSelecionadaException;

public class OuvinteDoBotaoExcluirDaJanelaDeListagemDeFuncionario implements ActionListener{
	private JanelaDeListagemDeFuncionarios janela;
	public OuvinteDoBotaoExcluirDaJanelaDeListagemDeFuncionario(JanelaDeListagemDeFuncionarios janela) {
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent e) {
		Validacao validacao = new Validacao();
		
		Gerente gerente = Gerente.obterInstancia();
		ImageIcon iconeAtencao = new ImageIcon(getClass().getResource("/imagens/IconeAtencao.png"));
		
		int linha = janela.getTabelaDeListagem().getSelectedRow();
		
		try {
			validacao.validarTabela(linha);			
			
			String nomeDoFuncionario = janela.getTabelaDeListagem().getValueAt(linha, 0).toString();
			
			Funcionario funcionario  = gerente.buscarFuncionario(nomeDoFuncionario);		
			
			validacao.validarFuncionarioDemitido(funcionario);
			
			int resp = JOptionPane.showConfirmDialog(janela, "Você deseja demitir esse funcionário?", "Confirmação de Demissão", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, iconeAtencao);
			
			if(resp == JOptionPane.YES_OPTION){
				
				ArrayList<Funcionario> array = null;
				for(Atividade a: gerente.getAtividades()){
					for(Funcionario f: a.getFuncionarios()){
						array = a.getFuncionarios();
						if(funcionario.equals(f)){
							break;
						}
					}
				}
				if(array != null)
					array.remove(funcionario);				
				
				funcionario.setDemitido(true);
				
				OuvinteDoComboBoxDaJanelaDeListagemDeFuncionario ouvinte = new OuvinteDoComboBoxDaJanelaDeListagemDeFuncionario(janela);
				
				ouvinte.actionPerformed(e);
				
				int respEmail = JOptionPane.showConfirmDialog(janela, "Deseja enviar um email para o funcionário demitido?","Enviar email", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, iconeAtencao);
				
				if(respEmail == JOptionPane.YES_OPTION){
				
					if(!funcionario.getEmail().equals("") || funcionario.getEmail() != null){
						String email = JOptionPane.showInputDialog(janela, "Digite o email de sua conta apra enviar ao funcionário demitido", "Email", JOptionPane.PLAIN_MESSAGE);
						if(email != null){
							validacao.validarEmail(email);
							String senha = JOptionPane.showInputDialog(janela, "Digite a senha", "Senha", JOptionPane.PLAIN_MESSAGE);
							if(senha != null){
								Email enviarEmail = new Email(email, senha);
								ImageIcon confirmacaoICO = new ImageIcon(getClass().getResource("/imagens/IconeDoBotaoCadastrar.png"));
								enviarEmail.enviarEmail(email, funcionario.getEmail(), "Demissão", "Você foi demitido.");
								JOptionPane.showMessageDialog(janela, "Email enviado com sucesso!", "Aviso", JOptionPane.PLAIN_MESSAGE, confirmacaoICO);
							}
						}
					}
				}
			}
			else{
				throw new FuncionarioNaoDeletadoException();
			}
		} catch (TabelaNaoSelecionadaException | FuncionarioNaoDeletadoException | EmailNaoEnviadoException | FuncionarioEmailInvalidoException | FuncionarioJaDemitidoException e1) {
			JOptionPane.showMessageDialog(janela, e1.getMessage(), "Aviso", JOptionPane.PLAIN_MESSAGE, iconeAtencao);
		}
	}
}