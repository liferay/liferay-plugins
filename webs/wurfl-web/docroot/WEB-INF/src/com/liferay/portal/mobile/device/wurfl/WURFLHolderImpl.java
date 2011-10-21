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
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.mobile.device.wurfl.util.PortletPropsValues;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

import net.sourceforge.wurfl.core.DefaultDeviceProvider;
import net.sourceforge.wurfl.core.DefaultWURFLHolder;
import net.sourceforge.wurfl.core.DefaultWURFLManager;
import net.sourceforge.wurfl.core.DefaultWURFLService;
import net.sourceforge.wurfl.core.DeviceProvider;
import net.sourceforge.wurfl.core.WURFLHolder;
import net.sourceforge.wurfl.core.WURFLManager;
import net.sourceforge.wurfl.core.WURFLService;
import net.sourceforge.wurfl.core.WURFLUtils;
import net.sourceforge.wurfl.core.matchers.MatcherManager;
import net.sourceforge.wurfl.core.resource.DefaultWURFLModel;
import net.sourceforge.wurfl.core.resource.WURFLModel;
import net.sourceforge.wurfl.core.resource.WURFLResources;
import net.sourceforge.wurfl.core.resource.XMLResource;

/**
 * @author Milen Dyankov
 * @author Michael C. Han
 */
public class WURFLHolderImpl implements WURFLHolder {

	public WURFLManager getWURFLManager() {
		if (_wurflHolder == null) {
			return null;
		}

		return _wurflHolder.getWURFLManager();
	}

	public WURFLUtils getWURFLUtils() {
		if (_wurflHolder == null) {
			return null;
		}

		return _wurflHolder.getWURFLUtils();
	}

	public void initialize() throws Exception {
		List<InputStream> inputStreams = new ArrayList<InputStream>();

		try {
			XMLResource xmlResource = getWURFLDatabase(inputStreams);

			WURFLResources wurflResources = getWURFLDatabasePatches(
				inputStreams);

			WURFLModel wurflModel = new DefaultWURFLModel(
				xmlResource, wurflResources);

			MatcherManager matcherManager = new MatcherManager(wurflModel);

			DeviceProvider deviceProvider = new DefaultDeviceProvider(
				wurflModel);

			WURFLService wurflService = new DefaultWURFLService(
				matcherManager, deviceProvider);

			WURFLManager wurflManager = new DefaultWURFLManager(wurflService);

			WURFLUtils wurflUtils = new WURFLUtils(wurflModel, deviceProvider);

			_wurflHolder = new DefaultWURFLHolder(wurflManager, wurflUtils);
		}
		finally {
			for (InputStream inputStream : inputStreams) {
				try {
					inputStream.close();
				}
				catch (IOException ioe) {
				}
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Initialised");
		}
	}

	public synchronized void reload() throws Exception {
		initialize();
	}

	protected XMLResource getWURFLDatabase(List<InputStream> inputStreams)
		throws IOException {

		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			PortletPropsValues.WURFL_DATABASE_PRIMARY);

		if (inputStream == null) {
			throw new IllegalStateException(
				"Unable to find " + PortletPropsValues.WURFL_DATABASE_PRIMARY);
		}

		if (PortletPropsValues.WURFL_DATABASE_PRIMARY.endsWith(".gz")) {
			inputStream = new GZIPInputStream(inputStream);
		}
		else if (PortletPropsValues.WURFL_DATABASE_PRIMARY.endsWith(".jar") ||
			PortletPropsValues.WURFL_DATABASE_PRIMARY.endsWith(".zip")) {

			ZipInputStream zipInputStream = new ZipInputStream(inputStream);

			inputStream = zipInputStream;

			zipInputStream.getNextEntry();
		}

		XMLResource xmlResource = new XMLResource(inputStream);

		inputStreams.add(inputStream);

		return xmlResource;
	}

	protected WURFLResources getWURFLDatabasePatches(
			List<InputStream> inputStreams)
		throws FileNotFoundException {

		WURFLResources wurflResources = new WURFLResources();

		String[] fileNames = FileUtil.listFiles(
			PortletPropsValues.WURFL_DATABASE_PATCHES);

		for (String fileName : fileNames) {
			File file = new File(fileName);

			FileInputStream fileInputStream = new FileInputStream(file);

			inputStreams.add(fileInputStream);

			XMLResource xmlResource = new XMLResource(file);

			wurflResources.add(xmlResource);
		}

		return wurflResources;
	}

	private static Log _log = LogFactoryUtil.getLog(WURFLHolderImpl.class);

	private WURFLHolder _wurflHolder;

}