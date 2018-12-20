package pl.jakub.walczak.driveme.services.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.jakub.walczak.driveme.model.user.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

@Service
public class MailerService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void sendEmail(String emailAddress, User user) throws MessagingException {
        Date date = Calendar.getInstance().getTime();
        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(emailAddress);
        helper.setSubject("Twoje konto w serwisie DriveMe zostało utworzone.");
        helper.setText("Możesz teraz się zalogować w serwisie z wykorzystaniem takiego hasła: " +
                user.getPassword());
        helper.setFrom("driveme.sys@gmail.com");
        helper.setSentDate(date);
        mailSender.send(message);
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("driveme.sys@gmail.com");
        mailSender.setPassword("DriveMe2018");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");



        return mailSender;
    }

}
