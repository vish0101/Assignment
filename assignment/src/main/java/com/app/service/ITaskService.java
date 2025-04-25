package com.app.service;

import java.util.List;


import com.app.dto.TaskDto;
import com.app.entity.Task;

public interface ITaskService {
	
	
	Task addTask(TaskDto t);
	List<Task> getAllTask();
	Task getTaskById(long id);
	String updateTask(long id, TaskDto updatedTask);
	String deleteTaskById(Long taskId);
	List<Task> getAllDeletedTask();

}
