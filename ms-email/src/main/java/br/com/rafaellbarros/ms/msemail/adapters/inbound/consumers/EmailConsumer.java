package br.com.rafaellbarros.ms.msemail.adapters.inbound.consumers;

import br.com.rafaellbarros.ms.msemail.adapters.inbound.dtos.EmailDTO;
import br.com.rafaellbarros.ms.msemail.application.model.entities.Email;
import br.com.rafaellbarros.ms.msemail.application.ports.EmailServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 15/02/2022
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailServicePort emailServicePort;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO) {
        final Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        emailServicePort.sendEmail(email);
        log.info("Email Status: {}", email.getStatusEmail().toString());
    }
}