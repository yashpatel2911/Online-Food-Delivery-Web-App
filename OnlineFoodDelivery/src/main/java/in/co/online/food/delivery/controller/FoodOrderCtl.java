package in.co.online.food.delivery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.online.food.delivery.bean.BaseBean;
import in.co.online.food.delivery.bean.FoodOrderBean;
import in.co.online.food.delivery.bean.RestaurantBean;
import in.co.online.food.delivery.bean.UserBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.exception.DuplicateRecordException;
import in.co.online.food.delivery.model.FoodOrderModel;
import in.co.online.food.delivery.model.RestaurantModel;
import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.DataValidator;
import in.co.online.food.delivery.util.PropertyReader;
import in.co.online.food.delivery.util.ServletUtility;

@WebServlet(name = "FoodOrderCtl", urlPatterns = { "/ctl/FoodOrderCtl" })
public class FoodOrderCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(FoodOrderCtl.class);

	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("FoodOrderCtl validate method start");
        boolean pass = true;

       
		

		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city",
					PropertyReader.getValue("error.require", "City"));
			pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("pinCode"))) {
			request.setAttribute("pinCode",
					PropertyReader.getValue("error.require", "Pin Code"));
			pass = false;
		}else if(!DataValidator.isInteger(request.getParameter("pinCode"))){
			request.setAttribute("pinCode", PropertyReader.getValue("error.invalid","Pin Code"));
			pass=false;
		} 
		
		
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address",
					PropertyReader.getValue("error.require", "Address"));
			pass = false;
		}
		
		
		
        log.debug("FoodOrderCtl validate method end");
        return pass;
    }
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("FoodOrderCtl Method populatebean Started");

		FoodOrderBean bean = new FoodOrderBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

	

		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setPinCode(DataUtility.getString(request.getParameter("pinCode")));

		bean.setAddress(DataUtility.getString(request.getParameter("address")));

		

		populateDTO(bean, request);

		log.debug("FoodOrderCtl Method populatebean Ended");

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FoodOrderCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
		
		HttpSession session=request.getSession();
		long onfdDI=DataUtility.getLong(request.getParameter("onfdDI"));
			session.setAttribute("FID", onfdDI);
	       FoodOrderModel model = new FoodOrderModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            FoodOrderBean bean;
	            try {
	                bean = model.findByPk(id);
	                ServletUtility.setOpration("Edit", request);
	                ServletUtility.setBean(bean, request);
	            } catch (ApplicationException e) {
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }

	        ServletUtility.forward(getView(), request, response);
	        log.debug("FoodOrderCtl doGet method end");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FoodOrderCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		FoodOrderModel model=new FoodOrderModel();
		long id=DataUtility.getLong(request.getParameter("id"));	
		HttpSession session=request.getSession();
 		UserBean uBean=(UserBean)session.getAttribute("user");
 		RestaurantModel rModel=new RestaurantModel();		
		if(OP_SAVE.equalsIgnoreCase(op)){			
			FoodOrderBean bean=(FoodOrderBean)populateBean(request);
			bean.setUserId(uBean.getId());
			long FIDD=(long)session.getAttribute("FID");
			bean.setFoodId(FIDD);			
				try {
					if(id>0){						
					/*model.update(bean);*/
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						long pk=model.add(bean);
						//bean.setId(id);
						ServletUtility.setSuccessMessage("Food is successfully Order", request);
						ServletUtility.forward(getView(), request, response);
					}
	              
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.forward(OFDView.ERROR_VIEW, request, response);
					return;
				
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(),
						request);
			}
			
		}else if (OP_DELETE.equalsIgnoreCase(op)) {
			FoodOrderBean bean=(FoodOrderBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(OFDView.FOOD_ORDER_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OFDView.FOOD_ORDER_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(OFDView.FOOD_ORDER_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("FoodOrderCtl doPost method end");
	}

	@Override
	protected String getView() {
		return OFDView.FOOD_ORDER_VIEW;
	}

}
