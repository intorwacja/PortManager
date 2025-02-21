package org.amw.portmanager.application.service;

import lombok.RequiredArgsConstructor;
import org.amw.portmanager.application.dto.Mail;
import org.amw.portmanager.application.infrastructure.MailService;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
@RequiredArgsConstructor
public class SMTPMailService implements MailService {

    private final SesClient sesClient;

    @Override
    public void sendMail(Mail mail) {
        try {
            SendEmailRequest request = SendEmailRequest.builder()
                    .source(mail.sender())
                    .destination(Destination.builder()
                            .toAddresses(mail.receiver())
                            .build())
                    .message(Message.builder()
                            .subject(Content.builder()
                                    .data(mail.subject())
                                    .build())
                            .body(Body.builder()
                                    .text(Content.builder()
                                            .data(mail.body())
                                            .build())
                                    .build())
                            .build())
                    .build();

            sesClient.sendEmail(request);
            System.out.println("Mail sent");
        } catch (SesException e) {
            System.err.println(e.getMessage());
        }
    }
}
