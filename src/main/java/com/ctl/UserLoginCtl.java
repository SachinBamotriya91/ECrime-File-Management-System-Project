package com.ctl;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.UserBean;
import com.exception.ApplicationException;
import com.exception.DuplicateRecordException;
import com.model.UserModel;

@WebServlet(urlPatterns="/UserLoginCtl")
public class UserLoginCtl extends HttpServlet {
	public static int count;
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UserBean bean=new UserBean();
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			UserModel model=new UserModel();
			
/*			System.err.println(req.getParameter("operation"));
*/			if(req.getParameter("operation")!=null && req.getParameter("operation").equals("Sign-In")){
				try {
					bean=model.authenticate(req.getParameter("login"), req.getParameter("password"));
					
				} catch (ApplicationException e) {
					e.printStackTrace();
				}
				if(bean!=null){
					HttpSession session=req.getSession();
					session.setAttribute("Id",session.getId());
					session.setAttribute("UserBean", bean);
					//req.getRequestDispatcher("ResetPassword.jsp").forward(req, resp);
				req.getRequestDispatcher("Welcome.jsp").forward(req, resp);
				}
				else{
					count=count+1;
				
					req.setAttribute("msg", "incorrect email and password");
					req.getRequestDispatcher("Login.jsp").forward(req, resp);
				}
			}
			if(req.getParameter("operation")!=null && req.getParameter("operation").equals("Update")){
				System.out.println("update");
					/*String dob=req.getParameter("dob");
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
					String dob1 = sdf2.format(sdf1.parse(dob));*/
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

					
					HttpSession session=req.getSession(false);
					bean=(UserBean)session.getAttribute("UserBean");
					bean.setFirstName(req.getParameter("firstname"));
					bean.setLastName(req.getParameter("lastname"));
					bean.setLogin(req.getParameter("login"));
					bean.setMobileNo(req.getParameter("mobilenumber"));
					bean.setGender(req.getParameter("gender"));
					bean.setUnsuccessfulLogin(count);
					try {
						bean.setDob(sdf1.parse(req.getParameter("dob")));
						model.update(bean);
						req.getRequestDispatcher("Welcome.jsp").forward(req, resp);
					} catch (ApplicationException e) {
						e.printStackTrace();
					} catch (DuplicateRecordException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}  
				
			}
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//req.getRequestDispatcher("Session Has been Expired ").forward(req, resp);
			resp.sendRedirect("Login.jsp");
		}
			
			
		}
		
