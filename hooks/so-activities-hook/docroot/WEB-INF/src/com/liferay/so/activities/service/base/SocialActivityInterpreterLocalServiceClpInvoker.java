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

package com.liferay.so.activities.service.base;

import com.liferay.so.activities.service.SocialActivityInterpreterLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialActivityInterpreterLocalServiceClpInvoker {
	public SocialActivityInterpreterLocalServiceClpInvoker() {
		_methodName26 = "getBeanIdentifier";

		_methodParameterTypes26 = new String[] {  };

		_methodName27 = "setBeanIdentifier";

		_methodParameterTypes27 = new String[] { "java.lang.String" };

		_methodName30 = "addActivityInterpreter";

		_methodParameterTypes30 = new String[] {
				"com.liferay.so.activities.model.SocialActivityInterpreter"
			};

		_methodName31 = "deleteActivityInterpreter";

		_methodParameterTypes31 = new String[] {
				"com.liferay.so.activities.model.SocialActivityInterpreter"
			};

		_methodName32 = "interpret";

		_methodParameterTypes32 = new String[] {
				"java.lang.String",
				"com.liferay.so.activities.model.SocialActivity",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName33 = "interpret";

		_methodParameterTypes33 = new String[] {
				"java.lang.String",
				"com.liferay.so.activities.model.SocialActivitySet",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return SocialActivityInterpreterLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			SocialActivityInterpreterLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			SocialActivityInterpreterLocalServiceUtil.addActivityInterpreter((com.liferay.so.activities.model.SocialActivityInterpreter)arguments[0]);

			return null;
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			SocialActivityInterpreterLocalServiceUtil.deleteActivityInterpreter((com.liferay.so.activities.model.SocialActivityInterpreter)arguments[0]);

			return null;
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return SocialActivityInterpreterLocalServiceUtil.interpret((java.lang.String)arguments[0],
				(com.liferay.so.activities.model.SocialActivity)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			return SocialActivityInterpreterLocalServiceUtil.interpret((java.lang.String)arguments[0],
				(com.liferay.so.activities.model.SocialActivitySet)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
}