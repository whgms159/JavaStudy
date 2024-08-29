package com.kosta.entity;

import java.time.LocalDateTime;

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

@Entity
@RequiredArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Parti {
	
	@Id
	@Column(updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JoinColumn(name = "member_id")
	@ManyToOne
	private User member;
	
	@JoinColumn(name = "group_id")
	@ManyToOne
	private StudyGroup group;
	
	@LastModifiedDate
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
}
