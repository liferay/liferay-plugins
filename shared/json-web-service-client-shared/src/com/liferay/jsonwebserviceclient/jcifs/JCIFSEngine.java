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

package com.liferay.jsonwebserviceclient.jcifs;

import java.io.IOException;

import jcifs.ntlmssp.NtlmFlags;
import jcifs.ntlmssp.Type1Message;
import jcifs.ntlmssp.Type2Message;
import jcifs.ntlmssp.Type3Message;

import jcifs.util.Base64;

import org.apache.http.impl.auth.NTLMEngine;
import org.apache.http.impl.auth.NTLMEngineException;

/**
 * @author Mladen Cikara
 */
public class JCIFSEngine implements NTLMEngine {

	public JCIFSEngine(String domain, String workstation) {
		_domain = domain;
		_workstation = workstation;
	}

	@Override
	public String generateType1Msg(String domain, String workstation)
		throws NTLMEngineException {

		Type1Message type1Message = new Type1Message(
			_TYPE_1_FLAGS, domain, workstation);

		return Base64.encode(type1Message.toByteArray());
	}

	@Override
	public String generateType3Msg(
			String username, String password, String domain, String workstation,
			String challenge)
		throws NTLMEngineException {

		Type2Message type2Message = null;

		try {
			type2Message = new Type2Message(Base64.decode(challenge));
		}
		catch (final IOException ioe) {
			throw new NTLMEngineException("Invalid NTLM type 2 message", ioe);
		}

		int type2Flags = type2Message.getFlags();

		int type3Flags =
			type2Flags &
			(0xffffffff ^
			(NtlmFlags.NTLMSSP_TARGET_TYPE_DOMAIN |
			NtlmFlags.NTLMSSP_TARGET_TYPE_SERVER));

		if (domain == null) {
			domain = _domain;
		}

		if (workstation == null) {
			workstation = _workstation;
		}

		final Type3Message type3Message = new Type3Message(
			type2Message, password, domain, username, workstation, type3Flags);

		return Base64.encode(type3Message.toByteArray());
	}

	private static final int _TYPE_1_FLAGS =
		NtlmFlags.NTLMSSP_NEGOTIATE_56 |
			NtlmFlags.NTLMSSP_NEGOTIATE_128 |
			NtlmFlags.NTLMSSP_NEGOTIATE_NTLM2 |
			NtlmFlags.NTLMSSP_NEGOTIATE_ALWAYS_SIGN |
			NtlmFlags.NTLMSSP_REQUEST_TARGET;

	private final String _domain;
	private final String _workstation;

}