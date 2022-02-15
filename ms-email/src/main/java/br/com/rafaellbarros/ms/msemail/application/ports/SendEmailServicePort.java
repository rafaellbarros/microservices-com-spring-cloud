package br.com.rafaellbarros.ms.msemail.application.ports;

import br.com.rafaellbarros.ms.msemail.application.domain.Email;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 15/02/2022
 */

public interface SendEmailServicePort {

    void sendEmailStmp(Email email);

}
