package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.data.Data;

public abstract class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final Logger logger = Logger.getLogger(MainServlet.class);
	
   abstract JSONObject Services(JSONObject req);
    public MainServlet() {
    	super();
       
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		
  	}
	

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String payloadRequest = Data.getDataString(request);
		logger.info(payloadRequest);
		
	 JSONObject req = new JSONObject(payloadRequest);
	 JSONObject req1=new JSONObject();
	 req1.put("Request", req);
		logger.info(req1);
	
		
		JSONObject res=new JSONObject();
		
		res = Services(req);
		
		 JSONObject req2=new JSONObject();
		 req2.put("Response", res);
	System.out.println(res);
	logger.info(req2);
	
		
		request.setCharacterEncoding("utf8");
        response.setContentType("application/json");
		
		 PrintWriter out = response.getWriter();
	        try {
	            out.println(res);
	        } finally {
	            out.close();
	        }
	}
}