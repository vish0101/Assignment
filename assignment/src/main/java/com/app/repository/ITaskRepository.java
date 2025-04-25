package com.app.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entity.Task;


@Repository
public interface ITaskRepository extends JpaRepository<Task	, Long> {
	
	@Query("SELECT t FROM Task t WHERE t.title = :title AND t.expectedEndDateTime = :expectedEndDateTime AND t.createdBy.id = :userId AND t.isDelete = false")
	Optional<Task> findDuplicateTask(@Param("title") String title,
	                                 @Param("expectedEndDateTime") Instant expectedEndDateTime,
	                                 @Param("userId") Long userId);

	  
	  @Query("SELECT t FROM Task t WHERE t.title = :title AND t.expectedEndDateTime = :expectedEndDateTime AND t.createdBy.id = :userId AND t.isDelete = false "+
	           "AND t.id <> :id")
	    Optional<Task> findDuplicateTask(@Param("id") long id ,
	    		@Param("title") String title,
	    		@Param("expectedEndDateTime") Instant  expectedEndDateTime , @Param("userId") long userId);
	  
	  List<Task> findByIsDeleteFalse();
	  
	  Optional<Task> findByIdAndIsDeleteFalse(Long id);

	  List<Task> findAllByIsDeleteTrue();


}
