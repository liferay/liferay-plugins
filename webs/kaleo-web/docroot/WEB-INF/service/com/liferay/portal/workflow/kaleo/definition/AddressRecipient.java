/*
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

package com.liferay.portal.workflow.kaleo.definition;

/**
 * <a href="AddressRecipient.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class AddressRecipient extends Recipient {

	public AddressRecipient(String address) {
		super(Type.ADDRESS);

		_address = address;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof RoleAssignment)) {
			return false;
		}

		AddressRecipient addressRecipient = (AddressRecipient)obj;

		if (_address.equals(addressRecipient._address)) {
			return true;
		}

		return true;
	}

	public String getAddress() {
		return _address;
	}

	public int hashCode() {
		return _address.hashCode();
	}

	private String _address;

}