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

package com.liferay.rtl.hook.filter.dynamiccss;

import com.liferay.portal.kernel.cache.key.CacheKeyGenerator;
import com.liferay.portal.kernel.cache.key.CacheKeyGeneratorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BufferCacheServletResponse;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.rtl.hook.filter.IgnoreModuleRequestFilter;

import java.io.File;

import java.net.URL;
import java.net.URLConnection;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eduardo Lundgren
 * @author Raymond Augé
 * @author Eduardo Garcia
 * @see com.liferay.portal.servlet.filters.dynamiccss.DynamicCSSFilter
 */
public class DynamicCSSFilter extends IgnoreModuleRequestFilter {

	public static final boolean ENABLED = GetterUtil.getBoolean(
		PropsUtil.get(
			"com.liferay.portal.servlet.filters.dynamiccss.DynamicCSSFilter"));

	@Override
	public void init(FilterConfig filterConfig) {
		doInit(filterConfig, filterConfig.getServletContext());
	}

	protected void doInit(
		FilterConfig filterConfig, ServletContext servletContext) {

		super.init(filterConfig);

		_servletContext = servletContext;

		File tempDir = (File)servletContext.getAttribute(
			JavaConstants.JAVAX_SERVLET_CONTEXT_TEMPDIR);

		_tempDir = new File(tempDir, _TEMP_DIR);

		_tempDir.mkdirs();

		DynamicCSSUtil.init();
	}

	protected String getCacheFileName(HttpServletRequest request) {
		CacheKeyGenerator cacheKeyGenerator =
			CacheKeyGeneratorUtil.getCacheKeyGenerator(
				DynamicCSSFilter.class.getName());

		cacheKeyGenerator.append(HttpUtil.getProtocol(request.isSecure()));
		cacheKeyGenerator.append(StringPool.UNDERLINE);
		cacheKeyGenerator.append(request.getRequestURI());

		String queryString = request.getQueryString();

		if (queryString != null) {
			cacheKeyGenerator.append(sterilizeQueryString(queryString));
		}

		return String.valueOf(cacheKeyGenerator.finish());
	}

	protected Object getDynamicContent(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		String requestURI = request.getRequestURI();

		String requestPath = requestURI;

		String contextPath = request.getContextPath();

		if (!contextPath.equals(StringPool.SLASH)) {
			requestPath = requestPath.substring(contextPath.length());
		}

		URL resourceURL = _servletContext.getResource(requestPath);

		if (resourceURL == null) {
			return null;
		}

		URLConnection urlConnection = resourceURL.openConnection();

		String cacheCommonFileName = getCacheFileName(request);

		File cacheContentTypeFile = new File(
			_tempDir, cacheCommonFileName + "_E_CONTENT_TYPE");
		File cacheDataFile = new File(
			_tempDir, cacheCommonFileName + "_E_DATA");

		if (cacheDataFile.exists() &&
			(cacheDataFile.lastModified() >= urlConnection.getLastModified())) {

			if (cacheContentTypeFile.exists()) {
				String contentType = FileUtil.read(cacheContentTypeFile);

				response.setContentType(contentType);
			}

			return cacheDataFile;
		}

		String dynamicContent = null;

		String content = null;

		try {
			if (requestPath.endsWith(_CSS_EXTENSION)) {
				if (_log.isInfoEnabled()) {
					_log.info("Parsing SASS on CSS " + requestPath);
				}

				content = StringUtil.read(urlConnection.getInputStream());

				dynamicContent = DynamicCSSUtil.parseSass(
					_servletContext, request, requestPath, content);

				response.setContentType(ContentTypes.TEXT_CSS);

				FileUtil.write(cacheContentTypeFile, ContentTypes.TEXT_CSS);
			}
			else if (requestPath.endsWith(_JSP_EXTENSION)) {
				if (_log.isInfoEnabled()) {
					_log.info("Parsing SASS on JSP or servlet " + requestPath);
				}

				BufferCacheServletResponse bufferCacheServletResponse =
					new BufferCacheServletResponse(response);

				processFilter(
					DynamicCSSFilter.class, request, bufferCacheServletResponse,
					filterChain);

				bufferCacheServletResponse.finishResponse();

				content = bufferCacheServletResponse.getString();

				dynamicContent = DynamicCSSUtil.parseSass(
					_servletContext, request, requestPath, content);

				FileUtil.write(
					cacheContentTypeFile,
					bufferCacheServletResponse.getContentType());
			}
			else {
				return null;
			}
		}
		catch (Exception e) {
			_log.error("Unable to parse SASS on CSS " + requestPath, e);

			if (_log.isDebugEnabled()) {
				_log.debug(content);
			}

			response.setHeader(
				HttpHeaders.CACHE_CONTROL,
				HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);
		}

		if (dynamicContent != null) {
			FileUtil.write(cacheDataFile, dynamicContent);
		}
		else {
			dynamicContent = content;
		}

		return dynamicContent;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		Object parsedContent = getDynamicContent(
			request, response, filterChain);

		if (parsedContent == null) {
			processFilter(
				DynamicCSSFilter.class, request, response, filterChain);
		}
		else {
			if (parsedContent instanceof File) {
				ServletResponseUtil.write(response, (File)parsedContent);
			}
			else if (parsedContent instanceof String) {
				ServletResponseUtil.write(response, (String)parsedContent);
			}
		}
	}

	protected String sterilizeQueryString(String queryString) {
		return StringUtil.replace(
			queryString, new String[] {StringPool.SLASH, StringPool.BACK_SLASH},
			new String[] {StringPool.UNDERLINE, StringPool.UNDERLINE});
	}

	private static final String _CSS_EXTENSION = ".css";

	private static final String _JSP_EXTENSION = ".jsp";

	private static final String _TEMP_DIR = "css";

	private static Log _log = LogFactoryUtil.getLog(DynamicCSSFilter.class);

	private ServletContext _servletContext;
	private File _tempDir;

}