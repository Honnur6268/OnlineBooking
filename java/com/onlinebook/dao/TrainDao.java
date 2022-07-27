package com.onlinebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.onlinebook.model.Bus;
import com.onlinebook.model.BusPassengerDetails;
import com.onlinebook.model.BusSeatDetails;
import com.onlinebook.model.PaymentBus;
import com.onlinebook.model.PaymentTrain;
import com.onlinebook.model.Train;
import com.onlinebook.model.TrainPassengerDetails;
import com.onlinebook.model.TrainSeatDetails;
import com.onlinebook.mysqlconnection.MyConnection;

public class TrainDao {
	public int save(Train addTrain) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into traindetails(TrainName, TrainNumber, TrainType, FromStation, ToStation, Departure, DepartureTime,ArrivalTime, PriceINR) values(?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, addTrain.getTrainName());
			preparedStatement.setString(2, addTrain.getTrainNumber());
			preparedStatement.setString(3, addTrain.getTrainType());
			preparedStatement.setString(4, addTrain.getFromStation());
			preparedStatement.setString(5, addTrain.getToStation());
			preparedStatement.setString(6, addTrain.getDeparture());
			preparedStatement.setString(7, addTrain.getDepartureTime());
			preparedStatement.setString(8, addTrain.getArrivalTime());
			preparedStatement.setString(9, addTrain.getPrice());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}

	public int update(Train updateTrian) {
		int status = 0;
		try {
			Connection con = MyConnection.getCon();

			PreparedStatement ps = con.prepareStatement(
					"update traindetails set FromStation=?,ToStation=?,Departure=?, DepartureTime=?, ArrivalTime=?, PriceINR=? where TrainNumber=?");
			System.out.println("id= " + updateTrian.getTrainNumber() + " " + updateTrian.getFromStation() + " "
					+ updateTrian.getToStation() + " " + updateTrian.getDeparture() + " "
					+ updateTrian.getDepartureTime() + " " + updateTrian.getArrivalTime() + " "
					+ updateTrian.getPrice());

			ps.setString(1, updateTrian.getFromStation());
			ps.setString(2, updateTrian.getToStation());
			ps.setString(3, updateTrian.getDeparture());
			ps.setString(4, updateTrian.getDepartureTime());
			ps.setString(5, updateTrian.getArrivalTime());
			ps.setString(6, updateTrian.getPrice());
			ps.setString(7, updateTrian.getTrainNumber());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public int delete(String trainNumber) {
		int status = 0;
		try {
			System.out.println("hai");
			Connection con = MyConnection.getCon();
			PreparedStatement ps = con.prepareStatement("delete from traindetails where TrainNumber=?");
			ps.setString(1, trainNumber);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}

	public int saveTrainPassengerDetails(TrainPassengerDetails passenger) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into TrainPassengerDetails(FirstName,LastName,Email, MobileNumber,Age, TrainNumber, TotalSeats, Total_Amount, SeatNumbers, SeatClassType) values(?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, passenger.getFirstName());
			preparedStatement.setString(2, passenger.getLastName());
			preparedStatement.setString(3, passenger.getEmail());
			preparedStatement.setString(4, passenger.getMobileNumber());
			preparedStatement.setString(5, passenger.getAge());
			preparedStatement.setString(6, passenger.getTrainNumber());
			preparedStatement.setString(7, passenger.getTotalSeats());
			preparedStatement.setString(8, passenger.getTotalAmount());
			preparedStatement.setString(9, passenger.getSeatNumbers());
			preparedStatement.setString(10, passenger.getSeatClassTYpe());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}

	public int deleteTrainPassengerTicket(String trainNumber) {
		int status = 0;
		try {
			System.out.println("hai");
			Connection con = MyConnection.getCon();
			PreparedStatement ps = con.prepareStatement("delete from trainpassengerdetails where TrainNumber=?");
			ps.setString(1, trainNumber);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
	
	public int saveBankPaymentTrain(PaymentTrain bankPaymentTrain) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into Payment_Train(BankName, BeneficiaryName, AccountNumber, IFSC) values(?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bankPaymentTrain.getBankName());
			preparedStatement.setString(2, bankPaymentTrain.getBeneficiaryName());
			preparedStatement.setString(3, bankPaymentTrain.getAccountNumber());
			preparedStatement.setString(4, bankPaymentTrain.getIFSC());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}
	
	public int saveCardPaymentTrain(PaymentTrain cardPaymentTrain) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into CardPayment_Train(CardNumber, ExpiryDate, CVV) values(?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cardPaymentTrain.getCardNumber());
			preparedStatement.setString(2, cardPaymentTrain.getCardExpireDate());
			preparedStatement.setString(3, cardPaymentTrain.getCVV());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;

	}

	public Train getRecordByTrainNumber(String trainNumber) {
		Train train = new Train();
		try {
			Connection con = MyConnection.getCon();
			PreparedStatement ps = con.prepareStatement("select * from traindetails where TrainNumber=?");
			ps.setString(1, trainNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				train.setTrainName(rs.getString("TrainName"));
				train.setTrainNumber(rs.getString("TrainNumber"));
				train.setTrainType(rs.getString("TrainType"));
				train.setFromStation(rs.getString("FromStation"));
				train.setToStation(rs.getString("ToStation"));
				train.setDeparture(rs.getString("Departure"));
				train.setDepartureTime(rs.getString("DepartureTime"));
				train.setArrivalTime(rs.getString("ArrivalTime"));
				train.setPrice(rs.getString("PriceINR"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return train;
	}
	
	public void saveSeatNumber(TrainSeatDetails seat) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into trainseatdetails(SeatNumbers, TrainNumber) values(?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,seat.getSeatNumbers());
			preparedStatement.setString(2,seat.getTrainNumber());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

//		return status;

	}
}
