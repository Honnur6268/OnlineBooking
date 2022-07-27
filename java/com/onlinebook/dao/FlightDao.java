package com.onlinebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.onlinebook.model.Bus;
import com.onlinebook.model.BusPassengerDetails;
import com.onlinebook.model.BusSeatDetails;
import com.onlinebook.model.Flight;
import com.onlinebook.model.FlightPassengerDetails;
import com.onlinebook.model.FlightSeatDetails;
import com.onlinebook.model.PaymentBus;
import com.onlinebook.model.PaymentFlight;
import com.onlinebook.mysqlconnection.MyConnection;

public class FlightDao {
	public int save(Flight addFlight) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into flightdetails(FlightName, FlightNumber, FlightType, FromStation, ToStation, Departure, DepartureTime,ArrivalTime, PriceINR) values(?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, addFlight.getFlightName());
			preparedStatement.setString(2, addFlight.getFlightNumber());
			preparedStatement.setString(3, addFlight.getFlightType());			
			preparedStatement.setString(4, addFlight.getFromStation());
			preparedStatement.setString(5, addFlight.getToStation());
			preparedStatement.setString(6, addFlight.getDeparture());
			preparedStatement.setString(7, addFlight.getDepartureTime());
			preparedStatement.setString(8, addFlight.getArrivalTime());
			preparedStatement.setString(9,  addFlight.getPrice());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}
	
	public int update(Flight updateFlight) {
		int status = 0;
		try {
			Connection con = MyConnection.getCon();

			PreparedStatement ps = con.prepareStatement(
					"update flightdetails set FromStation=?,ToStation=?,Departure=?, DepartureTime=?, ArrivalTime=?, PriceINR=? where FlightNumber=?");
			System.out.println("id= " + updateFlight.getFlightNumber() + " " + updateFlight.getFromStation() + " "
					+ updateFlight.getToStation() + " " + updateFlight.getDeparture() + " " + updateFlight.getDepartureTime()
					+ " " + updateFlight.getArrivalTime() + " " + updateFlight.getPrice());

			ps.setString(1, updateFlight.getFromStation());
			ps.setString(2, updateFlight.getToStation());
			ps.setString(3, updateFlight.getDeparture());
			ps.setString(4, updateFlight.getDepartureTime());
			ps.setString(5, updateFlight.getArrivalTime());
			ps.setString(6, updateFlight.getPrice());
			ps.setString(7, updateFlight.getFlightNumber());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
	
	public int delete(String flightNumber) {
		int status = 0;
		try {
			System.out.println("hai");
			Connection con = MyConnection.getCon();
			PreparedStatement ps = con.prepareStatement("delete from flightdetails where FlightNumber=?");
			ps.setString(1, flightNumber);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
	
	public int saveFlightPassengerDetails(FlightPassengerDetails passenger) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into FlightPassengerDetails(FirstName,LastName,Email, MobileNumber,Age, FlightNumber, TotalSeats, Total_Amount, SeatNumbers, SeatClassType) values(?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, passenger.getFirstName());
			preparedStatement.setString(2, passenger.getLastName());
			preparedStatement.setString(3, passenger.getEmail());
			preparedStatement.setString(4, passenger.getMobileNumber());
			preparedStatement.setString(5, passenger.getAge());
			preparedStatement.setString(6, passenger.getFlightNumber());
			preparedStatement.setString(7, passenger.getTotalSeats());
			preparedStatement.setString(8, passenger.getTotalAmount());
			preparedStatement.setString(9, passenger.getSeatNumbers());
			preparedStatement.setString(10, passenger.getSeatClassType());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}
	
	public int deleteFlightPassengerTicket(String flightNumber) {
		int status = 0;
		try {
			System.out.println("hai");
			Connection con = MyConnection.getCon();
			PreparedStatement ps = con.prepareStatement("delete from flightpassengerdetails where FlightNumber=?");
			ps.setString(1, flightNumber);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
	
	public int saveBankPaymentFlight(PaymentFlight bankPaymentFlight) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into Payment_Flight(BankName, BeneficiaryName, AccountNumber, IFSC) values(?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bankPaymentFlight.getBankName());
			preparedStatement.setString(2, bankPaymentFlight.getBeneficiaryName());
			preparedStatement.setString(3, bankPaymentFlight.getAccountNumber());
			preparedStatement.setString(4, bankPaymentFlight.getIFSC());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}
	
	
	public int saveCardPaymentFlight(PaymentFlight cardPaymentFlight) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into CardPayment_Flight(CardNumber, ExpiryDate, CVV) values(?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cardPaymentFlight.getCardNumber());
			preparedStatement.setString(2, cardPaymentFlight.getCardExpireDate());
			preparedStatement.setString(3, cardPaymentFlight.getCVV());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}
	
	public Flight getRecordByFlightNumber(String flightNumber) {
		Flight flight = new Flight();
		try {
			Connection con = MyConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from flightdetails where FlightNumber=?");
			ps.setString(1, flightNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flight.setFlightName(rs.getString("FlightName"));
				flight.setFlightNumber(rs.getString("flightNumber"));
				flight.setFlightType(rs.getString("FlightType"));
				flight.setFromStation(rs.getString("FromStation"));
				flight.setToStation(rs.getString("ToStation"));
				flight.setDeparture(rs.getString("Departure"));
				flight.setDepartureTime(rs.getString("DepartureTime"));
				flight.setArrivalTime(rs.getString("ArrivalTime"));
				flight.setPrice(rs.getString("PriceINR"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return flight;
	}
	
	public void saveSeatNumber(FlightSeatDetails seat) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into flightseatdetails(SeatNumbers, FlightNumber) values(?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,seat.getSeatNumbers());
			preparedStatement.setString(2,seat.getFlightNumber());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

//		return status;

	}
}
