package com.Servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONObject;

import com.dbconnection.JDBC;

@SuppressWarnings("serial")
public class Create extends MainServlet {

	public Create() {
		super();

	}

	@Override
	JSONObject Services(JSONObject req) {
		JSONObject result = new JSONObject();
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = JDBC.getConnection();
			logger.info("Database Connected");
			String SQLINSERT = "insert into Employee (Name,Designation,Qualification,Status,Salary) values(?,?,?,?,?)";
			ps = connection.prepareStatement(SQLINSERT);
			ps.setString(1, req.getString("Name"));
			ps.setString(2,req.getString("Designation"));
			ps.setString(3, req.getString("Qualification"));
			ps.setString(4,  req.getString("Status"));
			ps.setInt(5, req.getInt("Salary"));
			int executeUpdate = ps.executeUpdate();
			if (executeUpdate > 0) {

				logger.info("Employee Details created");
				result.put("Message", "Employee Details Created");

			}

			else {
				logger.info("Invalid Employee Type");
				result.put("Message", "Invalid Employee Type");

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Sorry, something wrong!", e);
			result.put("Message", "Error!");
		}
		JDBC.closeStatement(ps);
		JDBC.closeConnection(connection);
		logger.info("Connection closed");

		return result;
	}

}
