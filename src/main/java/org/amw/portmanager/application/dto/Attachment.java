package org.amw.portmanager.application.dto;

import java.io.ByteArrayOutputStream;

public record Attachment(
        String fileType,
        String fileName,
        ByteArrayOutputStream data
) {
}
