package com.Servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dbconnection.JDBC;

public class Display extends MainServlet {
	private static final long serialVersionUID = 1L;

	public Display() {
		super();

	}

	@Override
	JSONObject Services(JSONObject req) {
		JSONObject response = new JSONObject();
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = JDBC.getConnection();

			String SQLINSERT = "SELECT * FROM Employee";

			ps = connection.prepareStatement(SQLINSERT);

			ResultSet rs = ps.executeQuery();

			JSONArray array = new JSONArray();

			while (rs.next()) {

				JSONObject result = new JSONObject();

				result.put("Id", rs.getString("Emp_code"));
				result.put("Name", rs.getString("Name"));
				result.put("Designation", rs.getString("Designation"));
				result.put("Qualification", rs.getString("Qualification"));
				result.put("Status", rs.getString("Status"));
				result.put("Salary", rs.getInt("Salary"));

				array.put(result);
			}
			response.put("response", array);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Sorry, something wrong!", e);

			response.put("Message", "Something went wrong!");

		}
		JDBC.closeStatement(ps);
		JDBC.closeConnection(connection);
		logger.info("Connection closed");

		return response;

	}

}
