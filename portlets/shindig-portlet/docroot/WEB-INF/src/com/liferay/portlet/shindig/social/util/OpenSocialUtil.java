/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.shindig.social.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.shindig.social.opensocial.model.MediaItem;
import org.apache.shindig.social.opensocial.model.MediaItem.Type;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenSocialUtil {

	public static boolean isValidKey(String key) {
		if (key == null || key.length() == 0) {
			return false;
		}

		for (int i = 0; i < key.length(); ++i) {
			char c = key.charAt(i);

			if ((c >= 'a' && c <= 'z') ||
					(c >= 'A' && c <= 'Z') ||
					(c >= '0' && c <= '9') ||
					(c == '-') ||
					(c == '_') ||
					(c == '.')) {
				continue;
			}

			return false;
		}

		return true;
	}

	public static JSONArray getMediaItems(List<MediaItem> list) {
		if (list == null) {
			return null;
		}

		JSONArray mediaItems = new JSONArray();

		for (MediaItem mediaItem : list) {
			try {
		    	JSONObject mediaItemJson = new JSONObject();

		    	mediaItemJson.put("mimeType", mediaItem.getMimeType());
		    	mediaItemJson.put("type", mediaItem.getType());
		    	mediaItemJson.put("url", mediaItem.getUrl());

		    	mediaItems.put(mediaItemJson);
			}
			catch (JSONException je) {
			}
		}

		return mediaItems;
	}

	public static List<MediaItem> getMediaItems(JSONArray array) {
		if (array == null) {
			return null;
		}

		List<MediaItem> items = new ArrayList<MediaItem>();

		for (int i = 0; i < array.length(); i++) {
			try {
				JSONObject jsonItem = array.getJSONObject(i);
				MediaItem item = new MediaItem(
					jsonItem.getString("mimeType"),
					Type.valueOf(jsonItem.getString("type")),
					jsonItem.getString("url"));

				items.add(item);
			}
			catch (JSONException je) {
			}
		}

		return items;
	}

	public static JSONArray getTemplateParams(Map<String, String> map) {
		if (map == null) {
			return null;
		}

		JSONArray items = new JSONArray();

		for (Entry<String,String> entry : map.entrySet()) {
			try {
		    	JSONObject jsonEntry = new JSONObject();

		    	jsonEntry.put("key", entry.getKey());
		    	jsonEntry.put("value", entry.getValue());

		    	items.put(jsonEntry);
			}
			catch (JSONException je) {
			}
		}

		return items;
	}

	public static Map<String, String> getTemplateParams(JSONArray array) {
		if (array == null) {
			return null;
		}

		Map<String, String> map = new LinkedHashMap<String, String>();

		for (int i = 0; i < array.length(); i++) {
			try {
				JSONObject jsonItem = array.getJSONObject(i);

				map.put(
					jsonItem.getString("key"), jsonItem.getString("value"));
			}
			catch (JSONException je) {
			}
		}

		return map;
	}

}
