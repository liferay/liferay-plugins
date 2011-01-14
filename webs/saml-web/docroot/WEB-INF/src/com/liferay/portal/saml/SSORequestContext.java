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

import java.io.Serializable;

import org.opensaml.saml2.core.AuthnRequest;

/**
 * @author Mika Koivisto
 */
public class SSORequestContext implements Serializable {

	public static final int STAGE_INITIAL = 0;
	public static final int STAGE_AUTHENTICATED = 1;

	public SSORequestContext(
		String relyingPartyId, String relayState, AuthnRequest authnRequest) {

		_authnRequest = authnRequest;
		_relyingPartyId = relyingPartyId;
		_relayState = relayState;
	}

	public AuthnRequest getAuthnRequest() {
		return _authnRequest;
	}

	public String getRelayState() {
		return _relayState;
	}

	public String getRelyingPartyId() {
		return _relyingPartyId;
	}

	public int getStage() {
		return _stage;
	}

	public void setAuthnRequest(AuthnRequest authnRequest) {
		_authnRequest = authnRequest;
	}

	public void setRelayState(String relayState) {
		_relayState = relayState;
	}

	public void setRelyingPartyId(String relyingPartyId) {
		_relyingPartyId = relyingPartyId;
	}

	public void setStage(int stage) {
		_stage = stage;
	}

	private AuthnRequest _authnRequest;
	private String _relayState;
	private String _relyingPartyId;
	private int _stage;

}
