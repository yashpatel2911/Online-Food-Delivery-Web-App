package in.co.online.food.delivery.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import in.co.online.food.delivery.bean.BaseBean;
import in.co.online.food.delivery.bean.CategoryBean;
import in.co.online.food.delivery.bean.DessertBean;
import in.co.online.food.delivery.bean.FoodBean;
import in.co.online.food.delivery.bean.RestaurantBean;
import in.co.online.food.delivery.bean.UserBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.exception.DuplicateRecordException;
import in.co.online.food.delivery.model.CategoryModel;
import in.co.online.food.delivery.model.FoodModel;
import in.co.online.food.delivery.model.RestaurantModel;
import in.co.online.food.delivery.model.UserModel;
import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.DataValidator;
import in.co.online.food.delivery.util.PropertyReader;
import in.co.online.food.delivery.util.ServletUtility;

@WebServlet(name = "FoodCtl", urlPatterns = { "/ctl/FoodCtl" })
@MultipartConfig(maxFileSize = 16177215)
public class FoodCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(FoodCtl.class);
	@Override
	protected void preload(HttpServletRequest request) {
		
		RestaurantModel rModel=new RestaurantModel();
		CategoryModel cModel=new CategoryModel();

		HttpSession session=request.getSession();
		UserBean uBean=(UserBean)session.getAttribute("user");
		
		
		
		try {
			
			RestaurantBean rBean=rModel.findByUserId(uBean.getId());
			
			CategoryBean cBean=new CategoryBean();
			cBean.setRestaurantId(rBean.getId());
			
			List l2= cModel.search(cBean);
			request.setAttribute("catList", l2);
		
		} catch (ApplicationException e) {
			
			log.error(e);
		}

	}

	@Override
    protected boolean validate(HttpServletRequest request) {
		log.debug("FoodCtl validate method start");
        boolean pass = true;

       
		

		if (DataValidator.isNull(request.getParameter("foodName"))) {
			request.setAttribute("foodName",
					PropertyReader.getValue("error.require", "Food Name"));
			pass = false;
		} 
		
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price",
					PropertyReader.getValue("error.require", "Food Price"));
			pass = false;
		} 

		
		if (DataValidator.isNull(request.getParameter("discount"))) {
			request.setAttribute("discount", PropertyReader.getValue(
					"error.require", "Discount"));
			pass = false;
		}
		


		
		
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description",
					PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		
		
		
		
		
		
		
		
		if ("-----CategoryName-----".equalsIgnoreCase(request.getParameter("CategoryName"))) {
			request.setAttribute("CategoryName", PropertyReader.getValue("error.require", "Category Name"));
			pass = false;
		}
		
        log.debug("FoodCtl validate method end");
        return pass;
    }
	
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("FoodCtl Method populatebean Started");

		FoodBean bean = new FoodBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

	
		
		bean.setCategoryId(DataUtility.getLong(request.getParameter("CategoryName")));
		
		bean.setFoodPrice(DataUtility.getLong(request.getParameter("price")));

		bean.setDisscount(DataUtility.getLong(request.getParameter("discount")));
		
		bean.setFoodName(DataUtility.getString(request.getParameter("foodName")));



		bean.setFoodDescription(DataUtility.getString(request.getParameter("description")));

	

		populateDTO(bean, request);

		log.debug("FoodCtl Method populatebean Ended");

		return bean;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("FoodCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
	        
	       FoodModel model = new FoodModel();
	        long id = DataUtility.getLong(request.getParameter("id"));
	        ServletUtility.setOpration("Add", request);
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            FoodBean bean;
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
	        log.debug("FoodCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("FoodCtl doPost method start");
			String op=DataUtility.getString(request.getParameter("operation"));
			FoodModel model=new FoodModel();
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
            
            HttpSession session=request.getSession();
    		UserBean uBean=(UserBean)session.getAttribute("user");
    		RestaurantModel rModel=new RestaurantModel();
            
    		
            
			if(OP_SAVE.equalsIgnoreCase(op)){
				
				FoodBean bean=(FoodBean)populateBean(request);
				
					
				 bean.setImage(filePath);

				
					try {
						
						RestaurantBean rBean=rModel.findByUserId(uBean.getId());
						bean.setRestaurantId(rBean.getId());
						
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
				FoodBean bean=(FoodBean)populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(OFDView.FOOD_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
			}else if (OP_CANCEL.equalsIgnoreCase(op)) {
				ServletUtility.redirect(OFDView.FOOD_LIST_CTL, request, response);
				return;
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OFDView.FOOD_CTL, request, response);
			return;
	}
					
			
			ServletUtility.forward(getView(), request, response);
			 log.debug("FoodCtl doPost method end");
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
		return OFDView.FOOD_VIEW;
	}

}
