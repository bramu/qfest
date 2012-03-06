package com.tb.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tb.dao.AnswerDAO;
import com.tb.dao.QuestionDAO;

public class AnswersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AnswerDAO adao = new AnswerDAO();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			performAction(req, resp);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			performAction(req, resp);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void performAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, NumberFormatException, SQLException {
		if (req.getParameter("action").equals("writeAnswer")) {
			performWriteAnswerAction(req, resp);
		} else if (req.getParameter("action").equals("submitAnswer")) {
			performSubmitAnswerAction(req, resp);
		}
	}
	/*send request to the writeanswer.jsp and passing question id*/
	private void performWriteAnswerAction(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("questionId", req.getParameter("questionId"));

		RequestDispatcher rd1 = getServletContext().getRequestDispatcher(
				"/questions/writeAnswer.jsp");
		rd1.forward(req, resp);
	}
	/*call the submit answer method to insert the answer into the database*/
	private void performSubmitAnswerAction(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		try {
			int questionId = Integer.parseInt( req.getParameter("questionId"));
			String answer = req.getParameter("answer");
			int userId = (Integer) req.getSession().getAttribute("userId");
			adao.submitAnswer(questionId, answer, userId);
			QuestionDAO qdao = new QuestionDAO();
			qdao.countUpdates("answers_count", questionId);
			resp.sendRedirect("/qfest/questions");
			} catch (Exception e) {
			}
	}

}
