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
		_methodName28 = "getBeanIdentifier";

		_methodParameterTypes28 = new String[] {  };

		_methodName29 = "setBeanIdentifier";

		_methodParameterTypes29 = new String[] { "java.lang.String" };

		_methodName32 = "addActivityInterpreter";

		_methodParameterTypes32 = new String[] {
				"com.liferay.so.activities.model.SocialActivityInterpreter"
			};

		_methodName33 = "deleteActivityInterpreter";

		_methodParameterTypes33 = new String[] {
				"com.liferay.so.activities.model.SocialActivityInterpreter"
			};

		_methodName34 = "interpret";

		_methodParameterTypes34 = new String[] {
				"java.lang.String",
				"com.liferay.portlet.social.model.SocialActivity",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName35 = "interpret";

		_methodParameterTypes35 = new String[] {
				"java.lang.String",
				"com.liferay.so.activities.model.SocialActivitySet",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName36 = "updateActivitySet";

		_methodParameterTypes36 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return SocialActivityInterpreterLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			SocialActivityInterpreterLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			SocialActivityInterpreterLocalServiceUtil.addActivityInterpreter((com.liferay.so.activities.model.SocialActivityInterpreter)arguments[0]);

			return null;
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			SocialActivityInterpreterLocalServiceUtil.deleteActivityInterpreter((com.liferay.so.activities.model.SocialActivityInterpreter)arguments[0]);

			return null;
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return SocialActivityInterpreterLocalServiceUtil.interpret((java.lang.String)arguments[0],
				(com.liferay.portlet.social.model.SocialActivity)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return SocialActivityInterpreterLocalServiceUtil.interpret((java.lang.String)arguments[0],
				(com.liferay.so.activities.model.SocialActivitySet)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			SocialActivityInterpreterLocalServiceUtil.updateActivitySet(((Long)arguments[0]).longValue());

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
	private String _methodName36;
	private String[] _methodParameterTypes36;
}