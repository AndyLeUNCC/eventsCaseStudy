<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
<link rel="stylesheet" href="/pub/css/style.css">
<link rel="stylesheet" href="/pub/css/materialized.css">
<link rel="stylesheet" href="/pub/css/sub_menu.css">
<!-- link to the API service of search photos from flirk -->
<link rel="stylesheet" href="/pub/lightbox/css/lightbox.min.css">
<script src="/pub/lightbox/js/lightbox-plus-jquery.min.js"></script>
<script src="/pub/js/search_photos.js"></script> 

<!-- Import for using slider animation -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bxslider/4.2.15/jquery.bxslider.min.css"
	rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bxslider/4.2.15/jquery.bxslider.min.js"></script>
<script src="/pub/js/slider.js"></script>


</head>

<body>


	<header class="navigation max-width-1 m-auto">
		<!-- The navigation menu -->
		<div class="nav-left">
			<a href="/"> <span class="logo"> <img
					src="/pub/Images/bloglogo.png" alt="Blog Logo"></span>
			</a>
		</div>
		<div class="navbar">
			<sec:authorize access="!isAuthenticated()">
				<a href='/signUp/new' class="active">Sign Up</a>
				<a href='/login/login' class="active">Login</a>
			</sec:authorize>

			<sec:authorize access="isAuthenticated()">
				<a href='/login/logout' class="active">Logout</a>

				<sec:authorize access="hasAuthority('USER')">
					<div class="dropdown" aria-label="U Menu">
						<button class="dropbtn">
							User Account <i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content"
							aria-label="Dropdown content of Entertainment">
							<a href="/user/profile" class="active">Profile</a> <a
								href="/user/edit?id=${user.id}" class="active">Edit Account</a>
							<a href='/user/searchFile' class="active">Search File</a>
							<a href='/user/uploadFile' class="active">Upload File</a>
						</div>
					</div>

				</sec:authorize>
				<a href="/connection/new" class="active">Start a new connection</a>

				<sec:authorize access="hasAuthority('ADMIN')">
					<div class="dropdown" aria-label="Admin Menu">
						<button class="dropbtn">
							Admin <i class="fa fa-caret-down"></i>
						</button>
						<div class="dropdown-content"
							aria-label="Dropdown content of Entertainment">
							<a href="/admin/list" class="active">Users</a> <a
								href="/category/list" class="active">Categories</a> <a
								href="/connection/adminlist" class="active">Connections</a>


						</div>
					</div>

				</sec:authorize>
			</sec:authorize>




		</div>
	</header>
	<nav class="navigation max-width-1 m-auto">

		<div class="navbar">
			<a href="/connection/list" class="connection">Connections</a>
			<sec:authorize access="isAuthenticated()">
				<a href="/user/profile" class="welcome"> Welcome,
					${user.firstName} ${user.lastName}</a>
			</sec:authorize>


		</div>
	</nav>
	<c:choose>
		<c:when test="${formBeanKey.errorMessages.size() > 0 }">
			
				<c:forEach items="${formBeanKey.errorMessages}" var="message">
					<div class='error'>${message}</div>
				</c:forEach>
				
			
		</c:when>
		<c:when test="${formBeanKey.successMessages.size() > 0 }">
			<c:forEach items="${formBeanKey.successMessages}" var="message">
				<div class='success'>${message}</div>
			</c:forEach>
			
		</c:when>
	</c:choose> 
	<%-- if(errorMessages.length > 0) {%>
	<div class="content-left">
		<% errorMessages.forEach(message=>{%>
		<div class='error'>
			<%=message%>
		</div>
		<%});%>
		<%}%>

		<% if(successMessages.length > 0) {%>

		<% successMessages.forEach(message=>{%>
		<div class='success'>
			<%=message%>
		</div>
		<%});%>
	</div>
	<%}%>
	--%> 