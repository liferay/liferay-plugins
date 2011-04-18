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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.mobile.device.wurfl.util.PortletPropsKeys;
import com.liferay.portal.mobile.device.wurfl.util.PortletPropsValues;

import net.sourceforge.wurfl.core.DefaultDeviceProvider;
import net.sourceforge.wurfl.core.DefaultWURFLHolder;
import net.sourceforge.wurfl.core.DefaultWURFLManager;
import net.sourceforge.wurfl.core.DefaultWURFLService;
import net.sourceforge.wurfl.core.DeviceProvider;
import net.sourceforge.wurfl.core.WURFLHolder;
import net.sourceforge.wurfl.core.WURFLManager;
import net.sourceforge.wurfl.core.WURFLService;
import net.sourceforge.wurfl.core.WURFLUtils;
import net.sourceforge.wurfl.core.handlers.matchers.MatcherManager;
import net.sourceforge.wurfl.core.resource.DefaultWURFLModel;
import net.sourceforge.wurfl.core.resource.WURFLModel;
import net.sourceforge.wurfl.core.resource.WURFLResources;
import net.sourceforge.wurfl.core.resource.XMLResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

/**
 * @author Milen Dyankov
 * @author Michael C. Han
 */
public class WURFLHolderImpl implements WURFLHolder {

	public void initialize()
		throws Exception {

		List<InputStream> inputStreams = new ArrayList<InputStream>();

		try {
			XMLResource primaryDatabaseXMLResource = getWURFLDatabase(inputStreams);

			WURFLResources wurflPatches = getWURFLDatabasePatches(inputStreams);

			WURFLModel model = new DefaultWURFLModel(
				primaryDatabaseXMLResource, wurflPatches);

			MatcherManager matcherManager = new MatcherManager(model);

			DeviceProvider deviceProvider = new DefaultDeviceProvider(model);

			WURFLService service = new DefaultWURFLService(matcherManager,
					deviceProvider);

			WURFLManager manager = new DefaultWURFLManager(service);

			WURFLUtils utils = new WURFLUtils(model, deviceProvider);

			_wurflHolder = new DefaultWURFLHolder(manager, utils);
		}
		finally {
			for (InputStream in : inputStreams) {
				try {
					in.close();
				}
				catch (IOException e) {
					//nothing to do...
				}
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("WURFL initialised!");
		}
	}

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

	public synchronized void reload() throws Exception {
		initialize();
	}

	protected XMLResource getWURFLDatabase(List<InputStream> inputStreams)
		throws IOException {

		String primaryDatabaseFileName =
			PortletPropsValues.WURFL_DATABASE_PRIMARY;

		InputStream primaryDatabaseInputStream =
			getClass().getResourceAsStream(primaryDatabaseFileName);

		if (Validator.isNull(primaryDatabaseInputStream)) {
			throw new IllegalStateException(
				"WURFL database not initialized, please define: " +
				PortletPropsKeys.WURFL_DATABASE_PRIMARY);
		}

		if (primaryDatabaseFileName.endsWith(".zip") ||
			primaryDatabaseFileName.endsWith(".jar")) {

			ZipInputStream zipInputStream =
				new ZipInputStream(primaryDatabaseInputStream);

			primaryDatabaseInputStream = zipInputStream;

			//the zip file downloaded from the WURFL website is zipped
			//in a fashion that requires obtaining the next entry in the
			//zip file.
			zipInputStream.getNextEntry();
		}
		else if (primaryDatabaseFileName.endsWith(".gz")) {
			primaryDatabaseInputStream =
				new GZIPInputStream(primaryDatabaseInputStream);
		}

		XMLResource primaryDatabaseXMLResource = new XMLResource(
			primaryDatabaseInputStream);

		inputStreams.add(primaryDatabaseInputStream);

		return primaryDatabaseXMLResource;
	}

	protected WURFLResources getWURFLDatabasePatches(
			List<InputStream> inputStreams)
		throws FileNotFoundException {

		WURFLResources wurflPatches = new WURFLResources();

		String databasePatchesPath =
			PortletPropsValues.WURFL_DATABASE_PATCHES_PATH;

		File databasePatchesDir = new File(databasePatchesPath);

		if (databasePatchesDir.exists() && databasePatchesDir.isDirectory()) {
			File[] databasePatchFiles = databasePatchesDir.listFiles();

			for (File databasePatchFile : databasePatchFiles) {
				FileInputStream fis = new FileInputStream(databasePatchFile);

				inputStreams.add(fis);

				wurflPatches.add(new XMLResource(databasePatchFile));
			}
		}

		return wurflPatches;
	}

	private static Log _log = LogFactoryUtil.getLog(
		WURFLHolderImpl.class);

	private WURFLHolder _wurflHolder;

}