package org.ua2.json;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

public class JSONUtil {
	public static Map<Thread, IClient> clients = new HashMap<Thread, IClient>();
	
	public static void setClientForThread(IClient client) {
		clients.put(Thread.currentThread(), client);
	}
	
	private static JSONWrapper fetch(String uri, JSONWrapper data) throws JSONException {
		String dataStr = null;
		if(data != null) {
			dataStr = data.toString();
		}
		
		ClientResponse response = clients.get(Thread.currentThread()).getResponse(uri, dataStr);
		if(!response.ok) {
			JSONException exception = new JSONException("Could not get JSON");
			if(response.exception != null) {
				exception.initCause(exception);
			}
			throw exception;
		}
		return JSONWrapper.parse(response.data);
	}
	
	public static JSONWrapper get(String uri) throws JSONException {
		return fetch(uri, null);
	}
	
	public static JSONWrapper post(String uri, JSONWrapper wrapper) throws JSONException {
		return fetch(uri, wrapper);
	}
	
	public static JSONArray getFolders() throws JSONException {
		return get("/folders").getArray();
	}
	
	public static JSONArray getFolder(String folder) throws JSONException {
		return get("/folder/" + folder).getArray();
	}
}
