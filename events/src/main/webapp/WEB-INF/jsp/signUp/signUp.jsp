<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../partials/header.jsp" />

<!-- Main -->
<div class="home-articles max-width-1 m-auto font2">
	<div class="max-width-1 m-auto mx-1 center">
		<h1>Sign Up Account</h1>
		
		<form method="POST" action="/signUp/new">
			<input type="hidden" name="id" value="${formBeanKey.id}">

			<div class="form-box">
				<label for="username"><b>User Name</b></label> <input id="username"
					type="text" name="username" placeholder=" User Name "
					value="${formBeanKey.username}" required>
			</div>
			<div class="form-box">
				<label for="email"><b>Email</b></label> <input id="email"
					type="text" name="email" placeholder=" Email"
					value="${formBeanKey.email}" required>
			</div>
			<div class="form-box">
				<label for="firstname"><b>First Name</b></label> <input
					id="firstname" type="text" name="firstName"
					placeholder=" First Name " value="${formBeanKey.firstName}" required>
			</div>
			<div class="form-box">
				<label for="lastname"><b>Last Name</b></label> <input id="lastname"
					type="text" name="lastName" placeholder=" Last Name "
					value="${formBeanKey.lastName}" required>
			</div>

			<div class="form-box">
				<label for="password"><b>Password</b></label> <input id="password"
					type="text" name="password" placeholder=" Password "
					value="${formBeanKey.password}" required>
			</div>

			<div class="form-box">
				<label for="confirmPassword"><b>Confirm Password</b></label> <input
					id="confirmPassword" type="text" name="confirmPassword"
					placeholder=" Confirm Password "
					value="${formBeanKey.confirmPassword}" required>
			</div>
			<div class="form-box">
				<button type="submit" id="submit" class="btn">Submit</button>
				&nbsp; &nbsp;

				<button type="submit" id="cancel" class="btn" formmethod="GET"
					formaction="/">Cancel</button>

			</div>


		</form>

	</div>
</div>

<jsp:include page="../partials/footer.jsp" />