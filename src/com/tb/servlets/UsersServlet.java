package com.tb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tb.dao.*;
import com.tb.beans.*;


public class UsersServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserDAO udao = new UserDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			performAction(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			performAction(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void performAction(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, SQLException {
		
		 if (req.getParameter("action").equals("register")) {
			performRegisterAction(req, resp);
		} else if (req.getParameter("action").equals("login")) {
			performLoginAction(req, resp);
		} else if (req.getParameter("action").equals("create")) {
			performCreateAccountAction(req, resp);
		} else if (req.getParameter("action").equals("logincheck")) {
			performLoginCheckAction(req, resp);
		}else if (req.getParameter("action").equals("logout")) {
			performLogoutAction(req, resp);
		}	
	}
	/*send request to the user.jsp to render the registration form*/
	private void performRegisterAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/users/user.jsp");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*create account for the user checking the conditions*/
	private void performCreateAccountAction(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, SQLException {
		resp.setContentType("text/html");
		User u = new User();
		boolean b = req.getParameter("pw").equals(req.getParameter("c_pw"));
		
		try {
			if (b) {
				u = udao.createAccount(req.getParameter("name"),
						req.getParameter("email"), req.getParameter("pw"));
				req.getSession().setAttribute("name", u.getName());
				req.getSession().setAttribute("userId", u.getId());
				resp.sendRedirect("/qfest/questions");
				
				
			} else { 
				resp.sendRedirect("/qfest/users?action=register&msg=Password Missmatch Occurs Try Again");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*send request to the user.jsp to render the login form*/
	private void performLoginAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/users/user.jsp");
			rd.forward(req, resp);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*check the conditions to be successfully logged in*/
	private void performLoginCheckAction(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		try {
			
			resp.setContentType("text/html");
			User user = udao.loginCheck(req.getParameter("emailc"), req.getParameter("pwc"));
			if (user == null) {
				resp.sendRedirect("/qfest/users?action=login&msg1=Login Failed Try Again");
			} else {
				req.getSession().setAttribute("name", user.getName());
				req.getSession().setAttribute("userId", user.getId());
				resp.sendRedirect("/qfest/questions");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*sign outing the user*/
	private void performLogoutAction(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		
		req.getSession().removeAttribute("name");
		req.getSession().removeAttribute("userId");
		resp.sendRedirect("/qfest/questions?action=index");
	}
}
