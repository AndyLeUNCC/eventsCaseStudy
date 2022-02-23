<jsp:include page="../partials/header.jsp" />
<style>
input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #04AA6D;
	color: white;
	height: 40px;
	padding: 5px 5px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

img.avatar {
	width: 40%;
	border-radius: 50%;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>
<!-- Main -->
<div class="home-articles max-width-1 m-auto font2">
	<div class="register-photo">
		<div class="form-container">
			<div class="image-holder"></div>
			<h1 style="color: red">${errorMessage}</h1>
			<!-- form submission must align with security config see .loginProcessingUrl -->
			<form action='/login/loginSecurityPost' method="post">
				<h2 class="text-center">
					<strong>Login</strong> to account.
				</h2>
				<div class="form-group">
					<label for="email"><b>Email</b></label> <br> <input
						id="username" class="form-control" type="email" name="username"
						placeholder="Email" required>
				</div>
				<div class="form-group">
					<label for="password"><b>Password</b></label> <input id="password"
						class="form-control" type="password" name="password"
						placeholder="Password" required>
				</div>

				<div class="form-group">
					<button class="btn btn-success btn-block" type="submit">Login</button>
				</div>
				<label> <input type="checkbox" checked="checked"
					name="remember"> Remember me
				</label>
			</form>
		</div>
	</div>
</div>

<jsp:include page="../partials/footer.jsp" />
