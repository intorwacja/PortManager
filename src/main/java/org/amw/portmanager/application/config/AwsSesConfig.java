package org.amw.portmanager.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

import java.net.URI;

@Configuration
public class AwsSesConfig {

    @Value("${aws.access-key}")
    String accessKey;

    @Value("${aws.secret-key}")
    String secretKey;

    @Bean
    public SesClient sesClient() {
        return SesClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                ))
                .region(Region.EU_WEST_3)
                .endpointOverride(URI.create("http://localhost:4566"))
                .build();
    }
}
