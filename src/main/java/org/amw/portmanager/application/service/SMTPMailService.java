package org.amw.portmanager.application.service;

import org.amw.portmanager.application.dto.Mail;
import org.amw.portmanager.application.infrastructure.MailService;
import org.springframework.stereotype.Service;

@Service
public class SMTPMailService implements MailService {

    @Override
    public void sendMail(Mail mail) {
        //TODO: Create after connecting to SMTP service
    }
}
