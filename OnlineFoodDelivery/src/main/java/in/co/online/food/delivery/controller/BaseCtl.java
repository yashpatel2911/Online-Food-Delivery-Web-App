package in.co.online.food.delivery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.food.delivery.bean.BaseBean;
import in.co.online.food.delivery.bean.UserBean;
import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.DataValidator;
import in.co.online.food.delivery.util.ServletUtility;

@WebServlet("/BaseCtl")
public abstract class BaseCtl extends HttpServlet
{
	private static final Logger log=Logger.getLogger(BaseCtl.class);
	
	public static final String OP_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_LOG_OUT = "Logout";
	public static final String OP_RESET = "Reset";

	public static final String MSG_SUCCESS = "success";

	public static final String MSG_ERROR = "error";


	public BaseCtl() {
		// TODO Auto-generated constructor stub
	}


	protected boolean validate(HttpServletRequest request) {
		return true;
		
	}

	protected void preload(HttpServletRequest request) {
	}


	protected BaseBean populateBean(HttpServletRequest request) {
		return null;
	}

	
	
	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {
		log.debug("BaseCtl populate DTO method start");
	
		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;

		UserBean userbean = (UserBean) request.getSession().getAttribute("user");

		if (userbean == null) {
			createdBy = "root";
			modifiedBy = "root";
		} else {

			modifiedBy = userbean.getLogin();
			if ("null".equalsIgnoreCase(createdBy)|| DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}

		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());
		
		log.debug("BaseCtl populate DTO method end");
		return dto;
	}

	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		log.debug("BaseCtl service method start");
		
		preload(request);


		String op = DataUtility.getString(request.getParameter("operation"));

		System.out.println("operation ="+op);

		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op)&& !OP_VIEW.equalsIgnoreCase(op)&& !OP_DELETE.equalsIgnoreCase(op)&&!OP_RESET.equalsIgnoreCase(op)) {
			if (!validate(request)) {
				BaseBean bean = (BaseBean) populateBean(request);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
		}
		
		log.debug("BaseCtl service method end");
		super.service(request, response);
	}
	protected abstract String getView();
	
}
