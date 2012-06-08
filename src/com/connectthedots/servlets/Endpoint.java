package com.connectthedots.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Http servlet wrapper class to add convenient functionality
 *
 */
@SuppressWarnings("serial")
public class Endpoint extends HttpServlet {
		
	/**
	 * Override to implement HTTP GET with request/response wrappers
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doGet(Request request, Response response) throws ServletException, IOException {
		super.doGet(request.getRequest(), response.getResponse());
	}
	@Override
	protected final void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(new Request(req), new Response(resp));
	}
	
	/**
	 * Override to implement HTTP POST with request/response wrappers
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPost(Request request, Response response) throws ServletException, IOException {
		super.doPost(request.getRequest(), response.getResponse());
	}
	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(new Request(req), new Response(resp));
	}
	
	/**
	 * Override to implement HTTP POST with request/response wrappers
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doPut(Request request, Response response) throws ServletException, IOException {
		super.doPut(request.getRequest(), response.getResponse());
	}
	@Override
	protected final void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPut(new Request(req), new Response(resp));
	}	
	
	/**
	 * Override to implement HTTP POST with request/response wrappers
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doDelete(Request request, Response response) throws ServletException, IOException {
		super.doDelete(request.getRequest(), response.getResponse());
	}
	@Override
	protected final void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDelete(new Request(req), new Response(resp));
	}

	protected void forward(HttpServletRequest req, HttpServletResponse resp, String path) {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected String getParameter(Request request, String parameterName) {
		String[] params = (String[]) request.getParameterMap().get(parameterName);
		if (params != null && params.length == 1) {
			return params[0];
		}
		return null;
	}
}