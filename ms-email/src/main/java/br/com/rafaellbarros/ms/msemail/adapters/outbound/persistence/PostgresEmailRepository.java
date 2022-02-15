package br.com.rafaellbarros.ms.msemail.adapters.outbound.persistence;

import br.com.rafaellbarros.ms.msemail.application.model.entities.Email;
import br.com.rafaellbarros.ms.msemail.application.ports.EmailRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 15/02/2022
 */

@Component
@Primary
@RequiredArgsConstructor
public class PostgresEmailRepository implements EmailRepositoryPort {

    private final SpringDataPostgresEmailRepository emailRepository;

    @Override
    public Email save(Email email) {
        return emailRepository.save(email);
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
