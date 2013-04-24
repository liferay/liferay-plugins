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

package com.liferay.httpservice.internal;

import com.liferay.httpservice.internal.event.EventUtil;
import com.liferay.httpservice.internal.servlet.BundleServletContext;
import com.liferay.httpservice.internal.servlet.WebExtenderServlet;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class WebBundleDeployer {

	public WebBundleDeployer(WebExtenderServlet webExtenderServlet)
		throws Exception {

		_webExtenderServlet = webExtenderServlet;
	}

	public void close() {
		Set<String> servletContextNames = ServletContextPool.keySet();

		for (String servletContextName : servletContextNames) {
			ServletContext servletContext = ServletContextPool.get(
				servletContextName);

			if (!(servletContext instanceof BundleServletContext)) {
				continue;
			}

			BundleServletContext bundleServletContext =
				(BundleServletContext)servletContext;

			Bundle bundle = bundleServletContext.getBundle();

			try {
				doStop(bundle, servletContextName);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		_webExtenderServlet = null;
	}

	public void doStart(Bundle bundle, String servletContextName) {
		if (bundle.getState() != Bundle.ACTIVE) {
			return;
		}

		EventUtil.sendEvent(bundle, EventUtil.DEPLOYING, null, false);

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		if (servletContext != null) {
			EventUtil.sendEvent(bundle, EventUtil.FAILED, null, true);

			_collidedWABBundleIds.add(bundle.getBundleId());

			return;
		}

		try {
			BundleServletContext bundleServletContext =
				new BundleServletContext(
					bundle, servletContextName,
					_webExtenderServlet.getServletContext());

			bundleServletContext.open();
		}
		catch (Exception e) {
			EventUtil.sendEvent(bundle, EventUtil.FAILED, e, false);
		}
	}

	public void doStop(Bundle bundle, String servletContextName) {
		EventUtil.sendEvent(bundle, EventUtil.UNDEPLOYING, null, false);

		BundleServletContext bundleServletContext = null;

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		if ((servletContext != null) &&
			(servletContext instanceof BundleServletContext)) {

			bundleServletContext = (BundleServletContext)servletContext;
		}

		if (bundleServletContext == null) {
			EventUtil.sendEvent(bundle, EventUtil.UNDEPLOYED, null, false);

			return;
		}

		try {
			bundleServletContext.close();
		}
		catch (Exception e) {
			EventUtil.sendEvent(bundle, EventUtil.FAILED, null, false);
		}

		EventUtil.sendEvent(bundle, EventUtil.UNDEPLOYED, null, false);

		handleCollidedWABs(bundle, servletContextName);
	}

	protected void handleCollidedWABs(
		Bundle bundle, String servletContextName) {

		if (_collidedWABBundleIds.isEmpty()) {
			return;
		}

		BundleContext bundleContext = _webExtenderServlet.getBundleContext();

		Iterator<Long> iterator = _collidedWABBundleIds.iterator();

		while (iterator.hasNext()) {
			long bundleId = iterator.next();

			Bundle curBundle = bundleContext.getBundle(bundleId);

			if (curBundle == null) {
				iterator.remove();

				continue;
			}

			String curServletContextName =
				BundleServletContext.getServletContextName(curBundle);

			if (servletContextName.equals(curServletContextName) &&
				(bundle.getBundleId() != curBundle.getBundleId())) {

				iterator.remove();

				doStart(curBundle, servletContextName);

				break;
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(WebBundleDeployer.class);

	private List<Long> _collidedWABBundleIds = Collections.synchronizedList(
		new ArrayList<Long>());
	private WebExtenderServlet _webExtenderServlet;

}