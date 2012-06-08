package com.connectthedots.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.google.appengine.repackaged.com.google.common.base.Splitter;
import com.google.appengine.repackaged.com.google.common.collect.Iterables;
import com.connectthedots.util.Bytes;

/**
 * Http servlet request wrapper class to add convenient functionality.
 * 
 */
public class Request extends HttpServletRequestWrapper {

	private final static Splitter slash = Splitter.on('/');
	private HttpServletRequest request;

	public Request(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public String getFullUrl() {
		return getRequestURL().toString() + (getQueryString() == null ? "" : "?" + getQueryString());
	}
	
	public String getPostBody() throws IOException {
		return Bytes.readFully(request.getReader());
	}
	
	public String getParameter(String name, String dflt) {
		String value = request.getParameter(name);
		return value != null ? value : dflt;
	}
	
	public int getIntParameter(String name, int dflt) {
		String val = getParameter(name);
		if (val != null) {
			try {
				return Integer.parseInt(val);
			} catch (NumberFormatException e) {}
		}
		return dflt;
	}

	/**
	 * return the element of the path at the specified position
	 * @param position zero indexed
	 * @param dflt 
	 * @return integer at pos or dflt if pos not found or not an integer
	 */
	public int getIntPathElement(int position, int dflt) {
		String path = getPathInfo();
		if (path == null) return dflt;
		String[] pieces = Iterables.toArray(slash.split(path), String.class);
		try {
			if (position+1 < pieces.length) {  // account for leading slash
				return Integer.parseInt(pieces[position+1]);
			}
		} catch (NumberFormatException e) {}
		return dflt;
	}
	
	/**
	 * Attach an object to this request as an attribute, using the class name as the key
	 * @param o the object to attach
	 */
	public void setAttribute(Object o) {
		setAttribute(o.getClass().getSimpleName(), o);
	}
}