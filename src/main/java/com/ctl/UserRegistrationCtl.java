package com.ctl;

import java.io.IOException;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bean.UserBean;
import com.exception.ApplicationException;
import com.model.UserModel;
import com.util.DataUtility;

@WebServlet(urlPatterns = "/UserRegistrationCtl")
public class UserRegistrationCtl extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		UserBean login = null;
		try {
			login = model.findByLogin(req.getParameter("login"));
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}
		if (login == null) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			bean.setFirstName(req.getParameter("firstName"));
			bean.setLastName(req.getParameter("lastName"));
			bean.setLogin(req.getParameter("login"));
			bean.setPassword(req.getParameter("password"));
			// String date=(String)req.getParameter("dob");
			try {
				bean.setDob(sdf1.parse(req.getParameter("dob")));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			// bean.setDob(model.stringToDate(date));
			bean.setMobileNo(req.getParameter("mobileNo"));
			bean.setRoleId(1);
			bean.setUnsuccessfulLogin(0);
			bean.setGender(req.getParameter("gender"));
			bean.setLastLogin(DataUtility.getCurrentTimestamp());
			bean.setCreatedBy(req.getParameter("login"));
			bean.setModifiedBy("no one");
			bean.setCreatedDatetime(DataUtility.getCurrentTimestamp());
			bean.setModifiedDatetime(null);
			InetAddress ia = InetAddress.getLocalHost();
			String rp = String.valueOf(ia);
			String rp1 = rp.substring(rp.length() - 14);
			bean.setRegisteredIp(rp1);
			bean.setLastLoginIp("");

			bean.setUserLock("active");
			try {
				model.registerUser(bean);
				req.getRequestDispatcher("Login.jsp").forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			String name = req.getParameter("firstName");
			req.setAttribute("firstName", name);
			req.setAttribute("lastName", req.getParameter("lastName"));
			req.setAttribute("dob", req.getParameter("dob"));
			req.setAttribute("mobileno", req.getParameter("mobileNo"));
			req.setAttribute("msg", "Email Already Exist");

			req.getRequestDispatcher("Registration.jsp").forward(req, resp);

		}
	}

}