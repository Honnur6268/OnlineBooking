package com.onlinebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.onlinebook.model.Bus;
import com.onlinebook.model.Login;
import com.onlinebook.mysqlconnection.MyConnection;

public class LoginDao {

	public int signUP(Login signUp) {
		int status = 0;
		Connection connection = MyConnection.getCon();

		String query = "insert into user_registration(Name, Email, Password, Age, Mobile_Number, Gender) values(?,?,?,?,?,?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, signUp.getName());
			preparedStatement.setString(2, signUp.getEmail());
			preparedStatement.setString(3, signUp.getPass());
			preparedStatement.setInt(4, signUp.getAge());
			preparedStatement.setString(5, signUp.getMobileNumber());
			preparedStatement.setString(6, signUp.getGender());

			status = preparedStatement.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return status;
	}
	
	public boolean vaildate(Login bean) {
		boolean result = false;
		Connection connection = MyConnection.getCon();
		String sql = "select * from user_registration where Email=? and Password=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, bean.getEmail());
			ps.setString(2, bean.getPass());
			ResultSet rs = ps.executeQuery();
		
			result = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
