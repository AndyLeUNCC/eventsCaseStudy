<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en" xmlns:th="http://www.thymeleaf.org">

<jsp:include page="../partials/header.jsp" />
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

		<br>
		<form method="GET" action="/connection/new" name="new_connection"
			id="new_connection">
			<div class="form-box">
				<input type="submit" value="Add New" class="btn" />
			</div>
		</form>

	</div>
	<div class="content-left">
		<table class="table-rsvp">
			<thead>
				<tr>
					<th>Connection ID</th>
					<th>Connection Name</th>
					<th>Location</th>
					<th>Date</th>
					<th>Start Time</th>
					<th>End Time</th>

					<th colspan="2">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listConnections}" var="connection">
					<tr>
						<td>${connection.id}</td>
						<td>${connection.name}</td>
						<td>${connection.location}</td>
						<td>${connection.date}</td>
						<td>${connection.start_time}</td>
						<td>${connection.end_time}</td>
						<td><a class="btn btn-primary" role="button"
							href="/connection/edit?id=${connection.id}">Edit</a></td>
						<td><a class="btn btn-danger" role="button"
							href="/connection/delete?id=${connection.id}">Delete</a></td>

					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>
</div>
<jsp:include page="../partials/footer.jsp" />