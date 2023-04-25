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

		}

		FactoryDAO instance = new FactoryDAO("jdbc:mysql://localhost:3306/bddr_cours", "root", "network");

		return instance;
    }
	
	public Connection getConnection() throws SQLException {
		Connection connexion = DriverManager.getConnection(url, username, password);
		return connexion;
	}

}
