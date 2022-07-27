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

import com.onlinebook.dao.TrainDao;
import com.onlinebook.model.Bus;
import com.onlinebook.model.BusPassengerDetails;
import com.onlinebook.model.PaymentBus;
import com.onlinebook.model.PaymentTrain;
import com.onlinebook.model.Train;
import com.onlinebook.model.TrainPassengerDetails;
import com.onlinebook.model.TrainSeatDetails;

/**
 * Servlet implementation class TrainServlet
 */
@WebServlet("/TrainServlet")
public class TrainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TrainServlet() {
		super();
	}

	RequestDispatcher dispatcher;
	TrainDao dao = new TrainDao();
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
		case "booktrainticket":
			System.out.println("Booking Bus Ticket....");
			bookTrainTicket(request, response);
			break;
		case "deletetrainticket":
			System.out.println("Booking Bus Ticket....");
			deleteTrainTicket(request, response);
			break;
		case "Pay Using Bank":
			System.out.println("Booking Bus Ticket Using Bank....");
			bankPayment_train(request, response);
			break;
		case "Pay Using Card":
			System.out.println("Booking Bus Ticket Using Card....");
			cardPayment_train(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Train addtrain = new Train();
		String trainName = request.getParameter("trainName");
		String trainNumber = request.getParameter("trainNumber");
		String trainType = request.getParameter("trainType");
		String fromStation = request.getParameter("fromStation");
		String toStation = request.getParameter("toStation");
		String departure = request.getParameter("departure");
		String departureTime = request.getParameter("departureTime");
		String arrivalTIme = request.getParameter("ArrivalTime");
		String priceINR = request.getParameter("priceINR");

		addtrain.setTrainName(trainName);
		addtrain.setTrainNumber(trainNumber);
		addtrain.setTrainType(trainType);
		addtrain.setFromStation(fromStation);
		addtrain.setToStation(toStation);
		addtrain.setDeparture(departure);
		addtrain.setDepartureTime(departureTime);
		addtrain.setArrivalTime(arrivalTIme);
		addtrain.setPrice(priceINR);

		int i = dao.save(addtrain);

		if (i > 0) {
			request.setAttribute("status", "success");
			dispatcher = request.getRequestDispatcher("ViewTrain.jsp");
			dispatcher.forward(request, response);
		} else {
//			request.setAttribute("status", "Failed");
			dispatcher = request.getRequestDispatcher("AddTrain.jsp");
			dispatcher.forward(request, response);
//			out.println("<h3>Error Occured!! Please Try Again");

		}

	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Train updatetrain = new Train();

		PrintWriter out = response.getWriter();

		String trainName = request.getParameter("trainName");
		String trainNumber = request.getParameter("trainNumber");
		String trainType = request.getParameter("trainType");
		String fromStation = request.getParameter("fromStation");
		String toStation = request.getParameter("toStation");
		String departure = request.getParameter("departure");
		String departureTime = request.getParameter("departureTime");
		String arrivalTIme = request.getParameter("ArrivalTime");
		String priceINR = request.getParameter("priceINR");

//		String status = request.getParameter("status");

		updatetrain.setTrainName(trainName);
		updatetrain.setTrainNumber(trainNumber);
		updatetrain.setTrainType(trainType);
		updatetrain.setFromStation(fromStation);
		updatetrain.setToStation(toStation);
		updatetrain.setDeparture(departure);
		updatetrain.setDepartureTime(departureTime);
		updatetrain.setArrivalTime(arrivalTIme);
		updatetrain.setPrice(priceINR);

		int i = dao.update(updatetrain);
		if (i > 0) {
			request.setAttribute("status", "success");
			dispatcher = request.getRequestDispatcher("ViewTrain.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println("Error Occured during update!!!");
			request.setAttribute("status", "failed");
			dispatcher = request.getRequestDispatcher("ViewTrain.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String trainNumber = request.getParameter("TrainNumber");

		int i = dao.delete(trainNumber);
		if (i > 0) {

			request.setAttribute("status", "deleted");
			dispatcher = request.getRequestDispatcher("ViewTrain.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void bookTrainTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		TrainPassengerDetails trainPassenger = new TrainPassengerDetails();
		TrainSeatDetails seat = new TrainSeatDetails();
		System.out.println("Booked Bus Ticket....");

		String firstName = request.getParameter("firstName");
		String lasttName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String mobileNum = request.getParameter("mobileNum");
		String age = request.getParameter("age");
		String trainNumber = request.getParameter("trainNumber");
		String totalSeats = request.getParameter("totalTrainSeat");
		String totalAmount = request.getParameter("totalTrainAmount");
		String trainSeatNumbers = request.getParameter("trainSeatNumbers");
		String selectClass = request.getParameter("selectClass");

		trainPassenger.setTrainNumber(trainNumber);
		;
		trainPassenger.setFirstName(firstName);
		trainPassenger.setLastName(lasttName);
		trainPassenger.setEmail(email);
		trainPassenger.setMobileNumber(mobileNum);
		trainPassenger.setAge(age);
		trainPassenger.setTotalSeats(totalSeats);
		trainPassenger.setTotalAmount(totalAmount);
		trainPassenger.setSeatNumbers(trainSeatNumbers);
		trainPassenger.setSeatClassTYpe(selectClass);
		
		seat.setSeatNumbers(trainSeatNumbers);
		seat.setTrainNumber(trainNumber);

		dao.saveSeatNumber(seat);

		int i = dao.saveTrainPassengerDetails(trainPassenger);

		if (i > 0) {
			request.setAttribute("status", "success");
			session.setAttribute("trainNumber", trainNumber);
			dispatcher = request.getRequestDispatcher("PaymentTrain.jsp");
			dispatcher.forward(request, response);
		} else {
//				request.setAttribute("status", "Failed");
			dispatcher = request.getRequestDispatcher("TrainPassengerDetails.jsp");
			dispatcher.forward(request, response);
//				out.println("<h3>Error Occured!! Please Try Again");

		}

	}

	protected void deleteTrainTicket(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String trainNumber = request.getParameter("TrainNumber");

		int i = dao.deleteTrainPassengerTicket(trainNumber);
		if (i > 0) {

			request.setAttribute("status", "ticketDeleted");
			dispatcher = request.getRequestDispatcher("ViewTrainBooking.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("ViewBus.jsp");
		}

	}

	protected void bankPayment_train(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PaymentTrain bankPaymentTrain = new PaymentTrain();
		System.out.println("Booked Bus Ticket....");
		String bankName = request.getParameter("bankName");
		String beneficiaryName = request.getParameter("beneficiaryName");
		String accountNumber = request.getParameter("accountNumber");
		String ifscCode = request.getParameter("ifscCode");
		String trainNumber = request.getParameter("trainNumber");

		bankPaymentTrain.setBankName(bankName);
		bankPaymentTrain.setBeneficiaryName(beneficiaryName);
		bankPaymentTrain.setAccountNumber(accountNumber);
		bankPaymentTrain.setIFSC(ifscCode);

		int i = dao.saveBankPaymentTrain(bankPaymentTrain);

		if (i > 0) {
			request.setAttribute("statusBankTrain", "BankTransactionTrain");
//			response.sendRedirect("ViewBusBooking.jsp");
			dispatcher = request.getRequestDispatcher("UserHome.jsp");
			dispatcher.forward(request, response);
		} else {
//				request.setAttribute("status", "Failed");
			request.setAttribute("statusBankTrain", "BankTransactionFailed");
			dispatcher = request.getRequestDispatcher("PaymentTrain.jsp");
			dispatcher.forward(request, response);
//				out.println("<h3>Error Occured!! Please Try Again");

		}

	}

	protected void cardPayment_train(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PaymentTrain cardPaymentTrain = new PaymentTrain();
		System.out.println("Booked Bus Ticket....");
		String trainNumber = request.getParameter("trainNumber");
		String cardNumber = request.getParameter("cardNumber");
		String cardExpireDate = request.getParameter("cardExpireDate");
		String CVV = request.getParameter("cvv");

		cardPaymentTrain.setCardNumber(cardNumber);
		cardPaymentTrain.setCardExpireDate(cardExpireDate);
		cardPaymentTrain.setCVV(CVV);

		int i = dao.saveCardPaymentTrain(cardPaymentTrain);

		if (i > 0) {
			request.setAttribute("statusCardTrain", "CardTransactionTrain");
			dispatcher = request.getRequestDispatcher("UserHome.jsp");
			dispatcher.forward(request, response);
		} else {
//				request.setAttribute("status", "Failed");
			request.setAttribute("statusCardTrain", "CardTransactionFailed");
			dispatcher = request.getRequestDispatcher("PaymentTrain.jsp");
			dispatcher.forward(request, response);
//				out.println("<h3>Error Occured!! Please Try Again");

		}

	}
}
