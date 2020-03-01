<%@page import="in.co.online.food.delivery.controller.LoginCtl"%>
<%@page import="in.co.online.food.delivery.controller.OFDView"%>
<%@page import="in.co.online.food.delivery.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nothing+You+Could+Do" rel="stylesheet">

    <link rel="stylesheet" href="/OnlineFoodDelivery/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="/OnlineFoodDelivery/css/animate.css">
    
    <link rel="stylesheet" href="/OnlineFoodDelivery/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/OnlineFoodDelivery/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/OnlineFoodDelivery/css/magnific-popup.css">

    <link rel="stylesheet" href="/OnlineFoodDelivery/css/aos.css">

    <link rel="stylesheet" href="/OnlineFoodDelivery/css/ionicons.min.css">

    <link rel="stylesheet" href="/OnlineFoodDelivery/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/OnlineFoodDelivery/css/jquery.timepicker.css">

    
    <link rel="stylesheet" href="/OnlineFoodDelivery/css/flaticon.css">
    <link rel="stylesheet" href="/OnlineFoodDelivery/css/icomoon.css">
    <link rel="stylesheet" href="/OnlineFoodDelivery/css/style.css">
</head>
<body>

<%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }

%>

<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" >
	    <div class="container">
		      <a class="navbar-brand" href="<%=OFDView.WELCOME_CTL%>">Online<br><small>Food Delivery</small></a>
		      
	      <div class="collapse navbar-collapse">
	        <ul class="navbar-nav ml-auto">
	        <li class="nav-item active"><a href="<%=OFDView.WELCOME_CTL%>" class="nav-link">Home</a></li>
	        
	         <%
      			  if (userLoggedIn) {
   			 %>
	          <li class="nav-item"><a href="<%=OFDView.MY_PROFILE_CTL%>" class="nav-link">My Profile</a></li>
	          <li class="nav-item"><a href="<%=OFDView.CHANGE_PASSWORD_CTL%>" class="nav-link">Change Password</a></li>
	          <%if(userBean.getRoleId()==3){ %>
	          <li class="nav-item"><a href="<%=OFDView.FOOD_CHART_LIST_CTL%>" class="nav-link">Food Cart</a></li>
	          <li class="nav-item"><a href="<%=OFDView.DESSERT_CHART_LIST_CTL%>" class="nav-link">Dessert Cart</a></li>
	          <%} %>
	          <li class="nav-item"><a href="<%=OFDView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>" class="nav-link"><%=welcomeMsg%>&nbsp;LogOut</a></li>
	          <%}else{ %>
	          <li class="nav-item"><a href="<%=OFDView.LOGIN_CTL%>" class="nav-link">SignIn</a></li>
	          <li class="nav-item"><a href="<%=OFDView.USER_REGISTRATION_CTL%>" class="nav-link">SignUp</a></li>
	          <li class="nav-item"><a href="" class="nav-link"><%=welcomeMsg%></a></li>
	          <%}%>
	          
	        </ul>
	      </div>
		  </div>
	  </nav>
	  
	  <%if(userLoggedIn){%>
	  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
		     
		      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
		        <span class="oi oi-menu"></span> Menu
		      </button>
	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	       
	         
	          <%if(userBean.getRoleId()==1){ %>
	          <li class="nav-item"><a href="<%=OFDView.RESTAURANT_OWNER_CTL%>" class="nav-link">Restaurant Owner</a></li>
	          <li class="nav-item"><a href="<%=OFDView.RESTAURANT_CTL%>" class="nav-link">Restaurant</a></li>
	          <li class="nav-item"><a href="<%=OFDView.RESTAURANT_OWNER_LIST_CTL%>" class="nav-link">Owner LIst</a></li>
	          <li class="nav-item"><a href="<%=OFDView.RESTAURANT_LIST_CTL%>" class="nav-link">Restaurant List</a></li>
	          <%}else if(userBean.getRoleId()==2){ %>
	           <li class="nav-item"><a href="<%=OFDView.DESSERT_CTL%>" class="nav-link">Dessert</a></li>
	           <li class="nav-item"><a href="<%=OFDView.DESSERT_LIST_CTL%>" class="nav-link">Dessert List</a></li>
	     		 <li class="nav-item"><a href="<%=OFDView.CATEGORY_CTL%>" class="nav-link">Category</a></li>
	         	<li class="nav-item"><a href="<%=OFDView.FOOD_CTL%>" class="nav-link">Food</a></li>
	         	<li class="nav-item"><a href="<%=OFDView.FOOD_LIST_CTL%>" class="nav-link">Food List</a></li>
	          	<li class="nav-item"><a href="<%=OFDView.FOOD_ORDER_LIST_CTL%>" class="nav-link">Food Order List</a></li>
	         	 <li class="nav-item"><a href="<%=OFDView.DESSERT_ORDER_LIST_CTL%>" class="nav-link">Dessert Order List</a></li>
	          <%}else if(userBean.getRoleId()==3){ %>
	          <li class="nav-item"><a href="<%=OFDView.DESSERT_LIST_CTL%>" class="nav-link">Dessert List</a></li>
	          <li class="nav-item"><a href="<%=OFDView.FOOD_LIST_CTL%>" class="nav-link">Food List</a></li>
	          
	          <li class="nav-item"><a href="<%=OFDView.FOOD_ORDER_LIST_CTL%>" class="nav-link">Food Order List</a></li>
	          <li class="nav-item"><a href="<%=OFDView.DESSERT_ORDER_LIST_CTL%>" class="nav-link">Dessert Order List</a></li>
	          <%} %>
	        </ul>
	      </div>
		  </div>
	  </nav>
	  <%} %>
    <!-- END nav -->
</body>
</html>