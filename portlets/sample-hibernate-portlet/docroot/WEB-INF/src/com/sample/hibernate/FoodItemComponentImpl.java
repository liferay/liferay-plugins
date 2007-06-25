/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.sample.hibernate;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.ParamUtil;
import com.liferay.util.Validator;
import com.liferay.util.xml.BeanToXMLUtil;

import com.sample.hibernate.model.FoodItem;
import com.sample.hibernate.util.FoodItemUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * <a href="FoodItemComponentImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class FoodItemComponentImpl {

	public String process(HttpServletRequest req) {
		String result = null;

		try {
			String cmd = ParamUtil.getString(req, "cmd");

			if (cmd.equals("getFoodItemXml")) {
				long foodItemId = ParamUtil.getLong(req, "foodItemId", 0);

				result = getFoodItemXml(foodItemId);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (Validator.isNull(result)) {
			result = "<result />";
		}

		return result;
	}

	public String getFoodItemXml(long foodItemId) throws Exception {
		List foodItems = null;

		if (foodItemId > 0) {
			FoodItem foodItem = FoodItemUtil.getFoodItem(foodItemId);

			if (foodItem != null) {
				foodItems = new ArrayList();

				foodItems.add(foodItem);
			}
		}
		else {
			foodItems = FoodItemUtil.getFoodItems();
		}

		return getXml(foodItems);
	}

	public String getXml(List list) {
		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("result");

		Iterator itr = list.iterator();

		while (itr.hasNext()) {
			Object obj = itr.next();

			BeanToXMLUtil.addBean(obj, root);
		}

		return doc.asXML();
	}

	private static Log _log =
		LogFactoryUtil.getLog(FoodItemComponentImpl.class);

}