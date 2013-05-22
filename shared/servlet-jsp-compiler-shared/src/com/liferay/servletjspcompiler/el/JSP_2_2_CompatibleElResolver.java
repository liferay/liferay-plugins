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

package com.liferay.servletjspcompiler.el;

import java.beans.FeatureDescriptor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.PageContext;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class JSP_2_2_CompatibleElResolver extends ELResolver {

	@Override
	public Class<?> getCommonPropertyType(ELContext elContext, Object base) {
		if (base == null) {
			return String.class;
		}

		return null;
	}

	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(
		ELContext elContext, Object base) {

		List<FeatureDescriptor> featureDescriptors =
			new ArrayList<FeatureDescriptor>();

		PageContext pageContext = (PageContext)elContext.getContext(
			JspContext.class);

		Enumeration<?> enumeration = pageContext.getAttributeNamesInScope(
			PageContext.PAGE_SCOPE);

		while (enumeration.hasMoreElements()) {
			String name = (String)enumeration.nextElement();

			Object value = pageContext.getAttribute(
				name, PageContext.PAGE_SCOPE);

			FeatureDescriptor featureDescriptor = getFeatureDescriptor(
				name, value, "page");

			featureDescriptors.add(featureDescriptor);
		}

		enumeration = pageContext.getAttributeNamesInScope(
			PageContext.REQUEST_SCOPE);

		while (enumeration.hasMoreElements()) {
			String name = (String)enumeration.nextElement();

			Object value = pageContext.getAttribute(
				name, PageContext.REQUEST_SCOPE);

			FeatureDescriptor featureDescriptor = getFeatureDescriptor(
				name, value, "request");

			featureDescriptors.add(featureDescriptor);
		}

		enumeration = pageContext.getAttributeNamesInScope(
			PageContext.SESSION_SCOPE);

		while (enumeration.hasMoreElements()) {
			String name = (String)enumeration.nextElement();

			Object value = pageContext.getAttribute(
				name, PageContext.SESSION_SCOPE);

			FeatureDescriptor featureDescriptor = getFeatureDescriptor(
				name, value, "session");

			featureDescriptors.add(featureDescriptor);
		}

		enumeration = pageContext.getAttributeNamesInScope(
			PageContext.APPLICATION_SCOPE);

		while (enumeration.hasMoreElements()) {
			String name = (String)enumeration.nextElement();

			Object value = pageContext.getAttribute(
				name, PageContext.APPLICATION_SCOPE);

			FeatureDescriptor featureDescriptor = getFeatureDescriptor(
				name, value, "application");

			featureDescriptors.add(featureDescriptor);
		}

		return featureDescriptors.iterator();
	}

	@Override
	public Class<?> getType(ELContext elContext, Object base, Object property) {
		if (elContext == null) {
			throw new NullPointerException();
		}

		if (base == null) {
			elContext.setPropertyResolved(true);

			return Object.class;
		}

		return null;
	}

	@Override
	public Object getValue(ELContext elContext, Object base, Object property) {
		if (elContext == null) {
			throw new NullPointerException();
		}

		if (base == null) {
			elContext.setPropertyResolved(true);

			if (property instanceof String) {
				String attribute = (String)property;

				PageContext pageContext = (PageContext)elContext.getContext(
					JspContext.class);

				return pageContext.findAttribute(attribute);
			}
		}

		return null;
	}

	@Override
	public boolean isReadOnly(
		ELContext elContext, Object base, Object property) {

		if (elContext == null) {
			throw new NullPointerException();
		}

		if (base == null) {
			elContext.setPropertyResolved(true);
		}

		return false;
	}

	@Override
	public void setValue(
		ELContext elContext, Object base, Object property, Object value) {

		if (elContext == null) {
			throw new NullPointerException();
		}

		if (base != null) {
			return;
		}

		elContext.setPropertyResolved(true);

		if (property instanceof String) {
			PageContext pageContext = (PageContext)elContext.getContext(
				JspContext.class);

			String attribute = (String)property;

			if (pageContext.getAttribute(
					attribute, PageContext.REQUEST_SCOPE) != null) {

				pageContext.setAttribute(
					attribute, value, PageContext.REQUEST_SCOPE);
			}
			else if (pageContext.getAttribute(
						attribute, PageContext.SESSION_SCOPE) != null) {

				pageContext.setAttribute(
					attribute, value, PageContext.SESSION_SCOPE);
			}
			else if (pageContext.getAttribute(
						attribute, PageContext.APPLICATION_SCOPE) != null) {

				pageContext.setAttribute(
					attribute, value, PageContext.APPLICATION_SCOPE);
			}
			else {
				pageContext.setAttribute(
					attribute, value, PageContext.PAGE_SCOPE);
			}
		}
	}

	protected FeatureDescriptor getFeatureDescriptor(
		String name, Object value, String scopeName) {

		FeatureDescriptor featureDescriptor = new FeatureDescriptor();

		featureDescriptor.setDisplayName(name);
		featureDescriptor.setExpert(false);
		featureDescriptor.setHidden(false);
		featureDescriptor.setName(name);
		featureDescriptor.setPreferred(true);
		featureDescriptor.setShortDescription(scopeName + " scope attribute");
		featureDescriptor.setValue("resolvableAtDesignTime", Boolean.TRUE);
		featureDescriptor.setValue("type", value.getClass());

		return featureDescriptor;
	}

}