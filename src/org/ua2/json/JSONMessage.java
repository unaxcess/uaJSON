package org.ua2.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONMessage extends JSONItem {
	public JSONMessage(JSONWrapper wrapper) {
		super(wrapper);
	}
	
	public JSONMessage(JSONObject data) {
		super(data);
	}
	
	private List<Integer> getList(String name) {
		JSONArray array = data.optJSONArray(name);
		if(array == null) {
			return new ArrayList<Integer>();
		}
		
		List<Integer> list = new ArrayList<Integer>();
		for(int pos = 0; pos < array.length(); pos++) {
			try {
				list.add(array.getJSONObject(pos).getInt("id"));
			} catch(JSONException e) {
				throw new RuntimeException("Cannot get list " + name, e);
			}
		}
		
		return list;
	}

	public int getId() {
		return data.optInt("id");
	}
	
	public int getPosition() {
		return data.optInt("position");
	}

	public String getFolder() {
		return data.optString("folder");
	}
	
	public String getFrom() {
		return data.optString("from");
	}
	
	public String getTo() {
		return data.optString("to");
	}

	public String getSubject() {
		return data.optString("subject");
	}
	
	public String getBody() {
		return data.optString("body");
	}

	public void setRead(boolean read) {
		setField("read", read);
	}

	public boolean isRead() {
		return data.optBoolean("read");
	}
	
	public Date getDate() {
		return new Date(1000 * data.optLong("epoch"));
	}

	public int getThread() {
		return data.optInt("thread");
	}
	
	public List<Integer> getInReplyToHierarchy() {
		return getList("inReplyToHierarchy");
	}
	
	public List<Integer> getReplyToBy() {
		return getList("replyToBy");
	}
}
