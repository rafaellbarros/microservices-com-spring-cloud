package br.com.rafaellbarros.ms.msemail.application.ports;

import br.com.rafaellbarros.ms.msemail.application.domain.Email;
import br.com.rafaellbarros.ms.msemail.application.domain.PageInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 15/02/2022
 */
public interface EmailServicePort {

    Email sendEmail(final Email email);
    List<Email> findAll(final PageInfo pageInfo);
    Optional<Email> findById(UUID emailId);
    Email save(Email email);

}
