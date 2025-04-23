<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ma.formations.jdbc.service.model.Article" %>
<%
    Article article = (Article) request.getAttribute("article");
    boolean isEdit = (article != null);
%>
<html>
<head>
    <title><%= isEdit ? "Modifier" : "Ajouter" %> un Article</title>
    <link href="webjars/bootstrap/4.6.1/css/bootstrap.min.css"	rel="stylesheet">

    <link href="webjars/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">
    <link href="webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="webjars/font-awesome/4.7.0/css/font-awesome.min-jsf.css" rel="stylesheet">
    <link href="webjars/font-awesome/4.7.0/css/font-awesome-jsf.css" rel="stylesheet">

    <link href="webjars/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet">
    <link href="webjars/ionicons/2.0.1/css/ionicons.css" rel="stylesheet">

    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.6.1/js/bootstrap.min.js"></script>

</head>
<body class="bg-light">
<div class="container ">
    <h2 class="text-center mb-4">
        <%= isEdit ? "Modifier" : "Ajouter" %> un Article
    </h2>

    <form action="${pageContext.request.contextPath}/articles.do" method="post" class="w-50 mx-auto shadow p-4 bg-white rounded">
        <% if (isEdit) { %>
        <input type="hidden" name="id" value="<%= article.getId() %>"/>
        <% } %>

        <!-- Description -->
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <div class="input-group">
                <input type="text" class="form-control" id="description" name="description"
                       value="<%= isEdit ? article.getDescription() : "" %>" required>
            </div>
        </div>

        <!-- Quantité -->
        <div class="mb-3">
            <label for="quantite" class="form-label">Quantité</label>
            <div class="input-group">
                <input type="number" class="form-control" id="quantite" name="quantite"
                       step="0.01" value="<%= isEdit ? article.getQuantite() : "" %>" required>
                <span class="input-group-text">pcs</span>
            </div>
        </div>

        <!-- Prix -->
        <div class="mb-3">
            <label for="price" class="form-label">Prix</label>
            <div class="input-group">
                <input type="number" class="form-control" id="price" name="price"
                       step="0.01" value="<%= isEdit ? article.getPrice() : "" %>" required>
                <span class="input-group-text">€</span>
            </div>
        </div>

        <!-- Bouton -->
        <div class="d-grid">
            <button type="submit" class="btn <%= isEdit ? "btn-primary" : "btn-success" %>">
                <%= isEdit ? "Modifier" : "Ajouter" %>
            </button>

            <a href="${pageContext.request.contextPath}/articles.do" class="btn btn-secondary">
                Annuler
            </a>
        </div>
    </form>
</div>
</body>

</html>
