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

package com.liferay.saml2.session.impl;

import com.liferay.saml2.session.SPSession;
import com.liferay.saml2.session.SessionManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemorySessionManagerImpl implements SessionManager {

	public void registerSPSession(SPSession session) {
		_sessions.put(session.getNameID(), session);
		_sessionIdMap.put(session.getSessionId(), session.getNameID());
	}

	public SPSession findBySessionId(String id) {
		String nameID = _sessionIdMap.get(id);
		if (nameID != null) {
			return _sessions.get(nameID);
		}

		return null;
	}

	public SPSession findByNameID(String nameID) {
		return _sessions.get(nameID);
	}

	public void invalidate(SPSession session) {
		_sessions.remove(session.getNameID());
		_sessionIdMap.remove(session.getSessionId());
	}

	private Map<String, String> _sessionIdMap = new ConcurrentHashMap<String ,String>();
	private Map<String, SPSession> _sessions = new ConcurrentHashMap<String, SPSession>();

}
