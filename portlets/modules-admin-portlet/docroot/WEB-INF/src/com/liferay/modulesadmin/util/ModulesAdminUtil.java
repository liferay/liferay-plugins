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

package com.liferay.modulesadmin.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;
import org.osgi.framework.startlevel.BundleStartLevel;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;

/**
 * @author Raymond Augé
 * @author Miguel pastor
 */
public class ModulesAdminUtil {

	public String displayImports(PageContext pageContext, Bundle bundle) {
		BundleWiring bundleWiring = getBundleWiring(bundle);

		List<BundleRequirement> bundleRequirements =
			bundleWiring.getRequirements(BundleRevision.PACKAGE_NAMESPACE);

		if (bundleRequirements.isEmpty()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		for (BundleRequirement bundleRequirement : bundleRequirements) {
			Map<String, String> directives = bundleRequirement.getDirectives();

			String resolution = directives.get("resolution");

			if (resolution.equals("dynamic")) {
				continue;
			}

			String filter = directives.get("filter");

			Matcher matcher = _pattern.matcher(filter);

			if (matcher.matches()) {
				String packageName = matcher.group(1);

				sb.append(packageName);
			}
			else {
				_log.error("Unable to parse " + filter);

				continue;
			}

			boolean satisfied = false;

			List<BundleWire> bundleWires = bundleWiring.getRequiredWires(
				BundleRevision.PACKAGE_NAMESPACE);

			for (BundleWire bundleWire : bundleWires) {
				BundleCapability bundleCapability = bundleWire.getCapability();

				if (!bundleRequirement.matches(bundleCapability)) {
					continue;
				}

				satisfied = true;

				Map<String, Object> attributes =
					bundleCapability.getAttributes();

				Version version = (Version)attributes.get("version");

				sb.append(" {");

				if (!version.equals(Version.emptyVersion)) {
					sb.append(version);
					sb.append(", ");
				}

				BundleWiring providerBundleWiring =
					bundleWire.getProviderWiring();

				BundleRevision bundleRevision =
					providerBundleWiring.getRevision();

				sb.append(bundleRevision.getSymbolicName());

				sb.append("[");

				Bundle bundleRevisionBundle = bundleRevision.getBundle();

				sb.append(bundleRevisionBundle.getBundleId());

				sb.append("]}");
			}

			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			if (!satisfied) {
				sb.append(" <strong class=\"resolved\">");
				sb.append(LanguageUtil.get(request, "un-resolved"));
				sb.append("</strong>");
			}

			if (resolution.equals("optional")) {
				sb.append(" <strong class=\"resolution\">");
				sb.append(LanguageUtil.get(request, resolution));
				sb.append("</strong>");
			}

			sb.append("<br />");
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	public BundleStartLevel getBundleStartLevel(Bundle bundle) {
		return bundle.adapt(BundleStartLevel.class);
	}

	public BundleWiring getBundleWiring(Bundle bundle) {
		return bundle.adapt(BundleWiring.class);
	}

	public Dictionary<String, String> getHeaders(
		Bundle bundle, String languageId) {

		return bundle.getHeaders(languageId);
	}

	public List<ServiceReference<?>> getInUseServiceReferences(Bundle bundle) {
		return toList(bundle.getServicesInUse());
	}

	public List<ServiceReference<?>> getRegisteredServiceReferences(
		Bundle bundle) {

		return toList(bundle.getRegisteredServices());
	}

	protected List<ServiceReference<?>> toList(
		ServiceReference<?>[] serviceReferences) {

		if (serviceReferences == null) {
			serviceReferences = new ServiceReference[0];
		}

		return Arrays.asList(serviceReferences);
	}

	private static Log _log = LogFactoryUtil.getLog(ModulesAdminUtil.class);

	private Pattern _pattern = Pattern.compile(
		"\\(&\\(osgi\\.wiring\\.package=([\\w\\.\\*]+)\\)(\\(version>=?([\\w" +
			"\\.-]+)\\)|\\(&\\(version>=?([\\w\\.-]+)\\)\\(\\!\\(version>=?" +
				"([\\w\\.-]+)\\)\\)\\))?(.*)\\)");

}