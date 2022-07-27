package com.onlinebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.onlinebook.model.Bus;
import com.onlinebook.model.BusPassengerDetails;
import com.onlinebook.model.BusSeatDetails;
import com.onlinebook.model.PaymentBus;
import com.onlinebook.mysqlconnection.MyConnection;

public class BusDao {
	public int save(Bus addBus) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into busdetails(BusName, BusNumber, BusType, FromStation, ToStation, Departure, DepartureTime,ArrivalTime, PriceINR) values(?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, addBus.getBusName());
			preparedStatement.setString(2, addBus.getBusNumber());
			preparedStatement.setString(3, addBus.getBusType());
			preparedStatement.setString(4, addBus.getFromStation());
			preparedStatement.setString(5, addBus.getToStation());
			preparedStatement.setString(6, addBus.getDeparture());
			preparedStatement.setString(7, addBus.getDepartureTime());
			preparedStatement.setString(8, addBus.getArrivalTime());
			preparedStatement.setString(9, addBus.getPrice());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;
	}

	public int update(Bus updateBus) {
		int status = 0;
		try {
			Connection con = MyConnection.getCon();

			PreparedStatement ps = con.prepareStatement(
					"update busdetails set FromStation=?,ToStation=?,Departure=?, DepartureTime=?, ArrivalTime=?, PriceINR=? where BusNumber=?");
			System.out.println("id= " + updateBus.getBusNumber() + " " + updateBus.getFromStation() + " "
					+ updateBus.getToStation() + " " + updateBus.getDeparture() + " " + updateBus.getDepartureTime()
					+ " " + updateBus.getArrivalTime() + " " + updateBus.getPrice());

			ps.setString(1, updateBus.getFromStation());
			ps.setString(2, updateBus.getToStation());
			ps.setString(3, updateBus.getDeparture());
			ps.setString(4, updateBus.getDepartureTime());
			ps.setString(5, updateBus.getArrivalTime());
			ps.setString(6, updateBus.getPrice());
			ps.setString(7, updateBus.getBusNumber());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public int delete(String busNumber) {
		int status = 0;
		try {
			System.out.println("hai");
			Connection con = MyConnection.getCon();
			PreparedStatement ps = con.prepareStatement("delete from busdetails where BusNumber=?");
			ps.setString(1, busNumber);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public int saveBusPassengerDetails(BusPassengerDetails passenger) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into BusPassengerDetails(FirstName,LastName,Email, MobileNumber,Age, BusNumber, TotalSeats, Total_Amount, SeatNumbers) values(?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, passenger.getFirstName());
			preparedStatement.setString(2, passenger.getLastName());
			preparedStatement.setString(3, passenger.getEmail());
			preparedStatement.setString(4, passenger.getMobileNumber());
			preparedStatement.setString(5, passenger.getAge());
			preparedStatement.setString(6, passenger.getBusNumber());
			preparedStatement.setString(7, passenger.getTotalSeats());
			preparedStatement.setString(8, passenger.getTotalAmount());
			preparedStatement.setString(9, passenger.getSeatNumbers());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}
	
	public int deleteBusPassengerTicket(String busNumber) {
		int status = 0;
		try {
			System.out.println("hai");
			Connection con = MyConnection.getCon();
			PreparedStatement ps = con.prepareStatement("delete from buspassengerdetails where BusNumber=?");
			ps.setString(1, busNumber);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public int saveBankPaymentBus(PaymentBus bankPaymentBus) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into Payment_Bus(BankName, BeneficiaryName, AccountNumber, IFSC) values(?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bankPaymentBus.getBankName());
			preparedStatement.setString(2, bankPaymentBus.getBeneficiaryName());
			preparedStatement.setString(3, bankPaymentBus.getAccountNumber());
			preparedStatement.setString(4, bankPaymentBus.getIFSC());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}

	public int saveCardPaymentBus(PaymentBus cardPaymentBus) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into CardPayment_Bus(CardNumber, ExpiryDate, CVV) values(?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cardPaymentBus.getCardNumber());
			preparedStatement.setString(2, cardPaymentBus.getCardExpireDate());
			preparedStatement.setString(3, cardPaymentBus.getCVV());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}

	public Bus getRecordByBusNumber(String busNumber) {
		Bus bus = new Bus();
		try {
			Connection con = MyConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from busdetails where BusNumber=?");
			ps.setString(1, busNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				bus.setBusName(rs.getString("BusName"));
				bus.setBusNumber(rs.getString("busNumber"));
				bus.setBusType(rs.getString("BusType"));
				bus.setFromStation(rs.getString("FromStation"));
				bus.setToStation(rs.getString("ToStation"));
				bus.setDeparture(rs.getString("Departure"));
				bus.setDepartureTime(rs.getString("DepartureTime"));
				bus.setArrivalTime(rs.getString("ArrivalTime"));
				bus.setPrice(rs.getString("PriceINR"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return bus;
	}
	
	public int disableUserDetails(String email) {
		int status = 0;
		try {
			System.out.println("hai");
			Connection con = MyConnection.getCon();
			PreparedStatement ps = con.prepareStatement("delete from user_registration where Email=?");
			ps.setString(1, email);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
	
	public int saveSeatNumber(BusSeatDetails seat) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into seatdetails(SeatNumbers, BusNumber) values(?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,seat.getSeatNumbers());
			preparedStatement.setString(2,seat.getBusNumber());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}
	
	public int getSeatNumber(String busSeatNumber) {
		int status = 0;
		try {
			System.out.println("hai");
			Connection con = MyConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select *  from seatdetails where SeatNumbers=?");
			ps.setString(1, busSeatNumber);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
}
