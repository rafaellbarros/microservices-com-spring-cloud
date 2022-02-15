package br.com.rafaellbarros.ms.msemail.application.ports;

import br.com.rafaellbarros.ms.msemail.application.model.entities.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 15/02/2022
 */

public interface EmailRepositoryPort {

    Email save(Email email);
    Page<Email> findAll(Pageable  pageable);
    Optional<Email> findById(UUID email);

}
