package org.ua2.json;

import org.json.JSONObject;

public class JSONFolder extends JSONItem {
	public JSONFolder(JSONWrapper wrapper) {
		super(wrapper);
	}
	
	public JSONFolder(JSONObject data) {
		super(data);
	}

	public JSONFolder(String name) {
		super();
		
		setField("name", name);
	}

	public String getName() {
		return data.optString("folder");
	}
	
	public int getUnread() {
		return data.optInt("unread", 0);
	}

	public void setUnread(int unread) {
		setField("unread", unread);
	}
	
	public int getCount() {
		return data.optInt("count", 0);
	}

	public void setCount(int count) {
		setField("count", count);
	}

	public boolean getSubscribed() {
		return data.optBoolean("subscribed");
	}
}
