package br.com.rafaellbarros.ms.msemail.service;

import br.com.rafaellbarros.ms.msemail.model.entity.Email;
import br.com.rafaellbarros.ms.msemail.model.enums.StatusEmailEnum;
import br.com.rafaellbarros.ms.msemail.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 14/02/2022
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;

    private  final JavaMailSender emailSender;

    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmailEnum.SENT);
            log.error("Email enviado com sucesso!. {}", email);
        } catch (MailException e){
            email.setStatusEmail(StatusEmailEnum.ERROR);
            log.error("Erro ao tentar enviar email. {}", e.getMessage());
        } finally {
            return emailRepository.save(email);
        }
    }
}
