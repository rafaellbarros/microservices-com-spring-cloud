package br.com.rafaellbarros.ms.msemail.adapters.outbound.smtp;

import br.com.rafaellbarros.ms.msemail.application.domain.Email;
import br.com.rafaellbarros.ms.msemail.application.ports.SendEmailServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 15/02/2022
 */

@Service
@RequiredArgsConstructor
public class SmtpSendEmailService implements SendEmailServicePort {

    private final JavaMailSender emailSender;

    @Override
    public void sendEmailStmp(Email email) {
        var message = new SimpleMailMessage();
        message.setFrom(email.getEmailFrom());
        message.setTo(email.getEmailTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        emailSender.send(message);
    }
}
