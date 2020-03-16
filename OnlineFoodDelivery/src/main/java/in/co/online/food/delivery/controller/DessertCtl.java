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
import in.co.online.food.delivery.bean.DessertBean;
import in.co.online.food.delivery.bean.RestaurantBean;
import in.co.online.food.delivery.bean.UserBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.exception.DuplicateRecordException;
import in.co.online.food.delivery.model.DessertModel;
import in.co.online.food.delivery.model.RestaurantModel;
import in.co.online.food.delivery.model.UserModel;
import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.DataValidator;
import in.co.online.food.delivery.util.PropertyReader;
import in.co.online.food.delivery.util.ServletUtility;

@WebServlet(name = "DessertCtl", urlPatterns = { "/ctl/DessertCtl" })
@MultipartConfig(maxFileSize = 16177215)
public class DessertCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(DessertCtl.class);
	@Override
	protected void preload(HttpServletRequest request) {
		
		RestaurantModel model = new RestaurantModel();
		 

		try {
			
			List l = model.list();
			request.setAttribute("restList", l);
		
		} catch (ApplicationException e) {
			
			log.error(e);
		}

	}
	
	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("DessertCtl validate method start");
        boolean pass = true;

        String login = request.getParameter("login");
		

		if (DataValidator.isNull(request.getParameter("dessertName"))) {
			request.setAttribute("dessertName",
					PropertyReader.getValue("error.require", "Dessert Name"));
			pass = false;
		} 
		
		
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		
		if ("-----RestaurantName-----".equalsIgnoreCase(request.getParameter("RestaurantName"))) {
			request.setAttribute("RestaurantName", PropertyReader.getValue("error.require", "Restaurant  Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price",
					PropertyReader.getValue("error.require", "Dessert Price"));
			pass = false;
		} 

		
		if (DataValidator.isNull(request.getParameter("discount"))) {
			request.setAttribute("discount", PropertyReader.getValue(
					"error.require", "Discount"));
			pass = false;
		}
		
        log.debug("DessertCtl validate method end");
        return pass;
    }
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("DessertCtl Method populatebean Started");

		DessertBean bean = new DessertBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRestaurantId(DataUtility.getLong(request.getParameter("RestaurantName")));

		bean.setDessertName(DataUtility.getString(request.getParameter("dessertName")));


		bean.setDessertDescription(DataUtility.getString(request.getParameter("description")));
		
		bean.setFoodPrice(DataUtility.getLong(request.getParameter("price")));

		bean.setDisscount(DataUtility.getLong(request.getParameter("discount")));

		

		populateDTO(bean, request);

		log.debug("DessertCtl Method populatebean Ended");

		return bean;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("DessertCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       DessertModel model = new DessertModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            DessertBean bean;
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
	        log.debug("DessertCtl doGet method end");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("DessertCtl doPost method start");
		String op=DataUtility.getString(request.getParameter("operation"));
		DessertModel model=new DessertModel();
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
			
			DessertBean bean=(DessertBean)populateBean(request);
			bean.setImage(filePath);
			
				try {
					if(id>0){
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
	                ServletUtility.setBean(bean, request);

					}else {
						
					       
						
						long pk=model.add(bean);
						
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
			DessertBean bean=(DessertBean)populateBean(request);
		try {
			model.delete(bean);
			ServletUtility.redirect(OFDView.DESSERT_LIST_CTL, request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
		}else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OFDView.DESSERT_LIST_CTL, request, response);
			return;
	}else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(OFDView.DESSERT_CTL, request, response);
		return;
}
				
		
		ServletUtility.forward(getView(), request, response);
		 log.debug("DessertCtl doPost method end");
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
		return OFDView.DESSERT_VIEW;
	}

}
