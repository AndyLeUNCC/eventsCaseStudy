<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../partials/header.jsp" />

<!-- Main -->
<div class="home-articles max-width-1 m-auto font2">
	<div class="max-width-1 m-auto mx-1 center">
		<h1>Edit Category</h1>
		
		<form method="POST" action="/category/edit">
			<input type="hidden" name="id" value="${formBeanKey.id}">

			<div class="form-box">
				<label for="name"><b>Category Name</b></label> <input id="name"
					type="text" name="name" placeholder=" Category Name "
					value="${formBeanKey.name}">
			</div>

			<div class="form-box">
				<button type="submit" id="submit" class="btn">Submit</button>
				&nbsp; &nbsp;

				<button type="submit" id="cancel" class="btn" formmethod="GET"
					formaction="/category/list">Cancel</button>

			</div>


		</form>

	</div>
</div>

<jsp:include page="../partials/footer.jsp" />