package com.example.ssia.auth.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    @Value("${sendgrid.from}")
    private String senderEmail;

    private final SendGrid sendGrid;

    public void sendTextMail(String receiver, String subject, String contentStr) {
        Email from = new Email(senderEmail);
        Email to = new Email(receiver);
        Content content = new Content("text/plain", contentStr);
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sendGrid.api(request);
            log.info("Email sent to [{}]", receiver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
