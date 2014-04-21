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

package com.liferay.rtl.hook.filter;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.servlet.ServletContextUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.rtl.hook.filter.dynamiccss.DynamicCSSUtil;
import com.liferay.rtl.util.AggregateUtil;
import com.liferay.rtl.util.MinifierUtil;
import com.liferay.rtl.util.PropsValues;

import java.io.IOException;
import java.io.Serializable;

import java.net.URL;
import java.net.URLConnection;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 * @author Edward Han
 * @author Zsigmond Rab
 * @author Raymond Augé
 * @author Eduardo Garcia
 * @see com.liferay.portal.servlet.ComboServlet
 */
public class ComboServletFilter extends BasePortalFilter {

	@Override
	public void init(FilterConfig filterConfig) {
		doInit(filterConfig, filterConfig.getServletContext());
	}

	protected void doInit(
		FilterConfig filterConfig, ServletContext servletContext) {

		super.init(filterConfig);

		_servletContext = ServletContextPool.get(
			PortalUtil.getServletContextName());
	}

	protected byte[] getResourceContent(
			HttpServletRequest request, HttpServletResponse response,
			URL resourceURL, String resourcePath, String minifierType)
		throws IOException {

		String fileContentKey = resourcePath.concat(StringPool.QUESTION).concat(
			minifierType);

		FileContentBag fileContentBag = _fileContentBagPortalCache.get(
			fileContentKey);

		if ((fileContentBag != null) && !PropsValues.COMBO_CHECK_TIMESTAMP) {
			return fileContentBag._fileContent;
		}

		URLConnection urlConnection = null;

		if (resourceURL != null) {
			urlConnection = resourceURL.openConnection();
		}

		if ((fileContentBag != null) && PropsValues.COMBO_CHECK_TIMESTAMP) {
			long elapsedTime =
				System.currentTimeMillis() - fileContentBag._lastModified;

			if ((urlConnection != null) &&
				(elapsedTime <= PropsValues.COMBO_CHECK_TIMESTAMP_INTERVAL) &&
				(urlConnection.getLastModified() ==
					fileContentBag._lastModified)) {

				return fileContentBag._fileContent;
			}

			_fileContentBagPortalCache.remove(fileContentKey);
		}

		if (resourceURL == null) {
			fileContentBag = _EMPTY_FILE_CONTENT_BAG;
		}
		else {
			String stringFileContent = StringUtil.read(
				urlConnection.getInputStream());

			if (!StringUtil.endsWith(resourcePath, _CSS_MINIFIED_SUFFIX) &&
				!StringUtil.endsWith(
					resourcePath, _JAVASCRIPT_MINIFIED_SUFFIX)) {

				if (minifierType.equals("css")) {
					try {
						stringFileContent = DynamicCSSUtil.parseSass(
							_servletContext, request, resourcePath,
							stringFileContent);
					}
					catch (Exception e) {
						_log.error(
							"Unable to parse SASS on CSS " +
								resourceURL.getPath(), e);

						if (_log.isDebugEnabled()) {
							_log.debug(stringFileContent);
						}

						response.setHeader(
							HttpHeaders.CACHE_CONTROL,
							HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);
					}

					String baseURL = StringPool.BLANK;

					int index = resourcePath.lastIndexOf(CharPool.SLASH);

					if (index != -1) {
						baseURL = resourcePath.substring(0, index + 1);
					}

					stringFileContent = AggregateUtil.updateRelativeURLs(
						stringFileContent, baseURL);

					stringFileContent = MinifierUtil.minifyCss(
						stringFileContent);
				}
				else if (minifierType.equals("js")) {
					stringFileContent = MinifierUtil.minifyJavaScript(
						stringFileContent);
				}
			}

			fileContentBag = new FileContentBag(
				stringFileContent.getBytes(StringPool.UTF8),
				urlConnection.getLastModified());
		}

		if (PropsValues.COMBO_CHECK_TIMESTAMP) {
			int timeToLive =
				(int)(PropsValues.COMBO_CHECK_TIMESTAMP_INTERVAL / Time.SECOND);

			_fileContentBagPortalCache.put(
				fileContentKey, fileContentBag, timeToLive);
		}

		return fileContentBag._fileContent;
	}

	protected URL getResourceURL(
			ServletContext servletContext, String rootPath, String path)
		throws Exception {

		URL url = servletContext.getResource(path);

		if (url == null) {
			return null;
		}

		String filePath = ServletContextUtil.getResourcePath(url);

		int pos = filePath.indexOf(
			rootPath.concat(StringPool.SLASH).concat(_JAVASCRIPT_DIR));

		if (pos == 0) {
			return url;
		}

		return null;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		Set<String> modulePathsSet = new LinkedHashSet<String>();

		Enumeration<String> enu = request.getParameterNames();

		if (ServerDetector.isWebSphere()) {
			Map<String, String[]> parameterMap = HttpUtil.getParameterMap(
				request.getQueryString());

			enu = Collections.enumeration(parameterMap.keySet());
		}

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			if (_protectedParameters.contains(name)) {
				continue;
			}

			modulePathsSet.add(name);
		}

