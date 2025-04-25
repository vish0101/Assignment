package com.app.controller;

//import java.awt.print.Pageable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.TaskDto;
import com.app.entity.Task;
//import com.app.repository.ITaskRepository;
import com.app.service.ITaskService;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class TaskController {

	@Autowired
	private ITaskService taskService;

	// Create a new task
	@PostMapping("/task")
	public ResponseEntity<Task> createTask(@RequestBody @Valid TaskDto taskDto) {
		System.out.println("abcdef");
		Task createdTask = taskService.addTask(taskDto);
		return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
	}

	// Retrieve all active (non-deleted) tasks
	@GetMapping("/task")
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> tasks = taskService.getAllTask();
		return ResponseEntity.ok(tasks);
	}

	// Retrieve a specific task by ID
	@GetMapping("task/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
		Task task = taskService.getTaskById(id);
		return ResponseEntity.ok(task);
	}

	// Update a task
	@PutMapping("task/{id}")
	public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDto taskDto) {
		String result = taskService.updateTask(id, taskDto);
		return ResponseEntity.ok(result);
	}

	// delete a task by ID
	@DeleteMapping("task/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable Long id) {
		String result = taskService.deleteTaskById(id);
		return ResponseEntity.ok(result);
	}

	// Get all deleted tasks
	@GetMapping("/deletedTask/all")
	public ResponseEntity<List<Task>> getAllDeletedTasks() {
		List<Task> deletedTasks = taskService.getAllDeletedTask();
		return ResponseEntity.ok(deletedTasks);
	}

	

}
