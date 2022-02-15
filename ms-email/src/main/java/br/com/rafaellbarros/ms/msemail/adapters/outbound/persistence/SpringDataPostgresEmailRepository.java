package br.com.rafaellbarros.ms.msemail.adapters.outbound.persistence;

import br.com.rafaellbarros.ms.msemail.adapters.outbound.persistence.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 15/02/2022
 */

public interface SpringDataPostgresEmailRepository  extends JpaRepository<EmailEntity, UUID> {

}
