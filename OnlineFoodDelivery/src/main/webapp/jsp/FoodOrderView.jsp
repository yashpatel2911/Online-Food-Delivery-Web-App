<%@page import="in.co.online.food.delivery.controller.FoodOrderCtl"%>
<%@page import="in.co.online.food.delivery.util.DataUtility"%>
<%@page import="in.co.online.food.delivery.util.ServletUtility"%>
<%@page import="in.co.online.food.delivery.model.FoodOrderModel"%>
<%@page import="in.co.online.food.delivery.bean.RestaurantBean"%>
<%@page import="in.co.online.food.delivery.model.RestaurantModel"%>
<%@page import="in.co.online.food.delivery.bean.FoodBean"%>
<%@page import="in.co.online.food.delivery.bean.FoodOrderBean"%>
<%@page import="in.co.online.food.delivery.model.FoodModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Food Order</title>
</head>
<body>
<%@ include file="Header.jsp" %>
 <section class="ftco-about d-md-flex">
 		<% 
 				long FoodId =(long)session.getAttribute("FID");
 				FoodModel fModel=new FoodModel();
 				FoodBean fBean=fModel.findByPk(FoodId);
 				
 				RestaurantModel rModel=new RestaurantModel();
 				RestaurantBean rBean=rModel.findByPk(fBean.getRestaurantId());
 				
 				FoodOrderModel foModel=new FoodOrderModel();

 		%>
 
    	<div class="one-half img" style="background-image: url(../images/<%=fBean.getImage()%>);"></div>
    	<div class="one-half ftco-animate">
        <div class="heading-section ftco-animate ">
          <h2 class="mb-4"><%=fBean.getFoodName()%></h2>
          <h4 class="mb-4"><b><font color="red"> <%=ServletUtility.getErrorMessage(request)%></font></b>
		<b><font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font></b></h4>
        </div>
        <div>
        		
  				<p>Restaurant Name:-&nbsp;<%=fBean.getRestaurantName()%>&nbsp;Restaurant Contect No:-&nbsp;<%=rBean.getContectNo()%></p>
  				<p>MRP:-&nbsp;<%=fBean.getFoodPrice()%>&nbsp;Discount:-&nbsp;<%=fBean.getDisscount()%></p>
  				<p>Price:-&nbsp;<%=fBean.getFinalPrice()%></p>
  				<p><%=fBean.getFoodDescription()%></p>
  			</div>
    	</div>
    </section>
<section class="ftco-section contact-section">
	<div class="container mt-5">

		<h2>Fill This Detail</h2>

		<div class="row block-9">
			<div class="col-md-1"></div>
			<div class="col-md-6 ftco-animate">
				<form action="<%=OFDView.FOOD_ORDER_CTL%>" method="post" 
					class="contact-form">

					<jsp:useBean id="bean"
						class="in.co.online.food.delivery.bean.FoodOrderBean"
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
								<input type="text" name="city" class="form-control"
									value="<%=DataUtility.getStringData(bean.getCity())%>"
									placeholder="Enter City"> <font color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<input type="text" name="pinCode" class="form-control"
									value="<%=DataUtility.getStringData(bean.getPinCode())%>"
									placeholder="Enter PinCode"> <font color="red"> <%=ServletUtility.getErrorMessage("pinCode", request)%></font>
							</div>
						</div>
					</div>		
							
							
					
					<div class="form-group">
						 <textarea name="address" id="" cols="30" rows="5" class="form-control" placeholder="Address"><%=DataUtility.getStringData(bean.getAddress())%></textarea>
						 <font	color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font>
					</div>
					

					<div class="form-group">
						<input type="submit" name="operation"
							class="btn btn-primary py-3 px-5"
							value="<%=FoodOrderCtl.OP_SAVE%>">
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
<%@ include file="Footer.jsp" %>
</body>
</html>