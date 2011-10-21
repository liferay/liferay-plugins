/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.Capability;
import com.liferay.portal.kernel.mobile.device.KnownDevices;
import com.liferay.portal.kernel.mobile.device.NoKnownDevices;
import com.liferay.portal.kernel.mobile.device.VersionableName;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import net.sourceforge.wurfl.core.Device;
import net.sourceforge.wurfl.core.WURFLHolder;
import net.sourceforge.wurfl.core.WURFLUtils;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Milen Dyankov
 * @author Michael C. Han
 */
public class WURFLKnownDevices implements KnownDevices {

	public Set<VersionableName> getBrands() {
		if (!_initialized) {
			NoKnownDevices noKnownDevices = NoKnownDevices.getInstance();

			return noKnownDevices.getBrands();
		}

		return _brands;
	}

	public Set<VersionableName> getBrowsers() {
		if (!_initialized) {
			NoKnownDevices noKnownDevices = NoKnownDevices.getInstance();

			return noKnownDevices.getBrowsers();
		}

		return _browsers;
	}

	public Map<Capability, Set<String>> getDeviceIds() {
		return _devicesIds;
	}

	public Set<VersionableName> getOperatingSystems() {
		if (!_initialized) {
			NoKnownDevices noKnownDevices = NoKnownDevices.getInstance();

			return noKnownDevices.getOperatingSystems();
		}

		return _operatingSystems;
	}

	public Set<String> getPointingMethods() {
		if (!_initialized) {
			NoKnownDevices noKnownDevices = NoKnownDevices.getInstance();

			return noKnownDevices.getPointingMethods();
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

	public void setWurflHolder(WURFLHolder wurflHolder) {
		_wurflHolder = wurflHolder;
	}

	protected void loadWURFLDevices() {
		if (_initialized) {
			return;
		}

		WURFLUtils wurflUtils = _wurflHolder.getWURFLUtils();

		if (wurflUtils == null) {
			_log.error("Unable to load WURFL devices");

			return;
		}

		StopWatch stopWatch = null;

		if (_log.isDebugEnabled()) {
			stopWatch = new StopWatch();

			stopWatch.start();

			_log.debug("Loading database");
		}

		Map<String, VersionableName> brands =
			new HashMap<String, VersionableName>();
		Map<String, VersionableName> browsers =
			new HashMap<String, VersionableName>();
		Map<String, VersionableName> operatingSystems =
			new HashMap<String, VersionableName>();

		for (Object deviceIdObject : wurflUtils.getAllDevicesId()) {
			String deviceId = (String)deviceIdObject;

			Device device = wurflUtils.getDeviceById(deviceId);

			updateVersionableCapability(
				device, brands, WURFLConstants.BRAND_NAME,
				WURFLConstants.MODEL_NAME, WURFLConstants.MARKETING_NAME);

			updateVersionableCapability(
				device, browsers, WURFLConstants.MOBILE_BROWSER,
				WURFLConstants.MOBILE_BROWSER_VERSION, null);

			updateVersionableCapability(
				device, operatingSystems, WURFLConstants.DEVICE_OS,
				WURFLConstants.DEVICE_OS_VERSION, null);

			updateCapability(
				device, _pointingMethods, WURFLConstants.POINTING_METHOD);

			updateDevicesIds(device, WURFLConstants.DEVICE_OS);
		}

		_brands = new TreeSet<VersionableName>(brands.values());
		_browsers = new TreeSet<VersionableName>(browsers.values());
		_operatingSystems = new TreeSet<VersionableName>(
			operatingSystems.values());

		if (_log.isDebugEnabled()) {
			_log.debug("Loaded database in " + stopWatch.getTime() + " ms");
		}

		_initialized = true;
	}

	protected void updateCapability(
		Device device, Set<String> capabilityValues, String capabilityName) {

		String capabilityValue = device.getCapability(capabilityName);

		if (Validator.isNotNull(capabilityValue)) {
			capabilityValues.add(capabilityValue);
		}
	}

	protected void updateDevicesIds(Device device, String... capabilityNames) {
		if ((capabilityNames == null) || (capabilityNames.length == 0)) {
			return;
		}

		for (String capabilityName : capabilityNames) {
			String capabilityValue = device.getCapability(capabilityName);

			if (Validator.isNull(capabilityValue)) {
				continue;
			}

			Capability capability = new Capability(
				capabilityName, capabilityValue);

			Set<String> deviceIds = _devicesIds.get(capability);

			if (deviceIds == null) {
				deviceIds = new TreeSet<String>();

				_devicesIds.put(capability, deviceIds);
			}

			deviceIds.add(device.getId());
		}
	}

	protected void updateVersionableCapability(
		Device device, Map<String, VersionableName> capabilities,
		String capabilityName, String capabilityVersionAttributeName,
		String capabilitySubversionAttributeName) {

		String capabilityValue = device.getCapability(capabilityName);

		if (Validator.isNull(capabilityValue)) {
			return;
		}

		VersionableName versionableCapability = capabilities.get(
			capabilityValue);

		if (versionableCapability == null) {
			versionableCapability = new VersionableName(capabilityValue);

			capabilities.put(capabilityValue, versionableCapability);
		}

		String capabilitySubversionValue = null;

		if (Validator.isNotNull(capabilitySubversionAttributeName)) {
			capabilitySubversionValue = device.getCapability(
				capabilitySubversionAttributeName);
		}

		String capabilityVersionValue = device.getCapability(
			capabilityVersionAttributeName);

		if (Validator.isNotNull(capabilityVersionValue)) {
			if (Validator.isNotNull(capabilitySubversionValue)) {
				capabilityVersionValue =
					capabilityVersionValue + " (" + capabilitySubversionValue +
						")";
			}
		}
		else {
			capabilityVersionValue = capabilitySubversionValue;
		}

		versionableCapability.addVersion(capabilityVersionValue);
	}

	private static Log _log = LogFactoryUtil.getLog(WURFLKnownDevices.class);

	private Set<VersionableName> _brands;
	private Set<VersionableName> _browsers;
	private Map<Capability, Set<String>> _devicesIds =
		new HashMap<Capability, Set<String>>();
	private boolean _initialized;
	private Set<VersionableName> _operatingSystems;
	private Set<String> _pointingMethods = new TreeSet<String>();
	private WURFLHolder _wurflHolder;

}