<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en" xmlns:th="http://www.thymeleaf.org">

<jsp:include page="../partials/header.jsp" />
<div class="home-articles max-width-1 m-auto font2">
	<div class="content-left">
		<h2>Users List</h2>
		<br>
		<form method="GET" action="/admin/list" name="search" id="search">
			<div class="form-box">
				Filter: <input type="text" name="keyword" id="keyword" size="50"
					value="${keyword}" /> &nbsp; <input type="submit" value="Search"
					class="btn" /> &nbsp;
			</div>
			<!-- <input type="button" value="Clear"
				id="btnClear" onclick="clearSearch()" class="btn" /> -->
		</form>

		<br>
		<form method="GET" action="/admin/new" name="new_user" id="new_user">
			<div class="form-box">
				<input type="submit" value="Add User" class="btn" />
			</div>
		</form>

	</div>
	<div class="content-left">
		<table class="table-rsvp">
			<thead>
				<tr>
					<th>User ID</th>
					<th>User Name</th>
					<th>Email</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Phone</th>

					<th colspan="2">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listUsers}" var="user">
					<tr>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.email}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.phone}</td>
						<td><a class="btn btn-primary" role="button"
							href="/admin/edit?id=${user.id}">Edit</a></td>
						<td><a class="btn btn-danger" role="button"
							href="/admin/delete?id=${user.id}" onclick="return confirm('Are you sure you want to delete this item?');">Delete</a></td>

					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>
	<%-- <div >
	Total Items: ${totalItems} - Page ${currentPage} of ${totalPages} &nbsp;
	<span th:each="i:${#numbers.sequence(1, totalPages)}">
		<span> ${i}</span> &nbsp;&nbsp;
	
	</span>
	<a th:if="${currentPage > 1}"
			th:href="/@{'/search/user/page/1'}">First</a> <span
			th:unless="${currentPage > 1}">First</span> <a
			th:if="${currentPage > 1}"
			th:href="/@{'/page/' + ${currentPage - 1}}">Previous</a> <span
			th:unless="${currentPage > 1}">Previous</span> 
		<a th:if="${currentPage < totalPages}" th:href="/@{'/page/' + ${currentPage + 1}}">Next</a>
		<span th:unless="${currentPage < totalPages}">Next</span>
		<a th:if="${currentPage < totalPages}" th:href="/@{'/page/' + ${totalPages}}">Last</a>
		<span th:unless="${currentPage < totalPages}">Last</span>
	</div> --%>
</div>
<jsp:include page="../partials/footer.jsp" />