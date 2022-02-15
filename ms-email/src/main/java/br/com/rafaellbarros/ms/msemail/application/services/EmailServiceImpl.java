package br.com.rafaellbarros.ms.msemail.application.services;

import br.com.rafaellbarros.ms.msemail.application.model.entities.Email;
import br.com.rafaellbarros.ms.msemail.application.model.enums.StatusEmailEnum;
import br.com.rafaellbarros.ms.msemail.application.ports.EmailRepositoryPort;
import br.com.rafaellbarros.ms.msemail.application.ports.EmailServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 15/02/2022
 */

@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailServicePort {

    private final EmailRepositoryPort emailRepository;

    private final JavaMailSender emailSender;

    @Override
    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            var message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmailEnum.SENT);
            log.info("Email enviado com sucesso! {}", email);
        } catch (MailException e){
            email.setStatusEmail(StatusEmailEnum.ERROR);
            log.error("Erro ao tentar enviar email! {}", e.getMessage());
        } finally {
            return emailRepository.save(email);
        }
    }

    @Override
    public Page<Email> findAll(Pageable pageable) {
        return emailRepository.findAll(pageable);
    }

    @Override
    public Optional<Email> findById(UUID emailId) {
        return emailRepository.findById(emailId);
    }

}
