package br.com.rafaellbarros.ms.msemail.controller;

import br.com.rafaellbarros.ms.msemail.model.dto.EmailDTO;
import br.com.rafaellbarros.ms.msemail.model.entity.Email;
import br.com.rafaellbarros.ms.msemail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 14/02/2022
 */

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
        final Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        return new ResponseEntity<>(emailService.sendEmail(email), HttpStatus.CREATED);
    }

}
