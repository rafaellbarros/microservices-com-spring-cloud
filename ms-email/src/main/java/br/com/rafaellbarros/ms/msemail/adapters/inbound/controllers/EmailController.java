package br.com.rafaellbarros.ms.msemail.adapters.inbound.controllers;

import br.com.rafaellbarros.ms.msemail.adapters.dtos.EmailDTO;
import br.com.rafaellbarros.ms.msemail.application.domain.Email;
import br.com.rafaellbarros.ms.msemail.application.domain.PageInfo;
import br.com.rafaellbarros.ms.msemail.application.ports.EmailServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 14/02/2022
 */

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailServicePort emailServicePort;

    @PostMapping("/sending-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
        final var email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        return new ResponseEntity<>(emailServicePort.sendEmail(email), HttpStatus.CREATED);
    }

    @GetMapping("/emails")
    public ResponseEntity<Page<Email>> getAllEmails(@PageableDefault(size = 5, sort = "emailId", direction =
            Sort.Direction.DESC) Pageable pageable) {
        var pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable, pageInfo);
        var emailList = emailServicePort.findAll(pageInfo);
        return new ResponseEntity<>(new PageImpl<Email>(emailList, pageable, emailList.size()), HttpStatus.OK);
    }

    @GetMapping("/emails/{emailId}")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value="emailId") UUID emailId){
        return emailServicePort.findById(emailId).<ResponseEntity<Object>>map(email -> ResponseEntity.status(HttpStatus.OK)
                .body(email)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found."));
    }
}
