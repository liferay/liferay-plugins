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

package com.liferay.saml2.session;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.DateTime;
import org.opensaml.xml.XMLObject;

public class SPSession implements java.io.Serializable {

	public void addAttributes(Map<String, List<XMLObject>> attributes) {
		_attributes.putAll(attributes);
	}

	public List<XMLObject> getAttribute(String key) {
		return _attributes.get(key);
	}

	public Set<String> getAttributeKeys() {
		return _attributes.keySet();
	}

	public String getNameID() {
		return _nameID;
	}

	public String getNameIDFormat() {
		return _nameIDFormat;
	}

	public DateTime getNotOnOrAfter() {
		return _notOnOrAfter;
	}

	public void resetAttributes() {
		_attributes.clear();
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setNameID(String value) {
		_nameID = value;
	}

	public void setNameIDFormat(String value) {
		_nameIDFormat = value;
	}

	public void setNotOnOrAfter(DateTime value) {
		_notOnOrAfter = value;
	}

	public void setSessionId(String value) {
		_sessionId = value;
	}

	public boolean isValid() {
		return _notOnOrAfter.isAfterNow();
	}

	private Map<String, List<XMLObject>> _attributes =
		new ConcurrentHashMap<String, List<XMLObject>>();
	private String _nameID;
	private String _nameIDFormat;
	private DateTime _notOnOrAfter;
	private String _sessionId;
}
