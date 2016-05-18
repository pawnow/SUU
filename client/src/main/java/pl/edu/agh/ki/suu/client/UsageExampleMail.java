package pl.edu.agh.ki.suu.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class UsageExampleMail {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail() {
        MimeMessage mail = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo("mateuszbiedak@gmail.com");
            helper.setFrom("qhashio@gmail.com");
            helper.setSubject("Message title");
            helper.setText("Message received via JMS");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mail);
    }
}

