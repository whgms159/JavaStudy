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

import com.kosta.domain.StudyGroupDTO;
import com.kosta.entity.StudyGroup;
import com.kosta.entity.User;
import com.kosta.service.GroupService;
import com.kosta.service.UserService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
@RequestMapping("/studygroup")
public class StudyGroupController {
	private final GroupService groupService;
	private final UserService userService;
	
	
	@GetMapping("/list")
	public String getAllGroup(Model model, @AuthenticationPrincipal User user) {
		List<StudyGroupDTO> groupList = groupService.getAllGroup();
		System.out.println(groupList);
		model.addAttribute("list", groupList);
		return "studygroup/list";
	}
	@GetMapping("/add")//추가 화면
	public String addGroupPage(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("user", user);
		return "studygroup/form";
	}
	@PostMapping("/add")//실제 추가
	public String addGroup(StudyGroup sg, @RequestParam("leaderId") int leaderId, @AuthenticationPrincipal User user) {
		sg.setLeader(userService.findById(leaderId));
		groupService.save(sg, user);
		return "redirect:/studygroup/list";
		
	}
	@DeleteMapping("/delete")//삭제
	public String deleteGroup(@RequestParam("id") int id) throws Exception {
		groupService.deleteGroup(id);
		return "redirect:/studygroup/list";
	}
	@GetMapping("/detail") //상세 페이지
	public String detailGroup(@RequestParam("id") int id, Model model, @AuthenticationPrincipal User user) throws Exception {
		StudyGroupDTO sg = groupService.findById(id, user);
		
		System.out.println("내꺼니? " + sg.isMine());
		System.out.println("가입했니? " +sg.isJoined());
		
		model.addAttribute("sg",sg);
		model.addAttribute("user",user);
		return "studygroup/detail";
	}
	

}
