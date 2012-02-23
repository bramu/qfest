package com.tb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tb.beans.*;
import com.tb.dao.*;

public class QuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionDAO dao = new QuestionDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			performAction(req, resp);
		} catch (SQLException e) {
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

	public void performAction(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException {
		if (req.getParameter("action") == null) {
			performIndexAction(req, resp);
		} else if (req.getParameter("action").equals("index")) {
			performIndexAction(req, resp);
		} else if (req.getParameter("action").equals("new")) {
			performNewAction(req, resp);
		} else if (req.getParameter("action").equals("create")) {

			performCreateAction(req, resp);
		} else if (req.getParameter("action").equals("view")) {

			performViewAction(req, resp);
		} else if (req.getParameter("action").equals("edit")) {
			performEditAction(req, resp);
		} else if (req.getParameter("action").equals("update")) {

			performUpdateAction(req, resp);
		} else if (req.getParameter("action").equals("delete")) {

			performDeleteAction(req, resp);
		} else if (req.getParameter("action").equals("signout")) {

			performSignoutAction(req, resp);
		}
			
	}

	private void performIndexAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			int pageNo = 1;
			if (req.getParameter("page") != null) {
				pageNo = Integer.parseInt(req.getParameter("page"));
			}
			List<Question> questions = dao.fetchAll(pageNo);
			req.setAttribute("questions", questions);
			req.setAttribute("totalCount", dao.getTotalCount());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/questions/index.jsp");
			rd.forward(req, resp);

			/*if (req.getSession().getAttribute("is_logged_in") == null
					|| (Boolean) req.getSession().getAttribute("is_logged_in") == false) {
				pw.write("<a href = '/w5db/users'>AddQuestion</a>");
			} else {
				pw.write("You are successfully logged in");
				pw.write("<a href = '/w5db/questions?action=new'>AddQuestion</a> <br/>");
			}
			
			/*for (Question question : questions) {
				pw.write(question.getTitle());
				req.setAttribute("questions", questions);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/questions/index.jsp");
				rd.forward(req, resp);
					
			}
			pw.write("<a href = '/w5db/questions?action=signout'>signout</a> <br/>");*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void performNewAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/questions/new.jsp");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void performCreateAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			
			resp.setContentType("text/html");
			dao.create(req.getParameter("title"));
			resp.sendRedirect("/qfest/questions?action=index");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void performViewAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			Question q = dao.findById(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("question", q);
			RequestDispatcher rd  = getServletContext().getRequestDispatcher("/questions/view.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void performEditAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			Question q = new Question();
			q = dao.findById(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("question", q);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/questions/edit.jsp");
			rd.forward(req, resp);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void performUpdateAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			resp.setContentType("text/html");
			Question q = new Question();
			q = dao.update(Integer.parseInt(req.getParameter("id")),
					req.getParameter("title"));
			resp.sendRedirect("/qfest/questions?action=index");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void performDeleteAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {

			resp.setContentType("text/html");
			dao.delete(Integer.parseInt(req.getParameter("id")));
			resp.sendRedirect("/qfest/questions?action=index");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void performSignoutAction(HttpServletRequest req,
			HttpServletResponse resp) {
		resp.setContentType("text/html");
		try {
			PrintWriter pw = resp.getWriter();
			if ((Boolean) req.getSession().getAttribute("is_logged_in") == true) {
				req.getSession().setAttribute("is_logged_in", false);
				resp.sendRedirect("/qfest/questions");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
