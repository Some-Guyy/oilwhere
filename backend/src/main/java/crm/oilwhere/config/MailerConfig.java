package crm.oilwhere.config;

import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailerConfig {

    @Value("${mail.smtp.host}")
    private String smtpHost;

    @Value("${mail.smtp.port}")
    private int smtpPort;

    @Value("${mail.smtp.user}")
    private String smtpUser;

    @Value("${mail.smtp.password}")
    private String smtpPassword;

    @Bean
    public Mailer mailer() {
        return MailerBuilder
                .withSMTPServer(smtpHost, smtpPort, smtpUser, smtpPassword)
                .buildMailer();
    }
}
