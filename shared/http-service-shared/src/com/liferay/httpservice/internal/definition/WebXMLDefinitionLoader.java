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

package com.liferay.httpservice.internal.definition;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.net.URL;

import java.util.List;

import javax.servlet.Filter;
import javax.servlet.Servlet;

import org.osgi.framework.Bundle;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class WebXMLDefinitionLoader {

	public WebXMLDefinitionLoader() throws DocumentException {
		Class<?> clazz = getClass();

		Document document = SAXReaderUtil.read(
			clazz.getResource(
				"/com/liferay/httpservice/internal/servlet/dependencies/" +
					"default-web.xml"));

		_defaultWebXmlRootElement = document.getRootElement();
	}

	public WebXMLDefinition loadWebXML(Bundle bundle)
		throws DocumentException, IllegalAccessException,
			   InstantiationException {

		WebXMLDefinition webXML = new WebXMLDefinition();

		readContextParameters(bundle, _defaultWebXmlRootElement, webXML);
		readFilters(bundle, _defaultWebXmlRootElement, webXML);
		readListeners(bundle, _defaultWebXmlRootElement, webXML);
		readServlets(bundle, _defaultWebXmlRootElement, webXML);

		URL url = bundle.getEntry("WEB-INF/web.xml");

		if (url != null) {
			Document document = SAXReaderUtil.read(url);

			Element rootElement = document.getRootElement();

			readContextParameters(bundle, rootElement, webXML);
			readFilters(bundle, rootElement, webXML);
			readListeners(bundle, rootElement, webXML);
			readServlets(bundle, rootElement, webXML);
		}

		return webXML;
	}

	protected String getURLPattern(Element element) {
		String urlPattern = element.elementText("url-pattern");

		if (urlPattern.endsWith(_SLASH_STAR) && (urlPattern.length() > 2)) {
			urlPattern = urlPattern.substring(0, urlPattern.length() - 2);
		}

		if (urlPattern.startsWith(StringPool.STAR)) {
			urlPattern = StringPool.SLASH.concat(urlPattern);
		}

		return urlPattern;
	}

	protected void readContextParameters(
		Bundle bundle, Element rootElement, WebXMLDefinition webXML) {

		for (Element element : rootElement.elements("context-param")) {
			String name = element.elementText("param-name");
			String value = element.elementText("param-value");

			webXML.setContextParameter(name, value);
		}
	}

	protected void readFilters(
			Bundle bundle, Element rootElement, WebXMLDefinition webXML)
		throws IllegalAccessException, InstantiationException {

		List<Element> filterElements = rootElement.elements("filter");

		for (Element filterElement : filterElements) {
			FilterDefinition filterDefinition = new FilterDefinition();

			String filterClassName = filterElement.elementText("filter-class");

			Class<?> clazz = null;

			try {
				clazz = bundle.loadClass(filterClassName);
			}
			catch (Exception e) {
				_log.error("Unable to load filter " + filterClassName);

				continue;
			}

			Filter filter = (Filter)clazz.newInstance();

			filterDefinition.setFilter(filter);

			List<Element> initParamElements = filterElement.elements(
				"init-param");

			for (Element initParamElement : initParamElements) {
				String paramName = initParamElement.elementText("param-name");
				String paramValue = initParamElement.elementText("param-value");

				filterDefinition.setInitParameter(paramName, paramValue);
			}

			String filterName = filterElement.elementText("filter-name");

			filterDefinition.setName(filterName);

			List<Element> filterMappingElements = rootElement.elements(
				"filter-mapping");

			for (Element filterMappingElement : filterMappingElements) {
				String filterMappingElementFilterName =
					filterMappingElement.elementText("filter-name");

				if (filterMappingElementFilterName.equals(filterName)) {
					String urlPattern = getURLPattern(filterMappingElement);

					filterDefinition.addURLPattern(urlPattern);
				}
			}

			webXML.setFilterDefinition(filterName, filterDefinition);
		}
	}

	protected void readListeners(
			Bundle bundle, Element rootElement, WebXMLDefinition webXML)
		throws IllegalAccessException, InstantiationException {

		List<Element> listenerElements = rootElement.elements("listener");

		for (Element listenerElement : listenerElements) {
			ListenerDefinition listenerDefinition = new ListenerDefinition();

			String listenerClassName = listenerElement.elementText(
				"listener-class");

			Class<?> clazz = null;

			try {
				clazz = bundle.loadClass(listenerClassName);
			}
			catch (Exception e) {
				_log.error("Unable to load listener " + listenerClassName);

				continue;
			}

			Object listener = clazz.newInstance();

			listenerDefinition.setListener(listener);

			webXML.addListenerDefinition(listenerDefinition);
		}
	}

	protected void readServlets(
			Bundle bundle, Element rootElement, WebXMLDefinition webXML)
		throws IllegalAccessException, InstantiationException {

		List<Element> servletElements = rootElement.elements("servlet");

		for (Element servletElement : servletElements) {
			ServletDefinition servletDefinition = new ServletDefinition();

			String servletClassName = servletElement.elementText(
				"servlet-class");

			Class<?> servletClass = null;;

			try {
				servletClass = bundle.loadClass(servletClassName);
			}
			catch (Exception e) {
				_log.error("Unable to load servlet " + servletClassName);

				continue;
			}

			Servlet servlet = (Servlet)servletClass.newInstance();

			servletDefinition.setServlet(servlet);

			List<Element> initParamElements = servletElement.elements(
				"init-param");

			for (Element initParamElement : initParamElements) {
				String paramName = initParamElement.elementText("param-name");
				String paramValue = initParamElement.elementText("param-value");

				servletDefinition.setInitParameter(paramName, paramValue);
			}

			String servletName = servletElement.elementText("servlet-name");

			servletDefinition.setName(servletName);

			List<Element> servletMappingElements = rootElement.elements(
				"servlet-mapping");

			for (Element servletMappingElement : servletMappingElements) {
				String servletMappingElementServletName =
					servletMappingElement.elementText("servlet-name");

				if (servletMappingElementServletName.equals(servletName)) {
					String urlPattern = getURLPattern(servletMappingElement);

					servletDefinition.addURLPattern(urlPattern);
				}
			}

			webXML.setServletDefinition(servletName, servletDefinition);
		}
	}

	private static final String _SLASH_STAR = "/*";

	private static Log _log = LogFactoryUtil.getLog(
		WebXMLDefinitionLoader.class);

	private Element _defaultWebXmlRootElement;

}