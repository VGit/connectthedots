package com.connectthedots.http;

public enum ContentType {
	HTML("text/html"),
	JAVASCRIPT("application/javascript"),
	JSON("application/json"),
	TEXT("text/plain"),
	X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded"),
	BINARY("application/octet-stream"),
	XML("application/xml"),
	ZIP("application/zip")
	;
	
	private String mimeType;
	
	private ContentType(String value) {
		this.mimeType = value;
	}
	
	@Override
	public String toString() {
		return mimeType;
	}
}