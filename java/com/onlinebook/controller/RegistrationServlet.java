package com.onlinebook.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlinebook.dao.RegistrationDao;
import com.onlinebook.model.Registration;

/**
 * Servlet implementation class ResgistrationServlet
 */
@WebServlet("/BookTicket")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("inside post");
		
		response.setContentType("text/html");
		 
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		
		String m[] = email.split("@");
		String mail = m[0];
		System.out.println(mail);
		
		Registration bean = new Registration(name, email, password);
		RegistrationDao dao = new RegistrationDao();
		
		String result = dao.Regiterindb(bean);
		
		if (result.equals("User is registered")) {
//			session.setAttribute("name", name);
			session.setAttribute("mail", mail);
			dispatcher = request.getRequestDispatcher("/Loginpage/ticket.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("Loginpage/signup.jsp");
		}
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
}
