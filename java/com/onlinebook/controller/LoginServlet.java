package com.onlinebook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlinebook.dao.LoginDao;
import com.onlinebook.model.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	RequestDispatcher dispatcher;
//	LoginDao dao = new LoginDao();

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = request.getParameter("action");
		System.out.println(action);
		switch (action) {
		case "signup":
			signUp(request, response);
			break;
		case "login":
			login(request, response);
			break;

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void signUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();

		response.setContentType("text/html");

		String name  = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		Integer age = Integer.parseInt(request.getParameter("age"));
		String mobileNumber = request.getParameter("mobileNumber");
		String gender = request.getParameter("gender");
		System.out.println(gender);

		String m[] = email.split("@");
		String mail = m[0];
		System.out.println(mail);

		Login bean = new Login();
		LoginDao dao = new LoginDao();

		bean.setName(name);
		bean.setEmail(email);
		bean.setPass(pass);
		bean.setAge(age);
		bean.setMobileNumber(mobileNumber);
		bean.setGender(gender);
		

//		if (dao.vaildate(bean)) {
//			session.setAttribute("mail", mail);
//			dispatcher = request.getRequestDispatcher("/Loginpage/ticket.jsp");
//			dispatcher.forward(request, response);
//		} else {
//			response.sendRedirect("Loginpage/Login.jsp");
//		}
		int i = dao.signUP(bean);

		if (i > 0) {
			request.setAttribute("status", "added");
			session.setAttribute("mail", mail);
			session.setAttribute("name", name);
			dispatcher = request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
		} else {
//			request.setAttribute("status", "Failed");
			dispatcher = request.getRequestDispatcher("/signup.jsp");
			dispatcher.forward(request, response);
//			out.println("<h3>Error Occured!! Please Try Again");

		}

	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();

		response.setContentType("text/html");

		String email = request.getParameter("email");
		String pass = request.getParameter("pass");

		String m[] = email.split("@");
		String mail = m[0];
		System.out.println(mail);

		Login bean = new Login();
		LoginDao dao = new LoginDao();

		bean.setEmail(email);
		bean.setPass(pass);

		if (email.equals("Alan@gmail.com") && pass.equals("alan@6268")) {
			session.setAttribute("mail", mail);
			request.setAttribute("loginstatus", "admin");
			dispatcher = request.getRequestDispatcher("/admin.jsp");
			dispatcher.forward(request, response);
		} else if (dao.vaildate(bean)) {
			session.setAttribute("mail", mail);
			session.setAttribute("email", email);
			request.setAttribute("loginstatus", "user");
			dispatcher = request.getRequestDispatcher("/UserHome.jsp");
			dispatcher.forward(request, response);

		} else {
			request.setAttribute("loginstatus", "loginfailed");
			dispatcher = request.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("Login.jsp");
		}
	}
}
