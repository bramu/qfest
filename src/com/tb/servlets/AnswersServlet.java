package com.tb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tb.dao.AnswerDAO;

public class AnswersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AnswerDAO adao = new AnswerDAO();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		performAction(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		performAction(req, resp);
	}

	private void performAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getParameter("action").equals("writeAnswer")) {
			performWriteAnswerAction(req, resp);
		} else if (req.getParameter("action").equals("writeComment")) {
			performWriteCommentAction(req, resp);
		} else if (req.getParameter("action").equals("submitAnswer")) {
			performSubmitAnswerAction(req, resp);
		} else if (req.getParameter("action").equals("submitComment")) {
			performSubmitCommentAction(req, resp);
		}
	}

	private void performWriteAnswerAction(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("questionId", req.getParameter("questionId"));

		RequestDispatcher rd1 = getServletContext().getRequestDispatcher(
				"/questions/writeAnswer.jsp");
		rd1.forward(req, resp);
	}

	private void performWriteCommentAction(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("questionId", req.getParameter("questionId"));

		RequestDispatcher rd1 = getServletContext().getRequestDispatcher(
				"/questions/writeComment.jsp");
		rd1.forward(req, resp);
	}

	private void performSubmitAnswerAction(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		try {
			int questionId = Integer.parseInt( req.getParameter("questionId"));
			String answer = req.getParameter("answer");
			int userId = (Integer) req.getSession().getAttribute("userId");
			adao.submitAnswer(questionId, answer, userId);
			resp.sendRedirect("/qfest/questions");
		} catch (Exception e) {
		}

	}

	private void performSubmitCommentAction(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {

	}
}
