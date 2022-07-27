package com.onlinebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.onlinebook.model.Feedback;
import com.onlinebook.mysqlconnection.MyConnection;

public class FeedbackDao {

	public int saveFeedback(Feedback feedback) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into feedback(Name, Email, Rating, Comments) values(?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, feedback.getName());
			preparedStatement.setString(2, feedback.getEmail());
			preparedStatement.setString(3, feedback.getRating());
			preparedStatement.setString(4, feedback.getComments());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;
	}

}
