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
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.exception.RecordNotFoundException;
import in.co.online.food.delivery.model.UserModel;
import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.DataValidator;
import in.co.online.food.delivery.util.PropertyReader;
import in.co.online.food.delivery.util.ServletUtility;

@WebServlet(name = "ForgetPasswordCtl", urlPatterns = { "/ForgetPasswordCtl" })
public class ForgetPasswordCtl extends BaseCtl {
private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ForgetPasswordCtl.class);
    
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("ForgetPasswordCtl validate  Method Started");

        boolean pass = true;

        String login = request.getParameter("login");

        if (DataValidator.isNull(login)) {
            request.setAttribute("login",
                    PropertyReader.getValue("error.require", "Email Id"));
            pass = false;
        } 
        log.debug("ForgetPasswordCtl  validate Method Ended");

        return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		 log.debug("ForgetPasswordCtl Method populatebean Started");

	        UserBean bean = new UserBean();

	        bean.setLogin(DataUtility.getString(request.getParameter("login")));

	        log.debug("ForgetPasswordCtl Method populatebean Ended");

	        return bean;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ForgetPasswordCtl Method doGet Started");

        ServletUtility.forward(getView(), request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ForgetPasswordCtl Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));

        UserBean bean = (UserBean) populateBean(request);

        // get model
        UserModel model = new UserModel();

        if (OP_GO.equalsIgnoreCase(op)) {

            try {
                model.forgetPassword(bean.getLogin());
                
                ServletUtility.setSuccessMessage(
                        "Password has been sent to your email id.", request);
            } catch (RecordNotFoundException e) {
                ServletUtility.setErrorMessage(e.getMessage(), request);
                log.error(e);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
               
            } 
            ServletUtility.forward(getView(), request, response);
        }

        log.debug("ForgetPasswordCtl Method doPost Ended");
	}
	@Override
	protected String getView() {
		 return OFDView.FORGET_PASSWORD_VIEW;
	}

}
