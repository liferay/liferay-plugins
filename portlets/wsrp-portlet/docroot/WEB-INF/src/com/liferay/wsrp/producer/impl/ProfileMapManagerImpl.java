/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2009 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.wsrp.producer.impl;

import com.sun.portal.wsrp.producer.ProfileMapManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <a href="ProfileMapManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Rajesh Thiagarajan
 * @author Brian Wing Shun Chan
 *
 */
public class ProfileMapManagerImpl implements ProfileMapManager {

	public Map<String, String> getPortletMap() {
		return _portletMap;
	}

	public String getPortletMapping(String key) {
		return _portletMap.get(key);
	}

	public Map<String, String> getWSRPMap() {
		return _wsrpMap;
	}

	public String getWSRPMapping(String key) {
		return _wsrpMap.get(key);
	}

	private static Map<String, String> _portletMap =
		new HashMap<String, String>();
	private static Map<String, String> _wsrpMap = new HashMap<String, String>();

	static {
		_portletMap.put("user.bdate", "bdate");
		_portletMap.put("user.gender", "gender");
		_portletMap.put("user.name.prefix", "name/prefix");
		_portletMap.put("user.name.given", "name/given");
		_portletMap.put("user.name.family", "name/family");
		_portletMap.put("user.name.middle", "name/middle");
		_portletMap.put("user.name.suffix", "name/suffix");
		_portletMap.put("user.name.nickName", "name/nickName");
		_portletMap.put("user.employer", "employerInfo/employer");
		_portletMap.put("user.department", "employerInfo/department");
		_portletMap.put("user.jobtitle", "employerInfo/jobtitle");
		_portletMap.put("user.home-info.postal.name", "homeInfo/postal/name");
		_portletMap.put(
			"user.home-info.postal.street", "homeInfo/postal/street");
		_portletMap.put("user.home-info.postal.city", "homeInfo/postal/city");
		_portletMap.put(
			"user.home-info.postal.stateprov", "homeInfo/postal/stateprov");
		_portletMap.put(
			"user.home-info.postal.postalcode", "homeInfo/postal/postalcode");
		_portletMap.put(
			"user.home-info.postal.country", "homeInfo/postal/country");
		_portletMap.put(
			"user.home-info.postal.organization",
			"homeInfo/postal/organization");
		_portletMap.put(
			"user.home-info.telecom.telephone.intcode",
			"homeInfo/telecom/telephone/intcode");
		_portletMap.put(
			"user.home-info.telecom.telephone.loccode",
			"homeInfo/telecom/telephone/loccode");
		_portletMap.put(
			"user.home-info.telecom.telephone.number",
			"homeInfo/telecom/telephone/number");
		_portletMap.put(
			"user.home-info.telecom.telephone.ext",
			"homeInfo/telecom/telephone/ext");
		_portletMap.put(
			"user.home-info.telecom.telephone.comment",
			"homeInfo/telecom/telephone/comment");
		_portletMap.put(
			"user.home-info.telecom.fax.intcode",
			"homeInfo/telecom/fax/intcode");
		_portletMap.put(
			"user.home-info.telecom.fax.loccode",
			"homeInfo/telecom/fax/loccode");
		_portletMap.put(
			"user.home-info.telecom.fax.number", "homeInfo/telecom/fax/number");
		_portletMap.put(
			"user.home-info.telecom.fax.ext", "homeInfo/telecom/fax/ext");
		_portletMap.put(
			"user.home-info.telecom.fax.comment",
			"homeInfo/telecom/fax/comment");
		_portletMap.put(
			"user.home-info.telecom.mobile.intcode",
			"homeInfo/telecom/mobile/intcode");
		_portletMap.put(
			"user.home-info.telecom.mobile.loccode",
			"homeInfo/telecom/mobile/loccode");
		_portletMap.put(
			"user.home-info.telecom.mobile.number",
			"homeInfo/telecom/mobile/number");
		_portletMap.put(
			"user.home-info.telecom.mobile.ext", "homeInfo/telecom/mobile/ext");
		_portletMap.put(
			"user.home-info.telecom.mobile.comment",
			"homeInfo/telecom/mobile/comment");
		_portletMap.put(
			"user.home-info.telecom.pager.intcode",
			"homeInfo/telecom/pager/intcode");
		_portletMap.put(
			"user.home-info.telecom.pager.loccode",
			"homeInfo/telecom/pager/loccode");
		_portletMap.put(
			"user.home-info.telecom.pager.number",
			"homeInfo/telecom/pager/number");
		_portletMap.put(
			"user.home-info.telecom.pager.ext", "homeInfo/telecom/pager/ext");
		_portletMap.put(
			"user.home-info.telecom.pager.comment",
			"homeInfo/telecom/pager/comment");
		_portletMap.put("user.home-info.online.email", "homeInfo/online/email");
		_portletMap.put("user.home-info.online.uri", "homeInfo/online/uri");
		_portletMap.put(
			"user.business-info.postal.name", "businessInfo/postal/name");
		_portletMap.put(
			"user.business-info.postal.street", "businessInfo/postal/street");
		_portletMap.put(
			"user.business-info.postal.city", "businessInfo/postal/city");
		_portletMap.put(
			"user.business-info.postal.stateprov",
			"businessInfo/postal/stateprov");
		_portletMap.put(
			"user.business-info.postal.postalcode",
			"businessInfo/postal/postalcode");
		_portletMap.put(
			"user.business-info.postal.country", "businessInfo/postal/country");
		_portletMap.put(
			"user.business-info.postal.organization",
			"businessInfo/postal/organization");
		_portletMap.put(
			"user.business-info.telecom.telephone.intcode",
			"businessInfo/telecom/telephone/intcode");
		_portletMap.put(
			"user.business-info.telecom.telephone.loccode",
			"businessInfo/telecom/telephone/loccode");
		_portletMap.put(
			"user.business-info.telecom.telephone.number",
			"businessInfo/telecom/telephone/number");
		_portletMap.put(
			"user.business-info.telecom.telephone.ext",
			"businessInfo/telecom/telephone/ext");
		_portletMap.put(
			"user.business-info.telecom.telephone.comment",
			"businessInfo/telecom/telephone/comment");
		_portletMap.put(
			"user.business-info.telecom.fax.intcode",
			"businessInfo/telecom/fax/intcode");
		_portletMap.put(
			"user.business-info.telecom.fax.loccode",
			"businessInfo/telecom/fax/loccode");
		_portletMap.put(
			"user.business-info.telecom.fax.number",
			"businessInfo/telecom/fax/number");
		_portletMap.put(
			"user.business-info.telecom.fax.ext",
			"businessInfo/telecom/fax/ext");
		_portletMap.put(
			"user.business-info.telecom.fax.comment",
			"businessInfo/telecom/fax/comment");
		_portletMap.put(
			"user.business-info.telecom.mobile.intcode",
			"businessInfo/telecom/mobile/intcode");
		_portletMap.put(
			"user.business-info.telecom.mobile.loccode",
			"businessInfo/telecom/mobile/loccode");
		_portletMap.put(
			"user.business-info.telecom.mobile.number",
			"businessInfo/telecom/mobile/number");
		_portletMap.put(
			"user.business-info.telecom.mobile.ext",
			"businessInfo/telecom/mobile/ext");
		_portletMap.put(
			"user.business-info.telecom.mobile.comment",
			"businessInfo/telecom/mobile/comment");
		_portletMap.put(
			"user.business-info.telecom.pager.intcode",
			"businessInfo/telecom/pager/intcode");
		_portletMap.put(
			"user.business-info.telecom.pager.loccode",
			"businessInfo/telecom/pager/loccode");
		_portletMap.put(
			"user.business-info.telecom.pager.number",
			"businessInfo/telecom/pager/number");
		_portletMap.put(
			"user.business-info.telecom.pager.ext",
			"businessInfo/telecom/pager/ext");
		_portletMap.put(
			"user.business-info.telecom.pager.comment",
			"businessInfo/telecom/pager/comment");
		_portletMap.put(
			"user.business-info.online.email", "businessInfo/online/email");

		Set<String> keys = _portletMap.keySet();

		for (String key : keys) {
			_wsrpMap.put(_portletMap.get(key), key);
		}
	}

}