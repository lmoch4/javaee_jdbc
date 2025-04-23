<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Gestion des articles </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/articles.do">Consulter</a>
					<a class="dropdown-item" href="#">Autre fonctionnalit�</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a>
				</div></li>
		</ul>
		<form action="${pageContext.request.contextPath}/articles.do" method="get" class="d-flex mb-3" role="search">
			<input class="form-control me-2" type="search" name="search" placeholder="Search" aria-label="Search" />
			<button class="btn btn-outline-primary" type="submit">🔍</button>
		</form>

		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath}/logout.do">LogOut <span
					class="sr-only"></span>
			</a></li>
		</ul>
	</div>
</nav>