package com.tb.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tb.dao.RatingDAO;


public class RatingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   RatingDAO rdao = new RatingDAO();
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
		 if (req.getParameter("action").equals("like")) {
			performLikeAction(req, resp);
		} else if (req.getParameter("action").equals("unlike")) {
			performUnlikeAction(req, resp);
		} else if (req.getParameter("action").equals("inappropriate")) {
			performInappropriateAction(req, resp);
		} 
	}
	private void performLikeAction(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, SQLException {
		
		if(req.getParameter("type").equals("question")){
			rdao.like("question",Integer.parseInt(req.getParameter("questionId")));
			resp.sendRedirect("/qfest/questions");
		}

		else if (req.getParameter("type").equals("answer")){
			rdao.like("answer",Integer.parseInt(req.getParameter("answerId")));
			resp.sendRedirect("/qfest/questions?action=view");
		}
		//we need to ajax response
	}
	private void performUnlikeAction(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, SQLException {

		if(req.getParameter("type").equals("question")){
			rdao.unlike("question",Integer.parseInt(req.getParameter("questionId")));
			resp.sendRedirect("/qfest/questions");
		}

		else if (req.getParameter("type").equals("answer")){
			rdao.unlike("answer",Integer.parseInt(req.getParameter("answerId")));
			resp.sendRedirect("/qfest/questions?action=view");
		}
		//we need to ajax response
	}
	private void performInappropriateAction(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, SQLException {
		rdao.Inappropriate(Integer.parseInt(req.getParameter("id")));
		resp.sendRedirect("/qfest/questions");
	}
}
