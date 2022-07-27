package com.onlinebook.mysqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	public static Connection getCon() {

		Connection c = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (c == null) {
				c = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinebooking", "root", "Honnu@6268");

			}

		}

		catch (Exception e) {
			System.out.println(e);
		}
		return c;
	}

}
