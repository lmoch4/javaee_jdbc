package ma.formations.jdbc.service;

import java.util.List;

import ma.formations.jdbc.service.model.Article;

public interface IService {
	
	Boolean checkAccount(String username,String password);
	List<Article> getAllArticle();
}
