package br.com.rafaellbarros.ms.msemail.application.model.entities;

import br.com.rafaellbarros.ms.msemail.application.model.enums.StatusEmailEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * created by:
 *
 * @author rafael for DevDusCorre <rafaelbarros.softwareengineer@gmail.com> on 14/02/2022
 */

@Data
@Entity
@Table(name = "TB_EMAIL")
public class Email implements Serializable {

    @Serial
    private static final long serialVersionUID = -3499534654183959667L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID emailId;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmailEnum statusEmail;
}
