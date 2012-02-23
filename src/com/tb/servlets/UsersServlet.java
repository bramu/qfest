package com.tb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tb.dao.*;
import com.tb.beans.*;


public class UsersServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private QuestionDAO dao = new QuestionDAO();

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
		if (req.getParameter("action") == null) {
			PrintWriter pw = resp.getWriter();
			resp.setContentType("text/html");
			pw.write("if u r not register please click the below link to regiater");
			pw.write("<a href='/qfest/users?action=register'>RegisterNow</a></br>");
			pw.write(" if u r already registerd click the below link to login ");
			pw.write("<a href='/qfest/users?action=login'>LoginNow</a>");
			
		} else if (req.getParameter("action").equals("register")) {
			performRegisterAction(req, resp);
		} else if (req.getParameter("action").equals("login")) {
			performLoginAction(req, resp);
		} else if (req.getParameter("action").equals("create")) {
			performCreateAccountAction(req, resp);
		} else if (req.getParameter("action").equals("logincheck")) {
			performLoginCheckAction(req, resp);
		}

	}

	private void performRegisterAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/users/register.jsp");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void performCreateAccountAction(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, SQLException {
		resp.setContentType("text/html");
		User u = new User();
		boolean b = req.getParameter("pw").equals(req.getParameter("c_pw"));
		
		try {
			if (b) {
				u = dao.createAccount(req.getParameter("name"),
						req.getParameter("email"), req.getParameter("pw"),
						req.getParameter("c_pw"));
				req.getSession().setAttribute("name", u.getName());
				resp.sendRedirect("/qfest/questions");
				//req.getSession().setAttribute("is_logged_in", true);
				//resp.sendRedirect("/qfest/questions?action=index");
			} else {
				//resp.sendRedirect("/qfest/users?action=register");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void performLoginAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/users/login.jsp");
			rd.forward(req, resp);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void performLoginCheckAction(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		try {
			
			resp.setContentType("text/html");
			User user = dao.loginCheck(req.getParameter("emailc"), req.getParameter("pwc"));
			req.setAttribute("name", user.getName());
			
			resp.sendRedirect("/qfest/questions");
			
			//RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/questions/index.jsp");
			//rd.forward(req, resp);
			
			
			/*if (user != null) {
				req.getSession().setAttribute("is_logged_in", true);
				
				//resp.sendRedirect("/qfest/questions?action=index");
			} else {
				
				//resp.sendRedirect("/qfest/users?action=login");
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
