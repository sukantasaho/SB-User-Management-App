package com.main.mail;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class UserMailSystem {

	 
	 @Autowired
     private JavaMailSender sender;
	 
	 @Value("${spring.mail.username}")
	 private String fromEmail;
	 
	public boolean userRegisterEmail(String subject, String toEmail, String body) throws IOException, TemplateException 
	{
		        //create the email message
				MimeMessage message = sender.createMimeMessage();
				MimeMessageHelper helper;
				boolean status = false;
				try 
				{
					helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
							                       StandardCharsets.UTF_8.name());
					helper.setFrom(fromEmail);
					helper.setTo(toEmail);
					helper.setSubject(subject);
					helper.setSentDate(new Date());
					helper.setText(body, true);
					sender.send(message);
					status = true;
				} 
				catch (MessagingException e) 
				{
					 
					e.printStackTrace();
				}
				return status;
	}
	public  boolean userRecoverEmailSending(String subject, String toEmail, String body) throws IOException, TemplateException 
	{
		        //create the email message
				MimeMessage message = sender.createMimeMessage();
				MimeMessageHelper helper;
				boolean mailStatus = false;
				try 
				{
					helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
							                       StandardCharsets.UTF_8.name());
					helper.setFrom(fromEmail);
					helper.setTo(toEmail);
					helper.setSubject(subject);
					helper.setSentDate(new Date());
					helper.setText(body, true);
					sender.send(message);
					mailStatus = true;
				} 
				catch (MessagingException e) 
				{
					e.printStackTrace();
				}
			return mailStatus;	
	}

}
