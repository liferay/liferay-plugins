/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.proxy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Array;

import java.util.Map;

import javax.xml.namespace.QName;

import oasis.names.tc.wsrp.v1.types.CookieProtocol;
import oasis.names.tc.wsrp.v1.types.MarkupParams;
import oasis.names.tc.wsrp.v1.types.PropertyDescription;
import oasis.names.tc.wsrp.v1.types.RuntimeContext;
import oasis.names.tc.wsrp.v1.types.StateChange;
import oasis.names.tc.wsrp.v2.types.NavigationalContext;
import oasis.names.tc.wsrp.v2.types.Property;
import oasis.names.tc.wsrp.v2.types.SessionParams;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author Michael Young
 */
public class TypeConvertorUtil {

	public static Object convert(Object source, int sourceVersion)
		throws Exception {

		if (source == null) {
			return null;
		}

		String sourcePackage = _V1_PACKAGE;
		String destinationPackage = _V2_PACKAGE;

		if (sourceVersion == 2) {
			sourcePackage = _V2_PACKAGE;
			destinationPackage = _V1_PACKAGE;
		}

		Class<?> sourceClass = source.getClass();

		String sourceClassName = sourceClass.getSimpleName();

		Object destination = null;

		if (sourceClass.isArray()) {
			destination = source;

			Class<?> componentType = sourceClass.getComponentType();

			if (componentType.getName().contains(sourcePackage)) {
				Object[] sourceArray = (Object[])source;

				Class<?> destinationComponentType = Class.forName(
					destinationPackage + componentType.getSimpleName());

				Object[] destinationArray = (Object[])Array.newInstance(
					destinationComponentType, sourceArray.length);

				for (int i = 0; i < sourceArray.length; i++) {
					Object sourceArrayValue = sourceArray[i];

					destinationArray[i] = convert(
						sourceArrayValue, sourceVersion);
				}

				destination = destinationArray;
			}
		}
		else if (sourceClass == CookieProtocol.class) {
			CookieProtocol cookieProtocol = (CookieProtocol)source;

			destination =
				oasis.names.tc.wsrp.v2.types.CookieProtocol.fromValue(
					cookieProtocol.getValue());
		}
		else if (sourceClass ==
					oasis.names.tc.wsrp.v2.types.CookieProtocol.class) {

			oasis.names.tc.wsrp.v2.types.CookieProtocol cookieProtocol =
				(oasis.names.tc.wsrp.v2.types.CookieProtocol)source;

			destination = CookieProtocol.fromValue(cookieProtocol.getValue());
		}
		else if (sourceClass == StateChange.class) {
			StateChange stateChange = (StateChange)source;

			destination =
				oasis.names.tc.wsrp.v2.types.StateChange.fromValue(
					stateChange.getValue());
		}
		else if (sourceClass ==
					oasis.names.tc.wsrp.v2.types.StateChange.class) {

			oasis.names.tc.wsrp.v2.types.StateChange stateChange =
				(oasis.names.tc.wsrp.v2.types.StateChange)source;

			destination = StateChange.fromValue(stateChange.getValue());
		}
		else {
			Class <?> destinationClass = Class.forName(
				destinationPackage + sourceClassName);

			destination = destinationClass.newInstance();

			Map<String, Object> sourceChildren = PropertyUtils.describe(source);

			for (Map.Entry<String, Object> sourceChildEntry :
					sourceChildren.entrySet()) {

				String sourceChildName = sourceChildEntry.getKey();

				if (sourceChildName.equals("class")) {
					continue;
				}

				Object sourceChild = sourceChildEntry.getValue();

				if (sourceChild == null) {
					continue;
				}

				_convert(
					sourceVersion, sourcePackage, sourceClass, sourceChild,
					sourceChildName, destination);
			}
		}

		return destination;
	}

	private static void _convert(
			int sourceVersion, String sourcePackage, Object sourceClass,
			Object sourceChild, String sourceChildName, Object destination)
		throws Exception {

		Class<?> sourceChildClass = sourceChild.getClass();

		if (sourceChildClass == NavigationalContext.class) {
			sourceChildName = "navigationalState";

			NavigationalContext navigationalContext =
				(NavigationalContext)sourceChild;

			sourceChild = navigationalContext.getOpaqueValue();
		}
		else if (sourceChildClass == SessionParams.class) {
			sourceChildName = "sessionID";

			SessionParams sessionParams = (SessionParams)sourceChild;

			sourceChild = sessionParams.getSessionID();
		}

		if (sourceChild == null) {
			return;
		}

		sourceChildClass = sourceChild.getClass();

		Object destinationChild = null;

		if (sourceChildClass.isArray()) {
			destinationChild = convert(sourceChild, sourceVersion);
		}
		else {
			destinationChild = sourceChild;

			sourceChildClass = sourceChild.getClass();

			if (sourceChildClass.getName().contains(sourcePackage)) {
				destinationChild = convert(sourceChild, sourceVersion);
			}
		}

		String destinationChildName = sourceChildName;

		if (sourceChildName.equals("itemBinary")) {
			destinationChildName = "markupBinary";
		}
		else if (sourceChildName.equals("itemString")) {
			destinationChildName = "markupString";
		}
		else if (sourceChildName.equals("markupBinary")) {
			destinationChildName = "itemBinary";
		}
		else if (sourceChildName.equals("markupString")) {
			destinationChildName = "itemString";
		}
		else if (sourceChildName.equals("name") &&
				 (sourceClass == Property.class)) {

			QName qName = (QName)destinationChild;

			destinationChild = qName.getLocalPart();
		}
		else if (sourceChildName.equals("name") &&
				 (sourceClass == PropertyDescription.class)) {

			String name = (String)destinationChild;

			destinationChild = new QName("namespace", name, "prefix");
		}
		else if (sourceChildName.equals("navigationalState") &&
				 (sourceClass == MarkupParams.class)) {

			String navigationalState = (String)sourceChild;

			NavigationalContext navigationalContext = new NavigationalContext();

			navigationalContext.setOpaqueValue(navigationalState);

			destinationChild = navigationalContext;

			destinationChildName = "navigationalContext";
		}
		else if (sourceChildName.equals("requiresRewriting")) {
			destinationChildName = "requiresUrlRewriting";
		}
		else if (sourceChildName.equals("requiresUrlRewriting")) {
			destinationChildName = "requiresRewriting";
		}
		else if (sourceChildName.equals("sessionID") &&
				 (sourceClass == RuntimeContext.class)) {

			String sessionID = (String)sourceChild;

			SessionParams sessionParams = new SessionParams();

			sessionParams.setSessionID(sessionID);

			destinationChild = sessionParams;

			destinationChildName = "sessionParams";
		}

		try {
			PropertyUtils.setProperty(
				destination, destinationChildName, destinationChild);
		}
		catch (NoSuchMethodException nsme) {
			if (_log.isWarnEnabled()) {
				_log.warn(nsme, nsme);
			}
		}
	}

	private static final String _V1_PACKAGE = "oasis.names.tc.wsrp.v1.types.";

	private static final String _V2_PACKAGE = "oasis.names.tc.wsrp.v2.types.";

	private static Log _log = LogFactoryUtil.getLog(TypeConvertorUtil.class);

}