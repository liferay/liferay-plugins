/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.mobile.device.wurfl;

import com.liferay.portal.kernel.mobile.device.AbstractDevice;
import com.liferay.portal.kernel.mobile.device.Capability;
import com.liferay.portal.kernel.mobile.device.DeviceCapabilityFilter;
import com.liferay.portal.kernel.mobile.device.Dimensions;
import com.liferay.portal.kernel.mobile.device.VersionableName;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;

/**
 * @author Milen Dyankov
 * @author Michael C. Han
 */
public class WURFLDevice extends AbstractDevice {

	public WURFLDevice(
		Map<String, String> capabilities,
		DeviceCapabilityFilter deviceCapabilityFilter) {

		for (Entry<String, String> entry : capabilities.entrySet()) {
			String name = entry.getKey();
			String value = entry.getValue();

			if (!deviceCapabilityFilter.accept(name, value)) {
				continue;
			}

			Capability capability = new Capability(name, value);

			_capabilities.put(name, capability);
		}
	}

	public String getBrand() {
		return getValue(WURFLConstants.BRAND_NAME);
	}

	public String getBrowser() {
		return getValue(WURFLConstants.MOBILE_BROWSER);
	}

	public String getBrowserVersion() {
		return getValue(WURFLConstants.MOBILE_BROWSER_VERSION);
	}

	public Map<String, Capability> getCapabilities() {
		return _capabilities;
	}

	public String getCapability(String name) {
		Capability capability = _capabilities.get(name);

		if (capability == null) {
			return null;
		}

		return capability.getValue();
	}

	public String getModel() {
		return getValue(WURFLConstants.MODEL_NAME);
	}

	public String getOS() {
		return getValue(WURFLConstants.DEVICE_OS);
	}

	public String getOSVersion() {
		return getValue(WURFLConstants.DEVICE_OS_VERSION);
	}

	public String getPointingMethod() {
		return getValue(WURFLConstants.POINTING_METHOD);
	}

	public Dimensions getScreenSize() {
		Capability heightCapability = _capabilities.get(
			WURFLConstants.RESOLUTION_HEIGHT);
		Capability widthCapability = _capabilities.get(
			WURFLConstants.RESOLUTION_WIDTH);

		if ((heightCapability == null) || (widthCapability == null)) {
			return Dimensions.UNKNOWN;
		}

		float height = GetterUtil.getFloat(heightCapability.getValue());
		float width = GetterUtil.getFloat(widthCapability.getValue());

		return new Dimensions(height, width);
	}

	public boolean hasQwertyKeyboard() {
		Capability capability = _capabilities.get(
			WURFLConstants.HAS_QWERTY_KEYBOARD);

		if (capability == null) {
			return false;
		}

		return GetterUtil.getBoolean(capability.getValue(), false);
	}

	public boolean isTablet() {
		Capability capability = _capabilities.get(WURFLConstants.IS_TABLET);

		if (capability == null) {
			return false;
		}

		return GetterUtil.getBoolean(capability.getValue(), false);
	}

	protected String getValue(String name) {
		Capability capability = _capabilities.get(name);

		if (capability == null) {
			return VersionableName.UNKNOWN.getName();
		}

		return capability.getValue();
	}

	private Map<String, Capability> _capabilities =
		new HashMap<String, Capability>();

}