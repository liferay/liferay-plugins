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

package com.liferay.portlet.shindig.social.service;

import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.portlet.shindig.util.ShindigUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shindig.gadgets.GadgetToken;
import org.apache.shindig.social.ResponseError;
import org.apache.shindig.social.ResponseItem;
import org.apache.shindig.social.opensocial.DataService;

import org.json.JSONObject;

/**
 * <a href="BasicDataService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 *
 */
public class BasicDataService implements DataService {

	public ResponseItem<Map<String, Map<String, String>>> getPersonData(
			List<String> userIds, List<String> keys, GadgetToken token) {

		Map<String, Map<String, String>> data =
			new LinkedHashMap<String, Map<String, String>>();

        for (String userId : userIds) {
	        Map<String, String> personData = new HashMap<String, String>();

	        for (String key : keys) {
	            personData.put(key, ShindigUtil.getValue(key, userId));
	        }

            data.put(userId, personData);
		}

		return new ResponseItem<Map<String, Map<String, String>>>(data);
	}

	public ResponseItem updatePersonData(
			String userId, String key, String value, GadgetToken token) {

		if (!ShindigUtil.isValidKey(key)) {
			return new ResponseItem<Object>(ResponseError.BAD_REQUEST,
				"The person data key had invalid characters", new JSONObject());
		}

		try {
			ExpandoColumn expandoColumn = ShindigUtil.getColumn(key);

			ExpandoValueLocalServiceUtil.addValue(
				expandoColumn.getColumnId(), Long.parseLong(userId),
				Long.parseLong(userId), value);
		}
		catch (Exception e) {
		}

		return new ResponseItem<JSONObject>(new JSONObject());
	}

}
