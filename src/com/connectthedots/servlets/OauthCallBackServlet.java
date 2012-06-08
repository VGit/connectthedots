package com.connectthedots.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connectthedots.Constants;
import com.connectthedots.service.SignInService;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;

public class OauthCallBackServlet extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(OauthCallBackServlet.class
			.getName());
	SignInService signInService = new SignInService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. Make the oauth call
		log.info("Maing the oauth call");
		String callBackUrl = Constants.OAUTH_CALL_BACK_URL;
		LinkedInRequestToken requestToken = signInService.getLinkedInRequestToken(callBackUrl);
		HttpSession session = request.getSession();
		session.setAttribute("requestToken", requestToken);
	}
}
