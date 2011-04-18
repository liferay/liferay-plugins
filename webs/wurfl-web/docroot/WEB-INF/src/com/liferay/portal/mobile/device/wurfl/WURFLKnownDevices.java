/*
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation; version 2.0 of the License.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.mobile.device.wurfl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.Capability;
import com.liferay.portal.kernel.mobile.device.KnownDevices;
import com.liferay.portal.kernel.mobile.device.NoKnownDevices;
import com.liferay.portal.kernel.mobile.device.VersionableName;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.sourceforge.wurfl.core.Device;
import net.sourceforge.wurfl.core.WURFLHolder;
import net.sourceforge.wurfl.core.WURFLUtils;

/**
 * @author Milen Dyankov
 * @author Michael C. Han
 */
public class WURFLKnownDevices implements KnownDevices {

	public Set<VersionableName> getBrands() {
		if (!_initialized) {
			NoKnownDevices.getInstance().getBrands();
		}

		return _brands;
	}

	public Set<VersionableName> getBrowsers() {
		if (!_initialized) {
			NoKnownDevices.getInstance().getBrowsers();
		}

		return _browsers;
	}

	public Map<Capability, Set<String>> getDeviceIds() {
		return _devicesByCapability;
	}

	public Set<VersionableName> getOperatingSystems() {
		if (!_initialized) {
			NoKnownDevices.getInstance().getOperatingSystems();
		}

		return _operatingSystems;
	}

	public Set<String> getPointingMethods() {
		if (!_initialized) {
			NoKnownDevices.getInstance().getPointingMethods();
		}

		return _pointingMethods;
	}

	public synchronized void initialize() {
		loadWURFLDevices();
	}

	public synchronized void reload() {
		_initialized = false;

		loadWURFLDevices();
	}

	public void setWurflHolder(final WURFLHolder wurflHolder) {
		_wurflHolder = wurflHolder;
	}

	protected void loadWURFLDevices() {
		if (_initialized) {
			return;
		}

		WURFLUtils wurflUtils = _wurflHolder.getWURFLUtils();

		if (wurflUtils == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Can not load WURFL devices. " +
					"WURFL database was not loaded properly!");
			}

			return;
		}

		Map<String, VersionableName> oses =
			new HashMap<String, VersionableName>();
		Map<String, VersionableName> browsers =
			new HashMap<String, VersionableName>();
		Map<String, VersionableName> brands =
			new HashMap<String, VersionableName>();

		long startTime = System.currentTimeMillis();

		for (Object deviceIdObj : wurflUtils.getAllDevicesId()) {
			String deviceId = (String)deviceIdObj;
			_deviceIds.add(deviceId);

			Device device = wurflUtils.getDeviceById(deviceId);

			updateVersionableCapability(
				device, brands, WURFLConstants.BRAND_NAME,
				WURFLConstants.MODEL_NAME, WURFLConstants.MARKETING_NAME);

			updateVersionableCapability(
				device, oses, WURFLConstants.DEVICE_OS,
				WURFLConstants.DEVICE_OS_VERSION);

			updateVersionableCapability(
				device, browsers, WURFLConstants.MOBILE_BROWSER,
				WURFLConstants.MOBILE_BROWSER_VERSION);

			updateCapability(
				device, _pointingMethods, WURFLConstants.POINTING_METHOD);

			updateDevicesByCapabilities(device, WURFLConstants.DEVICE_OS);
		}

		_operatingSystems = new TreeSet<VersionableName>(oses.values());
		_browsers = new TreeSet<VersionableName>(browsers.values());
		_brands = new TreeSet<VersionableName>(brands.values());

		if (_log.isDebugEnabled()) {
			long endTime = System.currentTimeMillis();

			_log.debug(
				"Database load completed in " + (endTime - startTime) +
				" milliseconds.");
		}

		_initialized = true;
	}

	protected void updateVersionableCapability(
		Device device, Map<String, VersionableName> map,
		String nameAttribute, String versionAttribute) {

		updateVersionableCapability(
			device, map, nameAttribute, versionAttribute, null);
	}

	protected void updateVersionableCapability(
		Device device, Map<String, VersionableName> capabilitiesMap,
		String capabilityName, String capabilityVersionAttributeName,
		String capabilitySubVersionAttributeName) {

		String capabilityValue = device.getCapability(capabilityName);

		if (Validator.isNotNull(capabilityValue)) {
			VersionableName versionableCapability = capabilitiesMap.get(
				capabilityValue);

			if (versionableCapability == null) {
				versionableCapability = new VersionableName(capabilityValue);

				capabilitiesMap.put(capabilityValue, versionableCapability);
			}

			String capabilitySubVersionValue = null;
			if (Validator.isNotNull(capabilitySubVersionAttributeName)) {
				capabilitySubVersionValue = device.getCapability(
					capabilitySubVersionAttributeName);
			}

			String capabilityVersionValue = device.getCapability(
				capabilityVersionAttributeName);

			if (Validator.isNotNull(capabilityVersionValue)) {
				if (Validator.isNotNull(capabilitySubVersionValue)) {
					capabilityVersionValue = capabilityVersionValue.concat(
						" (").concat(capabilitySubVersionValue).concat(")");
				}
			}
			else {
				capabilityVersionValue = capabilitySubVersionValue;
			}

			versionableCapability.addVersion(capabilityVersionValue);
		}
	}

	protected void updateDevicesByCapabilities(
		Device device, String... capabilities) {

		if (Validator.isNull(capabilities)) {
			return;
		}

		for (String capabilityName : capabilities) {
			String capabilityValue = device.getCapability(capabilityName);

			if (Validator.isNotNull(capabilityValue)) {
				Capability capability = new Capability(
					capabilityName, capabilityValue);

				Set<String> deviceIds = _devicesByCapability.get(capability);

				if (deviceIds == null) {
					deviceIds = new TreeSet<String>();

					_devicesByCapability.put(capability, deviceIds);
				}

				deviceIds.add(device.getId());
			}
		}
	}

	protected void updateCapability(
		Device device, Set<String> capabilityValues, String attributeName) {

		String capabilityValue = device.getCapability(attributeName);

		if (Validator.isNotNull(capabilityValue)) {
			capabilityValues.add(capabilityValue);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		WURFLKnownDevices.class);

	private Set<VersionableName> _brands;
	private Set<VersionableName> _browsers;
	private Map<Capability, Set<String>> _devicesByCapability =
		new HashMap<Capability, Set<String>>();
	private Set<String> _deviceIds = new HashSet<String>();
	private boolean _initialized = false;
	private Set<VersionableName> _operatingSystems;
	private Set<String> _pointingMethods = new TreeSet<String>();
	private WURFLHolder _wurflHolder;

}