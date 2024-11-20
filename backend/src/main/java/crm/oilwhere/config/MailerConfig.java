package crm.oilwhere.config;

import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the SMTP mailer.
 * <p>
 * This class uses the Simple Java Mail library to create a {@link Mailer} bean
 * for sending emails via an SMTP server. SMTP server details are provided
 * through application properties.
 * </p>
 */
@Configuration
public class MailerConfig {

    /** The SMTP host address, loaded from application properties. */
    @Value("${mail.smtp.host}")
    private String smtpHost;

    /** The SMTP server port, loaded from application properties. */
    @Value("${mail.smtp.port}")
    private int smtpPort;

    /** The SMTP username, loaded from application properties. */
    @Value("${mail.smtp.user}")
    private String smtpUser;

    /** The SMTP password, loaded from application properties. */
    @Value("${mail.smtp.password}")
    private String smtpPassword;

    /**
     * Configures and provides a {@link Mailer} bean for sending emails.
     * <p>
     * The {@link Mailer} is built using the Simple Java Mail library with SMTP server
     * settings (host, port, username, and password) loaded from application properties.
     * </p>
     *
     * @return a configured {@link Mailer} instance for sending emails.
     */
    @Bean
    public Mailer mailer() {
        return MailerBuilder
                .withSMTPServer(smtpHost, smtpPort, smtpUser, smtpPassword)
                .buildMailer();
    }
}
