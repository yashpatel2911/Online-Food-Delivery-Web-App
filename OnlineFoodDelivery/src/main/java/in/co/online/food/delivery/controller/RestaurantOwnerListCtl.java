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
import in.co.online.food.delivery.bean.DessertOrderBean;
import in.co.online.food.delivery.bean.UserBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.model.UserModel;
import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.PropertyReader;
import in.co.online.food.delivery.util.ServletUtility;
@WebServlet(name = "RestaurantOwnerListCtl", urlPatterns = { "/ctl/RestaurantOwnerListCtl" })
public class RestaurantOwnerListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
   
    private final Logger log = Logger.getLogger(RestaurantOwnerListCtl.class);
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("RestaurantOwnerList  populateBean method start");
		UserBean bean = new UserBean();
		
		bean.setFirstName(DataUtility.getString(request.getParameter("name")));
		bean.setEmailId(DataUtility.getString(request.getParameter("emailId")));
		
		log.debug("RestaurantOwnerList  populateBean method end");

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("RestaurantOwnerList  doGet method start");

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		UserBean bean = (UserBean) populateBean(request);
		UserModel model = new UserModel();
		
		long roid=DataUtility.getLong(request.getParameter("roD"));

		List list = null;

		try {
			
			if(roid > 0) {
				
				UserBean deletebean = new UserBean();
				deletebean.setId(roid);
				model.delete(deletebean);
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			}
			
			bean.setRoleId(2L);
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
		log.debug("RestaurantOwnerList  doGet method end");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("RestaurantOwnerList  doPost method start");

		List list = null;
		String ids = request.getParameter("ids");

		System.out.println("=============================" + ids);

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		UserBean bean = (UserBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		UserModel model = new UserModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				} else if (OP_NEW.equalsIgnoreCase(op)) {
					ServletUtility.redirect(OFDView.RESTAURANT_OWNER_CTL, request, response);
					return;
				}

			
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null) {
					UserBean deletebean = new UserBean();
					
						deletebean.setId(DataUtility.getInt(ids));
						System.out.println("===============vsafff==============vds" + deletebean.getId());
						model.delete(deletebean);
					
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(OFDView.RESTAURANT_OWNER_LIST_CTL, request, response);
				return;
			}

			bean.setRoleId(2L);
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
		log.debug("RestaurantOwnerList  doPost method end");
	}

	protected String getView() {
		return OFDView.RESTAURANT_OWNER_LIST_VIEW;
	}
}
