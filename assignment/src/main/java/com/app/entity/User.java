package com.app.entity;

import java.util.List;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(nullable = false)
	private String timezone;

	@Column(name = "is_active", nullable = false)
	private boolean isActive;
	
//	@OneToMany(mappedBy = "createdBy", cascade=CascadeType.ALL,orphanRemoval = true,fetch=FetchType.EAGER)
//	private List<Task> taskCreated;
//	
//	public Task addTaskCreated(Task task) {
//		taskCreated.add(task);
//		task.setCreatedBy(this);
//		
//		return task;
//	}
//	
//	@OneToMany(mappedBy = "assignedTo", cascade=CascadeType.ALL,orphanRemoval = true,fetch=FetchType.EAGER)
//	private List<Task> taskAssigned;
//	
//	public Task addTaskAssigned(Task task) {
//		taskAssigned.add(task);
//		task.setAssignedTo(this);
//		
//		return task;
//	}
}



//@OneToMany(mappedBy = "admin",cascade=CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
//private List<Course> courses;
//	
//public Course addCourse(Course course) {
//	courses.add(course); 
//	course.setAdmin(this);
//	return course;
//}