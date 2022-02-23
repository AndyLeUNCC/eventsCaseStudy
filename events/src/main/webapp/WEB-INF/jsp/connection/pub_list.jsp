<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../partials/header.jsp" />

<!-- The main  -->
<div class="home-articles max-width-1 m-auto font2">
	<div class="content-left">
		<h2>Connections List</h2>
		<br>
		<form method="GET" action="/connection/list" name="search" id="search">
			<div class="form-box">
				Filter: <input type="text" name="keyword" id="keyword" size="50"
					value="${keyword}" /> &nbsp; <input type="submit" value="Search"
					class="btn" /> &nbsp;
			</div>

		</form>

	</div>
	<div class="content-left">

		<div class="row">
			<c:forEach items="${listCategories}" var="category">
				<div class="column">
					<div class="card">
						<h3>${category.name }</h3>
						<c:forEach items="${listConnections}" var="connection">
							<c:choose>
								<c:when test="${category.id == connection.category_id }">
									<p>
										<a href="/connection/show?id=${connection.id }">${ connection.name}</a>
									</p>
								</c:when>
							</c:choose>

						</c:forEach>



					</div>
				</div>
			</c:forEach>

		</div>
	</div>
</div>
<jsp:include page="../partials/footer.jsp" />
