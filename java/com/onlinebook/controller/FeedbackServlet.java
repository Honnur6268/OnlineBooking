package com.onlinebook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlinebook.dao.FeedbackDao;
import com.onlinebook.model.Feedback;

/**
 * Servlet implementation class Feedback
 */
@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FeedbackServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();

		response.setContentType("text/html");

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String rating = request.getParameter("rating");
		String comments = request.getParameter("comments");

		String status = request.getParameter("status");
		
		Feedback feedback = new Feedback();
		FeedbackDao feedbackDao = new FeedbackDao();

		feedback.setName(name);
		feedback.setEmail(email);
		feedback.setRating(rating);
		feedback.setComments(comments);

		int i = feedbackDao.saveFeedback(feedback);

		if (i > 0) {
			request.setAttribute("status", "feedback_Sent");
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} else {
//			request.setAttribute("status", "Failed");
			dispatcher = request.getRequestDispatcher("ContactUS/contactus.jsp");
			dispatcher.forward(request, response);
//			out.println("<h3>Error Occured!! Please Try Again");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
