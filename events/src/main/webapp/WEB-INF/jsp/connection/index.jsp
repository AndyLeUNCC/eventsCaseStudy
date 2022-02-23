<jsp:include page="../partials/header.jsp" />

<!-- The main  -->

<% if(categories.length > 0) {%>
<div class="row">

	<% categories.forEach(category => { %>
	<div class="column">
		<div class="card">
			<h3><%= category.name %></h3>
			<% if(connections.length > 0) {%>
			<% connections.forEach(connection => { %>
			<% if(category.name === connection.category) {%>
			<p>
				<a href="/connections/<%=connection.id%>"><%= connection.name %></a>
			</p>
			<% }; %>

			<% }); %>
		</div>
	</div>

	<%} else {%>
	<p>There are no connections to display.</p>
	<%} %>
	<% }); %>
	<%} else {%>
	<p>There are no categories to display.</p>
	<%} %>

</div>

<jsp:include page="../partials/footer.jsp" />
