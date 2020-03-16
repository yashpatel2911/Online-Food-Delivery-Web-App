<%@page import="in.co.online.food.delivery.bean.DessertBean"%>
<%@page import="in.co.online.food.delivery.model.DessertModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.online.food.delivery.bean.DessertChartBean"%>
<%@page import="in.co.online.food.delivery.controller.DessertChartListCtl"%>
<%@page import="in.co.online.food.delivery.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dessert Chart List</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<section class="ftco-section">
<form action="<%=OFDView.DESSERT_CHART_LIST_CTL%>" method="post">
      <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 heading-section ftco-animate text-center">
            <h2 class="mb-4">Your Dessert Chat List</h2>
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
									placeholder="Enter Dessert Name"> 
							</div>
						</div>
						
						<div class="col-md-6">
						<div class="form-group">
						<input type="submit" name="operation"
							class="btn btn-primary py-3 px-5"
							value="<%=DessertChartListCtl.OP_SEARCH%>"> 
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
					
					
					DessertChartBean bean = null;
					
					List list = ServletUtility.getList(request);
					
					Iterator<DessertChartBean> it = list.iterator();
					
					while (it.hasNext()) {
						bean = it.next();
						
						DessertModel dModel=new DessertModel();
						DessertBean dBean=dModel.findByPk(bean.getDessertId());
				%>
        
          <div class="col-md-4 d-flex ftco-animate">
          	<div class="blog-entry align-self-stretch">
              <a href="blog-single.html" class="block-20" style="background-image: url('../images/<%=dBean.getImage()%>');">
              </a>
              <div class="text py-4 d-block">
              	<div class="meta">
                  <div><a href="">MRP:-&nbsp;<%=dBean.getFoodPrice()%>&nbsp;Rs</a></div>
                  <div><a href="">Discount:-&nbsp;<%=dBean.getDisscount()%>%</a></div>
                  <div><a href="" class="meta-chat">Price:-&nbsp;<span><%=dBean.getFinalPrice()%>&nbsp;Rs</span></a></div>
                </div>
                <h3 class="heading mt-2"><a href=""><%=bean.getDessertName() %></a></h3>
                <p><%=dBean.getDessertDescription()%></p>
                
                <p class="price">
                 <a href="DessertChartListCtl?dsD=<%=bean.getId()%>" class="ml-2 btn btn-white btn-outline-white">Delete</a>
                <a href="DessertOrderCtl?onfdDDI=<%=bean.getDessertId()%>" class="ml-2 btn btn-white btn-outline-white">Order</a>
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