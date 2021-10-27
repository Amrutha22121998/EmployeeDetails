package com.Servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONObject;

import com.dbconnection.JDBC;

@SuppressWarnings("serial")
public class Update extends MainServlet {
	public Update() {
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
			String SQLUPDATE = "UPDATE Employee SET Name = ?, Designation = ?, Qualification =?, Status =?, Salary=?";
			SQLUPDATE += " WHERE Emp_code = ?";
			ps = connection.prepareStatement(SQLUPDATE);
			ps.setString(1, req.getString("Name"));
			ps.setString(2, req.getString("Designation"));
			ps.setString(3, req.getString("Qualification"));
			ps.setString(4, req.getString("Status"));
			ps.setInt(5, req.getInt("Salary"));
			ps.setInt(6, req.getInt("Emp_code"));
			int executeUpdate = ps.executeUpdate();

			if (executeUpdate > 0) {
				logger.info("Employee Details updated");
				result.put("Message", "Employee Details updated");

			} else {
				logger.info("Employee cannot be updated");
				result.put("Message", "Employee cannot be updated");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Sorry, something wrong!", e);
			result.put("Message", "Error!");
		}finally {
		JDBC.closeStatement(ps);
		JDBC.closeConnection(connection);
		logger.info("Connection closed");}

		return result;

	}

}
