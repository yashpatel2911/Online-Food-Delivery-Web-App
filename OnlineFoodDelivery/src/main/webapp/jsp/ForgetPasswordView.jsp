<%@page import="in.co.online.food.delivery.controller.ForgetPasswordCtl"%>
<%@page import="in.co.online.food.delivery.util.DataUtility"%>
<%@page import="in.co.online.food.delivery.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<section class="ftco-section contact-section">
	<div class="container mt-5">
		<h2>Forget Password</h2>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<div class="row block-9">

			<div class="col-md-1"></div>
			<div class="col-md-6 ftco-animate">
				<form action="<%=OFDView.FORGET_PASSWORD_CTL%>" method="post"
					class="contact-form">

					

					<div class="form-group">
						<input type="text" name="login" class="form-control"
							value="<%=ServletUtility.getParameter("login", request)%>"
							placeholder="Enter Email Id">
							<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
					</div>

					<div class="form-group">
						<input type="submit"  name="operation"
							class="btn btn-primary py-3 px-5" value="<%=ForgetPasswordCtl.OP_GO %>">
							
							 
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
	<%@ include file="Footer.jsp"%>
</body>
</html>