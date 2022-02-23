<jsp:include page="../partials/header.jsp" />

User
<b>${usernameSessionKey }</b>
is logged in ( from session )
<br>
Logged in user =
<b>${loggedInUser }</b>
( from response model )
<br>
<a href="/logout">Logout</a>

<jsp:include page="../partials/footer.jsp" />