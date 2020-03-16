package in.co.online.food.delivery.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import in.co.online.food.delivery.bean.BaseBean;
import in.co.online.food.delivery.bean.FoodBean;
import in.co.online.food.delivery.bean.RestaurantBean;
import in.co.online.food.delivery.bean.UserBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.exception.DuplicateRecordException;
import in.co.online.food.delivery.model.RestaurantModel;
import in.co.online.food.delivery.model.UserModel;
import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.DataValidator;
import in.co.online.food.delivery.util.PropertyReader;
import in.co.online.food.delivery.util.ServletUtility;

@WebServlet(name = "RestaurantCtl", urlPatterns = { "/ctl/RestaurantCtl" })
@MultipartConfig(maxFileSize = 16177215)
public class RestaurantCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(RestaurantCtl.class);
	@Override
	protected void preload(HttpServletRequest request) {
		
		UserModel model = new UserModel();
		UserBean bean=new UserBean();

		try {
			bean.setRoleId(2L);
			List l = model.search(bean);
			request.setAttribute("restUserList", l);
		
		} catch (ApplicationException e) {
			
			log.error(e);
		}

	}

	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("RestaurantCtl validate method start");
        boolean pass = true;

        String login = request.getParameter("login");
		

		if (DataValidator.isNull(request.getParameter("restName"))) {
			request.setAttribute("restName",
					PropertyReader.getValue("error.require", "Restaurant Name"));
			pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address",
					PropertyReader.getValue("error.require", "Address"));
			pass = false;
		} 

		
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue(
					"error.require", "City"));
			pass = false;
		}
		


		if (DataValidator.isNull(request.getParameter("rating"))) {
			request.setAttribute("rating",
					PropertyReader.getValue("error.require", "Rating"));
			pass = false;

		} 

		if (DataValidator.isNull(request.getParameter("contectNo"))) {
			request.setAttribute("contectNo", PropertyReader.getValue("error.require","Contect No"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		
		if ("-----RestaurantOwnerName-----".equalsIgnoreCase(request.getParameter("RestaurantOwner"))) {
			request.setAttribute("RestaurantOwner", PropertyReader.getValue("error.require", "Restaurant Owner Name"));
			pass = false;
		}
		
        log.debug("RestaurantCtl validate method end");
        return pass;
    }
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("RestaurantCtl Method populatebean Started");

		RestaurantBean bean = new RestaurantBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRestaurantUserId(DataUtility.getLong(request.getParameter("RestaurantOwnerName")));

		bean.setRestaurantName(DataUtility.getString(request.getParameter("restName")));

		bean.setAddress(DataUtility.getString(request.getParameter("address")));

		bean.setCity(DataUtility.getString(request.getParameter("city")));

		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		bean.setRating(DataUtility.getString(request.getParameter("rating")));
		bean.setContectNo(DataUtility.getString(request.getParameter("contectNo")));

		populateDTO(bean, request);

		log.debug("RestaurantCtl Method populatebean Ended");

		return bean;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("RestaurantCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       RestaurantModel model = new RestaurantModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            RestaurantBean bean;
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
	        log.debug("RestaurantCtl doGet method end");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("RestaurantCtl doPost method start");
			String op=DataUtility.getString(request.getParameter("operation"));
			RestaurantModel model=new RestaurantModel();
			long id=DataUtility.getLong(request.getParameter("id"));
			
			String savePath = DataUtility.getString(PropertyReader.getValue("path")); 
			
			 File fileSaveDir = new File(savePath);
	      if (!fileSaveDir.exists()) {
	          fileSaveDir.mkdir();
	      }
	      
	      
	      Part part = request.getPart("image");
	      
	      String fileName = extractFileName(part);
	      part.write(savePath + File.separator + fileName);
	      
	      String filePath = fileName;
			
			
			if(OP_SAVE.equalsIgnoreCase(op)){
				
				RestaurantBean bean=(RestaurantBean)populateBean(request);
				
				bean.setImage(filePath);
					try {
						if(id>0){
							
							
							
						model.update(bean);
						ServletUtility.setOpration("Edit", request);
						ServletUtility.setSuccessMessage("Data is successfully Updated", request);
		                ServletUtility.setBean(bean, request);

						}else {
							
							
							
							long pk=model.add(bean);
							//bean.setId(id);
							ServletUtility.setSuccessMessage("Data is successfully Saved", request);
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
				RestaurantBean bean=(RestaurantBean)populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(OFDView.RESTAURANT_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
			}else if (OP_CANCEL.equalsIgnoreCase(op)) {
				ServletUtility.redirect(OFDView.RESTAURANT_LIST_CTL, request, response);
				return;
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OFDView.RESTAURANT_CTL, request, response);
			return;
	}
					
			
			ServletUtility.forward(getView(), request, response);
			 log.debug("RestaurantCtl doPost method end");
	}
	
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

	@Override
	protected String getView() {
		return OFDView.RESTAURANT_VIEW;
	}

}
