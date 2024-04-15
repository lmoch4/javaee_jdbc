package ma.formations.jdbc.dao.article;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.formations.jdbc.dao.DatabaseManager;
import ma.formations.jdbc.service.model.Article;

public class ArticleDaoImplJDBC implements IArticleDao {
	@Override
	public List<Article> findAll() {
		List<Article> articles = new ArrayList<Article>();
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from article");
			while (rs.next()) {
				articles.add(new Article(rs.getLong("id"), rs.getString("description"), rs.getDouble("quantite"),
						rs.getDouble("price")));
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return articles;
	}

}
