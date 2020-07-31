package com.crud.tasks;

import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Method;

@SpringBootApplication
public class TasksApplication  {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TasksApplication.class, args);
		Method method = TrelloClient.class.getDeclaredMethod("getTrelloBoards");
		method.setAccessible(true);
	}
}
