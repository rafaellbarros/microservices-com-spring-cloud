package br.com.rafaellbarros.ms.msemail.repository;

import br.com.rafaellbarros.ms.msemail.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 14/02/2022
 */
public interface EmailRepository extends JpaRepository<Email, Long> {
}
