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
		 if (req.getParameter("action").equals("up")) {
			performUpAction(req, resp);
		} else if (req.getParameter("action").equals("down")) {
			performDownAction(req, resp);
		} else if (req.getParameter("action").equals("inappropriate")) {
			performInappropriateAction(req, resp);
		} 
	}
	private void performUpAction(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, SQLException {
		rdao.Up(Integer.parseInt(req.getParameter("id")));
		resp.sendRedirect("/qfest/questions");
		
	}
	private void performDownAction(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, SQLException {
		rdao.Down(Integer.parseInt(req.getParameter("id")));
		resp.sendRedirect("/qfest/questions");
	}
	private void performInappropriateAction(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, SQLException {
		rdao.Inappropriate(Integer.parseInt(req.getParameter("id")));
		resp.sendRedirect("/qfest/questions");
	}
}
