<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../partials/header.jsp" />
<!-- Main -->
<div class="home-articles max-width-1 m-auto font2">
<!-- 	<div class="connection-form">
 -->		<form action="connections.html" method="get" name="connection_form"
			id="connection_form" class="connection-detail-form">
			<div class="block-connection-detail">

				<div class="block-connection">
					<label><b>${connection.name}</b></label>

				</div>
				<div class="middle-block-connection-detail">

					<div class="block-connection">
						<img src="${connection.image_url}" alt="coffee icon">
					</div>
					<div class=".block-connection shift-right">

						<div class="form-box">
							<label>Hosted by ${host_user.firstName} ${host_user.lastName }</label>

						</div>
						<div class="form-box">
							<label class="form-box label"><b>Date: ${connection.date}</b></label>
						</div>

						<div class="form-box">

							<label class="form-box label">Duration: ${connection.start_time} -
								${connection.end_time}</label>
						</div>

						<div class="form-box">

							<label class="form-box label">Location: ${connection.location}</label>
						</div>


					</div>
				</div>
				<div class="block-connection">
					<label><b>Details: </b></label> <label>${connection.details}</label>
				</div>

			</div>

			<!-- Number of people are going this event -->
			<!-- if user is connection.host_id then show this div
				else show the RSVP div Yes, No, Attending
			 -->
			<c:choose>
				<c:when test="${user.id == null }">
					<div class="block-connection-update-delete shift-right">
						<div class="form-box">
							<b>
								<p>${listRsvp.size() } people are going</p>
							</b>
						</div>
						<div class="form-box">
						<p><a class="btn btn-primary" role="button"
								href="/login/login">Login</a> to use the RSVP feature</p> <br>
							
							
						</div>
					</div>
				</c:when>
				<c:when test="${user.id == connection.host_id }">
					<div class="block-connection-update-delete shift-right">
						<div class="form-box">
							<b>
								<p>${listRsvp.size() } people are going</p>
							</b>
						</div>
						<div class="form-box">
								
							<a class="btn btn-primary" role="button"
								href="/connection/edit?id=${connection.id}">Edit</a>
							<a class="btn btn-danger" role="button"
								href="/connection/delete?id=${connection.id}">Delete</a>
						</div>
					</div>
				</c:when>
				
				<c:otherwise>
					<!-- show the RSVP div Yes, No, Maybe -->
					<div class="block-connection-update-delete shift-right">
						<div class="form-box">
							<b>
								<p>${listRsvp.size() } people are going</p>
							</b>
						</div>
						<div class="form-box">
						<p>RSVP to attend!</p> <br>
								
							<a class="btn btn-primary" role="button"
								href="/rsvp/new?id=${connection.id}&attend=yes">Yes</a>
							<a class="btn btn-danger" role="button"
								href="/rsvp/new?id=${connection.id}&attend=no">No</a>
							<a class="btn btn-primary" role="button"
								href="/rsvp/new?id=${connection.id}&attend=maybe">Maybe</a>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			
		</form>
	</div>




<!-- </div>
 -->


<jsp:include page="../partials/footer.jsp" />
