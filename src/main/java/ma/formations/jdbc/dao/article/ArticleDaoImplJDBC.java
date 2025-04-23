package ma.formations.jdbc.dao.article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ma.formations.jdbc.dao.DatabaseManager;
import ma.formations.jdbc.dao.Utils;
import ma.formations.jdbc.service.model.Article;

public class ArticleDaoImplJDBC implements IArticleDao {

	private int id ;
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
				id = rs.getInt("id");
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

	@Override
	public Article getById(long id) {
		Article article = null;
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM article WHERE id = ?");
			ps.setLong(1, id); // affecter la valeur de l'id

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				article = new Article(
						rs.getLong("id"),
						rs.getString("description"),
						rs.getDouble("quantite"),
						rs.getDouble("price")
				);
			}

			rs.close();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		return article;
	}


	@Override
	public void save(Article article) {
		//Utils.check(article); // teste si tous les attribut sont valide
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO article (description, quantite, price) VALUES ( ?, ?, ?)"
			);

			ps.setString(1, article.getDescription());
			ps.setDouble(2, article.getQuantite());
			ps.setDouble(3, article.getPrice());

			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
	}

	@Override
	public void update(Article article) {
		//Utils.check(article);

		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE article SET description = ?, quantite = ?, price = ? WHERE id = ?"
			);
			ps.setString(1, article.getDescription());
			ps.setDouble(2, article.getQuantite());
			ps.setDouble(3, article.getPrice());
			ps.setLong(4, article.getId());

			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
	}

	@Override
	public void delete(long id) {
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement("DELETE FROM article WHERE id = ?");
			ps.setLong(1, id);

			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
		}
	}

	public List<Article> searchArticle(String description){
		List<Article> list = new ArrayList<>();
		try {
			Connection connection = DatabaseManager.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT * FROM article WHERE description LIKE ?"
			);
			ps.setString(1, "%" + description + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Article a = new Article(
						rs.getLong("id"),
						rs.getString("description"),
						rs.getDouble("quantite"),
						rs.getDouble("price")
				);
				list.add(a);
			}

			rs.close();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Erreur recherche article: " + e.getMessage());
		}
		return list;
	}

}
