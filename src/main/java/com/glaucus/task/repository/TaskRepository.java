package com.glaucus.task.repository;

/*
 * @author pankaj This is repository for making calls to db.
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glaucus.task.domain.NumberTable;

@Repository
public interface TaskRepository extends JpaRepository<NumberTable, Integer> {

}
