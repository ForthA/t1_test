package ru.tarasov.testing.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.tarasov.testing.dto.TaskDto;
import ru.tarasov.testing.service.NotificationService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaTaskConsumer {

    private final NotificationService notificationService;

    @KafkaListener(id = "${kafka.consumer.group-id}",
            topics = "${kafka.topic.mail_notification}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listener(@Payload List<TaskDto> messageList,
                         Acknowledgment ack){
        log.debug("Task consumer: starting");
        try {
            notificationService.sendMail(messageList);
        } finally {
            ack.acknowledge();
        }
        log.debug("Task consumer: done");
    }

}
