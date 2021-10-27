package com.data;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.dbconnection.JDBC;

public class ContextListener implements ServletContextListener {

	private static final Connection Connection = null;
	private static Logger log = Logger.getLogger(ContextListener.class);

	@SuppressWarnings("static-access")
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String fullPath = "D:\\Log4j_1\\log4j.properties";
		Properties logProperties = new Properties();

		try {

			logProperties.load(new FileInputStream(fullPath));
			PropertyConfigurator.configure(logProperties);

			log = Logger.getLogger(ContextListener.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unused")
		Connection con = null;

		try {
			con = new JDBC().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		@SuppressWarnings("unused")
		JDBC dbManager = (JDBC) ctx.getAttribute("DBManager");
		JDBC.closeConnection(Connection);

	}

}
