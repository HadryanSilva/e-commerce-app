package br.com.hadryan.ecommerce.notification.service;

import br.com.hadryan.ecommerce.notification.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.hadryan.ecommerce.notification.model.EmailTemplate.ORDER_CONFIRMATION;
import static br.com.hadryan.ecommerce.notification.model.EmailTemplate.PAYMENT_CONFIRMATION;

@RequiredArgsConstructor
@Slf4j
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal totalAmount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name()
        );

        helper.setFrom("contact@hadryan.com");
        final String templateName = ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", totalAmount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);
        helper.setSubject(ORDER_CONFIRMATION.getSubject());

        try {
            var htmlTemplate = templateEngine.process(templateName, context);
            helper.setText(htmlTemplate, true);
            helper.setTo(destinationEmail);

            mailSender.send(message);
            log.info("Email of order confirmation sent successfully to: {}", destinationEmail);
        } catch (MessagingException e) {
            log.warn("Error sending email to: {}", destinationEmail, e);
        }
    }

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name()
        );

        helper.setFrom("contact@hadryan.com");
        final String templateName = PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);
        helper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try {
            var htmlTemplate = templateEngine.process(templateName, context);
            helper.setText(htmlTemplate, true);
            helper.setTo(destinationEmail);

            mailSender.send(message);
            log.info("Email of payment success sent successfully to: {}", destinationEmail);
        } catch (MessagingException e) {
            log.warn("Error sending email to: {}", destinationEmail, e);
        }
    }

}
