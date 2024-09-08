package org.amw.portmanager.application.infrastructure;

import org.amw.portmanager.application.dto.Mail;

@FunctionalInterface
public interface MailService {
    public void sendMail(Mail mail);
}
