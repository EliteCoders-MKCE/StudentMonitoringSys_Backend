package com.sgs.student.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
/**
 * 
 * @author simclair
 * @class Mailer
 * Mail manager to send messages
 */
@Service
public class Mailer {

	/**
	 * 
	 * @return JavaMailSender
	 */
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("sgs.alertsys@gmail.com");
	    mailSender.setPassword("srdcqwswqjeoxzut");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
	
	/**
	 * @return 
	 * @param to
	 * @param subject
	 * @param body
	 */
	public void sendMail(String to,String subject,String body) {
		JavaMailSender mailSender = this.getJavaMailSender();
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("sgs.alertsys@gmail.com");
		msg.setTo(to);
		msg.setText(body);
		msg.setSubject(subject);
		mailSender.send(msg);
		System.out.println("Mail sent successfully...");
	}
	
	/**
	 * 
	 * @param from
	 * @param password
	 * @param to
	 * @param sub
	 * @param msg
	 * @deprecated
	 */
	  public static void send(String from,String password,String to,String sub,String msg){  
          //Get properties object    
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.rediffmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");  
          props.put("mail.smtp.socketFactory.fallback","true");
          props.put( "mail.smtp.ssl.enable","false");
          props.put( "mail.smtp.starttls.enable","false");   
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          session.setDebug(true);
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setContent(msg,"text/html");    
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }  

}
