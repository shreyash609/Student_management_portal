package in.ashokit.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;


	public boolean sendMail(String subject,String body, String to) {
		
		boolean isSent=false;
		
		try {
			MimeMessage createMimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(createMimeMessage);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			mailSender.send(createMimeMessage);
			isSent=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSent;
	}
}

