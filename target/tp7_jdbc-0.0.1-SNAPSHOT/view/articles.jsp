<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Description</th>
				<th scope="col">Quantit�</th>
				<th scope="col">Prix</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${articles}" var="article">
				<tr>
					<th scope="row">${article.id}</th>
					<td>${article.description}</td>
					<td>${article.quantite}</td>
					<td>${article.price}</td>
					<td><a href="${pageContext.request.contextPath}/articles.do?action=edit&id=${article.id}" type="button" class="btn btn-info">
						Modifier
					</a>
						<a href="${pageContext.request.contextPath}/articles.do?action=delete&id=${article.id}"
						   onclick="return confirm('Voulez-vous vraiment supprimer cet article ?');"
						   type="button" class="btn btn-danger">
						Supprimer
					</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/articles.do?action=new">
		<button type="button" class="btn btn-primary" >Ajouter un article</button>
	</a>
</div>