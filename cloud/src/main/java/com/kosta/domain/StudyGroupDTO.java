package com.kosta.domain;

import java.time.LocalDate;

import com.kosta.entity.StudyGroup;
import com.kosta.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class StudyGroupDTO {
	private int id;
	private String title;
	private UserDTO leader;
	private LocalDate finishedAt;
	private LocalDate startedAt;
	private boolean mine;
	private boolean joined;
	
	public static StudyGroupDTO setStudyGroupDTO(StudyGroup sg) {
		return StudyGroupDTO.builder()
			.id(sg.getId())
			.title(sg.getTitle())
			.leader(UserDTO.setUserDTO(sg.getLeader()))
			.finishedAt(sg.getFinishedAt())
			.startedAt(sg.getStartedAt())
			.build();
	}
}
