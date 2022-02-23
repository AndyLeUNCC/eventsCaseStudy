<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../partials/header.jsp" />

<!-- Main -->
<div class="home-articles max-width-1 m-auto font2">
	<div class="max-width-1 m-auto mx-1 center">
		<h1>Upload File</h1>

		<form method="POST" action="/user/fileUploadSubmit"
			enctype="multipart/form-data">

			<div class="form-box">
				<label for="username"><b>Title file:</b></label> <input id="title"
					type="text" name="title" placeholder=" Title file " value="">
			</div>
			<div class="form-box">
				<label for="file"><b>Select File:</b></label> <input id="file"
					type="file" name="file" placeholder=" " value="">
			</div>

			<div class="form-box">
				<button type="submit" id="submit" class="btn">Submit</button>
				&nbsp; &nbsp;

				<button type="submit" id="cancel" class="btn" formmethod="GET"
					formaction="/user/list">Cancel</button>

			</div>


		</form>

	</div>
</div>

<jsp:include page="../partials/footer.jsp" />