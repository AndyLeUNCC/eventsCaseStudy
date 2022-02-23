<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../partials/header.jsp" />

<div class="home-articles max-width-1 m-auto font2">
	<div class="content-left">

		<!-- The user's connections section -->
		<h2>${user.firstName} ${user.lastName }'s Connections</h2>
		<br>
		<c:choose>
			<c:when test="${listConnectionProfile.size() > 0 }">
				<table class="table-connection">
					<tr>
						<th>Connection</th>
						<th>Category</th>
						<th colspan="2">Actions</th>
					</tr>

					<c:forEach items="${listConnectionProfile}" var="connection">
						<tr>
							<td><a href='/connection/show?id=${connection.id}'>
									<p>${ connection.name}</p>
							</a></td>
							<td>
								<p>${connection.categoryName}</p>

							</td>
							<td><a class="btn btn-primary" role="button"
								href="/connection/edit?id=${connection.id}">Edit</a></td>
							<td><a class="btn btn-danger" role="button"
								href="/connection/delete?id=${connection.id}">Delete</a></td>
							
						</tr>
					</c:forEach>

				</table>
			</c:when>
			<c:otherwise>
				<p>You are currently hosting no connections</p>
			</c:otherwise>
		</c:choose>
		
	</div>
	
	
	<div class="content-left">
		<!-- The user's RSVP section -->
		<h2>${user.firstName} ${user.lastName }'s RSVPs</h2>
		<br>
		
		<%-- <%if (rsvps.length > 0){%>
		<table class="table-rsvp">
			<tr>
				<th>Connection</th>
				<th>Category</th>
				<th>Going</th>
				<th>Actions</th>
			</tr>

			<%rsvps.forEach(rsvp => {%>
			<tr>
				<td>
					<p><%=rsvp.connection.name%></p>
				</td>
				<td>
					<p><%=rsvp.connection.category%>
					</p>

				</td>
				<td>
					<p><%=rsvp.attending%>
					</p>

				</td>
				<td>
					<form action="" method="post" name="connection_form"
						id="connection_form" class="">
						<button type="submit" id="delete" class="btn" formmethod="POST"
							formaction="/connections/<%= rsvp.connection._id%>/rsvp/<%= rsvp.id%>?_method=DELETE">Delete</button>
						<button type="submit" id="update" class="btn" formmethod="GET"
							formaction="/connections/<%= rsvp.connection._id%>">Update</button>
					</form>
				</td>
			</tr>
			<%});%>
		</table>

		<%} else {%>
		<p>You have not rsvped any connections</p>
		<%}%> --%>
	</div>
</div>
<jsp:include page="../partials/footer.jsp" />
