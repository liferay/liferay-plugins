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

package com.sample.servicebuilder.service.http;

import com.liferay.util.JSONUtil;

import com.sample.servicebuilder.model.Foo;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * <a href="FooJSONSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be overwritten
 * the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by <code>com.sample.servicebuilder.service.http.FooServiceJSON</code>
 * to translate objects.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.sample.servicebuilder.service.http.FooServiceJSON
 *
 */
public class FooJSONSerializer {
	public static JSONObject toJSONObject(Foo model) {
		JSONObject jsonObj = new JSONObject();
		JSONUtil.put(jsonObj, "fooId", model.getFooId());
		JSONUtil.put(jsonObj, "field1", model.getField1());
		JSONUtil.put(jsonObj, "field2", model.getField2());
		JSONUtil.put(jsonObj, "field3", model.getField3());
		JSONUtil.put(jsonObj, "field4", model.getField4());
		JSONUtil.put(jsonObj, "field5", model.getField5());

		return jsonObj;
	}

	public static JSONArray toJSONArray(List models) {
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < models.size(); i++) {
			Foo model = (Foo)models.get(i);
			jsonArray.put(toJSONObject(model));
		}

		return jsonArray;
	}
}