package org.ua2.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class JSONClient {
	
	private JSONWrapper fetch(String uri, String data) throws JSONException {
		start();
		
		try {
			ClientResponse response = getResponse(uri, data);
			if(!response.ok) {
				JSONException exception = new JSONException("Could not get JSON");
				if(response.exception != null) {
					exception.initCause(response.exception);
				}
				throw exception;
			}
			return JSONWrapper.parse(response.data);
		} finally {
			stop();
		}
	}

	protected abstract ClientResponse getResponse(String uri, String data);

	protected abstract void start();

	protected abstract void stop();

	public JSONWrapper get(String uri) throws JSONException {
		return fetch(uri, null);
	}
	
	public JSONWrapper post(String uri, JSONArray array) throws JSONException {
		return fetch(uri, array.toString());
	}
	
	public JSONWrapper post(String uri, JSONObject object) throws JSONException {
		return fetch(uri, object.toString());
	}
	
	public JSONArray getFolders() throws JSONException {
		return get("/folders").getArray();
	}
	
	public JSONArray getMessages(String folder) throws JSONException {
		return getMessages(folder, false, false);
	}
	
	public JSONArray getMessages(String folder, boolean unreadOnly, boolean full) throws JSONException {
		String path = "/folder/" + folder;
		if(unreadOnly) {
			path += "/unread";
		}
		if(full) {
			path += "/full";
		}
		return get(path).getArray();
	}
	
	public JSONObject getMessage(int id) throws JSONException {
		return get("/message/" + id).getObject();
	}

	public void markMessage(int id, boolean read) throws JSONException {
		JSONArray array = new JSONArray();
		array.put(id);
		post("/message/read", array);
	}


	public void postMessage(int replyId, String folder, String to, String subject, String body) throws JSONException {
		JSONObject message = new JSONObject();
		
		if(to != null) {
			message.put("to", to);
		}
		
		if(subject != null) {
			message.put("subject", subject);
		}
		
		if(body != null) {
			message.put("body", body);
		}
		
		if(replyId > 0) {
			post("/message/" + replyId, message);
		} else {
			post("/folder/" + folder, message);
		}
	}
}
