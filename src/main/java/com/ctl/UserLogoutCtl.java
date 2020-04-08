package com.ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns="/UserLogoutCtl")
public class UserLogoutCtl  extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//doPost(req,resp);
		HttpSession session=req.getSession(false);
		session.removeAttribute("bean");
		session.invalidate();
		req.setAttribute("msg", "logout Successfully");
		req.getRequestDispatcher("Login.jsp").forward(req, resp);
	}
	/*protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession(false);
		session.invalidate();
		req.setAttribute("msg", "logout Successfully");
		req.getRequestDispatcher("Login.jsp").forward(req, resp);
	}*/

}
