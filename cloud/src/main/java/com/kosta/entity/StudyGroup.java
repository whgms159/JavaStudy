package com.kosta.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@Data
@Entity
@RequiredArgsConstructor
public class StudyGroup {
	
	@Id
	@Column(updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String title;
	
	@JoinColumn(nullable = false, name = "leader_id")
	@ManyToOne
	private User leader;
	
	@Column(name="finished_at")
	private LocalDate finishedAt;
	
	@CreatedDate
	@Column(name="created_at")
	private LocalDateTime createdAt;

	@Column(name = "started_at")
	private LocalDate startedAt;

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	
	
}
