/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation; version 3.0 of the License.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 */

package com.liferay.portal.kernel.mobile.device;

import com.liferay.portal.kernel.util.Validator;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public class DefaultDeviceCapabilityFilter implements DeviceCapabilityFilter {

	public boolean accept(String capabilityName) {
		if (_acceptableCapabilityNames.isEmpty() ||
			_acceptableCapabilityNames.contains(capabilityName)) {

			return true;
		}

		return false;
	}

	public boolean accept(String capabilityName, String capabilityValue) {
		if (Validator.isNull(capabilityValue)) {
			return false;
		}

		capabilityValue = capabilityValue.toLowerCase();

		if (capabilityValue.equals("false")) {
			return false;
		}

		if (!accept(capabilityName)) {
			return false;
		}

		return true;
	}

	public void setAcceptableCapabilityNames(
		Set<String> acceptableCapabilityNames) {

		_acceptableCapabilityNames.addAll(acceptableCapabilityNames);
	}

	private Set<String> _acceptableCapabilityNames = new HashSet<String>();

}