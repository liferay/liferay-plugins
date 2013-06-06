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

package com.liferay.polls.service.base;

import com.liferay.polls.service.PollsQuestionServiceUtil;

import java.util.Arrays;

/**
 * @author Juan Fern√°ndez
 * @generated
 */
public class PollsQuestionServiceClpInvoker {
	public PollsQuestionServiceClpInvoker() {
		_methodName32 = "getBeanIdentifier";

		_methodParameterTypes32 = new String[] {  };

		_methodName33 = "setBeanIdentifier";

		_methodParameterTypes33 = new String[] { "java.lang.String" };

		_methodName38 = "addPollsQuestion";

		_methodParameterTypes38 = new String[] {
				"java.util.Map", "java.util.Map", "int", "int", "int", "int",
				"int", "boolean", "java.util.List",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName39 = "deletePollsQuestion";

		_methodParameterTypes39 = new String[] { "long" };

		_methodName40 = "getPollsQuestion";

		_methodParameterTypes40 = new String[] { "long" };

		_methodName41 = "updatePollsQuestion";

		_methodParameterTypes41 = new String[] {
				"long", "java.util.Map", "java.util.Map", "int", "int", "int",
				"int", "int", "boolean", "java.util.List",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return PollsQuestionServiceUtil.getBeanIdentifier();
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			PollsQuestionServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return PollsQuestionServiceUtil.addPollsQuestion((java.util.Map<java.util.Locale, java.lang.String>)arguments[0],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Boolean)arguments[7]).booleanValue(),
				(java.util.List<com.liferay.polls.model.PollsChoice>)arguments[8],
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			return PollsQuestionServiceUtil.deletePollsQuestion(((Long)arguments[0]).longValue());
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return PollsQuestionServiceUtil.getPollsQuestion(((Long)arguments[0]).longValue());
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return PollsQuestionServiceUtil.updatePollsQuestion(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Boolean)arguments[8]).booleanValue(),
				(java.util.List<com.liferay.polls.model.PollsChoice>)arguments[9],
				(com.liferay.portal.service.ServiceContext)arguments[10]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName38;
	private String[] _methodParameterTypes38;
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
}