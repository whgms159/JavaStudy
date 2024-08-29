package com.kosta.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.domain.PartiDTO;
import com.kosta.entity.Parti;
import com.kosta.entity.User;
import com.kosta.service.GroupService;
import com.kosta.service.PartiService;
import com.kosta.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mystudy/*")
@RequiredArgsConstructor
public class MyStudyController {
	private final PartiService partiService;
	private final GroupService gs;
	private final UserService us;
	
	
	@GetMapping("/list")//내가 참여중인 스터디 목록
	public String partiList(Model model, @AuthenticationPrincipal User user)throws Exception {
	
		// 전체 스터디 목록
		List<PartiDTO>	 pList= partiService.getMyParti(user);
		model.addAttribute("partiList", pList);
		return "mystudy/list";
	}
	@GetMapping("/detail/{id}")//세부 내용
	public String partiDetail(@PathVariable("id") int id, Model model) throws Exception {
		Parti parti = partiService.findById(id);
		model.addAttribute("parti", parti);
		return "mystudy/detail";
	}
	
	@DeleteMapping("/delete")//스터티 탈퇴
	public String escapeGroup(@RequestParam("id") int id) {
		partiService.escapeParti(id);
		return "redirect:/mystudy/list";
	}
	@PostMapping("/add")//스터디 가입
	public String joinGroup( @RequestParam("id") int id ) throws Exception {
		Parti pd = partiService.findById(id);
		
		return "redirect:/mystudy/list";
	}
	
	
	
}
