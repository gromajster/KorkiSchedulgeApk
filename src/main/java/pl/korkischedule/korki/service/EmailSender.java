package pl.korkischedule.korki.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.korkischedule.korki.Entity.ContactEmail;

@Service

public class EmailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    @SneakyThrows
    public void sendEmail(ContactEmail email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email.getDestination());
        mailMessage.setSubject(email.getSenderName() + " have send an email via korki website!");
        mailMessage.setText(email.getMessage());
        System.out.println(mailMessage);
        javaMailSender.send(mailMessage);
    }
}
