package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    public void getTasksTest() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(dbService.getAllTasks())).thenReturn(taskDtoList);

        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1l, "Title", "Content");
        Task task = new Task(1L, "Task1", "Content1");

        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(dbService.getTask(taskDto.getId())).thenReturn(Optional.of(task));

        // When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Title")))
                .andExpect(jsonPath("$.content", is("Content")));


    }

    @Test
    public void deleteTaskTest() throws Exception {
        //Given

        //Whem & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

    }

    @Test
    public void updateTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1, "Title", "Content");
        Task task = new Task(1, "Task1", "Content1");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);
        when(taskMapper.maptoTask(any(TaskDto.class))).thenReturn(task);

        //Whem & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    public void createTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1, "Title", "Content");
        Task task = new Task(1, "Task1", "Content1");

        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(dbService.saveTask(any(Task.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //Whem & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }
}
