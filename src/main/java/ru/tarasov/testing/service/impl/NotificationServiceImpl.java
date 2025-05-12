package ru.tarasov.testing.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.tarasov.testing.dto.TaskDto;
import ru.tarasov.testing.service.NotificationService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    @Value("${spring.mail.recipient}")
    private String recipient;

    @Override
    public void sendMail(List<TaskDto> tasks) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(recipient);
        mailMessage.setText(formMessage(tasks));
        mailMessage.setSubject("Changes");
        try{
            javaMailSender.send(mailMessage);
        }

        catch (Exception e){
            log.error("Error while sending mail to {}", recipient);
        }
    }

    private String formMessage(List<TaskDto> tasks){
        return "Tasks changed: \n" +
                tasks;
    }

}
