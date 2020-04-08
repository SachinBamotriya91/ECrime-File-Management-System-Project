
package com.ctl;

import java.io.IOException;

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
@WebServlet(urlPatterns="/UserResetPasswordCtl")
public class UserResetPasswordCtl  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel model=new UserModel();
		UserBean bean=null;
		String login=req.getParameter("login");
		String newPassword=req.getParameter("password");
		
			try {
				bean=model.findByLogin(login);
				
				bean.setPassword(newPassword);
				HttpSession session=req.getSession(false);
				model.resetPassword(bean);
				session.invalidate();

				req.setAttribute("msg", "password Has been Changed Successfully please login with new password");
				req.getRequestDispatcher("Login.jsp").forward(req, resp);
				
				
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
	}


