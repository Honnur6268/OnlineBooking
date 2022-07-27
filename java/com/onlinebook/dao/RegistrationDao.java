package com.onlinebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.onlinebook.model.Registration;
import com.onlinebook.mysqlconnection.MyConnection;

public class RegistrationDao {

	public String Regiterindb(Registration bean) {
		Connection con = MyConnection.getCon();
		String sql = "insert into user_registration(Name,Email,Password) values (?,?,?) ";
		int i = 0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, bean.getName());
			preparedStatement.setString(2, bean.getEmail());
			preparedStatement.setString(3, bean.getPass());
			i = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (i != 0) {
			return "User is registered";
		} else {
			return "Error!!!!";
		}
	}
}
