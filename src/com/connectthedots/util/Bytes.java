package com.connectthedots.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * utilities for dealing with bytes and streams.
*
 */
public class Bytes {

	public static String readFully(Reader reader) throws IOException {
		if (reader == null) return null;
		
		char[] arr = new char[8*1024]; // 8K at a time
		StringBuffer buf = new StringBuffer();
		int numRead;

		while ((numRead = reader.read(arr, 0, arr.length)) > 0) {
			buf.append(arr, 0, numRead);
		}
		return buf.toString();
	}
	
	public static byte[] readFully(InputStream is) throws IOException {
		if (is == null) return null;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024); 
		byte[] buf = new byte[1024];
		int read = 0;

		while ((read = is.read(buf)) > 0) {
			baos.write(buf, 0, read);
		}
		return baos.toByteArray();
	}
	
}