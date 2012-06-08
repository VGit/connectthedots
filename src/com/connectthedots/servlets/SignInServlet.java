package com.connectthedots.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import com.connectthedots.service.SignInService;

public class SignInServlet extends Endpoint {

	private static final Logger log = Logger.getLogger(SignInServlet.class
			.getName());
	
	SignInService signInService = new SignInService();
	
	@Override
	protected void doGet(Request request, Response response)
			throws ServletException, IOException {
		log.info("In doGet method of SignInServlet");
		//1. Make a oauth call to get request token
	}
	
}
