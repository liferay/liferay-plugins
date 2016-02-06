/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.modulesadmin.portlet;

import aQute.bnd.header.OSGiHeader;
import aQute.bnd.header.Parameters;
import aQute.bnd.version.Version;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.freemarker.FreeMarkerPortlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Iterator;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.startlevel.BundleStartLevel;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class ModulesAdminPortlet extends FreeMarkerPortlet {

	@Override
	public void init(PortletConfig portletConfig) throws PortletException {
		super.init(portletConfig);

		PortletContext portletContext = portletConfig.getPortletContext();

		_bundleContext = (BundleContext)portletContext.getAttribute(
			"osgi-bundlecontext");
	}

	public void installBundleFromLocalFile(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		String fileName = uploadPortletRequest.getFullFileName("bundleFile");

		File file = uploadPortletRequest.getFile("bundleFile");

		if ((file == null) || !file.exists()) {
			throw new IllegalArgumentException("file-does-not-exist");
		}

		InputStream inputStream = new BufferedInputStream(
			new FileInputStream(file));

		try {
			Bundle bundle = getBundle(_bundleContext, inputStream);

			if (bundle == null) {
				_bundleContext.installBundle(fileName, inputStream);
			}
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	public void installBundleFromRemoteLocation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		String location = ParamUtil.getString(uploadPortletRequest, "location");

		_bundleContext.installBundle(location);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute("bundleContext", _bundleContext);

		super.render(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String json = null;

		try {
			long bundleId = ParamUtil.getLong(resourceRequest, "bundleId");

			Bundle bundle = _bundleContext.getBundle(bundleId);

			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("setBundleStartLevel")) {
				int startLevel = ParamUtil.getInteger(
					resourceRequest, "startLevel");

				BundleStartLevel bundleStartLevel = bundle.adapt(
					BundleStartLevel.class);

				bundleStartLevel.setStartLevel(startLevel);
			}
			else if (resourceID.equals("startBundle")) {
				bundle.start();
			}
			else if (resourceID.equals("stopBundle")) {
				bundle.stop();
			}
			else if (resourceID.equals("uninstallBundle")) {
				bundle.uninstall();
			}
			else if (resourceID.equals("updateBundle")) {
				bundle.update();
			}

			int state = bundle.getState();

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			if (state == Bundle.ACTIVE) {
				jsonObject.put("state", "active");
			}
			else if (state == Bundle.INSTALLED) {
				jsonObject.put("state", "installed");
			}
			else if (state == Bundle.RESOLVED) {
				jsonObject.put("state", "resolved");
			}
			else if (state == Bundle.STARTING) {
				jsonObject.put("state", "starting");
			}
			else if (state == Bundle.STOPPING) {
				jsonObject.put("state", "stopping");
			}
			else if (state == Bundle.UNINSTALLED) {
				jsonObject.put("state", "uninstalled");
			}
			else {
				jsonObject.put("state", StringPool.BLANK);
			}

			json = jsonObject.toString();
		}
		catch (BundleException be) {
			json = JSONFactoryUtil.serializeThrowable(be);
		}

		writeJSON(resourceRequest, resourceResponse, json);
	}

	public void uninstallBundle(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Bundle bundle = getBundle(actionRequest);

		if (bundle != null) {
			bundle.uninstall();
		}
	}

	public void updateBundleFromLocalFile(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		File file = uploadPortletRequest.getFile("bundleFile");

		if ((file == null) || !file.exists()) {
			throw new IllegalArgumentException("file-does-not-exist");
		}

		InputStream inputStream = new BufferedInputStream(
			new FileInputStream(file));

		try {
			Bundle bundle = getBundle(_bundleContext, inputStream);

			if (bundle != null) {
				bundle.update(inputStream);
			}
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	public void updateBundleFromRemoteLocation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Bundle bundle = getBundle(actionRequest);

		if (bundle != null) {
			bundle.update();
		}
	}

	@Override
	protected void checkPermissions(PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			throw new PrincipalException();
		}
	}

	/**
	 * @see com.liferay.osgi.bootstrap.ModuleFrameworkImpl#getBundle(
	 *      BundleContext, InputStream)
	 */
	protected Bundle getBundle(
			BundleContext bundleContext, InputStream inputStream)
		throws PortalException {

		try {
			if (inputStream.markSupported()) {

				// 1 megabyte is more than enough for even the largest manifest
				// file

				inputStream.mark(1024 * 1000);
			}

			JarInputStream jarInputStream = new JarInputStream(inputStream);

			Manifest manifest = jarInputStream.getManifest();

			if (inputStream.markSupported()) {
				inputStream.reset();
			}

			Attributes attributes = manifest.getMainAttributes();

			String bundleSymbolicNameAttributeValue = attributes.getValue(
				Constants.BUNDLE_SYMBOLICNAME);

			Parameters parameters = OSGiHeader.parseHeader(
				bundleSymbolicNameAttributeValue);

			Set<String> bundleSymbolicNameSet = parameters.keySet();

			Iterator<String> bundleSymbolicNameIterator =
				bundleSymbolicNameSet.iterator();

			String bundleSymbolicName = bundleSymbolicNameIterator.next();

			String bundleVersionAttributeValue = attributes.getValue(
				Constants.BUNDLE_VERSION);

			Version bundleVersion = Version.parseVersion(
				bundleVersionAttributeValue);

			for (Bundle bundle : bundleContext.getBundles()) {
				Version curBundleVersion = Version.parseVersion(
					bundle.getVersion().toString());

				if (bundleSymbolicName.equals(bundle.getSymbolicName()) &&
					bundleVersion.equals(curBundleVersion)) {

					return bundle;
				}
			}

			return null;
		}
		catch (IOException ioe) {
			throw new PortalException(ioe);
		}
	}

	protected Bundle getBundle(PortletRequest portletRequest) throws Exception {
		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(portletRequest);

		long bundleId = ParamUtil.getLong(uploadPortletRequest, "bundleId");

		return _bundleContext.getBundle(bundleId);
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof BundleException ||
			cause instanceof IllegalArgumentException ||
			cause instanceof PrincipalException) {

			return true;
		}

		return false;
	}

	private BundleContext _bundleContext;

}