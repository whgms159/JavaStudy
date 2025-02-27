package com.kosta.service;

import java.util.List;

import com.kosta.domain.PartiDTO;
import com.kosta.entity.Parti;
import com.kosta.entity.User;

public interface PartiService {

	List<Parti> getAllParti();

	void escapeParti(int id);


	Parti findById(int id) throws Exception;

	void save(Parti parti);


	List<PartiDTO> getMyParti(User user) throws Exception;


	

}
