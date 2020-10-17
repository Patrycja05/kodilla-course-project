package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask(){
        //Given
        TaskDto taskDto = new TaskDto(1L, "test_title", "test_content");

        //When
        Task task = taskMapper.maptoTask(taskDto);

        //Then
        assertEquals(1, task.getId());
        assertEquals("test_title", task.getTitle());
        assertEquals("test_content", task.getContent());
    }

    @Test
    public void testMapToTaskDto(){
        //Given
        Task task = new Task(1L, "test_title", "test_content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(1, taskDto.getId());
        assertEquals("test_title", taskDto.getTitle());
        assertEquals("test_content", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList(){
        //Given
        Task task1 = new Task(1L, "test_title", "test_content");
        Task task2 = new Task(2L, "test_title2", "test_content2");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals("test_title", taskDtoList.get(0).getTitle());
        assertEquals("test_content2", taskDtoList.get(1).getContent());
    }
}
