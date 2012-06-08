package com.connectthedots.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

//Ignore if you see a compilation error, it would work fine.
import com.google.appengine.repackaged.com.google.common.base.Splitter;
import com.google.appengine.repackaged.com.google.common.base.Splitter.MapSplitter;

/**
 * convenience wrapper for a string/string map. handy for dealing with urls.
 * 
 * @author cfr
 * 
 */
public class Dict {
	private Map<String, String> map;
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final MapSplitter querySplitter = Splitter.on('&').trimResults().withKeyValueSeparator("=");

	public Dict() {
		map = new HashMap<String, String>();
	}
		
	public Dict(Object ...kv) {
		this();
		if (kv.length % 2 == 1) {
			// TODO some sort of error, odd number of fields
		}
		for (int i=0; i+1<kv.length; i+=2) {
			map.put(kv[i].toString(), kv[i+1].toString());
		}
	}
	
	/**
	 * Create a new Dict, copying mappings from the provided map
	 * @param map
	 */
	@SuppressWarnings("rawtypes")
	public Dict(Map map) {
		this();
		for(Object key : map.keySet()) {
			this.map.put((String) key, (String) map.get(key));
		}
	}

	/**
	 * Create a new Dict parsed from an url query string
	 * @param query
	 */
	public Dict(String query) {
		this();
		 Map<String, String> params = querySplitter.split(query);
		 for (String key : params.keySet()) {
			 put(urlDecode(key), urlDecode(params.get(key)));
		 }
	}
	
	public String get(String key) {
		return map.get(key);
	}

	public void put(String key, String value) {
		map.put(key, value);
	}
	
	public Dict putAll(Dict other) {
		if (other != null) {
			map.putAll(other.map);
		}
		return this;
	}
	
	public boolean containsKey(String key) {
		return map.containsKey(key);
	}
	
	public String toJson() {
		try {
			return mapper.writeValueAsString(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * read keys from a blob of json.  input must be a simple json object
	 * @param json
	 * @throws IOException
	 */
	public Dict readJson(String json) throws IOException {
		Map<String, String> keys = mapper.readValue(json, new TypeReference<Map<String,String>>() { });
		map.putAll(keys);
		return this;
	}

	/**
	 * serialize this dict to a url encoded query string
	 * @return
	 */
	public String toUrlQuery() {
		StringBuilder sb = null;
		for (String key : keySet()) {
			if (sb == null) {
				sb = new StringBuilder();
			} else {
				sb.append('&');
			}
			sb.append(urlEncode(key)).append('=').append(urlEncode(get(key)));
		}
		return sb == null ? "" : sb.toString();
	}
	
	@Override
	public String toString() {
		return map.toString();
	}

	public Set<String> keySet() {
		return map.keySet();
	}

	public int size() {
		return map.size();
	}
	
	private String urlEncode(String string) {
		if (string != null) {
			try {
				string = URLEncoder.encode(string, "UTF-8");
			} catch (UnsupportedEncodingException e) {}
		}
		return string;
	}
	
	private String urlDecode(String string) {
		if (string != null) {
			try {
				string = URLDecoder.decode(string, "UTF-8");
			} catch (UnsupportedEncodingException e) {}
		}
		return string;
	}
}