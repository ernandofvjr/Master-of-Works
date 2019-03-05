package classes;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jframe.excecoes.validacao.EmailNaoEnviadoException;

public class Email {
	private Session session = null;
	private String username = null;
	private String senha = null;
   // construtor que recebe email e senha do usuario
	public Email(String userNameGmail, String senhaGmail) {
        this.username = userNameGmail;
        this.senha = senhaGmail;
        ajustaParametros();
    }
 
    //método para enviar email
    public void enviarEmail(String remetente, String destinatario, String assunto, String conteudo) throws EmailNaoEnviadoException {
    	 
        try {
 
            Message message = new MimeMessage(session);
 
            //Remetente da mensagem
            message.setFrom(new InternetAddress(remetente));
 
            //Destinatário da mensagem
            Address[] toUser = InternetAddress
                    .parse(destinatario);
 
            //Assunto da mensagem
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);
 
            //Conteudo da mensagem
            message.setText(conteudo);
 
            
//             Envia a mensagem criada
             
            Transport.send(message);
 
            System.out.println("Email enviado com Sucesso; ");
 
        } catch (MessagingException e) {
            throw new EmailNaoEnviadoException();
        }
 
    } 
    //ajusta os paramestros para o servidor do gmail
    private void ajustaParametros() {
 
        Properties props = new Properties();
 
//        Conexão com o servidor do gmail
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, senha);
                    }
                });
    }
}
 
