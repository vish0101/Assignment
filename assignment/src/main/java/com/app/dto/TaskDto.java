package com.app.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
	
	private Long id;
    private String title;
    private String description;
    private String status;
    
    private Instant  expectedStartDateTime;

    private Instant  expectedEndDateTime;

    
    private Long assignedToUserId;
    private Long createdByUserId;
	

}
