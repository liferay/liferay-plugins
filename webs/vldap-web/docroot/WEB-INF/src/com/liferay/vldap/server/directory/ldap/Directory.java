/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.vldap.server.directory.ldap;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.UserScreenNameComparator;
import com.liferay.vldap.util.LdapUtil;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.directory.shared.ldap.model.entry.DefaultEntry;
import org.apache.directory.shared.ldap.model.entry.Entry;
import org.apache.directory.shared.ldap.model.name.Dn;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public abstract class Directory {

	public boolean hasAttribute(String attributeId) {
		for (Attribute attribute : getAttributes()) {
			if (attributeId.equalsIgnoreCase(attribute.getAttributeId())) {
				return true;
			}
		}

		return false;
	}

	public boolean hasAttribute(String attributeId, String value) {
		for (Attribute attribute : getAttributes()) {
			if (attributeId.equalsIgnoreCase(attribute.getAttributeId()) &&
				value.equalsIgnoreCase(attribute.getValue())) {

				return true;
			}
		}

		return false;
	}

	public Entry toEntry(List<String> searchRequestAttributes)
		throws Exception {

		Entry entry = new DefaultEntry();

		entry.setDn(getDn());

		boolean wildcardAttributes = searchRequestAttributes.contains(
			StringPool.STAR);

		for (Attribute attribute : getAttributes()) {
			if (!wildcardAttributes &&
				!containsIgnoreCase(
					searchRequestAttributes, attribute.getAttributeId())) {

				continue;
			}

			entry.add(attribute.getAttributeId(), attribute.getValue());
		}

		if (containsIgnoreCase(searchRequestAttributes, "hassubordinates")) {
			entry.add("hassubordinates", "true");
		}

		return entry;
	}

	protected void addAttribute(String attributeId, String value) {
		Attribute attribute = new Attribute(attributeId, value);

		_attributes.add(attribute);
	}

	protected void addMemberAttributes(
			String top, Company company, LinkedHashMap<String, Object> params)
		throws Exception {

		List<User> users = UserLocalServiceUtil.search(
			company.getCompanyId(), null, null, null, null, null,
			WorkflowConstants.STATUS_APPROVED, params, true, 0,
			PortletPropsValues.SEARCH_MAX_SIZE,
			new UserScreenNameComparator());

		for (User user : users) {
			UserDirectory userDirectory = new UserDirectory(
				top, company, user);

			addAttribute("member", userDirectory.getName());
		}
	}

	protected boolean containsIgnoreCase(List<String> list, String value) {
		if (list == null) {
			return false;
		}

		for (String current : list) {
			if (current.equalsIgnoreCase(value)) {
				return true;
			}
		}

		return false;
	}

	protected List<Attribute> getAttributes() {
		return _attributes;
	}

	protected Dn getDn() throws Exception {
		String name = getName();

		try {
			return new Dn(name);
		}
		catch (Exception e) {
			_log.error("Invalid name " + name);

			throw e;
		}
	}

	protected String getName() {
		return _name;
	}

	protected void setName(
		String top, Company company, String... organizationUnits) {

		_name = LdapUtil.buildName(top, company, organizationUnits);
	}

	private static Log _log = LogFactoryUtil.getLog(Directory.class);

	private List<Attribute> _attributes = new ArrayList<Attribute>();
	private String _name;

}