package com.tb.servlets;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
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
	private QuestionDAO qdao = new QuestionDAO();

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
		}
		 else if (req.getParameter("action").equals("unanswered")) {

			performUnansweredAction(req, resp);
		} 
			
	}

	private void performIndexAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			int pageNo = 1;
			if (req.getParameter("page") != null) {
				pageNo = Integer.parseInt(req.getParameter("page"));
			}
			List<Question> questions = qdao.fetchAll(pageNo);
			req.setAttribute("questions", questions);
			req.setAttribute("totalCount", qdao.getTotalCount());
			req.setAttribute("pageNo", pageNo);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/questions/index.jsp");
			rd.forward(req, resp);
			
			
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
			qdao.create(req.getParameter("title"));
			resp.sendRedirect("/qfest/questions?action=index");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void performViewAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			Question q = qdao.findById(Integer.parseInt(req.getParameter("id")));
			AnswerDAO adao = new AnswerDAO();
			List<Answer> answers = adao.listOfAnswers(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("answers", answers);
			req.setAttribute("question", q);
			RequestDispatcher rd  = getServletContext().getRequestDispatcher("/questions/view.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void performUnansweredAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			int pageNo = 1;
			if (req.getParameter("page") != null) {
				pageNo = Integer.parseInt(req.getParameter("page"));
			}
			
			List<Question> unanswered = qdao.unanswered(pageNo);
			req.setAttribute("unanswered", unanswered);
			req.setAttribute("totalCount", qdao.getTotalCount());
			req.setAttribute("totalUnansweredCount", qdao.getTotalUnansweredCount());
			req.setAttribute("pageNo", pageNo);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/questions/unanswered.jsp");
			rd.forward(req, resp);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
