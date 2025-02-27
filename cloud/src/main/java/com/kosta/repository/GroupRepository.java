package com.kosta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kosta.entity.StudyGroup;
import com.kosta.entity.User;

@Repository
public interface GroupRepository extends JpaRepository<StudyGroup, Integer> {
	
}
