<%@page import="in.co.online.food.delivery.controller.ChangePasswordCtl"%>
<%@page import="in.co.online.food.delivery.util.DataUtility"%>
<%@page import="in.co.online.food.delivery.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<%@ include file="Header.jsp"%>
	<section class="ftco-section contact-section">
	<div class="container mt-5">
	
		<h2>Change Password</h2>
		
		<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b>
		<b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b>
		
		<div class="row block-9">
			<div class="col-md-1"></div>
			<div class="col-md-6 ftco-animate">
				<form action="<%=OFDView.CHANGE_PASSWORD_CTL%>" method="post" class="contact-form">
				
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
				
					
					<div class="form-group">
						<input type="password" name="oldPassword" class="form-control" 
							placeholder="Enter Old Password" value=<%=DataUtility
                    .getString(request.getParameter("oldPassword") == null ? ""
                            : DataUtility.getString(request
                                    .getParameter("oldPassword")))%>  >
							<font color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<input type="password" name="newPassword" class="form-control" 
									placeholder="Enter New Password" value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
                            : DataUtility.getString(request.getParameter("newPassword")))%>>
									<font color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<input type="password" name="confirmPassword" 
									class="form-control" placeholder="Your Confirm Password" value=<%=DataUtility.getString(request
                    .getParameter("confirmPassword") == null ? "" : DataUtility
                    .getString(request.getParameter("confirmPassword")))%> >
									<font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
							</div>
						</div>
					</div>
					

					<div class="form-group">
						<input type="submit" name="operation"
							class="btn btn-primary py-3 px-5" value="<%=ChangePasswordCtl.OP_SAVE%>">
							
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
	<%@ include file="Footer.jsp"%>
</body>
</html>