<jsp:include page="./partials/header.jsp" />
<!-- Main -->
<div class="home-articles max-width-1 m-auto font2">

	<!-- Contact Us -->
	<div class="content-left">
		<p>*****************************************************</p>
		<h3>Contact Us</h3>
		<p>Location - 218 South Tryon Street, Charlotte, NC 28001</p>
		<p>Phone-(704)687-8622</p>
		<br>
		<p>*****************************************************</p>

	</div>
	<!-- Contact Form -->
	<div class="content-left">
		<h3>Contact Form</h3>
		<form action="/">
			<div class="form-box">
				<label for="email"><b>Email</b></label> <input id="email"
					type="text" name="email" placeholder=" Email" value="">
			</div>
			<div class="form-box">
				<label for="fname"><b>Your Name</b></label> <input type="text"
					id="fname" name="firstname" placeholder="Your name..">
			</div>
			<!-- <div class="form-box">
				<label for="country"><b>Country<b></b></label> <select id="country"
					name="country">
					<option value="australia">Australia</option>
					<option value="canada">Canada</option>
					<option value="usa">USA</option>
				</select>
			</div> -->
			<div class="form-box">
				<label for="subject"><b>Subject</b></label>
				<textarea id="subject" name="subject"
					placeholder="Write something.." style="height: 200px"></textarea>
			</div>
			<div class="form-box">
				<input type="submit" value="Submit" class="btn">
			</div>
		</form>
	</div>

</div>
<jsp:include page="./partials/footer.jsp" />

