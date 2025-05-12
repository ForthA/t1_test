package ru.tarasov.testing.service;

import ru.tarasov.testing.dto.TaskDto;

import java.util.List;

public interface NotificationService {

    void sendMail(List<TaskDto> tasks);


}
