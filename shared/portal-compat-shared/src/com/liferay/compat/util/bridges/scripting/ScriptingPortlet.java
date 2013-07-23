/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.compat.util.bridges.scripting;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.scripting.ScriptingException;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.permission.PortalPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.IOException;
import java.io.InputStream;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 * @author Alberto Montero
 */
public class ScriptingPortlet extends GenericPortlet {

	@Override
	public void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String fileName = getFileName(renderRequest);

		if (fileName != null) {
			include(fileName, renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	@Override
	public void doEdit(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (renderRequest.getPreferences() == null) {
			super.doEdit(renderRequest, renderResponse);
		}
		else {
			include(editFile, renderRequest, renderResponse);
		}
	}

	@Override
	public void doHelp(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		include(helpFile, renderRequest, renderResponse);
	}

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		include(viewFile, renderRequest, renderResponse);
	}

	@Override
	public void init() throws PortletException {
		super.init();

		filePath = getInitParameter("file-path");

		if (Validator.isNull(filePath)) {
			filePath = StringPool.SLASH;
		}
		else if (filePath.contains(StringPool.BACK_SLASH) ||
				 filePath.contains(StringPool.DOUBLE_SLASH) ||
				 filePath.contains(StringPool.PERIOD) ||
				 filePath.contains(StringPool.SPACE)) {

			throw new PortletException(
				"template-path " + filePath + " has invalid characters");
		}
		else if (!filePath.startsWith(StringPool.SLASH) ||
				 !filePath.endsWith(StringPool.SLASH)) {

			throw new PortletException(
				"template-path " + filePath +
					" must start and end with a /");
		}

		actionFile = getInitParameter("action-file");
		editFile = getInitParameter("edit-file");
		helpFile = getInitParameter("help-file");
		resourceFile = getInitParameter("resource-file");
		viewFile = getInitParameter("view-file");

		language = getInitParameter("scripting-language");
		globalFiles = StringUtil.split(getInitParameter("global-files"));
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		include(actionFile, actionRequest, actionResponse);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			doRender(renderRequest, renderResponse);
		}
		catch (IOException ioe) {
			throw ioe;
		}
		catch (PortletException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		include(resourceFile, resourceRequest, resourceResponse);
	}

	protected void checkPath(String path) throws PortletException {
		if (Validator.isNotNull(path) &&
			(!path.startsWith(filePath) ||
			 !isFilePath(path, false))) {

			throw new PortletException(
				"Path " + path + " is not accessible by this portlet");
		}
	}

	protected void declareBeans(
			InputStream is, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws IOException, ScriptingException {

		String script = new String(FileUtil.getBytes(is));

		declareBeans(script, portletRequest, portletResponse);
	}

	protected void declareBeans(
			String script, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws IOException, ScriptingException {

		script = getGlobalScript() + script;

		PortletConfig portletConfig = getPortletConfig();
		PortletContext portletContext = getPortletContext();

		Map<String, Object> portletObjects = ScriptingUtil.getPortletObjects(
			portletConfig, portletContext, portletRequest, portletResponse);

		ScriptingUtil.exec(null, portletObjects, language, script);
	}

	protected void doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		Object error = SessionErrors.get(renderRequest, _ERROR);

		if (error != null) {
			Exception e = (Exception)error;

			writeErrorMessage(renderRequest, renderResponse, e.getMessage());

			return;
		}

		super.render(renderRequest, renderResponse);

		error = SessionErrors.get(renderRequest, _ERROR);

		if (error != null) {
			Exception e = (Exception)error;

			writeErrorMessage(renderRequest, renderResponse, e.getMessage());
		}
	}

	protected String getFileName(RenderRequest renderRequest) {
		return renderRequest.getParameter("file");
	}

	protected String getGlobalScript() throws IOException {
		if (globalScript != null) {
			return globalScript;
		}

		if (globalFiles.length == 0) {
			globalScript = StringPool.BLANK;

			return globalScript;
		}

		StringBundler sb = new StringBundler();

		for (String globalFile : globalFiles) {
			InputStream is = getPortletContext().getResourceAsStream(
				globalFile);

			if (is == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("Global file " + globalFile + " does not exist");
				}
			}

			if (is != null) {
				String script = new String(FileUtil.getBytes(is));

				sb.append(script);
				sb.append(StringPool.NEW_LINE);
			}
		}

		globalScript = sb.toString();

		return globalScript;
	}

	protected void include(
			String path, PortletRequest portletRequest,
			PortletResponse portletResponse)
		throws IOException, PortletException {

		checkPath(path);

		InputStream is = getPortletContext().getResourceAsStream(path);

		if (is == null) {
			_log.error(path + " is not a valid " + language + " file");

			return;
		}

		try {
			declareBeans(is, portletRequest, portletResponse);
		}
		catch (ScriptingException se) {
			SessionErrors.add(portletRequest, _ERROR, se);
		}
		finally {
			is.close();
		}
	}

	protected boolean isFilePath(String path, boolean isParentDirAllowed) {
		if (Validator.isNull(path)) {
			return false;
		}

		if (path.contains(StringPool.NULL_CHAR)) {
			return false;
		}

		if (isParentDirAllowed) {
			return true;
		}

		if (path.equals(StringPool.DOUBLE_PERIOD)) {
			return false;
		}

		String normalizedPath = path.replace(
			CharPool.BACK_SLASH, CharPool.SLASH);

		if (normalizedPath.startsWith(
				StringPool.DOUBLE_PERIOD.concat(StringPool.SLASH))) {

			return false;
		}

		if (normalizedPath.endsWith(
				StringPool.SLASH.concat(StringPool.DOUBLE_PERIOD))) {

			return false;
		}

		if (normalizedPath.contains(
				StringPool.SLASH.concat(
					StringPool.DOUBLE_PERIOD).concat(StringPool.SLASH))) {

			return false;
		}

		return true;
	}

	protected void writeErrorMessage(
			RenderRequest renderRequest, RenderResponse renderResponse,
			String errorMessage)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		StringBundler sb = new StringBundler(6);

		sb.append("<div class=\"portlet-msg-error\">");
		sb.append(themeDisplay.translate("an-unexpected-error-occurred"));
		sb.append("</div>");

		if (PortalPermissionUtil.contains(
				permissionChecker, ActionKeys.CONFIGURATION)) {

			sb.append("<pre>");
			sb.append(HtmlUtil.escape(errorMessage));
			sb.append("</pre>");
		}

		renderResponse.setContentType(ContentTypes.TEXT_HTML);

		PortletResponseUtil.write(renderResponse, sb.toString());
	}

	protected String actionFile;
	protected String editFile;
	protected String filePath;
	protected String[] globalFiles;
	protected String globalScript;
	protected String helpFile;
	protected String language;
	protected String resourceFile;
	protected String viewFile;

	private static final String _ERROR = ScriptingPortlet.class + ".ERROR";

	private static Log _log = LogFactoryUtil.getLog(ScriptingPortlet.class);

}