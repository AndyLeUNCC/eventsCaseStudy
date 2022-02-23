<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en" xmlns:th="http://www.thymeleaf.org">

<jsp:include page="../partials/header.jsp" />
<div class="home-articles max-width-1 m-auto font2">
	<div class="content-left">
		<h2>Categories List</h2>
		<br>
		<form method="GET" action="/category/list" name="search" id="search">
			<div class="form-box">
				Filter: <input type="text" name="keyword" id="keyword" size="50"
					value="${keyword}" /> &nbsp; <input type="submit" value="Search"
					class="btn" /> &nbsp;
			</div>
			<!-- <input type="button" value="Clear"
				id="btnClear" onclick="clearSearch()" class="btn" /> -->
		</form>

		<br>
		<form method="GET" action="/category/new" name="new_category"
			id="new_category">
			<div class="form-box">
				<input type="submit" value="Add New" class="btn" />
			</div>
		</form>

	</div>
	<div class="content-left">
		<table class="table-rsvp">
			<thead>
				<tr>
					<th>Category ID</th>
					<th>Category Name</th>

					<th colspan="2">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listCategories}" var="category">
					<tr>
						<td>${category.id}</td>
						<td>${category.name}</td>
						<td><a class="btn btn-primary" role="button"
							href="/category/edit?id=${category.id}">Edit</a></td>
						<td><a class="btn btn-danger" role="button"
							href="/category/delete?id=${category.id}">Delete</a></td>

					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>
</div>
<jsp:include page="../partials/footer.jsp" />