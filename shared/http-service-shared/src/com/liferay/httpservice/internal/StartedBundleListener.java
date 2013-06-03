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

import com.liferay.httpservice.internal.servlet.BundleServletContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class StartedBundleListener implements BundleListener {

	public StartedBundleListener(WebBundleDeployer webBundleDeployer) {
		_webBundleDeployer = webBundleDeployer;
	}

	@Override
	public void bundleChanged(BundleEvent bundleEvent) {
		int type = bundleEvent.getType();

		Bundle bundle = bundleEvent.getBundle();

		String servletContextName = BundleServletContext.getServletContextName(
			bundle);

		if (Validator.isNull(servletContextName)) {
			return;
		}

		try {
			if (type == BundleEvent.STARTED) {
				_webBundleDeployer.doStart(bundle, servletContextName);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		StartedBundleListener.class);

	private WebBundleDeployer _webBundleDeployer;

}