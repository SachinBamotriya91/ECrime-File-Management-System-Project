package com.ctl;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.UserBean;
import com.exception.ApplicationException;
import com.model.UserModel;
import com.util.DataUtility;
import com.util.PropertyReader;
@WebServlet(urlPatterns="/UserListCtl.do")
public class UserListCtl  extends HttpServlet{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	System.out.println("aa:"+req.getParameter("operation"));
	//System.out.println("bbb :"+req.getParameter("search"));
		UserModel model=new UserModel();
		int pageSize=DataUtility.getInt(PropertyReader.getValue("page.size"));
		List<UserBean> list = null;
		try {
			list=model.list(0,0);
		} catch (ApplicationException e1) {
			e1.printStackTrace();
		}
		HttpSession session=req.getSession(false);
		
		 if(req.getParameter("operation")!=null && req.getParameter("operation").equalsIgnoreCase("Delete")){
			String ids[]=req.getParameterValues("ids");
			if (ids!=null && ids.length > 0) {
				for (String id : ids) {
				model.setId(DataUtility.getLong(id));
					try {
						model.delete();
						list=model.list(0, 0);
					} catch (ApplicationException e) {
						e.printStackTrace();
					}
					int index=DataUtility.getInt( session.getAttribute("index").toString());
					index=index-((index%pageSize==0)?pageSize:index%pageSize);
					session.setAttribute("index",index);
					req.setAttribute("list", list);
					req.getRequestDispatcher("UserList.jsp").forward(req, resp);
				}
		}
		
		}
		else if(req.getParameter("operation")!=null && req.getParameter("operation").equalsIgnoreCase("nextPage")){
		
			int index=Integer.parseInt(session.getAttribute("index").toString());
			if(list.size()==index){
			int temp=(index%pageSize==0)?pageSize:index%pageSize;
			index=index-(temp);
			session.setAttribute("index", index);
			}
			else{
				session.setAttribute("index", index);
			}
			req.setAttribute("list", list);
			req.getRequestDispatcher("UserList.jsp").forward(req, resp);	
		}
		else if(req.getParameter("operation")!=null && req.getParameter("operation").equalsIgnoreCase("previousPage")){
			int index=Integer.parseInt(session.getAttribute("index").toString());
			if(list.size()==index && list.size()%pageSize!=0){
				int temp=index%pageSize;
				index=index-(pageSize+temp);}
			else{
				index=index-(pageSize*2);	}
			if(index<0){
				index=0;}
			session.setAttribute("index", index);
			req.setAttribute("list", list);
			req.getRequestDispatcher("UserList.jsp").forward(req, resp); }
		else if(req.getParameter("operation")!=null &&req.getParameter("operation").equalsIgnoreCase("search"))
		{
			UserBean bean=new UserBean();
			String value=req.getParameter("searchdata");
			String searchby=req.getParameter("searchby");
			if(searchby.equalsIgnoreCase("firstname")){
			bean.setFirstName(value);}
			else if(searchby.equalsIgnoreCase("lastname")){
				bean.setLastName(value);
			}
			else if(searchby.equalsIgnoreCase("login")){
				bean.setLogin(value);
			}
			else if(searchby.equalsIgnoreCase("gender")){
				bean.setGender(value);
			}
			else if(searchby.equalsIgnoreCase("mobilenumber")){
				bean.setMobileNo(value);
			}
			
			
			try {
				list= model.search(bean);
				session.setAttribute("index", 0);
				req.setAttribute("list", list);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
			
			req.getRequestDispatcher("UserList.jsp").forward(req, resp);
			}
		
	else{
			session.setAttribute("index", 0);
			req.setAttribute("list", list);
			req.getRequestDispatcher("UserList.jsp").forward(req, resp); }
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
}
