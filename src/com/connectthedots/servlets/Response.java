package com.connectthedots.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.connectthedots.http.ContentType;

/**
 * Http servlet response wrapper class to add convenient functionality.
 *
 */
public class Response extends HttpServletResponseWrapper {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	private HttpServletResponse response;

	public Response(HttpServletResponse response) {
		super(response);
		this.response = response;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	/**
	 * send an object as JSON
	 * @param object
	 * @throws IOException
	 */
	public <T> void sendJson(T object)throws JsonGenerationException, JsonMappingException, IOException {
		response.setContentType(ContentType.JSON.toString());
		mapper.writeValue(response.getWriter(), object);		
	}
	
}