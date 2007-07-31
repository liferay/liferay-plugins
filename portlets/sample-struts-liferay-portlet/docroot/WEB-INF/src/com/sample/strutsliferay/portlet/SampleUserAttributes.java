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

package com.sample.strutsliferay.portlet;

import com.liferay.portlet.CustomUserAttributes;
import com.liferay.portlet.UserAttributes;

import java.util.Map;

/**
 * <a href="SampleUserAttributes.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SampleUserAttributes extends CustomUserAttributes {

	public String getValue(String name, Map userInfo) {
		if (name == null) {
			return null;
		}

		String companyId = (String)userInfo.get(
			UserAttributes.LIFERAY_COMPANY_ID);
		String userId = (String)userInfo.get(UserAttributes.LIFERAY_USER_ID);

		if (name.equals("user.name.test")) {
			return "Test Name";
		}
		else {
			return super.getValue(name, userInfo);
		}
	}

	public Object clone() {
		return new SampleUserAttributes();
	}

}