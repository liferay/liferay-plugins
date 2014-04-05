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

package com.liferay.so.activities.service.base;

import com.liferay.so.activities.service.SocialActivityInterpreterLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialActivityInterpreterLocalServiceClpInvoker {
	public SocialActivityInterpreterLocalServiceClpInvoker() {
		_methodName34 = "getBeanIdentifier";

		_methodParameterTypes34 = new String[] {  };

		_methodName35 = "setBeanIdentifier";

		_methodParameterTypes35 = new String[] { "java.lang.String" };

		_methodName38 = "addActivityInterpreter";

		_methodParameterTypes38 = new String[] {
				"com.liferay.so.activities.model.SocialActivityInterpreter"
			};

		_methodName39 = "deleteActivityInterpreter";

		_methodParameterTypes39 = new String[] {
				"com.liferay.so.activities.model.SocialActivityInterpreter"
			};

		_methodName40 = "interpret";

		_methodParameterTypes40 = new String[] {
				"java.lang.String",
				"com.liferay.portlet.social.model.SocialActivity",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName41 = "interpret";

		_methodParameterTypes41 = new String[] {
				"java.lang.String",
				"com.liferay.so.activities.model.SocialActivitySet",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName42 = "updateActivitySet";

		_methodParameterTypes42 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return SocialActivityInterpreterLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			SocialActivityInterpreterLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			SocialActivityInterpreterLocalServiceUtil.addActivityInterpreter((com.liferay.so.activities.model.SocialActivityInterpreter)arguments[0]);

			return null;
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			SocialActivityInterpreterLocalServiceUtil.deleteActivityInterpreter((com.liferay.so.activities.model.SocialActivityInterpreter)arguments[0]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return SocialActivityInterpreterLocalServiceUtil.interpret((java.lang.String)arguments[0],
				(com.liferay.portlet.social.model.SocialActivity)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return SocialActivityInterpreterLocalServiceUtil.interpret((java.lang.String)arguments[0],
				(com.liferay.so.activities.model.SocialActivitySet)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			SocialActivityInterpreterLocalServiceUtil.updateActivitySet(((Long)arguments[0]).longValue());

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
	private String _methodName38;
	private String[] _methodParameterTypes38;
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName42;
	private String[] _methodParameterTypes42;
}