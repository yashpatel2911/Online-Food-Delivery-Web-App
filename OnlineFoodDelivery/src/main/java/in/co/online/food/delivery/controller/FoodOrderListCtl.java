package in.co.online.food.delivery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.online.food.delivery.bean.BaseBean;
import in.co.online.food.delivery.bean.DessertOrderBean;
import in.co.online.food.delivery.bean.FoodChartBean;
import in.co.online.food.delivery.bean.FoodOrderBean;
import in.co.online.food.delivery.bean.UserBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.model.ChartModel;
import in.co.online.food.delivery.model.FoodOrderModel;
import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.PropertyReader;
import in.co.online.food.delivery.util.ServletUtility;

@WebServlet(name = "FoodOrderListCtl", urlPatterns = { "/ctl/FoodOrderListCtl" })
public class FoodOrderListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private final Logger log = Logger.getLogger(FoodChartListCtl.class);

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("FoodChartListCtl  populateBean method start");
		FoodOrderBean bean = new FoodOrderBean();
		
		bean.setFoodName(DataUtility.getString(request.getParameter("name")));
		
		log.debug("FoodChartListCtl  populateBean method end");

		return bean;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FoodChartListCtl  doGet method start");

		int pageNo = 1;
		int pageSize = 3;

		FoodOrderBean bean = (FoodOrderBean) populateBean(request);
		FoodOrderModel model = new FoodOrderModel();

		List list = null;
		
		long dsid=DataUtility.getLong(request.getParameter("foD"));

		try {
			if(dsid > 0) {
				
				FoodOrderBean deletebean = new FoodOrderBean();
				deletebean.setId(dsid);
				model.delete(deletebean);
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			}
			
			
			HttpSession session=request.getSession(true);
			UserBean uBean=(UserBean)session.getAttribute("user");
			
			if(uBean.getRoleId()==3) {
				bean.setUserId(uBean.getId());
			}
			
			list = model.search(bean, pageNo, pageSize);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			
			return;
		}
		
		HttpSession session=request.getSession(true);
		UserBean uBean=(UserBean)session.getAttribute("user");
		
		bean.setUserId(uBean.getId());

		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("No record found ", request);
		}

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
		log.debug("FoodChartListCtl  doGet method end");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FoodChartListCtl  doPost method start");

		List list = null;
		String ids = request.getParameter("ids");

		System.out.println("=============================" + ids);

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? 3 : pageSize;

		FoodOrderBean bean = (FoodOrderBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		FoodOrderModel model = new FoodOrderModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				} 

			
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null ) {
					FoodOrderBean deletebean = new FoodOrderBean();
					
						deletebean.setId(DataUtility.getInt(ids));
						System.out.println("===============vsafff==============vds" + deletebean.getId());
						model.delete(deletebean);
					
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(OFDView.FOOD_ORDER_LIST_CTL, request, response);
				return;
			}

			
			HttpSession session=request.getSession(true);
			UserBean uBean=(UserBean)session.getAttribute("user");
			
			if(uBean.getRoleId()==3) {
				bean.setUserId(uBean.getId());
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
		log.debug("FoodChartListCtl  doPost method end");
	}

	@Override
	protected String getView() {
		return OFDView.FOOD_ORDER_LIST_VIEW;

}

}
