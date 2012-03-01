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

import com.tb.beans.Answer;
import com.tb.beans.Comment;
import com.tb.beans.Question;
import com.tb.dao.AnswerDAO;
import com.tb.dao.BookmarkDAO;
import com.tb.dao.QuestionDAO;

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
			throws SQLException, IOException, ServletException {
		if (req.getParameter("action") == null) {
			performIndexAction(req, resp);
		} else if (req.getParameter("action").equals("index")) {
			performIndexAction(req, resp);
		} else if (req.getParameter("action").equals("add")) {
			performAddAction(req, resp);
		} else if (req.getParameter("action").equals("create")) {

			performCreateAction(req, resp);
		} else if (req.getParameter("action").equals("view")) {
			performViewAction(req, resp);
		}  else if (req.getParameter("action").equals("writeComment")) {
			performWriteCommentAction(req, resp);
		} 
		else if (req.getParameter("action").equals("submitComment")) {
			performSubmitCommentAction(req, resp);
		}else if (req.getParameter("action").equals("getComments")) {
			performGetCommentAction(req, resp);
		}
	}
	private void performWriteCommentAction(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("questionId", req.getParameter("questionId"));

		RequestDispatcher rd1 = getServletContext().getRequestDispatcher(
				"/questions/writeComment.jsp");
		rd1.forward(req, resp);
	}
	private void performSubmitCommentAction(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		try {
			int questionId = Integer.parseInt( req.getParameter("questionId"));
			String comment = req.getParameter("comment");
			int userId = (Integer) req.getSession().getAttribute("userId");
			qdao.submitComment(questionId, comment, userId);
			resp.sendRedirect("/qfest/questions");
		} catch (Exception e) {
		}

	}
	private void performIndexAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			int pageNo = 1;
			if (req.getParameter("page") != null) {

				pageNo = Integer.parseInt(req.getParameter("page"));
			}
			if (req.getParameter("type") == null) {

				req.setAttribute("pageNo", pageNo);
				List<Question> questions = qdao.fetchAll(pageNo);
				req.setAttribute("questions", questions);
				req.setAttribute("totalCount", qdao.getTotalCount());
				RequestDispatcher rd2 = getServletContext()
						.getRequestDispatcher("/questions/index.jsp");
				rd2.forward(req, resp);
			}

			else if (req.getParameter("type").equals("unanswered")) {
				String type = req.getParameter("type");
				List<Question> unanswered = qdao.unanswered(pageNo);
				req.setAttribute("questions", unanswered);
				req.setAttribute("totalCount", qdao.getTotalUnansweredCount());
				req.setAttribute("pageNo", pageNo);
				req.setAttribute("type", type);
				RequestDispatcher rd1 = getServletContext()
						.getRequestDispatcher("/questions/index.jsp");
				rd1.forward(req, resp);
			}
			else if (req.getParameter("type").equals("recent")) {
				String type = req.getParameter("type");
				List<Question> recent = qdao.recent(pageNo);
				req.setAttribute("questions", recent);
				req.setAttribute("totalCount", qdao.getTotalCount());
				req.setAttribute("pageNo", pageNo);
				req.setAttribute("type", type);
				RequestDispatcher rd1 = getServletContext()
						.getRequestDispatcher("/questions/index.jsp");
				rd1.forward(req, resp);
			}
			else if (req.getParameter("type").equals("viewed")) {
				String type = req.getParameter("type");
				List<Question> viewed = qdao.mostViewed(pageNo);
				
				req.setAttribute("type", type);
				req.setAttribute("questions", viewed);
				req.setAttribute("pageNo", pageNo);
				req.setAttribute("totalCount", qdao.getTotalCount());
				RequestDispatcher rd1 = getServletContext()
						.getRequestDispatcher("/questions/index.jsp");
				rd1.forward(req, resp);
			}
			else if (req.getParameter("type").equals("rated")) {
				String type = req.getParameter("type");
				List<Question> rated = qdao.mostRated(pageNo);
				req.setAttribute("type", type);
				req.setAttribute("questions", rated);
				req.setAttribute("pageNo", pageNo);
				req.setAttribute("totalCount", qdao.getTotalCount());
				RequestDispatcher rd1 = getServletContext()
						.getRequestDispatcher("/questions/index.jsp");
				rd1.forward(req, resp);
			}else if (req.getParameter("type").equals("bookmarkable")) {
				BookmarkDAO bdao = new BookmarkDAO();
			    bdao.bookmarkable(Integer.parseInt(req.getParameter("id")), (Integer)req.getSession().getAttribute("userId"));
				resp.sendRedirect("/qfest/questions");
			}
			else if (req.getParameter("type").equals("bookmarked")) {
				String type = req.getParameter("type");
				int userId = (Integer)req.getSession().getAttribute("userId");
				BookmarkDAO bdao = new BookmarkDAO();
				List<Question> bookmarked = bdao.bookmarked(userId,pageNo);
				req.setAttribute("questions", bookmarked);
				req.setAttribute("totalCount", qdao.getTotalCount());
				req.setAttribute("pageNo", pageNo);
				req.setAttribute("type", type);
				RequestDispatcher rd1 = getServletContext()
						.getRequestDispatcher("/questions/index.jsp");
				rd1.forward(req, resp);
			}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void performAddAction(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		try {
			
			req.getSession().setAttribute("userId", req.getParameter("userId") );
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/questions/add.jsp");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void performCreateAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			int uId = Integer.parseInt(req.getParameter("userId"));
			 String tle = req.getParameter("title");
			String qText = req.getParameter("questionText");
			String aText = req.getParameter("answerText");
			qdao.create(uId, tle, qText, aText);
			resp.sendRedirect("/qfest/questions");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void performViewAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			
			Question q = qdao.findById(Integer.parseInt(req.getParameter("questionId")));	
			req.setAttribute("question", q);
			req.setAttribute("totalCount", qdao.getTotalCount());
			List<Comment> comments = qdao.listOfComments(Integer.parseInt(req
					.getParameter("questionId")));
			req.setAttribute("comments", comments);
			AnswerDAO adao = new AnswerDAO();
			List<Answer> answers = adao.listOfAnswers(Integer.parseInt(req
					.getParameter("questionId")));
			req.setAttribute("answers", answers);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/questions/view.jsp");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void performGetCommentAction(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			List<Comment> comments = qdao.listOfComments(Integer.parseInt(req
					.getParameter("questionId")));
			req.setAttribute("comments", comments);
			RequestDispatcher rd1 = getServletContext().getRequestDispatcher(
					"/questions/view.jsp");
			rd1.forward(req, resp);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
