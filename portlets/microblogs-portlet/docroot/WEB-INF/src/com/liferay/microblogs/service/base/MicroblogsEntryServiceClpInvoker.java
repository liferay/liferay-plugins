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

package com.liferay.microblogs.service.base;

import com.liferay.microblogs.service.MicroblogsEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MicroblogsEntryServiceClpInvoker {
	public MicroblogsEntryServiceClpInvoker() {
		_methodName20 = "getBeanIdentifier";

		_methodParameterTypes20 = new String[] {  };

		_methodName21 = "setBeanIdentifier";

		_methodParameterTypes21 = new String[] { "java.lang.String" };

		_methodName26 = "addMicroblogsEntry";

		_methodParameterTypes26 = new String[] {
				"long", "java.lang.String", "int", "long", "long", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName27 = "deleteMicroblogsEntry";

		_methodParameterTypes27 = new String[] { "long" };

		_methodName28 = "getMicroblogsEntries";

		_methodParameterTypes28 = new String[] { "int", "int" };

		_methodName29 = "getMicroblogsEntries";

		_methodParameterTypes29 = new String[] { "java.lang.String", "int", "int" };

		_methodName30 = "getMicroblogsEntriesCount";

		_methodParameterTypes30 = new String[] {  };

		_methodName31 = "getMicroblogsEntriesCount";

		_methodParameterTypes31 = new String[] { "java.lang.String" };

		_methodName32 = "getMicroblogsEntry";

		_methodParameterTypes32 = new String[] { "long" };

		_methodName33 = "getUserMicroblogsEntries";

		_methodParameterTypes33 = new String[] { "long", "int", "int" };

		_methodName34 = "getUserMicroblogsEntries";

		_methodParameterTypes34 = new String[] { "long", "int", "int", "int" };

		_methodName35 = "getUserMicroblogsEntriesCount";

		_methodParameterTypes35 = new String[] { "long" };

		_methodName36 = "getUserMicroblogsEntriesCount";

		_methodParameterTypes36 = new String[] { "long", "int" };

		_methodName37 = "updateMicroblogsEntry";

		_methodParameterTypes37 = new String[] {
				"long", "java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			MicroblogsEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return MicroblogsEntryServiceUtil.addMicroblogsEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return MicroblogsEntryServiceUtil.deleteMicroblogsEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntries((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntriesCount();
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntriesCount((java.lang.String)arguments[0]);
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntriesCount(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			return MicroblogsEntryServiceUtil.updateMicroblogsEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName20;
	private String[] _methodParameterTypes20;
	private String _methodName21;
	private String[] _methodParameterTypes21;
	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
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
	private String _methodName35;
	private String[] _methodParameterTypes35;
	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
}