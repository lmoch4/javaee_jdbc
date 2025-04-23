package ma.formations.jdbc.service;

import java.util.List;

import ma.formations.jdbc.dao.DaoImplJDBC;
import ma.formations.jdbc.dao.IDao;
import ma.formations.jdbc.dao.article.ArticleDaoImplJDBC;
import ma.formations.jdbc.dao.article.IArticleDao;
import ma.formations.jdbc.service.model.Article;
import ma.formations.jdbc.service.model.User;

public class ServiceImpl implements IService {
	private IDao dao = new DaoImplJDBC();
	private IArticleDao daoArticle = new ArticleDaoImplJDBC();

	@Override
	public Boolean checkAccount(String username, String password) {
		User u = dao.getUserByUsername(username);
		if (u == null)
			return false;
		return (password.equals(u.getPassword()));
	}

	@Override
	public List<Article> getAllArticle() {
		return daoArticle.findAll();
	}

	@Override
	public Article getById(long id) {
		return daoArticle.getById(id);
	}

	@Override
	public void save(Article article) {
		daoArticle.save(article);
	}

	@Override
	public void update(Article article) {
		daoArticle.update(article);
	}

	@Override
	public void delete(long id) {
		daoArticle.delete(id);
	}

	public List<Article> searchArticle(String description) {
		return daoArticle.searchArticle(description);
	}
}
