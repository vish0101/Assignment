package com.app.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.TaskDto;
import com.app.entity.Task;
import com.app.entity.TaskStatus;
import com.app.entity.User;
import com.app.exception.EntityNotFound;
import com.app.exception.ValidationError;
import com.app.repository.ITaskRepository;
import com.app.repository.IUserRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class TaskServiceImpl implements ITaskService {
	
	@Autowired
	ITaskRepository taskRepository;
	
	@Autowired
	IUserRepository userRepository;

	@Override
	public Task addTask(TaskDto taskDto) {
		
		Task t=new Task();
		
		t.setTitle(taskDto.getTitle());
		t.setDescription(taskDto.getDescription());
		t.setStatus(TaskStatus.valueOf(taskDto.getStatus()));
		t.setExpectedStartDateTime(taskDto.getExpectedStartDateTime());
		t.setExpectedEndDateTime(taskDto.getExpectedEndDateTime());
	
		if(t.getTitle()==null || t.getTitle().matches("^[a-zA-Z0-9]+$") || t.getTitle().trim().isEmpty()) {
			throw new ValidationError("Title is mandatory");
		}
		
		if(t.getDescription()==null || t.getDescription().matches("^[a-zA-Z0-9]+$") || t.getDescription().trim().isEmpty()) {
			throw new ValidationError("Discription is mandatory");
		}
		
		if (t.getStatus() == null) {
	        throw new ValidationError("Status is mandatory");
	    }
		
		 // Check for duplicate task
		Optional<Task> duplicate = taskRepository.findDuplicateTask(
	            taskDto.getTitle(),
	            taskDto.getExpectedEndDateTime(),
	            taskDto.getCreatedByUserId()
	    );

	    if (duplicate.isPresent()) {
	        throw new RuntimeException("Duplicate task: Same title and end date already exists for this user.");
	    }
	    
	    User createdBy= userRepository.findById(taskDto.getCreatedByUserId()).orElseThrow(()->new EntityNotFound("created by user not found by id :"+taskDto.getCreatedByUserId()));
	    t.setCreatedBy(createdBy);
	    
	    User assignetTo = userRepository.findById(taskDto.getCreatedByUserId()).orElseThrow(()->new EntityNotFound("assigned to user not found by id :"+taskDto.getCreatedByUserId()));
	    t.setAssignedTo(assignetTo);
	    
	 // Set timestamps
	    t.setCreatedAt(Instant.now());
	    t.setUpdatedAt(Instant.now());

	    // Soft delete flag
	    t.setIsDelete(false);

		
		return taskRepository.save(t);
	}

	@Override
	public List<Task> getAllTask() {
		// TODO Auto-generated method stub
		return taskRepository.findByIsDeleteFalse();
	}

	@Override
	public Task getTaskById(long id) {
	    return taskRepository.findByIdAndIsDeleteFalse(id).orElseThrow(()->new EntityNotFound("Task not found with id :"+id));
	}


	@Override
	public String updateTask(long id, TaskDto updatedTask) {
		Task existingTask = taskRepository.findByIdAndIsDeleteFalse(id).orElseThrow(()->new EntityNotFound("Task not found with id :"+id));
		
		if(updatedTask.getTitle()==null || updatedTask.getTitle().matches("^[a-zA-Z0-9]+$") || updatedTask.getTitle().trim().isEmpty()) {
			throw new ValidationError("Title is mandatory");
		}
		
		if(updatedTask.getDescription()==null || updatedTask.getDescription().matches("^[a-zA-Z0-9]+$") || updatedTask.getDescription().trim().isEmpty()) {
			throw new ValidationError("Discription is mandatory");
		}
		
		if (updatedTask.getStatus() == null) {
	        throw new ValidationError("Status is mandatory");
	    }
		
		 // Check for duplicate task
		
		Optional<Task> duplicate = taskRepository.findDuplicateTask(
				id,
				updatedTask.getTitle(),
				updatedTask.getExpectedEndDateTime(),
				updatedTask.getCreatedByUserId()
	    );

	    if (duplicate.isPresent()) {
	        throw new RuntimeException("Duplicate task: Same title and end date already exists for this user.");
	    }
	    
	    existingTask.setTitle(updatedTask.getTitle());
	    existingTask.setDescription(updatedTask.getDescription());
	    existingTask.setStatus(TaskStatus.valueOf(updatedTask.getStatus()));
	    existingTask.setExpectedStartDateTime(updatedTask.getExpectedStartDateTime());
	    existingTask.setExpectedEndDateTime(updatedTask.getExpectedEndDateTime());
	    
//	    User createdBy= userRepository.findById(updatedTask.getCreatedByUserId()).orElseThrow(()->new EntityNotFound("created by user not found by id :"+updatedTask.getCreatedByUserId()));
//	    existingTask.setCreatedBy(createdBy);
//	    
//	    User assignetTo = userRepository.findById(updatedTask.getCreatedByUserId()).orElseThrow(()->new EntityNotFound("assigned to user not found by id :"+updatedTask.getCreatedByUserId()));
//	    existingTask.setAssignedTo(assignetTo);
	    
	    // Update the timestamp
	    existingTask.setUpdatedAt(Instant.now());

	    // Save the updated task
	    taskRepository.save(existingTask);

	    return "Task updated successfully";
	}

	@Override
	public String deleteTaskById(Long taskId) {
//		if (!confirm) {
//	        return "Deletion not confirmed. Task was not deleted.";
//	    }

	    Task task = taskRepository.findByIdAndIsDeleteFalse(taskId)
	            .orElseThrow(() -> new EntityNotFound("Task not found or already deleted with id: " + taskId));

	    task.setIsDelete(true);
	    task.setUpdatedAt(Instant.now());
	    taskRepository.save(task);

	    return "Task with ID " + taskId + " has been successfully deleted.";
	}

	@Override
	public List<Task> getAllDeletedTask() {
	    return taskRepository.findAllByIsDeleteTrue();
	}

}
