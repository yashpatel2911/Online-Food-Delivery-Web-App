<%@page import="java.util.Observable"%>
<%@page import="in.co.online.food.delivery.util.ServletUtility"%>
<%@page import="in.co.online.food.delivery.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<section class="ftco-section contact-section">
	<div class="container mt-5">
		<h2>Login</h2>
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
		</font></b> <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
		</font></b>
		<div class="row block-9">

			<div class="col-md-1"></div>
			<div class="col-md-6 ftco-animate">
				<form action="<%=OFDView.LOGIN_CTL%>" method="post"
					class="contact-form">

					<jsp:useBean id="bean"
						class="in.co.online.food.delivery.bean.UserBean" scope="request"></jsp:useBean>

					<%
						String uri = (String) request.getAttribute("uri");
					%>

					<input type="hidden" name="uri" value="<%=uri%>"> <input
						type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedBy()%>"> <input type="hidden"
						name="createdDatetime"
						value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
					<input type="hidden" name="modifiedDatetime"
						value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

					<div class="form-group">
						<input type="text" name="login" class="form-control"
							value="<%=DataUtility.getStringData(bean.getLogin())%>"
							placeholder="Enter Login Id ">
							<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
					</div>


					<div class="form-group">
						<input type="password" name="password" class="form-control" value="<%=DataUtility.getStringData(bean.getPassword()) %>"
							placeholder="Enter Password">
							<font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
					</div>

					<div class="form-group">
						<input type="submit"  name="operation"
							class="btn btn-primary py-3 px-5" value="<%=LoginCtl.OP_SIGN_IN %>">
							
							 <input type="submit" name="operation"
							 class="btn btn-primary py-3 px-5" value="<%=LoginCtl.OP_SIGN_UP %>">
					</div>
					<a href="<%=OFDView.FORGET_PASSWORD_CTL%>"><b>Forget my password</b></a>
				</form>
			</div>
		</div>
	</div>
	</section>
	<%@ include file="Footer.jsp"%>
</body>
</html>