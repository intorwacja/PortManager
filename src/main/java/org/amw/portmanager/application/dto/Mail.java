package org.amw.portmanager.application.dto;

import lombok.Builder;

@Builder
public record Mail(String sender,
                   String receiver,
                   String subject,
                   String body) {
}
