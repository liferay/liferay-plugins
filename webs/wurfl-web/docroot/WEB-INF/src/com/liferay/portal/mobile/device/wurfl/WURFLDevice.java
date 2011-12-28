/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation; version 3.0 of the License.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.mobile.device.wurfl;

import com.liferay.portal.kernel.mobile.device.AbstractDevice;
import com.liferay.portal.kernel.mobile.device.Capability;
import com.liferay.portal.kernel.mobile.device.Dimensions;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;

/**
 * @author Milen Dyankov
 * @author Michael C. Han
 */
public class WURFLDevice extends AbstractDevice {

	public WURFLDevice(Map<String, String> capabilities) {
		for (Entry<String, String> entry : capabilities.entrySet()) {
			Capability capability = new Capability(
				entry.getKey(), entry.getValue());

			_capabilities.put(entry.getKey(), capability);
		}
	}

	public String getBrand() {
		Capability capability = _capabilities.get(WURFLConstants.BRAND_NAME);

		return capability.getValue();
	}

	public String getBrowser() {
		Capability capability = _capabilities.get(
			WURFLConstants.MOBILE_BROWSER);

		return capability.getValue();
	}

	public String getBrowserVersion() {
		Capability capability = _capabilities.get(
			WURFLConstants.MOBILE_BROWSER_VERSION);

		return capability.getValue();
	}

	public Map<String, Capability> getCapabilities() {
		return _capabilities;
	}

	public String getCapability(String name) {
		Capability capability = _capabilities.get(name);

		return capability.getValue();
	}

	public String getModel() {
		Capability capability = _capabilities.get(WURFLConstants.MODEL_NAME);

		return capability.getValue();
	}

	public String getOS() {
		Capability capability = _capabilities.get(WURFLConstants.DEVICE_OS);

		return capability.getValue();
	}

	public String getOSVersion() {
		Capability capability = _capabilities.get(
			WURFLConstants.DEVICE_OS_VERSION);

		return capability.getValue();
	}

	public String getPointingMethod() {
		Capability capability = _capabilities.get(
			WURFLConstants.POINTING_METHOD);

		return capability.getValue();
	}

	public Dimensions getScreenSize() {
		Capability heightCapability = _capabilities.get(
			WURFLConstants.RESOLUTION_HEIGHT);

		float height = GetterUtil.getFloat(heightCapability.getValue());

		Capability widthCapability = _capabilities.get(
			WURFLConstants.RESOLUTION_WIDTH);

		float width = GetterUtil.getFloat(widthCapability.getValue());

		return new Dimensions(height, width);
	}

	public boolean hasQwertyKeyboard() {
		Capability capability = _capabilities.get(
			WURFLConstants.HAS_QWERTY_KEYBOARD);

		return GetterUtil.getBoolean(capability.getValue(), false);
	}

	public boolean isTablet() {
		Capability capability = _capabilities.get(WURFLConstants.IS_TABLET);

		return GetterUtil.getBoolean(capability.getValue(), false);
	}

	private Map<String, Capability> _capabilities =
		new HashMap<String, Capability>();

}