package br.com.rafaellbarros.ms.msemail.application.services;

import br.com.rafaellbarros.ms.msemail.application.domain.Email;
import br.com.rafaellbarros.ms.msemail.application.domain.PageInfo;
import br.com.rafaellbarros.ms.msemail.application.domain.enums.StatusEmailEnum;
import br.com.rafaellbarros.ms.msemail.application.ports.EmailRepositoryPort;
import br.com.rafaellbarros.ms.msemail.application.ports.EmailServicePort;
import br.com.rafaellbarros.ms.msemail.application.ports.SendEmailServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
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

    private final EmailRepositoryPort emailRepositoryPort;
    private final SendEmailServicePort sendEmailServicePort;

    @Override
    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try{
            sendEmailServicePort.sendEmailStmp(email);
            email.setStatusEmail(StatusEmailEnum.SENT);
            log.info("Email enviado com sucesso! {}", email);
        } catch (Exception e){
            email.setStatusEmail(StatusEmailEnum.ERROR);
            log.error("Erro ao tentar enviar email! {}", e.getMessage());
        } finally {
            return save(email);
        }
    }

    @Override
    public List<Email> findAll(PageInfo pageInfo) {
        return  emailRepositoryPort.findAll(pageInfo);
    }

    @Override
    public Optional<Email> findById(UUID emailId) {
        return emailRepositoryPort.findById(emailId);
    }

    @Override
    public Email save(Email email) {
        return emailRepositoryPort.save(email);
    }

}
