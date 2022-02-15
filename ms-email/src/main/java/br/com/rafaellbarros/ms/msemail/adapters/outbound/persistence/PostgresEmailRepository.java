package br.com.rafaellbarros.ms.msemail.adapters.outbound.persistence;

import br.com.rafaellbarros.ms.msemail.adapters.outbound.persistence.entities.EmailEntity;
import br.com.rafaellbarros.ms.msemail.application.domain.Email;
import br.com.rafaellbarros.ms.msemail.application.domain.PageInfo;
import br.com.rafaellbarros.ms.msemail.application.ports.EmailRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private final ModelMapper modelMapper;


    @Override
    public Email save(Email email) {
        EmailEntity emailEntity = emailRepository.save(modelMapper.map(email, EmailEntity.class));
        return modelMapper.map(emailEntity, Email.class);
    }

    @Override
    public List<Email> findAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());
        return emailRepository.findAll(pageable).stream().map(entity -> modelMapper.map(entity, Email.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Email> findById(UUID emailId) {
        return emailRepository.findById(emailId).map(entity -> modelMapper.map(entity, Email.class));
    }
}
