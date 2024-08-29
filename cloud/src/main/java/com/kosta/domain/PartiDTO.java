package com.kosta.domain;

import java.time.LocalDateTime;

import com.kosta.entity.Parti;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PartiDTO {
	
	private int id;
	private UserDTO member;
	private StudyGroupDTO group;
	private LocalDateTime updatedAt;
	private boolean personal;

	public static PartiDTO setPartiDTO(Parti p) {
		return PartiDTO.builder()
				.id(p.getId())
				.member(UserDTO.setUserDTO(p.getMember()))
				.group(StudyGroupDTO.setStudyGroupDTO(p.getGroup()))
				.updatedAt(p.getUpdatedAt())
				.build();
				
	}
}
