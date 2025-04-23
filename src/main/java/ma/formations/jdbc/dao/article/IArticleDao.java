package ma.formations.jdbc.dao.article;

import java.util.List;

import ma.formations.jdbc.service.model.Article;

public interface IArticleDao {
	List<Article> findAll();
	Article getById(long id);
	void save(Article article);
	void update(Article article);
	void delete(long id);
	List<Article> searchArticle(String description);
}
