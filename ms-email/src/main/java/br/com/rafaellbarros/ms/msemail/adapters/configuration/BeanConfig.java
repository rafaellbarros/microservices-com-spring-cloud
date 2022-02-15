package br.com.rafaellbarros.ms.msemail.adapters.configuration;

import br.com.rafaellbarros.ms.msemail.MsEmailApplication;
import br.com.rafaellbarros.ms.msemail.application.ports.EmailRepositoryPort;
import br.com.rafaellbarros.ms.msemail.application.services.EmailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 15/02/2022
 */

@Configuration
@ComponentScan(basePackageClasses = MsEmailApplication.class)
public class BeanConfig {

    @Bean
    EmailServiceImpl emailServiceImpl(EmailRepositoryPort repository, JavaMailSender emailSender) {
        return new EmailServiceImpl(repository, emailSender);
    }

}