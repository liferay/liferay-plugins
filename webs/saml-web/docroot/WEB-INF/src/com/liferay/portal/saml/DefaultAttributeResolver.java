/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.saml;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.saml.opensaml.OpenSAMLUtil;
import com.liferay.portal.saml.util.PropsKeys;
import com.liferay.portal.service.http.UserJSONSerializer;

import java.util.ArrayList;
import java.util.List;

import org.opensaml.saml2.core.Attribute;
import org.opensaml.xml.XMLObject;

/**
 * @author Mika Koivisto
 */
public class DefaultAttributeResolver implements AttributeResolver {

	public void setIssuer(String issuer) {
		_issuer = issuer;
	}

	public List<Attribute> resolve(User user) {
		List<Attribute> attributes = new ArrayList<Attribute>();

		JSONObject jsonUser = UserJSONSerializer.toJSONObject(user);

		for (String name : getAttributeNames()) {
			String value = jsonUser.getString(name);

			Attribute attribute = OpenSAMLUtil.buildAttribute();

			XMLObject attributeValue = OpenSAMLUtil.buildAttributeValue(value);

			attribute.setName(name);
			attribute.getAttributeValues().add(attributeValue);

			attributes.add(attribute);
		}

		return attributes;
	}

	private String[] getAttributeNames() {
		String key = PropsKeys.SAML_SP_METADATA_ATTRIBUTES_RESOLVER_ATTRIBUTE_NAMES + "[" + _issuer + "]";
		return PropsUtil.getArray(key);
	}
	private String _issuer;
}
