package ma.formations.jdbc.presentation.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.formations.jdbc.service.IService;
import ma.formations.jdbc.service.ServiceImpl;
import ma.formations.jdbc.service.model.Article;

@WebServlet("/articles.do")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IService service = new ServiceImpl();


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null) {
			action = "list"; // ou une valeur par défaut
		}
		switch (action){
			case "new":
				doAddArticle(request, response);
				break;
			case "edit":
				doEditArticle(request, response);
				break;
			case "delete":
				doDeleteArticle(request, response);
				break;
			default:
				String search = request.getParameter("search");
				List<Article> articles ;
				if(search != null && !search.trim().isEmpty()){
					articles=service.searchArticle(search);
				}else {
					articles = service.getAllArticle();
				}
				request.setAttribute("articles", articles);
				request.getRequestDispatcher("/view/welcome.jsp").forward(request, response);

		}
	}

	protected void doAddArticle(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.getRequestDispatcher("/view/addarticle.jsp").forward(request, response);
	}

	protected void doEditArticle(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		Article article = service.getById(id);
		request.setAttribute("article", article);
		request.getRequestDispatcher("/view/addarticle.jsp").forward(request, response);
	}

	protected void doDeleteArticle(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		long idDelete = Long.parseLong(request.getParameter("id"));
		service.delete(idDelete);
		response.sendRedirect("articles.do");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String description = request.getParameter("description");
		double quantite = Double.parseDouble(request.getParameter("quantite"));
		double price = Double.parseDouble(request.getParameter("price"));

		if (idStr == null || idStr.isEmpty()) {
			// Ajout
			Article newArticle = new Article();
			newArticle.setDescription(description);
			newArticle.setQuantite(quantite);
			newArticle.setPrice(price);
			service.save(newArticle);
		} else {
			// Modification
			long id = Long.parseLong(idStr);
			Article existing = service.getById(id);
			existing.setDescription(description);
			existing.setQuantite(quantite);
			existing.setPrice(price);
			service.update(existing);
		}

		response.sendRedirect("articles.do");
	}
}
