/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.mentions.portlet;

import com.liferay.mentions.util.PortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.test.GroupTestUtil;
import com.liferay.portal.util.test.TestPropsValues;
import com.liferay.portal.util.test.UserTestUtil;

import javax.servlet.http.HttpServletRequest;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Sergio González
 */
@RunWith(Arquillian.class)
public class MentionsPortletTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_user1 = UserTestUtil.addUser("sergio", _group.getGroupId());
		_user2 = UserTestUtil.addUser("ana", _group.getGroupId());
	}

	@Test
	public void testInexistentUser() throws Exception {
		MentionsPortlet mentionsPortlet = new MentionsPortlet();

		HttpServletRequest request = new MockHttpServletRequest();

		ThemeDisplay themeDisplay = new ThemeDisplay();

		themeDisplay.setTilesTitle(PortletKeys.MENTIONS);

		Company company = CompanyLocalServiceUtil.getCompany(
			_group.getCompanyId());

		themeDisplay.setCompany(company);
		themeDisplay.setScopeGroupId(TestPropsValues.getGroupId());

		request.setAttribute(WebKeys.THEME_DISPLAY, themeDisplay);

		JSONArray jsonArray = mentionsPortlet.getJSONArray(request);
	}

	private Group _group;
	private User _user1;
	private User _user2;

}