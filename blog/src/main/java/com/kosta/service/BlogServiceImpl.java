package com.kosta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.entity.Article;
import com.kosta.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
	private final BlogRepository blogRepository;
	
	@Override
	public Article addArticle(Article article) throws Exception {
		return blogRepository.save(article);
		
	}

	@Override
	public List<Article> findAll() throws Exception {
		
		return blogRepository.findAll();
	}

	@Override
	public Article findById(int id) throws Exception {
		Optional<Article> arti = blogRepository.findById(id);
		Article article = arti.orElseThrow(()->new Exception("뺌"));
		return article;
		
	}

	@Override
	public void deleteArticle(int id) throws Exception {
		Article article = findById(id);
		blogRepository.deleteById(article.getId());
		
	}

	@Override
	public void modifyPage(int id) throws Exception {
		blogRepository.findById(id);
	}

	@Override
	public Article modifyArticle(Article article) throws Exception {
		Article article2 = findById(article.getId());
		article2.setContent(article.getContent());
		article2.setTitle(article.getTitle());
		Article arti = blogRepository.save(article2);
		return arti;
		
	}

	@Override
	public List<Article> findByTitle(String title) throws Exception {
		List<Article> list = blogRepository.findByTitleContains(title);
		return list;
	}

}
