package com.ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.bean.UserBean;
import com.exception.ApplicationException;
import com.exception.RecordNotFoundException;
import com.model.UserModel;
@WebServlet(urlPatterns="/UserForgetPasswordCtl")
public class UserForgetPasswordCtl extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel model=new UserModel();
		UserBean bean=new UserBean();
/*		String otppage=req.getParameter("otppage");	
		String otp;
		try {  bean=model.findByLogin(req.getParameter("login"));  } 
		catch (ApplicationException e1) {	e1.printStackTrace();}
		if(otppage==null){
			if(bean!=null){
				
				try {
					otp = model.generateOTP(bean.getLogin());
					req.setAttribute("otp",otp);
				} catch (ApplicationException e) {
					e.printStackTrace();
				} catch (RecordNotFoundException e) {
					e.printStackTrace();
				}
				req.setAttribute("login", bean.getLogin());
				req.getRequestDispatcher("otp.jsp").forward(req, resp);
			}
			else{
				req.setAttribute("msg", "email doesn't exist");
				req.getRequestDispatcher("ForgetPassword.jsp").forward(req, resp);
			}
		}
		else{
			try {
				model.forgetPassword(bean.getLogin());
			} catch (ApplicationException e1) {
				e1.printStackTrace();
			} catch (RecordNotFoundException e1) {
				e1.printStackTrace();
			}
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			
			
		}*/
		String login=req.getParameter("login");
		
		String operation=req.getParameter("operation");	
	//	String resendOTP=req.getParameter("Resend");
		String otp;
		if(StringUtils.equals(operation, "GETOTP")){
			try {
				System.err.println("login "+login);
				bean=model.findByLogin(login);
				HttpSession session=req.getSession();
				session.setAttribute("UserBean", bean);
			} catch (ApplicationException e1) {
				e1.printStackTrace();
			}
		
			if(bean!=null){
				try {
					otp = model.generateOTP(bean.getLogin());
					req.setAttribute("OTP",otp);
					req.setAttribute("login", bean.getLogin());
				} catch (ApplicationException e) {
					e.printStackTrace();
				} catch (RecordNotFoundException e) {
					e.printStackTrace();
				}
				req.getRequestDispatcher("ForgetPassword.jsp").forward(req, resp);
				
			}
			else{
				
			
				req.setAttribute("msg", "email doesn't exist");
				req.getRequestDispatcher("ForgetPassword.jsp").forward(req, resp);
			}
		}
		else{
			HttpSession session=req.getSession(false);
			UserBean b=(UserBean)session.getAttribute("UserBean");
			try {
				model.forgetPassword(b.getLogin());
			}catch (ApplicationException e) {
				e.printStackTrace();
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}
			req.setAttribute("msg", "Password Has been sent to your Email  Please Check Your");
			req.getRequestDispatcher("Login.jsp").forward(req, resp);
			
			
		}
	}
}
