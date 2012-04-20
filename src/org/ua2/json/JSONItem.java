package org.ua2.json;

import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class JSONItem {
	protected JSONObject data;
	
	public JSONItem(JSONWrapper wrapper) {
		this(wrapper.getObject());
	}
	
	public JSONItem(JSONObject data) {
		this.data = data;
	}
	
	protected JSONItem() {
		this(new JSONObject());
	}

	protected void setField(String name, String value) {
		try {
			data.put(name, value);
		} catch(JSONException e) {
			throw new RuntimeException("Cannot set " + name + " field", e);
		}
	}

	protected void setField(String name, int value) {
		try {
			data.put(name, value);
		} catch(JSONException e) {
			throw new RuntimeException("Cannot set " + name + " field", e);
		}
	}

	protected void setField(String name, boolean value) {
		try {
			data.put(name, value);
		} catch(JSONException e) {
			throw new RuntimeException("Cannot set " + name + " field", e);
		}
	}

	JSONObject getData() {
		return data;
	}
	
	/**
	 * Declared final to force all data to be kept in JSON
	 */
	public final String toString() {
		return data.toString();
	}
	
	public static String collectionToString(Collection<? extends JSONItem> items) {
		JSONArray array = new JSONArray();
		for(JSONItem item : items) {
			array.put(item.getData());
		}
		return array.toString();
	}
}
