package finalProject.NoPainNoGain.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailSender {
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.ru");
        mailSender.setPort(465);

        mailSender.setUsername("ivanivanov2023_18@mail.ru");
        mailSender.setPassword("CzgX7LYBBE0GfaQPrZL6");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "This is the test email template for your email:\n%s\n");
        return message;
    }
}
/*
mail.smtp.host=smtp.mail.ru
mail.smtp.port=465
mail.user.name=ivanivanov2023_18@mail.ru
mail.user.password=CzgX7LYBBE0GfaQPrZL6
mail.smtp.socketFactory.port=465
mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
mail.smtp.auth=true
 */

//        mail.host=smtp.mail.ru
//                mail.port=465
//                mail.username=uhb0f84h@mail.ru
//mail.password=dhg9j49h0gnj904h
//        mail.from=uhb0f84h@mail.ru
//mail.protocol=smtps
//        mail.smtps.auth=true