		if (modulePathsSet.size() == 0) {
			response.sendError(
				HttpServletResponse.SC_BAD_REQUEST,
				"Modules paths set is empty");

			return;
		}

		String[] modulePaths = modulePathsSet.toArray(
			new String[modulePathsSet.size()]);

		String firstModulePath = modulePaths[0];

		String extension = FileUtil.getExtension(firstModulePath);

		String minifierType = ParamUtil.getString(request, "minifierType");

		if (Validator.isNull(minifierType)) {
			minifierType = "js";

			if (StringUtil.equalsIgnoreCase(extension, _CSS_EXTENSION)) {
				minifierType = "css";
			}
		}

		if (!minifierType.equals("css") && !minifierType.equals("js")) {
			minifierType = "js";
		}

		String modulePathsString = null;

		byte[][] bytesArray = null;

		if (!PropsValues.COMBO_CHECK_TIMESTAMP) {
			modulePathsString = Arrays.toString(modulePaths);

			if (minifierType.equals("css") &&
				DynamicCSSUtil.isRightToLeft(request)) {

				modulePathsString += ".rtl";
			}

			bytesArray = _bytesArrayPortalCache.get(modulePathsString);
		}

		if (bytesArray == null) {
			String rootPath = ServletContextUtil.getRootPath(_servletContext);

			bytesArray = new byte[modulePaths.length][];

			for (int i = 0; i < modulePaths.length; i++) {
				String modulePath = modulePaths[i];

				if (!validateModuleExtension(modulePath)) {
					response.setHeader(
						HttpHeaders.CACHE_CONTROL,
						HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);

					return;
				}

				byte[] bytes = new byte[0];

				if (Validator.isNotNull(modulePath)) {
					modulePath = StringUtil.replaceFirst(
						modulePath, PortalUtil.getPathContext(),
						StringPool.BLANK);

					URL url = getResourceURL(
						_servletContext, rootPath, modulePath);

					if (url == null) {
						response.setHeader(
							HttpHeaders.CACHE_CONTROL,
							HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);

						return;
					}

					bytes = getResourceContent(
						request, response, url, modulePath, minifierType);
				}

				bytesArray[i] = bytes;
			}

			if ((modulePathsString != null) &&
				!PropsValues.COMBO_CHECK_TIMESTAMP) {

				_bytesArrayPortalCache.put(modulePathsString, bytesArray);
			}
		}

		String contentType = ContentTypes.TEXT_JAVASCRIPT;

		if (StringUtil.equalsIgnoreCase(extension, _CSS_EXTENSION)) {
			contentType = ContentTypes.TEXT_CSS;
		}

		response.setContentType(contentType);

		ServletResponseUtil.write(response, bytesArray);
	}

	protected boolean validateModuleExtension(String moduleName)
		throws Exception {

		boolean validModuleExtension = false;

		String[] fileExtensions = PrefsPropsUtil.getStringArray(
			PropsKeys.COMBO_ALLOWED_FILE_EXTENSIONS, StringPool.COMMA);

		for (String fileExtension : fileExtensions) {
			if (StringPool.STAR.equals(fileExtension) ||
				StringUtil.endsWith(moduleName, fileExtension)) {

				validModuleExtension = true;

				break;
			}
		}

		return validModuleExtension;
	}

	private static final String _CSS_EXTENSION = "css";

	private static final String _CSS_MINIFIED_SUFFIX = "-min.css";

	private static final FileContentBag _EMPTY_FILE_CONTENT_BAG =
		new FileContentBag(new byte[0], 0);

	private static final String _JAVASCRIPT_DIR = "html/js";

	private static final String _JAVASCRIPT_MINIFIED_SUFFIX = "-min.js";

	private static Log _log = LogFactoryUtil.getLog(ComboServletFilter.class);

	private PortalCache<String, byte[][]> _bytesArrayPortalCache =
		SingleVMPoolUtil.getCache("com.liferay.portal.servlet.ComboServlet");
	private PortalCache<String, FileContentBag> _fileContentBagPortalCache =
		SingleVMPoolUtil.getCache(FileContentBag.class.getName());
	private Set<String> _protectedParameters = SetUtil.fromArray(
		new String[]{"b", "browserId", "minifierType", "languageId", "t"});
	private ServletContext _servletContext;

	private static class FileContentBag implements Serializable {

		public FileContentBag(byte[] fileContent, long lastModifiedTime) {
			_fileContent = fileContent;
			_lastModified = lastModifiedTime;
		}

		private byte[] _fileContent;
		private long _lastModified;

	}

}