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

package com.liferay.screens.service.base;

import com.liferay.screens.service.ScreensDDLRecordServiceUtil;

import java.util.Arrays;

/**
 * @author José Manuel Navarro
 * @generated
 */
public class ScreensDDLRecordServiceClpInvoker {
	public ScreensDDLRecordServiceClpInvoker() {
		_methodName26 = "getBeanIdentifier";

		_methodParameterTypes26 = new String[] {  };

		_methodName27 = "setBeanIdentifier";

		_methodParameterTypes27 = new String[] { "java.lang.String" };

		_methodName30 = "getDDLRecord";

		_methodParameterTypes30 = new String[] { "long", "java.util.Locale" };

		_methodName31 = "getDDLRecords";

		_methodParameterTypes31 = new String[] {
				"long", "java.util.Locale", "int", "int"
			};

		_methodName32 = "getDDLRecords";

		_methodParameterTypes32 = new String[] {
				"long", "long", "java.util.Locale", "int", "int"
			};

		_methodName33 = "getDDLRecordsCount";

		_methodParameterTypes33 = new String[] { "long" };

		_methodName34 = "getDDLRecordsCount";

		_methodParameterTypes34 = new String[] { "long", "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return ScreensDDLRecordServiceUtil.getBeanIdentifier();
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			ScreensDDLRecordServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return ScreensDDLRecordServiceUtil.getDDLRecord(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			return ScreensDDLRecordServiceUtil.getDDLRecords(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return ScreensDDLRecordServiceUtil.getDDLRecords(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Locale)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			return ScreensDDLRecordServiceUtil.getDDLRecordsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return ScreensDDLRecordServiceUtil.getDDLRecordsCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
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
	private String _methodName34;
	private String[] _methodParameterTypes34;
}