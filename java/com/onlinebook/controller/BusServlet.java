package com.onlinebook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.onlinebook.dao.BusDao;
import com.onlinebook.model.Bus;
import com.onlinebook.model.BusPassengerDetails;
import com.onlinebook.model.BusSeatDetails;
import com.onlinebook.model.PaymentBus;

/**
 * Servlet implementation class BusServlet
 */
@WebServlet("/BusServlet")
public class BusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher;
	BusDao dao = new BusDao();
	HttpSession session;

	public BusServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		case "bookbusticket":
			System.out.println("Booking Bus Ticket....");
			bookbusticket(request, response);
			break;
		case "deletebusticket":
			System.out.println("Booking Bus Ticket....");
			deleteBusTicket(request, response);
			break;
		case "Pay using Bank":
			System.out.println("Booking Bus Ticket Using Bank....");
			bankPayment_bus(request, response);
			break;
		case "Pay Using Card":
			System.out.println("Booking Bus Ticket Using Card....");
			cardPayment_bus(request, response);
			break;
		case "disableUser":
			disableUser(request, response);
			break;
		case "Check Availaibility":
			CheckSeatsAvailaibilty(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bus addbus = new Bus();
		String busName = request.getParameter("busName");
		String busNumber = request.getParameter("busNumber");
		String busType = request.getParameter("busType");
		String fromStation = request.getParameter("fromStation");
		String toStation = request.getParameter("toStation");
		String departure = request.getParameter("departure");
		String departureTime = request.getParameter("departureTime");
		String arrivalTIme = request.getParameter("ArrivalTime");
		String priceINR = request.getParameter("priceINR");

		addbus.setBusName(busName);
		addbus.setBusNumber(busNumber);
		addbus.setBusType(busType);
		addbus.setFromStation(fromStation);
		addbus.setToStation(toStation);
		addbus.setDeparture(departure);
		addbus.setDepartureTime(departureTime);
		addbus.setArrivalTime(arrivalTIme);
		addbus.setPrice(priceINR);

		int i = dao.save(addbus);

		if (i > 0) {
			request.setAttribute("status", "added");
			dispatcher = request.getRequestDispatcher("ViewBus.jsp");
			dispatcher.forward(request, response);
		} else {
//			request.setAttribute("status", "Failed");
			dispatcher = request.getRequestDispatcher("AddBus.jsp");
			dispatcher.forward(request, response);
//			out.println("<h3>Error Occured!! Please Try Again");

		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bus updatebus = new Bus();

		PrintWriter out = response.getWriter();

		String busName = request.getParameter("busName");
		String busNumber = request.getParameter("busNumber");
		String busType = request.getParameter("busType");
		String fromStation = request.getParameter("fromStation");
		String toStation = request.getParameter("toStation");
		String departure = request.getParameter("departure");
		String departureTime = request.getParameter("departureTime");
		String arrivalTIme = request.getParameter("ArrivalTime");
		String priceINR = request.getParameter("priceINR");

//		String status = request.getParameter("status");

		updatebus.setBusName(busName);
		updatebus.setBusNumber(busNumber);
		updatebus.setBusType(busType);
		updatebus.setFromStation(fromStation);
		updatebus.setToStation(toStation);
		updatebus.setDeparture(departure);
		updatebus.setDepartureTime(departureTime);
		updatebus.setArrivalTime(arrivalTIme);
		updatebus.setPrice(priceINR);

		int i = dao.update(updatebus);
		if (i > 0) {
			request.setAttribute("status", "success");
			dispatcher = request.getRequestDispatcher("ViewBus.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("ViewBus.jsp");
		} else {
			System.out.println("Error Occured during update!!!");
			request.setAttribute("status", "failed");
			dispatcher = request.getRequestDispatcher("ViewBus.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("UpdateBus.jsp");
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String busNumber = request.getParameter("BusNumber");

		int i = dao.delete(busNumber);
		if (i > 0) {

			request.setAttribute("status", "deleted");
			dispatcher = request.getRequestDispatcher("ViewBus.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("ViewBus.jsp");
		}

	}

	protected void bookbusticket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		BusPassengerDetails busPassenger = new BusPassengerDetails();
		BusSeatDetails seat = new BusSeatDetails();
		System.out.println("Booked Bus Ticket....");

//		String ID = request.getParameter("ID");
		String firstName = request.getParameter("firstName");
		String lasttName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String mobileNum = request.getParameter("mobileNum");
		String age = request.getParameter("age");
		String busNumber = request.getParameter("busNumber");
		String totalSeats = request.getParameter("totalBusSeat");
		String totalAmount = request.getParameter("totalBusAmount");
		String busSeatNumbers = request.getParameter("busSeatNumbers");

		busPassenger.setBusNumber(busNumber);
		busPassenger.setFirstName(firstName);
		busPassenger.setLastName(lasttName);
		busPassenger.setEmail(email);
		busPassenger.setMobileNumber(mobileNum);
		busPassenger.setAge(age);
		busPassenger.setTotalSeats(totalSeats);
		busPassenger.setTotalAmount(totalAmount);
		busPassenger.setSeatNumbers(busSeatNumbers);
		seat.setBusNumber(busNumber);

		seat.setSeatNumbers(busSeatNumbers);
		seat.setBusNumber(busNumber);
		dao.saveSeatNumber(seat);

//		seat.setSeatNumbers(busSeatNumbers);
//		seat.setBusNumber(busNumber);

//		dao.saveSeatNumber(seat);
		int i = dao.saveBusPassengerDetails(busPassenger);

		if (i > 0) {
			request.setAttribute("status", "success");
			session.setAttribute("busNumber", busNumber);
			session.setAttribute("totalAmount", totalAmount);
			dispatcher = request.getRequestDispatcher("Payment.jsp");
			dispatcher.forward(request, response);
		} else {
//				request.setAttribute("status", "Failed");
			dispatcher = request.getRequestDispatcher("BusPassengerDetails.jsp");
			dispatcher.forward(request, response);
//				out.println("<h3>Error Occured!! Please Try Again");

		}

//		int k = dao.saveSeatNumber(seat);
//		int i = dao.getSeatNumber(busSeatNumbers);
//		if (i > 0) {
//			request.setAttribute("status", "seatselected");
//			session.setAttribute("busNumber", busNumber);
//		} else {
//				int j = dao.saveBusPassengerDetails(busPassenger);
//				if (j > 0) {
//					request.setAttribute("status", "success");
//					session.setAttribute("busNumber", busNumber);
//					dispatcher = request.getRequestDispatcher("Payment.jsp");
//					dispatcher.forward(request, response);
//				} else {
////						request.setAttribute("status", "Failed");
//					dispatcher = request.getRequestDispatcher("BusPassengerDetails.jsp");
//					dispatcher.forward(request, response);
////						out.println("<h3>Error Occured!! Please Try Again");
//		
//				}
//
//		}

	}

	protected void deleteBusTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String busNumber = request.getParameter("BusNumber");

		int i = dao.deleteBusPassengerTicket(busNumber);
		if (i > 0) {

			request.setAttribute("status", "ticketDeleted");
			dispatcher = request.getRequestDispatcher("ViewBusBooking.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("ViewBus.jsp");
		}

	}

	protected void bankPayment_bus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PaymentBus bankPaymentBus = new PaymentBus();
		System.out.println("Booked Bus Ticket....");
		String bankName = request.getParameter("bankName");
		String beneficiaryName = request.getParameter("beneficiaryName");
		String accountNumber = request.getParameter("accountNumber");
		String ifscCode = request.getParameter("ifscCode");
		String busNumber = request.getParameter("busNumber");

		bankPaymentBus.setBankName(bankName);
		bankPaymentBus.setBeneficiaryName(beneficiaryName);
		bankPaymentBus.setAccountNumber(accountNumber);
		bankPaymentBus.setIFSC(ifscCode);

		int i = dao.saveBankPaymentBus(bankPaymentBus);

		if (i > 0) {
			request.setAttribute("statusBank", "BankTransaction");
//			session.setAttribute("busNumber", busNumber);
//			response.sendRedirect("ViewBusBooking.jsp");
			dispatcher = request.getRequestDispatcher("UserHome.jsp");
			dispatcher.forward(request, response);
		} else {
//				request.setAttribute("status", "Failed");
			request.setAttribute("statusBank", "BankTransactionFailed");
			dispatcher = request.getRequestDispatcher("Payment.jsp");
			dispatcher.forward(request, response);
//				out.println("<h3>Error Occured!! Please Try Again");

		}

	}

	protected void cardPayment_bus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PaymentBus cardPaymentBus = new PaymentBus();
		System.out.println("Booked Bus Ticket....");
		String busNumber = request.getParameter("busNumber");
		String cardNumber = request.getParameter("cardNumber");
		String cardExpireDate = request.getParameter("cardExpireDate");
		String CVV = request.getParameter("cvv");

		cardPaymentBus.setCardNumber(cardNumber);
		cardPaymentBus.setCardExpireDate(cardExpireDate);
		cardPaymentBus.setCVV(CVV);

		int i = dao.saveCardPaymentBus(cardPaymentBus);

		if (i > 0) {
			request.setAttribute("statusCard", "CardTransaction");
//			session.setAttribute("busNumber", busNumber);
//			response.sendRedirect("ViewBusBooking.jsp");
			dispatcher = request.getRequestDispatcher("UserHome.jsp");
			dispatcher.forward(request, response);
		} else {
//				request.setAttribute("status", "Failed");
			request.setAttribute("statusCard", "CardTransactionFailed");
			dispatcher = request.getRequestDispatcher("Payment.jsp");
			dispatcher.forward(request, response);
//				out.println("<h3>Error Occured!! Please Try Again");

		}

	}

	protected void disableUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("Email");

		int i = dao.disableUserDetails(email);
		if (i > 0) {

			request.setAttribute("status", "userDisabled");
			dispatcher = request.getRequestDispatcher("Customers.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("ViewBus.jsp");
		}

	}

	protected void CheckSeatsAvailaibilty(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		BusPassengerDetails busPassenger = new BusPassengerDetails();
		BusSeatDetails seat = new BusSeatDetails();
		System.out.println("Checking for seats Availablity....");

		String busNumber = request.getParameter("busNumber");
		String bookedSeats = request.getParameter("bookedSeats");
		String busSeatNumbers = request.getParameter("busSeatNumbers");
		Double LowerPrice = Double.parseDouble(request.getParameter("Price"));

		System.out.println(bookedSeats);
		System.out.println(busSeatNumbers);

//		String bookedSeatsArray[] = busSeatNumbers.split("  ");
		Integer[] bookedSeatsArrayInt = new Integer[busSeatNumbers.length()];				
				for(int i = 0; i<busSeatNumbers.length();i++) {
					bookedSeatsArrayInt[i] = (int)busSeatNumbers.charAt(i);
					System.out.println(bookedSeatsArrayInt[i] + " " + i);
				}
//		String busSeatArray[] = busSeatNumbers.split("  ");

//		seat.setBusNumber(busNumber);

		if (bookedSeats.equals(busSeatNumbers)) {
			request.setAttribute("status", "seatselected");
				session.setAttribute("lowerPrice", LowerPrice);
			dispatcher = request.getRequestDispatcher("SelectBusSeat.jsp");
			dispatcher.forward(request, response);
		} else {

			dispatcher = request.getRequestDispatcher("BusPassangerDetails.jsp");
			dispatcher.forward(request, response);
		}

	}

}
