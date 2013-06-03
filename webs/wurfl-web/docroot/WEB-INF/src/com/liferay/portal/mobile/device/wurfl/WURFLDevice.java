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
	public Dimensions getDisplaySize() {

		return getDimensions(
			WURFLConstants.DISPLAY_HEIGHT, WURFLConstants.DISPLAY_WIDTH);
	}

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

	/**
	 * @deprecated please use {@link #getResolution()} instead
	 */
	@Deprecated
	@Override
	public Dimensions getScreenSize() {

		return getResolution();
	}

	@Override
	public Dimensions getResolution() {

		return getDimensions(
			WURFLConstants.RESOLUTION_HEIGHT, WURFLConstants.RESOLUTION_WIDTH);
	}

	@Override
	public boolean hasQwertyKeyboard() {

		return getBoolean(WURFLConstants.HAS_QWERTY_KEYBOARD);
	}

	@Override
	public boolean isTablet() {

		return getBoolean(WURFLConstants.IS_TABLET);
	}

	@Override
	public boolean supportsDualOrientation() {

		return getBoolean(WURFLConstants.DUAL_ORIENTATION);
	}

	protected boolean getBoolean(String name) {

		Capability capability = _capabilities.get(name);

		if (capability == null) {
			return false;
		}

		return GetterUtil.getBoolean(capability.getValue(), false);
	}

	protected Dimensions getDimensions(
		String heightCapability, String widthCapability) {

		Capability h = _capabilities.get(heightCapability);
		Capability w = _capabilities.get(widthCapability);

		if ((h == null) || (w == null)) {
			return Dimensions.UNKNOWN;
		}

		int height = GetterUtil.getInteger(h.getValue());
		int width = GetterUtil.getInteger(w.getValue());

		if (supportsDualOrientation() && height < width) {
			return new Dimensions(width, height);
		}
		else {
			return new Dimensions(height, width);
		}
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
