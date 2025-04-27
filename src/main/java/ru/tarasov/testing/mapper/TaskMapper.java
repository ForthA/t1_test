package ru.tarasov.testing.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.tarasov.testing.dto.TaskDto;
import ru.tarasov.testing.dto.TaskRequestDto;
import ru.tarasov.testing.dto.TaskUpdateDto;
import ru.tarasov.testing.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto taskToTaskDto(Task task);

    Task taskDtoToTask(TaskDto taskDto);

    Task taskRequestDtoToTask(TaskRequestDto taskRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTaskFromTaskUpdateDto(TaskUpdateDto taskUpdateDto, @MappingTarget Task task);

}
