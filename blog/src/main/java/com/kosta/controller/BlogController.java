package com.kosta.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosta.entity.Article;
import com.kosta.service.BlogService;

import lombok.RequiredArgsConstructor;


@RequestMapping("/blog/*")
@Controller
@RequiredArgsConstructor
public class BlogController {
	private final BlogService blogService;
	
	@GetMapping("/add")//추가 화면
	public String addPage() throws Exception{
		return "form";
	}
	@PostMapping("/add")//실제 추가
	public String addArticle(Article article) throws Exception{
		blogService.addArticle(article);
		return "redirect:/blog/list";
	}
	
	@GetMapping("/list")//목록
	public String articleList(Article article, Model model) throws Exception{
		List<Article> articleList=blogService.findAll();
		model.addAttribute("list", articleList);
		return "list";
	}
	
	
	@GetMapping("/detail/{id}")//디테일 화면
	public String articleDetail(@PathVariable("id") int id, Model model)throws Exception{
		try {
			Article article = blogService.findById(id);
			model.addAttribute("article", article);
			return "detail";
		} catch (Exception e) {
		
			e.printStackTrace();
			model.addAttribute("errMsg", e.getMessage());
			return "error";
		}
	}

	
	@DeleteMapping("/delete/{id}")//삭제
	public String deleteArticle(@PathVariable("id") int id) throws Exception{
		blogService.deleteArticle(id);
		return "redirect:/blog/list";
	}
	@GetMapping("/modify/{id}")//수정 화면
	public String modifyPage(@PathVariable("id") int id,Article article, Model model) throws Exception{
		blogService.modifyPage(id);
		model.addAttribute("article", article);
		return "form";
		
	}
	
	
	@PatchMapping("/modify")//실제 수정 
	public String modifyArticle(Article article) throws Exception{
		blogService.modifyArticle(article);
		return "redirect:/blog/list";
		
	}
	//검색
	@GetMapping("/search")
	public String searchByTitle(@RequestParam(value="title", required=false) String title, Model model) throws Exception {
		List<Article> articleList = blogService.findByTitle(title);
		model.addAttribute("list", articleList);
		return "list";
	}
	
}
