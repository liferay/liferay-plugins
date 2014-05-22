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

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncPrintWriter;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContextPathUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnsyncPrintWriterPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.ModelHintsConstants;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.rtl.scripting.ruby.RubyExecutor;
import com.liferay.rtl.util.PropsValues;

import java.io.File;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 * @author Sergio Sánchez
 * @author Eduardo Garcia
 * @see com.liferay.portal.servlet.filters.dynamiccss.DynamicCSSUtil
 */
public class DynamicCSSUtil {

	public static void init() {
		try {
			_rubyScript = StringUtil.read(
				PortalClassLoaderUtil.getClassLoader(),
				"com/liferay/portal/servlet/filters/dynamiccss/main.rb");

			RTLCSSUtil.init();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static boolean isRightToLeft(HttpServletRequest request) {
		String languageId = LanguageUtil.getLanguageId(request);

		Locale locale = LocaleUtil.fromLanguageId(languageId);

		String langDir = LanguageUtil.get(locale, "lang.dir");

		return langDir.equals("rtl");
	}

	public static String parseSass(
			ServletContext servletContext, HttpServletRequest request,
			String resourcePath, String content)
		throws Exception {

		if (!DynamicCSSFilter.ENABLED) {
			return content;
		}

		// Request will only be null when called by StripFilterTest

		if (request == null) {
			return content;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Theme theme = null;

		if (themeDisplay == null) {
			theme = _getTheme(request);

			if (theme == null) {
				String currentURL = PortalUtil.getCurrentURL(request);

				if (_log.isWarnEnabled()) {
					_log.warn("No theme found for " + currentURL);
				}

				if (isRightToLeft(request) &&
					!RTLCSSUtil.isExcludedPath(resourcePath)) {

					content = RTLCSSUtil.getRtlCss(content);
				}

				return content;
			}
		}

		String parsedContent = null;

		boolean themeCssFastLoad = _isThemeCssFastLoad(request, themeDisplay);

		URLConnection resourceURLConnection = null;

		URL resourceURL = servletContext.getResource(resourcePath);

		if (resourceURL != null) {
			resourceURLConnection = resourceURL.openConnection();
		}

		URLConnection cacheResourceURLConnection = null;

		URL cacheResourceURL = _getCacheResourceURL(
			servletContext, request, resourcePath);

		if (cacheResourceURL != null) {
			cacheResourceURLConnection = cacheResourceURL.openConnection();
		}

		if (themeCssFastLoad && (cacheResourceURLConnection != null) &&
			(resourceURLConnection != null) &&
			_isCacheResourceUpToDate(
				cacheResourceURLConnection, resourceURLConnection)) {

			parsedContent = StringUtil.read(
				cacheResourceURLConnection.getInputStream());
		}
		else {
			content = _parseStaticTokens(content);

			String queryString = request.getQueryString();

			if (!themeCssFastLoad && Validator.isNotNull(queryString)) {
				content = propagateQueryString(content, queryString);
			}

			parsedContent = _parseSass(
				servletContext, request, themeDisplay, theme, resourcePath,
				content);

			if (isRightToLeft(request) &&
				!RTLCSSUtil.isExcludedPath(resourcePath)) {

				// Append custom CSS for RTL

				URL rtlCustomResourceURL = _getRtlCustomResourceURL(
					servletContext, resourcePath);

				if (rtlCustomResourceURL != null) {
					URLConnection rtlCustomResourceURLConnection =
						rtlCustomResourceURL.openConnection();

					String rtlCustomContent = StringUtil.read(
						rtlCustomResourceURLConnection.getInputStream());

					String parsedRtlCustomContent = _parseSass(
						servletContext, request, themeDisplay, theme,
						resourcePath, rtlCustomContent);

					parsedContent += parsedRtlCustomContent;
				}
			}
		}

		if (Validator.isNull(parsedContent)) {
			return content;
		}

		String portalContextPath = PortalUtil.getPathContext();

		String baseURL = portalContextPath;

		String contextPath = ContextPathUtil.getContextPath(servletContext);

		if (!contextPath.equals(portalContextPath)) {
			baseURL = StringPool.SLASH.concat(
				GetterUtil.getString(servletContext.getServletContextName()));
		}

		if (baseURL.endsWith(StringPool.SLASH)) {
			baseURL = baseURL.substring(0, baseURL.length() - 1);
		}

		parsedContent = StringUtil.replace(
			parsedContent,
			new String[] {
				"@base_url@", "@portal_ctx@", "@theme_image_path@"
			},
			new String[] {
				baseURL, portalContextPath,
				_getThemeImagesPath(request, themeDisplay, theme)
			});

		return parsedContent;
	}

	private static String _getCacheFileName(String fileName, String suffix) {
		String cacheFileName = StringUtil.replace(
			fileName, StringPool.BACK_SLASH, StringPool.SLASH);

		int x = cacheFileName.lastIndexOf(StringPool.SLASH);
		int y = cacheFileName.lastIndexOf(StringPool.PERIOD);

		return cacheFileName.substring(0, x + 1) + ".sass-cache/" +
			cacheFileName.substring(x + 1, y) + suffix +
			cacheFileName.substring(y);
	}

	private static URL _getCacheResourceURL(
			ServletContext servletContext, HttpServletRequest request,
			String resourcePath)
		throws Exception {

		String suffix = StringPool.BLANK;

		if (isRightToLeft(request)) {
			suffix = "_rtl";
		}

		return servletContext.getResource(
			_getCacheFileName(resourcePath, suffix));
	}

	private static String _getCssThemePath(
			ServletContext servletContext, HttpServletRequest request,
			ThemeDisplay themeDisplay, Theme theme)
		throws Exception {

		if (themeDisplay != null) {
			return themeDisplay.getPathThemeCss();
		}

		if (PortalUtil.isCDNDynamicResourcesEnabled(request)) {
			String cdnHost = PortalUtil.getCDNHost(request);

			if (Validator.isNotNull(cdnHost)) {
				return cdnHost.concat(theme.getStaticResourcePath()).concat(
					theme.getCssPath());
			}
		}

		return servletContext.getRealPath(theme.getCssPath());
	}

	private static String _getRtlCustomFileName(String fileName) {
		int pos = fileName.lastIndexOf(StringPool.PERIOD);

		return fileName.substring(0, pos) + "_rtl" + fileName.substring(pos);
	}

	private static URL _getRtlCustomResourceURL(
			ServletContext servletContext, String resourcePath)
		throws Exception {

		return servletContext.getResource(_getRtlCustomFileName(resourcePath));
	}

	private static File _getSassTempDir(ServletContext servletContext) {
		File sassTempDir = (File)servletContext.getAttribute(_SASS_DIR_KEY);

		if (sassTempDir != null) {
			return sassTempDir;
		}

		File tempDir = (File)servletContext.getAttribute(
			JavaConstants.JAVAX_SERVLET_CONTEXT_TEMPDIR);

		sassTempDir = new File(tempDir, _SASS_DIR);

		sassTempDir.mkdirs();

		servletContext.setAttribute(_SASS_DIR_KEY, sassTempDir);

		return sassTempDir;
	}

	private static Theme _getTheme(HttpServletRequest request)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(request);

		String themeId = ParamUtil.getString(request, "themeId");

		if (Validator.isNotNull(themeId)) {
			try {
				Theme theme = ThemeLocalServiceUtil.getTheme(
					companyId, themeId, false);

				return theme;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		String requestURI = URLDecoder.decode(
			request.getRequestURI(), StringPool.UTF8);

		Matcher portalThemeMatcher = _portalThemePattern.matcher(requestURI);

		if (portalThemeMatcher.find()) {
			String themePathId = portalThemeMatcher.group(1);

			themePathId = StringUtil.replace(
				themePathId, StringPool.UNDERLINE, StringPool.BLANK);

			themeId = PortalUtil.getJsSafePortletId(themePathId);
		}
		else {
			Matcher pluginThemeMatcher = _pluginThemePattern.matcher(
				requestURI);

			if (pluginThemeMatcher.find()) {
				String themePathId = pluginThemeMatcher.group(1);

				themePathId = StringUtil.replace(
					themePathId, StringPool.UNDERLINE, StringPool.BLANK);

				StringBundler sb = new StringBundler(4);

				sb.append(themePathId);
				sb.append(PortletConstants.WAR_SEPARATOR);
				sb.append(themePathId);
				sb.append("theme");

				themePathId = sb.toString();

				themeId = PortalUtil.getJsSafePortletId(themePathId);
			}
		}

		if (Validator.isNull(themeId)) {
			return null;
		}

		try {
			Theme theme = ThemeLocalServiceUtil.getTheme(
				companyId, themeId, false);

			return theme;
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	private static String _getThemeImagesPath(
			HttpServletRequest request, ThemeDisplay themeDisplay, Theme theme)
		throws Exception {

		String themeImagesPath = null;

		if (themeDisplay != null) {
			themeImagesPath = themeDisplay.getPathThemeImages();
		}
		else {
			String cdnHost = PortalUtil.getCDNHost(request);
			String themeStaticResourcePath = theme.getStaticResourcePath();

			themeImagesPath =
				cdnHost + themeStaticResourcePath + theme.getImagesPath();
		}

		return themeImagesPath;
	}

	private static boolean _isCacheResourceUpToDate(
		URLConnection cacheResourceURLConnection,
		URLConnection resourceURLConnection) {

		if (!_SUPPORTS_THEME_CSS_FAST_LOAD_DISABLED) {
			return true;
		}

		if (cacheResourceURLConnection.getLastModified() ==
				resourceURLConnection.getLastModified()) {

			return true;
		}
		else {
			return false;
		}
	}

	private static boolean _isThemeCssFastLoad(
		HttpServletRequest request, ThemeDisplay themeDisplay) {

		if (!_SUPPORTS_THEME_CSS_FAST_LOAD_DISABLED) {
			return true;
		}

		if (themeDisplay != null) {
			return themeDisplay.isThemeCssFastLoad();
		}

		return SessionParamUtil.getBoolean(
			request, "css_fast_load", PropsValues.THEME_CSS_FAST_LOAD);
	}

	private static String _parseSass(
			ServletContext servletContext, HttpServletRequest request,
			ThemeDisplay themeDisplay, Theme theme, String resourcePath,
			String content)
		throws Exception {

		Map<String, Object> inputObjects = new HashMap<String, Object>();

		String cssThemePath = _getCssThemePath(
			servletContext, request, themeDisplay, theme);

		inputObjects.put("content", content);
		inputObjects.put("cssRealPath", resourcePath);
		inputObjects.put("cssThemePath", cssThemePath);

		File sassTempDir = _getSassTempDir(servletContext);

		inputObjects.put("sassCachePath", sassTempDir.getCanonicalPath());

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		UnsyncPrintWriter unsyncPrintWriter = UnsyncPrintWriterPool.borrow(
			unsyncByteArrayOutputStream);

		inputObjects.put("out", unsyncPrintWriter);

		_rubyExecutor.eval(null, inputObjects, null, _rubyScript);

		unsyncPrintWriter.flush();

		return unsyncByteArrayOutputStream.toString();
	}

	private static String _parseStaticTokens(String content) {
		return StringUtil.replace(
			content,
			new String[] {
				"@model_hints_constants_text_display_height@",
				"@model_hints_constants_text_display_width@",
				"@model_hints_constants_textarea_display_height@",
				"@model_hints_constants_textarea_display_width@"
			},
			new String[] {
				ModelHintsConstants.TEXT_DISPLAY_HEIGHT,
				ModelHintsConstants.TEXT_DISPLAY_WIDTH,
				ModelHintsConstants.TEXTAREA_DISPLAY_HEIGHT,
				ModelHintsConstants.TEXTAREA_DISPLAY_WIDTH
			});
	}

	/**
	 * @see {@link AggregateFilter#aggregateCss(String, String)}
	 */
	private static String propagateQueryString(
		String content, String queryString) {

		StringBuilder sb = new StringBuilder(content.length());

		int pos = 0;

		while (true) {
			int importX = content.indexOf(_CSS_IMPORT_BEGIN, pos);
			int importY = content.indexOf(
				_CSS_IMPORT_END, importX + _CSS_IMPORT_BEGIN.length());

			if ((importX == -1) || (importY == -1)) {
				sb.append(content.substring(pos));

				break;
			}

			sb.append(content.substring(pos, importY));
			sb.append(CharPool.QUESTION);
			sb.append(queryString);
			sb.append(_CSS_IMPORT_END);

			pos = importY + _CSS_IMPORT_END.length();
		}

		return sb.toString();
	}

	private static final String _CSS_IMPORT_BEGIN = "@import url(";

	private static final String _CSS_IMPORT_END = ");";

	private static final String _SASS_DIR = "sass";

	private static final String _SASS_DIR_KEY =
		DynamicCSSUtil.class.getName() + "#sass";

	private static final boolean _SUPPORTS_THEME_CSS_FAST_LOAD_DISABLED = false;

	private static Log _log = LogFactoryUtil.getLog(DynamicCSSUtil.class);

	private static Pattern _pluginThemePattern = Pattern.compile(
		"\\/([^\\/]+)-theme\\/", Pattern.CASE_INSENSITIVE);
	private static Pattern _portalThemePattern = Pattern.compile(
		"themes\\/([^\\/]+)\\/css", Pattern.CASE_INSENSITIVE);
	private static RubyExecutor _rubyExecutor = new RubyExecutor();
	private static String _rubyScript;

}