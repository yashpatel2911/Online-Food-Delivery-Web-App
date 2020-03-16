package in.co.online.food.delivery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.food.delivery.bean.BaseBean;
import in.co.online.food.delivery.bean.DessertBean;
import in.co.online.food.delivery.bean.DessertOrderBean;
import in.co.online.food.delivery.bean.FoodBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.model.FoodModel;
import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.PropertyReader;
import in.co.online.food.delivery.util.ServletUtility;

@WebServlet(name = "FoodListCtl", urlPatterns = { "/ctl/FoodListCtl" })
public class FoodListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private final Logger log = Logger.getLogger(FoodListCtl.class);
	
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("FoodListCtl  populateBean method start");
		FoodBean bean = new FoodBean();

		bean.setFoodName(DataUtility.getString(request.getParameter("name")));
		bean.setCategoryName(DataUtility.getString(request.getParameter("category")));
		log.debug("FoodListCtl  populateBean method end");

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FoodListCtl  doGet method start");

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		FoodBean bean = (FoodBean) populateBean(request);
		FoodModel model = new FoodModel();

		List list = null;
		
		long foid=DataUtility.getLong(request.getParameter("foD"));

		try {
			
			if(foid > 0) {
				
				FoodBean deletebean = new FoodBean();
				deletebean.setId(foid);
				model.delete(deletebean);
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			}
			
			
			list = model.search(bean, pageNo, pageSize);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		}

		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("No record found ", request);
		}

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
		log.debug("FoodListCtl  doGet method end");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FoodListCtl  doPost method start");

		List list = null;
		String ids = request.getParameter("ids");

		System.out.println("=============================" + ids);

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		FoodBean bean = (FoodBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		FoodModel model = new FoodModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				} else if (OP_NEW.equalsIgnoreCase(op)) {
					ServletUtility.redirect(OFDView.FOOD_CTL, request, response);
					return;
				}

			
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null ) {
					FoodBean deletebean = new FoodBean();
					
						deletebean.setId(DataUtility.getInt(ids));
						System.out.println("===============vsafff==============vds" + deletebean.getId());
						model.delete(deletebean);
					
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(OFDView.FOOD_LIST_CTL, request, response);
				return;
			}

			list = model.search(bean, pageNo, pageSize);

			if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("FoodListCtl  doPost method end");
	}

	@Override
	protected String getView() {
		return OFDView.FOOD_LIST_VIEW;
	}

}
