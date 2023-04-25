package com.dao;

import java.sql.*;

public class FactoryDAO {
	private String url;
	private String username;
	private String password;
	
	FactoryDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static FactoryDAO getInstance() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.getException();
		}

		return new FactoryDAO("jdbc:mysql://localhost:3306/bddr_cours", "root", "network");
    }
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}
