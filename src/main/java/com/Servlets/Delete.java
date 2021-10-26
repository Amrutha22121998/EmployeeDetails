package com.Servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONObject;

import com.dbconnection.JDBC;

@SuppressWarnings("serial")
public class Delete extends MainServlet {

	public Delete() {
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

			int reqId = req.getInt("Emp_code");
			String SQLINSERT = "delete from  Employee where Emp_code = ? ";
			ps = connection.prepareStatement(SQLINSERT);
			ps.setInt(1, reqId);
			int executeUpdate = ps.executeUpdate();
			if (executeUpdate > 0) {
				logger.info("Employee Details Deleted");
				result.put("Message", "Deleted Succesfully");
			} else {
				logger.info("Invalid id");
				result.put("Message", "Invalid id");
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
