package pl.edu.agh.ki.suu.server.sender.service.impl;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.server.sender.service.MessageSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailMessageSender implements MessageSender {

    private JavaMailSenderImpl mailSender;

    public MailMessageSender(){
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setUsername("qhashio@gmail.com");
        mailSender.setPassword("IOTQHash");
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.ssl.enable", "true");
        mailSender.setJavaMailProperties(properties);
    }

    @Override
    public void send(Message message, Configuration client) {
        System.out.println("Send Message: " + message.getPayload() + " to: " + message.getTarget().getName() + " " + message.getTarget().getAddress() + " using MAIL");
        MimeMessage mail = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(client.getSender().getAddress());
            helper.setFrom("qhashio@gmail.com");
            helper.setSubject("Message title");
            helper.setText(message.getPayload());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mail);
    }

}
