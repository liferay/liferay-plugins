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

package com.liferay.servletjspcompiler.compiler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.servletjspcompiler.compiler.internal.JspResolverFactory;
import com.liferay.servletjspcompiler.compiler.internal.util.PortletPropsValues;

import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import java.security.ProtectionDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.ServletContext;

import javax.tools.JavaFileManager;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;

import org.apache.jasper.Constants;
import org.apache.jasper.JspCompilationContext;
import org.apache.jasper.compiler.ErrorDispatcher;
import org.apache.jasper.compiler.Jsr199JavaCompiler;

import org.osgi.framework.Bundle;

import org.phidias.compile.BundleJavaManager;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class JspCompiler extends Jsr199JavaCompiler {

	public static Log _log = LogFactoryUtil.getLog(JspCompiler.class);

	@Override
	public void init(
		JspCompilationContext ctxt, ErrorDispatcher errDispatcher,
		boolean suppressLogging) {

		super.init(ctxt, errDispatcher, suppressLogging);

		ServletContext servletContext = ctxt.getServletContext();

		_bundle = (Bundle)servletContext.getAttribute("osgi-bundle");

		initClassPath(servletContext);
		initTLDMappings(servletContext);
	}

	protected void addDependenciesToClassPath() {
		for (String dependency : PortletPropsValues.JSP_COMPILER_DEPENDENCIES) {

			File file = new File(dependency);

			if (file.exists() && file.canRead()) {
				if (_classpath.contains(file)) {
					_classpath.remove(file);
				}

				_classpath.add(0, file);

				continue;
			}

			try {
				Class<?> clazz = Class.forName(
					dependency, true, ClassLoaderUtil.getPortalClassLoader());

				addDependencyToClassPath(clazz);

				continue;
			}
			catch (ClassNotFoundException e) {
			}

			if (_log.isErrorEnabled()) {
				_log.error(
					"Could not add depedency {" + dependency +
						"} to the classpath");
			}
		}
	}

	protected void addDependencyToClassPath(Class<?> clazz) {
		ProtectionDomain protectionDomain = clazz.getProtectionDomain();

		if (protectionDomain == null) {
			return;
		}

		URL location = protectionDomain.getCodeSource().getLocation();

		try {
			File file = new File(processJarLocation(location));

			if (file.exists() && file.canRead()) {

				// Make sure it's added at the beginning.

				if (_classpath.contains(file)) {
					_classpath.remove(file);
				}

				_classpath.add(0, file);
			}
		}
		catch (Exception use) {
			_log.error(use, use);
		}
	}

	@Override
	protected JavaFileManager getJavaFileManager(
		JavaFileManager javaFileManager) {

		if (javaFileManager instanceof StandardJavaFileManager) {
			StandardJavaFileManager standardJavaFileManager =
				(StandardJavaFileManager)javaFileManager;

			try {
				standardJavaFileManager.setLocation(
					StandardLocation.CLASS_PATH, _classpath);

				BundleJavaManager bundleJavaManager = new BundleJavaManager(
					_bundle, standardJavaFileManager, options, true);

				bundleJavaManager.setResourceResolver(
					JspResolverFactory.getResourceResolver());

				javaFileManager = bundleJavaManager;
			}
			catch (IOException e) {
				_log.error(e, e);
			}
		}

		return super.getJavaFileManager(javaFileManager);
	}

	protected void initClassPath(ServletContext servletContext) {
		_reentrantLock.lock();

		try {
			_classpath = (ArrayList<File>)servletContext.getAttribute(
				_JSP_COMPILER_CLASSPATH);

			if (_classpath != null) {
				return;
			}

			_classpath = new ArrayList<File>();

			addDependenciesToClassPath();

			servletContext.setAttribute(_JSP_COMPILER_CLASSPATH, _classpath);
		}
		finally {
			_reentrantLock.unlock();
		}
	}

	protected void initTLDMappings(ServletContext servletContext) {
		Map<String, String[]> mappings =
			(Map<String, String[]>)servletContext.getAttribute(
				Constants.JSP_TLD_URI_TO_LOCATION_MAP);

		if (mappings != null) {
			return;
		}

		mappings = new HashMap<String, String[]>();

		mappings.put(
			"http://java.sun.com/jsp/jstl/core",
			new String[] {"/WEB-INF/tld/c.tld", null});

		servletContext.setAttribute(
			Constants.JSP_TLD_URI_TO_LOCATION_MAP, mappings);
	}

	private URI processJarLocation(URL url)
		throws MalformedURLException, URISyntaxException {

		String protocol = url.getProtocol();

		if ("reference".equals(protocol)) {
			String file = url.getFile();

			url = new URL(file);

			protocol = url.getProtocol();
		}

		if ("file".equals(protocol)) {
			return url.toURI();
		}
		else if ("jar".equals(protocol)) {
			String file = url.getFile();

			return new URI(StringUtil.extractFirst(file, "!/"));
		}

		throw new URISyntaxException(
			url.toString(), "Unable to handle this URI");
	}

	private static final String _JSP_COMPILER_CLASSPATH =
		JspCompiler.class.getName().concat("_JSP_COMPILER_CLASSPATH");

	private Bundle _bundle;
	private List<File> _classpath;
	private ReentrantLock _reentrantLock = new ReentrantLock();

}