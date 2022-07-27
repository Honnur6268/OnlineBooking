package com.onlinebook.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlinebook.dao.FlightDao;
import com.onlinebook.model.Flight;
import com.onlinebook.model.FlightPassengerDetails;
import com.onlinebook.model.FlightSeatDetails;
import com.onlinebook.model.PaymentFlight;

/**
 * Servlet implementation class AirlineServlet
 */
@WebServlet("/FlightServlet")
public class FlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FlightServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	RequestDispatcher dispatcher;
	FlightDao dao = new FlightDao();
	PrintWriter out;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String choice = request.getParameter("choice");
		System.out.println(choice);
		switch (choice) {
		case "add":
			insert(request, response);
			break;
		case "update":
			update(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		case "bookflightticket":
			System.out.println("Booking Flight Ticket....");
			bookFlightticket(request, response);
			break;
		case "deleteflightticket":
			System.out.println("Booking Flight Ticket....");
			deleteFlightTicket(request, response);
			break;
		case "Pay using Bank":
			System.out.println("Booking Flight Ticket Using Bank....");
			bankPayment_flight(request, response);
			break;
		case "Pay Using Card":
			System.out.println("Booking Flight Ticket Using Card....");
			cardPayment_flight(request, response);
			break;

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Flight addFlight = new Flight();
		String flightName = request.getParameter("flightName");
		String flightNumber = request.getParameter("flightNumber");
		String flightType = request.getParameter("flightType");
		String fromStation = request.getParameter("fromStation");
		String toStation = request.getParameter("toStation");
		String departure = request.getParameter("departure");
		String departureTime = request.getParameter("departureTime");
		String arrivalTIme = request.getParameter("ArrivalTime");
		String priceINR = request.getParameter("priceINR");

		addFlight.setFlightName(flightName);
		addFlight.setFlightNumber(flightNumber);
		addFlight.setFlightType(flightType);
		addFlight.setFromStation(fromStation);
		addFlight.setToStation(toStation);
		addFlight.setDeparture(departure);
		addFlight.setDepartureTime(departureTime);
		addFlight.setArrivalTime(arrivalTIme);
		addFlight.setPrice(priceINR);

		int i = dao.save(addFlight);

		if (i > 0) {
			request.setAttribute("status", "success");
			dispatcher = request.getRequestDispatcher("ViewFlight.jsp");
			dispatcher.forward(request, response);
		} else {
//			request.setAttribute("status", "Failed");
			dispatcher = request.getRequestDispatcher("AddFlight.jsp");
			dispatcher.forward(request, response);
//			out.println("<h3>Error Occured!! Please Try Again");

		}

	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Flight updateflight = new Flight();

		String flightName = request.getParameter("flightName");
		String flightNumber = request.getParameter("flightNumber");
		String flightType = request.getParameter("flightType");
		String fromStation = request.getParameter("fromStation");
		String toStation = request.getParameter("toStation");
		String departure = request.getParameter("departure");
		String departureTime = request.getParameter("departureTime");
		String arrivalTIme = request.getParameter("ArrivalTime");
		String priceINR = request.getParameter("priceINR");

//		String status = request.getParameter("status");

		updateflight.setFlightName(flightName);
		updateflight.setFlightNumber(flightNumber);
		updateflight.setFlightType(flightType);
		updateflight.setFromStation(fromStation);
		updateflight.setToStation(toStation);
		updateflight.setDeparture(departure);
		updateflight.setDepartureTime(departureTime);
		updateflight.setArrivalTime(arrivalTIme);
		updateflight.setPrice(priceINR);

		int i = dao.update(updateflight);
		if (i > 0) {
			request.setAttribute("status", "success");
			dispatcher = request.getRequestDispatcher("ViewFlight.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("ViewBus.jsp");
		} else {
			System.out.println("Error Occured during update!!!");
			request.setAttribute("status", "failed");
			dispatcher = request.getRequestDispatcher("ViewFlight.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("updateflight.jsp");
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String flightNumber = request.getParameter("FlightNumber");

		int i = dao.delete(flightNumber);
		if (i > 0) {

			request.setAttribute("status", "deleted");
			dispatcher = request.getRequestDispatcher("ViewFlight.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("ViewBus.jsp");
		}

	}

	protected void bookFlightticket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		FlightPassengerDetails flightPassenger = new FlightPassengerDetails();
		FlightSeatDetails seat = new FlightSeatDetails();
		System.out.println("Booked Bus Ticket....");

		String firstName = request.getParameter("firstName");
		String lasttName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String mobileNum = request.getParameter("mobileNum");
		String age = request.getParameter("age");
		String flightNumber = request.getParameter("flightNumber");
		String totalSeats = request.getParameter("totalFlightSeat");
		String totalAmount = request.getParameter("totalFlightAmount");
		String flightSeatNumbers = request.getParameter("flightSeatNumbers");
		String selectClass = request.getParameter("selectClass");

		flightPassenger.setFlightNumber(flightNumber);
		flightPassenger.setFirstName(firstName);
		flightPassenger.setLastName(lasttName);
		flightPassenger.setEmail(email);
		flightPassenger.setMobileNumber(mobileNum);
		flightPassenger.setAge(age);
		flightPassenger.setTotalSeats(totalSeats);
		flightPassenger.setTotalAmount(totalAmount);
		flightPassenger.setSeatNumbers(flightSeatNumbers);
		flightPassenger.setSeatClassType(selectClass);

		seat.setSeatNumbers(flightSeatNumbers);
		seat.setFlightNumber(flightNumber);

		dao.saveSeatNumber(seat);
		
		int i = dao.saveFlightPassengerDetails(flightPassenger);

		if (i > 0) {
			request.setAttribute("status", "success");
			session.setAttribute("flightNumber", flightNumber);
			dispatcher = request.getRequestDispatcher("PaymentFlight.jsp");
			dispatcher.forward(request, response);
		} else {
//				request.setAttribute("status", "Failed");
			dispatcher = request.getRequestDispatcher("FlightPassengerDetails.jsp");
			dispatcher.forward(request, response);
//				out.println("<h3>Error Occured!! Please Try Again");

		}

	}

	protected void deleteFlightTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String flightNumber = request.getParameter("FlightNumber");

		int i = dao.deleteFlightPassengerTicket(flightNumber);
		if (i > 0) {

			request.setAttribute("status", "ticketDeleted");
			dispatcher = request.getRequestDispatcher("ViewFlightBooking.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("ViewBus.jsp");
		}

	}

	protected void bankPayment_flight(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PaymentFlight bankPaymentFlight = new PaymentFlight();
		System.out.println("Booked Bus Ticket....");
		String bankName = request.getParameter("bankName");
		String beneficiaryName = request.getParameter("beneficiaryName");
		String accountNumber = request.getParameter("accountNumber");
		String ifscCode = request.getParameter("ifscCode");
		String flightNumber = request.getParameter("flightNumber");

		bankPaymentFlight.setBankName(bankName);
		bankPaymentFlight.setBeneficiaryName(beneficiaryName);
		bankPaymentFlight.setAccountNumber(accountNumber);
		bankPaymentFlight.setIFSC(ifscCode);

		int i = dao.saveBankPaymentFlight(bankPaymentFlight);

		if (i > 0) {
			request.setAttribute("statusBankFlight", "BankTransactionFlight");
//			session.setAttribute("flightNumber", flightNumber);
//			response.sendRedirect("ViewFlightBooking.jsp?flightNumber=" + flightNumber);
			dispatcher = request.getRequestDispatcher("UserHome.jsp");
			dispatcher.forward(request, response);
		} else {
//				request.setAttribute("status", "Failed");
			request.setAttribute("statusBankFlight", "BankTransactionFailed");
			dispatcher = request.getRequestDispatcher("PaymentFlight.jsp");
			dispatcher.forward(request, response);
//				out.println("<h3>Error Occured!! Please Try Again");

		}

	}

	protected void cardPayment_flight(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PaymentFlight cardPaymentFlight = new PaymentFlight();
		System.out.println("Booked Bus Ticket....");
		String flightNumber = request.getParameter("flightNumber");
		String cardNumber = request.getParameter("cardNumber");
		String cardExpireDate = request.getParameter("cardExpireDate");
		String CVV = request.getParameter("cvv");

		cardPaymentFlight.setCardNumber(cardNumber);
		cardPaymentFlight.setCardExpireDate(cardExpireDate);
		cardPaymentFlight.setCVV(CVV);

		int i = dao.saveCardPaymentFlight(cardPaymentFlight);

		if (i > 0) {
			request.setAttribute("statusCardFlight", "CardTransactionFlight");
//			session.setAttribute("flightNumber", flightNumber);
//			response.sendRedirect("ViewFlightBooking.jsp?flightNumber=" + flightNumber);
			dispatcher = request.getRequestDispatcher("UserHome.jsp");
			dispatcher.forward(request, response);
		} else {
//				request.setAttribute("status", "Failed");
			request.setAttribute("statusCardFlight", "CardTransactionFailed");
			dispatcher = request.getRequestDispatcher("PaymentFlight.jsp");
			dispatcher.forward(request, response);
//				out.println("<h3>Error Occured!! Please Try Again");

		}

	}
}
