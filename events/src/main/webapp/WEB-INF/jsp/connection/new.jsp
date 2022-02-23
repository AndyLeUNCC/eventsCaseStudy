<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../partials/header.jsp" />

<!-- Main -->
<div class="home-articles max-width-1 m-auto font2">
	<div class="max-width-1 m-auto mx-1 ">
				
		<h1>New Connection</h1>

 
		<form method="POST" action="/connection/new" name="connection_form"
			id="connection_form">
			<input type="hidden" name="id" value="${formBeanKey.id}"> <input
				type="hidden" name="host_id" value="${user.id}">


			<div class="form-box">
				<label for="category_id"><b>Topic</b></label> <select class=""
					name="category_id" id="category_id">
					<c:forEach items="${listCategories}" var="category">
						<option value="${category.id}">${category.name }</option>
					</c:forEach>

				</select>

			</div>
			<div class="form-box">
				<label for="name"><b>Title</b></label> <input id="name" name="name"
					type="text" placeholder=" title " value="${formBeanKey.name}" required>
			</div>
			
			<div class="form-box">
				<label for="details"><b>Details</b></label>
				<textarea name="details" id="details" cols="30" rows="2"
					placeholder="description" required>${formBeanKey.details}</textarea>
			</div>
			<div class="form-box">
				<label for="location"><b>Where</b></label> <input id="location"
					name="location" type="text" placeholder=" location "
					value="${formBeanKey.location}" required>
			</div>
			<div class="form-box">
				<label for="date"><b>When</b></label> <input id="date" name="date"
					type="date" value="${formBeanKey.date}" required>
			</div>
			<div class="form-box">
				<label for="startTime"><b>Start</b> </label> <input id="startTime"
					name="start_time" type="time" value="${formBeanKey.start_time}" required>
			</div>
			<div class="form-box">
				<label for="endTime"><b>End</b> </label> <input id="endTime"
					name="end_time" type="time" value="${formBeanKey.end_time}" required>
			</div>
			<div class="form-box">
				<label for="image"><b>Image URL</b></label> <input id="image"
					name="image_url" type="text" placeholder=" image URL "
					value="${formBeanKey.image_url}" required>
			</div>
			<div class="form-box">
				<button type="submit" id="submit" class="btn">Create Connection</button>
				&nbsp; &nbsp;

				<button type="submit" id="cancel" class="btn" formmethod="GET"
					formaction="/connection/list">Cancel</button>
			</div>
		</form>
	</div>

</div>
<jsp:include page="../partials/footer.jsp" />
