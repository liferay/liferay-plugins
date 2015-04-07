/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
import java.util.Map;
import java.util.Map.Entry;

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

	@Override
	public String getBrand() {
		return getValue(WURFLConstants.BRAND_NAME);
	}

	@Override
	public String getBrowser() {
		return getValue(WURFLConstants.MOBILE_BROWSER);
	}

	@Override
	public String getBrowserVersion() {
		return getValue(WURFLConstants.MOBILE_BROWSER_VERSION);
	}

	@Override
	public Map<String, Capability> getCapabilities() {
		return _capabilities;
	}

	@Override
	public String getCapability(String name) {
		Capability capability = _capabilities.get(name);

		if (capability == null) {
			return null;
		}

		return capability.getValue();
	}

	@Override
	public String getModel() {
		return getValue(WURFLConstants.MODEL_NAME);
	}

	@Override
	public String getOS() {
		return getValue(WURFLConstants.DEVICE_OS);
	}

	@Override
	public String getOSVersion() {
		return getValue(WURFLConstants.DEVICE_OS_VERSION);
	}

	@Override
	public String getPointingMethod() {
		return getValue(WURFLConstants.POINTING_METHOD);
	}

	@Override
	public Dimensions getScreenPhysicalSize() {
		return getDimensions(
			WURFLConstants.SCREEN_PHYSICAL_HEIGHT,
			WURFLConstants.SCREEN_PHYSICAL_WIDTH);
	}

	@Override
	public Dimensions getScreenResolution() {
		return getDimensions(
			WURFLConstants.RESOLUTION_HEIGHT, WURFLConstants.RESOLUTION_WIDTH);
	}

	@Override
	public boolean hasQwertyKeyboard() {
		Capability capability = _capabilities.get(
			WURFLConstants.HAS_QWERTY_KEYBOARD);

		if (capability == null) {
			return false;
		}

		return GetterUtil.getBoolean(capability.getValue(), false);
	}

	@Override
	public boolean isTablet() {
		Capability capability = _capabilities.get(WURFLConstants.IS_TABLET);

		if (capability == null) {
			return false;
		}

		return GetterUtil.getBoolean(capability.getValue(), false);
	}

	protected Dimensions getDimensions(
		String heightCapabilityName, String widthCapabilityName) {

		Capability heightCapability = _capabilities.get(heightCapabilityName);
		Capability widthCapability = _capabilities.get(widthCapabilityName);

		if ((heightCapability == null) || (widthCapability == null)) {
			return Dimensions.UNKNOWN;
		}

		boolean dualOrientation = false;

		Capability dualOrientationCapability = _capabilities.get(
			WURFLConstants.DUAL_ORIENTATION);

		if (dualOrientationCapability != null) {
			dualOrientation = GetterUtil.getBoolean(
				dualOrientationCapability.getValue());
		}

		float height = GetterUtil.getFloat(heightCapability.getValue());
		float width = GetterUtil.getFloat(widthCapability.getValue());

		if (dualOrientation && (height < width)) {
			return new Dimensions(width, height);
		}

		return new Dimensions(height, width);
	}

	protected String getValue(String name) {
		Capability capability = _capabilities.get(name);

		if (capability == null) {
			return VersionableName.UNKNOWN.getName();
		}

		return capability.getValue();
	}

	private Map<String, Capability> _capabilities = new HashMap<>();

}