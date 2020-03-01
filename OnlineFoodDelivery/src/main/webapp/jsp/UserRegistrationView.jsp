<%@page import="in.co.online.food.delivery.controller.UserRegistrationCtl"%>
<%@page import="in.co.online.food.delivery.util.ServletUtility"%>
<%@page import="in.co.online.food.delivery.util.DataUtility"%>
<%@page import="in.co.online.food.delivery.controller.OFDView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<section class="ftco-section contact-section">
	<div class="container mt-5">
	
		<h2>User Registration</h2>
		
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b>
		<b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
		
		<div class="row block-9">
			<div class="col-md-1"></div>
			<div class="col-md-6 ftco-animate">
				<form action="<%=OFDView.USER_REGISTRATION_CTL%>" method="post" class="contact-form">
				
				<jsp:useBean id="bean" class="in.co.online.food.delivery.bean.UserBean"
			scope="request"></jsp:useBean>
				
				<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
				
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<input type="text" name="firstName" class="form-control" value="<%=DataUtility.getStringData(bean.getFirstName())%>"
									placeholder="Enter First Name">
									<font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<input type="text" name="lastName" class="form-control" value="<%=DataUtility.getStringData(bean.getLastName())%>"
									placeholder="Your Last Name">
									<font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
							</div>
						</div>
					</div>
					<div class="form-group">
						<input type="text" name="login" class="form-control" value="<%=DataUtility.getStringData(bean.getLogin())%>"
							placeholder="Enter Login Id">
							<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<input type="password" name="password" class="form-control" value="<%=DataUtility.getStringData(bean.getPassword())%>"
									placeholder="Enter Password">
									<font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<input type="password" name="confirmPassword" value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"
									class="form-control" placeholder="Your Confirm Password">
									<font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
							</div>
						</div>
					</div>
					<div class="form-group">
						<input type="text" name="emailId" class="form-control" value="<%=DataUtility.getStringData(bean.getEmailId())%>"
							placeholder="Email Id">
							<font color="red"> <%=ServletUtility.getErrorMessage("emailId", request)%></font>
					</div>
					<div class="form-group">
						<input type="text" name="mobile" class="form-control" value="<%=DataUtility.getStringData(bean.getMobileNo())%>"
							placeholder="Enter 10 Digits mobile No.">
							<font color="red"> <%=ServletUtility.getErrorMessage("mobile", request)%>
					</div>

					<div class="form-group">
						<input type="submit" name="operation"
							class="btn btn-primary py-3 px-5" value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
							<input type="submit" name="operation"
							class="btn btn-primary py-3 px-5" value="<%=UserRegistrationCtl.OP_RESET%>">
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
	<%@ include file="Footer.jsp"%>
</body>
</html>