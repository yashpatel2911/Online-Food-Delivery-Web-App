<%@page import="in.co.online.food.delivery.util.DataUtility"%>
<%@page import="in.co.online.food.delivery.bean.FoodBean"%>
<%@page import="in.co.online.food.delivery.model.FoodModel"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.online.food.delivery.bean.FoodOrderBean"%>
<%@page import="in.co.online.food.delivery.controller.FoodOrderListCtl"%>
<%@page import="in.co.online.food.delivery.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Food Order List</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="ftco-section">
<form action="<%=OFDView.FOOD_ORDER_LIST_CTL%>" method="post">
      <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 heading-section ftco-animate text-center">
            <h2 class="mb-4">Food Order List</h2>
            <b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
          </div>
        </div>
        <div class="container">
    		<div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 heading-section ftco-animate text-center">
            <div class="row">
						<div class="col-md-6">
						<div class="form-group">
								<input type="text" name="name" class="form-control"
									value="<%=ServletUtility.getParameter("name", request)%>"
									placeholder="Enter Food Name"> 
							</div>
						</div>
						
						<div class="col-md-6">
						<div class="form-group">
						<input type="submit" name="operation"
							class="btn btn-primary py-3 px-5"
							value="<%=FoodOrderListCtl.OP_SEARCH%>"> 
							</div>
							
						</div>
					</div>
					</div>		
          </div>
        </div>
        
        <div class="row d-flex">
        	<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					
					
					FoodOrderBean bean = null;
					
					List list = ServletUtility.getList(request);
					
					Iterator<FoodOrderBean> it = list.iterator();
					
					while (it.hasNext()) {
						bean = it.next();
						
						FoodModel dModel=new FoodModel();
						FoodBean dBean=dModel.findByPk(bean.getFoodId());
				%>
        
          <div class="col-md-4 d-flex ftco-animate">
          	<div class="blog-entry align-self-stretch">
              <a href="blog-single.html" class="block-20" style="background-image: url('../images/<%=dBean.getImage()%>');">
              </a>
              <div class="text py-4 d-block">
              	<div class="meta">
              	<div><a href="">OrderId:-&nbsp;<%=bean.getOrderId()%></a></div>
                  <div><a href="">MRP:-&nbsp;<%=dBean.getFoodPrice()%>&nbsp;Rs</a></div>
                  <div><a href="">Discount:-&nbsp;<%=dBean.getDisscount()%></a></div>
                  <div><a href="" class="meta-chat">Price:-&nbsp;<span><%=dBean.getFinalPrice()%>&nbsp;Rs</span></a></div>
                </div>
                <p>City:-&nbsp;<%=bean.getCity()%>&nbsp;PinCode:-&nbsp;<%=bean.getPinCode()%></p>
                <p>Date:-&nbsp;<%=bean.getDateTime()%></p>
                <h5 class="heading mt-2"><a href="">Customer Name:-&nbsp;<%=bean.getUserName()%></a></h5>
                <h3 class="heading mt-2"><a href="">Food Name:-&nbsp;<%=bean.getFoodName()%></a></h3>
                <p><%=bean.getAddress()%></p>
                
                <p class="price">
                <%UserBean uBean=(UserBean)session.getAttribute("user"); %>
                <%if(uBean.getRoleId()==3){%>
                <a href="FoodOrderListCtl?foD=<%=bean.getId()%>" class="ml-2 btn btn-white btn-outline-white">Delete</a>
                <%} %>
                </p>
              </div>
            </div>
          </div>
          
          <%} %>
          
          
        </div>
      </div>
      <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
      </form>
    </section>

<%@ include file="Footer.jsp" %>
</body>
</html>