/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.Validator;

/**
 * @author Michael C. Han
 */
public class AddressRecipient extends Recipient {

	public AddressRecipient(String address) {
		super(RecipientType.ADDRESS);

		_address = address;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof AddressRecipient)) {
			return false;
		}

		AddressRecipient addressRecipient = (AddressRecipient)obj;

		if (Validator.equals(_address, addressRecipient._address)) {
			return true;
		}

		return true;
	}

	public String getAddress() {
		return _address;
	}

	@Override
	public int hashCode() {
		return _address.hashCode();
	}

	private String _address;

}