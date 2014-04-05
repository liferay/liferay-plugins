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

package com.liferay.portal.workflow.kaleo.service.base;

import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoDefinitionServiceClpInvoker {
	public KaleoDefinitionServiceClpInvoker() {
		_methodName86 = "getBeanIdentifier";

		_methodParameterTypes86 = new String[] {  };

		_methodName87 = "setBeanIdentifier";

		_methodParameterTypes87 = new String[] { "java.lang.String" };

		_methodName92 = "getKaleoDefinitions";

		_methodParameterTypes92 = new String[] { "int", "int" };

		_methodName93 = "getKaleoDefinitions";

		_methodParameterTypes93 = new String[] { "long", "int", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return KaleoDefinitionServiceUtil.getBeanIdentifier();
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			KaleoDefinitionServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return KaleoDefinitionServiceUtil.getKaleoDefinitions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return KaleoDefinitionServiceUtil.getKaleoDefinitions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName93;
	private String[] _methodParameterTypes93;
}